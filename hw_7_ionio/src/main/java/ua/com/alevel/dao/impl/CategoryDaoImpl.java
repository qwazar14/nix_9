package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.db.CategoryDB;
import ua.com.alevel.db.impl.CategoryDBImpl;
import ua.com.alevel.entity.Category;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private final CategoryDB instanceDB = CategoryDBImpl.getInstance();

    public CategoryDaoImpl() {
    }

    @Override
    public void create(Category entity) {
        instanceDB.create(entity);
    }

    @Override
    public void update(Category entity) {
        instanceDB.update(entity);
    }

    @Override
    public void delete(String id) {
        instanceDB.delete(id);
    }

    @Override
    public Category findById(String id) {
        return instanceDB.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return instanceDB.findAll();
    }
}
