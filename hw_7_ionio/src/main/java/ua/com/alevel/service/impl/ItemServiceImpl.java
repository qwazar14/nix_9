package ua.com.alevel.service.impl;

import ua.com.alevel.dao.ItemDao;
import ua.com.alevel.dao.impl.ItemDaoImpl;
import ua.com.alevel.entity.Item;
import ua.com.alevel.service.ItemService;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao = new ItemDaoImpl();

    public ItemServiceImpl() {
    }

    @Override
    public void create(Item entity) {
        itemDao.create(entity);
    }

    @Override
    public void update(Item entity) {
        itemDao.update(entity);
    }

    @Override
    public void delete(String id) {
        itemDao.delete(id);
    }

    @Override
    public Item findById(String id) {
        return itemDao.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }
}
