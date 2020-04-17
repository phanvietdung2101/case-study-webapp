package service;

import model.OrderItem;

import java.util.List;

public interface IOrderDAO {
    List<OrderItem> findAllOrderItemByUsername(String username);

    boolean removeOrderItem(int id);
}
