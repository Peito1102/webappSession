package org.vasquez.apiservlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.vasquez.apiservlet.webapp.session.models.Carro;
import org.vasquez.apiservlet.webapp.session.models.ItemCarro;
import org.vasquez.apiservlet.webapp.session.models.Producto;
import org.vasquez.apiservlet.webapp.session.services.ProductoService;
import org.vasquez.apiservlet.webapp.session.services.ProductoServiceImpl;
import org.vasquez.apiservlet.webapp.session.services.ProductoServiceJDBCImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/carro/agregar")
public class AgregarCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJDBCImpl(conn);
        Optional<Producto> producto = service.findById(id);
        if (producto.isPresent()) {
            ItemCarro item = new ItemCarro(1,producto.get());
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            carro.addItemCarro(item);
        }
        resp.sendRedirect(req.getContextPath() + "/carro/ver");
    }
}
