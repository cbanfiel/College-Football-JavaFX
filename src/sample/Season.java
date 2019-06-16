package sample;

public class Season {
    private int wins;
    private int losses;
    private int rank;

    public Season(int wins, int losses, int rank) {
        this.wins = wins;
        this.losses = losses;
        this.rank = rank;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getRank() {
        return rank;
    }
}
