package service;

import model.Product;

import java.sql.Connection;
import java.util.List;

public interface IProductDAO {
    List<Product> findAll();
    Product findById(int id);
}
