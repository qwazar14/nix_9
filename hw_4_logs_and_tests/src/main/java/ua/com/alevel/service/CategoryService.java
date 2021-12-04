package ua.com.alevel.service;

import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;

public interface CategoryService extends BaseService<Category> {

    Item[] findAllItems(Category category);
}