package service;

import model.Category;
import model.Product;
import model.Tag;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{

    private static final String QUERY_FIND_ALL = "SELECT * FROM view_all";
    private static final String QUERY_FIND_BY_ID = "SELECT * FROM Product where id = ?";
    private static final String QUERY_FIND_CATEGORY = "{call view_product_by_category(?)}";
    private static final String QUERY_LIST_CATEGORY = "SELECT * FROM Category";
    private static final String QUERY_LIST_TAG = "SELECT * FROM Tag";
    private static final String QUERY_FIND_TAG = "{call view_product_by_tag(?)}";
    private static final String QUERY_FIND_CATEGORY_TAG = "{call view_product_by_category_tag(?,?)}";

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

}
