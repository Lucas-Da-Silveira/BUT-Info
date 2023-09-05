const fs = require('fs')
const chalk = require('chalk')

const addNote = (title, body) => {
    const notes = loadNotes()
    const duplicateNote = false;

    if (!duplicateNote) {

        console.log(chalk.green.inverse('New note added!'))
    } else {
        console.log(chalk.red.inverse('Note title taken!'))
    }
}



const saveNotes = (notes) => {
    //TODO
}

const loadNotes = () => {
    try {
        //TODO
    } catch (e) {
        return []
    }
}

const listNotes = () => {
    //TODO
}

// EXPORT MODULES
module.exports = {
    addNote: addNote,
    listNotes: listNotes
}