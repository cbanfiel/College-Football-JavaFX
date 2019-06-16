package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CreateAPlayerMenu{
//    private static TeamData teamData = new TeamData();
//    private static ArrayList<Team> teams = teamData.load();
    private ObservableList<Team> teamObservableList = FXCollections.observableArrayList(Menu.getTeams());
    private ObservableList<String> stringObservableList = FXCollections.observableArrayList("QB","RB","WR","TE","OL","DL","LB","DB","K");


    @FXML
    ChoiceBox<Team> teamSelection = new ChoiceBox<>();

    @FXML
    ChoiceBox<String> positionSelector = new ChoiceBox();

    @FXML
    TextField nameInput = new TextField();
    @FXML
    TextField numberInput = new TextField();
    @FXML
    TextField ratingInput = new TextField();






    public void initialize(){
        teamSelection.setItems(teamObservableList);
        positionSelector.setItems(stringObservableList);

    }

    public void handleOkay(){
        Team team = teamSelection.getSelectionModel().getSelectedItem();
        System.out.println(team);
        String name = nameInput.getText();
        int number = Integer.parseInt(numberInput.getText());
        System.out.println(number);
        int rating = Integer.parseInt(ratingInput.getText());
        System.out.println(rating);
        String position = positionSelector.getSelectionModel().getSelectedItem();
        System.out.println(position);
        team.createPlayer(name,rating,position,number);
        System.out.println(team.printRoster());

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
