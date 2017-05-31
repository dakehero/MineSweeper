
import java.awt.*;
import java.awt.event.*;
import  java.time.*;

import javax.swing.*;

public class GameTimer extends JFrame implements Runnable{
    JFrame frm=new JFrame("watch");
    JPanel bottom=new JPanel();
    JPanel mid=new JPanel();
    JLabel h=new JLabel("0");
    JLabel biao=new JLabel(":");
    JLabel s=new JLabel("00");
    Thread t=new Thread(this);
    private int i=0,j=0;
    public void run(){
        try
        {
            while(true){
                Thread.sleep(1000);
                i++;
                if(i>=1&&i<10)
                {
                    s.setText("0"+i);
                }
                else if(i>=10&&i<60)
                    s.setText(""+i);
                if(i==60)
                {
                    i=0;
                    j++;
                    s.setText("00");
                    h.setText(""+j);
                }
            }
        }catch(Exception e){
            System.out.println("Timer error!");
        }
    }
    public GameTimer()
    {
        frm.setLayout(new BorderLayout());
        frm.setBounds(200, 200, 300, 150);
        frm.setVisible(true);
        frm.add("South",bottom);
        frm.add("Center",mid);

        mid.add(h);
        mid.add(biao);
        mid.add(s);
        h.setFont(new Font("Consulas",Font.PLAIN,36));
        biao.setFont(new Font("Consulas",Font.PLAIN,36));
        s.setFont(new Font("Consulas",Font.PLAIN,36));

    }

    public void start(){
        if(t.isAlive())
            t.resume();
        else t.start();
    }

    public void pause(){
        t.suspend();
    }

    public void reset(){
            h.setText("0");
            s.setText("00");
            i=0;
            j=0;

    }
}
