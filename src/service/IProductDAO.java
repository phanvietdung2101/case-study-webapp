package service;

import model.Category;
import model.Product;
import model.Tag;

import java.sql.Connection;
import java.util.List;

public interface IProductDAO {
    List<Product> findAll();
    List<Product> findCategory(String category_name);
    List<Product> findTag(String tag_name);
    List<Product> findCategoryTag(String category_name,String tag_name);
    List<Category> listAllCategoryName();
    List<Tag> listAllTagName();
    Product findById(int id);
    List<Product> searchByName(String string);

    boolean addProduct(String name, long price, String image, String description, int category_id, int tag_id);
}
