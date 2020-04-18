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
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "remove":
                removeOrderItem(request,response);
                break;
            case "checkout":
                checkOutOrder(request,response);
                break;
            default:
                showOrderList(request, response);
                break;
        }



    }

    private void checkOutOrder(HttpServletRequest request, HttpServletResponse response) {
        int order_id = Integer.parseInt(request.getParameter("id"));
        if(order_id != 0){
            orderDAO.checkOutOrder(order_id);
        }
        try {
            response.sendRedirect("/order");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showOrderList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = String.valueOf(request.getSession(false).getAttribute("username"));
        if(username == "null"){
            response.sendRedirect("/login");
        } else {
            List<OrderItem> orderItemList = orderDAO.findAllOrderItemByUsername(username);
            int totalPrice = 0;
            for(OrderItem orderItem : orderItemList){
                totalPrice += orderItem.getItem_price();
            }
            int order_id = 0;
            if(orderItemList.size() > 0) {
                order_id = orderItemList.get(0).getOrder_id();
            }
            request.setAttribute("order_id",order_id);
            request.setAttribute("totalPrice",totalPrice);
            request.setAttribute("orderItemList",orderItemList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/product/order.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void removeOrderItem(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        orderDAO.removeOrderItem(id);
        try {
            response.sendRedirect("/order");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
