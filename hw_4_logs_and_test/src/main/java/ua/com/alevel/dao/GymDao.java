package ua.com.alevel.dao;

import ua.com.alevel.entity.Gym;
import ua.com.alevel.entity.Trainer;

import java.util.List;

public interface GymDao extends BaseDao<Gym> {
    List<Trainer> getEmployeesByDepartment(String id);
}