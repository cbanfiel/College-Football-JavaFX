package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ExpectationsMenu {
    Team selectedTeam;
    Expectations expectations;
    Expectations.Expectation selectedExpectation;

    @FXML
    Label teamName = new Label();

    @FXML
    Label expectationDialog = new Label();

    @FXML
    ListView<Expectations.Expectation> availableExpectations = new ListView<>();

    @FXML
    ListView<Expectations.Expectation> acceptedExpectations = new ListView<>();

    @FXML
    Button closeButton = new Button();

    @FXML
    Label wasMet = new Label();

    @FXML
    Label moneyLeft = new Label();

    @FXML
            ListView<Expectations.Expectation> passedExpectations;

    @FXML
            ListView<Expectations.Expectation> failedExpectations;
    @FXML
    ImageView teamLogo = new ImageView();

    @FXML
            Label moneyIncentive = new Label();

    ObservableList<Expectations.Expectation> acceptedExpectationsObservableList;
    ObservableList<Expectations.Expectation> passedExpectationObservable;

    ObservableList<Expectations.Expectation> failedExpectationObservable;





    public void initializeScreen(){
        System.out.println(selectedTeam);
        expectations = selectedTeam.getExpectations();
        ObservableList<Expectations.Expectation> expectationObservableList= FXCollections.observableArrayList(expectations.getExpectationArrayList());
        acceptedExpectationsObservableList = FXCollections.observableArrayList(expectations.getSelectedExpectations());
        teamName.setText(selectedTeam.toString());
        availableExpectations.setItems(expectationObservableList);
        acceptedExpectations.setItems(acceptedExpectationsObservableList);
         availableExpectations.getSelectionModel().selectFirst();
         selectedExpectation = availableExpectations.getSelectionModel().getSelectedItem();
         expectationDialog.setText("\"" + selectedExpectation.getString() + "\"");
         wasMet.setText("" + selectedExpectation.isMet());
         moneyIncentive.setText("Incentive: $" + selectedExpectation.getGain());
         moneyLeft.setText("Current Money: $" + selectedTeam.getMoney());
        passedExpectationObservable = FXCollections.observableArrayList(expectations.getMetExpectations());
        failedExpectationObservable = FXCollections.observableArrayList(expectations.getFailedExpectations());
        passedExpectations.setItems(passedExpectationObservable);
        failedExpectations.setItems(failedExpectationObservable);
        teamLogo.setImage(selectedTeam.getLogo());
        selectedTeam.getExpectations().removeFailedorPassedExpectations();

    }

    public void handleChangeExpectation(){
        selectedExpectation = availableExpectations.getSelectionModel().getSelectedItem();
        expectationDialog.setText("\"" + selectedExpectation.getString() + "\"");
        moneyIncentive.setText("Incentive: $" + selectedExpectation.getGain());

    }

    public void handleAcceptExpectation(){
        if(expectations.acceptExpectation(selectedExpectation)){
            expectationDialog.setText("Added Successfully");
        }else{
            expectationDialog.setText("You already accepted that one");
        }
        acceptedExpectationsObservableList=FXCollections.observableArrayList(expectations.getSelectedExpectations());
        acceptedExpectations.setItems(acceptedExpectationsObservableList);
        System.out.println();

    }


    public void setSelectedTeam(Team team){
        this.selectedTeam = team;
        initializeScreen();
    }

    public void backToDynasty() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
