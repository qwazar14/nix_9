package ua.com.alevel.controller;

import ua.com.alevel.config.GenerateImpl;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;
import ua.com.alevel.service.ItemService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class ItemController {

    final ItemService itemService = GenerateImpl.generateImplementation(ItemService.class);

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {

        System.out.println();
        System.out.println("1. Add item (create)");
        System.out.println("2. Update item (update)");
        System.out.println("3. Delete item (delete)");
        System.out.println("4. Find item by UUID (findById)");
        System.out.println("5. Find all items (findAll)");
        System.out.println("0. Go back");
        System.out.println("x. Exit");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll(reader);
            case "0" -> new MainController().run();
            case "x" -> System.exit(0);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("[CREATING] Enter item name");
            String name = reader.readLine();
            System.out.println("[CREATING] Enter item price");
            String pr = reader.readLine();
            int price = 0;
            try {
                price = Integer.parseInt(pr);

            } catch (Exception e) {
                System.out.println("invalid data format, use only numbers");
                return;
            }
            System.out.println("[CREATING] Enter category UUID to put item");
            String idCategory = reader.readLine();
            Item item = new Item();
            Category categoryName = CategoryController.categoryService.findById(idCategory);
            if (categoryName == null) {
                System.out.println("category doesn't exist");
                return;
            }
            item.setCategory(categoryName);
            item.setPrice(price);
            item.setName(name);
            itemService.create(item);
            CategoryController.categoryService.findById(idCategory).setItems(item);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        int price = 0;
        try {
            System.out.println("[UPDATING] Enter item's UUID: ");
            String id = reader.readLine();
            System.out.println("[UPDATING] Enter item's price");
            String pr = reader.readLine();
            try {
                price = Integer.parseInt(pr);
            } catch (NoSuchElementException e) {
                System.out.println("invalid data format, use only numbers\"");
            }
            Item item = new Item();
            item.setId(id);
            item.setPrice(price);
            itemService.update(item);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("[DELETING] Enter item's UUID: ");
            String id = reader.readLine();
            itemService.delete(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("[FIND] Enter item's UUID: ");
            String id = reader.readLine();
            Item item = itemService.findById(id);
            if (item != null) {
                System.out.println("item = " + item);
            } else {
                System.out.println("item not found");
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        Item[] items = itemService.findAll();
        if (items != null && items.length != 0) {
            for (Item item : items) {
                System.out.println("item = " + item);
            }
        } else {
            System.out.println("items list empty");
        }
    }

}