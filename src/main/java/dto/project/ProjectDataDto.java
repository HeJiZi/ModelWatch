package dto.project;


import bean.Project;

public class ProjectDataDto {
    private Long pId;
    private String pName;
    private String pContext;
    private String pPreview;

    public ProjectDataDto(Project project) {
        pId = project.getpId();
        pName = project.getpName();
        pContext = project.getpContext();
        pPreview =project.getpPreview();
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
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
}
