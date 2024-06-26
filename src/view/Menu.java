package view;

/**
 * Shelly：要去建立一个窗口（游戏的初始界面）
 */

import Musics.MenuThread;
import Musics.MyThread;
import Musics.Test;
import Musics.myPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Menu extends JFrame implements ActionListener {
    public MenuThread t01 = new MenuThread("桌面背景音乐");
    public MenuThread newMusic = new MenuThread("重新开始");
    int click = 0;
    public JButton music;
    public JButton noMusic;
    public static ChessGameFrame chessGameFrame;
    private final int WIDTH;
    private final int HEIGHT;
    private JButton Game_Start;
    private JButton Game_Over;
    private JButton Help;

    public JTextField redCoinsKuang;
    public JLabel redRank;
    public JTextField blackCoinsKuang;
    public JLabel blackRank;

    public Menu(int width, int height) {
        ChessGameFrame chessGameFrame1 = new ChessGameFrame(720,720);
        chessGameFrame = chessGameFrame1;
        chessGameFrame1.setVisible(false);
        setTitle("Dark Chess Menu"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        /**
         * add label
         */
        addRankList();
        addMusicButton();
        /**
         * add background
         */
        addBackground();
        addGame_Start();
        addGame_Over();
        addHelp();
        t01.start();
    }

    public void addBackground() {
        //1.把图片添加到标签里（把标签的大小设为和图片大小相同），把标签放在分层面板的最底层；
        ImageIcon bg = new ImageIcon("imgs/更改大小后的封面.png");
        JLabel label = new JLabel(bg);
        label.setSize(bg.getIconWidth(), bg.getIconHeight());
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());
        add(label);
        this.setVisible(true);
    }

    @Override

    public void actionPerformed(ActionEvent e) {//跳转界面
        if (e.getSource() == Game_Start) {
            String path = "Music/大按钮的副本.wav";
            Test.AudioPlay2 clickMusic = new Test.AudioPlay2(path);
            clickMusic.run = true;
            clickMusic.start();
            ImageIcon bg = new ImageIcon("imgs/花.png");
            int value = JOptionPane.showOptionDialog(null, "请选择你想要进行的模式", "选择", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, bg, new String[]{"开始新游戏", "复盘模式", "取消"}, "0");
            if (value == JOptionPane.YES_OPTION) {
                t01.over();
                chessGameFrame.setVisible(true);
                chessGameFrame.t01.start();
                this.dispose();
            }

            if (value == JOptionPane.NO_OPTION) {
                String path1 = JOptionPane.showInputDialog(this, "Input path here : ");
                if(!path1.equals(null)) {
                    t01.over();
                    this.dispose();
                    chessGameFrame.setVisible(true);
                    chessGameFrame.t01.start();
                    chessGameFrame.Review.setVisible(true);
                    chessGameFrame.regret.setVisible(false);
                    chessGameFrame.close.setVisible(false);
                    chessGameFrame.Cheat.setVisible(false);
                    chessGameFrame.Load.setVisible(false);
                    chessGameFrame.Save.setVisible(false);
                    chessGameFrame.gameController.loadGameFromFile(path1);
                }
            }
        }

        if (e.getSource() == Game_Over) {
            String path = "Music/大按钮的副本.wav";
            Test.AudioPlay2 clickMusic = new Test.AudioPlay2(path);
            clickMusic.run = true;
            clickMusic.start();
            this.dispose();
            System.exit(0);
        }

    }

    public void addGame_Start() {
        Game_Start = new JButton("开始");
        Game_Start.setLocation(290, 422);
        Game_Start.setSize(180, 60);
        add(Game_Start);
        Game_Start.addActionListener(this::actionPerformed);
        Game_Start.setVisible(true);
    }

    public void addGame_Over() {
        Game_Over = new JButton("结束");
        Game_Over.setLocation(290, 490);
        Game_Over.setSize(180, 60);
        add(Game_Over);
        Game_Over.addActionListener(this::actionPerformed);
        setVisible(true);
    }

    public void addHelp() {
        Help = new JButton();
        Help.setLocation(290, 560);
        Help.setSize(180, 60);
        add(Help);
        Help.addActionListener(e -> {
            JOptionPane.showConfirmDialog(null, "帮助如下", "帮助", JOptionPane.YES_NO_OPTION);
        });
    }

    public void addRankList() {//每一次Text的文本不知道在哪里改
        ImageIcon RankList = new ImageIcon("imgs/Menu排行榜.png");
        JLabel label = new JLabel(RankList);
        label.setSize(RankList.getIconWidth(), RankList.getIconHeight());
        label.setLocation(0, 0);
        label.setBounds(10, 0, RankList.getIconWidth(), RankList.getIconHeight());
        redRank = new JLabel("红方");
        redRank.setFont(new Font("Rockwell", Font.BOLD, 20));
        redRank.setForeground(Color.RED);
        redRank.setSize(100, 100);
        redRank.setLocation(60, 80);
        add(redRank);
        String s1 = String.valueOf(chessGameFrame.RedCoins);
        redCoinsKuang = new JTextField(s1, 3);
        redCoinsKuang.setEditable(false);
        redCoinsKuang.setSize(36, 20);
        redCoinsKuang.setLocation(170, 115);
        add(redCoinsKuang);
        blackRank = new JLabel("黑方");
        blackRank.setFont(new Font("Rockwell", Font.BOLD, 20));
        blackRank.setSize(100, 100);
        blackRank.setLocation(60, 110);
        add(blackRank);
        String s2 = String.valueOf(chessGameFrame.BlackCoins);
        blackCoinsKuang = new JTextField(s2, 3);
        blackCoinsKuang.setEditable(false);
        blackCoinsKuang.setSize(36, 20);
        blackCoinsKuang.setLocation(170, 150);
        add(blackCoinsKuang);
        add(label);
        this.setVisible(true);
    }

    public void addMusicButton() {
        noMusic = new JButton(new ImageIcon("imgs/pause.png"));
        noMusic.setSize(30, 30);
        noMusic.setLocation(WIDTH * 9 / 10 - 10, 10);
        add(noMusic);
        noMusic.addActionListener(this::actionMusic);
        music = new JButton(new ImageIcon("imgs/play.png"));
        music.setSize(30, 30);
        music.setLocation(WIDTH * 9 / 10 + 30, 10);
        add(music);
        setVisible(true);
        music.addActionListener(this::actionMusic);
    }

    public void actionMusic(ActionEvent e) {//跳转界面
        if (e.getSource() == noMusic) {
            if (click == 0) {
                t01.over();
            }
            if (click == 1) {
                newMusic.over();
                click = 0;
            }
        }

        if (e.getSource() == music) {
            newMusic.start();
            click = 1;
        }
    }
}
