/**
 * @author 全伟
 * show 用于处理游戏后端逻辑并将结果显示到前端界面
 */
import javax.swing.*;
import java.awt.*;


public class MineSweeperHelper {

    private boolean isFirstClick;//是否为第一次点击

    private MineSweeper mineSweeper;

    public MineSweeperHelper(MineSweeper mineSweeper){
        isFirstClick=true;
        this.mineSweeper = mineSweeper;
    }

    public boolean isFirstClick() {
        return isFirstClick;
    }

    public void setFirstClick(boolean firstClick) {
        isFirstClick = firstClick;
    }

    public void updateLabels(){
        //更新剩余地雷数和已找到地雷数标签
        mineSweeper.minesFoundLable.setText(String.valueOf(
                mineSweeper.mineField.getMinesFound()));
        mineSweeper.minesRemainingLable.setText(String.valueOf(
                mineSweeper.mineField.getMinesRemaining()));

    }

    public void updateButtons(){
        //更新雷区按钮图标
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
        //当游戏结束时显示全部图标
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

    /**
     * @param previousRows 上一局游戏的行数（用于清楚面板上的按钮）
     * @param previousColumns 上一局游戏的列数（用于清楚面板上的按钮）
     */
    public void newGame(int previousRows, int previousColumns){
        //开启新游戏
        mineSweeper.gameTimer.reset();//重置游戏计时器
        //移除全部按钮
        for (int i = 0; i < previousRows; i++) {
            for (int j = 0; j < previousColumns; j++) {
                mineSweeper.minePanel.remove(mineSweeper.mineButtons[i][j]);
            }
        }

        isFirstClick=true;//重置首次点击标识

        mineSweeper.init();
        //更新面板和窗体
        mineSweeper.minePanel.validate();
        mineSweeper.frame.validate();
        //TODO:设置窗口大小
        mineSweeper.frame.pack();
        updateLabels();//更新标签
    }

    public void endGame(boolean won){
        mineSweeper.gameTimer.pause();//停止计时器
        showAll();//显示全部图标
        String wonOrLost;//失败和胜利语
        int option;
        //TODO：添加计分器
        if(won){
            wonOrLost="You won!";
        }
        else {
            wonOrLost="You lost.";
        }
        option = JOptionPane.showConfirmDialog(mineSweeper.frame,wonOrLost+"Play again?",
                wonOrLost,JOptionPane.YES_NO_OPTION);//显示提示语，询问是否开启新游戏
        if(option==1){
            //选择否是退出
            System.exit(0);
        }
        else{
            //选择是时开启新游戏
            newGame(mineSweeper.rows,mineSweeper.columns);
        }

    }

}
