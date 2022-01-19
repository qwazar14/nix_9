package ua.alevel.com.alevel.dao;

import ua.alevel.com.alevel.entity.Category;
import ua.alevel.com.alevel.entity.Item;

public interface CategoryDao extends BaseDao<Category> {
    Item[] findAllItems(Category category);
}