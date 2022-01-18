package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.ItemDao;
import ua.com.alevel.db.ItemDB;
import ua.com.alevel.db.impl.ItemDBImpl;
import ua.com.alevel.entity.Item;

import java.util.List;

public class ItemDaoImpl implements ItemDao {

    private final ItemDB instanceDB = ItemDBImpl.getInstance();

    public ItemDaoImpl() {
    }

    @Override
    public void create(Item entity) {
        instanceDB.create(entity);
    }

    @Override
    public void update(Item entity) {
        instanceDB.update(entity);
    }

    @Override
    public void delete(String id) {
        instanceDB.delete(id);
    }

    @Override
    public Item findById(String id) {
        return instanceDB.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return instanceDB.findAll();
    }
}
