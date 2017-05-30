import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperActionListener implements ActionListener {
    private MineSweeper mineSweeper;
    private MineSweeperHelper mineSweeperHelper;

    public MineSweeperActionListener(MineSweeper mineSweeper,MineSweeperHelper helper){
        this.mineSweeper=mineSweeper;
        mineSweeperHelper=helper;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(e.getActionCommand().equals("New Game")){
            mineSweeperHelper.newGame(mineSweeper.rows,mineSweeper.columns);
            return;
        }
        else if(e.getActionCommand().equals("Small (8*8, 10 Mines)")){
            int previousRows = mineSweeper.rows;
            int previousColumns = mineSweeper.columns;

            mineSweeper.rows=8;
            mineSweeper.columns=8;
            mineSweeper.mines=10;

            mineSweeperHelper.newGame(previousRows,previousColumns);
            return;
        }
        else if(e.getActionCommand().equals("Medium (16*16,40 Mines)")){
            int previousRows = mineSweeper.rows;
            int previousColumns = mineSweeper.columns;

            mineSweeper.rows=16;
            mineSweeper.columns=16;
            mineSweeper.mines=40;

            mineSweeperHelper.newGame(previousRows,previousColumns);
            return;
        }
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
