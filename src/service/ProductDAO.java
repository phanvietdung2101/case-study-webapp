package service;

import model.Category;
import model.Product;
import model.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{

    private static final String QUERY_FIND_ALL = "SELECT * FROM view_all";
    private static final String QUERY_FIND_BY_ID = "{call view_product_detail(?)}";
    private static final String QUERY_FIND_CATEGORY = "{call view_product_by_category(?)}";
    private static final String QUERY_LIST_CATEGORY = "SELECT * FROM Category";
    private static final String QUERY_LIST_TAG = "SELECT * FROM Tag";
    private static final String QUERY_FIND_TAG = "{call view_product_by_tag(?)}";
    private static final String QUERY_FIND_CATEGORY_TAG = "{call view_product_by_category_tag(?,?)}";
    private static final String QUERY_ADD_PRODUCT = "insert into Product(name,price,image) values (?,?,?) ";
    private static final String QUERY_ADD_PRODUCT_DESCRIPTION = "insert into Product_description values (?,?)";
    private static final String QUERY_ADD_PRODUCT_CATEGORY = "insert into Product_category values (?,?)";
    private static final String QUERY_ADD_PRODUCT_TAG = "insert into Product_tag values (?,?)" ;

    private MySqlConnection mySqlConnection = new MySqlConnection();

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try (
                Connection connection = mySqlConnection.getConnection();
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
    public Product findById(int id) {
        Product product = null;
        try(
                Connection connection = mySqlConnection.getConnection();
                CallableStatement statement = connection.prepareCall(QUERY_FIND_BY_ID)
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

    @Override
    public boolean addProduct(String name, long price, String image, String description, int category_id, int tag_id) {
        boolean added = false;
        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;
        PreparedStatement statement4 = null;
        ResultSet rs = null;
        try {
            connection = mySqlConnection.getConnection();
            connection.setAutoCommit(false);
            statement1 = connection.prepareStatement(QUERY_ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS);

            // Insert a product
            statement1.setString(1,name);
            statement1.setLong(2,price);
            statement1.setString(3,image);

            int rowAffected = 0;
            rowAffected = statement1.executeUpdate();
            // Rollback 1

            rs = statement1.getGeneratedKeys();

            int productId = 0;
            while (rs.next()){
                productId = rs.getInt(1);
            }
            if(rowAffected != 1){
                connection.rollback();
            } else {
                // Insert description for product
                statement2 = connection.prepareStatement(QUERY_ADD_PRODUCT_DESCRIPTION);
                statement2.setString(2, description);
                statement2.setInt(1, productId);

                rowAffected = statement2.executeUpdate();
                if(rowAffected != 1){
                    connection.rollback();
                } else {
                    // Insert category for product
                    statement3 = connection.prepareStatement(QUERY_ADD_PRODUCT_CATEGORY);
                    statement3.setInt(1, category_id);
                    statement3.setInt(2, productId);
                    rowAffected = statement3.executeUpdate();

                    if(rowAffected != 1){
                        connection.rollback();
                    } else {
                        // Insert tag for product
                        statement4 = connection.prepareStatement(QUERY_ADD_PRODUCT_TAG);
                        statement4.setInt(1, productId);
                        statement4.setInt(2, tag_id);
                        rowAffected = statement4.executeUpdate();
                        if(rowAffected != 1){
                            connection.rollback();
                        } else {
                            added = true;
                            connection.commit();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            };
        } finally {
            try {
                connection.setAutoCommit(true);
                if(rs != null) rs.close();
                if(statement1 != null) statement1.close();
                if(statement2 != null) statement2.close();
                if(statement3 != null) statement3.close();
                if(statement4 != null) statement4.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return added;
    }

    @Override
    public List<Product> findCategory(String category_name) {
        List<Product> productList = new ArrayList<>();
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_CATEGORY);
        )
        {
            statement.setString(1,category_name);
            ResultSet resultSet = statement.executeQuery();

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
    public List<Product> findTag(String tag_name) {
        List<Product> productList = new ArrayList<>();
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_TAG);
        )
        {
            statement.setString(1,tag_name);
            ResultSet resultSet = statement.executeQuery();

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
    public List<Product> findCategoryTag(String category_name, String tag_name) {
        List<Product> productList = new ArrayList<>();
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_CATEGORY_TAG);
        )
        {
            statement.setString(1,category_name);
            statement.setString(2,tag_name);
            ResultSet resultSet = statement.executeQuery();

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
    public List<Category> listAllCategoryName() {
        List<Category> categoryList = new ArrayList<>();
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_LIST_CATEGORY);
                ResultSet resultSet = statement.executeQuery()
        )
        {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("category");

                categoryList.add(new Category(id,name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public List<Tag> listAllTagName() {
        List<Tag> tagList = new ArrayList<>();
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_LIST_TAG);
                ResultSet resultSet = statement.executeQuery()
        )
        {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("tag");

                tagList.add(new Tag(id,name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tagList;
    }

//    public static void main(String[] args) {
//        ProductDAO productDAO = new ProductDAO();
//        System.out.println(productDAO.addProduct(
//                "iPhone 11 Pro Max",28990000,
//                "https://cdn.cellphones.com.vn/media/catalog/product/cache/7/image/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone-11-pro-max-space-select-2019.png"
//                ,"this is iphone",1,1
//        ));
//    }
}
