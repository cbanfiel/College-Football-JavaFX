package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BootUp implements Initializable {

    @FXML
    ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000),image);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);

        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                Stage loginScreen=new Stage();
//                Parent root=null;
//                try {
//                    root= FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//                }catch (Exception e){
//                }
//
//                Stage current=(Stage) image.getScene().getWindow();
//                Scene scene = new Scene(root,1280,720);
//
//                loginScreen.setScene(scene);
//                loginScreen.initStyle(StageStyle.TRANSPARENT);
//
//                current.hide();
//                loginScreen.show();
try {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("MainMenu.fxml"));
    loader.load();
    Menu menu = loader.getController();

    //Grab Stage
    Stage stage = (Stage) image.getScene().getWindow();

    Parent parent = loader.getRoot();
    stage.setScene(new Scene(parent));
}
catch (IOException e){

}

            }
        });
        fadeTransition.play();
    }
}
