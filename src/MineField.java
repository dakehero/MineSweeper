/**
 * 王佳栋
 * 此类主要负责埋雷
 */
public class MineField {
    private Mine[][] mineField;
    private int rows;//行
    private int columns;//列
    private int mines;//地雷数目
    private int minesFound =0;//发现地雷的数目
    private int minesRemaining;//剩余地雷数目
    private int emptiesRemaining;//剩余可点击格数
    //游戏中返回的三种状态
    enum gameState{
        WIN,
        LOSE,
        CONTINUE
    }
    //构造函数，提供接口
    public MineField(int rows, int columns, int mines){
        //传参
        this.rows=rows;
        this.columns=columns;
        this.mines=mines;
        minesRemaining=mines;//初始化剩余地雷数目
        emptiesRemaining=rows*columns-mines;//初始化非雷格剩余数目
        mineField =new Mine[rows][columns];//申请内存空间存放雷池
        init();//初始化函数
        //TODO: to populate after first-click
        //populate();//布雷函数
    }
    //为mineField数组中的每一个元素所占的位置“埋”一个默认的“雷”
    private void init(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mineField[i][j]=new Mine();
            }
        }
    }
    //递归     埋会“爆炸”的雷   mineCount默认为0
    public void populate(int x,int y){

        populate(0,x,y);
    }
    
    private void populate(int mineCount,int x,int y){
        int currentCount =mineCount;//局部变量currentCount负责在该函数内部记录已经埋下的雷的数目
        double mineChance=(double) mines/(double)(rows*columns);//概率
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Mine thisMine= mineField[i][j];
                //TODO: skip first-click button
                if((!thisMine.hasMine())&&(i!=x&&j!=y)){//如果没有雷
                    if(Math.random()<mineChance){//如果随机出来的数小于概率，就埋雷，已埋雷数+1
                        thisMine.setMine();
                        currentCount++;
                        if(currentCount==mines){//如果数目达到要求，函数结束
                            return;
                        }
                    }
                }
            }
        }
        /*
        for (Mine[] minesRow: mineField) {
            for (Mine thisMine:minesRow){
                if(!thisMine.hasMine()){
                    if(Math.random()<mineChance){
                        thisMine.setMine();
                        currentCount++;
                        if(currentCount==mines){
                            return;
                        }
                    }
                }
            }
        }
        */
        if(currentCount<mines){//如果数目还没达到要求，递归
            populate(currentCount,x,y);
        }
    }
    //接口
    public int getMinesRemaining(){
        return minesRemaining;
    }
    //接口
    public int getMinesFound(){
        return minesFound;
    }
    //根据Mine的内容对鼠标点击后应该产生什么结果做出反应，需要有优先级
    public gameState resolveClick(int x, int y ,boolean left){
        //TODO: deal with these shit!!!
        for(int i = 0 ; i < rows ; i++){
            for (int j = 0; j < columns; j++){
                if(i == x && j == y){
                    Mine thisMine = mineField[i][j];
                    if(left){//如果是左键点击
                        if(thisMine.getFlagState()==Mine.flagState.MINE){//优先判断flag
                            return gameState.CONTINUE;
                        }
                        if(thisMine.isCleared()){
                            return  gameState.CONTINUE;
                        }
                        if(thisMine.hasMine()){
                            return gameState.LOSE;
                        }
                        else{
                            return cascade(i,j);//是0的话，瀑布
                        }
                    }
                    else{//如果是非左键，统一当做右键处理
                        Mine.flagState state = thisMine.setFlagState();
                        if(state == Mine.flagState.MINE){
                            minesFound++;
                            minesRemaining--;
                        }
                        else if(state==Mine.flagState.SUSPECT){
                            minesFound--;
                            minesRemaining++;
                        }
                    }
                }
            }
        }
        return gameState.CONTINUE;//其他情况对游戏无影响
    }
    //瀑布，点到0，对周围进行扩展操作
    private gameState cascade(int x, int y){
        if( x<0 || y<0 || x>rows || y>columns ){//若点到无关区,对游戏无影响
            return gameState.CONTINUE;
        }

        Mine thisMine=mineField[x][y];

        if(!thisMine.isCleared()){
            thisMine.clear();
            emptiesRemaining--;
            if(emptiesRemaining==0) {
                return gameState.WIN;
            }
        }
//???
        if(countAdjacentMines(x,y)>0){
            return gameState.CONTINUE;
        }
        else {
            for (int i = x-1 ; i <= x+1 ; i++) {
                for (int j = y-1; j <= y+1 ; j++) {
                    if(i<0||j<0||i>=rows||j>=columns){
                        continue;
                    }
                    else if(!mineField[i][j].isCleared()){
                        cascade(i,j);
                    }
                }
            }
        }
        return  gameState.CONTINUE;
    }
    //清点附近雷的数目
    public int countAdjacentMines(int x,int y){
        int count = 0;
        for(int i = x-1 ; i <= x+1 ; i++){//周围8格
            for (int j = y-1; j <= y+1 ; j++) {
                if(i == x && j == y){
                    continue;
                }
                else if(i<0||j<0||i>=rows||j>=columns){
                    continue;
                }
                else if(mineField[i][j].hasMine()){
                    count++;
                }
            }
        }
        return count;
    }

    public Mine.flagState getMineFlag(int x,int y){
        return  mineField[x][y].getFlagState();
    }

    public boolean getMineCleared(int x, int y){
        return mineField[x][y].isCleared();
    }

    public boolean isMine(int x,int y){
        return mineField[x][y].hasMine();
    }

}
