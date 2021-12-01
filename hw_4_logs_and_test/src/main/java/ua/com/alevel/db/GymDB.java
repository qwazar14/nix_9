package ua.com.alevel.db;

import ua.com.alevel.entity.Gym;
import ua.com.alevel.entity.Trainer;

import java.util.List;

public interface GymDB extends BaseDB<Gym> {
    List<Trainer> getTrainersByGym(String id);
}
