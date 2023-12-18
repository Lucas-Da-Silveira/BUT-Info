const prizesService = require('../services/prizes.service.js');

const getPrizeCategories = async (req, res) => {
  try {
    const categories = await prizesService.getPrizeCategories();
    res.json(categories);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const getCategoryWithMostLaureates = async (req, res) => {
  try {
    const categoryWithMostLaureates = await prizesService.getCategoryWithMostLaureates();
    res.json(categoryWithMostLaureates);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const getLaureatesCountByYear = async (req, res) => {
  try {
    const laureatesCountByYear = await prizesService.getLaureatesCountByYear();
    res.json(laureatesCountByYear);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

module.exports = { getLaureatesCountByYear };

const getEmptyYears = async (req, res) => {
  try {
    const emptyYears = await prizesService.getEmptyYears();
    res.json(emptyYears);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const getSortedYearsByLaureates = async (req, res) => {
  try {
    const sortOrder = req.query.sort || 'asc';
    const sortedYearsByLaureates = await prizesService.getSortedYearsByLaureates(sortOrder);
    res.json(sortedYearsByLaureates);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

module.exports = {
  getPrizeCategories,
  getCategoryWithMostLaureates,
    getLaureatesCountByYear,
    getEmptyYears,
    getSortedYearsByLaureates
};