
import java.awt.*;

import javax.swing.*;

/**
 * @author 全伟
 * 游戏计时器面板和计时功能
 */
public class GameTimer extends JFrame implements Runnable{
    JFrame frame = new JFrame("Timer");
    JPanel panel = new JPanel();

    JLabel secondsLabel = new JLabel("0");
    JLabel colon = new JLabel(".");
    JLabel millis = new JLabel("000");

    Thread timerThread = new Thread(this);

    private long startTime;//计时器启动时间
    private long currentTime;//当前时间

    public void run(){//计时器线程
        try
        {
            while(true){
                Thread.sleep(1);

                currentTime=System.currentTimeMillis();

                millis.setText(String.format("%03d",(currentTime-startTime)%1000));
                secondsLabel.setText(String.valueOf((currentTime-startTime)/1000));
            }
        }

        catch(Exception e){
            System.out.println("Timer error!");
        }
    }
    public GameTimer()
    {
        //用于初始化计时器窗口
        frame.setBounds(200, 200, 300, 100);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.add(secondsLabel);
        panel.add(colon);
        panel.add(millis);

        secondsLabel.setFont(new Font("Consulas",Font.PLAIN,36));
        colon.setFont(new Font("Consulas",Font.PLAIN,36));
        millis.setFont(new Font("Consulas",Font.PLAIN,36));

    }

    public void start(){
        //启动计时器线程
        startTime=System.currentTimeMillis();
        if(timerThread.isAlive())
            timerThread.resume();
        else timerThread.start();
    }

    public void pause(){
        //暂停计时器
        timerThread.suspend();
    }

    public void reset(){
        //重置计时器标签
        secondsLabel.setText("0");
        millis.setText("000");
        timerThread.suspend();
    }
}

