const itemservice = require('../services/items.services');

exports.getItems = (req, res) => {
    itemservice.getItems((error,data)=>{
        if(error){
            return res.status(500).send("Internal error");
        }
        return res.status(200).json(data);
    });
}