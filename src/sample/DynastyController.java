package sample;

import animatefx.animation.Pulse;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.util.function.Predicate;

public class DynastyController extends Menu {
    //    ArrayList teams = new TeamData().load();
    Team selectedTeam;
    Sliders sliders = new Sliders();
    Franchise franchise;
    boolean advance = false;

    @FXML
    TextArea teamRankings = new TextArea();

    @FXML
    ListView<Team> seasonTeamsListView = new ListView<>();

    @FXML
    Label teamInfoLabel = new Label();

    @FXML
    ListView<WeekSchedule> previousGamesListView = new ListView<>();

    @FXML
    ListView<Player> teamRosterListView = new ListView<Player>();

    @FXML
    ListView<Team> top25ListView = new ListView<>();

    @FXML
    Button dynastyButton = new Button();

    @FXML
    Label yearLabel = new Label();

    @FXML
    TextArea playerInfoTextArea = new TextArea();

    @FXML
    TextArea teamHistoryTextArea = new TextArea();

    @FXML
    ImageView teamLogo = new ImageView();

    @FXML
    ImageView playerFace = new ImageView();

    @FXML
    ChoiceBox teamFocus;

    @FXML
    Label titleLabel = new Label();

    @FXML
    CheckBox strippers = new CheckBox();

    @FXML
    CheckBox payRecruits = new CheckBox();

    @FXML
    CheckBox drugs = new CheckBox();

    @FXML
    Label underSanctions = new Label();

    @FXML
    Label prestigeLabel = new Label();

    @FXML
    Label financesLabel = new Label();

    @FXML
    Label strengthOfSchedule = new Label();

    @FXML
    ImageView scheduleTeam1 = new ImageView();
    @FXML
    ImageView scheduleTeam2 = new ImageView();
    @FXML
    ImageView scheduleTeam3 = new ImageView();
    @FXML
    ImageView scheduleTeam4 = new ImageView();
    @FXML
    ImageView scheduleTeam5 = new ImageView();
    @FXML
    ImageView scheduleTeam6 = new ImageView();
    @FXML
    ImageView scheduleTeam7 = new ImageView();
    @FXML
    ImageView scheduleTeam8 = new ImageView();
    @FXML
    ImageView scheduleTeam9 = new ImageView();
    @FXML
    ImageView scheduleTeam10 = new ImageView();
    @FXML
    ImageView scheduleTeam11 = new ImageView();
    @FXML
    ImageView scheduleTeam12 = new ImageView();

    @FXML
    Label week1 = new Label();
    @FXML
    Label week2 = new Label();
    @FXML
    Label week3 = new Label();
    @FXML
    Label week4 = new Label();
    @FXML
    Label week5 = new Label();
    @FXML
    Label week6 = new Label();
    @FXML
    Label week7 = new Label();
    @FXML
    Label week8 = new Label();
    @FXML
    Label week9 = new Label();
    @FXML
    Label week10 = new Label();
    @FXML
    Label week11 = new Label();
    @FXML
    Label week12 = new Label();
    @FXML
    Label teamInfoLabel2 = new Label();

    @FXML
    Label newsfeed1 = new Label();
    @FXML
    ImageView newsfeed1image1 = new ImageView();
    @FXML
    ImageView newsfeed1image2 = new ImageView();

    @FXML
    ImageView newsfeedNumber1Image = new ImageView();

    @FXML
    Label newsfeedNumber1 = new Label();

    @FXML
    ImageView scheduleTeamLogo = new ImageView();

    @FXML
    Tab espnTab = new Tab();

    ImageView espnLogo = new ImageView();

    @FXML
    Label newsfeedSelectedTeamLabel = new Label();

    @FXML
    ImageView newsfeedSelectedTeamImage = new ImageView();

    @FXML
    ImageView newsfeedSelectedTeamOppImage = new ImageView();


    @FXML
    Label rosterTabHeight = new Label();
    @FXML
    Label rosterTabWeight = new Label();
    @FXML
    Label rosterTabClass = new Label();

    @FXML
    Label rosterTabTeamName = new Label();
    @FXML
    Label rosterTabPlayerName = new Label();
    @FXML
    ImageView rosterTabTeamLogo = new ImageView();
    @FXML
    ImageView rosterTabPlayerImage = new ImageView();
    @FXML
    Label rosterTabOverall = new Label();
    @FXML
    Label playerRatings = new Label();

