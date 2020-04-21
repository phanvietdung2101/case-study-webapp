package controller;

import model.Category;
import model.Product;
import model.Tag;
import model.User;
import service.OrderDAO;
import service.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomePageServlet",urlPatterns = "/index")
public class HomePageServlet extends HttpServlet {
    ProductDAO productDAO = new ProductDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "add_to_cart":
                addToCard(request,response);
                break;
            case "view":
                showProduct(request,response);
                break;
            case "search":
                showHomepageSearchName(request,response);
                break;
            default:
                showHomepage(request,response);
                break;

        }
    }

    private void showHomepageSearchName(HttpServletRequest request, HttpServletResponse response) {
        String string = request.getParameter("string");
        List<Product> productList = productDAO.searchByName(string);
        List<Category> categoryList = productDAO.listAllCategoryName();
        List<Tag> tagList = productDAO.listAllTagName();

        request.setAttribute("categoryList",categoryList);
        request.setAttribute("tagList",tagList);
        request.setAttribute("productList",productList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void addToCard(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            if (user != null) {
                int product_id = Integer.parseInt(request.getParameter("product_id"));
                int user_id = user.getId();
                OrderDAO orderDAO = new OrderDAO();
                if(!orderDAO.findOrderByUserId(user_id)){
                    orderDAO.createNewOrder(user_id);
                }
                orderDAO.addOrderItem(product_id, user_id);
                response.sendRedirect("/index");
            } else {
                response.sendRedirect("/login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.findById(id);
        request.setAttribute("product",product);
        RequestDispatcher dispatcher ;
        if(product == null){
            dispatcher = request.getRequestDispatcher("error.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/view/product/product-view.jsp");
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showHomepage(HttpServletRequest request, HttpServletResponse response) {
        String category_name = request.getParameter("category");
        String tag_name = request.getParameter("tag");
        List<Product> productList;
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/home/filter-product.jsp");

        if(category_name != null && tag_name != null){
            productList = productDAO.findCategoryTag(category_name,tag_name);
        }
        else if(category_name != null){
            productList = productDAO.findCategory(category_name);
        }
        else if(tag_name != null){
            productList = productDAO.findTag(tag_name);
        }
        else {
            productList = productDAO.findAll();
            dispatcher = request.getRequestDispatcher("index.jsp");
        }



        List<Category> categoryList = productDAO.listAllCategoryName();
        List<Tag> tagList = productDAO.listAllTagName();

        request.setAttribute("categoryList",categoryList);
        request.setAttribute("tagList",tagList);
        request.setAttribute("productList",productList);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
