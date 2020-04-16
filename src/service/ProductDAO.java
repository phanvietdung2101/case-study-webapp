package service;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{

    private static final String QUERY_FIND_ALL = "SELECT * FROM view_all";
    private static final String QUERY_FIND_BY_ID = "SELECT * FROM Product where id = ?";

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_ALL);
                ResultSet resultSet = statement.executeQuery()
            )
        {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                long price = resultSet.getLong("price");
                String image = resultSet.getString("image");

                productList.add(new Product(id,name,price,image));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop_online_m3", "root", "password");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try(
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_BY_ID)
        ) {
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                long price = resultSet.getLong("price");
                String image = resultSet.getString("image");
                product = new Product(id,name,description,price,image);
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
