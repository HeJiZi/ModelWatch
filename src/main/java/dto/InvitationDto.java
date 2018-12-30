package dto;

import bean.Invitation;
import entity.Page;

import java.util.List;


public class InvitationDto{
    public String uAvater;
    public String uUsername;
    public String mAvater;
    public String pName;

    public InvitationDto(String uAvater,String uUsername,String mAvater,String pName){
        this.uAvater=uAvater;
        this.uUsername=uUsername;
        this.mAvater=mAvater;
        this.pName=pName;
    }

    public String getuAvater() {
        return uAvater;
    }

    public void setuAvater(String uAvater) {
        this.uAvater = uAvater;
    }

    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    public String getmAvater() {
        return mAvater;
    }

    public void setmAvater(String mAvater) {
        this.mAvater = mAvater;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }


}