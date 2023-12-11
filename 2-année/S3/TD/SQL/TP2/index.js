const express = require("express");
const bodyParser = require("body-parser");
const sequelize = require("./database/db");
const {Client, Passport} = require("./models")

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
}

app.use(bodyParser.json());

Client.hasOne(Passport);
Passport.belongsTo(Client);

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