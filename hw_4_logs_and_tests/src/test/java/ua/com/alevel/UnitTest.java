package ua.com.alevel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;
import ua.com.alevel.service.impl.CategoryServiceImpl;
import ua.com.alevel.service.impl.ItemServiceImpl;

public class UnitTest {

    public static final CategoryServiceImpl categoryService = new CategoryServiceImpl();
    public static final ItemServiceImpl itemService = new ItemServiceImpl();

    public static final int ENTITY_SIZE = 5;

    public static final int PRICE_MIN = 0;
    public static final int PRICE_MAX = 100;
    public static final String ID = "entity_id";
    public static final String ITEM_NAME = "item";
    public static final String CATEGORY_NAME = "category";
    public static final String IS_UPDATED = "_updated";
    public static final Category CATEGORY = generateRandomCategory();

    @BeforeAll
    @Order(1)
    public static void setItems() {
        for (int i = 0; i < ENTITY_SIZE; i++) {
            Item item = generateSomeItems(ITEM_NAME + i, getRandomPrice(), CATEGORY);
            itemService.create(item);
            item.getCategory().setItems(item);
        }
        Assertions.assertEquals(itemService.findAll().length, ENTITY_SIZE);
    }

    @BeforeAll
    @Order(2)
    public static void setCategory() {
        for (int i = 0; i < ENTITY_SIZE; i++) {
            Category category = generateSomeCategories(CATEGORY_NAME + 1);
            categoryService.create(category);
        }
        Assertions.assertEquals(categoryService.findAll().length, ENTITY_SIZE);
    }

    public static int getRandomPrice() {
        return (int) ((Math.random() * (PRICE_MAX - PRICE_MIN)) + PRICE_MAX);
    }

    private static Category generateRandomCategory() {
        Category category = new Category();
        category.setId(ID);
        category.setName(CATEGORY_NAME);
        return category;
    }

    private static Category generateSomeCategories(String name) {
        Category category = new Category();
        category.setId(ID);
        category.setName(name);
        return category;
    }

    private static Item generateRandomItems() {
        Item item = new Item();
        item.setName(ITEM_NAME);
        item.setPrice(getRandomPrice());
        item.setCategory(CATEGORY);
        return item;
    }

    private static Item generateSomeItems(String name, int price, Category category) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setCategory(category);
        return item;
    }

    private static Item getRandomItem() {
        return itemService.findAll()[0];
    }

    private static Category getRandomCategory() {
        return categoryService.findAll()[0];
    }

    @Test
    @Order(1)
    public void shouldBeCreateCategory() {
        Category category = generateRandomCategory();
        categoryService.create(category);
        Category[] categories = categoryService.findAll();
        Assertions.assertEquals(categories.length - 1, ENTITY_SIZE + 1);
    }

    @Test
    @Order(2)
    public void shouldBeUpdateCategoryById() {
        Category category = getRandomCategory();
        category.setName(CATEGORY_NAME + IS_UPDATED);
        categoryService.update(category);
        category = categoryService.findById(category.getId());
        Assertions.assertEquals(CATEGORY_NAME + IS_UPDATED, category.getName());
    }

    @Test
    @Order(3)
    public void shouldBeCreateCategoryWhenNameIsNull() {
        Category category = new Category();
        category.setName(null);
        categoryService.create(category);
    }

    @Test
    @Order(4)
    public void shouldBeCreateItem() {
        Item item = generateRandomItems();
        itemService.create(item);
        Item[] items = itemService.findAll();
        Assertions.assertEquals(items.length, ENTITY_SIZE + 1);
    }

    @Test
    @Order(5)
    public void shouldBeUpdateItemById() {
        Item item = getRandomItem();
        item.setName(ITEM_NAME + IS_UPDATED);
        itemService.update(item);
        item = itemService.findById(item.getId());
        Assertions.assertEquals(ITEM_NAME + IS_UPDATED, item.getName());
    }


}
