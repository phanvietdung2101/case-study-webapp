package service;

import model.User;

import java.sql.*;

public class UserDAO implements IUserDAO{
    private MySqlConnection mySqlConnection = new MySqlConnection();

    private final String QUERY_ADD_NEW_USER = "{call addNewUser(?,?,?,?,?)}";
    private final String QUERY_FIND_USER_LOGIN = "select id,name,email from User where email = ? and password = ?;";
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
                int id = resultSet.getInt("id");
                user = new User(id,name,email);
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
        boolean isValidate = false ;
        try (
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_USER_LOGIN)
        ) {
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery() ;
            while(resultSet.next()){
                isValidate = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValidate;
    }

    @Override
    public boolean validateEmailUser(String email) {
        boolean isValidate = false;
        try(
                Connection connection = mySqlConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(QUERY_FIND_USER_DETAIL)
                ) {
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                isValidate = true;
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValidate;
    }

    @Override
    public void registerNewUser(String name, String email, String address, String phone_number, String password1) {
        try (
                Connection connection = mySqlConnection.getConnection();
                CallableStatement statement = connection.prepareCall(QUERY_ADD_NEW_USER);
                ){
            statement.setString(1,name);
            statement.setString(2,email);
            statement.setString(3,address);
            statement.setString(4,phone_number);
            statement.setString(5,password1);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String hidePassword(String password) {
        String hide_password = password;
        // Xu ly String

        return hide_password;
    }

//    public static void main(String[] args) {
//        UserDAO userDao = new UserDAO();
//        System.out.println(!userDao.validateEmailUser("phanvietdung2101@gmail.com"));
//    }
}
