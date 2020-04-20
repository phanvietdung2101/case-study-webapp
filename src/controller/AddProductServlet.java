package controller;

import model.Category;
import model.Tag;
import service.IProductDAO;
import service.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddProductServlet",urlPatterns = "/addProduct")
public class AddProductServlet extends HttpServlet {
    private IProductDAO productDAO = new ProductDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        int tag_id = Integer.parseInt(request.getParameter("tag_id"));

        boolean added;
        added = productDAO.addProduct(name,price,image,description,category_id,tag_id);
        if(added  == true){
            String msg = "Add success";
            request.setAttribute("msg",msg);
        }
        showAddProductForm(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            default:
                showAddProductForm(request,response);
                break;
        }

    }

    private void showAddProductForm(HttpServletRequest request, HttpServletResponse response) {
        List<Tag> tagList = productDAO.listAllTagName();
        List<Category> categoryList = productDAO.listAllCategoryName();
        request.setAttribute("tagList",tagList);
        request.setAttribute("categoryList",categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/addProduct.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
