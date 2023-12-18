const yargs = require("yargs");
const chalk = require("chalk");
const request = require("request");



// Customize yargs version
yargs.version('1.1.0')

// utilisation de YARGS
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
        let title = argv.title;
        let body = argv.body;
        request.post({
            url: "http://localhost:3000/api/notes",
            form: {title: title, body: body}
        }, (err, res, body) => {
            console.log('body',chalk.blue(body));
        })
    }
});


// Create list command
yargs.command({
    command: 'list',
    describe: 'List your notes',
    handler() {
        request({
            url: "http://localhost:3000/api/notes",
            form: {title: title, body: body}
        }, (err, res, body) => {
            console.log('body',chalk.blue(body));
        })
    }
})


yargs.parse();