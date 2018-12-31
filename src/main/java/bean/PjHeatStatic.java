package bean;

public class PjHeatStatic {
    private String pDay;
    private int markNum;
    private int subNum;

    public String getpDay() {
        return pDay;
    }

    public void setpDay(String pDay) {
        this.pDay = pDay;
    }

    public int getMarkNum() {
        return markNum;
    }

    public void setMarkNum(int markNum) {
        this.markNum = markNum;
    }

    public int getSubNum() {
        return subNum;
    }

    public void setSubNum(int subNum) {
        this.subNum = subNum;
    }

    @Override
    public String toString() {
        return "PjHeatStatic{" +
                "pDay='" + pDay + '\'' +
                ", markNum=" + markNum +
                ", subNum=" + subNum +
                '}'+'\n';
    }
}
