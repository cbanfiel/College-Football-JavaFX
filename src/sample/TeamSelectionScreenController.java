package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.gameplay.Gameplay;

import java.io.IOException;

public class TeamSelectionScreenController {

    Team homeTeam;
    Team awayTeam;

    @FXML
    ImageView homeTeamLogo = new ImageView();
    @FXML
    ImageView awayTeamLogo = new ImageView();

    @FXML
    ListView<Team> homeTeamSelector = new ListView();

    @FXML
    ListView<Team> awayTeamSelector = new ListView();

    ObservableList<Team> observableList = FXCollections.observableArrayList(Menu.getTeams());


    public void initialize(){
        homeTeamSelector.setItems(observableList);
        awayTeamSelector.setItems(observableList);
        homeTeamSelector.getSelectionModel().selectFirst();
        awayTeamSelector.getSelectionModel().selectLast();
        homeTeam = homeTeamSelector.getSelectionModel().getSelectedItem();
        awayTeam = awayTeamSelector.getSelectionModel().getSelectedItem();
        homeTeamLogo.setImage(homeTeam.getLogo());
        awayTeamLogo.setImage(awayTeam.getLogo());

    }

    public void startGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("InGameMenu.fxml"));
        loader.load();
        InGameMenuController inGameMenuController = loader.getController();
        inGameMenuController.setGame(new Gameplay(homeTeam,awayTeam));

        //Grab Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Parent parent = loader.getRoot();
        stage.setScene(new Scene(parent));


    }

    public void handleSelectTeam(){
        homeTeam = homeTeamSelector.getSelectionModel().getSelectedItem();
        awayTeam = awayTeamSelector.getSelectionModel().getSelectedItem();
        homeTeamLogo.setImage(homeTeam.getLogo());
        awayTeamLogo.setImage(awayTeam.getLogo());
    }


}
