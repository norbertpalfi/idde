package edu.bbte.pnim1858.springbackend;

import edu.bbte.pnim1858.springbackend.model.UsedCar;
import edu.bbte.pnim1858.springbackend.repo.UsedCarSpringDataDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class Bean1 {
    @Autowired
    private UsedCarSpringDataDao usedCarDao;

    public void pelda() {

        log.info("frontend message");
        usedCarDao.save(new UsedCar(10, 100, "1st", 1,1999));
        usedCarDao.save(new UsedCar(9, 121, "2nd", 2,2013));
        log.info("findAll: {}", usedCarDao.findAll());
        log.info("findAllByName: {}", usedCarDao.findAllByName("1st"));
    }

}
