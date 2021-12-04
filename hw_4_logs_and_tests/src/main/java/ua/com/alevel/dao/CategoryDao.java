package ua.com.alevel.dao;

import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;

public interface CategoryDao extends BaseDao<Category> {
    Item[] findAllItems(Category category);
}