package ua.com.alevel.db.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.db.TrainerDB;
import ua.com.alevel.entity.Gym;
import ua.com.alevel.entity.Trainer;
import ua.com.alevel.util.GenerateUUID;

import java.util.Arrays;
import java.util.List;

public class TrainerDBImpl implements TrainerDB {

    private final Logger log = LoggerFactory.getLogger("error");

    private Trainer[] trainersArray;
    private static TrainerDBImpl instance;
    private static int count = 0;

    private TrainerDBImpl(){
        trainersArray = new Trainer[2];
    }

    public static TrainerDBImpl getInstance() {
        if (instance == null) {
            instance = new TrainerDBImpl();
        }
        return instance;
    }


    @Override
    public void create(Trainer entity) {
        entity.setId(GenerateUUID.generateId(trainersArray, count));
        trainersArray[count] = entity;
        count++;
        if(count == trainersArray.length) {
            trainersArray = Arrays.copyOf(trainersArray, count + 10);
        }
    }

    @Override
    public void update(Trainer entity) {
        trainersArray[findIndexTrainerInArray(entity.getId())] = entity;
    }

    @Override
    public void delete(String id) {
        int idInArray = findIndexTrainerInArray(id);
        trainersArray[idInArray] = null;
        for (int i = idInArray; i < count-1; i++) {
            trainersArray[i] = trainersArray[i+1];
        }
        trainersArray[count-1] = null;
        count--;
    }

    @Override
    public Trainer findById(String id) {
        for (int i = 0; i < count; i++) {
            if (trainersArray[i].getId().equals(id)) {
                return trainersArray[i];
            }
        }
        throw new RuntimeException("trainer not found by id");

    }

    @Override
    public Trainer[] findAll() {
        Trainer[] allTrainers = new Trainer[count];
        for (int i = 0; i < count; i++) {
            allTrainers[i] = trainersArray[i];
        }
        return allTrainers;
    }


    private int findIndexTrainerInArray(String id) {
        for (int i = 0; i < count; i++) {
            if (trainersArray[i].getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("company not found by id");
    }

    @Override
    public Gym tryGetGym(String id) {
        return null;
    }
}
