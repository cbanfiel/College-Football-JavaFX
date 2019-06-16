package sample;

public class Game {

    private int teamScore;
    private int oppTeamScore;
    private boolean home;
    private Team oppTeam;

    public Game(int teamScore, int oppTeamScore, boolean home, Team oppTeam) {
        this.teamScore = teamScore;
        this.oppTeamScore = oppTeamScore;
        this.home = home;
        this.oppTeam = null;
        this.oppTeam = oppTeam;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public int getOppTeamScore() {
        return oppTeamScore;
    }

    public boolean isHome() {
        return home;
    }

    public String homeOrAway(){
        if(home){
            return "Vs.";
        }else{
            return "@";
        }
    }

    public Team getOppTeam() {
        return oppTeam;
    }

    public String isOppTeamRanked(){
        if(oppTeam.getRank()<=25){
            return "#" + oppTeam.getRank();
        }else{
            return "";
        }
    }

    @Override
    public String toString() {
        if(teamScore>oppTeamScore){
            return "W " + homeOrAway() + " " + isOppTeamRanked() + " " + oppTeam.getName() + " " + teamScore + "-" + oppTeamScore;
        }else{
            return "L " + homeOrAway() + " " + isOppTeamRanked() + " " + oppTeam.getName() + " " + teamScore + "-" + oppTeamScore;
        }
    }
}
