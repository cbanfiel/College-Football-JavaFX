package sample;

public class Coach {
    String name;
    int passpercentage;
    int aggresiveness;
    int defType;
    //0 4-3, 1,3-4
    int offType;
    //0 balanced, 1 spread, 2 run n gun


    public Coach(){
        this.name = "Coach";
        this.passpercentage=50;
        this.aggresiveness=50;
        this.defType=0;
        this.offType=0;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasspercentage(int passpercentage) {
        this.passpercentage = passpercentage;
    }

    public void setAggresiveness(int aggresiveness) {
        this.aggresiveness = aggresiveness;
    }

    public void setDefType(int defType) {
        this.defType = defType;
    }

    public void setOffType(int offType) {
        this.offType = offType;
    }

    public String getName() {
        return name;
    }

    public int getPasspercentage() {
        return passpercentage;
    }

    public int getAggresiveness() {
        return aggresiveness;
    }

    public int getDefType() {
        return defType;
    }

    public int getOffType() {
        return offType;
    }
}
