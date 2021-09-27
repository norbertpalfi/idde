package edu.bbte.pnim1858.web;

import edu.bbte.quiz.backend.dao.AbstractDaoFactory;
import edu.bbte.quiz.backend.dao.UsedCarDao;
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

@WebServlet("/cars")
public class CarServlet extends HttpServlet {
    static final Logger LOGGER = LoggerFactory.getLogger(CarServlet.class);
    UsedCarDao usedCarDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usedCarDao = AbstractDaoFactory.getInstance().getUsedCarDao();
        LOGGER.info("created, init");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        String reqId = req.getParameter("id");

        if (reqId == null) {
            for (UsedCar usedCar : usedCarDao.selectAll()) {
                printWriter.println(usedCar);
            }
        } else {
            try {
                int id = Integer.parseInt(reqId);
                UsedCar usedCar = usedCarDao.select(id);
                if (usedCar == null) {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    printWriter.println(usedCar);
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        try {
            Integer kilometers = Integer.parseInt(req.getParameter("kilometers"));
            Integer horsePower = Integer.parseInt(req.getParameter("horsePower"));
            String carModel = req.getParameter("carModel");
            Integer startDate = Integer.parseInt(req.getParameter("startDate"));
            if (carModel != null) {
                usedCarDao.create(new UsedCar(kilometers, horsePower, carModel, startDate));
                resp.sendRedirect("cars.hbs");
                return;
            }
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            printWriter.println("Please add all following fields: kilometers, horsePower, ownerId, "
                    + "carModel, startDate.");
        } catch (NumberFormatException e) {
            LOGGER.info("NFE");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            printWriter.println("Please fill out all fields with correct data type.");
        }

    }
}
