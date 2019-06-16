package sample;

import java.util.Random;
import java.util.Scanner;

public class PlayGame {
    Scanner scanner = new Scanner(System.in);
    Random rand = new Random();
    int homeScore = 0;
    int awayScore = 0;
    int score = 0;
    int time = 60;
    int hometally = 0;
    int awaytally = 0;
    int possessions;
    boolean displayPlayByPlay = false;
    Team home;
    Team away;
    Sliders sliders = new Sliders();

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void displayResults(Team homeTeam, Team awayTeam) {
        System.out.println(homeTeam.getName() + ": " + homeScore);
        System.out.println(awayTeam.getName() + ": " + awayScore);
    }

    public void playGame(Team homeTeam, Team awayTeam, boolean isHome) {
//        System.out.println("******************************************************************");
        home = homeTeam;
        away = awayTeam;
        homeScore = 0;
        awayScore = 0;
        time = 60;
        if (coinFlip()) {
            if (displayPlayByPlay) {
                System.out.println(homeTeam.getName() + " Has won the toss");
            }
            while (time > 0) {
                homeScore += offensivePossession(homeTeam, awayTeam, isHome);
                if (displayPlayByPlay) {
                    System.out.print(" SCORE: " + homeScore + "-" + awayScore);
                    System.out.println();
                }
                awayScore += offensivePossession(awayTeam, homeTeam, !isHome);
                if (displayPlayByPlay) {
                    System.out.print(" SCORE: " + homeScore + "-" + awayScore);
                    System.out.println();
                }
            }
        } else {
            if (displayPlayByPlay) {
                System.out.println(awayTeam.getName() + " Has won the toss");
            }
            while (time > 0) {
                awayScore += offensivePossession(awayTeam, homeTeam, !isHome);

                if (displayPlayByPlay) {
                    System.out.print(" SCORE: " + homeScore + "-" + awayScore);
                    System.out.println();
                }
                homeScore += offensivePossession(homeTeam, awayTeam, isHome);
                if (displayPlayByPlay) {
                    System.out.print(" SCORE: " + homeScore + "-" + awayScore);
                    System.out.println();
                }
            }
        }
        while (homeScore == awayScore) {
            if (displayPlayByPlay) {
                System.out.println("OVERTIME");
            }
            homeScore += offensivePossession(homeTeam, awayTeam, isHome);
            if (displayPlayByPlay) {
                System.out.println(homeScore + " " + awayScore);
            }
            awayScore += offensivePossession(awayTeam, homeTeam, !isHome);
            if (displayPlayByPlay) {
                System.out.println(homeScore + " " + awayScore);
            }
        }
        if (displayPlayByPlay) {
            System.out.println("GAME OVER!");
            System.out.println();
        }
//        System.out.println("FINAL SCORE");
        if (homeTeam.getRank() == -1) {
//            System.out.println(homeTeam.getName() + ": " + homeScore);
        }
        if (awayTeam.getRank() == -1) {
//            System.out.println(awayTeam.getName() + ": " + awayScore);
        }
        if (homeTeam.getRank() > -1) {
//            System.out.println("#" + homeTeam.getRank() + " " + homeTeam.getName() + ": " + homeScore);
        }
        if (awayTeam.getRank() > -1) {
//            System.out.println("#" + awayTeam.getRank() + " " + awayTeam.getName() + ": " + awayScore);
        }
//        System.out.println("******************************************************************");
//        System.out.println();
    }

    public boolean coinFlip() {
        int flip = rand.nextInt(2);
        if (flip == 0) {
            return true;
        } else {
            return false;
        }

    }

