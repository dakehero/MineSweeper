import javax.swing.*;
import java.awt.*;

public class MineSweeper {
    int columns=8;
    int rows =8;
    int mines=10;
    //窗体部分
    JFrame frame =new JFrame("MineSweeper");
    JPanel minePanel =new JPanel();
    JLabel minesRemainingLable = new JLabel("Remaining:");
    JLabel minesFoundLable=new JLabel("Found: 0");
    //雷区按钮
    JButton[][] mineButtons;
    Dimension buttonSize= new Dimension(20,20);
    //
    MineFeild mineField;
    MineSweeperMouseListener mouseListener;
    MineSweeperActionListener actionListener;
    MineSweeperHelper helper;

    public MineSweeper(){
        helper=new MineSweeperHelper();
        actionListener=new 
    }

}
