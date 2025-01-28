package org.vasquez.apiservlet.webapp.session.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.vasquez.apiservlet.webapp.session.models.Carro;

@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Iniciando la aplicación.");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje","algun valor global en la app");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destruyendo la aplicación.");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("destruyendo el request!");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando el request!");
        sre.getServletRequest().setAttribute("mensaje","algun valor global de la app!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("creando la sesión http");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro",carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("destruyendo la sesión http");
    }
}
