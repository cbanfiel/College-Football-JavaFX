package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.gameplay.Gameplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Menu {
    private static TeamData teamData;
    private static ArrayList<Team> teams;
    private static ArrayList<Team> proteams;
    private static Team team;
    private static Scanner scanner = new Scanner(System.in);
    Sliders sliders = new Sliders();
    private static Franchise currentFranchise;
    private static ArrayList<Team> customLeagueTeams;

    @FXML
    Button createPlayerButton = new Button();

    @FXML
    Button createTeamButton = new Button();

    @FXML
    AnchorPane rootPane;

    @FXML
    Button closeButton = new Button();


    public static ArrayList<Team> getTeams() {
        return teams;
    }

    public void initialize(){
            if(teamData==null){
                teamData=new TeamData();
            }
            if(teams==null){
                NCAARosters.readNames();
                teams = teamData.load();
                System.out.println(NCAARosters.firstNames[1]);
            }
//
//            Team team1 = teams.get(1);
//            Team team2 = teams.get(1);
//
//
//        Gameplay gameplay = new Gameplay(team1,team2);
//        System.out.println(team1 + " " + team2);
//        System.out.println(team1.getOffensivePlaybook().getPlays().get(0).getPlayType());

//        gameplay.info();


    }

    public void clicked1(ActionEvent e) throws IOException {
        Parent home_page_parent =FXMLLoader.load(getClass().getResource("DynastyMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void loadDynastyMode(ActionEvent event) throws IOException{
        if(currentFranchise==null){
            currentFranchise = new Franchise(sliders,teams);
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DynastyMenu.fxml"));
        loader.load();
        DynastyController dynastyController = loader.getController();
        dynastyController.setFranchise(currentFranchise);

        //Grab Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));


    }

    public void startPlayGame(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TeamSelectionScreen.fxml"));
        loader.load();
        TeamSelectionScreenController teamSelectionScreenController = loader.getController();

        //Grab Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));



    }
    public void clicked2(ActionEvent e) throws IOException {
        Parent home_page_parent =FXMLLoader.load(getClass().getResource("PlaySeasonMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    public void clicked3(ActionEvent e) throws IOException {
        Parent home_page_parent
                =FXMLLoader.load(getClass().getResource("TeamInfoMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    public void clicked4(ActionEvent e) throws IOException {
        Parent home_page_parent
                =FXMLLoader.load(getClass().getResource("CreateAPlayer.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    public void clicked5(ActionEvent e) throws IOException {
        Parent home_page_parent
                =FXMLLoader.load(getClass().getResource("CreateATeamMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void exit(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }






    public void print() {
        int choice;
        boolean quit = false;
        while (!quit) {
            System.out.println();
            System.out.println("Welcome to College Football 2019");
            System.out.println("********************************************");
            System.out.println("Select an option");
            System.out.println("1. Cycle Through Teams");
            System.out.println("2. Display All Teams");
            System.out.println("3. Play Game");
            System.out.println("4. Play Season");
            System.out.println("5. Sim 100 Games");
            System.out.println("6. Look At Team Roster");
            System.out.println("7. Create a Player");
            System.out.println("8. Create a Team");
            System.out.println("9. Start Franchise");
            System.out.println("10. Edit Roster");
            System.out.println("11. Edit Sliders");
            System.out.println("12. To Quit");


            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Cycle through teams");
                    cycleThroughTeams();
                    break;
                case 2:
                    printCurrentTeams();
                    break;
                case 3:
                    playGame();
                    break;
                case 4:
                    playSeason();
                    break;
                case 5:
                    play100();
                    break;
                case 6:
                    displayRos();
                    break;
                case 7:
                    createPlayer();
                    break;
                case 8:
                    createTeam();
                    break;
                case 9:
//                    startFranchise();
                    break;
                case 10:
                    editRosterMenu();
                    break;
                case 11:
                    setSliders();
                    break;
                case 12:
                    System.out.println("Quitting.....");
                    quit = true;
                    break;

            }
        }


    }

//    public void startFranchise() {
//        Franchise franchise = new Franchise(sliders);
//        franchise.uI(teams);
//    }

    public void printCurrentTeams() {
        setRankings(teams);
        for (int i = 0; i < 25; i++) {
            team = teams.get(i);
            System.out.println("#" + (i + 1) + " " + team.getName());
        }

        for (int i = 25; i < teams.size(); i++) {
            team = teams.get(i);
            System.out.println(team.getName());
        }
    }

    public Team cycleThroughTeams() {
        setRankings(teams);
        Team selectedTeam = teams.get(0);
        System.out.println("1 to go back 2 to go forward 3 to quit");
        System.out.print("\r#" + selectedTeam.getRank() + " " + selectedTeam.getName());
        int choice = 1;
        int teamIndex = 0;
        while (choice == 1 || choice == 2) {
            choice = scanner.nextInt();
            selectedTeam = teams.get(teamIndex);
            switch (choice) {
                case 1: {
                    teamIndex--;
                    if (teamIndex < 0) {
                        teamIndex = teams.size() - 1;
                    }
                    selectedTeam = teams.get(teamIndex);
                    System.out.print("\r#" + selectedTeam.getRank() + " " + selectedTeam.getName());
                    break;
                }
                case 2: {
                    teamIndex++;
                    if (teamIndex > teams.size() - 1) {
                        teamIndex = 0;
                    }
                    selectedTeam = teams.get(teamIndex);
                    System.out.print("\r#" + selectedTeam.getRank() + " " + selectedTeam.getName());
                    break;
                }
                case 3: {
                    System.out.println("Back to menu");
                    break;
                }
            }
        }
        return selectedTeam;
    }

    public ArrayList<Team> setRankings(ArrayList<Team> teams) {
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

    public void playGame() {
        Team team1 = null;
        Team team2 = null;
        String input;
        while (team1 == null) {
            System.out.println("Enter Home Team:");
            scanner.nextLine();
            input = scanner.nextLine();
            team1 = queryTeams(input);
        }
        while (team2 == null) {
            System.out.println("Enter Away Team:");
            input = scanner.nextLine();
            team2 = queryTeams(input);
        }
        PlayGame newGame = new PlayGame();
        newGame.uI(team1, team2);

    }

    public Team queryTeams(String name) {
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

    public Player queryPlayers(int number, Team selectedTeam) {
        Player player;
        for (int i = 0; i < selectedTeam.getTeamRoster().size(); i++) {
            player = selectedTeam.getTeamRoster().get(i);
            if (number == (player.getNumber())) {
                return player;
            }
        }
        System.out.println("Player not found");
        return null;
    }

    public void playSeason() {
        if (teams.size() % 2 > 0) {
            System.out.println("You must add another team to play a season");
        } else {
            PlaySeason newSeason = new PlaySeason(teams);
        }
    }

    public void play100() {
        Team team1 = null;
        Team team2 = null;
        String input;
        while (team1 == null) {
            System.out.println("Enter Home Team:");
            scanner.nextLine();
            input = scanner.nextLine();
            team1 = queryTeams(input);
        }
        while (team2 == null) {
            System.out.println("Enter Away Team:");
            input = scanner.nextLine();
            team2 = queryTeams(input);
        }
        PlayGame newGame = new PlayGame();
        newGame.sim100(team1, team2);
    }

    public void displayRos() {
        String input;
        System.out.println("Enter team name: ");
        scanner.nextLine();
        input = scanner.nextLine();
        Team team = queryTeams(input);
        team.printRoster();
    }

    public void createPlayer() {
        scanner.nextLine();
        Team team = null;
        while (team == null) {
            System.out.println("Enter Team Name");
            String teamName = scanner.nextLine();
            team = queryTeams(teamName);
        }
        System.out.println("Enter Player Name");
        String name = scanner.nextLine();
        System.out.println("Enter Number ");
        int number = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Position");
        String pos = scanner.nextLine();
        System.out.println("Enter rating");
        int rating = scanner.nextInt();
        scanner.nextLine();
        team.createPlayer(name, rating, pos, number);
        System.out.println(name + " Successfully Added to: " + team.getName());


    }

    public void createTeam() {
        scanner.nextLine();
        System.out.println("Enter Team Name");
        String name = scanner.nextLine();
        System.out.println("Enter Team Rating");
        int rating = scanner.nextInt();
        scanner.nextLine();
        Team team = new Team(name, rating);
        teams.add(team);
        if (teams.size() % 2 > 0) {
            System.out.println("Keep in mind you must add another team to play a season");
        }
    }

    public void editRosterMenu() {
        String input;
        boolean quit = false;
        System.out.println("EDIT PLAYER");
        System.out.println("Enter team name: ");
        scanner.nextLine();
        input = scanner.nextLine();
        Team team = queryTeams(input);
        team.printRoster();
        System.out.println();
        System.out.println();
        System.out.println("Select Player by Entering there Jersey Number");
        int num;
        num = scanner.nextInt();
        Player player = queryPlayers(num, team);
        while (!quit) {
            System.out.println(player.getYear() + " " + player.getPosition() + " #" + player.getNumber() + " " + player.getName() + " Selected: " + player.getRating() + "OVR");
            System.out.println("1. Make Starter");
            System.out.println("2. Edit Rating");
            System.out.println("3. Edit Name");
            System.out.println("4. Edit Year");
            System.out.println("5. Back To Menu");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    player.setDepthchartpos(-1);
                    team.reorderBasedOnPlayerDepthChartPos();
                    System.out.println(player.getName() + " is now the starting " + player.getPosition() + " on " + team.getName());
                    break;
                case 2:
                    System.out.println("Enter Rating:");
                    int newRat = scanner.nextInt();
                    player.setRating(newRat);
                    break;
                case 3:
                    System.out.println("Enter Name:");
                    String newName = scanner.nextLine();
                    player.setName(newName);
                    break;
                case 4:
                    System.out.println("Enter Year:");
                    String newYr = scanner.nextLine();
                    player.setYear(newYr);
                    break;
                case 5:
                    System.out.println("Quitting");
                    quit = true;
                    break;

            }
        }

        print();
    }

    public void teamHub(Team team) {
        System.out.println("Welcome to your TEAM HUB");
        int choice;
        boolean quit = false;
        while (!quit) {
            System.out.println("1. View Current Roster");
            System.out.println("2. View Previous Seasons");
            System.out.println("3. View graduated players");
            System.out.println("4. Back");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    ArrayList<Player> graduatedPlayers = team.getGraduatedPlayers();
                    for (int i = 0; i < graduatedPlayers.size(); i++) {
                        Player player = graduatedPlayers.get(i);
                        System.out.println(player.getYear() + " " + player.getPosition() + " #" + player.getNumber() + " " + player.getName() + " Selected: " + player.getRating() + "OVR");
                    }
                    break;
                case 4:
                    System.out.println("back to menu...");
                    quit = true;
                    break;
            }
        }

    }

    public void setSliders() {
        System.out.println("Edit Sliders");
        int choice;
        boolean quit = false;
        while (!quit) {
            System.out.println("Set season length");
            System.out.println("Set drive time");
            System.out.println("Set touchdown slider");
            System.out.println("Set drive result slider");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Current Slider: " + sliders.getSeasonLength());
                    System.out.println("Set slider:");
                    int slider = scanner.nextInt();
                    sliders.setSeasonLength(slider);
                    break;
                case 2:
                    System.out.println("Current Slider: " + sliders.getDriveTime());
                    System.out.println("Set slider:");
                    slider = scanner.nextInt();
                    sliders.setDriveTime(slider);
                    break;
                case 3:
                    System.out.println("Current Slider: " + sliders.getTouchdownSlider());
                    System.out.println("Set slider:");
                    slider = scanner.nextInt();
                    sliders.setTouchdownSlider(slider);
                    break;
                case 4:
                    System.out.println("Current Slider: " + sliders.getDriveResultSlider());
                    System.out.println("Set slider:");
                    slider = scanner.nextInt();
                    sliders.setDriveResultSlider(slider);
                    break;
                case 5:
                    quit=true;
                    break;
            }


        }
print();
    }
}



