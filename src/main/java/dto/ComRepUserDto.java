package dto;

import bean.User;

public class ComRepUserDto {

    private String uUsername;
    private String uAvater;

    public ComRepUserDto(User user) {
        this.uUsername = user.getuUsername();
        this.uAvater = user.getuAvater();
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



}
