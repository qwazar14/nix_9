package ua.com.alevel.controller;

import ua.com.alevel.config.GenerateImpl;
import ua.com.alevel.entity.Category;
import ua.com.alevel.service.CategoryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CategoryController {
    static final CategoryService categoryService = GenerateImpl.generateImplementation(CategoryService.class);

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
        System.out.println("1. Add category (create)");
        System.out.println("2. Update category (update)");
        System.out.println("3. Delete category (delete)");
        System.out.println("4. Find category by UUID (findById)");
        System.out.println("5. Find all categories (findAll)");
        System.out.println("6. Find all items in category (findAllItems)");
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
            case "6" -> findAllItems(reader);
            case "0" -> new MainController().run();
            case "x" -> System.exit(0);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.println("[CREATING] Enter category name: ");
            String name = reader.readLine();
            Category category = new Category();
            category.setName(name);
            categoryService.create(category);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.println("[UPDATING] Enter category's UUID: ");
            String id = reader.readLine();
            System.out.println("[UPDATING] Enter new category name");
            String name = reader.readLine();
            Category category = new Category();
            category.setId(id);
            category.setName(name);
            categoryService.update(category);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.println("[DELETING] Enter category's UUID: ");
            String id = reader.readLine();
            categoryService.delete(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.println("[FIND] Enter category's UUID: ");
            String id = reader.readLine();
            Category category = categoryService.findById(id);
            if (category != null) {
                System.out.println("category = " + category);
            } else {
                System.out.println("category with UUID '" + id + "' not found");
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        Category[] categories = categoryService.findAll();
        if (categories != null && categories.length != 0) {
            for (Category category : categories) {
                System.out.println("category = " + category);
            }
        } else {
            System.out.println("list of categories is empty");
        }
    }

    private void findAllItems(BufferedReader reader) {
        int count = 0;
        try {
            System.out.println("[FIND] Enter category's UUID: ");
            String id = reader.readLine();
            if (categoryService.findById(id) == null) {
                System.out.println("category with UUID '" + id + "' not found");
                return;
            }
            if (categoryService.findAllItems(categoryService.findById(id)) == null) {
                System.out.println("list of items is empty for this category " + categoryService.findById(id).getName());
                return;
            }
            for (int i = 0; i < categoryService.findAllItems(categoryService.findById(id)).length; i++) {
                if (categoryService.findAllItems(categoryService.findById(id))[i] != null) {
                    System.out.print(categoryService.findAllItems(categoryService.findById(id))[i] + "\n");
                } else {
                    count++;
                }
            }
            if (count == categoryService.findAllItems(categoryService.findById(id)).length) {
                System.out.println("item list is empty");
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}