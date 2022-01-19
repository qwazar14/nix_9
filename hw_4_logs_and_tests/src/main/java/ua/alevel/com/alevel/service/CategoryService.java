package ua.alevel.com.alevel.service;

import ua.alevel.com.alevel.entity.Category;
import ua.alevel.com.alevel.entity.Item;

public interface CategoryService extends BaseService<Category> {

    Item[] findAllItems(Category category);
}