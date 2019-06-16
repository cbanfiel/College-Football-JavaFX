package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CreateATeamMenu {
    ArrayList teams = Menu.getTeams();

    @FXML
    TextField nameInput = new TextField();

    @FXML
    TextField ratingInput = new TextField();

    public void handleOkay(){
        String name = nameInput.getText();
        int rating = Integer.parseInt(ratingInput.getText());
        Team team = new Team(name,rating);
        teams.add(team);
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
