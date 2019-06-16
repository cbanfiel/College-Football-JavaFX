package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Expectations {
    String expectationDialog;
    Team team;
    //    String winMoreDialog = "I would like you win " + (team.getPreviousSeasons().get(team.getPreviousSeasons().size()-1).getWins() + 1) + " games this year";
    String winMoreDialog = "I would like you to win more than last year";
    String makeABowlDialog = " I would like you to win 6 games this year and become Bowl Elligible";
    String winATitleDialog = " I would like you to win a title in the next 5 years";
    String beTop25Dialog = " I would like you to be a top 25 Team this year";
    String beTop10Dialog = " I would like you to be a top 10 team this year";
    Expectation winMore = new Expectation(winMoreDialog, 50000, "Win More");
    Expectation makeABowl = new Expectation(makeABowlDialog, 50000, "Make A Bowl");
    Expectation winATitle = new Expectation(winATitleDialog, 100000, "Win A Title Soon");
    Expectation beTop25 = new Expectation(beTop25Dialog, 50000, "Finish Top 25");
    Expectation beTop10 = new Expectation(beTop10Dialog, 75000, "Finish Top 10");
    Expectation win3Games = new Expectation("I would like you to win 3 games this year", 25000, "Win 3 Games");
    Expectation winaGame = new Expectation("Please for the love of God just win one game this year", 10000, "Win 1 Game");
    Expectation customWin = new Expectation("I would like you to win this many games", 35000, "Win Games");
    ArrayList<Expectation> expectationArrayList;
    ArrayList<Expectation> selectedExpectations;
    ArrayList<Expectation> failedExpectations;
    ArrayList<Expectation> metExpectations;

    public ArrayList<Expectation> getFailedExpectations() {
        return failedExpectations;
    }

    public ArrayList<Expectation> getMetExpectations() {
        return metExpectations;
    }

    public Expectations(Team team) {
        this.team = team;
        expectationArrayList = new ArrayList<>();
        selectedExpectations = new ArrayList<>();
        failedExpectations = new ArrayList<>();
        metExpectations = new ArrayList<>();
        customizeExpectations();
    }

    public void customizeExpectations() {
        expectationArrayList.clear();
        if (team.getPreviousSeasons().size() != 0) {
            if (team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getRank() <= 35 && team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getRank() >= 15) {
                expectationArrayList.add(beTop25);
            }
            if (team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getRank() <= 15) {
                expectationArrayList.add(beTop10);
            }
            if (team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getWins() < 12) {
                expectationArrayList.add(winMore);
            }
            if (team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getWins() <= 7) {
                expectationArrayList.add(makeABowl);
            }
            if (team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getRank() <= 10) {
                expectationArrayList.add(winATitle);
            }
            if (team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getWins() <= 2) {
                expectationArrayList.add(winaGame);
            }
            if (team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getWins() <= 5) {
                expectationArrayList.add(win3Games);
            }

        } else {
            if (team.getTeamPrestige() == 5) {
                expectationArrayList.add(winATitle);
                expectationArrayList.add(beTop10);
                expectationArrayList.add(beTop25);
            }
            if (team.getTeamPrestige() == 4) {
                expectationArrayList.add(beTop10);
                expectationArrayList.add(beTop25);
                expectationArrayList.add(makeABowl);
            }
            if (team.getTeamPrestige() == 3) {
                expectationArrayList.add(beTop25);
                expectationArrayList.add(makeABowl);
            }
            if (team.getTeamPrestige() == 2) {
                expectationArrayList.add(makeABowl);
                expectationArrayList.add(win3Games);
            }
            if (team.getTeamPrestige() == 1) {
                expectationArrayList.add(win3Games);
                expectationArrayList.add(winaGame);
            }
        }
    }

    public void removeAcceptedExpactations() {
        if (selectedExpectations.contains(winATitle)) {
            selectedExpectations.clear();
            if(winATitle.timeLine>0) {
                selectedExpectations.add(winATitle);
            }
        } else {
            selectedExpectations.clear();
        }
    }

    public void removeFailedorPassedExpectations() {
        failedExpectations.clear();
        metExpectations.clear();
    }


    public ArrayList<Expectation> getExpectationArrayList() {
        return expectationArrayList;
    }

    public ArrayList<Expectation> getSelectedExpectations() {
        return selectedExpectations;
    }

    public boolean acceptExpectation(Expectation expectation) {
        if (!selectedExpectations.contains(expectation)) {
            if (expectation.equals(winATitle)) {
                expectation.timeLine = 5;
            }
            selectedExpectations.add(expectation);
            return true;
        }
        return false;
    }

    public void yearlyRevenue(){
        team.setMoney(team.getMoney() + (team.getWins()*3500));
        team.setMoney(team.getMoney() + (team.getLosses()* -1300));
    }

    public void metExpectations() {
        yearlyRevenue();
        if (selectedExpectations.contains(winMore)) {
            if (team.getPreviousSeasons().get(team.getPreviousSeasons().size() - 1).getWins() < team.getWins()) {
                team.setMoney(team.getMoney() + winMore.gain);
                winMore.setMet(true);
                metExpectations.add(winMore);
            } else {
                team.setMoney(team.getMoney() - winMore.gain);
                winMore.setMet(false);
                failedExpectations.add(winMore);
            }
        }
        if (selectedExpectations.contains(winATitle)) {
            winATitle.timeLine--;
            if (team.getRank() == 1) {
                team.setMoney(team.getMoney() + winATitle.gain);
                winATitle.setMet(true);
                metExpectations.add(winATitle);
                selectedExpectations.remove(winATitle);
            } else {
                if (winATitle.timeLine  == 0) {
                    team.setMoney(team.getMoney() - winATitle.gain);
                    winATitle.setMet(false);
                    failedExpectations.add(winATitle);
                }
            }
        }
        if (selectedExpectations.contains(makeABowl)) {
            if (team.getWins() >= 6) {
                team.setMoney(team.getMoney() + makeABowl.gain);
                makeABowl.setMet(true);
                metExpectations.add(makeABowl);
            } else {
                team.setMoney(team.getMoney() - makeABowl.gain);
                makeABowl.setMet(false);
                failedExpectations.add(makeABowl);


            }
        }
        if (selectedExpectations.contains(beTop25)) {
            if (team.getRank() <= 25) {
                team.setMoney(team.getMoney() + beTop25.gain);
                beTop25.setMet(true);
                metExpectations.add(beTop25);

            } else {
                team.setMoney(team.getMoney() - beTop25.gain);
                beTop25.setMet(false);
                failedExpectations.add(beTop25);

            }
        }
        if (selectedExpectations.contains(beTop10)) {
            if (team.getRank() <= 10) {
                team.setMoney(team.getMoney() + beTop10.gain);
                beTop10.setMet(true);
                metExpectations.add(beTop10);
            } else {
                team.setMoney(team.getMoney() - beTop10.gain);
                beTop10.setMet(false);
                failedExpectations.add(beTop10);
            }
        }

        if (selectedExpectations.contains(win3Games)) {
            if (team.getWins() >= 3) {
                team.setMoney(team.getMoney() + win3Games.gain);
                win3Games.setMet(true);
                metExpectations.add(win3Games);
            } else {
                team.setMoney(team.getMoney() - win3Games.gain);
                win3Games.setMet(false);
                failedExpectations.add(win3Games);


            }
        }

        if (selectedExpectations.contains(winaGame)) {
            if (team.getWins() >= 3) {
                team.setMoney(team.getMoney() + winaGame.gain);
                winaGame.setMet(true);
                metExpectations.add(winaGame);
            } else {
                team.setMoney(team.getMoney() - winaGame.gain);
                winaGame.setMet(false);
                failedExpectations.add(winaGame);


            }
        }
        removeAcceptedExpactations();
    }


    public class Expectation {
        String string;
        String shortDescription;
        int gain;
        boolean met;
        int timeLine;

        public Expectation(String string, int gain, String shortDescription) {
            this.string = string;
            this.gain = gain;
            this.shortDescription = shortDescription;
        }

        public String getString() {
            return string;
        }

        public int getGain() {
            return gain;
        }

        public boolean isMet() {
            return met;
        }

        public void setMet(boolean met) {
            this.met = met;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        @Override
        public String toString() {
            return shortDescription;
        }
    }

}
