package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UpgradeMenu {
    Team selectedTeam;

    @FXML
    Label availableMoney = new Label();

    @FXML
    Label teamName = new Label();

    @FXML
    Button upgradeRecruiting = new Button();

    @FXML
    Button upgradeTraining = new Button();

    @FXML
    Button closeButton = new Button();

    public void initalizeScreen() {
        teamName.setText(selectedTeam.getName());
        availableMoney.setText("Available Funds: $" + selectedTeam.getMoney());
        upgradeRecruiting.setText("Upgrade Recruiting(+" + (selectedTeam.getRecruitingUpgrade() + 1) + ")");
        upgradeTraining.setText("Upgrade Training(+" + (selectedTeam.getTrainingUpgrade() + 1) + ")");


    }

    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = selectedTeam;
        initalizeScreen();
    }

    public void handleUpgradeRecruiting() {
        if (selectedTeam.getMoney() < 350000) {
            upgradeRecruiting.setText("Not enough funds");
        } else {
            selectedTeam.setRecruitingUpgrade(selectedTeam.getRecruitingUpgrade() + 1);
            selectedTeam.setMoney(selectedTeam.getMoney() - 350000);
            initalizeScreen();
        }
    }


    public void handleUpgradeTraining() {
        if (selectedTeam.getMoney() < 350000) {
            upgradeTraining.setText("Not enough funds");
        } else {
            selectedTeam.setTrainingUpgrade(selectedTeam.getTrainingUpgrade() + 1);
            selectedTeam.setMoney(selectedTeam.getMoney() - 350000);
            initalizeScreen();
        }
    }

    public void backToDynasty() {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


}
