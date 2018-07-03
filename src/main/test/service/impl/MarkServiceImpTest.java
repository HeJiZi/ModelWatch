package service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.MarkService;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})

public class MarkServiceImpTest {

    @Autowired
    MarkService markService;
    @Test
    public void addMark() {
        markService.addMark(2,1);
    }

    @Test
    public void deleteMark() {
        markService.deleteMark(2,1);
    }
}