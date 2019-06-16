package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.gameplay.Gameplay;

import java.util.*;

public class PlaySeason {

    ArrayList<Team> teams;
    Random rand = new Random();
    PlayGame season = new PlayGame();
    int week = 0;
    Sliders sliders = new Sliders();
    int seasonLength = sliders.getSeasonLength();
    ArrayList<Player> injuredPlayers = new ArrayList<>();


    //new
    Gameplay game;

    public PlaySeason(ArrayList teams) {
        this.teams = teams;
        for(int i=0; i<teams.size(); i++){
            this.teams.get(i).addScheduleToHistory();
        }



        generateSchedule();
        startedASeason();

    }

    public void startedASeason() {
        for (int i = 0; i < teams.size(); i++) {
            teams.get(i).seasonStarted();
        }
    }

    @FXML
    Label teamRankings = new Label();

    public void initialize() {
//        setRankings(teams);
//        String string = printWeeklyRankings(teams);
//        System.out.println(string);
        teamRankings.setText("hello");
    }


    public void uI(ArrayList<Team> teams) {
        Scanner scanner = new Scanner(System.in);
        int action;
        boolean quit = false;
        System.out.println("NEW SEASON");
        setRankings();
        printWeeklyRankings();
        while (!quit) {
            System.out.println("Enter 1 To sim A week Ahead");
            System.out.println("Enter 2 To sim to a specific week");
            System.out.println("Enter 3 To sim to end of the season");
            System.out.println("Enter 4 to look at specific team");
            System.out.println("Enter 5 to Quit");
            action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {

                case 1:
                    if (week >= seasonLength) {
                        System.out.println("Season Over");
                        printSeasonResults();
                        setBowls();
                        quit = true;
                        break;
                    }
                    playWeek();
                    break;
                case 2:
                    System.out.println("Enter week up to " + seasonLength);
                    int weekNum = scanner.nextInt();
                    scanner.nextLine();
                    weekNum++;
                    if (weekNum > seasonLength || weekNum <= week) {
                        System.out.println("Invalid Week Try Again");
                        break;
                    }
                    while (week < weekNum) {
                        playWeek();
                    }
                    break;
                case 3:
                    while (week - 1 < seasonLength) {
                        playWeek();
                    }
                    break;
                case 4:
                    System.out.println("Enter Team name");
                    String teamName = scanner.nextLine();
                    printTeamInfo(teams, teamName);

                    break;
                case 5:
                    System.out.println("Quitting");
                    quit = true;
                    break;
            }
        }
    }


    public void simSeason(ArrayList<Team> teams) {
        setWeek1Rankings();
        for (int i = 0; i < seasonLength; i++) {
            setTeamIndex(teams);
            playWeek();
        }
        setRankings();
        printSeasonResults();
    }

    public String printSeasonResults() {
        String string = "";
        Team team;
        setRankings();
        string += "Season Over";

        string += "\n" + ("******************************************************************");
        for (int i = 0; i < 25; i++) {
            team = teams.get(i);
            string += "\n" + ("#" + (i + 1) + " " + team.getName() + " RECORD: " + team.getWins() + "-" + team.getLosses() + team.printLastGameResult());
        }

        for (int i = 25; i < teams.size(); i++) {
            team = teams.get(i);
            string += "\n" + (team.getName() + " RECORD: " + team.getWins() + "-" + team.getLosses() + team.printLastGameResult());
        }
        return string;
    }

    public void setTeamIndex(ArrayList<Team> teams) {
        ArrayList<Integer> randomNums = new ArrayList<Integer>();

        while (randomNums.size() < teams.size()) {
            int random = rand.nextInt(teams.size());
            if (!randomNums.contains(random)) {
                randomNums.add(random);
            }
        }
        for (int i = 0; i < teams.size(); i++) {
            int number = randomNums.get(i);
//            System.out.println(number);
            Team team = teams.get(i);
            team.setIndex(number);
        }
    }

    public ArrayList<Team> reorderTeamIndex(ArrayList<Team> teams) {
        Team team;
        for (int i = 0; i < teams.size(); i++) {
            team = teams.get(i);
            teams.remove(teams.get(i));
            teams.add(team.getIndex(), team);
        }
        return teams;
    }


