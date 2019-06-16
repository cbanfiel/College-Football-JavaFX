//package sample;
//
//import java.util.Random;
//
//public class SimGame {
//    Random rand = new Random();
//    Team homeTeam;
//    Team awayTeam;
//    int homeTeamScore;
//    int awayTeamScore;
//
//    int down;
//    int timeRemaining;
//    boolean possession;
////true home.. false away
//
//
//    public void homePossesion() {
//        while (down < 4) {
//            int playcall = rand.nextInt(100);
//            if (playcall < homeTeam.getHeadCoach().getPasspercentage()) {
//                pass();
//            } else {
//                run();
//            }
//        }
//        if (down == 4 && homeTeamScore < awayTeamScore && timeRemaining <= 4) {
//            pass();
//        }
//
//    }
//
//    public void awayPossesion() {
//
//    }
//
//    public void pass(Team off, Team def) {
//        int yardsGained = 0;
//        int random = rand.nextInt(150);
//        int mod;
//        off.getDepthChart().getQB().get(0).getStats().setPassAttempts(1);
//        if (off.getDepthChart().getQB().get(0).getRating() + LineInteraction(off, def) + lbRbInteraction(off, def) + dbWrInteraction(off, def) > random) {
//            off.getDepthChart().getQB().get(0).getStats().setCompletions(1);
//            mod = rand.nextInt(10);
//            if (mod > 8) {
//                yardsGained += longGain();
//            } else if (mod > 4) {
//                yardsGained += mediumGain();
//            } else {
//                yardsGained += shortGain();
//            }
//            mod = rand.nextInt(10)+1;
//            if(mod>7){
//                off.getDepthChart().getWR().get(0).getStats().setYards(yardsGained);
//                off.getDepthChart().getWR().get(0).getStats().setCatches(1);
//            }else if(mod>4){
//                off.getDepthChart().getWR().get(1).getStats().setYards(yardsGained);
//                off.getDepthChart().getWR().get(1).getStats().setCatches(1);
//            }else if(mod>2){
//                off.getDepthChart().getWR().get(2).getStats().setYards(yardsGained);
//                off.getDepthChart().getWR().get(2).getStats().setCatches(1);
//            }
//            else{
//                off.getDepthChart().getTE().get(0).getStats().setYards(yardsGained);
//                off.getDepthChart().getTE().get(0).getStats().setCatches(1);
//            }
//        } else {
//            mod = rand.nextInt(10);
//            if (mod > 9) {
//                //inteception
//                off.getDepthChart().getQB().get(0).getStats().setInterceptionsThrown(1);
//                mod = rand.nextInt(10)+1;
//                switch (mod) {
//                    case 1:
//                        def.getDepthChart().getDB().get(0).getStats().setInterceptions(1);
//                        break;
//                    case 2:
//                        def.getDepthChart().getDB().get(0).getStats().setInterceptions(1);
//                        break;
//                    case 3:
//                        def.getDepthChart().getDB().get(1).getStats().setInterceptions(1);
//                        break;
//                    case 4:
//                        def.getDepthChart().getDB().get(1).getStats().setInterceptions(1);
//                        break;
//                    case 5:
//                        def.getDepthChart().getDB().get(2).getStats().setInterceptions(1);
//                        break;
//                    case 6:
//                        def.getDepthChart().getDB().get(3).getStats().setInterceptions(1);
//                        break;
//                    case 7:
//                        def.getDepthChart().getLB().get(0).getStats().setInterceptions(1);
//                        break;
//                    case 8:
//                        def.getDepthChart().getLB().get(1).getStats().setInterceptions(1);
//                        break;
//                    case 9:
//                        def.getDepthChart().getLB().get(2).getStats().setInterceptions(1);
//                        break;
//
//
//
//                }
//            }
//
//        }
//
//    }
//
//    public void run() {
//
//    }
//
//    public int LineInteraction(Team off, Team def) {
//        int defSum = 0;
//        int offSum = 0;
//        defSum += def.getDepthChart().getDL().get(0).getRating();
//        defSum += def.getDepthChart().getDL().get(1).getRating();
//        defSum += def.getDepthChart().getDL().get(2).getRating();
//        defSum += def.getDepthChart().getDL().get(3).getRating();
//        offSum += off.getDepthChart().getOL().get(0).getRating();
//        offSum += off.getDepthChart().getOL().get(1).getRating();
//        offSum += off.getDepthChart().getOL().get(2).getRating();
//        offSum += off.getDepthChart().getOL().get(3).getRating();
//        offSum += off.getDepthChart().getOL().get(4).getRating();
//
//        double modifer = defSum + offSum * 0.1;
//
//        return (int) modifer;
//
//    }
//
//
//    public int dbWrInteraction(Team off, Team def) {
//        int defSum = 0;
//        int offSum = 0;
//        defSum += def.getDepthChart().getDB().get(0).getRating();
//        defSum += def.getDepthChart().getDB().get(1).getRating();
//        defSum += def.getDepthChart().getDB().get(2).getRating();
//        defSum += def.getDepthChart().getDB().get(3).getRating();
//        offSum += off.getDepthChart().getWR().get(0).getRating();
//        offSum += off.getDepthChart().getWR().get(1).getRating();
//        offSum += off.getDepthChart().getWR().get(2).getRating();
//        offSum += off.getDepthChart().getTE().get(0).getRating();
//
//        double modifer = defSum + offSum * 0.1;
//
//        return (int) modifer;
//
//    }
//
//    public int lbRbInteraction(Team off, Team def) {
//        int defSum = 0;
//        int offSum = 0;
//        defSum += def.getDepthChart().getLB().get(0).getRating();
//        defSum += def.getDepthChart().getLB().get(1).getRating();
//        defSum += def.getDepthChart().getLB().get(2).getRating();
//        offSum += off.getDepthChart().getRB().get(0).getRating();
//        offSum *= 2;
//        double modifer = defSum + offSum * 0.1;
//
//        return (int) modifer;
//
//    }
//
//    public int shortGain() {
//        int yards = rand.nextInt(10) - 3;
//        return yards;
//    }
//
//    public int mediumGain() {
//        int yards = rand.nextInt(10) + 7;
//        return yards;
//    }
//
//    public int longGain() {
//        int hugegain = rand.nextInt(10);
//        int yards;
//        if (hugegain > 7) {
//            yards = rand.nextInt(100) + 15;
//        } else {
//            yards = rand.nextInt(10) + 15;
//        }
//        return yards;
//    }
//
//
//}
