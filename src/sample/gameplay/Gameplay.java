package sample.gameplay;

/*
OffensivePlaybook.Plays selection = mixture of coaching, game score, game time;

Coaching Stats:
Run vs Pass
RB1 VS RB2;
Off Playbook: West Coast, Run n gun, balanced, run heavy, pass heavy, spread, option
Def Playbook: 4-3, 3-4, 3-3-5, 5-2-6
Aggresiveness: (how often will go for 4th and short) (also more big plays but more turnovers)

Def playbook determines how many of each pos are on field.
Off playbook determines whos on field / run vs pass/ aggresivness(formula to mix with coaching ratings)



Kicking
XP = easy
30yd = mid
40yd = hard
50yd = hardest


Need Punting

Field position:
0 = endzone
50 = mid

boolean inOppTerritory = false;
say place on the field = 56

if(over 50 than 50 - place = -6 + 50 = 44; switch to decrement(in opp field position =  true)


Things that happen in football game;
sacks = OL vs DL low possibility of LB and DB sacks but included
tackles = who has ball/ who is closest?
ints = (formula with ratings invovlved) who was targeted? who was defending. Aggresiveness tie in?
fumbles = who fumbled? who was near????????
injures maybe???

catches = who was targeted?
drops = who was targeted
qb attempts = qb throws to a wr
rb attempts =  rb

which wr/te? : take in from playbook ooh good idea.

Playbook class can have aggresiveness whos on field target percentages aggresieness

how will yardage be determined? random + interactions of players needed(ratings) + aggresiveness/play selection

Lets just go all in and do playbook plays so that you can have an actual play by play simulation.

Playbook Class holds plays;

OffensivePlaybook.Plays class will holds details about a play (main targets(pass play main targets WR1 30% WR2 29% RB1 25% etc),
yardage(if wr1 = target than +route trajectory (how many yards downfield are they) yards downfield if caught(then run after catch will be calculated)
, success rate, aggresiveness(risk factor)



RE-VAMP PLAYER RATINGS;
QB:
SPEED
PASS
AWARENESS
RUN
CARRY

RB
SPEED
RUN
CATCH
CARRY

WR
CATCH
SPEED
CARRY

OL
BLOCK

DL
BREAKBLOCK
TACKLE

LB
SPEED
AWARENESS
CATCH
TACKLE

DB
SPEED
AWARENESS
CATCH
TAKLE

K
KICK

ALL

SPEED
PASS
RUN
CATCH
CARRY
AWARENESS
BLOCK
BREAKBLOCK
TACKLE
KICK

INTERACTIONS:

SPEED vs SPEED
PASS vs AWARENESS & CATCH
RUN VS TACKLE & AWARENESS
CATCH VS AWARENESS??? Maybe just catch vs  itself
CARRY VS itself
AWARENESS VS PLAYER DEFENDINGS AWARENESS
BLOCK VS BREAKBLOCK

Maybe try step by step
1. OffensivePlaybook.Plays seleected
2. OL DL interactions
3. QB passes to WR1 (does it make it there?)
4. wr vs db interaction( determines catch vs drop vs int)
5. wr run vs db tackle(determines yardage after)


1. OffensivePlaybook.Plays selected
2. OL DL interactions
3. RB1 runs(fumble?)
4. tackled by(pick closest players)

determinning tackled by maybe based on yardage?
if -5-5yds = DL60% LB30% DB10%
if 5-10= DL10 LB60 DB30
if 10+ DL10 LB30 DB60

determinning yardage
run = DL vs OL then RB vs


determining yardage.. setup average and then interactions will move it up or down















 */


import sample.Player;
import sample.Team;

import java.util.ArrayList;
import java.util.Random;

public class Gameplay {
    Random rand = new Random();
    Team home;
    Team away;
    boolean homePossesion; //true home possesion false away possesion
    int currentYardMarker; //home is always ++ away is always--
    int down;
    int lineOfScrimmage;
    int time;
    int homeScore = 0;
    int awayScore = 0;
    String playByPlay;
    int firstDownMarker;
    String gameSummary;
    int qtrLength;
    OffensivePlaybook.Plays humanSelectedPlay=null;

    //SLIDERS
    //SPREAD
    int max=16;
    int min=1;

    public void setHumanSelectedPlay(OffensivePlaybook.Plays humanSelectedPlay) {
        this.humanSelectedPlay = humanSelectedPlay;
    }

