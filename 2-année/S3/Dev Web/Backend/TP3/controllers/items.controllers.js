const itemservice = require('../services/items.services');

exports.getItems = (req, res) => {
    itemservice.getItems((error,data)=>{
        if(error){
            return res.status(500).send("Internal error");
        }
        return res.status(200).json(data);
    });
}
exports.getItemsById = (req, res) => {
    const uuid = req.params.uuid;
    itemservice.getItemsById(uuid, (error, data) => {
        if(error){
            return res.status(500).send("Internal error");
        }
        if(!data){
            return res.status(404).send("Items not found");
        }
        return res.status(200).json(data);
    });
}

exports.getItemsPromotion = (req, res) => {
    itemservice.getItemsPromotion((error, data) => {
        if(error){
            return res.status(500).send("Internal error");
        }
        return res.status(200).json(data);
    });
}