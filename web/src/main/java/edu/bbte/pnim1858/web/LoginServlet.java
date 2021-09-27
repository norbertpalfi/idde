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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    UsedCarDao usedCarDao;

    @Override
    public void init() throws ServletException {
        super.init();
        usedCarDao = AbstractDaoFactory.getInstance().getUsedCarDao();
        LOGGER.info("login init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("auth", 0);
        session.invalidate();

        Template view = HandlebarsTemplateFactory.getTemplate("login");

        Collection<UsedCar> model = usedCarDao.selectAll();

        view.apply(model,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("user".equals(req.getParameter("user")) && "passwd".equals(req.getParameter("password"))) {
            LOGGER.info("logged in");
            HttpSession session = req.getSession();
            session.setAttribute("auth","ok");
        } else {
            LOGGER.info("bad try");
        }
        resp.sendRedirect("cars.hbs");
    }
}
