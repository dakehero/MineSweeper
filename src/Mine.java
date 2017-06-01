/**
 * 本类用于标记方块的状态
 * @author 陈钰元
 */
public class Mine {
    enum flagState{
        //每个方块上有三种状态
        UNKNOWN,            //空白
        MINE,               //十字架
        SUSPECT             //问号

    }
    private boolean isCleared =false;//是否被清除
    private boolean hasMine =false;//是否为地雷
    private flagState flag=flagState.UNKNOWN;  //初始状态为空白

    public boolean hasMine(){

        return hasMine;
    }
    public void setMine(){

        hasMine=true;
    }
    public boolean isCleared(){

        return isCleared;
    }
    public void clear(){

        isCleared=true;
    }
    public flagState getFlagState(){

        return flag;
    }
    public flagState setFlagState(){
           //每调用一次set Flag State（）则进行一次状态转换
        //空白->十字架 十字架->问号 问号->空白
        if(flag==flagState.UNKNOWN){
            flag=flagState.MINE;

            return flagState.MINE;
        }
        if(flag==flagState.MINE){
            flag=flagState.SUSPECT;

            return flagState.SUSPECT;
        }
        if(flag==flagState.SUSPECT){
            flag=flagState.UNKNOWN;

            return flagState.UNKNOWN;
        }

        return flagState.UNKNOWN;
    }


}
