package dto.project;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.security.Timestamp;

public class ProjectPhotoDto {
    private Long pId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp pCreateTime;
    private String pName;
    private String pPreview;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Timestamp getpCreateTime() {
        return pCreateTime;
    }

    public void setpCreateTime(Timestamp pCreateTime) {
        this.pCreateTime = pCreateTime;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPreview() {
        return pPreview;
    }

    public void setpPreview(String pPreview) {
        this.pPreview = pPreview;
    }
}
