package service;

public interface DeleteService {
    boolean deleteLog(Long lId);
    int deleteInvitation(long pId,int uId);
    int deleteWait(Long pId,String uName);

    boolean deleteSubscribe(long pId);

    boolean deleteProject(String password,long pId);
}
