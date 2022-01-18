package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.CategoryController;
import ua.com.alevel.entity.Category;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.service.impl.CategoryServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static ua.com.alevel.util.PrintUIUtil.printCategoryMenu;

public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService = new CategoryServiceImpl();


    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            printCategoryMenu();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crud(String pos, BufferedReader reader) {
        switch (pos) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll();
            case "0" -> new DBControllerImpl().run();
        }
        printCategoryMenu();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.print("Enter category name: ");
            String name = reader.readLine();
            Category category = new Category();
            category.setName(name);
            categoryService.create(category);
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.print("Enter category ID: ");
            String id = reader.readLine();
            System.out.print("Enter new category name: ");
            String name = reader.readLine();
            Category category = new Category();
            category.setId(id);
            category.setName(name);
            categoryService.update(category);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.print("Enter category ID: ");
            String id = reader.readLine();
            categoryService.delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.print("Enter category ID: ");
            String id = reader.readLine();
            Category category = categoryService.findById(id);
            System.out.print("category = " + category);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findAll() {
        List<Category> categories = categoryService.findAll();
        if (categories.size() != 0) {
            for (Category category : categories) {
                System.out.println("category = " + category);
            }
        } else {
            System.out.println("Category list empty");
        }
    }
}
