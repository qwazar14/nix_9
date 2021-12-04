package ua.com.alevel.db;

import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;

public interface CategoryDB extends BaseDB<Category> {
    Item[] findAllItems(Category category);
}