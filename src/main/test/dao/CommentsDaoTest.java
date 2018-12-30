package dao;

import entity.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(value = SpringJUnit4ClassRunner.class)

@ContextConfiguration("classpath:spring/spring-dao.xml")
public class CommentsDaoTest {

    @Autowired
    CommentsDao commentsDao;

    @Test
    public void addComment() {
    }

    @Test
    public void deleteComment() {
    }

    @Test
    public void updateCommentState() {
    }

    @Test
    public void selectComments() {
    }

    @Test
    public void selectCommentsByMId() {
        Page page =new Page(15);
        page.setCurrentPageNum(1);
        System.out.println(commentsDao.SelectCommentsByMIdPage(1,page));
    }

    @Test
    public void selectCommentsNum() {
    }
}