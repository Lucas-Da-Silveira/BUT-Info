const fs = require('fs')
const chalk = require('chalk')

const addNote = (title, body) => {
    const notes = loadNotes()
    const duplicateNote = notes.find((note) => note.title === title)

    if (!duplicateNote) {
        notes.push({
            title: title,
            body: body
        });
        saveNotes(notes);
        console.log(chalk.green.inverse('New note added!'))
    } else {
        console.log(chalk.red.inverse('Note title taken!'))
    }
}



const saveNotes = (notes) => {
    const dataString = JSON.stringify(notes);
    fs.writeFileSync("notes.json", dataString);
}

const loadNotes = () => {
    try {
        const dataBuffer = fs.readFileSync("notes.json");
        const dataString = dataBuffer.toString();

        return JSON.parse(dataString);

    } catch (e) {
        return []
    }
}

const listNotes = () => {
    fs.readFile("notes.json",(error, dataBuffer)=>{
        if(error){
            return [];
        }
        const dataString = dataBuffer.toString();
        let dataJSON = JSON.parse(dataString);
        console.log(chalk.green("Voici les notes"));
        dataJSON.forEach((note)=>{
            console.log(note.title);
        })
    })
}

// EXPORT MODULES
module.exports = {
    addNote: addNote,
    listNotes: listNotes
}