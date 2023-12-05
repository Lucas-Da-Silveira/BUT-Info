const pool = require('./database/connection');
const jsonData = require('./data/prize.json');


const insertLaureates = async (laureates, prizeYear, prizeCategory) => {
  for (const laureate of laureates) {
    const { id, firstname, surname, motivation, share } = laureate;
    const query = {
      text: 'INSERT INTO laureates (id, firstname, surname, motivation, share) VALUES ($1, $2, $3, $4, $5)',
      values: [id, firstname, surname, motivation, share],
    };

    try {
      await pool.query(query);
      const prizeLaureateQuery = {
        text: 'INSERT INTO prize_laureates (prize_year, prize_category, laureate_id) VALUES ($1, $2, $3)',
        values: [prizeYear, prizeCategory, id],
      };
      await pool.query(prizeLaureateQuery);
      console.log(`Inserted laureate ${firstname} ${surname}`);
    } catch (error) {
      console.error(`Error inserting laureate ${firstname} ${surname}: ${error}`);
    }
  }
};

const insertPrizes = async () => {
  for (const prize of jsonData.prizes) {
    const { year, category, laureates } = prize;
    const query = {
      text: 'INSERT INTO prizes (year, category) VALUES ($1, $2) ON CONFLICT DO NOTHING',
      values: [year, category],
    };

    try {
      await pool.query(query);
      console.log(`Inserted prize ${category} for year ${year}`);
      await insertLaureates(laureates, year, category);
    } catch (error) {
      console.error(`Error inserting prize ${category} for year ${year}: ${error}`);
    }
  }
};

insertPrizes().then(() => {
  console.log('Finished inserting data');
  pool.end();
});
