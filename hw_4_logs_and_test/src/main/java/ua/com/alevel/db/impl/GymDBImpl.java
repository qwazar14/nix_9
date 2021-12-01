package ua.com.alevel.db.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.db.GymDB;
import ua.com.alevel.entity.Gym;
import ua.com.alevel.entity.Trainer;
import ua.com.alevel.util.GenerateUUID;

import java.util.Arrays;
import java.util.List;

public class GymDBImpl implements  GymDB {
    private final Logger log = LoggerFactory.getLogger("error");

    private Gym [] gymsArray;
    private static GymDBImpl instance;
    private static int count = 0;

    private GymDBImpl(){
        gymsArray = new Gym[2];
    }

    public static GymDBImpl getInstance() {
        if (instance == null) {
            instance = new GymDBImpl();
        }
        return instance;
    }


    @Override
    public void create(Gym entity) {
        entity.setId(GenerateUUID.generateId(gymsArray, count));
        gymsArray[count] = entity;
        count++;
        if(count == gymsArray.length) {
            gymsArray = Arrays.copyOf(gymsArray, count + 10);
        }
    }

    @Override
    public void update(Gym entity) {
        gymsArray[findIndexGymInArray(entity.getId())] = entity;
    }

    @Override
    public void delete(String id) {
        int idInArray = findIndexGymInArray(id);
        gymsArray[idInArray] = null;
        for (int i = idInArray; i < count-1; i++) {
            gymsArray[i] = gymsArray[i+1];
        }
        gymsArray[count-1] = null;
        count--;
    }

    @Override
    public Gym findById(String id) {
        for (int i = 0; i < count; i++) {
            if (gymsArray[i].getId().equals(id)) {
                return gymsArray[i];
            }
        }
        throw new RuntimeException("gym not found by id");

    }

    @Override
    public Gym[] findAll() {
        Gym[] allGyms = new Gym[count];
        for (int i = 0; i < count; i++) {
            allGyms[i] = gymsArray[i];
        }
        return allGyms;
    }

    @Override
    public List<Trainer> getTrainersByGym(String id) {
        return null;
    }
    private int findIndexGymInArray(String id) {
        for (int i = 0; i < count; i++) {
            if (gymsArray[i].getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("company not found by id");
    }
}
