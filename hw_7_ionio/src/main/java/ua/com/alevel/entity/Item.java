package ua.com.alevel.entity;

public class Item extends BaseEntity {

    private String name;
    private int price;

    public Item() {
        super();
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
        return "Item{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
