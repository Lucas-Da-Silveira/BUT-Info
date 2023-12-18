const express = require("express");
const bodyParser = require("body-parser");
const sequelize = require("./database/db");
const {Client, Passport, Sale, Item, SaleItem} = require("./models")

const port = 3001;
const app = express();

async function authenticationDb() {
    return sequelize.authenticate();
}

async function seed_data() {
    let clients = [
        {id: 100, firstname: 'Abigail', lastname: 'Kylie'},
        {id: 110, firstname: 'Anna', lastname: 'Carolyn'}
    ]
    let passports = [
        {
            country: 'France', passportNumber: 111201, issueDate:
                '2014-12-07', expirationDate: '2024-12-07'
        },
        {
            country: 'USA', passportNumber: 901222, issueDate:
                '2019-11-07', expirationDate: '2027-11-07'
        }
    ]
    await Client.bulkCreate(clients);
    await Passport.bulkCreate(passports);
    for (let i = 0; i < passports.length; i) {
        passport = passports[i];
        cl = clients[i];
        pass = await Passport.findOne({
            where: {
                passportNumber:
                passport.passportNumber
            }
        });
        client = await Client.findOne({where: {id: cl.id}});
        await client.setPassport(pass);
    }
    insert
    dummy
    sales
    let dummy_sales = [
        {saledate: '2020-01-15', saletext: 'sale 1', clientId: 100},
        {saledate: '2020-01-18', saletext: 'sale 2', clientId: 100},
        {saledate: '2020-01-21', saletext: 'sale 3', clientId: 110},
        {saledate: '2020-01-25', saletext: 'sale 4', clientId: 110},
        {saledate: '2020-02-25', saletext: 'sale 5', clientId: 110},
    ]
    await Sale.bulkCreate(dummy_sales);
    insert
    dummy
    items
    let dummy_items = [
        {itemname: 'Pocket knife—Nile', itemtype: "E", itemcolor: "Brown"},
        {itemname: 'Pocket knife—Avon', itemtype: "E", itemcolor: "Brown"},
        {itemname: 'Compass', itemtype: "N", itemcolor: "_"},
        {itemname: 'Hammock', itemtype: "F", itemcolor: "Khaki"},
        {itemname: 'Safari cooking kit', itemtype: "E", itemcolor: "_"}
    ]
    await Item.bulkCreate(dummy_items);
    let dummy_sale_items = [
        {quantity: 2, price: 10, saleno: 1, itemno: 3},
        {quantity: 3, price: 24, saleno: 1, itemno: 1},
        {quantity: 1, price: 8, saleno: 2, itemno: 1},
        {quantity: 2, price: 10, saleno: 3, itemno: 3},
        {quantity: 3, price: 60, saleno: 3, itemno: 5},
        {quantity: 1, price: 20, saleno: 4, itemno: 4},
        {quantity: 1, price: 10, saleno: 4, itemno: 2},
        {quantity: 2, price: 10, saleno: 5, itemno: 3}
    ]
    await SaleItem.bulkCreate(dummy_sale_items);
}

app.use(bodyParser.json());

Client.hasOne(Passport);
Passport.belongsTo(Client);

Sale.belongsTo(Client, {constraints: true, onDelete: 'CASCADE'});
Client.hasMany(Sale);

Sale.belongsToMany(Item, {through: SaleItem, foreignKey: 'saleleno'});
Item.belongsToMany(Sale, {through: SaleItem, foreignKey: 'itemno'});


authenticationDb()
    .then(async () => {
        console.log("Connection successful");
        //Client.sync();
        await sequelize.sync({force: true});
        console.log("All models were synchronized successfully");
    })
    .catch(() => {
        console.log("Connection failed");
    })

app.listen(port, () => {
    console.log(`Le serveur ecoute sur le port ${port}`);
})


app.post("/clients", async (req, res) => {
    let data = {
        id: req.body.id,
        firstname: req.body.firstname,
        lastname: req.body.lastname,
        passport_country: req.body.passport_country,
        passportNumber: req.body.passportNumber,
        passport_issue_date: req.body.passport_issue_date,
        passport_expirey_date: req.body.passport_expirey_date
    }
    let newClient = await Client.create({id: data.id, firstname: data.firstname, lastname: data.lastname});
    await newClient.createPassport({
        country: data.passport_country, passportNumber: data.passportNumber,
        issueDate: data.passport_issue_date, expirationDate: data.passport_expirey_date
    });
    return res.status(200).send({status: 1, data: "Data inserted successfully"});
});

app.post("/sales", async (req, res) => {
    let data = {
        saleno: req.body.saleno,
        saledate: req.body.saledate,
        saletext: req.body.saletext,
        client_id: req.body.client_id
    }
    let newSale = await Sale.create({
        saleno: data.saleno,
        saledate: data.saledate,
        saletext: data.saletext,
        clientId: data.client_id
    });
    return res.status(200).send({status: 1, data: "Data inserted successfully"});
});

app.get("clients/lazy/:id", async (req, res) => {
    let id = req.params.id;
    const client = await Client.findByPk(id);
    let response = {
        client_id: client.clientId,
        client_fullname: client.firstname + " " + client.lastname
    };
    response.sales = await client.getSales();
    return res.status(200).send({status: 1, data: response});
});

app.get("/clients/eager/:id", async (req, res) => {
    let id = req.params.id;
    const client = await Client.findByPk(id, {include: Sale});
    return res.status(200).send({status: 1, data: client});
});

app.put("/clients/:id", async (req, res) => {
    try {
        const clientId = req.params.id;
        const {firstname, lastname} = req.body;

        const client = await Client.findByPk(clientId);
        if (!client) {
            return res.status(404).json({error: "Client not found"});
        }

        await Client.update(
            {firstname, lastname},
            {where: {id: clientId}}
        );

        return res.status(200).json({status: 1, data: "Client updated successfully"});
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
});

app.delete("/sales/:id", async (req, res) => {
    try {
        const saleId = req.params.id;

        const sale = await Sale.findByPk(saleId);
        if (!sale) {
            return res.status(404).json({error: "Sale not found"});
        }

        await Sale.destroy({where: {id: saleId}});

        return res.status(200).json({status: 1, data: "Sale deleted successfully"});
    } catch (error) {
        console.error(error);
        return res.status(500).json({error: "Internal Server Error"});
    }
});

app.get("/sales/topclient", async (req, res) => {
    try {
        const client = await Client.findAll({
            attributes: ['id',
                [sequelize.literal('concat_ws(\' \', "firstName", "lastName")'), 'fullName'],
                [sequelize.fn('COUNT', 'clientid'), 'numSales']],
            include: [{model: Sale, attributes: []}],
            group: ['id', sequelize.literal('concat_ws(\' \', "firstName", "lastName")')],
            having: sequelize.where(sequelize.fn('COUNT', sequelize.col('clientid')), '>=', 2),
            order: [[sequelize.fn('COUNT', sequelize.col('clientid')), 'DESC']]
        });
        return res.status(200).send({status: 1, data: client[0]});
    } catch (error) {
        console.error(error);
    }
});