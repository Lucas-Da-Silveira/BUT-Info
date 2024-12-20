const chalk = require("chalk");
const yargs = require("yargs");
const items = require("./models/items.js");


console.log(process.argv);

// Customize yargs version
yargs.version('1.1.0')

// Utilisation de YARGS
// Create add command
yargs.command({
    command: 'add',
    describe: 'Add a new virus',
    builder: {
        file:{
            describe: 'virus file',
            demandOption: true,
            type: 'file'
        }
    },
    handler(argv) {
        console.log(argv.file);
        items.addVirus(argv.file);
        //console.log(argv.body);
    }
});


// Create list command
yargs.command({
    command: 'list',
    describe: 'List your virus',
    handler() {
        items.listVirus();
    }
})



yargs.parse();