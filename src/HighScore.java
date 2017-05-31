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
    private void HihgScore() throws IOException{
        File file = new File("HighScoreList.txt");
        if (!file.exists()){
            file.createNewFile();
            FileWriter out = new FileWriter(file);
            for (int i = 0; i <60 ; i++) {
                if(i<15){
                    out.write("*");
                }
                else if(i<30&i>15){
                    out.write(0);
                }
                else if(i>30&i<45){
                    out.write(0);
                }
                else if(i>45&i<50){
                    out.write("LARGE");
                }
                else if(i>50&i<55){
                    out.write("MEDIUM");
                }
                else if(i>55&i<60){
                    out.write("SMALL");
                }
            }

        }
        FileReader fileReader = new FileReader("HighScoreList.txt");
        BufferedReader buf = new BufferedReader(fileReader);
        HighScoreItem[] tmpScore = new HighScoreItem[15];

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



    public boolean isHighScore(int seconds,int mills,HighScoreItem.Difficulty difficulty){
        if(difficulty == HighScoreItem.Difficulty.SMALL){
            for (int i = 4; i >=0 ; i--) {
                if(tmpScoreS[i].seconds==0){
                    return true;
                }
                if(seconds < tmpScoreS[i].seconds ){
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
