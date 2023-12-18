const { Pool } = require('pg');
const { query } = require('../database/connection.js');
const {pool} = require("../database/connection");
require('dotenv').config();

const getPrizeCategories = async () => {
  try {
    const query = 'SELECT DISTINCT category FROM prizes;';
    const result = await pool.query(query);
    return result.rows.map((row) => row.category);
  } catch (error) {
    console.error('Error fetching prize categories', error);
    throw new Error('Internal Server Error');
  }
};

const getCategoryWithMostLaureates = async () => {
  try {
    const query = `
      SELECT prize_category, COUNT(DISTINCT laureate_id) AS laureate_count
      FROM prize_laureates
      GROUP BY prize_category
      ORDER BY laureate_count DESC
      LIMIT 1;
    `;
    const result = await pool.query(query);
    return result.rows[0];
  } catch (error) {
    console.error('Error fetching category with most laureates', error);
    throw new Error('Internal Server Error');
  }
};

const getLaureatesCountByYear = async () => {
  try {
    const query = `
      SELECT prize_year, COUNT(DISTINCT laureate_id) AS laureate_count
      FROM prize_laureates
      GROUP BY prize_year
      ORDER BY prize_year;
    `;
    const result = await pool.query(query);
    return result.rows;
  } catch (error) {
    console.error('Error fetching laureates count by year', error);
    throw new Error('Internal Server Error');
  }
};

const getEmptyYears = async () => {
  try {
    const query = `
      SELECT DISTINCT year
      FROM prizes
      WHERE year NOT IN (SELECT DISTINCT prize_year FROM prize_laureates);
    `;
    const result = await pool.query(query);
    return result.rows.map((row) => row.year);
  } catch (error) {
    console.error('Error fetching empty years', error);
    throw new Error('Internal Server Error');
  }
};

const getSortedYearsByLaureates = async (sortOrder) => {
  try {
    const order = sortOrder === 'asc' ? 'ASC' : 'DESC';
    const query = `
      SELECT prize_year, COUNT(DISTINCT laureate_id) AS laureate_count
      FROM prize_laureates
      GROUP BY prize_year
      HAVING COUNT(DISTINCT laureate_id) > 0
      ORDER BY laureate_count ${order}, prize_year ${order};
    `;
      const result = await pool.query(query);
    return result.rows;
  } catch (error) {
    console.error('Error fetching sorted years by laureates', error);
    throw new Error('Internal Server Error');
  }
};

module.exports = {
    getPrizeCategories,
    getCategoryWithMostLaureates,
    getLaureatesCountByYear,
    getEmptyYears,
    getSortedYearsByLaureates
};