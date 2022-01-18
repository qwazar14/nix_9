package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.ItemController;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Item;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.service.ItemService;
import ua.com.alevel.service.impl.CategoryServiceImpl;
import ua.com.alevel.service.impl.ItemServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static ua.com.alevel.util.PrintUIUtil.printCategoryMenu;
import static ua.com.alevel.util.PrintUIUtil.printItemMenu;

public class ItemControllerImpl implements ItemController {

    private final ItemService itemService = new ItemServiceImpl();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            printItemMenu();
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
        printItemMenu();
    }

    private void create(BufferedReader reader) {
        try {
            System.out.print("Enter item name: ");
            String name = reader.readLine();
            System.out.print("Enter price for \"" + name + "\" :");
            int price = Integer.parseInt(reader.readLine());
            Item item = new Item();
            item.setName(name);
            item.setPrice(price);
            itemService.create(item);
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        try {
            System.out.print("Enter item ID: ");
            String id = reader.readLine();
            System.out.print("Enter new item name: ");
            String name = reader.readLine();
            System.out.println("Enter new price for " + name + ":");
            int price = Integer.parseInt(reader.readLine());
            Item item = new Item();
            item.setId(id);
            item.setName(name);
            item.setPrice(price);
            itemService.update(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(BufferedReader reader) {
        try {
            System.out.print("Enter item ID: ");
            String id = reader.readLine();
            itemService.delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findById(BufferedReader reader) {
        try {
            System.out.print("Enter item ID: ");
            String id = reader.readLine();
            Item item = itemService.findById(id);
            System.out.print("item = " + item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findAll() {
        List<Item> items = itemService.findAll();
        if (items.size() != 0) {
            for (Item item : items) {
                System.out.println("items = " + items);
            }
        } else {
            System.out.println("Items list empty");
        }
    }
}

