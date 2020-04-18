package service;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO{
    private MySqlConnection mySqlConnection = new MySqlConnection();
    private final String QUERY_FIND_USER_LOGIN = "select name from User where email = ? and password = ?;";
    private final String QUERY_FIND_USER_DETAIL = "select * from User where email = ?";

    @Override
    public User findUserLogin(String email,String password) {
        User user = null;
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_USER_LOGIN)
        ) {
            statement.setString(1,email);
            statement.setString(2,password);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String name = resultSet.getString("name");
                user = new User(name);
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findUserDetail(String email){
        User user = null;
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_USER_DETAIL)
                ) {
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone_number = resultSet.getString("phone_number");
                String password = resultSet.getString("password");
                String hide_password = hidePassword(password);
                user = new User(id,name,email,address,phone_number,hide_password);
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private String hidePassword(String password) {
        String hide_password = password;
        // Xu ly String

        return hide_password;
    }
}
