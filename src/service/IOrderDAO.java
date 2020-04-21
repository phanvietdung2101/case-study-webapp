package service;

import model.OrderItem;

import java.util.List;

public interface IOrderDAO {
    List<OrderItem> findAllOrderItemByUserId(int user_id);

    boolean removeOrderItem(int item_id);
    boolean checkOutOrder(int order_id);
    boolean addOrderItem(int product_id,int user_id);
    boolean findOrderByUserId(int user_id);
    boolean createNewOrder(int user_id);
}