    public boolean playWeek() {
        checkInjuredPlayers();
        boolean playing = true;
        if (week >= seasonLength) {
            return false;

        } else {
            Team home;
            Team away;
//        setRankings(teams);
//        printWeeklyRankings(teams);
//            setTeamIndex(teams);
//            teams = reorderTeamIndex(teams);

            for (int i = 0; i < teams.size(); i++) {
                Team team = teams.get(i);
                if (team.getSchedule().get(week).isWasPlayed()) {

                } else {
                    setInjuries(team);
                    setInjuries(team.getSchedule().get(week).getOpp());
//                    season.simGame(team, team.getSchedule().get(week).getOpp(),team.getSchedule().get(week).isHome());
                    game = new Gameplay(team,team.getSchedule().get(week).getOpp());
                    while(game.getTime()>0 || game.getHomeScore()==game.getAwayScore()){
                        game.playPossesion();
                    }
                    int teamScore = game.getHomeScore();
                    int oppTeamScore = game.getAwayScore();
                    if (teamScore > oppTeamScore) {
                        team.setWins(1);
                        team.getSchedule().get(week).getOpp().setLosses(1);
                    } else {
                        team.getSchedule().get(week).getOpp().setWins(1);
                        team.setLosses(1);
                    }
                    //old way
                    team.setLastGame(teamScore, oppTeamScore, team.getSchedule().get(week).getOpp(), team.getSchedule().get(week).isHome());
                    team.getSchedule().get(week).getOpp().setLastGame(oppTeamScore, teamScore, team, team.getSchedule().get(week).getOpp().getSchedule().get(week).isHome());
                    //new way
                    team.getSchedule().get(week).setYourScore(teamScore);
                    team.getSchedule().get(week).getOpp().getSchedule().get(week).setYourScore(oppTeamScore);
                    team.getSchedule().get(week).setOppScore(oppTeamScore);
                    team.getSchedule().get(week).getOpp().getSchedule().get(week).setOppScore(teamScore);
                    team.getSchedule().get(week).setYourRank(team.getRank());
                    team.getSchedule().get(week).setOppRank(team.getSchedule().get(week).getOpp().getRank());

                    team.getSchedule().get(week).getOpp().getSchedule().get(week).setYourRank(team.getSchedule().get(week).getOpp().getRank());
                    team.getSchedule().get(week).getOpp().getSchedule().get(week).setOppRank(team.getRank());

                    team.getSchedule().get(week).setWasPlayed(true);
                    team.getSchedule().get(week).getOpp().getSchedule().get(week).setWasPlayed(true);

                    archiveGameStats(team.getSchedule().get(week).getOpp());
                    archiveGameStats(team);



                }
            }

            week++;
            setRankings();
            printWeeklyRankings();
        }
        return playing;
    }

    void archiveGameStats(Team team){
        for(int i=0; i<team.getTeamRoster().size(); i++){
            Player play = team.getTeamRoster().get(i);
            play.getGameStatsHistory().add(play.getGameStats());
            play.startNewGameStats();
        }
    }

    public String playBowls() {
        String results = "";
        checkInjuredPlayers();
        Team home;
        Team away;
//        setRankings(teams);
//        printWeeklyRankings(teams);
        setRankings();
        home = teams.get(0);
        away = teams.get(1);
        setInjuries(home);
        setInjuries(away);
        season.playGame(home, away, true);
        int homeScore = season.getHomeScore();
        int awayScore = season.getAwayScore();
        if (homeScore > awayScore) {
            home.setWins(1);
            away.setLosses(1);
            home.setRank(1);
            away.setRank(2);
            results += "#" + home.getRank() + " " + home.getName() + " Beat " + "#" + away.getRank() + " " + away.getName() + " " + homeScore + "-" + awayScore;
            home.addChampionship();

        } else {
            away.setWins(1);
            home.setLosses(1);
            home.setRank(2);
            away.setRank(1);
            away.addChampionship();
            results += "#" + away.getRank() + " " + away.getName() + " Beat " + "#" + home.getRank() + " " + home.getName() + " " + awayScore + "-" + homeScore;

        }
        home.setLastGame(homeScore, awayScore, away, true);
        away.setLastGame(awayScore, homeScore, home, false);

        WeekSchedule weekSchedule = new WeekSchedule();
        WeekSchedule weekSchedule2 = new WeekSchedule();


        home.getSchedule().add(weekSchedule);
        away.getSchedule().add(weekSchedule2);

//
        home.getSchedule().get(week).setYourScore(homeScore);
        home.getSchedule().get(week).setOppScore(awayScore);
        home.getSchedule().get(week).setOpp(away);
        home.getSchedule().get(week).setWeek(week);
        home.getSchedule().get(week).setHome(true);
        away.getSchedule().get(week).setOppScore(homeScore);
        away.getSchedule().get(week).setYourScore(awayScore);
        away.getSchedule().get(week).setOpp(home);
        away.getSchedule().get(week).setWeek(week);
        away.getSchedule().get(week).setHome(false);

        home.getSchedule().get(week).setWasPlayed(true);
        away.getSchedule().get(week).setWasPlayed(true);



//        printTitleResults(home,away);
        return results;
//        printWeeklyRankings();
    }


