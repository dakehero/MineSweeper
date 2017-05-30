//TODO：write a javadoc

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MineSweeper {

    int columns=8;
    int rows =8;
    int mines=10;

    //窗体部分
    JFrame frame =new JFrame("MineSweeper");
    JPanel minePanel =new JPanel();
    JPanel controlPanel;
    JLabel minesRemainingLable = new JLabel("Remaining:");
    JLabel minesFoundLable=new JLabel("Found: 0");

    //雷区按钮
    JButton[][] mineButtons;
    Dimension buttonSize= new Dimension(20,20);

    //游戏类实例需要MineSweeper对象才能实例化
    MineField mineField;
    MineSweeperMouseListener mouseListener;//游戏监听器
    MineSweeperActionListener actionListener;//菜单监听器
    MineSweeperHelper helper;//辅助对象

    public MineSweeper(){
        helper=new MineSweeperHelper(this);
        actionListener=new MineSweeperActionListener(this,helper);
        mouseListener=new MineSweeperMouseListener(this,helper);
        init();
    }

    public void init(){
        //开启游戏时调用
        initMines();
        initMinesPanel();
        mineField=new MineField(rows,columns,mines);
        initControlPanel();
    }



    private void initMines(){
        mineButtons =new JButton[rows][columns];
        /*
        for(JButton[] mineButtonsRow:mineButtons){
            for(JButton mineButton:mineButtonsRow){
                mineButton=new JButton();
                mineButton.setSize(buttonSize);
                mineButton.setMaximumSize(buttonSize);
                mineButton.setMinimumSize(buttonSize);
                mineButton.addMouseListener(mouseListener);
                mineButton.setEnabled(true);
                mineButton.setText("");
                mineButton.setIcon(null);
            }
        }
        */

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                JButton currentButton = new JButton();
                currentButton.setPreferredSize(buttonSize);
                currentButton.setMaximumSize(buttonSize);
                currentButton.setMinimumSize(buttonSize);
                currentButton.addMouseListener(mouseListener);
                currentButton.setEnabled(true);
                currentButton.setText("");
                currentButton.setIcon(null);
                mineButtons[i][j]=currentButton;
            }
        }
    }

    private void initMinesPanel(){
        minePanel.setLayout(new GridLayout(rows,columns));//设置为网格面板
         /*
        for(JButton[] mineButtonsRow:mineButtons){
            for(JButton mineButton:mineButtonsRow){
                minePanel.add(mineButton);
            }
        }
        */

         for(int i=0;i<rows;i++){
             for(int j=0;j<columns;j++){
                 minePanel.add(mineButtons[i][j]);
             }
         }
    }

    private void initControlPanel(){
        Border pandingBorder =BorderFactory.createEmptyBorder(5,5,5,5);//留下空白
        controlPanel=new JPanel();
        minesFoundLable.setBorder(pandingBorder);
        minesFoundLable.setAlignmentX(Component.LEFT_ALIGNMENT);
        minesRemainingLable.setBorder(pandingBorder);
        minesRemainingLable.setAlignmentX(Component.LEFT_ALIGNMENT);
        minesRemainingLable.setText("Reamining: "+mineField.getMinesRemaining());
        controlPanel.add(minesFoundLable);
        controlPanel.add(minesRemainingLable);
        GridLayout gridLayout=new GridLayout(1,2);
        controlPanel.setLayout(gridLayout);
        Dimension controlPanelSize = new Dimension(0,40);
        controlPanel.setPreferredSize(controlPanelSize);
        controlPanel.setMaximumSize(new Dimension(1000,20));
    }



    private void addAndArrangePanels(){
        frame.getContentPane().add(controlPanel);
        frame.getContentPane().add(minePanel);


    }

    private void addMenu(){
        JMenu file=new JMenu("file");
        file.setMnemonic('F');

        JMenuItem newItem=new JMenuItem("NewGame");
        newItem.setMnemonic('n');
        newItem.addActionListener(actionListener);
        file.add(newItem);

        ButtonGroup sizeOptions =new ButtonGroup();

        JRadioButtonMenuItem smallOption =new JRadioButtonMenuItem("Small (8*8, 10 Mines)");
        smallOption.setMnemonic('s');
        smallOption.addActionListener(actionListener);
        sizeOptions.add(smallOption);
        file.add(smallOption);
        smallOption.setSelected(true);

        JRadioButtonMenuItem mediumOption = new JRadioButtonMenuItem("Medium (16*16,40 Mines)" );
        mediumOption.setMnemonic('m');
        mediumOption.addActionListener(actionListener);
        sizeOptions.add(mediumOption);
        file.add(mediumOption);

        JRadioButtonMenuItem largeOption = new JRadioButtonMenuItem("Large (16*32, 100 Mines)");
        largeOption.setMnemonic('l');
        largeOption.addActionListener(actionListener);
        sizeOptions.add(largeOption);
        file.add(largeOption);


        JMenuItem exitItem =new JMenuItem("Exit");
        exitItem.setMnemonic('x');
        exitItem.addActionListener(actionListener);
        file.add(exitItem);

        JMenuBar menuBar=new JMenuBar();
        menuBar.add(file);

        frame.setJMenuBar(menuBar);
    }

    private void createAndShowGUI(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));

        addAndArrangePanels();
        addMenu();

        //TODO:重设窗体大小
      //  frame.setPreferredSize(new Dimension(500,700));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){
        MineSweeper mineSweeper =new MineSweeper();
        mineSweeper.createAndShowGUI();
    }


}
