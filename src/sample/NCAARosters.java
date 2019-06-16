/*
THIS WOULD HAVE ALL OF THE PLAYERS IN SEPARATE METHODS FOR THERE GIVEN TEAM
 */

package sample;

import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NCAARosters {
    //for generated players
    Random rand = new Random();
    ArrayList<Player> teamRoster = new ArrayList<Player>();
    ArrayList<Player> customRoster = new ArrayList<Player>();
    Image face;
    static String[] firstNames;
    static String[] lastNames;


    public void customPlayers() {
        Player p1 = new Player("Shea Patterson", 86, "QB", "Michigan", 2, "JR");
        face = new Image("file:///C:/Users/chadb/IdeaProjects/NCAAFootball19GUILastTime/src/sample/img/players/sheapatterson.gif");
        p1.setFace(face);
        Player p2 = new Player("Karan Higdon", 93, "RB", "Michigan", 22, "SR");
        Player p3 = new Player("Donovoan Peoples-Jones", 84, "WR", "Michigan", 9, "SO");
        Player p4 = new Player("Zach Gentry", 79, "TE", "Michigan", 83, "JR");
        Player p5 = new Player("Ben Bredeson", 87, "OL", "Michigan", 74, "JR");
        Player p6 = new Player("Chase Winovich", 96, "DL", "Michigan", 15, "SR");
        Player p7 = new Player("Devin Bush", 95, "LB", "Michigan", 10, "SR");
        Player p8 = new Player("Lavert Hill", 85, "DB", "Michigan", 24, "JR");
        Player p9 = new Player("Quinn Nordin", 75, "K", "Michigan", 3, "SO");
        Player p10 = new Player("Jim Harbaugh", 30, "COACH", "Michigan", 0, "COACH");
        Player p11 = new Player("Tua Taligova", 95, "QB", "Alabama", 10, "SO");
        Player p12 = new Player("Chad Banfield", 65, "QB", "EMU", 2, "JR");
        face = new Image("file:///C:/Users/chadb/IdeaProjects/NCAAFootball19GUILastTime/src/sample/img/players/chadbanfield.gif");
        p12.setFace(face);
        Player[] players = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12};
        customRoster.addAll(Arrays.asList(players));
    }

    public Player createPlayer(String name, int rating, String positon, String team, int number) {
        Player createdPlayer = new Player(name, rating, positon, team, number, "FR");
        return createdPlayer;
    }


    public ArrayList<Player> generatePlayers(int teamRating, Team team) {
        //OFFENSE

        Player QB = new Player(pickName(), generatePlayerRating(teamRating), "QB", "GENERIC", rand.nextInt(19 - 1) + 1, getyear(team));
        Player QB2 = new Player(pickName(), generatePlayerRating(teamRating), "QB", "GENERIC", rand.nextInt(19 - 1) + 1, getyear(team));
        Player QB3 = new Player(pickName(), generatePlayerRating(teamRating), "QB", "GENERIC", rand.nextInt(19 - 1) + 1, getyear(team));


        Player RB = new Player(pickName(), generatePlayerRating(teamRating), "RB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player RB2 = new Player(pickName(), generatePlayerRating(teamRating), "RB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player RB3 = new Player(pickName(), generatePlayerRating(teamRating), "RB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));

        Player WR = new Player(pickName(), generatePlayerRating(teamRating), "WR", "GENERIC", rand.nextInt(89 - 80) + 80, getyear(team));
        Player WR2 = new Player(pickName(), generatePlayerRating(teamRating), "WR", "GENERIC", rand.nextInt(89 - 80) + 80, getyear(team));
        Player WR3 = new Player(pickName(), generatePlayerRating(teamRating), "WR", "GENERIC", rand.nextInt(89 - 80) + 80, getyear(team));
        Player WR4 = new Player(pickName(), generatePlayerRating(teamRating), "WR", "GENERIC", rand.nextInt(89 - 80) + 80, getyear(team));
        Player WR5 = new Player(pickName(), generatePlayerRating(teamRating), "WR", "GENERIC", rand.nextInt(89 - 80) + 80, getyear(team));
        Player WR6 = new Player(pickName(), generatePlayerRating(teamRating), "WR", "GENERIC", rand.nextInt(89 - 80) + 80, getyear(team));


        Player TE = new Player(pickName(), generatePlayerRating(teamRating), "TE", "GENERIC", rand.nextInt(89 - 80) + 80, getyear(team));
        Player TE2 = new Player(pickName(), generatePlayerRating(teamRating), "TE", "GENERIC", rand.nextInt(89 - 80) + 80, getyear(team));
        Player TE3 = new Player(pickName(), generatePlayerRating(teamRating), "TE", "GENERIC", rand.nextInt(89 - 80) + 80, getyear(team));

        Player OL = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        Player OL2 = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        Player OL3 = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        Player OL4 = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        Player OL5 = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        Player OL6 = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        Player OL7 = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        Player OL8 = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        Player OL9 = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        Player OL10 = new Player(pickName(), generatePlayerRating(teamRating), "OL", "GENERIC", rand.nextInt(79 - 50) + 50, getyear(team));
        //DEFENSE
        Player DL = new Player(pickName(), generatePlayerRating(teamRating), "DL", "GENERIC", rand.nextInt(99 - 90) + 90, getyear(team));
        Player DL2 = new Player(pickName(), generatePlayerRating(teamRating), "DL", "GENERIC", rand.nextInt(99 - 90) + 90, getyear(team));
        Player DL3 = new Player(pickName(), generatePlayerRating(teamRating), "DL", "GENERIC", rand.nextInt(99 - 90) + 90, getyear(team));
        Player DL4 = new Player(pickName(), generatePlayerRating(teamRating), "DL", "GENERIC", rand.nextInt(99 - 90) + 90, getyear(team));
        Player DL5 = new Player(pickName(), generatePlayerRating(teamRating), "DL", "GENERIC", rand.nextInt(99 - 90) + 90, getyear(team));
        Player DL6 = new Player(pickName(), generatePlayerRating(teamRating), "DL", "GENERIC", rand.nextInt(99 - 90) + 90, getyear(team));
        Player DL7 = new Player(pickName(), generatePlayerRating(teamRating), "DL", "GENERIC", rand.nextInt(99 - 90) + 90, getyear(team));
        Player DL8 = new Player(pickName(), generatePlayerRating(teamRating), "DL", "GENERIC", rand.nextInt(99 - 90) + 90, getyear(team));

        Player LB = new Player(pickName(), generatePlayerRating(teamRating), "LB", "GENERIC", rand.nextInt(59 - 50) + 50, getyear(team));
        Player LB2 = new Player(pickName(), generatePlayerRating(teamRating), "LB", "GENERIC", rand.nextInt(59 - 50) + 50, getyear(team));
        Player LB3 = new Player(pickName(), generatePlayerRating(teamRating), "LB", "GENERIC", rand.nextInt(59 - 50) + 50, getyear(team));
        Player LB4 = new Player(pickName(), generatePlayerRating(teamRating), "LB", "GENERIC", rand.nextInt(59 - 50) + 50, getyear(team));
        Player LB5 = new Player(pickName(), generatePlayerRating(teamRating), "LB", "GENERIC", rand.nextInt(59 - 50) + 50, getyear(team));
        Player LB6 = new Player(pickName(), generatePlayerRating(teamRating), "LB", "GENERIC", rand.nextInt(59 - 50) + 50, getyear(team));

        Player DB = new Player(pickName(), generatePlayerRating(teamRating), "DB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player DB2 = new Player(pickName(), generatePlayerRating(teamRating), "DB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player DB3 = new Player(pickName(), generatePlayerRating(teamRating), "DB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player DB4 = new Player(pickName(), generatePlayerRating(teamRating), "DB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player DB5 = new Player(pickName(), generatePlayerRating(teamRating), "DB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player DB6 = new Player(pickName(), generatePlayerRating(teamRating), "DB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player DB7 = new Player(pickName(), generatePlayerRating(teamRating), "DB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player DB8 = new Player(pickName(), generatePlayerRating(teamRating), "DB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));
        Player DB9 = new Player(pickName(), generatePlayerRating(teamRating), "DB", "GENERIC", rand.nextInt(49 - 20) + 20, getyear(team));

        //SPECIAL TEAMS
        Player K = new Player(pickName(), generatePlayerRating(teamRating), "K", "GENERIC", rand.nextInt(98) + 1, getyear(team));
        Player K2 = new Player(pickName(), generatePlayerRating(teamRating), "K", "GENERIC", rand.nextInt(98) + 1, getyear(team));

        Player COACH = new Player("Coach", 50, "COACH", "GENERIC", 0, "COACH");
        Player[] genericDepthChart = {QB, QB2, QB3, RB, WR, TE, OL, DL, DB, LB, K, COACH, RB2, RB3, WR2, WR3, WR4, WR5, TE2, TE3, OL2, OL3, OL4, OL5, DL2, DL3, DL4, LB2, LB3, DB2, DB3, DB4, DB5, K2, WR6, OL6, OL7, OL8, OL9, OL10, DL5, DL6, DL7, DL8, LB4, LB5, LB6, DB6, DB7, DB8, DB9};
        teamRoster.addAll(Arrays.asList(genericDepthChart));
        return teamRoster;
    }

    public int generatePlayerRating(int teamRating) {
        int high = teamRating + 10;
        int low = teamRating - 25;
        int rating = rand.nextInt(high - low) + low;
        if (rating > 99) {
            rating = 99;
        }
        if (rating < 40) {
            rating = 40;
        }
        return rating;
    }

    public String getyear(Team team) {
        if (team.getTeamType().equals("college")) {
            int year = rand.nextInt(4);
            if (year == 0) {
                return "FR";
            } else if (year == 1) {
                return "SO";
            } else if (year == 2) {
                return "JR";
            } else {
                return "SR";
            }
        } else return "" + getAge();

    }

    public int getAge() {
        int age = rand.nextInt(20) + 20;
        return age;
    }

    public static void readNames() {

        String names = "";
        try {
            Path path = FileSystems.getDefault().getPath("src\\sample\\files\\FirstNames.txt");
            names = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        firstNames = names.split("\\n");
        try {
            Path path = FileSystems.getDefault().getPath("src\\sample\\files\\LastNames.txt");
            names = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastNames = names.split("\\n");


    }

    public String pickName() {
        String first = firstNames[rand.nextInt(firstNames.length)].trim().toLowerCase();
        String last = lastNames[rand.nextInt(lastNames.length)].trim().toLowerCase();
        first = first.substring(0, 1).toUpperCase() + first.substring(1);
        last = last.substring(0, 1).toUpperCase() + last.substring(1);
        return first + " " + last;
    }
}
