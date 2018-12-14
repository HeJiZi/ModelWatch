package dto;

import entity.Page;

import java.util.List;

public class ListObject {
    private List list;
    private Page page;

    public ListObject(List list, Page page) {
        this.list = list;
        this.page = page;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
