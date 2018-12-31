package dto.project;

import bean.Project;

public class TinyProjectDto {
    private Long pId;
    private String pName;

    public TinyProjectDto(Project project){
        this.pId = project.getpId();
        this.pName = project.getpName();
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
}
