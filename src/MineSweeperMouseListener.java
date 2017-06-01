import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MineSweeperMouseListener implements MouseListener{

    private MineSweeper mineSweeper;
    private MineSweeperHelper mineSweeperHelper;

    public MineSweeperMouseListener(MineSweeper mineSweeper,MineSweeperHelper helper){
        this.mineSweeper=mineSweeper;
        mineSweeperHelper=helper;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        JButton clickButton = (JButton)e.getSource();
        if(clickButton==mineSweeper.newGameButton){
            mineSweeperHelper.newGame(mineSweeper.rows,mineSweeper.columns);
            return;
        }

        for (int i = 0; i < mineSweeper.rows; i++) {
            for (int j = 0; j < mineSweeper.columns; j++) {
                if(clickButton == mineSweeper.mineButtons[i][j]){

                    if(mineSweeperHelper.isFirstClick()){
                        mineSweeper.gameTimer.start();
                        mineSweeperHelper.setFirstClick(false);
                        mineSweeper.mineField.populate(i,j);
                    }

                    MineField.gameState state;
                    if(e.getButton() == MouseEvent.BUTTON1){
                        state = mineSweeper.mineField.resolveClick(i,j,true);
                        if(state == MineField.gameState.CONTINUE){
                            if(mineSweeper.mineField.getMineFlag(i,j) == Mine.flagState.UNKNOWN){
                                clickButton.removeMouseListener(this);
                            }
                        }
                    }
                    else{
                        state = mineSweeper.mineField.resolveClick(i,j,false);
                    }
                    if(state == MineField.gameState.WIN){
                        mineSweeperHelper.endGame(true);
                    }
                    else if(state == MineField.gameState.LOSE){
                        mineSweeperHelper.endGame(false);
                    }
                    else{
                        mineSweeperHelper.updateButtons();
                    }

                }
            }
            mineSweeperHelper.updateLabels();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
