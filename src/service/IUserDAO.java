package service;

import model.User;

public interface IUserDAO {
    User findUserLogin(String email,String password);
}
