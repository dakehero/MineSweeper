
import java.awt.*;

import javax.swing.*;

public class GameTimer extends JFrame implements Runnable{
    JFrame frm=new JFrame("Timer");
    JPanel mid=new JPanel();
    JLabel h=new JLabel("0");
    JLabel biao=new JLabel(":");
    JLabel s=new JLabel("000");
    Thread t=new Thread(this);

    private long startTime;
    private long currentTime;

    public void run(){
        try
        {/*
            while(true){
                Thread.sleep(10);
                i++;
                if(i>=1&&i<10)
                {
                    s.setText("0"+i);
                }
                else if(i>=10&&i<100)
                    s.setText(""+i);
                else if(i==100)
                {
                    i=0;
                    j++;
                    s.setText("00");
                    h.setText(""+j);
                }
            }
            */

            while(true){
                Thread.sleep(1);
                currentTime=System.currentTimeMillis();
                s.setText(String.format("%03d",(currentTime-startTime)%1000));
                h.setText(String.valueOf((currentTime-startTime)/1000));
            }
        }

        catch(Exception e){
            System.out.println("Timer error!");
        }
    }
    public GameTimer()
    {
        frm.setBounds(200, 200, 300, 100);
        frm.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frm.setVisible(true);
        frm.add(mid);

        mid.add(h);
        mid.add(biao);
        mid.add(s);
        h.setFont(new Font("Consulas",Font.PLAIN,36));
        biao.setFont(new Font("Consulas",Font.PLAIN,36));
        s.setFont(new Font("Consulas",Font.PLAIN,36));

    }

    public void start(){
        startTime=System.currentTimeMillis();
        if(t.isAlive())
            t.resume();
        else t.start();
    }

    public void pause(){
        t.suspend();
    }

    public void reset(){

        h.setText("0");
        s.setText("000");
        t.suspend();
    }
}

