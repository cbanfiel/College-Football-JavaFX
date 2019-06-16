package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String name;
    private String firstName;
    private String lastName;
    private int rating;
    private String position;
    private String year;
    private int number;
    private String team;
    private int prevRating = 0;
    private int depthchartpos;
    private PlayerStats stats = new PlayerStats();
    private boolean injured;
    private int injurySeverity;
    private int injuryLength;
    Image face;
    private boolean currentRedshirt = false;
    private boolean wasRedshirted = false;
    private int age;
    // ratings
    private int passAccuracy;
    private int passStrength;
    private int speed;
    private int strength;
    private int defense;
    private int block;
    private int carry;
    private int recieve;
    private int kickPower;
    private int kickAccuracy;

    private ArrayList<PlayerStats> gameStatsHistory = new ArrayList<>();
    private PlayerStats gameStats = new PlayerStats();

    public ArrayList<PlayerStats> getGameStatsHistory() {
        return gameStatsHistory;
    }

    public void setGameStatsHistory(ArrayList<PlayerStats> gameStatsHistory) {
        this.gameStatsHistory = gameStatsHistory;
    }

    public PlayerStats getGameStats() {
        return gameStats;
    }

    public void startNewGameStats(){
        this.gameStats = new PlayerStats();
    }

    public void setGameStats(PlayerStats gameStats) {
        this.gameStats = gameStats;
    }

    public void setRedshirt() {
        currentRedshirt = true;
        wasRedshirted = true;
        rating *= -1;
    }

    public void clearRedshirt() {
        currentRedshirt = false;
        wasRedshirted = false;
    }

    public boolean isCurrentRedshirt() {
        return currentRedshirt;
    }

    public boolean isWasRedshirted() {
        return wasRedshirted;
    }

    public void removeRedshirt() {
        currentRedshirt = false;
        rating *= -1;
    }

    public Image getFace() {
        return face;
    }

    public void setFace(Image face) {
        this.face = face;
    }

    public boolean isInjured() {
        return injured;
    }

    public void setInjury(int injuryLength, int injurySeverity) {
        this.injured = true;
        this.injuryLength = injuryLength;
        this.injurySeverity = injurySeverity;
        rating -= injurySeverity;
    }

    public void clearInjury() {
        this.injured = false;
        this.injuryLength = 0;
        this.injurySeverity = 0;
    }

    public void heal() {
        injuryLength -= 1;
        if (injuryLength <= 0) {
            rating += injurySeverity;
            injuryLength = 0;
            injured = false;
        }

    }

    public PlayerStats getStats() {
        return stats;
    }

    @Override
    public String toString() {
        if (wasRedshirted) {

            if (injured) {
                return "*INJURED*" + "RS" + " " + year + " " + position + " #" + number + " " + name + " OVR:" + rating;
            }
            return "RS" + " " + year + " " + position + " #" + number + " " + name + " OVR:" + rating;
        } else {
            if (injured) {
                return "*INJURED*" + year + " " + position + " #" + number + " " + name + " OVR:" + rating;
            }
            return year + " " + position + " #" + number + " " + name + " OVR:" + rating;
        }
    }

    //college
    public Player(String name, int rating, String position, String team, int number, String year) {
        this.name = name;
        this.rating = rating;
        this.position = position;
        this.team = team;
        this.number = number;
        this.year = year;
        setRatings();

    }

    public void setRatings() {
        Random rand = new Random();
        this.passAccuracy = rating;
        this.passStrength = rating;
        this.speed = rating;
        this.strength = rating;
        this.defense = rating;
        this.block = rating;
        this.carry = rating;
        this.recieve = rating;
        this.kickPower = rating;
        this.kickAccuracy = rating;

        int mod = Tools.scale(35, 0, 40, 99, this.rating);
        switch (this.position) {
            case "QB":
                this.passAccuracy = rand.nextInt(10) + 55 + mod;
                this.passStrength = rand.nextInt(10) + 55 + mod;
                this.speed = rand.nextInt(39) + 50;
                this.strength = rand.nextInt(10) + 40;
                this.defense = rand.nextInt(10) + 40;
                this.block = rand.nextInt(10) + 40;
                this.carry = rand.nextInt(10) + speed;
                this.recieve = rand.nextInt(20) + 40;
                this.kickPower = rand.nextInt(10) + 40;
                this.kickAccuracy = rand.nextInt(10) + 40;
                break;
            case "RB":
                this.speed = rand.nextInt(10) + 60 + mod;
                this.carry = rand.nextInt(10) + 60 + mod;
                this.recieve = rand.nextInt(10) + 40 + mod;
                this.strength = rand.nextInt(10) + 40 + mod;
                this.passAccuracy = rand.nextInt(10) + 40;
                this.passStrength = rand.nextInt(10) + 40;
                this.defense = rand.nextInt(10) + 40;
                this.block = rand.nextInt(10) + 40;
                this.kickPower = rand.nextInt(10) + 40;
                this.kickAccuracy = rand.nextInt(10) + 40;
                break;


            case "WR":
                this.speed = rand.nextInt(10) + 65 + mod;
                this.carry = rand.nextInt(10) + 35 + mod;
                this.recieve = rand.nextInt(10) + 55 + mod;
                this.strength = rand.nextInt(10) + 20 + mod;
                this.passAccuracy = rand.nextInt(10) + 40;
                this.passStrength = rand.nextInt(10) + 40;
                this.defense = rand.nextInt(10) + 40;
                this.block = rand.nextInt(10) + 40;
                this.kickPower = rand.nextInt(10) + 40;
                this.kickAccuracy = rand.nextInt(10) + 40;
                break;

            case "TE":
                this.speed = rand.nextInt(10) + 55 + mod;
                this.carry = rand.nextInt(10) + 35 + mod;
                this.recieve = rand.nextInt(10) + 45 + mod;
                this.strength = rand.nextInt(10) + 40 + mod;
                this.passAccuracy = rand.nextInt(10) + 40;
                this.passStrength = rand.nextInt(10) + 40;
                this.defense = rand.nextInt(10) + 40;
                this.block = rand.nextInt(20) + 40;
                this.kickPower = rand.nextInt(10) + 40;
                this.kickAccuracy = rand.nextInt(10) + 40;
                break;

            case "OL":
                this.passAccuracy = rand.nextInt(10) + 40;
                this.passStrength = rand.nextInt(10) + 40;
                this.speed = rand.nextInt(10) + 40;
                this.strength = rand.nextInt(10) + 50 + mod;
                this.defense = rand.nextInt(10) + 40;
                this.block = rand.nextInt(10) + 50 + mod;
                this.carry = rand.nextInt(10) + 40;
                this.recieve = rand.nextInt(10) + 40;
                this.kickPower = rand.nextInt(10) + 40;
                this.kickAccuracy = rand.nextInt(10) + 40;
                break;

            case "DL":
                this.passAccuracy = rand.nextInt(10) + 40;
                this.passStrength = rand.nextInt(10) + 40;
                this.speed = rand.nextInt(10) + 40 + mod;
                this.strength = rand.nextInt(10) + 60 + mod;
                this.defense = rand.nextInt(10) + 60 + mod;
                this.block = rand.nextInt(10) + 40;
                this.carry = rand.nextInt(10) + 40;
                this.recieve = rand.nextInt(10) + 40;
                this.kickPower = rand.nextInt(10) + 40;
                this.kickAccuracy = rand.nextInt(10) + 40;
                break;

            case "LB":
                this.passAccuracy = rand.nextInt(10) + 40;
                this.passStrength = rand.nextInt(10) + 40;
                this.speed = rand.nextInt(20) + 60;
                this.strength = rand.nextInt(10) + 40;
                this.defense = rand.nextInt(10) + 60 + mod;
                this.block = rand.nextInt(10) + 40;
                this.carry = rand.nextInt(10) + 40;
                this.recieve = rand.nextInt(10) + 40 + mod;
                this.kickPower = rand.nextInt(10) + 40;
                this.kickAccuracy = rand.nextInt(10) + 40;
                break;

            case "DB":
                this.passAccuracy = rand.nextInt(10) + 40;
                this.passStrength = rand.nextInt(10) + 40;
                this.speed = rand.nextInt(10) + 65 + mod;
                this.strength = rand.nextInt(10) + 40;
                this.defense = rand.nextInt(10) + 60 + mod;
                this.block = rand.nextInt(10) + 40;
                this.carry = rand.nextInt(20) + 40;
                this.recieve = rand.nextInt(10) + 40 + mod;
                this.kickPower = rand.nextInt(10) + 40;
                this.kickAccuracy = rand.nextInt(10) + 40;
                break;

            case "K":
                this.passAccuracy = rand.nextInt(10) + 40;
                this.passStrength = rand.nextInt(10) + 40;
                this.speed = rand.nextInt(10) + 40;
                this.strength = rand.nextInt(10) + 40;
                this.defense = rand.nextInt(10) + 40;
                this.block = rand.nextInt(10) + 40;
                this.carry = rand.nextInt(10) + 40;
                this.recieve = rand.nextInt(10) + 40;
                this.kickPower = rand.nextInt(10) + 60 + mod;
                this.kickAccuracy = rand.nextInt(10) + 60 + mod;
                break;


        }

        if (this.passAccuracy >= 99) {
            this.passAccuracy = 99;
        }
        if (this.passStrength >= 99) {
            this.passStrength = 99;
        }

        if (this.speed > 99) {
            this.speed = 99;
        }

        if (this.strength > 99) {
            this.strength = 99;
        }
        if (this.defense > 99) {
            this.defense = 99;
        }
        if (this.block > 99)
            this.block = 99;
        if (this.carry > 99) {
            this.carry = 99;
        }
        if (this.recieve > 99) {
            this.recieve = 99;
        }
        if (this.kickPower > 99) {
            kickPower = 99;
        }
        if (this.kickAccuracy > 99) {
            kickAccuracy = 99;
        }
        calculateRating();
    }

    public void calculateRating() {
        switch (this.position) {
            case "QB":
                double rat = (speed*0.2) + (passAccuracy*0.4) + (passStrength*0.4);
                rating = (int) rat;
                break;
            case "RB":
                rating = (recieve + speed + strength + carry) / 4;
                break;

            case "WR":
                rating = (recieve + speed) / 2;
                break;

            case "TE":
                rating = (recieve + speed) / 2;
                break;

            case "OL":
                rating = (block + strength) / 2;
                break;

            case "DL":
                rating = (speed + defense + strength) / 4;
                break;

            case "LB":
                rating = (speed + defense + recieve) / 3;
                break;

            case "DB":
                rating = (speed + defense + recieve) / 3;
                break;

            case "K":
                rating = (kickAccuracy + kickPower) / 2;
                break;


        }
    }


    public String playerRatings() {
        return "PASS ACC: " + passAccuracy + "\n" +
                "PASS STR: " + passStrength + "\n" +
                "SPD: " + speed + "\n" +
                "STR: " + strength + "\n" +
                "DEF: " + defense + "\n" +
                "BLOCK: " + block + "\n" +
                "CARRY: " + carry + "\n" +
                "RECIEVE: " + recieve + "\n" +
                "KICKPOW: " + kickPower + "\n" +
                "KICKACC: " + kickAccuracy + "\n";
    }


    public String playerInfo() {
        if (injured) {
            return "Name: " + name + "\n" +
                    "Position: " + position + "\n" +
                    "Number: " + number + "\n" +
                    "Rating: " + rating + "\n" +
                    "Previous Years Rating: " + prevRating + "\n" +
                    "Year: " + year + "\n\n" +
                    "INJURY REPORT" + "\n" +
                    "Injury Severity: " + injurySeverity + " (0-30) \n" +
                    "Injury Length: " + injuryLength + " Weeks" + "\n" + getStats();
        } else {
            return "Name: " + name + "\n" +
                    "Position: " + position + "\n" +
                    "Number: " + number + "\n" +
                    "Rating: " + rating + "\n" +
                    "Previous Years Rating: " + prevRating + "\n" +
                    "Year: " + year + "\n" + "\n" + getStats();

        }
    }

    public void setDepthchartpos(int depthchartpos) {
        this.depthchartpos = depthchartpos;
    }

    public int getDepthchartpos() {
        return depthchartpos;
    }

    public int getPrevRating() {
        return prevRating;
    }

    public void setPrevRating(int prevRating) {
        this.prevRating = prevRating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public int getNumber() {
        return number;
    }

    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getPosition() {
        return position;
    }

    public int getPassAccuracy() {
        return passAccuracy;
    }

    public int getPassStrength() {
        return passStrength;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getBlock() {
        return block;
    }

    public int getCarry() {
        return carry;
    }

    public int getRecieve() {
        return recieve;
    }

    public int getKickPower() {
        return kickPower;
    }

    public int getKickAccuracy() {
        return kickAccuracy;
    }

    //return player stats for stupid javafx trash tableview

    public int getRushingTouchdowns() {
        return stats.getRushingTouchdowns();
    }

    public int getReceivingTouchdowns() {
        return stats.getReceivingTouchdowns();
    }

    public int getPassingYards() {
        return stats.getPassingYards();
    }

    public int getCompletions() {
        return stats.getCompletions();
    }

    public int getPassAttempts() {
        return stats.getPassAttempts();
    }

    public int getPassingTouchdowns() {
        return stats.getPassingTouchdowns();
    }

    public int getInterceptionsThrown() {
        return stats.getInterceptionsThrown();
    }

    public int getFumbles() {
        return stats.getFumbles();
    }

    public int getKicksMade() {
        return stats.getKicksMade();
    }

    public int getKicksAttempted() {
        return stats.getKicksAttempted();
    }

    public int getTackles() {
        return stats.getTackles();
    }

    public int getSacks() {
        return stats.getSacks();
    }

    public int getInterceptions() {
        return stats.getInterceptions();
    }

    public int getFumblesRecovered() {
        return stats.getFumblesRecovered();
    }

    public int getKicksBlocked() {
        return stats.getKicksBlocked();
    }

    public int getReturnYards() {
        return stats.getReturnYards();
    }

    public int getReturnTouchdowns() {
        return stats.getReturnTouchdowns();
    }

    public int getCatches() {
        return stats.getCatches();
    }

    public int getRushAttempts() {
        return stats.getRushAttempts();
    }

    public int getRushingYards() {
        return stats.getRushingYards();
    }

    public int getRecieivingYards() {
        return stats.getRecieivingYards();
    }

    public String getAverageRushingYards() {
        return stats.getAverageRushingYards();
    }

    public String getCompletionPercentage() {
        return stats.getCompletionPercentage();
    }
}
