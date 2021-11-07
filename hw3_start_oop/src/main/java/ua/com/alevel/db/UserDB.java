package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.Arrays;
import java.util.UUID;

public final class UserDB {

    private static UserDB instance;
    int size = 0;
    private User[] users;

    private UserDB() {
        users = new User[size];
    }

    public static UserDB getInstance() {
        if (instance == null) {
            instance = new UserDB();
        }
        return instance;
    }

    public void create(User user) {
        User[] usersDuplicate = new User[size + 1];
        user.setId(generateId());
        if (size >= 0) System.arraycopy(users, 0, usersDuplicate, 0, size);
        size++;
        usersDuplicate[size - 1] = user;
        users = usersDuplicate;
    }

    public void update(User user) {
        User current = findById(user.getId());
        current.setAge(user.getAge());
        current.setName(user.getName());
    }

    public boolean delete(String id) {
        for (int i = 0; i < size; i++) {
            if (users[i].getId().equals(id)) {
                deleteAt(i);
                return true;
            }
        }
        return false;
    }

    private void deleteAt(int index) {
        User[] usersDuplicate = new User[size - 1];
        for (int i = 0, iD = 0; i < size; i++) {
            if (i != index) {
                usersDuplicate[iD] = users[i];
                iD++;
            }
        }
        size--;
        users = usersDuplicate;
    }

    public User findById(String id) {
        return Arrays.stream(users).filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    public User[] findAll() {
        return users;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (Arrays.stream(users).anyMatch(user -> user.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
