package gr.aueb.cf.eudapp.controller;

import gr.aueb.cf.eudapp.authentication.AuthenticationProvider;
import gr.aueb.cf.eudapp.dao.exception.UserDAOException;
import gr.aueb.cf.eudapp.dto.UserLoginDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        String isError = request.getParameter("isError");
        request.setAttribute("isError", isError == null ? "false": "true");
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Data Binding
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean principleIsAuthenticated = false;

        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);

        try {
            principleIsAuthenticated = AuthenticationProvider.authenticate(userLoginDTO);

            if (principleIsAuthenticated) {
                HttpSession session = request.getSession(false);
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/Employees");
            } else {
                response.sendRedirect(request.getContextPath() + "/login?isError=true");
            }

        } catch (UserDAOException e) {
            response.sendRedirect(request.getContextPath() + "/login?isError=true");
        }

    }

}
