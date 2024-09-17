const requireLogin = require('../middlewares/requireLogin');
const mongoose = require('mongoose');
const Blog = mongoose.model('Blog');

module.exports = app => {
  app.get('/api/blogs/:id', requireLogin, async (req, res) => {
    const blog = await Blog.findOne({
      _id: req.params.id,
      _user: req.user.id
    });
    res.send(blog);
  });

  app.get('/api/blogs', requireLogin, async (req, res) => {
    const blogs = await Blog.find({_user: req.user.id });
    res.send(blogs);
  });

  app.post('/api/blogs', requireLogin, async (req, res) => {
    const { title, content } = req.body;
    const blog = await new Blog({
        title,
        content,
        _user: req.user.id
    });
    try {
      await blog.save();
      res.send(blog);
    } catch (err) {
      res.send(400, err);
    }
  });

  app.delete('/api/blogs/:id', requireLogin, async (req, res) => {
    try {
      await Blog.findOneAndDelete({
        _id: req.params.id,
        _user: req.user.id
      });
      const blogs = await Blog.find({_user: req.user.id });
      res.send(blogs);
    } catch (err) {
      res.status(500).send({ error: 'Failed to delete blog' });
    }
  });
};