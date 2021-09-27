package edu.bbte.quiz.frontend;

import edu.bbte.quiz.backend.dao.AbstractDaoFactory;
import edu.bbte.quiz.backend.dao.UsedCarDao;
import edu.bbte.quiz.backend.model.UsedCar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsedCarUI {
    static final Logger LOGGER = LoggerFactory.getLogger(UsedCarUI.class);

    public static void main(String[] args) {

        LOGGER.info("frontend message");
        UsedCarDao usedCarDao = AbstractDaoFactory.getInstance().getUsedCarDao();
        usedCarDao.create(new UsedCar(10, 100, "1st", 1999));
        usedCarDao.create(new UsedCar(9, 121, "2nd", 1980));
        LOGGER.info("{}", usedCarDao.selectAll());
        //quizDao.delete(1);
        LOGGER.info("{}", usedCarDao.selectAll());
        //UsedCar a = new UsedCar(1,10,"something", "else", 2020);
        //quizDao.update(0,a);
        LOGGER.info("{}", usedCarDao.selectAll());

    }
}

