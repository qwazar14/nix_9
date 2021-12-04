package ua.com.alevel.dao.impl;

import ua.com.alevel.config.ActiveClass;
import ua.com.alevel.dao.ItemDao;
import ua.com.alevel.db.ItemDB;
import ua.com.alevel.db.impl.ItemDBImpl;
import ua.com.alevel.entity.Item;

@ActiveClass
public class ItemDaoImpl implements ItemDao {

    protected final ItemDB db = ItemDBImpl.getInstance();

    @Override
    public void create(Item item) {
        db.create(item);
    }

    @Override
    public void update(Item item) {
        db.update(item);
    }

    @Override
    public void delete(String id) {
        db.delete(id);
    }

    @Override
    public Item findById(String id) {
        return db.findById(id);
    }

    @Override
    public Item[] findAll() {
        return db.findAll();
    }

}