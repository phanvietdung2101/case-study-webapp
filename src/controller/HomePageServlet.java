package controller;

import model.Product;
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
            default:
                showHomepage(request,response);
                break;

        }
    }

    private void addToCard(HttpServletRequest request, HttpServletResponse response) {

        try {
            if (request.getParameter("user_id") != null) {
                int product_id = Integer.parseInt(request.getParameter("product_id"));
                int user_id = Integer.parseInt(request.getParameter("user_id"));
                OrderDAO orderDAO = new OrderDAO();
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
        List<Product> productList = productDAO.findAll();
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
}
