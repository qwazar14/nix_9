package ua.com.alevel.entity;

public class Category extends BaseEntity {


    private String name;
    private Item[] items;

    public Category() {
        items = new Item[10];
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void setItems(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                return;
            }
        }
        changeSize();
        setItems(item);
    }

    public void changeSize() {
        Item[] newItems = new Item[items.length * 2];
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + super.getId() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}