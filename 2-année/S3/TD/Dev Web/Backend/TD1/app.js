const chalk = require("chalk");
const yargs = require("yargs");
const notes = require("./notes");


console.log(process.argv);

// Customize yargs version
yargs.version('1.1.0')

// Utilisation de YARGS
// Create add command
yargs.command({
    command: 'add',
    describe: 'Add a new note',
    builder: {
        title: {
            describe: 'Note title',
            demandOption: true,
            type: 'string'
        },
        body: {
            describe: 'Note body',
            demandOption: true,
            type: 'string'
        }
    },
    handler(argv) {
        console.log(argv.title);
        console.log(argv.body);
        notes.addNote (argv.title, argv.body);
    }
});

 
// Create list command
yargs.command({
    command: 'list',
    describe: 'List your notes',
    handler() {
        notes.listNotes();
    }
})

 

yargs.parse();