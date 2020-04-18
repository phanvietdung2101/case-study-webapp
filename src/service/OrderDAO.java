package service;

import model.OrderItem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private static final String QUERY_REMOVE_ORDER_ITEM = "{call remove_order_item(?)}";
    private static final String QUERY_CHECK_OUT_ORDER = "{call check_out_all_item(?)}";
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
                int id = resultSet.getInt("id");
                String product_name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                long item_price = resultSet.getLong("item_price");
                String image = resultSet.getString("image");
                int order_id = resultSet.getInt("order_id");
                orderItemList.add(new OrderItem(id,product_name,image,quantity,item_price, order_id));
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    @Override
    public boolean removeOrderItem(int item_id) {
        boolean rowRemoved = false;
        try (
                Connection connection = mySqlConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(QUERY_REMOVE_ORDER_ITEM);
        ){
            callableStatement.setInt(1, item_id);
            rowRemoved = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowRemoved;
    }

    @Override
    public boolean checkOutOrder(int order_id) {
        boolean ischeckOut = false;
        try (
                Connection connection = mySqlConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(QUERY_CHECK_OUT_ORDER)
        ) {
            callableStatement.setInt(1,order_id);
            ischeckOut = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ischeckOut;
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.checkOutOrder(1));

    }


}
