package ua.com.alevel.db.impl;

import ua.com.alevel.db.CategoryDB;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Category;
import ua.com.alevel.util.WriteReadJSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoryDBImpl implements CategoryDB {

    private static CategoryDBImpl instance;
    private final String PATH_OF_FILE_CATEGORIES = "hw_7_ionio/categories.json";
    private List<Category> categories;

    private CategoryDBImpl() {
        categories = new ArrayList<>();
    }

    public static CategoryDBImpl getInstance() {
        if (instance == null) {
            instance = new CategoryDBImpl();
            WriteReadJSONUtil.readDatabase(instance);

        }
        return instance;
    }

    @Override
    public void create(Category company) {
        company.setId(generateId(categories));
        categories.add(company);
        WriteReadJSONUtil.writeDatabase(instance);
    }

    @Override
    public void update(Category entity) {
        Category current = findById(entity.getId());
        current.setId(entity.getName());
        current.setName(entity.getName());
        WriteReadJSONUtil.readDatabase(instance);
    }

    @Override
    public void delete(String id) {
        categories.removeIf(category -> category.getId().equals(id));
        WriteReadJSONUtil.readDatabase(instance);
    }

    @Override
    public Category findById(String id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("category not found by id"));
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public String getPath() {
        return PATH_OF_FILE_CATEGORIES;
    }

    @Override
    public void setDB(List<? extends BaseEntity> listOfEntities) {
        this.categories = (List<Category>) listOfEntities;
    }

    @Override
    public Class getClassOfEntity() {
        return Category.class;
    }

    public static String generateId(List<? extends BaseEntity> items) {
        String id = UUID.randomUUID().toString();
        if (items.stream().anyMatch(item -> item.getId().equals(id))) {
            return generateId(items);
        }
        return id;
    }
}
