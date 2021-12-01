package ua.com.alevel.entity;

public class Gym extends BaseEntity {
    private String gymName;

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "id='" + super.getId() + '\'' +
                ", gymName='" + gymName + '\'' +
                '}';
    }
}

