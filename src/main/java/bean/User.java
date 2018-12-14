package bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;


public class User {
    private int uId;
    private String uUsername;
    private String uAvater;
    private String uPassword;
    private String uEmail;
    private String uSex;
    private int uState;
    private int invState;
    private int uModelNum;
    private int uProjectNum;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date uBirthday;
    private String uSignature;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp uCreateTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp invTime;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    public String getuAvater() {
        return uAvater;
    }

    public void setuAvater(String uAvater) {
        this.uAvater = uAvater;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public Date getuBirthday() {
        return uBirthday;
    }

    public void setuBirthday(Date uBirthday) {
        this.uBirthday = uBirthday;
    }

    public String getuSignature() {
        return uSignature;
    }

    public void setuSignature(String uSignature) {
        this.uSignature = uSignature;
    }

    public Timestamp getuCreateTime() {
        return uCreateTime;
    }

    public void setuCreateTime(Timestamp uCreateTime) {
        this.uCreateTime = uCreateTime;
    }

    public int getuModelNum() {
        return uModelNum;
    }

    public void setuModelNum(int uModelNum) {
        this.uModelNum = uModelNum;
    }

    public int getuProjectNum() {
        return uProjectNum;
    }

    public void setuProjectNum(int uProjectNum) {
        this.uProjectNum = uProjectNum;
    }

    public int getuState() {
        return uState;
    }

    public void setuState(int uState) {
        this.uState = uState;
    }

    public Timestamp getInvTime() {
        return invTime;
    }

    public void setInvTime(Timestamp invTime) {
        this.invTime = invTime;
    }

    public int getInvState() {
        return invState;
    }

    public void setInvState(int invState) {
        this.invState = invState;
    }

    //    private int inId;
//    public void setInId(int inId){
//        this.inId=inId;
//    }
//    public int getInId(){
//        return inId;
//    }


    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uUsername='" + uUsername + '\'' +
                ", uAvater='" + uAvater + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uSex='" + uSex + '\'' +
                ", uState=" + uState +
                ", uBirthday=" + uBirthday +
                ", uSignature='" + uSignature + '\'' +
                ", uCreateTime=" + uCreateTime +
                ", invTime=" + invTime +
                ", invState=" + invState +
                ", uModelNum=" + uModelNum +
                ", uProjectNum=" + uProjectNum +
                '}'+'\n';
    }
}
