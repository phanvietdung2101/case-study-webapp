package controller;

import model.User;
import service.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AccountServlet",urlPatterns = "/account")
public class AccountServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "change":
                changePassword(request,response);
                break;
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "change":
                showChangePassword(request,response);
                break;
            case "logout":
                logOutAccount(request,response);
                break;
            default:
                showAccountDetail(request,response);
                break;

        }
    }

    private void showChangePassword(HttpServletRequest request, HttpServletResponse response) {
        String email = String.valueOf(request.getSession(false).getAttribute("email"));
        try {
            if (email == "null") {
                response.sendRedirect("/login");
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/account/change-password.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logOutAccount(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAccountDetail(HttpServletRequest request, HttpServletResponse response) {
        String email = String.valueOf(request.getSession(false).getAttribute("email"));
        User user = null;
        try {
            if(email == "null"){
                response.sendRedirect("/login");
            } else {
                user = userDAO.findUserDetail(email);
                request.setAttribute("user",user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/account/account.jsp");
                dispatcher.forward(request,response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response) {
        String email = String.valueOf(request.getSession(false).getAttribute("email"));
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        userDAO.changePassword(email,oldPassword,newPassword);
        try {
            response.sendRedirect("/account");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
