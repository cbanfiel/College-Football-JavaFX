package sample;

import javafx.scene.image.Image;
import sample.gameplay.DefensivePlaybook;
import sample.gameplay.OffensivePlaybook;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Team {
    Random rand = new Random();
    boolean inSeason = false;
    private ArrayList<Player> createdPlayers = new ArrayList<Player>();
    private Coach headCoach;
    private String name;
    private int OVRrating;
    private int rank = -1;
    private ArrayList<Player> teamRoster = new ArrayList<Player>();
    private int wins;
    private int losses;
    private int gamesPlayed;
    private int index;
    private boolean played = false;
    NCAARosters ros2019 = new NCAARosters();
    private int lastGameTeamScore = 0;
    private int lastGameOppTeamScore = 0;
    private Team lastTeamPlayed;
    private int bowl = 0;
    private ArrayList<Game> previousGames = new ArrayList<Game>();
    boolean home;
    Game game;
    private DepthChart depthChart;
    private ArrayList<Season> previousSeasons = new ArrayList<Season>();
    private ArrayList<Player> graduatedPlayers = new ArrayList<Player>();
    private TeamFocus teamFocus;
    private Image logo;
    private int championships=0;
    private boolean paysRecruits = false;
    private boolean hiresStrippers = false;
    private boolean givesPlayersPeds = false;
    private boolean sanctions = false;
    private int sanctionLength =0;
    private boolean hasCheated=false;
    private int teamPrestige;
    private Expectations expectations;
    private int money;
    private int recruitingUpgrade=0;
    private int trainingUpgrade=0;
    private ArrayList<Team> fullSchedule = new ArrayList<>();
    private ArrayList<WeekSchedule> schedule = new ArrayList<>();
    private ArrayList<ArrayList> oldSchedules = new ArrayList();
    private int scheduleStrength = 0;
    private String teamType;
    private DefensivePlaybook defensivePlaybook;
    private OffensivePlaybook offensivePlaybook;

    public OffensivePlaybook getOffensivePlaybook() {
        return offensivePlaybook;
    }

    public DefensivePlaybook getDefensivePlaybook() {
        return defensivePlaybook;
    }



    public void addScheduleToHistory() {
        if (schedule.size() == 0) {
//new
        } else {
            oldSchedules.add(schedule);
            schedule.clear();
        }
    }

    public int getScheduleStrength() {
        generateScheduleStrength();
        return scheduleStrength;
    }

    public void generateScheduleStrength(){
        int total=0;
        for(int i=0; i<schedule.size(); i++){
            total += schedule.get(i).getOpp().getOVRrating();
        }
        scheduleStrength = total/schedule.size();
    }



    public ArrayList<WeekSchedule> getSchedule() {
        return schedule;
    }

    public void addScheduleOpponent(Team opponent){
        fullSchedule.add(opponent);
    }


    public ArrayList<Team> getFullSchedule() {
        return fullSchedule;
    }

    public int getRecruitingUpgrade() {
        return recruitingUpgrade;
    }

    public void setRecruitingUpgrade(int recruitingUpgrade) {
        this.recruitingUpgrade = recruitingUpgrade;
    }

    public int getTrainingUpgrade() {
        return trainingUpgrade;
    }

    public void setTrainingUpgrade(int trainingUpgrade) {
        this.trainingUpgrade = trainingUpgrade;
    }

    public void setTeamPrestige(int teamPrestige) {
        this.teamPrestige = teamPrestige;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Expectations getExpectations() {
        return expectations;
    }


    public boolean isHasCheated() {
        return hasCheated;
    }

    public void setSanctions(int length){
        sanctions=true;
        sanctionLength=length;
        paysRecruits=false;
        hiresStrippers=false;
        givesPlayersPeds=false;
        hasCheated=false;
    }

    public boolean isSanctions() {
        return sanctions;
    }

    public void removeSanctions(){
        sanctions = false;
    }

    public int getSanctionLength() {
        return sanctionLength;
    }

    public void setSanctionLength(int sanctionLength) {
        this.sanctionLength = sanctionLength;
    }

    public boolean isPaysRecruits() {
        return paysRecruits;
    }

    public void setPaysRecruits(boolean paysRecruits) {
        this.paysRecruits = paysRecruits;
        hasCheated=true;
    }

    public boolean isHiresStrippers() {
        return hiresStrippers;
    }

    public void setHiresStrippers(boolean hiresStrippers) {
        this.hiresStrippers = hiresStrippers;
        hasCheated=true;
    }

    public boolean isGivesPlayersPeds() {
        return givesPlayersPeds;
    }

    public void setGivesPlayersPeds(boolean givesPlayersPeds) {
        this.givesPlayersPeds = givesPlayersPeds;
        hasCheated=true;
    }

    public int getChampionships() {
        return championships;
    }

    public String getChampionshipsString() {
        return "" + championships;
    }

    public void addChampionship() {
        this.championships += 1;
    }

    public ArrayList<Season> getPreviousSeasons() {
        return previousSeasons;
    }

    public void setTeamFocus(TeamFocus teamFocus) {
        this.teamFocus = teamFocus;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public void seasonStarted() {
        inSeason = true;
    }

    public ArrayList<Player> getGraduatedPlayers() {
        return graduatedPlayers;
    }

    public void addGraduatedPlayers(Player player) {
        graduatedPlayers.add(player);
    }

    public void setPreviousSeasons() {
        Season season = new Season(wins, losses, rank);
        previousSeasons.add(season);
    }

    public int getBowl() {
        return bowl;
    }

    public void setBowl(int bowl) {
        this.bowl = bowl;
    }

    public void setPreviousGames(ArrayList<Game> previousGames) {
        this.previousGames = previousGames;
    }

    public void printPreviousGames(ArrayList<Game> previousGames) {
        if (previousGames == null) {
            System.out.println("No Games Played Yet");
        } else {
            System.out.println("Previous Year Results: ");
            for (int i = 0; i < previousGames.size(); i++) {
                game = previousGames.get(i);
                System.out.println(printGameResult(game.getOppTeam(), game.getTeamScore(), game.getOppTeamScore(), game.isHome()));
            }
        }

    }


    public String printPreviousSeasons() {
        String string = "";
        if (previousSeasons == null) {
            //nothing
        } else {
            for (int i = 0; i < previousSeasons.size(); i++) {
                Season season = previousSeasons.get(i);
                if (season.getRank() <= 25) {
                    string += (" Year " + (i + 1) + ": #" + season.getRank() + " " + season.getWins() + "-" + season.getLosses() + "\n" );
                }else{
                    string += (" Year " + (i + 1) + ": " + season.getWins() + "-" + season.getLosses() + "\n");
                }
            }
        }
        return string;

    }


    public void setLastGame(int lastGameTeamScore, int lastGameOppTeamScore, Team lastTeamPlayed, boolean home) {
        this.lastGameTeamScore = lastGameTeamScore;
        this.lastGameOppTeamScore = lastGameOppTeamScore;
        this.lastTeamPlayed = lastTeamPlayed;
        this.home = home;
        if (lastTeamPlayed != null) {
            addGameToHistory();
        }
    }

    public void removePreviousGames() {
        for (int i = previousGames.size() - 1; i > -1; i--)
            previousGames.remove(i);
    }

    public ArrayList<Game> getPreviousGames() {
        return previousGames;
    }

    public void addGameToHistory() {
        Game game = new Game(lastGameTeamScore, lastGameOppTeamScore, home, lastTeamPlayed);
        previousGames.add(game);
    }

    public int getOVRrating() {
        return OVRrating;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed += gamesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins += wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses += losses;
    }

    public void resetWL() {
        wins = 0;
        losses = 0;
    }

    public String getName() {
        return name;
    }

    public TeamFocus getTeamFocus() {
        return teamFocus;
    }

    public Team(String name, int rating) {
        teamFocus = new TeamFocus(rand.nextInt(3));
        this.name = name;
        this.teamType = "college";
        generatePrestige(rating);
        setTeamRoster(ros2019.generatePlayers(rating, this));
        addPlayer();
        OVRrating = calculateRating();
        autoReorderDepthChart();
        headCoach = new Coach();
        for(int i=0; i<teamRoster.size(); i++){
            Player player = teamRoster.get(i);
            player.setTeam(name);
        }
        expectations = new Expectations(this);
        defensivePlaybook = new DefensivePlaybook(this);
        offensivePlaybook = new OffensivePlaybook(this);
    }

    public String getTeamType() {
        return teamType;
    }

    public Team(String name, int rating, String teamType) {
        teamFocus = new TeamFocus(rand.nextInt(3));
        this.name = name;
        this.teamType = teamType;
        generatePrestige(rating);
        setTeamRoster(ros2019.generatePlayers(rating, this));
        addPlayer();
        OVRrating = calculateRating();
        autoReorderDepthChart();
        headCoach = new Coach();
        for(int i=0; i<teamRoster.size(); i++){
            Player player = teamRoster.get(i);
            player.setTeam(name);
        }
        expectations = new Expectations(this);
    }

    public void generatePrestige(int rating){
        if(rating<58){
            this.teamPrestige=1;
        }else if(rating<68){
            this.teamPrestige=2;
        }else if(rating<78){
            this.teamPrestige=3;
        }else if(rating<88){
            this.teamPrestige=4;
        }else{
            this.teamPrestige=5;
        }

        money = teamPrestige*100000;
    }

    public int getTeamPrestige() {
        return teamPrestige;
    }

    public Coach getHeadCoach() {
        return headCoach;
    }

    public void createPlayer(String name, int rating, String pos, int number) {
        Player created = ros2019.createPlayer(name, rating, pos, this.name, number);
        createdPlayers.add(created);
        addCreatedPlayer();


    }

    public void reorderBasedOnPlayerDepthChartPos() {
        ArrayList<Player> QB = depthChart.getQB();
        ArrayList<Player> RB = depthChart.getRB();
        ArrayList<Player> WR = depthChart.getWR();
        ArrayList<Player> OL = depthChart.getOL();
        ArrayList<Player> DL = depthChart.getDL();
        ArrayList<Player> LB = depthChart.getLB();
        ArrayList<Player> DB = depthChart.getDB();
        ArrayList<Player> K = depthChart.getK();
        ArrayList<Player> TE = depthChart.getTE();

        if (QB.size() > 1) {
            Collections.sort(QB, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.getDepthchartpos() - p2.getDepthchartpos();
                }
            });
        }
        if (RB.size() > 1) {
            Collections.sort(RB, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.getDepthchartpos() - p2.getDepthchartpos();
                }
            });
        }
        if (WR.size() > 1) {
            Collections.sort(WR, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.getDepthchartpos() - p2.getDepthchartpos();
                }
            });
        }
        if (TE.size() > 1) {
            Collections.sort(TE, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.getDepthchartpos() - p2.getDepthchartpos();
                }
            });
        }
        if (OL.size() > 1) {
            Collections.sort(OL, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.getDepthchartpos() - p2.getDepthchartpos();
                }
            });
        }
        if (DL.size() > 1) {
            Collections.sort(DL, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.getDepthchartpos() - p2.getDepthchartpos();
                }
            });
        }
        if (LB.size() > 1) {
            Collections.sort(LB, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.getDepthchartpos() - p2.getDepthchartpos();
                }
            });
        }
        if (DB.size() > 1) {
            Collections.sort(DB, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.getDepthchartpos() - p2.getDepthchartpos();
                }
            });
        }
        if (K.size() > 1) {
            Collections.sort(K, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    return p1.getDepthchartpos() - p2.getDepthchartpos();
                }
            });
        }
        depthChart = new DepthChart(QB, RB, WR, TE, OL, DL, LB, DB, K);
        ArrayList<Player> teamRoster2 = new ArrayList<Player>();

        teamRoster2.addAll(QB);
        teamRoster2.addAll(RB);
        teamRoster2.addAll(WR);
        teamRoster2.addAll(TE);
        teamRoster2.addAll(OL);
        teamRoster2.addAll(DL);
        teamRoster2.addAll(LB);
        teamRoster2.addAll(DB);
        teamRoster2.addAll(K);


        teamRoster = teamRoster2;


    }

    public void autoReorderDepthChart() {
        ArrayList<Player> QB = new ArrayList<Player>();
        ArrayList<Player> RB = new ArrayList<Player>();
        ArrayList<Player> WR = new ArrayList<Player>();
        ArrayList<Player> TE = new ArrayList<Player>();
        ArrayList<Player> OL = new ArrayList<Player>();
        ArrayList<Player> DL = new ArrayList<Player>();
        ArrayList<Player> LB = new ArrayList<Player>();
        ArrayList<Player> DB = new ArrayList<Player>();
        ArrayList<Player> K = new ArrayList<Player>();
        for (int i = 0; i < teamRoster.size(); i++) {
            Player player = teamRoster.get(i);
            if (player.getPosition().equals("QB")) {
                QB.add(player);
            } else if (player.getPosition().equals("RB")) {
                RB.add(player);
            } else if (player.getPosition().equals("WR")) {
                WR.add(player);
            } else if (player.getPosition().equals("TE")) {
                TE.add(player);
            } else if (player.getPosition().equals("OL")) {
                OL.add(player);
            } else if (player.getPosition().equals("DL")) {
                DL.add(player);
            } else if (player.getPosition().equals("LB")) {
                LB.add(player);
            } else if (player.getPosition().equals("DB")) {
                DB.add(player);
            } else if (player.getPosition().equals("K")) {
                K.add(player);
            }
            if (QB.size() > 1) {
                Collections.sort(QB, new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getRating() - p1.getRating();
                    }
                });
            }
            if (RB.size() > 1) {
                Collections.sort(RB, new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getRating() - p1.getRating();
                    }
                });
            }
            if (WR.size() > 1) {
                Collections.sort(WR, new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getRating() - p1.getRating();
                    }
                });
            }
            if (TE.size() > 1) {
                Collections.sort(TE, new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getRating() - p1.getRating();
                    }
                });
            }
            if (OL.size() > 1) {
                Collections.sort(OL, new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getRating() - p1.getRating();
                    }
                });
            }
            if (DL.size() > 1) {
                Collections.sort(DL, new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getRating() - p1.getRating();
                    }
                });
            }
            if (LB.size() > 1) {
                Collections.sort(LB, new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getRating() - p1.getRating();
                    }
                });
            }
            if (DB.size() > 1) {
                Collections.sort(DB, new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getRating() - p1.getRating();
                    }
                });
            }
            if (K.size() > 1) {
                Collections.sort(K, new Comparator<Player>() {
                    @Override
                    public int compare(Player p1, Player p2) {
                        return p2.getRating() - p1.getRating();
                    }
                });
            }
        }
        depthChart = new DepthChart(QB, RB, WR, TE, OL, DL, LB, DB, K);
        ArrayList<Player> teamRoster2 = new ArrayList<Player>();

        teamRoster2.addAll(QB);
        teamRoster2.addAll(RB);
        teamRoster2.addAll(WR);
        teamRoster2.addAll(TE);
        teamRoster2.addAll(OL);
        teamRoster2.addAll(DL);
        teamRoster2.addAll(LB);
        teamRoster2.addAll(DB);
        teamRoster2.addAll(K);


        teamRoster = teamRoster2;


    }


    public void addCreatedPlayer() {
        ArrayList<Player> players = createdPlayers;
        Player player = new Player("", 0, "", "", 0, "");
        for (int i = 0; i < players.size(); i++) {
            player = players.get(i);
            teamRoster.add(player);
            autoReorderDepthChart();

        }
    }

    // NEED TO RE WORK SO THAT ROSTERS ARE ALL SAME SIZE
    public void addPlayer() {
        ros2019.customPlayers();
        ArrayList<Player> players = ros2019.customRoster;
        Player player = new Player("", 0, "", "", 0, "");
        for (int i = 0; i < players.size(); i++) {
            player = players.get(i);
            if (player.getTeam().equals(name)) {
                for (int j = 0; j < teamRoster.size(); j++) {
                    Player player2 = teamRoster.get(j);
                    if (player.getPosition().equals(player2.getPosition())) {
                        teamRoster.remove(j);
                        teamRoster.add(player);
                        j = 100;
                    }
                }
            }
        }
    }

    public Team(String name) {
        this.name = name;

    }

    public ArrayList<Player> getTeamRoster() {
        return teamRoster;
    }

    public void setTeamRoster(ArrayList<Player> teamRoster) {
        this.teamRoster = teamRoster;
    }

    public int calculateRating() {
        int totalRatings = 0;
        for (int i = 0; i < teamRoster.size(); i++) {
            Player player = teamRoster.get(i);

            totalRatings += player.getRating();
        }

        OVRrating = totalRatings / teamRoster.size();
//        System.out.println(name + " Overall Rating: " + rating);
        return OVRrating;
    }


    public void addPlayer(Player player) {
        teamRoster.add(player);
    }

    public DepthChart getDepthChart() {
        return depthChart;
    }

    public String simpleToString() {
        if (rank <= 25) {
            return "#" + rank + " " + name + " " + wins + "-" + losses + " OVR: " + getOVRrating();


        } else {
            return name + " " + wins + "-" + losses + " OVR: " + getOVRrating();
        }
    }

    public String prevGamesToString() {
        if (wins + losses == 0) {
            return "";
        } else {
            return "" + previousGames.get(wins + losses - 1);
        }
    }

    @Override
    public String toString() {
        if(rank==-1){
            return name;
        }
        else if (rank <= 25) {
            return "#" + rank + " " + name + " " + wins + "-" + losses + " " + prevGamesToString();

        } else {
            return name + " " + wins + "-" + losses + " " + prevGamesToString();
        }
    }

    public String scheduleToString() {
        if(rank==-1){
            return name;
        }
        else if (rank <= 25) {
            return "#" + rank + " " + name ;

        } else {
            return name;
        }
    }


    public String printRoster() {
        String playerInfo = "";
        String prompt = name + ": " + calculateRating() + " OVR \n Team Roster Size: " + teamRoster.size();
        for (int i = 0; i < teamRoster.size(); i++) {
            Player player = teamRoster.get(i);
            if (i > 0) {
                Player previous = teamRoster.get(i - 1);
                if (!player.getPosition().equals(previous.getPosition())) {
                    System.out.println();
                }
            }
            int change = player.getRating() - player.getPrevRating();
            if (player.getPrevRating() == 0 || change == 0) {
                playerInfo += "\n" + player.getYear() + " " + player.getPosition() + " #" + player.getNumber() + " " + player.getName() + " " + player.getRating() + " OVR";
            } else if (change > 0) {
                playerInfo += "\n" + player.getYear() + " " + player.getPosition() + " #" + player.getNumber() + " " + player.getName() + " " + player.getRating() + " OVR" + " (+" + change + ")";
            } else {

                playerInfo += "\n" + player.getYear() + " " + player.getPosition() + " #" + player.getNumber() + " " + player.getName() + " " + player.getRating() + " OVR" + " (" + change + ")";
            }

        }
        return prompt + playerInfo;
    }

