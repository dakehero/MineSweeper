
public class Mine {
    enum flagState{
        UNKNOWN,
        MINE,
        SUSPECT
    }
    private boolean isCleared =false;
    private boolean hasMine =false;
    private flagState flag=flagState.UNKNOWN;

    public boolean hasMine(){
        return false;
    }
    public void setMine(){}
    public boolean isCleared(){
        return false;
    }
    public void clear(){}
    public flagState getFlagState(){
        return null;
    }
    public flagState setFlagState(){
        return null;
    }


}
