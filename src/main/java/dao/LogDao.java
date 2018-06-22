package dao;

import bean.Log;

public interface LogDao {
    /**
     * 添加一条记录
     * @param log
     * @return 添加成功返回1，添加失败返回0
     */
    int addLog(Log log);
}
