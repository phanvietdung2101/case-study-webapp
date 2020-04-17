package service;

import model.OrderItem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private MySqlConnection mySqlConnection = new MySqlConnection();
    private final String QUERY_FIND_ORDER_ITEM = "{call find_order_list(?)}";

    @Override
    public List<OrderItem> findAllOrderItemByUsername(String username) {
        List<OrderItem> orderItemList = new ArrayList<>();
        try(
                Connection connection = mySqlConnection.getConnection();
                CallableStatement statement = connection.prepareCall(QUERY_FIND_ORDER_ITEM);
        ) {
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String product_name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                long item_price = resultSet.getLong("item_price");
                String image = resultSet.getString("image");
                orderItemList.add(new OrderItem(product_name,image,quantity,item_price));
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

}
