package service;

import model.User;

import java.sql.*;

public class UserDAO implements IUserDAO{
    private MySqlConnection mySqlConnection = new MySqlConnection();
    private final String QUERY_FIND_USER_LOGIN = "select name from User where email = ? and password = ?;";
    private final String QUERY_FIND_USER_DETAIL = "select * from User where email = ?";
    private final String QUERY_CALL_CHANGE_PASSWORD = "{call changePassword(?,?)}";


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

    @Override
    public boolean changePassword(String email,String oldPassword,String newPassword){
        boolean isChanged = false;
        if(validateUser(email,oldPassword)){
            try (
                    Connection connection = mySqlConnection.getConnection();
                    CallableStatement statement = connection.prepareCall(QUERY_CALL_CHANGE_PASSWORD);
                    ) {
                statement.setString(1,email);
                statement.setString(2,newPassword);

                isChanged = statement.executeUpdate() > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isChanged;
    }

    @Override
    public boolean validateUser(String email, String password){
        boolean isValid = false ;
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_USER_LOGIN)
        ) {
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery() ;
            while(resultSet.next()){
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private String hidePassword(String password) {
        String hide_password = password;
        // Xu ly String

        return hide_password;
    }

//    public static void main(String[] args) {
//        UserDAO userDao = new UserDAO();
//        System.out.println(userDao.validateUser("phan@gmail.com","phanvietdung1")
//        );
//    }
}
