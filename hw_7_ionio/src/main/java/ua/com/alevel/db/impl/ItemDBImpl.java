package ua.com.alevel.db.impl;

import ua.com.alevel.db.ItemDB;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Item;
import ua.com.alevel.util.WriteReadJSONUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ItemDBImpl implements ItemDB {

    private static ItemDBImpl instance;
    private final String PATH_OF_FILE_ITEMS = "hw_7_ionio/items.json";
    private List<Item> items;

    private ItemDBImpl() {
        items = new ArrayList<>();
    }

    public static ItemDBImpl getInstance() {
        if (instance == null) {
            instance = new ItemDBImpl();
            WriteReadJSONUtil.readDatabase(instance);
        }
        return instance;
    }

    @Override
    public void create(Item item) {
        item.setId(generateId(items));
        items.add(item);
        WriteReadJSONUtil.readDatabase(instance);
    }

    @Override
    public void update(Item item) {
        Item current = findById(item.getId());
        current.setId(item.getName());
        current.setName(item.getName());
        current.setPrice(item.getPrice());
        WriteReadJSONUtil.readDatabase(instance);
    }

    @Override
    public void delete(String id) {
        items.removeIf(item -> item.getId().equals(id));
        WriteReadJSONUtil.readDatabase(instance);
    }

    @Override
    public Item findById(String id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("item not found by id"));
    }

    @Override
    public List<Item> findAll() {
        return items;
    }

    @Override
    public String getPath() {
        return PATH_OF_FILE_ITEMS;
    }

    @Override
    public void setDB(List<? extends BaseEntity> listOfEntities) {
        this.items = Collections.unmodifiableList((List<Item>) listOfEntities);
    }

    @Override
    public Class getClassOfEntity() {
        return null;
    }

    public static String generateId(List<? extends BaseEntity> items) {
        String id = UUID.randomUUID().toString();
        if (items.stream().anyMatch(item -> item.getId().equals(id))) {
            return generateId(items);
        }
        return id;
    }
}
