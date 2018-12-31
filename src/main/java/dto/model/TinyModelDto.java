package dto.model;

import bean.Model;

public class TinyModelDto {
    private Long mId;
    private String mName;

    public TinyModelDto(Model model){
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
