package service;

import model.OrderItem;

import java.util.List;

public interface IOrderDAO {
    List<OrderItem> findAllOrderItemByUsername(String username);

    boolean removeOrderItem(int item_id);
    boolean checkOutOrder(int order_id);
    boolean addOrderItem(int product_id,int user_id);
}
