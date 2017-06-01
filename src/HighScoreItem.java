/**
 * HighScoreItem类的声明和包装
 * <p>4个公开的变量：名字，时间秒，时间毫秒，枚举类型的难度。一个构造函数<br>
 *  @author 伍淼
 */

public class HighScoreItem {

    public HighScoreItem() {
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

