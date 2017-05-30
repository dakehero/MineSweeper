
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
