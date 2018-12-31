package dto.project;

import bean.Project;
import com.fasterxml.jackson.annotation.JsonFormat;
import dto.model.ModelPhotoDto;
import dto.TinyUserDto;
import util.DtoListUtil;

import java.sql.Timestamp;
import java.util.List;

public class PjOverviewDto {
    private long pId;
    private String pName;
    private String pContext;
    private String pPreview;

    private int pSubsNum;
    private boolean pSubed;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp pCreateTime;

    private TinyUserDto user;
    private List<ModelPhotoDto> models;

    public PjOverviewDto() {
    }

    public PjOverviewDto(Project project) {
        pId = project.getpId();
        pName = project.getpName();
        pContext = project.getpContext();
        pPreview =project.getpPreview();
        pSubsNum = project.getpSubsNum();
        pSubed = project.ispSubed();
        pCreateTime = project.getpCreateTime();
        user = project.getUser() == null?null:new TinyUserDto(project.getUser());
        models = project.getModels() ==null?null:DtoListUtil.transelateList(project.getModels(),ModelPhotoDto.class);
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpContext() {
        return pContext;
    }

    public void setpContext(String pContext) {
        this.pContext = pContext;
    }

    public String getpPreview() {
        return pPreview;
    }

    public void setpPreview(String pPreview) {
        this.pPreview = pPreview;
    }

    public int getpSubsNum() {
        return pSubsNum;
    }

    public void setpSubsNum(int pSubsNum) {
        this.pSubsNum = pSubsNum;
    }

    public boolean ispSubed() {
        return pSubed;
    }

    public void setpSubed(boolean pSubed) {
        this.pSubed = pSubed;
    }

    public TinyUserDto getUser() {
        return user;
    }

    public void setUser(TinyUserDto user) {
        this.user = user;
    }

    public List<ModelPhotoDto> getModels() {
        return models;
    }

    public void setModels(List<ModelPhotoDto> models) {
        this.models = models;
    }

    public Timestamp getpCreateTime() {
        return pCreateTime;
    }

    public void setpCreateTime(Timestamp pCreateTime) {
        this.pCreateTime = pCreateTime;
    }
}
