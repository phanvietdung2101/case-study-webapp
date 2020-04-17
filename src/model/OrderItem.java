package model;

public class OrderItem {
    private String name;
    private String image;
    private int quantity;
    private long item_price;


    public OrderItem() {
    }

    public OrderItem(String name,String image, int quantity, long item_price) {
        this.name = name;
        this.quantity = quantity;
        this.item_price = item_price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getItem_price() {
        return item_price;
    }

    public void setItem_price(long item_price) {
        this.item_price = item_price;
    }
}