    private void checkInjuredPlayers() {
        for (int i = 0; i < injuredPlayers.size(); i++) {
            Player player = injuredPlayers.get(i);
            player.heal();
            if (!player.isInjured()) {
                injuredPlayers.remove(player);
            }
        }
    }


    private void setInjuries(Team team) {
        int wasInjury = rand.nextInt(100);
        if (wasInjury < 8) {
            DepthChart dc = team.getDepthChart();
            Player QB;
            Player RB;
            Player WR;
            Player TE;
            Player OL;
            Player DL;
            Player LB;
            Player DB;
            Player K;
            Player[] arr = {QB = dc.getQB().get(0), RB = dc.getRB().get(0), WR = dc.getWR().get(0),
                    TE = dc.getTE().get(0),
                    OL = dc.getOL().get(0),

                    DL = dc.getDL().get(0),
                    LB = dc.getLB().get(0),
                    DB = dc.getDB().get(0),

                    K = dc.getK().get(0),
            };
            int selectedPlayer = rand.nextInt(arr.length);
            Player selPly = arr[selectedPlayer];

            int severity = rand.nextInt(30);
            int length = rand.nextInt(12);

            selPly.setInjury(length, severity);
            injuredPlayers.add(selPly);
//            System.out.println(selPly.getName() + selPly.getTeam() + selPly.getNumber() + length);

        }
    }


    public ArrayList<Team> setRankings() {
        setWeek1Rankings();
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                return t2.getWins() - t1.getWins();
            }
        });
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            team.setRank(i + 1);
        }
        return teams;
    }

    public ArrayList<Team> setWeek1Rankings() {
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                return t2.getOVRrating() - t1.getOVRrating();
            }
        });
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            team.setRank(i + 1);
        }

        return teams;
    }

    public ArrayList<Team> sortTeamsByName() {
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return teams;
    }


    public String printWeeklyRankings() {
        Team team;
        String string = "";
        if (week == 0) {
            string += "\n" + ("PRESEASON RANKINGS");
        } else {
            string += "\n" + ("WEEK " + week + " RESULTS");
        }
        string += "\n" + ("******************************************************************");
        for (int i = 0; i < 25; i++) {
            team = teams.get(i);
            string += "\n" + ("#" + (i + 1) + " " + team.getName() + " RECORD: " + team.getWins() + "-" + team.getLosses() + team.printLastGameResult());
        }
        return string;

    }


    public void printTeamInfo(ArrayList<Team> teams, String teamName) {
        Team team;
        team = queryTeams(teams, teamName);
        if (team != null) {
            System.out.println(team.getName() + " : " + team.getWins() + "-" + team.getLosses());
            System.out.println("Rank #" + team.getRank());
            System.out.println("Last Game Result: " + team.printLastGameResult());
            System.out.println("Schedule: ");
            ArrayList<Game> games = team.getPreviousGames();
            team.printPreviousSeasons();
            team.printPreviousGames(games);
            team.printRoster();
        } else {
            System.out.println("Try again");
        }
    }

    public Team queryTeams(ArrayList<Team> teams, String name) {
        Team team;
        for (int i = 0; i < teams.size(); i++) {
            team = teams.get(i);
            if (name.equals(team.getName())) {
                return team;
            }
        }
        System.out.println("Team not found");
        return null;
    }


    public void setBowls() {
        System.out.println();
        System.out.println("NATIONAL TITLE GAME RESULTS:");
        setRankings();
        Team team1 = teams.get(0);
        Team team2 = teams.get(1);
        PlayGame title = new PlayGame();
        title.playGame(team1, team2, true);
        title.displayResults(team1, team2);


    }

