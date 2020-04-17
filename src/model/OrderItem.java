package model;

public class OrderItem {
    private int id;
    private String name;
    private String image;
    private int quantity;
    private long item_price;
    private int order_id;


    public OrderItem() {
    }

    public OrderItem(String name,String image, int quantity, long item_price) {
        this.name = name;
        this.quantity = quantity;
        this.item_price = item_price;
        this.image = image;
    }

    public OrderItem(int id, String name, String image, int quantity, long item_price, int order_id) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.item_price = item_price;
        this.order_id = order_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
