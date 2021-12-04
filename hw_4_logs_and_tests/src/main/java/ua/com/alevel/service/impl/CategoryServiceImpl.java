package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.config.GenerateImpl;
import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;
import ua.com.alevel.service.CategoryService;

@ActiveClass
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final CategoryDao categoryDao = GenerateImpl.generateImplementation(CategoryDao.class);

    @Override
    public void create(Category category) {
        try {
            LOGGER_INFO.info("operation: create, status: start, type: " + category);
            categoryDao.create(category);
            LOGGER_INFO.info("operation: create, status: finish, type: " + category);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("operation: create, status: failed, type: " + category);
        }

    }

    @Override
    public void update(Category entity) {
        try {
            LOGGER_INFO.info("operation: update, status: start, type: " + entity);
            categoryDao.update(entity);
            LOGGER_INFO.info("operation: update, status: finish, type: " + entity);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("operation: update, status: failed, type: " + entity);
        }

    }

    @Override
    public void delete(String id) {
        try {
            LOGGER_WARN.warn("operation: delete, status: start, id:" + id);
            categoryDao.delete(id);
            LOGGER_WARN.warn("operation: delete, status: finish, id:" + id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("operation: delete, status: failed, id:" + id);
        }
    }

    @Override
    public Category findById(String id) {
        LOGGER_INFO.info("operation: findById, status: start, id:" + id);
        return categoryDao.findById(id);
    }

    @Override
    public Category[] findAll() {
        LOGGER_INFO.info("operation: findAll, status: start");
        return categoryDao.findAll();
    }

    @Override
    public Item[] findAllItems(Category category) {
        LOGGER_INFO.info("operation: findAllItems, status: start");
        return categoryDao.findAllItems(category);
    }

}