    public int offensivePossession(Team offense, Team defense, boolean isOffenseHome) {
//        possessions++;
        DepthChart offenseDC;
        DepthChart defenseDC;
        offenseDC = offense.getDepthChart();
        defenseDC = defense.getDepthChart();
//        ArrayList<Player> offenseRos = new ArrayList<Player>();
//        offenseRos = offense.getTeamRoster();
//        ArrayList<Player> defenseRos = new ArrayList<Player>();
//        defenseRos = defense.getTeamRoster();

        Player QB = new Player("QB", 60, "QB", "GENERIC", 10, "FR");
        Player RB = new Player("RB", 60, "RB", "GENERIC", 10, "FR");
        Player WR = new Player("WR", 60, "WR", "GENERIC", 10, "FR");
        Player TE = new Player("TE", 60, "TE", "GENERIC", 10, "FR");
        Player OL = new Player("OL", 60, "OL", "GENERIC", 10, "FR");
        //DEFENSE
        Player DL = new Player("DL", 60, "DL", "GENERIC", 10, "FR");
        Player LB = new Player("DL", 60, "LB", "GENERIC", 10, "FR");
        Player DB = new Player("DL", 60, "DB", "GENERIC", 10, "FR");
        //SPECIAL TEAMS
        Player K = new Player("K", 60, "K", "GENERIC", 10, "FR");
        Player COACH = new Player("K", 60, "K", "GENERIC", 10, "FR");


        home.autoReorderDepthChart();
        away.autoReorderDepthChart();


        QB = offenseDC.getQB().get(0);
        RB = offenseDC.getRB().get(0);
        WR = offenseDC.getWR().get(0);
        TE = offenseDC.getTE().get(0);
        OL = offenseDC.getOL().get(0);

        DL = defenseDC.getDL().get(0);
        LB = defenseDC.getLB().get(0);
        DB = defenseDC.getDB().get(0);

        K = offenseDC.getK().get(0);


        int qbadv = 0;
        int rbadv = 0;
        int wradv = 0;



        qbadv = ((QB.getRating() * 3) - DB.getRating() - LB.getRating() - (DL.getRating() * 2) + OL.getRating());
        rbadv = RB.getRating()*2 - DL.getRating() + OL.getRating();
        wradv = WR.getRating() - DB.getRating();
        int runningPer = 50;
        int passingPer = 50;
        // 100=32 100=24 100=16
        qbadv = (qbadv * 32 * passingPer / 100);
        rbadv = (rbadv * 24 * runningPer / 100);
        wradv = (wradv * 16 * passingPer / 100);
        if(offense.getTeamFocus().isCoaching()){
            qbadv+=175;
            rbadv+=75;
            wradv+=50;
        }
        if(defense.getTeamFocus().isCoaching()){
            qbadv-=175;
            rbadv-=75;
            wradv-=50;
        }

        if(isOffenseHome){
            qbadv+=75;
            rbadv+=50;
            wradv+=25;
        }

        //sliders
        int fgSlider = 1700;
        int touchdownSlider = sliders.getTouchdownSlider();
        int driveresultSlider = sliders.getDriveResultSlider();

//        int fieldgoal = fgSlider + qbadv + rbadv + wradv;
//        System.out.println(fieldgoal);
//        int touchdown = touchdownSlider + qbadv + rbadv + wradv;
//        System.out.println(touchdown);

        int advantage = qbadv + rbadv + wradv;
        double touchdown = (advantage + touchdownSlider);
        double fieldgoal = (advantage + touchdownSlider) / 2;
//        System.out.println(touchdown);
//        System.out.println(fieldgoal);

        if (touchdown < 100) {
            touchdown = 200;
        }
        if (fieldgoal < 100) {
            fieldgoal = 100;
        }

//        System.out.println(qbadv + " " + rbadv + " " + wradv);
//        System.out.println(fieldgoal+ " " + touchdown);
        score = 0;
        int driveResult = rand.nextInt(driveresultSlider);
//        System.out.println(driveResult);
        if (driveResult < fieldgoal) {
            int random = rand.nextInt(100);
            if (random > K.getRating()) {
                if (displayPlayByPlay) {
                    System.out.print(K.getName() + " Misses a fieldgoal");
                }
            } else {
                score += 3;
                if (displayPlayByPlay) {
                    System.out.print(K.getName() + " Makes a fieldgoal");
                }
            }
        } else if (driveResult > fieldgoal && driveResult < touchdown) {
            if (displayPlayByPlay) {
                System.out.print(offense.getName() + " SCORES A TD!");
            }
            score += 7;
        } else {
            if (displayPlayByPlay) {
                System.out.print(offense.getName() + " Punts it away");
            }
        }
        int timeLost = rand.nextInt(sliders.getDriveTime()) + 1;
        time -= timeLost;

        return score;

    }



    public void sim100(Team home, Team away) {
        int games = 100;
        for (int i = 0; i < games; i++) {
            playGame(home, away, true);
            if (homeScore > awayScore) {
                hometally++;
            } else {
                awaytally++;
            }

        }
        System.out.println(home.getName() + " WON: " + hometally + " Games " + away.getName() + " WON: " + awaytally + " Games");

    }

    public void uI(Team home, Team away) {
        this.home = home;
        this.away = away;
        boolean quit = false;
        String ans = "Y";
        System.out.println("New Game Started");
        System.out.println(home.getName() + " VS. " + away.getName());
        while (!quit) {
            System.out.println("Would you like to display play by play? (Y/N)");
            ans = scanner.nextLine();
            if (ans.equals("Y")) {
                System.out.println("Play by play will be displayed");
                System.out.println();
                displayPlayByPlay = true;
                quit = true;
            } else if (ans.equals("N")) {
                System.out.println("Play by play will not be displayed");
                System.out.println();
                displayPlayByPlay = false;
                quit = true;
            } else {
                System.out.println("Try again");
            }
        }
        playGame(home, away, true);
        displayResults(home, away);


    }


}
