package ua.com.alevel.service;

import ua.com.alevel.entity.Gym;
import ua.com.alevel.entity.Trainer;

import java.util.List;

public interface GymService extends BaseService<Gym> {
    List<Trainer> getTrainersByGym(String id);
}
