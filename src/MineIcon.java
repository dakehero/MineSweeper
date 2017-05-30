import javax.swing.*;
import java.io.File;

public class MineIcon {
    private static Icon mineIcon=new ImageIcon("resource"+File.separator+"mineIcon.png");
    private static Icon suspectIcon=new ImageIcon("resource"+File.separator+"flagIcon.png");
    private static Icon oneIcon=new ImageIcon("resource"+File.separator+"oneIcon.png");
    private static Icon twoIcon=new ImageIcon("resource"+File.separator+"twoIcon.png");
    private static Icon threeIcon=new ImageIcon("resource"+File.separator+"threeIcon.png");
    private static Icon fourIcon=new ImageIcon("resource"+File.separator+"fourIcon.png");
    private static Icon fiveIcon=new ImageIcon("resource"+File.separator+"fiveIcon.png");
    private static Icon sixIcon=new ImageIcon("resource"+File.separator+"sixIcon.png");
    private static Icon sevenIcon=new ImageIcon("resource"+File.separator+"sevenIcon.png");
    private static Icon eightIcon=new ImageIcon("resource"+File.separator+"eightIcon.png");

    public static Icon getMineIcon(){
        return mineIcon;
    }

    public static Icon getSuspectIcon(){
        return suspectIcon;
    }
    public static Icon getNumberIcon(int mineCount){
        switch (mineCount){
            case 1: return oneIcon;
            case 2: return twoIcon;
            case 3: return threeIcon;
            case 4: return fourIcon;
            case 5: return fiveIcon;
            case 6: return sixIcon;
            case 7: return sevenIcon;
            case 8: return eightIcon;
            default: return null;
        }
    }
}
