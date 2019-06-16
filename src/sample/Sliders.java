package sample;

public class Sliders {
    int seasonLength=12;
    int touchdownSlider=3400;
    int driveResultSlider=15000;
    int driveTime=3;

    public int getDriveResultSlider() {
        return driveResultSlider;
    }

    public void setDriveResultSlider(int driveResultSlider) {
        System.out.println("Current Slider: " + driveResultSlider);
        this.driveResultSlider = driveResultSlider;
    }

    public int getDriveTime() {
        return driveTime;
    }

    public void setDriveTime(int driveTime) {
        System.out.println("Current Slider: " + driveTime);
        this.driveTime = driveTime;
    }

    public int getSeasonLength() {
        return seasonLength;
    }

    public int getTouchdownSlider() {
        return touchdownSlider;
    }


    public void setSeasonLength(int seasonLength) {
        System.out.println("Current Slider: " + seasonLength);
        this.seasonLength = seasonLength;
    }

    public void setTouchdownSlider(int touchdownSlider) {
        System.out.println("Current Slider: " + touchdownSlider);
        this.touchdownSlider = touchdownSlider;
    }

}
