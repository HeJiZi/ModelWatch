package bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Administrator {
    private int aId;
    private String aUsername;
    private String aPassword;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp aCreateTime;

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getaUsername() {
        return aUsername;
    }

    public void setaUsername(String aUsername) {
        this.aUsername = aUsername;
    }

    public String getaPassword() {
        return aPassword;
    }

    public void setaPassword(String aPassword) {
        this.aPassword = aPassword;
    }

    public Timestamp getaCreateTime() {
        return aCreateTime;
    }

    public void setaCreateTime(Timestamp aCreateTime) {
        this.aCreateTime = aCreateTime;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "aId=" + aId +
                ", aUsername='" + aUsername + '\'' +
                ", aPassword='" + aPassword + '\'' +
                ", aCreateTime=" + aCreateTime +
                '}';
    }
}
