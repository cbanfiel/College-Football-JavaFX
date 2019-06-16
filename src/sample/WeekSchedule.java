package sample;

public class WeekSchedule {
    int week;
    Team opp;
    boolean home;
    int yourScore;
    int oppScore;
    int yourRank;
    int oppRank;
    boolean wasPlayed;

    public boolean isWasPlayed() {
        return wasPlayed;
    }

    public void setWasPlayed(boolean wasPlayed) {
        this.wasPlayed = wasPlayed;
    }

    public int getYourScore() {
        return yourScore;
    }

    public void setYourScore(int yourScore) {
        this.yourScore = yourScore;
    }

    public int getOppScore() {
        return oppScore;
    }

    public void setOppScore(int oppScore) {
        this.oppScore = oppScore;
    }

    public WeekSchedule() {
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Team getOpp() {
        return opp;
    }

    public void setOpp(Team opp) {
        this.opp = opp;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public String homeString(boolean home){
        if(home){
            return "Vs.";
        }else{
            return "@";
        }
    }

    public String winString(){
        if(yourScore>oppScore){
            return "W";
        }else{
            return "L";
        }
    }

    public int getYourRank() {
        return yourRank;
    }

    public void setYourRank(int yourRank) {
        this.yourRank = yourRank;
    }

    public int getOppRank() {
        return oppRank;
    }

    public void setOppRank(int oppRank) {
        this.oppRank = oppRank;
    }

    @Override
    public String toString() {



        if (wasPlayed) {
            if(week==12){
                return "Bowl: " + " " + winString() + " " + homeString(home) + " " + opp.scheduleToString() + " " + yourScore +"-" + oppScore;
            }
            return "Week: " + (week+1) + " " + winString() + " " + homeString(home) + " " + opp.scheduleToString() + " " + yourScore +"-" + oppScore;
        } else {
            return "Week: " + (week+1) + " " + homeString(home) + " " + opp.scheduleToString();

        }
    }
}
