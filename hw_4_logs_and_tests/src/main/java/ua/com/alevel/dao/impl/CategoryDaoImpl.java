package ua.com.alevel.dao.impl;

import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.db.impl.CategoryDBImpl;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;

@ActiveClass
public class CategoryDaoImpl implements CategoryDao {

    private final CategoryDBImpl db = CategoryDBImpl.getInstance();

    @Override
    public void create(Category category) {
        db.create(category);
    }

    @Override
    public void update(Category category) {
        db.update(category);
    }

    @Override
    public void delete(String id) {
        db.delete(id);
    }

    @Override
    public Category findById(String id) {
        return db.findById(id);
    }

    @Override
    public Category[] findAll() {
        return db.findAll();
    }

    @Override
    public Item[] findAllItems(Category category) {
        return db.findAllItems(category);
    }
}