const scraperObject = {
    url: 'http://books.toscrape.com',
    async scraper(browser, pages){
        var curpage = 1;
        let page = await browser.newPage();
        console.log(`Navigating to ${this.url}...`);
        // Naviguer vers la page sélectionnée
        await Promise.all([
            page.goto(this.url,{waitUntil: 'domcontentloaded'}),
            page.waitForNetworkIdle({ idleTime: 250 }),
        ]);
        let scrapedData = []; // ajouté pour la pagination
        // Attendez que le DOM requis soit rendu
        async function scrapeCurrentPage(){
            await Promise.all([
                page.waitForSelector('.page_inner', { visible: true }),
                page.waitForNetworkIdle({ idleTime: 250 }),
            ]);
            // Obtenez le lien vers tous les livres requis
            let urls = await page.$$eval('section ol > li', links => {
                //console.log(links)
                // Assurez-vous que le livre à scraper est en stock
                links = links.filter(link => link.querySelector('.instock.availability > i').textContent !== "In stock")
                // Extraire les liens des données
                links = links.map(el => el.querySelector('h3 > a').href)
                return links;
            });
            // Parcourez chacun de ces liens, ouvrez une nouvelle instance de page et obtenez-en les données pertinentes
            let pagePromise = (link) => new Promise(async(resolve, reject) => {
                let dataObj = {};
                let newPage = await browser.newPage();
                await Promise.all([
                    newPage.goto(link,{waitUntil: 'domcontentloaded'}),
                    newPage.waitForNetworkIdle({ idleTime: 75 }),
                ]);
                try{
                    dataObj['bookTitle'] = await newPage.$eval('.product_main > h1', text => text.textContent);
                    dataObj['bookPrice'] = await newPage.$eval('.price_color', text => text.textContent);
                    dataObj['noAvailable'] = await newPage.$eval('.instock.availability', text => {
                        // Supprimer les espaces de nouvelle ligne et de tabulation
                        text = text.textContent.replace(/(\r\n\t|\n|\r|\t)/gm, "");
                        // Obtenir le nombre de stock disponible
                        let regexp = /^.*\((.*)\).*$/i;
                        let stockAvailable = regexp.exec(text)[1].split(' ')[0];
                        return stockAvailable;
                    });
                    dataObj['imageUrl'] = await newPage.$eval('#product_gallery img', img => img.src);
                    dataObj['bookDescription'] = await newPage.$eval('#product_description', div => div.nextSibling.nextSibling.textContent);
                    dataObj['upc'] = await newPage.$eval('.table.table-striped > tbody > tr > td', table => table.textContent);
                }catch (err){

                }
                await newPage.close();
                resolve(dataObj);
            });

            for(link in urls){
                let currentPageData = await pagePromise(urls[link]);
                if(!(JSON.stringify(currentPageData) === '{}')){
                    scrapedData.push(currentPageData);
                }
                // console.log(currentPageData);
            }
            // Lorsque toutes les données de cette page sont terminées, cliquez sur le bouton "Next"" et lancez le
            // scraping de la page suivante.
            // Vous allez d'abord vérifier si ce bouton existe, ainsi vous savez s'il y a vraiment une page suivante.
            let nextButtonExist = false;
            try{
                const nextButton = await page.$eval('.next > a', a => a.textContent);
                nextButtonExist = true;
            }
            catch(err){
                nextButtonExist = false;
            }
            if(nextButtonExist){
                if (curpage <= pages){
                    curpage++;
                    await page.click('.next > a');
                    return scrapeCurrentPage(); // Appelez cette fonction récursivement
                }
            }
            await page.close();
            return scrapedData;
        }
        let data = await scrapeCurrentPage();
        //console.log(data);
        return data;
    }
}
module.exports = scraperObject;