//    public void generateSchedule() {
//        for (int i = 0; i < teams.size(); i++) {
//            Team team = teams.get(i);
//            while (team.getFullSchedule().size() != 10) {
//                team.addScheduleOpponent(null);
//            }
//        }
//        int t = 0;
//        int n = 0;
//        for (int i = 0; i < teams.size(); i++) {
//            Team team = teams.get(i);
//            t++;
//            if (!team.getFullSchedule().contains(null)) {
//                System.out.println("full");
//            } else {
//                while (team.getFullSchedule().contains(null)) {
//                    int j = team.getFullSchedule().indexOf(null);
//
//                    int oppTeamId = rand.nextInt(teams.size());
//                    while (teams.get(oppTeamId) == team || team.getFullSchedule().contains(teams.get(oppTeamId)) || teams.get(oppTeamId).getFullSchedule().get(j) != null) {
//                        oppTeamId = rand.nextInt(teams.size());
//                    }
//                    System.out.println(team + " Week " + j + teams.get(oppTeamId));
//                    team.getFullSchedule().set(j, teams.get(oppTeamId));
//                    WeekSchedule weekSchedule = new WeekSchedule();
//                    weekSchedule.setOpp(teams.get(oppTeamId));
//                    weekSchedule.setWeek(j);
//                    int home = rand.nextInt(2);
//                    System.out.println(home);
//                    boolean isHome = false;
//                    if (home == 1) {
//                        isHome = true;
//                    }
//                    weekSchedule.setHome(isHome);
//                    team.getSchedule().add(weekSchedule);
//
//
//                    WeekSchedule weekSchedule1 = new WeekSchedule();
//                    teams.get(oppTeamId).getFullSchedule().set(j, team);
//                    weekSchedule1.setOpp(team);
//                    weekSchedule1.setWeek(j);
//                    if (isHome) {
//                        weekSchedule1.setHome(false);
//                    } else {
//                        weekSchedule1.setHome(true);
//                    }
//                    teams.get(oppTeamId).getSchedule().add(weekSchedule1);
//                    System.out.println(weekSchedule);
//                    System.out.println(weekSchedule1);
//                    n++;
//                    System.out.println(n);
//                    System.out.println(t);
//                    System.out.println(teams.get(i).getName() + teams.get(i).getFullSchedule().size() + teams.get(i).getFullSchedule());
//                }
//            }
//        }
//        for (int i = 0; i < teams.size(); i++) {
//            System.out.println(teams.get(i).getName() + teams.get(i).getFullSchedule().size() + teams.get(i).getFullSchedule());
//        }
//        System.out.println(teams.get(0).getName() + teams.get(0).getFullSchedule().size() + teams.get(0).getFullSchedule());
//
//        System.out.println(teams.get(0).getSchedule());
//
//
//    }

    public void generateSchedule() {

        setTeamIndex(teams);
        teams = reorderTeamIndex(teams);
        if (teams.size() % 2 != 0) {
            Team team = new Team("Bye");
            teams.add(team);
        }

        int halfSize = teams.size() / 2;
        ArrayList<Team> teams1 = new ArrayList<>();
        ArrayList<Team> teams2 = new ArrayList<>();

        for (int i = 0; i < halfSize; i++) {
            teams1.add(teams.get(i));
        }
        for (int i = halfSize; i < teams.size(); i++) {
            teams2.add(teams.get(i));
        }

//        System.out.println(teams1);
//        System.out.println(teams2);
        for (int i = 0; i < seasonLength; i++) {
            Team temp = teams2.remove(0);
            teams2.add(temp);
            for (int j = 0; j < teams2.size(); j++) {
                teams1.get(j).addScheduleOpponent(teams2.get(j));
                teams2.get(j).addScheduleOpponent(teams1.get(j));

                WeekSchedule weekSchedule = new WeekSchedule();
                weekSchedule.setOpp(teams2.get(j));
                weekSchedule.setWeek(i);
                int home = rand.nextInt(2);
//                System.out.println(home);
                boolean isHome = false;
                if (home == 1) {
                    isHome = true;
                }
                weekSchedule.setHome(isHome);
                teams1.get(j).getSchedule().add(weekSchedule);


                WeekSchedule weekSchedule1 = new WeekSchedule();
                weekSchedule1.setOpp(teams1.get(j));
                weekSchedule1.setWeek(i);
                if (isHome) {
                    weekSchedule1.setHome(false);
                } else {
                    weekSchedule1.setHome(true);
                }
                teams2.get(j).getSchedule().add(weekSchedule1);

            }
        }

        sortTeamsByName();
//        for (int i = 0; i < teams.size(); i++) {
//            System.out.println(teams.get(i).getName() + teams.get(i).getFullSchedule().size() + teams.get(i).getFullSchedule());
//        }
//
//        System.out.println(teams.get(0).getName() + teams.get(0).getFullSchedule().size() + teams.get(0).getFullSchedule());
//
//        System.out.println(teams.get(0).getSchedule());
//
    }

}
