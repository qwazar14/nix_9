package ua.com.alevel.db.impl;

import ua.com.alevel.db.ItemDB;
import ua.com.alevel.entity.Item;

import java.util.Arrays;
import java.util.UUID;

public class ItemDBImpl implements ItemDB {

    protected static ItemDBImpl instance;
    private Item[] items;

    public ItemDBImpl() {
        items = new Item[10];
    }

    public static ItemDBImpl getInstance() {
        if (instance == null) {
            instance = new ItemDBImpl();
        }
        return instance;
    }

    public void create(Item item) {
        item.setId(generateId(item));
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
        changeSize();
        create(item);
    }

    public void update(Item item) {
        Item current = findById(item.getId());
        if (current != null) {
            current.setPrice(item.getPrice());
        } else {
            System.out.println("there is no items with id" + item.getId());
            return;
        }
    }

    public void delete(String id) {
        Item itemsList[];
        try {
            itemsList = findById(id).getCategory().getItems();
        } catch (NullPointerException e) {
            System.out.println("items not found");
            return;
        }
        int position = -1;
        for (int i = 0; i < itemsList.length; i++) {
            if (itemsList[i] != null) {
                if (itemsList[i].getId().equals(id)) {
                    itemsList[i] = null;
                    position = i;
                }
            }
        }
        if (position != -1) {
            for (int i = position; i < itemsList.length - 1; i++) {
                itemsList[i] = itemsList[i + 1];
            }
            itemsList[itemsList.length - 1] = null;
        }
        if (findById(id) != null) {
            findById(id).getCategory().setItems(itemsList);
        }
        position = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getId().equals(id)) {
                    items[i] = null;
                    position = i;
                }
            }
        }
        if (position != -1) {
            for (int i = position; i < items.length - 1; i++) {
                items[i] = items[i + 1];
            }
            items[items.length - 1] = null;
        } else System.out.println("there is no items with id = " + id);
    }

    public Item findById(String id) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getId().equals(id)) {
                    return items[i];
                }
            }
        }
        return null;
    }

    public Item[] findAll() {
        int counter = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                counter++;
            }
        }
        Item newItems[] = Arrays.copyOf(items, counter);
        return newItems;
    }

    private String generateId(Item item) {
        String id = UUID.randomUUID().toString();
        if (findById(id) == null) {
            return id;
        }
        return generateId(item);
    }

    public void changeSize() {
        Item newItems[] = new Item[items.length * 2];
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }


}