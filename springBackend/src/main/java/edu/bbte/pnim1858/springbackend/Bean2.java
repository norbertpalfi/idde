package edu.bbte.pnim1858.springbackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class Bean2 {
    @Autowired
    private Bean1 bean1;

    @PostConstruct
    public void something() {
        log.info("bean2");
        bean1.pelda();
    }
}
