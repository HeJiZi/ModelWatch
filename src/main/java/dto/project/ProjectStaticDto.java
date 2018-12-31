package dto.project;

import bean.LogStatic;
import bean.PjHeatStatic;
import bean.Project;
import dto.UserCommitStaticDto;
import dto.model.ModelPhotoDto;
import dto.model.ModelStaticDto;
import util.DtoListUtil;

import java.util.List;

public class ProjectStaticDto {
    private ModelPhotoDto hotestModel;
    private List<ModelStaticDto> modelStatic;
    private List<PjHeatStatic> pjHeatStatic;
    private List<UserCommitStaticDto> users;
    private List<LogStatic> logStatic;

    public ProjectStaticDto(Project project) {
        if(project.getModels().size()>0){
            hotestModel = new ModelPhotoDto(project.getModels().get(0));
            modelStatic = DtoListUtil.transelateList(project.getModels(),ModelStaticDto.class);
        }
        pjHeatStatic = project.getPjHeatStatic();
        logStatic = project.getLogStatic();
        users = DtoListUtil.transelateList(project.getCollaborators(),UserCommitStaticDto.class);
    }

    public ModelPhotoDto getHotestModel() {
        return hotestModel;
    }

    public void setHotestModel(ModelPhotoDto hotestModel) {
        this.hotestModel = hotestModel;
    }

    public List<ModelStaticDto> getModelStatic() {
        return modelStatic;
    }

    public void setModelStatic(List<ModelStaticDto> modelStatic) {
        this.modelStatic = modelStatic;
    }

    public List<PjHeatStatic> getPjHeatStatic() {
        return pjHeatStatic;
    }

    public void setPjHeatStatic(List<PjHeatStatic> pjHeatStatic) {
        this.pjHeatStatic = pjHeatStatic;
    }

    public List<UserCommitStaticDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserCommitStaticDto> users) {
        this.users = users;
    }

    public List<LogStatic> getLogStatic() {
        return logStatic;
    }

    public void setLogStatic(List<LogStatic> logStatic) {
        this.logStatic = logStatic;
    }
}
