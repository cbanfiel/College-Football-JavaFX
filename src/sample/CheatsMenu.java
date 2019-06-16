package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CheatsMenu{

    @FXML
    CheckBox strippers = new CheckBox();

    @FXML
    CheckBox payRecruits = new CheckBox();

    @FXML
    CheckBox drugs = new CheckBox();

    Team selectedTeam = null;


    public void initializeScreen(){
        System.out.println(selectedTeam);
        strippers.setSelected(selectedTeam.isHiresStrippers());
        payRecruits.setSelected(selectedTeam.isPaysRecruits());
        drugs.setSelected(selectedTeam.isGivesPlayersPeds());
    }


    public void applyChanges(ActionEvent e){
        System.out.println(selectedTeam);
        selectedTeam.setHiresStrippers(strippers.isSelected());
        selectedTeam.setPaysRecruits(payRecruits.isSelected());
        selectedTeam.setGivesPlayersPeds(drugs.isSelected());


    }

    public void setSelectedTeam(Team team){
        selectedTeam = team;
        initializeScreen();
    }

    public void clicked1(ActionEvent e) throws IOException {
        Parent home_page_parent =FXMLLoader.load(getClass().getResource("DynastyMenu.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}



