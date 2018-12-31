package dto.model;

import bean.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ModelPhotoDto {
    private Long mId;
    private String mName;
    private String mPreview;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp mCreateTime;

    public ModelPhotoDto(){

    }
    public ModelPhotoDto(Model model){
        mId = model.getmId();
        mName = model.getmName();
        mPreview = model.getmPreview();
        mCreateTime = model.getmCreateTime();
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

    public String getmPreview() {
        return mPreview;
    }

    public void setmPreview(String mPreview) {
        this.mPreview = mPreview;
    }

    public Timestamp getmCreateTime() {
        return mCreateTime;
    }

    public void setmCreateTime(Timestamp mCreateTime) {
        this.mCreateTime = mCreateTime;
    }
}
