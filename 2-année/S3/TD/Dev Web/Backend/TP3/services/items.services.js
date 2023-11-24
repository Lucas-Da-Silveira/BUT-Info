const fs = require('fs');
const path = require('path');
const {v4:uuid4} = require('uuid');
const data = require('../datasource/data');

const getItems = (callback) => {
    let items = [];
    try {
        items = data.items;
        callback(null, items);
    } catch (error) {
        console.log(error);
        callback(error, null);
    }
}

const getItemById = (uuid, callback) => {

}




module.exports = {
    getItems
}