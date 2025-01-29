package org.vasquez.apiservlet.webapp.session.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.vasquez.apiservlet.webapp.session.services.LoginService;
import org.vasquez.apiservlet.webapp.session.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebFilter("/carro/*")
public class LoginFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername((HttpServletRequest) request);
        if (username.isPresent()) {
            filterChain.doFilter(request,response);
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Lo sentimos no estas autorizado para ingresar a esta página!");
        }

    }
}
