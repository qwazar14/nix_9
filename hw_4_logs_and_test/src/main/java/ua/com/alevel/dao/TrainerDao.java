package ua.com.alevel.dao;

import ua.com.alevel.entity.Gym;
import ua.com.alevel.entity.Trainer;

public interface TrainerDao extends BaseDao<Trainer> {
    Gym tryGetGym(String id);
}
