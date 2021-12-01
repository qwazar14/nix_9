package ua.com.alevel.db;

import ua.com.alevel.entity.Gym;
import ua.com.alevel.entity.Trainer;

public interface TrainerDB extends BaseDB<Trainer> {
    Gym tryGetGym(String id);
}
