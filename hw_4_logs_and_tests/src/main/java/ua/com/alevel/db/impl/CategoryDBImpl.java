package ua.com.alevel.db.impl;

import ua.com.alevel.db.CategoryDB;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;

import java.util.Arrays;
import java.util.UUID;

public class CategoryDBImpl implements CategoryDB {

    public static CategoryDBImpl instance;
    protected Item[] items;
    private Category[] categories;

    public CategoryDBImpl() {
        categories = new Category[10];
        items = new Item[10];
    }

    public static CategoryDBImpl getInstance() {
        if (instance == null) {
            instance = new CategoryDBImpl();
        }
        return instance;
    }

    @Override
    public void create(Category category) {
        category.setId(generateId(category));
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] == null) {
                categories[i] = category;
                return;
            }
        }
        changeSize();
        create(category);
    }

    @Override
    public void update(Category category) {
        Category current = findById(category.getId());
        if (current != null) {
            current.setName(category.getName());
        } else {
            System.out.println("there is no categories with id" + category.getId());
            return;
        }
    }

    @Override
    public void delete(String id) {
        if (findById(id) != null) {
            Item[] items = findAllItems(findById(id));
            for (int i = 0; i < items.length; i++) {
                if (items[0] != null) {
                    ItemDBImpl.getInstance().delete(items[0].getId());
                }
            }
        }
        int position = -1;
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null) {
                if (categories[i].getId().equals(id)) {
                    categories[i] = null;
                    position = i;
                }
            }
        }
        if (position != -1) {
            for (int i = position; i < categories.length - 1; i++) {
                categories[i] = categories[i + 1];
            }
            categories[categories.length - 1] = null;
        } else {
            System.out.println("there is no categories with id = " + id);
            return;
        }
    }

    @Override
    public Category findById(String id) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null) {
                if (categories[i].getId().equals(id)) {
                    return categories[i];
                }
            }
        }
        return null;
    }

    @Override
    public Category[] findAll() {
        int counter = 0;
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null) {
                counter++;
            }
        }
        return Arrays.copyOf(categories, counter);
    }


    @Override
    public Item[] findAllItems(Category category) {
        return category.getItems();
    }

    public void changeSize() {
        Category[] newCategories = new Category[categories.length * 2];
        for (int i = 0; i < categories.length; i++) {
            newCategories[i] = categories[i];
        }
        categories = newCategories;
    }

    private String generateId(Category category) {
        String id = UUID.randomUUID().toString();
        if (findById(id) == null) {
            return id;
        }
        return generateId(category);
    }
}