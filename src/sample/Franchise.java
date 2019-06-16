package sample;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Franchise {
    Random rand = new Random();
    Sliders sliders;
    ArrayList<Team> teams;
    PlaySeason franchise;
    ArrayList<PlaySeason> history = new ArrayList<PlaySeason>();
    int year = 1;
    NCAARosters franchiseplayers = new NCAARosters();
    Menu menu = new Menu();
    Team selectedTeam;

    public Franchise(Sliders sliders, ArrayList<Team> teams) {
        this.sliders = sliders;
        this.teams = teams;
        franchise = new PlaySeason(teams);
    }

    public int getYear() {
        return year;
    }

    public void graduatePlayers() {
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            team.getExpectations().metExpectations();
            team.setPreviousSeasons();
            team.resetWL();
            team.removePreviousGames();
            team.setLastGame(0, 0, null, false);
            ArrayList<Player> roster = team.getTeamRoster();
            for (int j = 0; j < roster.size(); j++) {

                Player player = roster.get(j);
                if (player.isCurrentRedshirt()) {
                    player.removeRedshirt();
                    offseasonTraining(player, team);
                } else {
                    if (player.getYear().equals("SR")) {
                        team.addGraduatedPlayers(player);
                        recruit(team.calculateRating(), team.getRank(), player, team);

                    } else if (player.getYear().equals("JR")) {
                        player.setYear("SR");
                        offseasonTraining(player, team);
                    } else if (player.getYear().equals("SO")) {
                        player.setYear("JR");
                        offseasonTraining(player, team);

                    } else if (player.getYear().equals("COACH")) {
                        //do nothing
                    } else {
                        player.setYear("SO");
                        offseasonTraining(player, team);
                    }
                }
            }
            team.autoReorderDepthChart();
            setSanctions(team);
            team.getExpectations().customizeExpectations();
        }
    }


    public void advanceYear() {
        year++;
        history.add(franchise);
        PlaySeason nextYear = new PlaySeason(teams);
        franchise = nextYear;
        System.out.println();
        System.out.println("Year " + year);
        franchise.setRankings();
        franchise.printWeeklyRankings();
    }

    public void offseasonTraining(Player player, Team team) {


        int rating = player.getRating();
        player.setPrevRating(rating);
        int development = rand.nextInt(4) - 3;
        if (team.getTeamFocus().isTraining()) {
            development += 1;
        }

        development += team.getTeamPrestige();

        if (team.isGivesPlayersPeds()) {
            development += 5;
        }

        if (player.getDepthchartpos() == 0) {
//            System.out.println(team.getName() + " " + player.getName() + " Was a starter");
            development += 5;
        }

        if (team.isSanctions()) {
            development -= 2;
        }

        development += team.getTrainingUpgrade();


        rating += development;
        if (rating > 99) {
            rating = 99;
        }
        if (rating < 40) {
            rating = 40;
        }
        player.setRating(rating);

    }

    public void recruit(int teamrating, int teamRank, Player player, Team team) {
        int rating = generateRecruitRating(teamrating, teamRank, team);
        Player freshman = player;
        player.clearInjury();
        player.clearRedshirt();
        player.setYear("FR");
        player.setName("");
        player.setPrevRating(0);
        player.setRating(rating);
        if (player.getPosition().equals("QB")) {
            player.setNumber(rand.nextInt(19 - 1) + 1);
        } else if (player.getPosition().equals("RB") || player.getPosition().equals("DB")) {
            player.setNumber(rand.nextInt(49 - 1) + 1);
        } else if (player.getPosition().equals("TE") || player.getPosition().equals("WR")) {
            player.setNumber(rand.nextInt(89 - 80) + 80);
        } else if (player.getPosition().equals("OL")) {
            player.setNumber(rand.nextInt(79 - 50) + 50);
        } else if (player.getPosition().equals("DL")) {
            player.setNumber(rand.nextInt(99 - 50) + 50);
        } else if (player.getPosition().equals("LB")) {
            player.setNumber(rand.nextInt(59 - 50) + 50);
        } else if (player.getPosition().equals("K")) {
            player.setNumber(rand.nextInt(99 - 1) + 1);
        }
    }

    public int generateRecruitRating(int teamRating, int teamRank, Team team) {
        int high = 68 - (teamRank / 5) + (teamRating / 3);
        int low = 35 - (teamRank / 5) + (teamRating / 3);
        if (teamRank <= 10) {
            high += 1;
            low += 1;
        }

        if (teamRank <= 25) {
            high += 2;
            low += 2;
        }

        high += team.getTeamPrestige();
        low += team.getTeamPrestige();
//        if(year>=2) {
//                int difference = team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getWins() - team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 2).getWins();
//                high+=difference;
//                low+=difference;
//                System.out.println("shit " + team.getName() + " " + difference);
//            }
        if (high <= low) {
            high = low + 1;
        }

        int rating = rand.nextInt(high - low) + low;
        int breakoutPlayer = rand.nextInt(100);
        if (breakoutPlayer < 1) {
            rating += 20;
//            System.out.println(rating + " " + team.getName());
        }
        if (team.getTeamFocus().isRecruiting()) {
            rating += 2;
        }

        if (team.isHiresStrippers()) {
            rating += 3;
        }

        if (team.isPaysRecruits()) {
            rating += 3;
        }

        if (team.isSanctions()) {
            rating -= 8;
        }

        if (team.getMoney() <= 0) {
            rating -= 8;
            System.out.println(team + " " + rating);
        }

        rating += team.getRecruitingUpgrade();

        if (rating > 86) {
            rating = 86;
        }
        if (rating < 40) {
            rating = 40;
        }

//        if(team.getTeamPrestige()>=4){
//            System.out.println(team.getName() + " " + rating);
//        }
        return rating;
    }

    public void setSanctions(Team team) {
        int chance = 0;
        int random = rand.nextInt(20) + 1;

        if (team.isSanctions()) {
            team.setSanctionLength(team.getSanctionLength() - 1);
            if (team.getSanctionLength() == 0) {
                team.removeSanctions();
            }
        }


        if (team.isPaysRecruits()) {
            chance += 2;
        }
        if (team.isHiresStrippers()) {
            chance += 2;
        }
        if (team.isPaysRecruits()) {
            chance += 2;
        }

        if (chance == 0 && team.isHasCheated()) {
            chance += 1;
        }

        if (chance >= random) {
            int years = rand.nextInt(7) + 1;
            team.setSanctions(years);
            team.setMoney(team.getMoney() - 100000);
        }


    }



}



