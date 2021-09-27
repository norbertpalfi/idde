package edu.bbte.pnim1858.web;

import com.github.jknack.handlebars.Template;
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
import java.util.Collection;

@WebServlet("/cars.hbs")
public class CarControllerServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarControllerServlet.class);
    UsedCarDao usedCarDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usedCarDao = AbstractDaoFactory.getInstance().getUsedCarDao();
        LOGGER.info("created, init carservlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Collection<UsedCar> model;

        final String search = req.getParameter("search");
        if (search == null || search.isEmpty()) {
            model = usedCarDao.selectAll();
        } else {
            model = usedCarDao.selectByName(search);
        }

        Template view = HandlebarsTemplateFactory.getTemplate("cars");

        view.apply(model, resp.getWriter());
    }
}