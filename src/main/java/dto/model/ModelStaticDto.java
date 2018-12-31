package dto.model;

import bean.Model;

public class ModelStaticDto {
    private String mName;
    private int comNum;
    private int markNum;

    public ModelStaticDto(Model model) {
        mName = model.getmName();
        comNum  = model.getComNum();
        markNum = model.getMarkNum();
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getComNum() {
        return comNum;
    }

    public void setComNum(int comNum) {
        this.comNum = comNum;
    }

    public int getMarkNum() {
        return markNum;
    }

    public void setMarkNum(int markNum) {
        this.markNum = markNum;
    }
}
