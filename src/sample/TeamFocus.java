package sample;

import java.util.ArrayList;

public class TeamFocus {
    private boolean recruiting;
    private boolean training;
    private boolean coaching;

    public TeamFocus(String choice) {
        recruiting=false;
        training=false;
        coaching=false;

       if(choice.equals("recruiting")){
           recruiting=true;
       }else if(choice.equals("training")){
           training=true;
       }else {
           coaching=true;
       }

    }



    public TeamFocus(int choice) {
        recruiting=false;
        training=false;
        coaching=false;

        if(choice==0){
            recruiting=true;
        }else if(choice==1){
            training=true;
        }else {
            coaching=true;
        }

    }

    public void changeTeamFocus(int choice) {
        recruiting=false;
        training=false;
        coaching=false;

        if(choice==0){
            recruiting=true;
        }else if(choice==1){
            training=true;
        }else {
            coaching=true;
        }

    }

    @Override
    public String toString() {
        if(recruiting){
           return "Recruiting";
        }else if(training){
            return "Training";
        }else if(coaching){
            return "Coaching";
        }
        return "";
    }

    public boolean isRecruiting() {
        return recruiting;
    }

    public boolean isTraining() {
        return training;
    }

    public boolean isCoaching() {
        return coaching;
    }

}
