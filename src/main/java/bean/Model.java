package bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Model {
    private Long mId;
    private String mData;
    private String mPreview;
    private String mName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp mCreateTime;

    private Project project;
    private Class aClass;

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getmData() {
        return mData;
    }

    public void setmData(String mData) {
        this.mData = mData;
    }

    public String getmPreview() {
        return mPreview;
    }

    public void setmPreview(String mPreview) {
        this.mPreview = mPreview;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Timestamp getmCreateTime() {
        return mCreateTime;
    }

    public void setmCreateTime(Timestamp mCreateTime) {
        this.mCreateTime = mCreateTime;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
