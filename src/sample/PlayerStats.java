package sample;

public class PlayerStats {
    int passingYards = 0;
    int completions = 0;
    int passAttempts = 0;
    int passingTouchdowns = 0;
    int rushingTouchdowns=0;
    int receivingTouchdowns =0;
    int interceptionsThrown = 0;
    int fumbles = 0;
    int kicksMade = 0;
    int kicksAttempted = 0;
    int tackles = 0;
    int sacks = 0;
    int interceptions = 0;
    int fumblesRecovered = 0;
    int kicksBlocked = 0;
    int returnYards = 0;
    int returnTouchdowns = 0;
    int catches = 0;
    int rushAttempts = 0;
    int rushingYards = 0;
    int recieivingYards = 0;
    String averageRushingYards="";
    String completionPercentage="";

    public void setRushingTouchdowns(int rushingTouchdowns) {
        this.rushingTouchdowns += rushingTouchdowns;
    }

    public void setReceivingTouchdowns(int receivingTouchdowns) {
        this.receivingTouchdowns += receivingTouchdowns;
    }

    public int getRushingTouchdowns() {
        return rushingTouchdowns;
    }

    public int getReceivingTouchdowns() {
        return receivingTouchdowns;
    }

    public int getPassingYards() {
        return passingYards;
    }

    public int getCompletions() {
        return completions;
    }

    public int getPassAttempts() {
        return passAttempts;
    }

    public int getPassingTouchdowns() {
        return passingTouchdowns;
    }

    public int getInterceptionsThrown() {
        return interceptionsThrown;
    }

    public int getFumbles() {
        return fumbles;
    }

    public int getKicksMade() {
        return kicksMade;
    }

    public int getKicksAttempted() {
        return kicksAttempted;
    }

    public int getTackles() {
        return tackles;
    }

    public int getSacks() {
        return sacks;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public int getFumblesRecovered() {
        return fumblesRecovered;
    }

    public int getKicksBlocked() {
        return kicksBlocked;
    }

    public int getReturnYards() {
        return returnYards;
    }

    public int getReturnTouchdowns() {
        return returnTouchdowns;
    }

    public int getCatches() {
        return catches;
    }

    public int getRushAttempts() {
        return rushAttempts;
    }

    public int getRushingYards() {
        return rushingYards;
    }

    public int getRecieivingYards() {
        return recieivingYards;
    }

    public void setPassingYards(int passingYards) {
        this.passingYards += passingYards;
    }

    public void setRushAttempts(int rushAttempts) {
        this.rushAttempts += rushAttempts;
    }

    public void setRushingYards(int rushingYards) {
        this.rushingYards += rushingYards;
    }

    public void setRecieivingYards(int recieivingYards) {
        this.recieivingYards += recieivingYards;
    }

    public void setCatches(int catches) {
        this.catches += catches;
    }


    public void setCompletions(int completions) {
        this.completions += completions;
    }

    public void setPassAttempts(int passAttempts) {
        this.passAttempts += passAttempts;
    }

    public void setPassingTouchdowns(int passingTouchdowns) {
        this.passingTouchdowns += passingTouchdowns;
    }

    public void setInterceptionsThrown(int interceptionsThrown) {
        this.interceptionsThrown += interceptionsThrown;
    }

    public void setFumbles(int fumbles) {
        this.fumbles += fumbles;
    }

    public void setKicksMade(int kicksMade) {
        this.kicksMade += kicksMade;
    }

    public void setKicksAttempted(int kicksAttempted) {
        this.kicksAttempted += kicksAttempted;
    }

    public void setTackles(int tackles) {
        this.tackles += tackles;
    }

    public void setSacks(int sacks) {
        this.sacks += sacks;
    }

    public void setInterceptions(int interceptions) {
        this.interceptions += interceptions;
    }

    public void setFumblesRecovered(int fumblesRecovered) {
        this.fumblesRecovered += fumblesRecovered;
    }

    public void setKicksBlocked(int kicksBlocked) {
        this.kicksBlocked += kicksBlocked;
    }

    public void setReturnYards(int returnYards) {
        this.returnYards += returnYards;
    }

    public void setReturnTouchdowns(int returnTouchdowns) {
        this.returnTouchdowns += returnTouchdowns;
    }


    public String getCompletionPercentage() {
        if (passAttempts > 0) {
            double comp = completions;
            double att = passAttempts;
            completionPercentage = String.format("%.1f",(comp/att) * 100);
            return completionPercentage;
        } else {
            return "0";
        }
    }

    public String getAverageRushingYards(){
        if(rushAttempts>0){
            double att = rushAttempts;
            double yds = rushingYards;
            averageRushingYards = String.format("%.1f",(yds/att));
            return averageRushingYards;
        }
        return "0";
    }


}