    // ROSTER TABLE VIEW
    @FXML
    TableView rosterTableView = new TableView();
    @FXML
    TableColumn<String, Player> nameColumn = new TableColumn<>();
    @FXML
    TableColumn<String, Player> positionColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> ovrColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> passAccuracyColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> passStrengthColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> carryColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> blockColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> kickPowerColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> kickAccuracyColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> defenseColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> recieveColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> strengthColumn = new TableColumn<>();
    @FXML
    TableColumn<Integer, Player> speedColumn = new TableColumn<>();
    @FXML
    TableView statsTableView = new TableView();
    @FXML
    TableView globalStatsTable = new TableView();


    Alert seasonOver = new Alert(Alert.AlertType.INFORMATION);

    Alert underSanctionsAlert = new Alert(Alert.AlertType.INFORMATION);

    DialogPane dialogPane = seasonOver.getDialogPane();

    Player player;


    private Predicate<Team> top25only;

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
        initialize(this.franchise);
    }

    public void initialize(Franchise franchise) {
        this.franchise = franchise;

        teamFocus.setItems(FXCollections.observableArrayList("Recruiting", "Training", "Coaching"));
        yearLabel.setText("YEAR " + franchise.getYear());
        startSeason();
        manageDialogPane();
        ObservableList<Team> teamObservableList = FXCollections.observableArrayList(franchise.franchise.sortTeamsByName());
        seasonTeamsListView.setItems(teamObservableList);
        seasonTeamsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        seasonTeamsListView.getSelectionModel().selectFirst();
        selectedTeam = seasonTeamsListView.getSelectionModel().getSelectedItem();
//        handleFilteredList();
        teamInfoLabel.setText(selectedTeam.simpleToString());
        ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
        teamRosterListView.setItems(rosterObservableList);
        teamLogo.setImage(selectedTeam.getLogo());
        teamLogo.setFitWidth(60);
        teamLogo.setFitHeight(60);
        viewFocus();
        prestigeLabel.setText("Prestige: " + selectedTeam.getTeamPrestige());
        titleLabel.setText(selectedTeam.getChampionshipsString());
        financesLabel.setText("Finances: $" + selectedTeam.getMoney());
        ObservableList<WeekSchedule> previousGamesObservableList = FXCollections.observableArrayList(selectedTeam.getSchedule());
        previousGamesListView.setItems(previousGamesObservableList);
        scheduleTeam1.setImage(selectedTeam.getSchedule().get(0).getOpp().getLogo());
        strengthOfSchedule.setText("STRENGTH OF SCHEDULE: " + selectedTeam.getScheduleStrength());
        espnLogo.setImage(new Image("file:///C:/Users/chadb/IdeaProjects/NCAAFootball19GUILastTime/src/sample/img/espn menu/espn.gif"));
        espnLogo.setFitWidth(60);
        espnLogo.setFitHeight(15);
        espnTab.setGraphic(espnLogo);
        handleNewsFeed();
        handleRosterTab();

        top25only = new Predicate<Team>() {
            @Override
            public boolean test(Team team) {
                return team.getRank() <= 25;
            }
        };
        handleFilteredList();
        handleTableInit();
    }


    public void handleClickListView() {
        selectedTeam = seasonTeamsListView.getSelectionModel().getSelectedItem();
        teamInfoLabel.setText(selectedTeam.simpleToString());
        ObservableList<WeekSchedule> previousGamesObservableList = FXCollections.observableArrayList(selectedTeam.getSchedule());
        previousGamesListView.setItems(previousGamesObservableList);
        ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
        teamRosterListView.setItems(rosterObservableList);
        playerInfoTextArea.setText("");
        teamHistoryTextArea.setText(selectedTeam.printPreviousSeasons());
        teamLogo.setImage(selectedTeam.getLogo());
        teamLogo.setFitWidth(60);
        teamLogo.setFitHeight(60);
        viewFocus();
        titleLabel.setText(selectedTeam.getChampionshipsString());
        prestigeLabel.setText("Prestige: " + selectedTeam.getTeamPrestige());
        financesLabel.setText("Finances: $" + selectedTeam.getMoney());
        strengthOfSchedule.setText("STRENGTH OF SCHEDULE: " + selectedTeam.getScheduleStrength());
        handleRosterTab();


        cheatsInitialize();
//        isUnderSanctions();
    }

    public void handleTop25ClickListView() {
        selectedTeam = top25ListView.getSelectionModel().getSelectedItem();
        teamInfoLabel.setText(selectedTeam.simpleToString());
        ObservableList<WeekSchedule> previousGamesObservableList = FXCollections.observableArrayList(selectedTeam.getSchedule());
        previousGamesListView.setItems(previousGamesObservableList);
        ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
        teamRosterListView.setItems(rosterObservableList);
        playerInfoTextArea.setText("");
        teamHistoryTextArea.setText(selectedTeam.printPreviousSeasons());
        teamLogo.setImage(selectedTeam.getLogo());
        teamLogo.setFitWidth(60);
        teamLogo.setFitHeight(60);
        viewFocus();
        titleLabel.setText(selectedTeam.getChampionshipsString());
        prestigeLabel.setText("Prestige: " + selectedTeam.getTeamPrestige());
        financesLabel.setText("Finances: $" + selectedTeam.getMoney());
        strengthOfSchedule.setText("STRENGTH OF SCHEDULE: " + selectedTeam.getScheduleStrength());
        handleRosterTab();


        cheatsInitialize();
//        isUnderSanctions();
    }

    public void handleClickPlayer() {
        player = teamRosterListView.getSelectionModel().getSelectedItem();
        playerInfoTextArea.setText(player.playerInfo());
        playerFace.setImage(player.getFace());
        playerFace.setFitHeight(75);
        playerFace.setFitWidth(60);
    }

    public void startSeason() {
        franchise.franchise.setRankings();
        teamRankings.setText(franchise.franchise.printWeeklyRankings());
    }

    public void simWeek() {
        if (franchise.franchise.playWeek()) {
            globalStatsTable();
            handleScheduleLogos();
            handleNewsFeed();
            teamRankings.setText(franchise.franchise.printWeeklyRankings());
            teamInfoLabel.setText(selectedTeam.simpleToString());
            ObservableList<WeekSchedule> previousGamesObservableList = FXCollections.observableArrayList(selectedTeam.getSchedule());
            previousGamesListView.setItems(previousGamesObservableList);
            ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
            teamRosterListView.setItems(rosterObservableList);
            handleFilteredList();
            ObservableList<Team> teamObservableList = FXCollections.observableArrayList(franchise.franchise.sortTeamsByName());
            seasonTeamsListView.setItems(teamObservableList);
            strengthOfSchedule.setText("STRENGTH OF SCHEDULE: " + selectedTeam.getScheduleStrength());


        } else if (!franchise.franchise.playWeek() && !advance) {
            String titleResults = franchise.franchise.playBowls();
            teamRankings.setText(franchise.franchise.printWeeklyRankings());
            teamInfoLabel.setText(selectedTeam.simpleToString());
            ObservableList<WeekSchedule> previousGamesObservableList = FXCollections.observableArrayList(selectedTeam.getSchedule());
            previousGamesListView.setItems(previousGamesObservableList);
            ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
            teamRosterListView.setItems(rosterObservableList);
//            Causes better compareable team to still be #1 even if lost champ game
//            handleFilteredList();
            handleScheduleLogos();
            ObservableList<Team> teamObservableList = FXCollections.observableArrayList(franchise.franchise.sortTeamsByName());
            seasonTeamsListView.setItems(teamObservableList);
            seasonOver.setTitle("Season Over");
            seasonOver.setHeaderText("National Championship Game: \n" + titleResults);
            seasonOver.setContentText(titleResults);
            seasonOver.showAndWait();
            advance = true;
            teamInfoLabel.setText("SEASON OVER");
            dynastyButton.setText("Advance To Next Season");
            titleLabel.setText(selectedTeam.getChampionshipsString());
            simWeek();

        } else {
            dynastyButton.setText("Sim To Next Week");
            franchise.graduatePlayers();
            franchise.advanceYear();
            advance = false;
            if (selectedTeam.isSanctions()) {
                underSanctionsAlert.setHeaderText("You are under Sanctions");
                underSanctionsAlert.setContentText(selectedTeam.getName() + " is under sanctions for cheating!");
                underSanctionsAlert.showAndWait();
                cheatsInitialize();
            }
            cheatsInitialize();
            teamRankings.setText(franchise.franchise.printWeeklyRankings());
            teamInfoLabel.setText(selectedTeam.simpleToString());
            ObservableList<WeekSchedule> previousGamesObservableList = FXCollections.observableArrayList(selectedTeam.getSchedule());
            previousGamesListView.setItems(previousGamesObservableList);
            ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
            teamRosterListView.setItems(rosterObservableList);
            handleFilteredList();
            ObservableList<Team> teamObservableList = FXCollections.observableArrayList(franchise.franchise.sortTeamsByName());
            seasonTeamsListView.setItems(teamObservableList);
            yearLabel.setText("YEAR " + franchise.getYear());
            teamHistoryTextArea.setText(selectedTeam.printPreviousSeasons());
            financesLabel.setText("Finances: $" + selectedTeam.getMoney());
            strengthOfSchedule.setText("STRENGTH OF SCHEDULE: " + selectedTeam.getScheduleStrength());
            handleScheduleLogos();
            handleNewsFeed();
            handleRosterTab();



        }
//        System.out.println(inSeason);
    }

    public void handleFilteredList() {
        ObservableList<Team> top25ObservableList = FXCollections.observableArrayList(franchise.franchise.setRankings());
        FilteredList<Team> top25teams = new FilteredList<>(top25ObservableList);
        top25teams.setPredicate(top25only);
        top25ListView.setItems(top25teams);

    }

    public void simToEnd() {
        while (franchise.franchise.playWeek()) {
            simWeek();
        }
    }

    public void backToMenu(ActionEvent e) throws IOException {
//        Parent home_page_parent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//        Scene home_page_scene = new Scene(home_page_parent);
//        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//        app_stage.hide(); //optional
//        app_stage.setScene(home_page_scene);
//        app_stage.show();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainMenu.fxml"));
        loader.load();
        Menu menu = loader.getController();

        //Grab Stage
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));

    }

    public void manageDialogPane() {
        dialogPane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Image image = new Image("https://i.pinimg.com/originals/7d/d6/ea/7dd6eacb5d84f770cb48e2b51f16b11f.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(100);
        seasonOver.setGraphic(imageView);
    }

    public void handleChangeFocus() {
        selectedTeam.getTeamFocus().changeTeamFocus(teamFocus.getSelectionModel().getSelectedIndex());
//        System.out.println(selectedTeam.getTeamFocus());
//        System.out.println(teamFocus.getValue());

        selectedTeam.setHiresStrippers(strippers.isSelected());
        selectedTeam.setPaysRecruits(payRecruits.isSelected());
        selectedTeam.setGivesPlayersPeds(drugs.isSelected());
        cheatsInitialize();
    }

    public void viewFocus() {
        if (selectedTeam.getTeamFocus().isTraining()) {
            teamFocus.setValue("Training");
        } else if (selectedTeam.getTeamFocus().isRecruiting()) {
            teamFocus.setValue("Recruiting");
        } else if (selectedTeam.getTeamFocus().isCoaching()) {
            teamFocus.setValue("Coaching");
        }

    }

    public void sim20years() {
        for (int i = 0; i < (5 * 14); i++) {
            simWeek();
        }
    }


    public void cheatsInitialize() {
        if (selectedTeam.isSanctions()) {
            underSanctions.setText("Under Sanctions For " + selectedTeam.getSanctionLength() + " Years");
            strippers.setDisable(true);
            payRecruits.setDisable(true);
            drugs.setDisable(true);
        } else {
            underSanctions.setText("");
            strippers.setDisable(false);
            payRecruits.setDisable(false);
            drugs.setDisable(false);
        }
        strippers.setSelected(selectedTeam.isHiresStrippers());
        payRecruits.setSelected(selectedTeam.isPaysRecruits());
        drugs.setSelected(selectedTeam.isGivesPlayersPeds());
    }

    public void handleRedshirt() {
        Player selected = teamRosterListView.getSelectionModel().getSelectedItem();
        if (selected.isWasRedshirted()) {
        } else {
            selected.setRedshirt();
        }
        selectedTeam.autoReorderDepthChart();
        ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
        teamRosterListView.setItems(rosterObservableList);

    }

    public void handleUpgrade() {
        if (selectedTeam.getMoney() >= 50000) {
            selectedTeam.setMoney(selectedTeam.getMoney() - 50000);
            player.setRating(player.getRating() + 1);
            ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
            teamRosterListView.setItems(rosterObservableList);
            handleClickPlayer();
            financesLabel.setText("Finances: $" + selectedTeam.getMoney());
        } else {
            //NOt enough money
        }
    }

    public void handlePrestigeUpgrade() {
        if (selectedTeam.getMoney() >= 500000 && selectedTeam.getTeamPrestige() < 5) {
            selectedTeam.setMoney(selectedTeam.getMoney() - 500000);
            selectedTeam.setTeamPrestige(selectedTeam.getTeamPrestige() + 1);
            financesLabel.setText("Finances: $" + selectedTeam.getMoney());
            prestigeLabel.setText("Prestige: " + selectedTeam.getTeamPrestige());


        } else {
            //NOt enough money
        }
    }

    public void handleExpectationsMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ExpectationsMenu.fxml"));
        loader.load();
        ExpectationsMenu expectationsMenu = loader.getController();
        expectationsMenu.setSelectedTeam(selectedTeam);
        Stage stage = new Stage();
        stage.setTitle("Check");
        Parent parent = loader.getRoot();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(parent));
        stage.showAndWait();


    }

    public void handleUpgradesMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpgradeMenu.fxml"));
        loader.load();
        UpgradeMenu upgradeMenu = loader.getController();
        upgradeMenu.setSelectedTeam(selectedTeam);
        Stage stage = new Stage();
        stage.setTitle("Check");
        Parent parent = loader.getRoot();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(parent));
        stage.showAndWait();


    }

    public void animate() {
        new Pulse(teamLogo).playOnFinished(new Pulse(teamLogo)).play();

    }

    public void handleClickScheduleView(){
        handleScheduleLogos();
    }



    public void handleScheduleLogos() {
        teamInfoLabel2.setText(selectedTeam.simpleToString());
        scheduleTeamLogo.setImage(selectedTeam.getLogo());
        scheduleTeam1.setImage(selectedTeam.getSchedule().get(0).getOpp().getLogo());
        scheduleTeam2.setImage(selectedTeam.getSchedule().get(1).getOpp().getLogo());
        scheduleTeam3.setImage(selectedTeam.getSchedule().get(2).getOpp().getLogo());
        scheduleTeam4.setImage(selectedTeam.getSchedule().get(3).getOpp().getLogo());
        scheduleTeam5.setImage(selectedTeam.getSchedule().get(4).getOpp().getLogo());
        scheduleTeam6.setImage(selectedTeam.getSchedule().get(5).getOpp().getLogo());
        scheduleTeam7.setImage(selectedTeam.getSchedule().get(6).getOpp().getLogo());
        scheduleTeam8.setImage(selectedTeam.getSchedule().get(7).getOpp().getLogo());
        scheduleTeam9.setImage(selectedTeam.getSchedule().get(8).getOpp().getLogo());
        scheduleTeam10.setImage(selectedTeam.getSchedule().get(9).getOpp().getLogo());
        scheduleTeam11.setImage(selectedTeam.getSchedule().get(10).getOpp().getLogo());
        scheduleTeam12.setImage(selectedTeam.getSchedule().get(11).getOpp().getLogo());
        week1.setText(selectedTeam.getSchedule().get(0).toString());
        week2.setText(selectedTeam.getSchedule().get(1).toString());
        week3.setText(selectedTeam.getSchedule().get(2).toString());
        week4.setText(selectedTeam.getSchedule().get(3).toString());
        week5.setText(selectedTeam.getSchedule().get(4).toString());
        week6.setText(selectedTeam.getSchedule().get(5).toString());
        week7.setText(selectedTeam.getSchedule().get(6).toString());
        week8.setText(selectedTeam.getSchedule().get(7).toString());
        week9.setText(selectedTeam.getSchedule().get(8).toString());
        week10.setText(selectedTeam.getSchedule().get(9).toString());
        week11.setText(selectedTeam.getSchedule().get(10).toString());
        week12.setText(selectedTeam.getSchedule().get(11).toString());
    }

    public void handleNewsFeed() {
        if (franchise.franchise.week == 0) {
            handlePreSeasonNewsFeed();
        } else {
            franchise.franchise.setRankings();
            Team temp = selectedTeam;
            int week = franchise.franchise.week - 1;
            int min = 10000;
            for (int i = 24; i >= 0; i--) {
                int rank = franchise.franchise.teams.get(i).getSchedule().get(week).getYourRank();
                int oppRank = franchise.franchise.teams.get(i).getSchedule().get(week).getOppRank();
                int num = rank + oppRank;
                if (num < min) {
                    min = num;
                    temp = franchise.franchise.teams.get(i);
                }
            }

            newsfeed1image1.setImage(temp.getLogo());
            newsfeed1image2.setImage(temp.getSchedule().get(week).getOpp().getLogo());
            int tempRank = temp.getSchedule().get(week).getYourRank();
            int oppRank = temp.getSchedule().get(week).getOppRank();
            String tempRankString = "";
            String oppRankString = "";
            if (tempRank <= 25) {
                tempRankString = "#" + tempRank + " ";
            }
            if (oppRank <= 25) {
                oppRankString = "#" + oppRank + " ";
            }


            if (temp.getSchedule().get(week).getYourScore() > temp.getSchedule().get(week).getOppScore()) {
                newsfeed1.setText(tempRankString + temp.getName() + " Wins a big game over " + oppRankString + temp.getSchedule().get(week).getOpp().getName() + " " + temp.getSchedule().get(week).getYourScore() + "-" + temp.getSchedule().get(week).getOppScore());
            } else {
                newsfeed1.setText(oppRankString + temp.getSchedule().get(week).getOpp().getName() + " Upsets " + tempRankString + temp.getName() + " " + temp.getSchedule().get(week).getOppScore() + "-" + temp.getSchedule().get(week).getYourScore());
            }

            Team number1 = franchise.franchise.teams.get(0);
            newsfeedNumber1Image.setImage(number1.getLogo());
            if (number1.getRank() == number1.getSchedule().get(week).getYourRank()) {
                newsfeedNumber1.setText(number1.getName() + " Still remains a top of the rankings this week after a win over " + number1.getSchedule().get(week).getOpp().getName() + " " + number1.getSchedule().get(week).getYourScore() + "-" + number1.getSchedule().get(week).getOppScore());
            } else {
                newsfeedNumber1.setText(number1.getName() + " Moves to the number one spot this week after a win over " + number1.getSchedule().get(week).getOpp().getName() + " " + number1.getSchedule().get(week).getYourScore() + "-" + number1.getSchedule().get(week).getOppScore());
            }

            newsfeedSelectedTeamImage.setImage(selectedTeam.getLogo());
            newsfeedSelectedTeamOppImage.setImage(selectedTeam.getSchedule().get(week).getOpp().getLogo());
            if (selectedTeam.getSchedule().get(week).getYourScore() > selectedTeam.getSchedule().get(week).getOppScore()) {
                newsfeedSelectedTeamLabel.setText(selectedTeam.scheduleToString() + " Beats " + selectedTeam.getSchedule().get(week).getOpp().scheduleToString() + " " + selectedTeam.getSchedule().get(week).getYourScore() + "-" + selectedTeam.getSchedule().get(week).getOppScore());
                if (selectedTeam.getSchedule().get(week).getYourRank() - 10 > selectedTeam.getSchedule().get(week).getOppRank() && selectedTeam.getSchedule().get(week).getOppRank() <= 25) {
                    newsfeedSelectedTeamLabel.setText(selectedTeam.scheduleToString() + " With a big upset over " + selectedTeam.getSchedule().get(week).getOpp().scheduleToString() + " " + selectedTeam.getSchedule().get(week).getYourScore() + "-" + selectedTeam.getSchedule().get(week).getOppScore());
                }


            } else {
                newsfeedSelectedTeamLabel.setText(selectedTeam.scheduleToString() + " Loses to " + selectedTeam.getSchedule().get(week).getOpp().scheduleToString() + " " + selectedTeam.getSchedule().get(week).getYourScore() + "-" + selectedTeam.getSchedule().get(week).getOppScore());
            }
        }
    }

    public void handlePreSeasonNewsFeed() {
        franchise.franchise.setRankings();
        newsfeed1.setText(franchise.franchise.teams.get(0).scheduleToString() + " Looks forward to a National Title push this season with a week one game against " + franchise.franchise.teams.get(0).getSchedule().get(0).getOpp().scheduleToString());
        newsfeed1image2.setImage(franchise.franchise.teams.get(0).getSchedule().get(0).getOpp().getLogo());
        newsfeed1image1.setImage(franchise.franchise.teams.get(0).getLogo());
    }

    public void handleRosterTab() {
        rosterTabTeamLogo.setImage(selectedTeam.getLogo());
        rosterTabTeamName.setText(selectedTeam.simpleToString());
    }

    public void handleClickRosterTab(){
        handleTable();
    }

    public void handleTableSelection() {
        Player selectedPlayer = (Player) rosterTableView.getSelectionModel().getSelectedItem();

        rosterTabPlayerName.setText("" + selectedPlayer.getPosition() + " #" + selectedPlayer.getNumber() + " " + selectedPlayer.getName());
        if (selectedPlayer.getFace() != null) {
            rosterTabPlayerImage.setImage(selectedPlayer.getFace());
        } else {
            rosterTabPlayerImage.setImage(new Image("file:///C:/Users/chadb/IdeaProjects/NCAAFootball19GUILastTime/src/sample/img/stuff/nophoto.png"));
        }
        rosterTabClass.setText("CLASS: " + selectedPlayer.getYear());
        rosterTabOverall.setText("OVERALL: " + selectedPlayer.getRating());
        playerRatings.setText(selectedPlayer.playerRatings());
        generateStatsTable(selectedPlayer);
    }

    public void handleTableInit() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        passAccuracyColumn.setCellValueFactory(new PropertyValueFactory<>("passAccuracy"));
        passStrengthColumn.setCellValueFactory(new PropertyValueFactory<>("passStrength"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        ovrColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        carryColumn.setCellValueFactory(new PropertyValueFactory<>("carry"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        strengthColumn.setCellValueFactory(new PropertyValueFactory<>("strength"));
        kickAccuracyColumn.setCellValueFactory(new PropertyValueFactory<>("kickAccuracy"));
        kickPowerColumn.setCellValueFactory(new PropertyValueFactory<>("kickPower"));
        recieveColumn.setCellValueFactory(new PropertyValueFactory<>("recieve"));
        defenseColumn.setCellValueFactory(new PropertyValueFactory<>("defense"));
        blockColumn.setCellValueFactory(new PropertyValueFactory<>("block"));
    }

    public void handleTable() {
        System.out.println("runned");
        rosterTableView.getItems().clear();
        rosterTableView.getItems().addAll(selectedTeam.getTeamRoster());
        rosterTableView.getSelectionModel().select(0);
        handleTableSelection();

    }


    public void generateStatsTable(Player player) {
        statsTableView.getItems().clear();
        statsTableView.getColumns().clear();
        switch (player.getPosition()) {
            case "QB":
            case "RB":
            case "WR":
            case "TE":

                TableColumn<Integer, PlayerStats> passYards = new TableColumn<>("PASSYDS");
                passYards.setCellValueFactory(new PropertyValueFactory<>("passingYards"));
                TableColumn<Integer, PlayerStats> passAtt = new TableColumn<>("PASSATT");
                passAtt.setCellValueFactory(new PropertyValueFactory<>("passAttempts"));
                TableColumn<Integer, PlayerStats> completions = new TableColumn<>("COMP");
                completions.setCellValueFactory(new PropertyValueFactory<>("completions"));
                TableColumn<Integer, PlayerStats> touchdowns = new TableColumn<>("PASSTDS");
                touchdowns.setCellValueFactory(new PropertyValueFactory<>("passingTouchdowns"));

                TableColumn<Integer, PlayerStats> interceptionsThrown = new TableColumn<>("INTS");
                interceptionsThrown.setCellValueFactory(new PropertyValueFactory<>("interceptionsThrown"));
                TableColumn<Integer, PlayerStats> rushingTouchdowns = new TableColumn<>("RUSHTDS");
                rushingTouchdowns.setCellValueFactory(new PropertyValueFactory<>("rushingTouchdowns"));
                TableColumn<Integer, PlayerStats> receptions = new TableColumn<>("CATCHES");
                receptions.setCellValueFactory(new PropertyValueFactory<>("catches"));
                TableColumn<Integer, PlayerStats> fumbles = new TableColumn<>("FUMBLES");
                fumbles.setCellValueFactory(new PropertyValueFactory<>("fumbles"));

                TableColumn<Integer, PlayerStats> rushYards = new TableColumn<>("RUSHYARDS");
                rushYards.setCellValueFactory(new PropertyValueFactory<>("rushingYards"));
                TableColumn<Integer, PlayerStats> rushAttempts = new TableColumn<>("RUSHATT");
                rushAttempts.setCellValueFactory(new PropertyValueFactory<>("rushAttempts"));
                TableColumn<Integer, PlayerStats> receivingTouchdowns = new TableColumn<>("RECTDS");
                receivingTouchdowns.setCellValueFactory(new PropertyValueFactory<>("receivingTouchdowns"));
                TableColumn<String, PlayerStats> avgRushYds = new TableColumn<>("AVGYDS");
                avgRushYds.setCellValueFactory(new PropertyValueFactory<>("averageRushingYards"));
                TableColumn<String, PlayerStats> completionPercentage = new TableColumn<>("COMP%");
                completionPercentage.setCellValueFactory(new PropertyValueFactory<>("completionPercentage"));


                statsTableView.getColumns().addAll(passYards, passAtt, completions, completionPercentage, touchdowns, interceptionsThrown, rushAttempts, rushYards, avgRushYds, rushingTouchdowns, fumbles, receptions, receivingTouchdowns);
                statsTableView.getItems().addAll(player.getStats());
                break;


        }
    }

    public void globalStatsTable() {
        globalStatsTable.getItems().clear();
        globalStatsTable.getColumns().clear();

        TableColumn<String, Player> name = new TableColumn<>("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<String, Player> teamName = new TableColumn<>("TEAM");
        teamName.setCellValueFactory(new PropertyValueFactory<>("team"));
        TableColumn<String, Player> position = new TableColumn<>("POS");
        position.setCellValueFactory(new PropertyValueFactory<>("position"));



        TableColumn<Integer, PlayerStats> passYards = new TableColumn<>("PASSYDS");
        passYards.setCellValueFactory(new PropertyValueFactory<>("passingYards"));

        TableColumn<Integer, PlayerStats> passAtt = new TableColumn<>("PASSATT");
        passAtt.setCellValueFactory(new PropertyValueFactory<>("passAttempts"));
        TableColumn<Integer, PlayerStats> completions = new TableColumn<>("COMP");
        completions.setCellValueFactory(new PropertyValueFactory<>("completions"));
        TableColumn<Integer, PlayerStats> touchdowns = new TableColumn<>("PASSTDS");
        touchdowns.setCellValueFactory(new PropertyValueFactory<>("passingTouchdowns"));
        TableColumn<Integer, PlayerStats> interceptionsThrown = new TableColumn<>("INTS");
        interceptionsThrown.setCellValueFactory(new PropertyValueFactory<>("interceptionsThrown"));
        TableColumn<Integer, PlayerStats> rushingTouchdowns = new TableColumn<>("RUSHTDS");
        rushingTouchdowns.setCellValueFactory(new PropertyValueFactory<>("rushingTouchdowns"));
        TableColumn<Integer, PlayerStats> receptions = new TableColumn<>("CATCHES");
        receptions.setCellValueFactory(new PropertyValueFactory<>("catches"));
        TableColumn<Integer, PlayerStats> fumbles = new TableColumn<>("FUMBLES");
        fumbles.setCellValueFactory(new PropertyValueFactory<>("fumbles"));
        TableColumn<Integer, PlayerStats> rushYards = new TableColumn<>("RUSHYARDS");
        rushYards.setCellValueFactory(new PropertyValueFactory<>("rushingYards"));
        TableColumn<Integer, PlayerStats> rushAttempts = new TableColumn<>("RUSHATT");
        rushAttempts.setCellValueFactory(new PropertyValueFactory<>("rushAttempts"));
        TableColumn<Integer, PlayerStats> receivingTouchdowns = new TableColumn<>("RECTDS");
        receivingTouchdowns.setCellValueFactory(new PropertyValueFactory<>("receivingTouchdowns"));
        TableColumn<String, PlayerStats> avgRushYds = new TableColumn<>("AVGYDS");
        avgRushYds.setCellValueFactory(new PropertyValueFactory<>("averageRushingYards"));
        TableColumn<String, PlayerStats> completionPercentage = new TableColumn<>("COMP%");
        completionPercentage.setCellValueFactory(new PropertyValueFactory<>("completionPercentage"));

        globalStatsTable.getColumns().addAll(teamName,position,name,passYards, passAtt, completions, completionPercentage, touchdowns, interceptionsThrown, rushAttempts, rushYards, avgRushYds, rushingTouchdowns, fumbles, receptions, receivingTouchdowns);

       for(Team selected: getTeams()) {
           for (int i=0; i<selected.getTeamRoster().size(); i++) {
               globalStatsTable.getItems().addAll(selected.getTeamRoster().get(i));
           }
       }
       teamName.setPrefWidth(125);
       passYards.setSortType(TableColumn.SortType.DESCENDING);
       globalStatsTable.getSortOrder().add(passYards);
    }
}





