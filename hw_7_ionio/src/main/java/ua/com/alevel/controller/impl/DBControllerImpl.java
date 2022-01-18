package ua.com.alevel.controller.impl;

import ua.com.alevel.controller.BaseController;
import ua.com.alevel.controller.DBController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static ua.com.alevel.util.PrintUIUtil.printMainNavigation;

public class DBControllerImpl implements DBController {

    private final Map<String, BaseController> entityInDB = new HashMap<>();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            printMainNavigation();
            while ((position = reader.readLine()) != null) {
                runNavigation(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    public DBControllerImpl() {
        entityInDB.put("Company", new CategoryControllerImpl());
        entityInDB.put("Customer", new ItemControllerImpl());
    }

    private void runNavigation(String position, BufferedReader reader) {
        switch(position){
            case "1" -> new CategoryControllerImpl().run();
            case "2" -> new ItemControllerImpl().run();
            case "0" -> System.exit(0);
        }
    }

}
