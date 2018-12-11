package dao;

import bean.Log;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface LogDao {
    /**
     * 添加一条记录
     * @param log
     * @return 添加成功返回1，添加失败返回0
     */
    int addLog(Log log);

    /**
     * 添加一条记录
     * @param lId
     * @return 添加成功返回1，添加失败返回0
     */
    int deleteLog(Long lId);

    int updateLogContext(@Param("lId")Long lId,
                         @Param("lContext") String lContext);
    /**
     * 获取日志列表
     * @param pid
     * @return
     */
    List<Log> getLogByPid(long pid);

    List<Log> filterLog(@Param("beginTime")Timestamp beginTime,
                        @Param("endTime")Timestamp endTime,
                        @Param("uUserName")String uUserName,
                        @Param("mName")String mName);



}