    public int getQuarter() {


        if (time < qtrLength) {
            return 4;
        }
        if (time < (qtrLength * 2)) {
            return 3;
        }
        if (time < (qtrLength * 3)) {
            return 2;
        } else {
            return 1;
        }

    }

    //CACHED FOR GAMEPLAY
    int DLineRush = 0;
    int OLblock = 0;
    int recieverBigPlayAbility = 0;
    int dbBigPlayCoverage = 0;
    int totalDefense = 0;


    //TESTING PASS METHOD
    int completions = 0;
    int attempts = 0;
    int interceptionsThrown = 0;
    int sacks = 0;
    int ydg = 0;

    //TESTING RUN METHOD
    int rushes = 0;
    int rydg = 0;
    int rfumbles = 0;

    String infoString = "";

    public void info() {
        try {

            System.out.println("Completions:" + completions + "\n" + "Attempts " + attempts + "\n" + "INTS: " + interceptionsThrown + "\n" + "SACKS: " + sacks);
            System.out.println("PERCENTAGE: " + (completions * 100) / attempts);
            System.out.println("TOTAL YDS " + ydg);
            System.out.println("AVG YDS: " + ydg / completions);
        } catch (ArithmeticException e) {
        }
        System.out.println("Runs " + rushes);
        System.out.println("Yardage" + rydg);
        System.out.println("fumbles " + rfumbles);
        System.out.println("avg: " + rydg / rushes);

    }


    public String getGameSummary() {
        return gameSummary;
    }

    public void setGameSummary(String gameSummary) {
        this.gameSummary = gameSummary;
    }

    public boolean isHomePossesion() {
        return homePossesion;
    }

