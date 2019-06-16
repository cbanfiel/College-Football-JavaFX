package sample;

import java.util.ArrayList;

public class DepthChart {
    ArrayList<Player> QB = new ArrayList<Player>();
    ArrayList<Player> RB = new ArrayList<Player>();
    ArrayList<Player> WR = new ArrayList<Player>();
    ArrayList<Player> TE = new ArrayList<Player>();
    ArrayList<Player> OL = new ArrayList<Player>();
    ArrayList<Player> DL = new ArrayList<Player>();
    ArrayList<Player> LB = new ArrayList<Player>();
    ArrayList<Player> DB = new ArrayList<Player>();
    ArrayList<Player> K = new ArrayList<Player>();


    public DepthChart(ArrayList<Player> QB, ArrayList<Player> RB, ArrayList<Player> WR, ArrayList<Player> TE, ArrayList<Player> OL, ArrayList<Player> DL, ArrayList<Player> LB, ArrayList<Player> DB, ArrayList<Player> k) {
        this.QB = QB;
        this.RB = RB;
        this.WR = WR;
        this.TE = TE;
        this.OL = OL;
        this.DL = DL;
        this.LB = LB;
        this.DB = DB;
        K = k;
        setPlayerDepthChartPos();
    }

    public ArrayList<Player> getQB() {
        return QB;
    }

    public ArrayList<Player> getRB() {
        return RB;
    }

    public ArrayList<Player> getWR() {
        return WR;
    }

    public ArrayList<Player> getTE() {
        return TE;
    }

    public ArrayList<Player> getOL() {
        return OL;
    }

    public ArrayList<Player> getDL() {
        return DL;
    }

    public ArrayList<Player> getLB() {
        return LB;
    }

    public ArrayList<Player> getDB() {
        return DB;
    }

    public ArrayList<Player> getK() {
        return K;
    }

    public void setPlayerDepthChartPos(){
        for(int i = 0; i<QB.size(); i++){
            Player player = QB.get(i);
            player.setDepthchartpos(i);
        }
        for(int i = 0; i<RB.size(); i++){
            Player player = RB.get(i);
            player.setDepthchartpos(i);
        }

        for(int i = 0; i<WR.size(); i++){
            Player player = WR.get(i);
            player.setDepthchartpos(i);
        }
        for(int i = 0; i<TE.size(); i++){
            Player player = TE.get(i);
            player.setDepthchartpos(i);
        }
        for(int i = 0; i<OL.size(); i++){
            Player player = OL.get(i);
            player.setDepthchartpos(i);
        }
        for(int i = 0; i<DL.size(); i++){
            Player player = DL.get(i);
            player.setDepthchartpos(i);
        }

        for(int i = 0; i<LB.size(); i++){
            Player player = LB.get(i);
            player.setDepthchartpos(i);
        }
        for(int i = 0; i<DB.size(); i++){
            Player player = DB.get(i);
            player.setDepthchartpos(i);
        }
        for(int i = 0; i<K.size(); i++){
            Player player = K.get(i);
            player.setDepthchartpos(i);
        }

    }

    public void reorderBasedOnPlayerDepthChartPos(){

    }

    @Override
    public String toString() {
        return "DepthChart{" +
                "QB=" + QB +
                ", RB=" + RB +
                ", WR=" + WR +
                ", TE=" + TE +
                ", OL=" + OL +
                ", DL=" + DL +
                ", LB=" + LB +
                ", DB=" + DB +
                ", K=" + K +
                '}';
    }
}
