package ua.com.alevel.controller;

import ua.com.alevel.entity.User;
import ua.com.alevel.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserController {

    private final UserService userService = new UserService();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Выберите операцию:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("X")) {
                    System.exit(0);
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("1. Создать пользователя (create)");
        System.out.println("2. Редактировать пользователя (update)");
        System.out.println("3. Удалить пользователся (delete)");
        System.out.println("4. Найти пользователя по ID (findById)");
        System.out.println("5. Открыть список всех пользователей (findAll");
        System.out.println("X. Закрыть программу");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll(reader);
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("UserController.create");
        try {
            System.out.println("Введите имя");
            String name = reader.readLine();

            System.out.println("Введите возраст");
            String ageString = reader.readLine();
            int age = Integer.parseInt(ageString);

            User user = new User();
            user.setAge(age);
            user.setName(name);

            userService.create(user);
        } catch (NumberFormatException | IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("UserController.update");
        try {
            System.out.println("Введите ID");
            String id = reader.readLine();

            if (id.length() < 32) {
                String message = "Введите корректный ID";
                System.out.println(message);
            } else {
                System.out.println("Введите новое имя");
                String name = reader.readLine();

                System.out.println("Введите новый возраст");
                String ageString = reader.readLine();
                int age = Integer.parseInt(ageString);

                User user = new User();
                user.setId(id);
                user.setAge(age);
                user.setName(name);

                userService.update(user);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("UserController.delete");
        try {
            System.out.println("Введите ID");
            String id = reader.readLine();

            boolean deleteResult = userService.delete(id);
            if (!deleteResult) {
                System.out.println("Пользователь не найден...");
            }
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("UserController.findById");
        try {
            System.out.println("Введите ID");
            String id = reader.readLine();

            User user = userService.findById(id);

            String message = user != null ? ("пользователь = " + user) : "пользователь c ID: \"" + id + "\" не существует ";
            System.out.println(message);
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private void findAll(BufferedReader reader) {
        System.out.println("UserController.findAll");
        User[] users = userService.findAll();
        if (users != null && users.length != 0) {
            for (User user : users) {
                System.out.println("пользователь = " + user);
            }
        } else {
            System.out.println("список пользователей пуст...");
        }
    }
}