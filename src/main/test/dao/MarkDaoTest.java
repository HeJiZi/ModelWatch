package dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(value =SpringJUnit4ClassRunner.class)

@ContextConfiguration("classpath:spring/spring-dao.xml")
public class MarkDaoTest {

    @Autowired MarkDao markDao;

    @Test
    public void addMark() {
        System.out.println(markDao.addMark(2,1));
    }

    @Test
    public void deleteMark() {
        System.out.println(markDao.deleteMark(2,1));
    }

    @Test
    public void selectMark(){
        System.out.println(markDao.selectMark(1,2));
        System.out.println(markDao.selectMark(6,8));
    }
}