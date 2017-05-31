
public class HighScoreItem {
    public HighScoreItem(String usrName, int seconds, int millis, Difficulty difficulty) {
        this.usrName = usrName;
        this.seconds = seconds;
        this.millis = millis;
        this.difficulty = difficulty;
    }

    enum Difficulty {
        SMALL,
        MEDIUM,
        LARGE
    }
    public String usrName;
    public int seconds;
    public int millis;
    public Difficulty difficulty;

}

