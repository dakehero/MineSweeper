import javax.swing.*;
import java.awt.*;

public class MineSweeperHelper {

    public boolean isFirstClick() {
        return isFirstClick;
    }

    public void setFirstClick(boolean firstClick) {
        isFirstClick = firstClick;
    }

    private boolean isFirstClick=true;


    private   MineSweeper mineSweeper;

    public MineSweeperHelper(MineSweeper mineSweeper){

        this.mineSweeper = mineSweeper;
    }

    public void updateLabels(){

        mineSweeper.minesFoundLable.setText(String.valueOf(
                mineSweeper.mineField.getMinesFound()));
        mineSweeper.minesRemainingLable.setText(String.valueOf(
                mineSweeper.mineField.getMinesRemaining()));

    }

    public void updateButtons(){
        for (int i = 0; i < mineSweeper.rows; i++) {
            for (int j = 0; j < mineSweeper.columns; j++) {
                if(mineSweeper.mineField.getMineCleared(i,j)==true){

                    mineSweeper.mineButtons[i][j].removeMouseListener(mineSweeper.mouseListener);
                    mineSweeper.mineButtons[i][j].setBackground(Color.WHITE);

                    int count = mineSweeper.mineField.countAdjacentMines(i,j);
                    if(count>0){
                        mineSweeper.mineButtons[i][j].setIcon(MineIcon.getNumberIcon(count));
                    }

                }
                else{
                    if(mineSweeper.mineField.getMineFlag(i,j) == Mine.flagState.MINE){
                        mineSweeper.mineButtons[i][j].setIcon(MineIcon.getFlagIcon());
                    }
                    else if(mineSweeper.mineField.getMineFlag(i,j)==Mine.flagState.SUSPECT){
                        mineSweeper.mineButtons[i][j].setIcon(MineIcon.getSuspectIcon());
                    }
                    else{
                        mineSweeper.mineButtons[i][j].setIcon(null);
                    }

                }
            }
        }
    }

    public  void showAll(){
        for (int i = 0; i < mineSweeper.rows; i++) {
            for (int j = 0; j < mineSweeper.columns; j++) {
                boolean mine =mineSweeper.mineField.isMine(i,j);
                if(mine){
                    mineSweeper.mineButtons[i][j].setIcon(MineIcon.getMineIcon());
                }
                else{
                    JButton thisButton = mineSweeper.mineButtons[i][j];
                    thisButton.removeMouseListener(mineSweeper.mouseListener);
                    thisButton.setBackground(Color.WHITE);
                    thisButton.setIcon(null);
                    int count = mineSweeper.mineField.countAdjacentMines(i,j);

                    if(count >0 ){
                        thisButton.setIcon(MineIcon.getNumberIcon(count));
                    }
                }
            }
        }
    }

    public void newGame(int previousRows, int previousColumns){
        mineSweeper.gameTimer.reset();
        mineSweeper.gameTimer.pause();
        for (int i = 0; i < previousRows; i++) {
            for (int j = 0; j < previousColumns; j++) {
                mineSweeper.minePanel.remove(mineSweeper.mineButtons[i][j]);
            }
        }
        isFirstClick=true;

        mineSweeper.init();
        mineSweeper.minePanel.validate();
        mineSweeper.frame.validate();
        //TODO:设置窗口大小
        mineSweeper.frame.pack();
        updateLabels();
    }

    public void endGame(boolean won){
        mineSweeper.gameTimer.pause();
        showAll();
        String wonOrLost;
        int option;
        if(won){
            wonOrLost="You won!";
        }
        else {
            wonOrLost="You lost.";
        }
        option = JOptionPane.showConfirmDialog(mineSweeper.frame,wonOrLost+"Play again?",
                wonOrLost,JOptionPane.YES_NO_OPTION);
        if(option==1){
            System.exit(0);
        }
        else{
            newGame(mineSweeper.rows,mineSweeper.columns);
        }
    }

}
