package service.impl;

import dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteServiceImpl implements service.DeleteService {
    @Autowired
    LogDao logDao;
    public boolean DeleteLog(Long lId) {
        return (logDao.deleteLog(lId)==1);
    }
}
