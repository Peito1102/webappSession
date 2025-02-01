package org.vasquez.apiservlet.webapp.session.services;

import org.vasquez.apiservlet.webapp.session.models.Producto;
import org.vasquez.apiservlet.webapp.session.repositories.ProductoRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJDBCImpl implements ProductoService{

    private ProductoRepositoryImpl repositoryJDBC;

    public ProductoServiceJDBCImpl(Connection connection) {
        this.repositoryJDBC = new ProductoRepositoryImpl(connection);
    }

    @Override
    public List<Producto> listar() {
        try {
            return repositoryJDBC.listar();
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> findById(Long id) {
        try {
            return Optional.ofNullable(repositoryJDBC.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJDBCException(throwables.getMessage(),throwables.getCause());
        }

    }
}
