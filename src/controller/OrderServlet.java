package controller;

import model.OrderItem;
import service.OrderDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet",urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    OrderDAO orderDAO = new OrderDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = String.valueOf(request.getSession(false).getAttribute("username"));
        if(username == "null"){
            response.sendRedirect("/login");
        } else {
            List<OrderItem> orderItemList = orderDAO.findAllOrderItemByUsername(username);
            request.setAttribute("orderItemList",orderItemList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/product/order.jsp");
            dispatcher.forward(request, response);
        }
    }
}
