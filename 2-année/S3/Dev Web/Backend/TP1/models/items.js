const fs = require('fs');
const chalk = require('chalk');

const addVirus = (name) => {
    const viruss = loadVirus();
    const duplicateVirus = viruss.find((virus) => virus.name === name);

    if (!duplicateVirus) {

        viruss.push({
            name: name,
        });
        saveViruss(viruss);
        console.log(chalk.green.inverse("New virus added!"));
    } else {
        console.log(chalk.red.inverse("virus title taken!"));
    }
}



const saveViruss = (virus) => {
    const dataString = JSON.stringify(virus);
    fs.writeFileSync("/home/lucas/BUT-Info/2-année/S3/TD/Dev Web/Backend/TP1/data/data.json", dataString);
}

const loadVirus = () => {
    try {
        const dataBuffer = fs.readFileSync("/home/lucas/BUT-Info/2-année/S3/TD/Dev Web/Backend/TP1/data/data.json");
        const dataString = dataBuffer.toString();

        return JSON.parse(dataString);
    } catch (e) {
        return [];
    }
}

const listVirus = () => {
    fs.readFile("/home/lucas/BUT-Info/2-année/S3/TD/Dev Web/Backend/TP1/data/data.json", (error, dataBuffer) => {
        if (error) return [];

        const dataString = dataBuffer.toString();
        let dataJSON = JSON.parse(dataString);
        console.log(chalk.green.inverse("Voici les virus:"));

        dataJSON.forEach(virus => {
            console.log(virus.name);
        })
    });
}

const getVirus = (name) => {
    const viruss = loadVirus();
    const virus = viruss.find((virus) => virus.name === name);

    if (virus) {
        console.log(chalk.green.inverse(virus.name));
        //console.log(virus.body);
    } else {
        console.log(chalk.red.inverse("virus not found!"));
    }
}

const deleteVirus = (name) => {
    const viruss = loadVirus();
    const virussToKeep = viruss.filter((virus) => virus.name !== name);

    if (viruss.length > virussToKeep.length) {
        console.log(chalk.green.inverse("virus removed!"));
        saveViruss(virussToKeep);
    } else {
        console.log(chalk.red.inverse("No virus found!"));
    }
}

// EXPORT MODULES
module.exports = {
    addVirus:addVirus,
    listVirus:listVirus,
    getVirus:getVirus,
    deleteVirus:deleteVirus
}