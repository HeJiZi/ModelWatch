package service;

public interface DeleteService {
    boolean DeleteLog(Long lId);
    int deleteInvitation(long pId,int uId);
    int deleteWait(Long pId,String uName);

}
