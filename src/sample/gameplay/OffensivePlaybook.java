package sample.gameplay;

import sample.Player;
import sample.Team;

import java.util.ArrayList;
import java.util.Random;

public class OffensivePlaybook {
    Team team;
    String name;
    String[] types;
    ArrayList<Plays> plays;


    public ArrayList<Plays> getPlays() {
        return plays;
    }

    public OffensivePlaybook(Team team, String name) {
        plays = new ArrayList<>();
        this.team = team;
        this.name = name;
        setPlaybook();
//        System.out.println(team.getName() + "playbook set");
    }

    public OffensivePlaybook(Team team){
        plays = new ArrayList<>();
        this.team = team;
        types = new String[] {"Pro-Style", "Option"};
        this.name = pickPlaybook();
        setPlaybook();
//        System.out.println(team.getName() + this.name + " playbook set");
    }




    public void setPlaybook(){
        if(name.equalsIgnoreCase("pro-style")){
            plays.add(new Plays("pro-style pass","pass"));
            plays.add(new Plays("pro-style run","run"));
        }
        if(name.equalsIgnoreCase("option")){
            plays.add(new Plays("speed option","run"));
            plays.add(new Plays("spread pass","pass"));

        }



    }

    private void setModfiers() {

    }

    public String pickPlaybook(){
        Random random = new Random();
        return types[random.nextInt(types.length)];
    }



    public class Plays{
        ArrayList<Player> onField;
        ArrayList<Player> targets;
        String playName;
        String playType;
        //Modifiers
        int yardsMod=0;
        int qbAccuracyMod=0;
        int bigPlayMod=0;

        @Override
        public String toString() {
            return playName;
        }

        public int getYardsMod() {
            return yardsMod;
        }

        public int getQbAccuracyMod() {
            return qbAccuracyMod;
        }

        public int getBigPlayMod() {
            return bigPlayMod;
        }

        public String getPlayType() {
            return playType;
        }

        public String getPlayName() {
            return playName;
        }



        public ArrayList<Player> getOnField() {
            return onField;
        }

        public ArrayList<Player> getTargets() {
            return targets;
        }

        public Plays(String playName, String type) {
            onField= new ArrayList<>();
            targets = new ArrayList<>();
            this.playType = type;
            this.playName = playName;
            setInfo();
        }

        public void setInfo(){
            if(playName.equalsIgnoreCase("pro-style pass")){
                onField.clear();
                onField.add(team.getDepthChart().getQB().get(0));
                onField.add(team.getDepthChart().getRB().get(0));
                onField.add(team.getDepthChart().getOL().get(0));
                onField.add(team.getDepthChart().getOL().get(1));
                onField.add(team.getDepthChart().getOL().get(2));
                onField.add(team.getDepthChart().getOL().get(3));
                onField.add(team.getDepthChart().getOL().get(4));
                onField.add(team.getDepthChart().getTE().get(0));
                onField.add(team.getDepthChart().getWR().get(0));
                onField.add(team.getDepthChart().getWR().get(1));
                onField.add(team.getDepthChart().getWR().get(2));
                targets.clear();
               targets.add(team.getDepthChart().getTE().get(0));
               targets.add(team.getDepthChart().getWR().get(0));
               targets.add(team.getDepthChart().getWR().get(1));
               targets.add(team.getDepthChart().getWR().get(2));
               targets.add(team.getDepthChart().getRB().get(0));
            }
            if(playName.equalsIgnoreCase("pro-style run")){
                onField.clear();
                onField.add(team.getDepthChart().getQB().get(0));
                onField.add(team.getDepthChart().getRB().get(0));
                onField.add(team.getDepthChart().getOL().get(0));
                onField.add(team.getDepthChart().getOL().get(1));
                onField.add(team.getDepthChart().getOL().get(2));
                onField.add(team.getDepthChart().getOL().get(3));
                onField.add(team.getDepthChart().getOL().get(4));
                onField.add(team.getDepthChart().getTE().get(0));
                onField.add(team.getDepthChart().getWR().get(0));
                onField.add(team.getDepthChart().getWR().get(1));
                onField.add(team.getDepthChart().getWR().get(2));
                targets.clear();
                targets.add(team.getDepthChart().getRB().get(0));
            }
            if(playName.equalsIgnoreCase("speed option")){
                onField.clear();
                onField.add(team.getDepthChart().getQB().get(0));
                onField.add(team.getDepthChart().getRB().get(0));
                onField.add(team.getDepthChart().getOL().get(0));
                onField.add(team.getDepthChart().getOL().get(1));
                onField.add(team.getDepthChart().getOL().get(2));
                onField.add(team.getDepthChart().getOL().get(3));
                onField.add(team.getDepthChart().getOL().get(4));
                onField.add(team.getDepthChart().getWR().get(0));
                onField.add(team.getDepthChart().getWR().get(1));
                onField.add(team.getDepthChart().getWR().get(2));
                onField.add(team.getDepthChart().getWR().get(3));
                targets.clear();
                targets.add(team.getDepthChart().getRB().get(0));
                targets.add(team.getDepthChart().getRB().get(0));
                targets.add(team.getDepthChart().getQB().get(0));

                this.yardsMod=-1;
                this.bigPlayMod=5;

            }

            if(playName.equalsIgnoreCase("spread pass")){
                onField.clear();
                onField.add(team.getDepthChart().getQB().get(0));
                onField.add(team.getDepthChart().getRB().get(0));
                onField.add(team.getDepthChart().getOL().get(0));
                onField.add(team.getDepthChart().getOL().get(1));
                onField.add(team.getDepthChart().getOL().get(2));
                onField.add(team.getDepthChart().getOL().get(3));
                onField.add(team.getDepthChart().getOL().get(4));
                onField.add(team.getDepthChart().getWR().get(0));
                onField.add(team.getDepthChart().getWR().get(1));
                onField.add(team.getDepthChart().getWR().get(2));
                onField.add(team.getDepthChart().getWR().get(3));
                targets.clear();
                targets.add(team.getDepthChart().getRB().get(0));
                targets.add(team.getDepthChart().getWR().get(0));
                targets.add(team.getDepthChart().getWR().get(1));
                targets.add(team.getDepthChart().getWR().get(2));
                targets.add(team.getDepthChart().getWR().get(3));
            }


        }

    }


}


