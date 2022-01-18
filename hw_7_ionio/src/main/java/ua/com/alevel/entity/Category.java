package ua.com.alevel.entity;

public class Category extends BaseEntity {

    private String name;

    public Category() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
