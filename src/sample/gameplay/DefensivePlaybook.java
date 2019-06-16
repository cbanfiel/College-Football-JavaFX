package sample.gameplay;

import sample.Player;
import sample.Team;

import java.util.ArrayList;
import java.util.Random;

public class DefensivePlaybook {
    Team team;
    ArrayList<Player> onField;
    String name;
    int DLmodifier;
    int LBmodifier;
    int DBmodifier;
    ArrayList<Player> DL;
    ArrayList<Player> LB;
    ArrayList<Player> DB;
    String[] types;

    public ArrayList<Player> getDL() {
        return DL;
    }

    public ArrayList<Player> getLB() {
        return LB;
    }

    public ArrayList<Player> getDB() {
        return DB;
    }

    public ArrayList<Player> getOnField() {
        return onField;
    }

    public int getDLmodifier() {
        return DLmodifier;
    }

    public int getLBmodifier() {
        return LBmodifier;
    }

    public int getDBmodifier() {
        return DBmodifier;
    }

    public DefensivePlaybook(Team team, String name) {
        onField = new ArrayList<>();
        this.team = team;
        this.name = name;
        setPlaybook();
//        System.out.println(team.getName() + "playbook set");
    }

    public DefensivePlaybook(Team team){
        onField = new ArrayList<>();
        this.team = team;
        types = new String[] {"4-3","3-4", "3-3-5", "5-3", "5-2", "4-2-5"};
        this.name = pickPlaybook();
        setPlaybook();
//        System.out.println(team.getName() + "playbook set");
    }




    public void setPlaybook(){
        if(name.equals("4-3")){
            onField.add(team.getDepthChart().getDL().get(0));
            onField.add(team.getDepthChart().getDL().get(1));
            onField.add(team.getDepthChart().getDL().get(2));
            onField.add(team.getDepthChart().getDL().get(3));
            onField.add(team.getDepthChart().getLB().get(0));
            onField.add(team.getDepthChart().getLB().get(1));
            onField.add(team.getDepthChart().getLB().get(2));
            onField.add(team.getDepthChart().getDB().get(0));
            onField.add(team.getDepthChart().getDB().get(1));
            onField.add(team.getDepthChart().getDB().get(2));
            onField.add(team.getDepthChart().getDB().get(3));
            setPositions();
        }

        if(name.equalsIgnoreCase("3-4")){
            onField.add(team.getDepthChart().getDL().get(0));
            onField.add(team.getDepthChart().getDL().get(1));
            onField.add(team.getDepthChart().getDL().get(2));
            onField.add(team.getDepthChart().getLB().get(0));
            onField.add(team.getDepthChart().getLB().get(1));
            onField.add(team.getDepthChart().getLB().get(2));
            onField.add(team.getDepthChart().getLB().get(3));
            onField.add(team.getDepthChart().getDB().get(0));
            onField.add(team.getDepthChart().getDB().get(1));
            onField.add(team.getDepthChart().getDB().get(2));
            onField.add(team.getDepthChart().getDB().get(3));
            setPositions();
        }

        if(name.equalsIgnoreCase("3-3-5")){
            onField.add(team.getDepthChart().getDL().get(0));
            onField.add(team.getDepthChart().getDL().get(1));
            onField.add(team.getDepthChart().getDL().get(2));
            onField.add(team.getDepthChart().getLB().get(0));
            onField.add(team.getDepthChart().getLB().get(1));
            onField.add(team.getDepthChart().getLB().get(2));
            onField.add(team.getDepthChart().getDB().get(0));
            onField.add(team.getDepthChart().getDB().get(1));
            onField.add(team.getDepthChart().getDB().get(2));
            onField.add(team.getDepthChart().getDB().get(3));
            onField.add(team.getDepthChart().getDB().get(4));
            setPositions();
        }

        if(name.equalsIgnoreCase("5-3")){
            onField.add(team.getDepthChart().getDL().get(0));
            onField.add(team.getDepthChart().getDL().get(1));
            onField.add(team.getDepthChart().getDL().get(2));
            onField.add(team.getDepthChart().getDL().get(3));
            onField.add(team.getDepthChart().getDL().get(4));
            onField.add(team.getDepthChart().getLB().get(0));
            onField.add(team.getDepthChart().getLB().get(1));
            onField.add(team.getDepthChart().getLB().get(2));
            onField.add(team.getDepthChart().getDB().get(0));
            onField.add(team.getDepthChart().getDB().get(1));
            onField.add(team.getDepthChart().getDB().get(2));
            setPositions();
        }

        if(name.equalsIgnoreCase("5-2")){
            onField.add(team.getDepthChart().getDL().get(0));
            onField.add(team.getDepthChart().getDL().get(1));
            onField.add(team.getDepthChart().getDL().get(2));
            onField.add(team.getDepthChart().getDL().get(3));
            onField.add(team.getDepthChart().getDL().get(4));
            onField.add(team.getDepthChart().getLB().get(0));
            onField.add(team.getDepthChart().getLB().get(1));
            onField.add(team.getDepthChart().getDB().get(0));
            onField.add(team.getDepthChart().getDB().get(1));
            onField.add(team.getDepthChart().getDB().get(2));
            onField.add(team.getDepthChart().getDB().get(3));
            setPositions();
        }


        if(name.equalsIgnoreCase("4-2-5")){
            onField.add(team.getDepthChart().getDL().get(0));
            onField.add(team.getDepthChart().getDL().get(1));
            onField.add(team.getDepthChart().getDL().get(2));
            onField.add(team.getDepthChart().getDL().get(3));
            onField.add(team.getDepthChart().getLB().get(0));
            onField.add(team.getDepthChart().getLB().get(1));
            onField.add(team.getDepthChart().getDB().get(0));
            onField.add(team.getDepthChart().getDB().get(1));
            onField.add(team.getDepthChart().getDB().get(2));
            onField.add(team.getDepthChart().getDB().get(3));
            onField.add(team.getDepthChart().getDB().get(4));
            setPositions();
        }


    }

    private void setPositions() {
        DL = new ArrayList<>();
        LB = new ArrayList<>();
        DB = new ArrayList<>();
        for(int i=0; i<onField.size(); i++){
            Player play = onField.get(i);
            if(play.getPosition().equalsIgnoreCase("dl")){
                DL.add(play);
            }
            else if(play.getPosition().equalsIgnoreCase("lb")){
                LB.add(play);
            }
            else if(play.getPosition().equalsIgnoreCase("db")){
                DB.add(play);
            }
        }
    }

    public String pickPlaybook(){
        Random random = new Random();
        return types[random.nextInt(types.length)];
    }

    @Override
    public String toString() {
        return "DefensivePlaybook{" +
                "team=" + team +
                ", onField=" + onField +
                ", name='" + name + '\'' +
                ", DLmodifier=" + DLmodifier +
                ", LBmodifier=" + LBmodifier +
                ", DBmodifier=" + DBmodifier +
                '}';
    }
}
