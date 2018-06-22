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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date uBirthday;
    private String uSignature;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp uCreateTime;

    private int uModelNum;
    private int uProjectNum;

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

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", uUsername='" + uUsername + '\'' +
                ", uAvater='" + uAvater + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uEmail='" + uEmail + '\'' +
                ", uSex='" + uSex + '\'' +
                ", uBirthday=" + uBirthday +
                ", uSignature='" + uSignature + '\'' +
                ", uCreateTime=" + uCreateTime +
                '}';
    }
}
