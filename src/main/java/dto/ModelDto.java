package dto;

import bean.Model;

public class ModelDto {
    private Long mId;
    private String mName;

    public ModelDto(Model model){
        this.mId = model.getmId();
        this.mName = model.getmName();
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
