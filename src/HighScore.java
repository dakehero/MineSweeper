/**
 * HighScore类，用于对HighScore进行文件写入，读取，判断分数，返回分数等工作。
 * <p><code>HighScore</code>构造函数，用于初始化HighScoreItem数组，打开或建立文件并初始化文件内容。<br>
 *    <code>writeHighScore</code> 方法，用于将新的记录写入到文件中<br>
 *    <code>isHighScore</code> 方法，用于判断新的分数是否能够成为记录。<br>
 *    <code> returnList</code>方法，用于返回当前的高分榜。</p>
 * @author 伍淼
 */

import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HighScore {
    private HighScoreItem[] tmpScoreS = new HighScoreItem[5];
    private HighScoreItem[] tmpScoreM = new HighScoreItem[5];
    private HighScoreItem[] tmpScoreL = new HighScoreItem[5];
    private HighScoreItem[] tmpScore = new HighScoreItem[15];

    /**
     * @throws IOException
     */
    public void HihgScore() throws IOException{
        File file = new File("HighScoreList.txt");

        if (!file.exists()){
            file.createNewFile();       //如果文件不存在，建立新的文件
            FileWriter out = new FileWriter(file);
            //初始化新建的文件内容
            for (int i = 0; i <60 ; i++) {
                if(i<15){
                    out.write("*");
                }
                else if(i<30&i>=15){
                    out.write(0);
                }
                else if(i>=30&i<45){
                    out.write(0);
                }
                else if(i>=45&i<50){
                    out.write("LARGE");
                }
                else if(i>=50&i<55){
                    out.write("MEDIUM");
                }
                else if(i>=55&i<60){
                    out.write("SMALL");
                }
            }
        }
        //读取文件内容，并写入数组
        FileReader fileReader = new FileReader("HighScoreList.txt");
        BufferedReader buf = new BufferedReader(fileReader);

        for (int i = 0; i <15 ; i++) {
            tmpScore[i].usrName= buf.readLine();
        }
        for (int i = 0; i <15 ; i++) {
            tmpScore[i].seconds=Integer.parseInt(buf.readLine());
        }
        for (int i = 0; i <15 ; i++) {
            tmpScore[i].millis=Integer.parseInt(buf.readLine());
        }
        for (int i = 0; i <15 ; i++) {
            tmpScore[i].difficulty= HighScoreItem.Difficulty.valueOf(buf.readLine());
        }

        for (int i = 0; i <5 ; i++) {
            tmpScoreL[i]=tmpScore[i];
        }
        for (int i = 5; i <10 ; i++) {
            tmpScoreM[i-5]=tmpScore[i];
        }
        for (int i = 10; i <15 ; i++) {
            tmpScoreS[i-10]=tmpScore[i];
        }
    }

    /**
     * @param name
     * @param seconds
     * @param mills
     * @param difficulty
     * @throws IOException
     */
    public void writeHighScore(String name,int seconds,int mills,HighScoreItem.Difficulty difficulty) throws IOException{
        int tmp = 0;//用于判断记录是否写满
        HighScoreItem tmpScoreItem=new HighScoreItem();
        if (difficulty == HighScoreItem.Difficulty.SMALL) {
            //高分榜没写满，直接空白部分写入
            for (int i = 0; i < 5; i++) {
                if (tmpScoreS[i].seconds == 0) {
                    tmpScoreS[i].usrName = name;
                    tmpScoreS[i].seconds = seconds;
                    tmpScoreS[i].millis = mills;
                    tmp = 1;
                }
            }
            //高分榜写满，找到大于当前成绩的数据,覆盖它
            if (tmp == 0) {
                for (int i = 4; i>=0 ; i--) {
                    if (seconds < tmpScoreS[i].seconds) {
                        tmpScoreS[i].usrName = name;
                        tmpScoreS[i].seconds = seconds;
                        tmpScoreS[i].millis = mills;
                    }
                    else if (seconds == tmpScoreS[i].seconds) {
                        if (mills < tmpScoreS[i].millis) {
                            tmpScoreS[i].usrName = name;
                            tmpScoreS[i].seconds = seconds;
                            tmpScoreS[i].millis = mills;
                        }
                    }
                }
            }

            //将数据排序
            for (int i = 0; i <5 ; i++) {
                if(tmpScoreL[i+1].seconds<tmpScoreL[i].seconds){
                    tmpScoreItem=tmpScoreL[i];
                    tmpScoreL[i]=tmpScoreL[i+1];
                    tmpScoreL[i+1]=tmpScoreItem;
                }
                else if (tmpScoreL[i+1].seconds==tmpScoreL[i].seconds){
                    if(tmpScoreL[i+1].millis<tmpScoreL[i].millis){
                        tmpScoreItem=tmpScoreL[i];
                        tmpScoreL[i]=tmpScoreL[i+1];
                        tmpScoreL[i+1]=tmpScoreItem;

                    }
                }
            }
        }

        //难度为M的操作
        if (difficulty == HighScoreItem.Difficulty.MEDIUM) {
            for (int i = 0; i < 5; i++) {
                if (tmpScoreM[i].seconds == 0) {
                    tmpScoreM[i].usrName = name;
                    tmpScoreM[i].seconds = seconds;
                    tmpScoreM[i].millis = mills;
                    tmp = 1;
                }
            }
            if (tmp == 0) {
                for (int i = 4; i >= 0; i--) {
                    if (seconds < tmpScoreM[i].seconds) {
                        tmpScoreM[i].usrName = name;
                        tmpScoreM[i].seconds = seconds;
                        tmpScoreM[i].millis = mills;
                    } else if (seconds == tmpScoreM[i].seconds) {
                        if (mills < tmpScoreM[i].millis) {
                            tmpScoreM[i].usrName = name;
                            tmpScoreM[i].seconds = seconds;
                            tmpScoreM[i].millis = mills;
                        }
                    }
                }
            }
            for (int i = 0; i <5 ; i++) {
                if(tmpScoreM[i+1].seconds<tmpScoreM[i].seconds){
                    tmpScoreItem=tmpScoreM[i];
                    tmpScoreM[i]=tmpScoreM[i+1];
                    tmpScoreM[i+1]=tmpScoreItem;
                }
                else if (tmpScoreM[i+1].seconds==tmpScoreM[i].seconds){
                    if(tmpScoreM[i+1].millis<tmpScoreM[i].millis){
                        tmpScoreItem=tmpScoreM[i];
                        tmpScoreM[i]=tmpScoreM[i+1];
                        tmpScoreM[i+1]=tmpScoreItem;

                    }
                }
            }
        }
        //难度为L的操作
        if (difficulty == HighScoreItem.Difficulty.LARGE) {
            for (int i = 0; i < 5; i++) {
                if (tmpScoreL[i].seconds == 0) {
                    tmpScoreL[i].usrName = name;
                    tmpScoreL[i].seconds = seconds;
                    tmpScoreL[i].millis = mills;
                    tmp = 1;
                }
            }
            if (tmp == 0) {
                for (int i = 4; i >=0; i--) {
                    if (seconds < tmpScoreL[i].seconds) {
                        tmpScoreL[i].usrName = name;
                        tmpScoreL[i].seconds = seconds;
                        tmpScoreL[i].millis = mills;
                    }
                    else if (seconds == tmpScoreL[i].seconds) {
                        if (mills < tmpScoreL[i].millis) {
                            tmpScoreL[i].usrName = name;
                            tmpScoreL[i].seconds = seconds;
                            tmpScoreL[i].millis = mills;
                        }
                    }
                }
            }
            for (int i = 0; i <5 ; i++) {
                if(tmpScoreL[i+1].seconds<tmpScoreL[i].seconds){
                    tmpScoreItem=tmpScoreL[i];
                    tmpScoreL[i]=tmpScoreL[i+1];
                    tmpScoreL[i+1]=tmpScoreItem;
                }
                else if (tmpScoreL[i+1].seconds==tmpScoreL[i].seconds){
                    if(tmpScoreL[i+1].millis<tmpScoreL[i].millis){
                        tmpScoreItem=tmpScoreL[i];
                        tmpScoreL[i]=tmpScoreL[i+1];
                        tmpScoreL[i+1]=tmpScoreItem;

                    }
                }
            }

        }

        //重新将数值写入到tmpScore数组中，方便重写回文件中
        for (int i = 0; i <5 ; i++) {
            tmpScore[i]=tmpScoreL[i];
        }
        for (int i = 5; i <10 ; i++) {
            tmpScore[i]=tmpScoreM[i-5];
        }
        for (int i = 10; i <15 ; i++) {
            tmpScore[i]=tmpScoreS[i];
        }
        //打开文件，覆盖写入更新后的数组
        File file = new File("HighScoreList.txt");
        FileWriter out = new FileWriter(file,false);
        for (int i = 0; i <60 ; i++) {
            if(i<15){
                for (int j = 0; j <15 ; j++) {
                    out.write(tmpScore[i].usrName);
                }
            }
            else if(i>=15&i<30){
                for (int j = 0; j <15 ; j++) {
                    out.write(tmpScore[i].seconds);
                }
            }
            else if(i>=30&i<45){
                for (int j = 0; j <15 ; j++) {
                    out.write(tmpScore[i].millis);
                }
            }
            else if(i>=45&i<60){
                for (int j = 0; j <5 ; j++) {
                    out.write("LARGE");
                }
                for (int j = 0; j <5 ; j++) {
                    out.write("MEDIUM");
                }
                for (int j = 0; j <5 ; j++) {
                    out.write("SMALL");
                }
            }
        }
    }

    /**
     * @param seconds
     * @param mills
     * @param difficulty
     * @return 如果分数可以放入高分榜，则返回true，否之则返回false
     */
    public boolean isHighScore(int seconds,int mills,HighScoreItem.Difficulty difficulty){
       //难度为S时的是否能放入
        if(difficulty == HighScoreItem.Difficulty.SMALL){

            for (int i = 4; i >=0 ; i--) {
                    if(tmpScoreS[i].seconds==0){//高分榜未满，返回true
                        return true;
                    }

                if(seconds < tmpScoreS[i].seconds ){//高分榜满了，存在大于当前成绩的记录时，返回true
                    return true;
                }
                else if (seconds == tmpScoreS[i].seconds){
                    if(mills < tmpScoreS[i].millis){
                        return true;
                    }
                }
            }
            return false;
        }
        //难度为M时是否能放入
        else if(difficulty == HighScoreItem.Difficulty.MEDIUM){
            for (int i = 4; i >=0 ; i--) {
                if(tmpScoreM[i].seconds==0){
                    return true;
                }
                if(seconds < tmpScoreM[i].seconds ){
                    return true;
                }
                else if (seconds == tmpScoreM[i].seconds){
                    if(mills < tmpScoreM[i].millis){
                        return true;
                    }
                }
            }
            return false;
        }
        //难度为L时是否能放入
        else {
            for (int i = 4; i >= 0; i--) {
                if(tmpScoreL[i].seconds==0){
                    return true;
                }
                if (seconds < tmpScoreL[i].seconds) {
                    return true;
                } else if (seconds == tmpScoreL[i].seconds) {
                    if (mills < tmpScoreL[i].millis) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * @return 返回当前的分数列表
     */
    public  HighScoreItem[] returnList(){
         HighScoreItem[] highScoreList = new HighScoreItem[15];
        for (int i = 0; i <15 ; i++) {
            if(i<5){
                highScoreList[i] = tmpScoreL[i];
            }
            else if (i>5&i<10){
                highScoreList[i]=tmpScoreM[i-5];
            }
            else if (i>10&i<15){
                highScoreList[i]=tmpScoreS[i-10];
            }
        }
        return highScoreList;
    }



}
