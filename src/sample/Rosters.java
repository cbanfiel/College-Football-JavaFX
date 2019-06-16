/*
THIS WOULD HAVE ALL OF THE PLAYERS IN ONE ARRAY LIST DETERMINED BY THERE LISTED TEAM
 */

package sample;

import java.util.ArrayList;

public class Rosters {

    private ArrayList<Player> roster = new ArrayList<Player>();

    public ArrayList<Player> getRoster() {
        return roster;
    }

    public void playerDatabase() {
        //GENERIC TEAM
        //OFFENSE
        Player QB = new Player("QB",60,"QB","GENERIC",10,"FR");
        Player RB = new Player("RB",60,"RB","GENERIC",10,"FR");
        Player WR = new Player("WR",60,"WR","GENERIC",10,"FR");
        Player TE = new Player("TE",60,"TE","GENERIC",10,"FR");
        Player OL = new Player("OL",60,"OL","GENERIC",10,"FR");
        //DEFENSE
        Player DL = new Player("DL",60,"DL","GENERIC",10,"FR");
        Player LB = new Player("DL",60,"LB","GENERIC",10,"FR");
        Player DB = new Player("DL",60,"DB","GENERIC",10,"FR");
        //SPECIAL TEAMS
        Player K = new Player("K",60,"K","GENERIC",10,"FR");



        //MICHIGAN
        Player sheaPatterson = new Player("Shea Patterson", 84, "QB", "Michigan", 2, "JR");
        roster.add(sheaPatterson);
        Player karanHigdon = new Player("Karan Higdon", 94, "RB", "Michigan", 23, "SR");
        roster.add(karanHigdon);
        Player donovanPeoplesJones = new Player("Donovan Peoples-Jones", 86, "WR", "Michigan", 81, "JR");
        roster.add(donovanPeoplesJones);
        Player jakeButt = new Player("Jake Butt", 99, "TE", "Michigan", 88, "SR");
        roster.add(jakeButt);

        //Alabama
        Player tuaTaligova = new Player("Tua Tagovailoa", 94, "QB", "Alabama", 12, "SO");
        roster.add(tuaTaligova);

        //Clemson
        Player trevorLawrence = new Player("Trevor Lawrence", 96,"QB","Clemson",12,"FR");
        roster.add(trevorLawrence);

        //Florida
        Player timTebow = new Player("Tim Tebow", 99,"QB","Florida",15,"SR");
        roster.add(timTebow);
    }

    public ArrayList<Player> loadRoster (String searchTeam) {
        playerDatabase();
        ArrayList<Player> teamRoster =  new ArrayList<Player>();
        for (int i = 0; i < this.roster.size(); i++) {
            Player player = this.roster.get(i);

            if (player.getTeam().equals(searchTeam)) {
                teamRoster.add(player);
            }
        }
        return teamRoster;

    }
}
