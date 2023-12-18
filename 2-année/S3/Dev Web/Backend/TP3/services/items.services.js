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

const getItemsById = (uuid, callback) => {
    let items = [];
    try {
        items = data.items;
        const item = items.find(u => u.id === uuid)
        callback(null, item);
    } catch (error) {
        console.log(error);
        callback(error, null);
    }
}

const getItemsPromotion = (callback) => {
    let items = [];
    try {
        items = data.items;
        const item = items.filter(u => u.promotion === true)
        callback(null, item);
    } catch (error) {
        console.log(error);
        callback(error, null);
    }
}




module.exports = {
    getItems,
    getItemsById,
    getItemsPromotion
}