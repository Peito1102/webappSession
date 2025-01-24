package org.vasquez.apiservlet.webapp.cookies.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vasquez.apiservlet.webapp.cookies.services.LoginService;
import org.vasquez.apiservlet.webapp.cookies.services.LoginServiceCookieImpl;
import org.vasquez.apiservlet.webapp.cookies.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

        if (cookieOptional.isPresent()) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("     <head>");
                out.println("         <meta charset=\"UTF-8\">");
                out.println("         <title>Saludo</title>");
                out.println("     </head>");
                out.println("     <body>");
                out.println("         <h1>Login Correcto!</h1>");
                out.println("         <h3>Hola " + cookieOptional.get() + " has iniciado sesión anteriormente!</h3>");
                out.println("         <p><a href='"+ req.getContextPath() +"/index.html'>Volver</a></p>");
                out.println("         <p><a href='"+ req.getContextPath() +"/logout'>Cerrar Sesión</a></p>");
                out.println("     </body>");
                out.println("</html>");

            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            req.getSession().setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }

    }
}
