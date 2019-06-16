package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class TeamInfoMenuController {

    private int selectedTeam = 0;

    private static Team team;
    Sliders sliders = new Sliders();



    @FXML
    ListView<Team> listViewTeams = new ListView<>();

    @FXML
    Label teamNameLabel = new Label();
    @FXML
    ListView<Player> teamInfo = new ListView<>();

    ObservableList<Team> observableList = FXCollections.observableArrayList(Menu.getTeams());

    FilteredList<Team> top25teams = new FilteredList<>(observableList);

    public void initialize(){
        listViewTeams.setItems(observableList);
        listViewTeams.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewTeams.getSelectionModel().selectFirst();
        team = listViewTeams.getSelectionModel().getSelectedItem();
        teamNameLabel.setText(team.toString());
        ObservableList<Player> observableRosterList = FXCollections.observableArrayList(team.getTeamRoster());
        teamInfo.setItems(observableRosterList);

    }

    public void handleClickListView(){
        team = listViewTeams.getSelectionModel().getSelectedItem();
        teamNameLabel.setText(team.toString());
        ObservableList<Player> observableRosterList = FXCollections.observableArrayList(team.getTeamRoster());
        teamInfo.setItems(observableRosterList);
    }

    public void handleNextTeam(){
        selectedTeam++;
        initialize();
    }

    public void handlePreviousTeam(){
        selectedTeam--;
        initialize();
    }

    public void backToMenu(ActionEvent e) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }



}
