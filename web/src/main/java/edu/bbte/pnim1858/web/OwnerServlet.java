package edu.bbte.pnim1858.web;

import edu.bbte.quiz.backend.dao.AbstractDaoFactory;
import edu.bbte.quiz.backend.dao.OwnerDao;
import edu.bbte.quiz.backend.dao.UsedCarDao;
import edu.bbte.quiz.backend.dao.jdbc.DataSourceFactory;
import edu.bbte.quiz.backend.dao.jdbc.OwnerJdbcDao;
import edu.bbte.quiz.backend.model.UsedCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/owners")
public class OwnerServlet extends HttpServlet {
    static final Logger LOGGER = LoggerFactory.getLogger(CarServlet.class);
    OwnerDao ownerDao;
    OwnerJdbcDao ownerJdbcDao;
    private boolean logQueries = DataSourceFactory.getLogQueries();
    private boolean logUpdates = DataSourceFactory.getLogUpdates();


    @Override
    public void init() throws ServletException {
        super.init();
        ownerDao = AbstractDaoFactory.getInstance().getOwnerDao();
        LOGGER.info("created, init");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        if(!logQueries && !logUpdates) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if(logQueries) {
            printWriter.println("Number of reads: " + ownerJdbcDao.getNrOfReads());
        }
        if(logUpdates) {
            printWriter.println("Number of writes: " + ownerJdbcDao.getNrOfWrites());
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
