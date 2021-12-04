package ua.com.alevel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;
import ua.com.alevel.service.impl.CategoryServiceImpl;
import ua.com.alevel.service.impl.ItemServiceImpl;

public class ServicesTest {

    public static final ItemServiceImpl itemService = new ItemServiceImpl();
    public static final CategoryServiceImpl categoryService = new CategoryServiceImpl();

    private static final int DEFAULT_SIZE_ITEMS = 100;
    private static final int PRICE = 100;
    private static final String ID = "100";
    private static final int PRICE_UPDATE = 10;
    private static final String NAME_ITEMS = "Item";
    private static final int DEFAULT_SIZE = 100;
    private static final String NAME = "Category";
    private static final Category CATEGORY = generateRandomCategory();
    private static final String NAME_UPDATE = "CategoryUpdate";

    @BeforeAll
    @Order(1)
    public static void setUpCategories() {
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            Category category = generateCategory(NAME + i);
            categoryService.create(category);
        }
        Assertions.assertEquals(categoryService.findAll().length, DEFAULT_SIZE);
    }

    @BeforeAll
    @Order(2)
    public static void setUpItems() {
        for (int i = 0; i < DEFAULT_SIZE_ITEMS; i++) {
            Item item = generateItem(NAME_ITEMS + i, PRICE + i, CATEGORY);
            itemService.create(item);
            item.getCategory().setItems(item);
        }
        Assertions.assertEquals(itemService.findAll().length, DEFAULT_SIZE_ITEMS);
    }

    private static Category generateRandomCategory() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        return category;
    }

    private static Category generateCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    private static Item generateRandomItem() {
        Item item = new Item();
        item.setName(NAME_ITEMS);
        item.setPrice(PRICE);
        item.setCategory(CATEGORY);
        return item;
    }

    private static Item generateItem(String name, int price, Category category) {
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
    public void shouldBeUpdateCategoryById() {
        Category category = getRandomCategory();
        category.setName(NAME_UPDATE);
        categoryService.update(category);
        category = categoryService.findById(category.getId());
        Assertions.assertEquals(NAME_UPDATE, category.getName());
    }

    @Test
    @Order(2)
    public void shouldBeFindAllItems() {
        int numberOfNullElements = 0;
        Item[] items = itemService.findAll();
        for (Item item : items) {
            if (item == null) {
                numberOfNullElements++;
            }
        }
        Assertions.assertEquals(itemService.findAll().length - numberOfNullElements, DEFAULT_SIZE_ITEMS);
    }


    @Test
    @Order(3)
    public void shouldBeCreateItem() {
        Item item = generateRandomItem();
        itemService.create(item);
        Item[] items = itemService.findAll();
        Assertions.assertEquals(items.length, DEFAULT_SIZE_ITEMS + 1);
    }

    @Test
    @Order(4)
    public void shouldBeUpdateItemById() {
        Item item = getRandomItem();
        item.setPrice(PRICE_UPDATE);
        itemService.update(item);
        item = itemService.findById(item.getId());
        Assertions.assertEquals(PRICE_UPDATE, item.getPrice());
    }

    @Test
    @Order(5)
    public void shouldBeDeleteItemById() {
        int notNullElement = 0;
        Item item = getRandomItem();
        Category category = item.getCategory();
        itemService.delete(item.getId());
        Assertions.assertEquals(itemService.findAll().length, DEFAULT_SIZE_ITEMS);
        Item[] items = categoryService.findAllItems(category);
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                notNullElement++;
            }
        }
        Assertions.assertEquals(notNullElement, DEFAULT_SIZE_ITEMS - 1);
    }

    @Test
    @Order(6)
    public void shouldBeDeleteCategoryById() {
        int numberOfNotNullElements = 0;
        Category category = generateRandomCategory();
        Item[] items = categoryService.findAllItems(category);
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                numberOfNotNullElements++;
            }
        }
        categoryService.delete(category.getId());
        Assertions.assertEquals(categoryService.findAll().length, DEFAULT_SIZE + 1);
    }
}