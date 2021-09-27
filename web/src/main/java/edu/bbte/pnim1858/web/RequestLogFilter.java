package edu.bbte.pnim1858.web;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter("/cars.hbs")
public class RequestLogFilter extends HttpFilter {
    static final Logger LOGGER = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpSession session = req.getSession();

        if (session == null || !"ok".equals(session.getAttribute("auth"))) {
            res.sendRedirect("./login");
            LOGGER.info("not ok");
        } else {
            chain.doFilter(req,res);
            LOGGER.info("Allowed");
        }
        LOGGER.info("method: {}, url: {}, status: {}",req.getMethod(),req.getRequestURI(),res.getStatus());

    }

}
