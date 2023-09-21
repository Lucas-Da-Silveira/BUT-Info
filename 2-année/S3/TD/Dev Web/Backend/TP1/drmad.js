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
        name: {
            describe: 'virus title',
            demandOption: true,
            type: 'string'
        },
    },
    handler(argv) {
        console.log(argv.name);
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