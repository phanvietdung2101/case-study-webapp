package service;

import model.OrderItem;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO implements IOrderDAO {
    private static final String QUERY_REMOVE_ORDER_ITEM = "{call remove_order_item(?)}";
    private static final String QUERY_CHECK_OUT_ORDER = "{call check_out_all_item(?)}";
    private static final String QUERY_ADD_ORDER_ITEM = "{call addOrderItem(?,?)}";
    private static final String QUERY_FIND_ORDER_ID = "{call find_order_by_user_id(?)}";
    private static final String QUERY_ADD_ORDER = "{call create_new_order_by_user_id(?)}";
    private MySqlConnection mySqlConnection = new MySqlConnection();
    private final String QUERY_FIND_ORDER_ITEM = "{call find_order_list(?)}";

    @Override
    public List<OrderItem> findAllOrderItemByUserId(int user_id) {
        List<OrderItem> orderItemList = new ArrayList<>();
        try(
                Connection connection = mySqlConnection.getConnection();
                CallableStatement statement = connection.prepareCall(QUERY_FIND_ORDER_ITEM);
        ) {
            statement.setInt(1,user_id);
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

    @Override
    public boolean addOrderItem(int product_id, int user_id) {
        boolean isAdd = false;
        try (
                Connection connection = mySqlConnection.getConnection();
                CallableStatement callableStatement = connection.prepareCall(QUERY_ADD_ORDER_ITEM);
                ) {
            callableStatement.setInt(1,user_id);
            callableStatement.setInt(2,product_id);
            isAdd = callableStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdd;
    }

    @Override
    public boolean findOrderByUserId(int user_id) {
        boolean isExist = false;
        try (
            Connection conn = mySqlConnection.getConnection();
            CallableStatement statement = conn.prepareCall(QUERY_FIND_ORDER_ID);
        ) {
            statement.setInt(1,user_id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                isExist = true;
            }
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    @Override
    public boolean createNewOrder(int user_id) {
        boolean isCreate = false;
        try (
            Connection conn = mySqlConnection.getConnection();
            CallableStatement statement = conn.prepareCall(QUERY_ADD_ORDER)
        ){
            statement.setInt(1,user_id);
            isCreate = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isCreate;
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.addOrderItem(3,1));
    }




}
