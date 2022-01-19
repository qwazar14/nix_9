package ua.alevel.com.alevel.db;

import ua.alevel.com.alevel.entity.Category;
import ua.alevel.com.alevel.entity.Item;

public interface CategoryDB extends BaseDB<Category> {
    Item[] findAllItems(Category category);
}