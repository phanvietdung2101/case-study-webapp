package service;

import model.User;

public interface IUserDAO {
    User findUserLogin(String email,String password);
    User findUserDetail(String email);
    boolean changePassword(String email,String oldPassword,String newPassword);
    boolean validateUser(String email,String password);
}