//    public void displayPlayer (Player player) {
//            int change = player.getRating() - player.getPrevRating();
//            if (player.getPrevRating() == 0 || change == 0) {
//                System.out.println(player.getYear() + " " + player.getPosition() + " #" + player.getNumber() + " " + player.getName() + " " + player.getRating() + " OVR");
//            } else if (change > 0) {
//                System.out.println(player.getYear() + " " + player.getPosition() + " #" + player.getNumber() + " " + player.getName() + " " + player.getRating() + " OVR" + " (+" + change+")");
//            } else {
//
//                System.out.println(player.getYear() + " " + player.getPosition() + " #" + player.getNumber() + " " + player.getName() + " " + player.getRating() + " OVR" + " (" + change+")");
//            }
//        }
//
//        public void printRosterv2(){
//        ArrayList<Player> Depthplayers = depthChart.getQB();
//           for(int i=0; i<Depthplayers.size(); i++){
//               Player player = Depthplayers.get(i);
//               displayPlayer(player);
//            }
//            Depthplayers = depthChart.getRB();
//            for(int i=0; i<Depthplayers.size(); i++){
//                Player player = Depthplayers.get(i);
//                displayPlayer(player);
//            }
//            Depthplayers = depthChart.getWR();
//            for(int i=0; i<Depthplayers.size(); i++){
//                Player player = Depthplayers.get(i);
//                displayPlayer(player);
//            }
//            Depthplayers = depthChart.getTE();
//            for(int i=0; i<Depthplayers.size(); i++){
//                Player player = Depthplayers.get(i);
//                displayPlayer(player);
//            }
//            Depthplayers = depthChart.getOL();
//            for(int i=0; i<Depthplayers.size(); i++){
//                Player player = Depthplayers.get(i);
//                displayPlayer(player);
//            }
//            Depthplayers = depthChart.getDL();
//            for(int i=0; i<Depthplayers.size(); i++){
//                Player player = Depthplayers.get(i);
//                displayPlayer(player);
//            }
//           Depthplayers = depthChart.getLB();
//            for(int i=0; i<Depthplayers.size(); i++){
//                Player player = Depthplayers.get(i);
//                displayPlayer(player);
//            }
//            Depthplayers = depthChart.getLB();
//            for(int i=0; i<Depthplayers.size(); i++){
//                Player player = Depthplayers.get(i);
//                displayPlayer(player);
//            }
//            Depthplayers = depthChart.getLB();
//            for(int i=0; i<Depthplayers.size(); i++){
//                Player player = Depthplayers.get(i);
//                displayPlayer(player);
//            }
//
//
//
//        }


    //    public void loadRoster(){
