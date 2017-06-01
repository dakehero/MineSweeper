import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 完成对菜单各项菜单项的监听操作
 * <p>包括新游戏，难度（small，medium，large），退出游戏</p>
 * @author 陈钰元
 */
public class MineSweeperActionListener implements ActionListener {
    private MineSweeper mineSweeper;
    private MineSweeperHelper mineSweeperHelper;

    public MineSweeperActionListener(MineSweeper mineSweeper,MineSweeperHelper helper){
        this.mineSweeper=mineSweeper;
        mineSweeperHelper=helper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //退出操作
        if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        //新游戏操作
        else if(e.getActionCommand().equals("NewGame")){
            mineSweeperHelper.newGame(mineSweeper.rows,mineSweeper.columns);//启动新一局游戏
            return;
        }
        //简单难度
        /*
        *首先记录当前游戏的行和列（用于清除游戏面板）
        * 重置行和列以及地雷数
        * 下同
         */
        else if(e.getActionCommand().equals("Small (8*8, 10 Mines)")){
            int previousRows = mineSweeper.rows;
            int previousColumns = mineSweeper.columns;

            mineSweeper.rows=8;
            mineSweeper.columns=8;
            mineSweeper.mines=10;

            mineSweeperHelper.newGame(previousRows,previousColumns);
            return;
        }
        //中级难度
        else if(e.getActionCommand().equals("Medium (16*16,40 Mines)")){
            int previousRows = mineSweeper.rows;
            int previousColumns = mineSweeper.columns;

            mineSweeper.rows=16;
            mineSweeper.columns=16;
            mineSweeper.mines=40;

            mineSweeperHelper.newGame(previousRows,previousColumns);
            return;
        }
        //高级难度
        else if(e.getActionCommand().equals("Large (16*32, 100 Mines)")){
            int previousRows = mineSweeper.rows;
            int previousColumns = mineSweeper.columns;

            mineSweeper.rows=16;
            mineSweeper.columns=32;
            mineSweeper.mines=100;

            mineSweeperHelper.newGame(previousRows,previousColumns);
            return;
        }
    }
}