    public String getPlayByPlay() {
        return playByPlay;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public int getLineOfScrimmage() {
        return lineOfScrimmage;
    }

    //testing
    int fumbles = 0;
    int interceptions = 0;


    int averageRun = 3;
    int averagePass = 7;

    public void setRatings() {
    }


    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public int getCurrentYardMarker() {
        return currentYardMarker;
    }

    public int getDown() {
        return down;
    }

    public int getTime() {
        return time;
    }

    public Gameplay(Team home, Team away) {
        time = 140;
        qtrLength = time / 4;
        this.home = home;
        this.away = away;
    }

    public void flipCoin() {
        homePossesion = rand.nextBoolean();
    }


    public void playGame() {
        flipCoin();
        setYardMarker();
        lineOfScrimmage = currentYardMarker;
        down = 1;


    }


    public void setYardMarker() {
        if (homePossesion) {
            currentYardMarker = 25;
            lineOfScrimmage = currentYardMarker;
            firstDownMarker = lineOfScrimmage + 10;

        } else {
            currentYardMarker = 75;
            lineOfScrimmage = currentYardMarker;
            firstDownMarker = lineOfScrimmage - 10;

        }
    }

    public int getFirstDownMarker() {
        return firstDownMarker;
    }

    public void turnOver() {
        switchPossesion();
    }


    public OffensivePlaybook.Plays pickPlay(Team off, Team def) {

        if(humanSelectedPlay != null){

            OffensivePlaybook.Plays play = humanSelectedPlay;
            humanSelectedPlay = null;
            System.out.println(play.getPlayName());
            return play;
        }

        ArrayList<OffensivePlaybook.Plays> plays = off.getOffensivePlaybook().getPlays();
        int passvsrun = rand.nextInt(10);
        OffensivePlaybook.Plays play = off.getOffensivePlaybook().getPlays().get(rand.nextInt(off.getOffensivePlaybook().getPlays().size()));

        if (passvsrun >= 4) {
            while (!play.getPlayType().equalsIgnoreCase("pass")) {
                play = off.getOffensivePlaybook().getPlays().get(rand.nextInt(off.getOffensivePlaybook().getPlays().size()));
            }
        } else {
            while (!play.getPlayType().equalsIgnoreCase("run")) {
                play = off.getOffensivePlaybook().getPlays().get(rand.nextInt(off.getOffensivePlaybook().getPlays().size()));
            }
        }
        if (down == 3 && !thirdAndShort()) {
            while (!play.getPlayType().equalsIgnoreCase("pass")) {
                play = off.getOffensivePlaybook().getPlays().get(rand.nextInt(off.getOffensivePlaybook().getPlays().size()));
            }
        }

        if (time < (qtrLength / 4) && (isHomePossesion() && homeScore < awayScore) || time < (qtrLength / 4) && (!isHomePossesion() && homeScore > awayScore)) {
            while (!play.getPlayType().equalsIgnoreCase("pass")) {
                play = off.getOffensivePlaybook().getPlays().get(rand.nextInt(off.getOffensivePlaybook().getPlays().size()));
            }
        }

        return play;

    }

    public boolean thirdAndShort(){
        if(homePossesion && (firstDownMarker-currentYardMarker)<=4){
            return true;
        }
        if(!homePossesion && ((firstDownMarker-currentYardMarker)*-1)<=4){
            return true;
        }
        return false;
    }

    public boolean isGameCloseEnough() {
        if (homePossesion) {
            if (homeScore + 14 < awayScore) {
                return false;
            } else {
                return true;
            }
        } else {
            if (awayScore + 14 < homeScore) {
                return false;
            }
            return true;
        }
    }

    public boolean isWithInFGDistanceAndFGScore(Team off) {
        int diff;
        if (homeScore > awayScore) {
            diff = homeScore - awayScore;
        } else {
            diff = awayScore - homeScore;
        }

        boolean scoreMakesSense = false;
        Boolean fgInRange = false;
        switch (diff) {
            case 1:
            case 2:
            case 3:
            case 8:
            case 9:
            case 10:
                scoreMakesSense = true;
            default:
                scoreMakesSense = false;
        }

        if (scoreMakesSense) {
            int kickerDistance = scale(50, 30, 40, 99, off.getDepthChart().getK().get(0).getRating());

            if (homePossesion) {
                if (currentYardMarker + kickerDistance >= 100) {
                    fgInRange = true;
                }
            } else {
                if (currentYardMarker - kickerDistance <= 0) {
                    fgInRange = true;
                }
            }
        }
        return (scoreMakesSense && fgInRange);
    }

    public void goOnFourthDown(Team off) {
        if (homePossesion) {
            simPlayTree(pickPlay(home, away), home, away);
            if (currentYardMarker < firstDownMarker) {
                turnOver();
                playByPlay += " TURNOVER ON DOWNS!";
//                System.out.println("TURNOVER ON DOWNS" + homeScore + " " + awayScore);
            } else {
                down = 1;
                lineOfScrimmage = currentYardMarker;
                firstDownMarker = lineOfScrimmage + 10;
            }
        } else {
            simPlayTree(pickPlay(away, home), away, home);
            if (currentYardMarker > firstDownMarker) {
                turnOver();
                playByPlay += " TURNOVER ON DOWNS!";
//                System.out.println("TURNOVER ON DOWNS" + homeScore + " " + awayScore);

            }else {
//                System.out.println("Made it on 4th down" + homeScore + " " + awayScore + away.getName());

                down = 1;
                lineOfScrimmage = currentYardMarker;
                firstDownMarker = lineOfScrimmage - 10;
            }
        }

    }


    public void specialTeamsDecision(Team off) {

        if (time < (qtrLength / 4) && (isHomePossesion() && homeScore < awayScore) || time < (qtrLength / 4) && (!isHomePossesion() && homeScore > awayScore)) {
            if (isGameCloseEnough()) {
                if (isWithInFGDistanceAndFGScore(off)) {
                    kickFgIfInRange(off);
                    return;
                }
                goOnFourthDown(off);
                return;
            }
        }


        boolean fg = kickFgIfInRange(off);

        if (!fg) {
            punt();
        }
    }

    private boolean kickFgIfInRange(Team off) {
        int kickerDistance = scale(50, 30, 40, 99, off.getDepthChart().getK().get(0).getRating());
        Boolean fg = false;
        if (homePossesion) {
            if (currentYardMarker + kickerDistance >= 100) {
                kickFieldGoal(off);
                fg = true;
            }
        } else {
            if (currentYardMarker - kickerDistance <= 0) {
                kickFieldGoal(off);
                fg = true;
            }
        }
        return fg;
    }


    private void kickFieldGoal(Team off) {
        off.getDepthChart().getK().get(0).getStats().setKicksAttempted(1);
        off.getDepthChart().getK().get(0).getGameStats().setKicksAttempted(1);

//            System.out.println("KICK*****************************************");

        int scale = scale(95, 50, 40, 99, off.getDepthChart().getK().get(0).getRating());
        playByPlay = off.getDepthChart().getK().get(0) + " Attempts a fieldgoal but misses";

        if (scale > rand.nextInt(100)) {
            off.getDepthChart().getK().get(0).getStats().setKicksMade(1);
            off.getDepthChart().getK().get(0).getGameStats().setKicksMade(1);

            if (homePossesion) {
                playByPlay = off.getDepthChart().getK().get(0) + " Makes a fieldgoal";
                homeScore += 3;
                currentYardMarker = 75;
                lineOfScrimmage=currentYardMarker;
                firstDownMarker=lineOfScrimmage-10;
            } else {
                awayScore += 3;
                playByPlay = off.getDepthChart().getK().get(0) + " Makes a fieldgoal";
                currentYardMarker = 25;
                lineOfScrimmage=currentYardMarker;
                firstDownMarker=lineOfScrimmage+10;

            }
        }
        //KICK FG
        switchPossesion();
    }

    public void switchPossesion() {
        lineOfScrimmage = currentYardMarker;
        homePossesion = !homePossesion;
        if(homePossesion){
            firstDownMarker= lineOfScrimmage+10;
        }else{
            firstDownMarker= lineOfScrimmage-10;
        }
        down = 1;
    }

    private void punt() {
//        System.out.println("PUNT**********************************************************************");
//        System.out.println();
        playByPlay += " Punts it away!";
        if (homePossesion) {
            currentYardMarker += 40;
            if (currentYardMarker >= 100) {
                currentYardMarker = 75;
            }
            lineOfScrimmage = currentYardMarker;
            firstDownMarker = lineOfScrimmage - 10;
        } else {
            currentYardMarker -= 40;
            if (currentYardMarker <= 0) {
                currentYardMarker = 25;
            }
            lineOfScrimmage = currentYardMarker;
            firstDownMarker = lineOfScrimmage + 10;
        }
        switchPossesion();
    }

    public int scale(int max, int min, int baseMin, int baseMax, int valueIn) {
        return ((max - min) * (valueIn - baseMin) / (baseMax - baseMin)) + min;
    }

    public void checkForFirstAndGoal(){
        if(firstDownMarker>=100){
            firstDownMarker=100;
        }else if(firstDownMarker<=0){
            firstDownMarker=0;
        }
    }


    public void playPossesion() {

        playByPlay = "";


        if (time > 0 || homeScore == awayScore) {

            if (homePossesion) {
                if (down >= 4) {
                    specialTeamsDecision(home);
                } else {
                    simPlayTree(pickPlay(home, away), home, away);
                    firstDownMarker = lineOfScrimmage + 10;
                    checkForFirstAndGoal();
                    if (currentYardMarker >= firstDownMarker) {
                        down = 1;
                        lineOfScrimmage = currentYardMarker;
                        firstDownMarker = lineOfScrimmage + 10;
                        checkForFirstAndGoal();


                    } else {
                        down++;

                    }
                    if (currentYardMarker >= 100) {
                        homeScore += 7;
                        currentYardMarker = 75;
                        switchPossesion();
                        firstDownMarker = lineOfScrimmage - 10;
                    }
                }

            } else {
                if (down >= 4) {
                    specialTeamsDecision(away);
                } else {
                    simPlayTree(pickPlay(away, home), away, home);

                    firstDownMarker = lineOfScrimmage - 10;
                    checkForFirstAndGoal();
                    if (currentYardMarker <= firstDownMarker) {
                        down = 1;
                        lineOfScrimmage = currentYardMarker;
                        firstDownMarker = lineOfScrimmage - 10;
                        checkForFirstAndGoal();

                    } else {
                        down++;
                    }
                    if (currentYardMarker <= 0) {
                        awayScore += 7;
                        currentYardMarker = 25;
                        switchPossesion();
                        firstDownMarker = lineOfScrimmage + 10;
                    }
                }
            }
        }


//        System.out.println("DOWN " + down);
    }


    public void simPlayTree(OffensivePlaybook.Plays play, Team off, Team def) {


        //OL Blocking + scaled
        for (Player player : play.getOnField()) {
            if (player.getPosition().equalsIgnoreCase("OL")) {
                OLblock += (player.getBlock() + player.getStrength()) / 2;
            }
        }
        OLblock = scale(max, min, 200, 495, OLblock);
        infoString += "\n" + ("OLBLOCK =" + OLblock);

        //Cache Step 2 and scale
        for (Player player : play.getTargets()) {
            recieverBigPlayAbility += player.getSpeed();
        }
        recieverBigPlayAbility = scale(max, min, (40 * 5), (99 * 5), recieverBigPlayAbility);


        for (Player player : def.getDefensivePlaybook().getDB()) {
            dbBigPlayCoverage += (player.getSpeed() + player.getDefense()) / 2;
        }
        dbBigPlayCoverage = scale(max, min, (40 * 4), (99 * 6), dbBigPlayCoverage);

        //cache defense and scale
        for (Player player : def.getDefensivePlaybook().getOnField()) {
            totalDefense += player.getDefense();
        }
        totalDefense /= 11;
        totalDefense = scale(max, min, 40, 99, totalDefense);


        if (play.playType.equalsIgnoreCase("pass")) {

            if (isHomePossesion()) {
                currentYardMarker += pass(play, off, def);
            } else {
                currentYardMarker -= pass(play, off, def);
            }
        }
        if (play.playType.equalsIgnoreCase("run")) {
            if (isHomePossesion()) {
                currentYardMarker += run(play, off, def);
            } else {
                currentYardMarker -= run(play, off, def);
            }
        }
    }


    public int pass(OffensivePlaybook.Plays play, Team off, Team def) {
        time--;
        //
        //MODIFIERS
        int qbMod = play.getQbAccuracyMod();
        int yardMod = play.getYardsMod();
        int bigPlayMod = play.getBigPlayMod();


        //PASS RUSH
        int OLDLOutcome = OLblock - DLineRush;
        infoString += "\n" + ("OL DL OUTCOME: " + OLDLOutcome);

        //OUTCOME
        int random = rand.nextInt(100) + 1;
        if (random >= 90 - OLDLOutcome) {
            infoString += "\n" + ("He Has ALl Day");
            qbMod += rand.nextInt(15);
            bigPlayMod += rand.nextInt(5);
            //AlL Day
        } else if (random > 40 - OLDLOutcome) {
            infoString += "\n" + ("No change");
            //no change
        } else if (random > 10 - OLDLOutcome) {
            infoString += "\n" + ("QB is rushed in the pocket");
            qbMod -= rand.nextInt(15);
            yardMod -= rand.nextInt(5);
            bigPlayMod -= rand.nextInt(5);
            //rushed
        } else {
            infoString += "\n" + ("QB is sacked");
            //Sack Stats
            Player tackler = def.getDefensivePlaybook().getDL().get(rand.nextInt(def.getDefensivePlaybook().getDL().size()));
            tackler.getStats().setSacks(1);
            tackler.getGameStats().setSacks(1);

            int sackLoss = rand.nextInt(6) - 7;
            sacks++;
            return sackLoss;
            //sacked
        }


        //STEP 2 Broken Coverage Check
        int brokenCoverageCheck = recieverBigPlayAbility - dbBigPlayCoverage;

        //OUTCOME

        random = rand.nextInt(100) + 1;
        if (random >= 90 - (brokenCoverageCheck + bigPlayMod)) {
            //broken coverage
            infoString += "\n" + ("Broken Coverage");
            yardMod += rand.nextInt(40);
        } else {
            //solid coverage
            infoString += "\n" + ("Good Coverage");
        }


        //STEP 3 Throw
        Player qb = off.getDepthChart().getQB().get(0);
        //STATS
        qb.getStats().setPassAttempts(1);
        qb.getGameStats().setPassAttempts(1);

        //
        infoString += "\n" + (qb);
        int scaledQBAccuracy = scale(75, 35, 40, 99, qb.getPassAccuracy());
        infoString += "\n" + ("SCLAED QB ACCURACY:" + scaledQBAccuracy);
        attempts++;
        int dynamicAcc = scaledQBAccuracy + qbMod;
        infoString += "\n" + ("DYNAMIC Accuracy : " + dynamicAcc);
        if (dynamicAcc > rand.nextInt(100)) {
            //completed
            infoString += "\n" + ("Completed Pass");
            completions++;


        } else {
            //incomplete
            Player defender = def.getDefensivePlaybook().getDB().get(rand.nextInt(def.getDefensivePlaybook().getDB().size()));
            int interceptionChance = (defender.getDefense() + defender.getRecieve()) / 2;
            interceptionChance = scale(4, 0, 40, 99, interceptionChance);
            if (rand.nextInt(55) + interceptionChance >= 54) {
                //intercepted
                infoString += "\n" + ("Intercepted");
                defender.getStats().setInterceptions(1);
                defender.getGameStats().setInterceptions(1);

                qb.getStats().setInterceptionsThrown(1);
                qb.getGameStats().setInterceptionsThrown(1);

                turnOver();
                interceptionsThrown++;
                playByPlay = "#" + qb.getNumber() + " " + qb.getName() + " is intercepted by #" + defender.getNumber() + " " + defender.getName();
                return 0;
            }
            playByPlay = "#" + qb.getNumber() + " " + qb.getName() + " Throws an incomplete pass!";
            infoString += "\n" + ("Incomplete");

            return 0;
        }

        //STEP 4 YARDAGE

        //Select Reciever
        Player reciever = play.getTargets().get(rand.nextInt(play.getTargets().size()));
        //scale receiver rating
        int scaledReceiverAbility = scale(max, min, 40, 99, (reciever.getSpeed() + reciever.getRecieve()) / 2);
        infoString += "\n" + (reciever.getRating() + " and " + scaledReceiverAbility);


        infoString += "\n" + ("TOTAL DEFENSE:" + totalDefense);
        infoString += "\n" + ("BIGPLAY " + recieverBigPlayAbility);
        int recieverAdvantage = scaledReceiverAbility - totalDefense;
        if (recieverAdvantage < 0) {
            recieverAdvantage = 0;
        }
        infoString += "\n" + ("RECEIVER ADVANTAGE : " + recieverAdvantage);

        int yardage = yardMod + recieverAdvantage + rand.nextInt(15);


        infoString += "\n" + ("OLDL = " + OLDLOutcome + " brokencoverage= " + brokenCoverageCheck);
        infoString += "\n" + ("BIG PLAY MOD " + bigPlayMod + " QBMOD " + qbMod + " YARDMOD " + yardMod);
        infoString += "\n" + ("Yardage: " + yardage);
        ydg += yardage;

        qb.getStats().setCompletions(1);
        qb.getGameStats().setCompletions(1);

        qb.getStats().setPassingYards(yardage);
        qb.getGameStats().setPassingYards(yardage);

        reciever.getStats().setCatches(1);
        reciever.getStats().setRecieivingYards(yardage);
        reciever.getGameStats().setCatches(1);
        reciever.getGameStats().setRecieivingYards(yardage);


        if (resultedInTouchdown(yardage)) {
            qb.getStats().setPassingTouchdowns(1);
            reciever.getStats().setReceivingTouchdowns(1);
            qb.getGameStats().setPassingTouchdowns(1);
            reciever.getGameStats().setReceivingTouchdowns(1);
            playByPlay = "#" + qb.getNumber() + " " + qb.getName() + " completes a pass to #" + reciever.getNumber() + " " + reciever.getName() + " for a touchdown!";

        } else {
            Player tackler = def.getDefensivePlaybook().getOnField().get(rand.nextInt(def.getDefensivePlaybook().getOnField().size()));
            tackler.getStats().setTackles(1);
            tackler.getGameStats().setTackles(1);

            playByPlay = "#" + qb.getNumber() + " " + qb.getName() + " completes a pass to #" + reciever.getNumber() + " " + reciever.getName() + " for " + yardage + " yards";

        }

        return yardage;
    }


    public int run(OffensivePlaybook.Plays play, Team off, Team def) {
        //TIME Management
        time -= 2;
        //
        Player runner = play.getTargets().get(rand.nextInt(play.getTargets().size()));


        infoString += "\n" + ("run play");
        rushes++;
        int bigPlayMod = play.getBigPlayMod();
        int yardMod = play.getYardsMod();
        int yardage = 0;


        int OLDLOutcome = OLblock - DLineRush;
        infoString += "\n" + ("OL DL OUTCOME: " + OLDLOutcome);

        // STEP 1 RUN RUSH
        //
        // OUTCOME
        int random = rand.nextInt(100) + 1;
        if (random >= 90 - OLDLOutcome) {
            //Huge Hole
            infoString += "\n" + ("Huge Hole");
            bigPlayMod += rand.nextInt(5);
            yardMod += rand.nextInt(5);
        } else if (random > 40 - OLDLOutcome) {
            infoString += "\n" + ("No change");
            //no change
        } else if (random > 10 - OLDLOutcome) {
            //no hole
            infoString += "\n" + ("No Hole for the runner");
            yardMod -= rand.nextInt(3);
            bigPlayMod -= rand.nextInt(5);
        } else {
            infoString += "\n" + ("Loss of yards");
            yardage = rand.nextInt(5) - 5;
            Player defender = def.getDefensivePlaybook().getDL().get(rand.nextInt(def.getDefensivePlaybook().getDL().size()));
            defender.getStats().setTackles(1);
            defender.getGameStats().setTackles(1);

            playByPlay = "#" + runner.getNumber() + " " + runner.getName() + " is stuffed in the backfield for a loss of " + yardage + " yards";
            return yardage;
            //loss of yards
        }

        //STEP 2 CHECK BROKEN PLAY
        //STATS
        runner.getStats().setRushAttempts(1);
        runner.getGameStats().setRushAttempts(1);

        //

        int runnerBigPlayAbility = runner.getSpeed();
        //scale
        runnerBigPlayAbility = scale(max, min, 40, 99, runnerBigPlayAbility);
        int brokenPlayCheck = runnerBigPlayAbility - totalDefense;


        //OUTCOME

        int brokenPlayOdds = brokenPlayCheck + bigPlayMod;
        //boost min
        infoString += "\n" + ("initial " + brokenPlayOdds);
        if (brokenPlayOdds <= -4) {
            brokenPlayOdds = -4;
        }

        infoString += "\n" + (brokenPlayOdds);
        random = rand.nextInt(100) + 1;
        if (random >= 90 - (brokenPlayOdds)) {
            //broken play
            infoString += "\n" + ("Broken Play!");
            yardMod += rand.nextInt((runnerBigPlayAbility * 4) + 15);

        } else {
            //solid coverage
            infoString += "\n" + ("Run Covered Well");
        }

        //step 3 get inital yardage;
        int initialYardage = scale(5, 2, 40, 99, runnerBigPlayAbility);

        //step 4 get yardage after contact
        int runnerStrength = runner.getStrength();
        //scale
        runnerStrength = scale(max, min, 40, 99, runnerStrength);
        int yardsAfterContact = runnerStrength - totalDefense;

        random = rand.nextInt(100) + 1;
        if (random >= 90 - yardsAfterContact) {
            //break tackle
            infoString += "\n" + ("Broken Tackle!");
            yardMod += rand.nextInt(3) + 3;
        } else if (random > 30 - yardsAfterContact) {
            //good push
            infoString += "\n" + ("Good Push");
            yardMod += rand.nextInt(2) + 1;
        } else {
            //stopped immediately
            infoString += "\n" + ("Stopped Immediately");
        }


        if (rand.nextInt(2000) < runner.getCarry()) {
            //fumble
            infoString += "\n" + ("Fumble");
            rfumbles++;
            runner.getStats().setFumbles(1);
            runner.getGameStats().setFumbles(1);

            Player defender = def.getDefensivePlaybook().getOnField().get(rand.nextInt(def.getDefensivePlaybook().getOnField().size()));
            defender.getStats().setFumblesRecovered(1);
            defender.getGameStats().setFumblesRecovered(1);

            playByPlay = "#" + runner.getNumber() + " " + runner.getName() + " fumbles! Recovered by #" + defender.getNumber() + " " + defender.getName();
            turnOver();
            return 0;
        }

        int yards = initialYardage + yardMod + rand.nextInt(3);
        rydg += yards;
        infoString += "\n" + (initialYardage);
        infoString += "\n" + (yardMod);

        infoString += "\n" + ("Yards: " + yards);

        runner.getStats().setRushingYards(yards);
        if (resultedInTouchdown(yards)) {
            runner.getStats().setRushingTouchdowns(1);
            runner.getGameStats().setRushingTouchdowns(1);

            playByPlay = "#" + runner.getNumber() + " " + runner.getName() + " runs it in for a touchdown!";

        } else {
            Player tackler = def.getDefensivePlaybook().getOnField().get(rand.nextInt(def.getDefensivePlaybook().getOnField().size()));
            tackler.getStats().setTackles(1);
            tackler.getGameStats().setTackles(1);

            playByPlay = "#" + runner.getNumber() + " " + runner.getName() + " carries it for " + yards + " yards";
        }
        return yards;


    }

    public boolean resultedInTouchdown(int yards) {
        if (homePossesion) {
            return (currentYardMarker + yards >= 100);
        } else {
            return (currentYardMarker - yards <= 0);
        }

    }


}