//        Rosters roster =  new Rosters();
//        teamRoster=roster.loadRoster(name);
//
//    }
    public String printGameResult(Team opp, int yourScore, int oppScore, boolean home) {
        if (opp == null) {
            return "";
        } else {
            int oppRank = opp.getRank();
            if (oppRank > 25) {
                if (yourScore > oppScore) {
                    return " W " + opp.getName() + " " + yourScore + "-" + oppScore;
                } else {
                    return " L " + opp.getName() + " " + yourScore + "-" + oppScore;
                }
            } else {
                if (yourScore > oppScore) {
                    return " W #" + opp.getRank() + " " + opp.getName() + " " + yourScore + "-" + oppScore;
                } else {
                    return " L #" + opp.getRank() + " " + opp.getName() + " " + yourScore + "-" + oppScore;
                }
            }
        }
    }


    public String printLastGameResult() {
        if (lastTeamPlayed == null) {
            return "";
        } else {
            int oppRank = lastTeamPlayed.getRank();
            if (oppRank > 25) {
                if (lastGameTeamScore > lastGameOppTeamScore) {
                    return " W " + lastTeamPlayed.getName() + " " + lastGameTeamScore + "-" + lastGameOppTeamScore;
                } else {
                    return " L " + lastTeamPlayed.getName() + " " + lastGameTeamScore + "-" + lastGameOppTeamScore;
                }
            } else {
                if (lastGameTeamScore > lastGameOppTeamScore) {
                    return " W #" + lastTeamPlayed.getRank() + " " + lastTeamPlayed.getName() + " " + lastGameTeamScore + "-" + lastGameOppTeamScore;
                } else {
                    return " L #" + lastTeamPlayed.getRank() + " " + lastTeamPlayed.getName() + " " + lastGameTeamScore + "-" + lastGameOppTeamScore;
                }
            }
        }
    }


}




