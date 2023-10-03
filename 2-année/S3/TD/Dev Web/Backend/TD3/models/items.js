const fs = require('fs');
const path = require('path');
const util = require('util');
const readFileAsync = util.promisify(fs.readFile);
const writeFileAsync = util.promisify(fs.writeFile);

class Items {
    constructor() {
        this.dataFilePath = path.join(__dirname, '..', 'data', 'data.json');
        this.dataFileDest = path.join(__dirname, '..', 'data', 'data_copy.json');
    }

    copyCallback(callback) {
        fs.readFile(this.dataFilePath, (error, data) => {
            if (error) {
                callback(error);
                return;
            }
            fs.writeFile(this.dataFileDest, data, (writeError) => {
                if (writeError) {
                    callback(writeError);
                    return;
                }
                fs.readFile(this.dataFileDest, (errorCpy, dataCpy) => {
                    if (errorCpy) {
                        callback(errorCpy);
                    }
                    console.log(dataCpy);
                    callback(null);
                })
            })
        })
    }

    copyPromise(){
        return readFileAsync(this.dataFilePath)
            .then(data =>{ writeFileAsync(this.dataFileDest, data)})
            .then(()=> readFileAsync(this.dataFileDest))
            .then(dataCpy=> console.log(dataCpy))
    }

    async capyAsync(){
        try{
            const data = await readFileAsync(this.dataFilePath);
            await writeFileAsync(this.dataFileDest, data);
            const dataCpy = await readFileAsync(this.dataFileDest);
            console.log(dataCpy);
        }catch (error){throw error}
    }
}
module.exports = new Items();