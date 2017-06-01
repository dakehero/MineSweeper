import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


/**
 * 本类用于监听全部面板上的按钮点击事件
 * <p>实现对按钮操作的排雷，新游戏，设置状态</p>
 * @author 陈钰元
 */
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
        //😊按钮用于开启新游戏
        if(clickButton==mineSweeper.newGameButton){
            mineSweeperHelper.newGame(mineSweeper.rows,mineSweeper.columns);
            return;
        }
        //面板上的地雷按钮
        for (int i = 0; i < mineSweeper.rows; i++) {
            for (int j = 0; j < mineSweeper.columns; j++) {
                //循环便利确定定位按钮坐标
                if(clickButton == mineSweeper.mineButtons[i][j]){
                    //第一次点击
                    if(mineSweeperHelper.isFirstClick()){
                        mineSweeper.gameTimer.start();//启动计时器
                        mineSweeperHelper.setFirstClick(false);//以后就不是第一次点击了
                        mineSweeper.mineField.populate(i,j);//布置地雷（第一次点击保证不会出现地雷）
                    }

                    MineField.gameState state;//游戏状态
                    //左键点击时
                    if(e.getButton() == MouseEvent.BUTTON1){
                        state = mineSweeper.mineField.resolveClick(i,j,true);//后端处理点击事件
                        if(state == MineField.gameState.CONTINUE){
                            //游戏继续，点击到了非雷按钮
                            if(mineSweeper.mineField.getMineFlag(i,j) == Mine.flagState.UNKNOWN){
                                clickButton.removeMouseListener(this);
                            }
                        }
                    }
                    else{
                        //非左键点击
                        state = mineSweeper.mineField.resolveClick(i,j,false);
                    }
                    //胜利
                    if(state == MineField.gameState.WIN){
                        mineSweeperHelper.endGame(true);
                    }
                    //失败
                    else if(state == MineField.gameState.LOSE){
                        mineSweeperHelper.endGame(false);
                    }
                    //游戏继续时，更新全部按钮上的图标
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
