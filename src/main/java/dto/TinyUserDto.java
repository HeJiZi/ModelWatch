package dto;

import bean.User;

public class TinyUserDto {
    private int uId;
    private String uUsername;

    public TinyUserDto(User user){
        this.uId = user.getuId();
        this.uUsername = user.getuUsername();
    }

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
}
