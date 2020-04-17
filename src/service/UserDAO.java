package service;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO{
    private MySqlConnection mySqlConnection = new MySqlConnection();
    private final String QUERY_FIND_USER_BY_EMAIL = "select name from User where email = ? and password = ?;";
    @Override
    public User findUserLogin(String email,String password) {
        User user = null;
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_USER_BY_EMAIL)
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
}
