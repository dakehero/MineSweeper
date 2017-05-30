import javax.swing.*;

public class MineIcon {
    //TODO:添加图片及其路径
    //TODO：实例化图标对象
    private static Icon mineIcon;
    private static Icon suspectIcon;
    private static Icon oneIcon;
    private static Icon twoIcon;
    private static Icon threeIcon;
    private static Icon fourIcon;
    private static Icon fiveIcon;
    private static Icon sixIcon;
    private static Icon sevenIcon;
    private static Icon eightIcon;

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
