const { Pool } = require('pg');
const { query } = require('../database/connection.js');
const {pool} = require("../database/connection");
require('dotenv').config();

const getLaureates = async () => {
  try {
    const result = await query('SELECT id, firstname, surname FROM laureates');
    return result.rows;
  } catch (error) {
    console.error('Error fetching laureates', error);
    throw new Error('Internal Server Error');
  }
};

const getLaureateById = async (id) => {
  try {
    const query = `
      SELECT laureates.id, firstname, surname, year, category, motivation
      FROM laureates
      JOIN prize_laureates ON laureates.id = prize_laureates.laureate_id
      JOIN prizes ON prize_laureates.prize_year = prizes.year AND prize_laureates.prize_category = prizes.category
      WHERE laureates.id = $1;
    `;
    const result = await pool.query(query, [id]);
    return result.rows;
  } catch (error) {
    console.error('Error fetching laureate by id', error);
    throw new Error('Internal Server Error');
  }
};

const getMultiplePrizesLaureates = async () => {
  try {
    const query = `
      SELECT laureates.id, firstname, surname, COUNT(*) as num_prizes
      FROM laureates
      JOIN prize_laureates ON laureates.id = prize_laureates.laureate_id
      GROUP BY laureates.id
      HAVING COUNT(*) > 1;
    `;
    const result = await pool.query(query);
    return result.rows;
  } catch (error) {
    console.error('Error fetching laureates with multiple prizes', error);
    throw new Error('Internal Server Error');
  }
};

const deleteLaureateById = async (id) => {
  try {
    const deletePrizesQuery = 'DELETE FROM prize_laureates WHERE laureate_id = $1';
    const deletePrizesValues = [id];
    await pool.query(deletePrizesQuery, deletePrizesValues);

    const deleteLaureateQuery = 'DELETE FROM laureates WHERE id = $1 RETURNING *';
    const deleteLaureateValues = [id];
    const result = await pool.query(deleteLaureateQuery, deleteLaureateValues);

    if (result.rowCount === 0) {
      throw new Error('Laureate not found');
    }

    return result.rows[0];
  } catch (error) {
    console.error('Error deleting laureate by ID', error);
    throw new Error('Internal Server Error');
  }
};

const updateMotivation = async (id, year, category, newMotivation) => {
  try {
    const laureateExistsQuery = 'SELECT * FROM prize_laureates WHERE laureate_id = $1 AND prize_year = $2 AND prize_category = $3';
    const laureateExistsValues = [id, year, category];
    const laureateExistsResult = await pool.query(laureateExistsQuery, laureateExistsValues);

    if (laureateExistsResult.rowCount === 0) {
      throw new Error('Laureate not found in the specified year and category');
    }

    const updateMotivationQuery = 'UPDATE laureates SET motivation = $1 WHERE id = $2 RETURNING *';
    const updateMotivationValues = [newMotivation, id];
    const updatedLaureate = await pool.query(updateMotivationQuery, updateMotivationValues);

    return updatedLaureate.rows[0];
  } catch (error) {
    console.error('Error updating laureate motivation', error);
    throw new Error('Internal Server Error');
  }
};

module.exports = {
  getLaureates,
  getLaureateById,
  getMultiplePrizesLaureates,
    deleteLaureateById,
    updateMotivation
};
