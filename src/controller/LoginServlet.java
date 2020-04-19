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

@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action  == null){
            action = "";
        }
        switch (action){
            case "register_user":
                registerUser(request,response);
                break;
            default:
                login(request,response);
                break;
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action  == null){
            action = "";
        }
        switch (action){
            case "register_user":
                showRegisterForm(request,response);
                break;
            case "forgot_password":
                showForgotPasswordForm(request,response);
                break;
            default:
                showLoginForm(request,response);
                break;
        }
    }

    private void showForgotPasswordForm(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login/register-user.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login/login.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = null;
        user = userDAO.findUserLogin(email,password);

        RequestDispatcher dispatcher = null;
        try {
            if (user != null) {
                String name = user.getName();
                int id = user.getId();
                HttpSession session = request.getSession();
                session.setAttribute("user_id", id);
                session.setAttribute("username", name);
                session.setAttribute("email", email);
                session.setAttribute("user", user);
                response.sendRedirect("/index");

            } else {
                dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String name = request.getParameter("username");
        String address = request.getParameter("address");
        String phone_number = request.getParameter("phone_number");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        try {
            String msg = null;
            if (!password1.equals(password2)) {
                msg = "invalid password";
            } else if(userDAO.validateEmailUser(email)){
                msg = "Email already existed";
            } else {
                userDAO.registerNewUser(name, email, address, phone_number, password1);
                msg = "Register user success for user email " + email;
            }
            request.setAttribute("msg", msg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login/register-user.jsp");
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
