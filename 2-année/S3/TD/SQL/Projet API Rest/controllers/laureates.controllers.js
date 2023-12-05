const laureatesService = require('../services/laureates.service.js');

const listLaureates = async (req, res) => {
  try {
    const laureates = await laureatesService.getLaureates();
    res.json(laureates);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const getLaureateById = async (req, res) => {
  try {
    const id = parseInt(req.params.id);
    if (isNaN(id)) {
      return res.status(400).json({ error: 'Invalid ID format' });
    }
    const laureate = await laureatesService.getLaureateById(id);
    if (!laureate) {
      return res.status(404).json({ error: 'Laureate not found' });
    }
    res.json(laureate);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const getMultiplePrizesLaureates = async (req, res) => {
  try {
    const laureates = await laureatesService.getMultiplePrizesLaureates();
    res.json(laureates);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const deleteLaureateById = async (req, res) => {
  try {
    const laureateId = req.params.id;
    const deletedLaureate = await laureatesService.deleteLaureateById(laureateId);
    res.json(deletedLaureate);
  } catch (error) {
    if (error.message === 'Laureate not found') {
      res.status(404).json({ error: error.message });
    } else {
      res.status(500).json({ error: 'Internal Server Error' });
    }
  }
};

const updateMotivation = async (req, res) => {
  const { id, year, category } = req.params;
  const { newMotivation } = req.body;

  try {
    const updatedLaureate = await laureatesService.updateMotivation(id, year, category, newMotivation);
    res.json(updatedLaureate);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

module.exports = {
  listLaureates,
  getLaureateById,
  getMultiplePrizesLaureates,
    deleteLaureateById,
    updateMotivation
};

