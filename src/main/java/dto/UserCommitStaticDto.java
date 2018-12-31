package dto;

import bean.User;

public class UserCommitStaticDto {
    private String uUsername;
    private int submitNum;

    public UserCommitStaticDto(User user) {
        uUsername = user.getuUsername();
        submitNum = user.getSubmitNum();
    }

    public String getuUsername() {
        return uUsername;
    }

    public void setuUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    public int getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(int submitNum) {
        this.submitNum = submitNum;
    }
}
