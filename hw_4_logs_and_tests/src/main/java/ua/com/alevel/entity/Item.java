package ua.com.alevel.entity;

public class Item extends BaseEntity {

    private String name;

    private int price;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", shop=" + getCategory().getName() +
                '}';
    }
}