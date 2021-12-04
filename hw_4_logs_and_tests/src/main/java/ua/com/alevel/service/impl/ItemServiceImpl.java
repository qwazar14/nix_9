package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.config.GenerateImpl;
import ua.com.alevel.dao.ItemDao;
import ua.com.alevel.entity.Item;
import ua.com.alevel.service.ItemService;

@ActiveClass
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("INFO");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("WARN");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("ERROR");

    private final ItemDao itemDao = GenerateImpl.generateImplementation(ItemDao.class);

    @Override
    public void create(Item item) {
        LOGGER_INFO.info("operation: create, status: start, type: item");
        try {
            itemDao.create(item);
            LOGGER_INFO.info("operation: create, status: finish, type: item");
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("operation: create, status: failed, type: item");
        }
    }

    @Override
    public void update(Item item) {
        try {
            LOGGER_INFO.info("operation: update, status: start, type: item");
            itemDao.update(item);
            LOGGER_INFO.info("operation: update, status: finish, type: item");
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("operation: update, status: failed, type: item");
        }
    }

    @Override
    public void delete(String id) {
        try {
            LOGGER_WARN.warn("operation: delete, status: start, type: item");
            itemDao.delete(id);
            LOGGER_WARN.warn("operation: delete, status: finish, type: item");
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("operation: delete, status: failed, type: item");
        }
    }

    @Override
    public Item findById(String id) {
        LOGGER_INFO.info("operation: findById, status: start, type: item");
        return itemDao.findById(id);
    }

    @Override
    public Item[] findAll() {
        LOGGER_INFO.info("operation: findAll, status: start, type: item");
        return itemDao.findAll();
    }

}
