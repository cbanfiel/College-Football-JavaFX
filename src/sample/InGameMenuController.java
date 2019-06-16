package sample;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import sample.gameplay.Gameplay;
import sample.gameplay.OffensivePlaybook;

public class InGameMenuController {
    Gameplay gameplay;
    Team home;
    Team away;
    int yards;
    @FXML
    Label homeNameLabel = new Label();
    @FXML
    Label awayNameLabel = new Label();
    @FXML
    ImageView homeLogo = new ImageView();
    @FXML
    ImageView awayLogo = new ImageView();
    @FXML
    Label playByPlayLabel = new Label();
    @FXML
    ImageView footballImage = new ImageView();
    @FXML
    Label gameData = new Label();
    @FXML
    ImageView fieldLogo = new ImageView();
    @FXML
    TextArea gameSummary = new TextArea();
    @FXML
    ImageView isHomePossesionImage = new ImageView();
    @FXML
    ImageView isAwayPossesionImage = new ImageView();
    @FXML
    Label quarterLabel = new Label();
    @FXML
    Label selectedPlayLabel = new Label();
    @FXML
    ListView<OffensivePlaybook.Plays> playBookList = new ListView<>();
    @FXML
    Line lineOfScrimmageMarker = new Line();
    @FXML
            Line firstDownMarker = new Line();


    ObservableList<OffensivePlaybook.Plays> playsObservableList;


    public void initalizeGameMenu(){
        isAwayPossesionImage.setVisible(false);
        isHomePossesionImage.setVisible(false);
        home = gameplay.getHome();
        away = gameplay.getAway();
        homeLogo.setImage(home.getLogo());
        awayLogo.setImage(away.getLogo());
        homeNameLabel.setText(home.getName() +": " + gameplay.getHomeScore());
        awayNameLabel.setText(away.getName() +": " + gameplay.getAwayScore());
        fieldLogo.setImage(home.getLogo());
        gameplay.playGame();
        quarterLabel.setText("Quarter: " + gameplay.getQuarter());
        if(gameplay.isHomePossesion()){
            playByPlayLabel.setText(home.getName() + " Has won the toss and elected to receive!");
            isHomePossesionImage.setVisible(true);

            footballImage.setLayoutX((gameplay.getCurrentYardMarker()*5.76)+346 - (footballImage.getFitWidth()/2));
            lineOfScrimmageMarker.setLayoutX((gameplay.getLineOfScrimmage()*5.76)+346);
            firstDownMarker.setLayoutX(gameplay.getFirstDownMarker()*5.76+346);
        }else{
            isAwayPossesionImage.setVisible(true);
            playByPlayLabel.setText(away.getName() + " Has won the toss and elected to receive!");

            footballImage.setLayoutX((gameplay.getCurrentYardMarker()*5.76)+346 - (footballImage.getFitWidth()/2));
            lineOfScrimmageMarker.setLayoutX((gameplay.getLineOfScrimmage()*5.76)+346);
            firstDownMarker.setLayoutX(gameplay.getFirstDownMarker()*5.76+346);



        }
        loadPlayBook();
    }

    public void simNextPlay(){
        double current = footballImage.getLayoutX();

        if(gameplay.getTime()>0 || gameplay.getHomeScore()==gameplay.getAwayScore()){
            gameplay.playPossesion();
        }
        isAwayPossesionImage.setVisible(!gameplay.isHomePossesion());
        isHomePossesionImage.setVisible(gameplay.isHomePossesion());
        playByPlayLabel.setText(gameplay.getPlayByPlay());
        homeNameLabel.setText(home.getName() +": " + gameplay.getHomeScore());
        awayNameLabel.setText(away.getName() +": " + gameplay.getAwayScore());
        int yardsTilFirst = (gameplay.getCurrentYardMarker() - gameplay.getFirstDownMarker());
        if(yardsTilFirst<0){
            yardsTilFirst*=-1;
        }

        gameData.setText("Down: " + gameplay.getDown() + " and " + yardsTilFirst + " Ball at the: " + gameplay.getCurrentYardMarker() + " Time Left: " + gameplay.getTime());
        if(gameplay.getTime()<=0){
            playByPlayLabel.setText("GAME OVER");
            gameData.setText("");
        }
        gameplay.setGameSummary(gameplay.getGameSummary() + "\n" + "TIME: " + gameplay.getTime() + " " + gameplay.getPlayByPlay());
        gameSummary.setText(gameplay.getGameSummary());
        quarterLabel.setText("Quarter: " + gameplay.getQuarter());
        loadPlayBook();


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(300.0d),new KeyValue(footballImage.layoutXProperty(),(gameplay.getCurrentYardMarker()*5.76)+346 - (footballImage.getFitWidth()/2))));
        timeline.setCycleCount(1);
        Timeline setMarkers = new Timeline();
        setMarkers.getKeyFrames().addAll(new KeyFrame(Duration.millis(300.0d),new KeyValue(lineOfScrimmageMarker.layoutXProperty(),(gameplay.getLineOfScrimmage()*5.76)+346)));
        setMarkers.getKeyFrames().addAll(new KeyFrame(Duration.millis(300.0d),new KeyValue(firstDownMarker.layoutXProperty(),(gameplay.getFirstDownMarker()*5.76)+346)));
        timeline.setOnFinished(e -> setMarkers.play());
        timeline.play();

    }

    public void simToEnd(){
        while (gameplay.getTime()>0 || gameplay.getHomeScore()==gameplay.getAwayScore()){
            simNextPlay();
        }
    }

    public void simToNextQuarter(){
        int current = gameplay.getQuarter();
        if(current==4){
            simToEnd();
            return;
        }
        while(gameplay.getQuarter()==current){
            simNextPlay();
        }
    }

    public void setGame(Gameplay game){
        this.gameplay = game;
        initalizeGameMenu();
    }

   public void loadPlayBook(){
       selectedPlayLabel.setText("CPU-Selection");

       if(gameplay.isHomePossesion()) {
            playsObservableList = FXCollections.observableArrayList(gameplay.getHome().getOffensivePlaybook().getPlays());
            playBookList.setItems(playsObservableList);
        }else{
            playsObservableList = FXCollections.observableArrayList(gameplay.getAway().getOffensivePlaybook().getPlays());
            playBookList.setItems(playsObservableList);
        }

    }


    public void handleSelectPlay(){
        selectedPlayLabel.setText(playBookList.getSelectionModel().getSelectedItem().getPlayName());
        gameplay.setHumanSelectedPlay(playBookList.getSelectionModel().getSelectedItem());

    }

}
