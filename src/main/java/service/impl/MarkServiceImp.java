package service.impl;

import dao.MarkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.MarkService;

@Service
public class MarkServiceImp implements MarkService {

    @Autowired
    MarkDao markDao;

    public int addMark(int mid, int uid) {
        return markDao.addMark(mid,uid);
    }

    public int deleteMark(int mid, int uid) {
        return markDao.deleteMark(mid,uid);
    }

    public int selectMark(int mid, int uid) {
        return markDao.selectMark(mid,uid);
    }
}
