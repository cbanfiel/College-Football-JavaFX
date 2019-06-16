package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class PlaySeasonController {
//    ArrayList teams = new TeamData().load();
    PlaySeason season = new PlaySeason(Menu.getTeams());
    Team selectedTeam;
    Sliders sliders = new Sliders();

    @FXML
    TextArea teamRankings = new TextArea();

    @FXML
    ListView <Team> seasonTeamsListView = new ListView<>();

    @FXML
    Label teamInfoLabel = new Label();

    @FXML
    ListView<Game> previousGamesListView = new ListView<Game>();

    @FXML
    ListView<Player> teamRosterListView = new ListView<Player>();

    @FXML
    ListView<Team> top25ListView = new ListView<>();

    private Predicate<Team> top25only;


    public void initialize() {

        teamRankings.setText("hello");
        startSeason();
        ObservableList<Team> teamObservableList = FXCollections.observableArrayList(season.sortTeamsByName());
        seasonTeamsListView.setItems(teamObservableList);
        seasonTeamsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        seasonTeamsListView.getSelectionModel().selectFirst();
        handleFilteredList();
        selectedTeam = seasonTeamsListView.getSelectionModel().getSelectedItem();
        teamInfoLabel.setText(selectedTeam.simpleToString());
        ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
        teamRosterListView.setItems(rosterObservableList);
        top25only = new Predicate<Team>(){
            @Override
            public boolean test(Team team) {
                return team.getRank()<=25;
            }
        };
    }

    public void handleClickListView(){
        selectedTeam = seasonTeamsListView.getSelectionModel().getSelectedItem();
        teamInfoLabel.setText(selectedTeam.simpleToString());
        ObservableList<Game> previousGamesObservableList = FXCollections.observableArrayList(selectedTeam.getPreviousGames());
        previousGamesListView.setItems(previousGamesObservableList);
        ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
        teamRosterListView.setItems(rosterObservableList);
    }

    public void handleTop25ClickListView(){
        selectedTeam = top25ListView.getSelectionModel().getSelectedItem();
        teamInfoLabel.setText(selectedTeam.simpleToString());
        ObservableList<Game> previousGamesObservableList = FXCollections.observableArrayList(selectedTeam.getPreviousGames());
        previousGamesListView.setItems(previousGamesObservableList);
        ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
        teamRosterListView.setItems(rosterObservableList);
    }

    public void startSeason() {
        season.setRankings();
        teamRankings.setText(season.printWeeklyRankings());
    }

    public void simWeek() {
        if (season.playWeek()) {
            teamRankings.setText(season.printWeeklyRankings());
            teamInfoLabel.setText(selectedTeam.simpleToString());
            ObservableList<Game> previousGamesObservableList = FXCollections.observableArrayList(selectedTeam.getPreviousGames());
            previousGamesListView.setItems(previousGamesObservableList);
            ObservableList<Player> rosterObservableList = FXCollections.observableArrayList(selectedTeam.getTeamRoster());
            teamRosterListView.setItems(rosterObservableList);
            handleFilteredList();
            ObservableList<Team> teamObservableList = FXCollections.observableArrayList(season.sortTeamsByName());
            seasonTeamsListView.setItems(teamObservableList);
        }
        else{
            teamRankings.setText(season.printSeasonResults());
        }
    }

    public void handleFilteredList(){
        ObservableList<Team> top25ObservableList = FXCollections.observableArrayList(season.setRankings());
        FilteredList<Team> top25teams = new FilteredList<>(top25ObservableList);
        top25teams.setPredicate(top25only);
        top25ListView.setItems(top25teams);

    }




}

