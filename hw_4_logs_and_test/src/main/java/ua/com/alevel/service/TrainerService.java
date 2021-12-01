package ua.com.alevel.service;

import ua.com.alevel.entity.Gym;
import ua.com.alevel.entity.Trainer;

public interface TrainerService extends BaseService<Trainer> {
    Gym tryGetGym(String id);
}
