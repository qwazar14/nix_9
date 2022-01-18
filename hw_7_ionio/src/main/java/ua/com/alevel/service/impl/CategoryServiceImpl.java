package ua.com.alevel.service.impl;

import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.dao.impl.CategoryDaoImpl;
import ua.com.alevel.entity.Category;
import ua.com.alevel.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao = new CategoryDaoImpl();

    public CategoryServiceImpl() {
    }

    @Override
    public void create(Category entity) {
        categoryDao.create(entity);
    }

    @Override
    public void update(Category entity) {
        categoryDao.update(entity);
    }

    @Override
    public void delete(String id) {
        categoryDao.delete(id);
    }

    @Override
    public Category findById(String id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
