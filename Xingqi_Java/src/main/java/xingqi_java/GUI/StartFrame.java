/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xingqi_java.GUI;
import xingqi_java.run.Core;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
/**
 *
 * startup frame
 */
public class StartFrame extends JFrame {

    private JPanel TITLE, top, middle, bottom; 
    private JPanel topLeftPanel, topRightPanel;
    private JPanel p1Colors, p2Colors, p1Names, p2Names, bgColors, timers, fgColors, lineColors; 
    private JButton p1Chooser, p2Chooser, bgChooser, begin, loadGame, fgChooser, lineChooser;
    private JTextField p1Name, p2Name;
    private BoardFrame boardFrame;
    private JSpinner minutes;
    private Profile[] themes;
    private JComboBox profileSelector;
    private Image logo;
    private Profile profile;


    public StartFrame(Core core) {
        super("Start Menu");
        this.setLayout(new GridLayout(4, 0));
        
        logo = new ImageIcon(getClass().getResource("/images/logo.png")).getImage();
        JLabel logoPic = new JLabel(new ImageIcon(logo.getScaledInstance(120, 120, 50)));


       
        profile = new Profile();
        Profile basic = new Profile();

        themes = new Profile[4];
        themes[0] = basic;


        String[] themes2 = new String[themes.length];
        themes2[0] = "Basic";


        Font bigFont = new Font("Sans_Serif", Font.PLAIN, 40);
        Font mediumFont = new Font("Sans_Serif", Font.BOLD, 15);


        //title
        TITLE = new JPanel(new GridLayout(0, 2));
        JLabel rightTitle = new JLabel("Xiang Qi");
        rightTitle.setFont(bigFont);
        rightTitle.setHorizontalAlignment(SwingConstants.LEFT);
        logoPic.setHorizontalAlignment(SwingConstants.RIGHT);
        TITLE.add(logoPic);
        TITLE.add(rightTitle);


        //Player 1 stuff

        JPanel p1TitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        JLabel p1Title = new JLabel("Player 1:");
        p1Title.setFont(mediumFont);
        p1TitlePanel.add(p1Title);


        p1Names = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        p1Names.add(new JLabel("Name: "));
        p1Name = new JTextField("Player 1", 12);
        p1Names.add(p1Name);

        p1Colors = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p1Colors.add(new JLabel("Color: "));
        p1Chooser = new JButton("Select");
        p1Colors.add(p1Chooser);


        topLeftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        topLeftPanel.add(p1TitlePanel);
        topLeftPanel.add(p1Names);
        topLeftPanel.add(p1Colors);

        //Player 2 stuff

        JPanel p2TitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 5));
        JLabel p2Title = new JLabel("Player 2:");
        p2Title.setFont(mediumFont);
        p2TitlePanel.add(p2Title);


        p2Names = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p2Names.add(new JLabel("Name: "));
        p2Name = new JTextField("Player 2", 12);
        p2Names.add(p2Name);

        p2Colors = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        p2Colors.add(new JLabel("Color: "));
        p2Chooser = new JButton("Select");
        p2Colors.add(p2Chooser);

        topRightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        topRightPanel.add(p2TitlePanel);
        topRightPanel.add(p2Names);
        topRightPanel.add(p2Colors);

//====future update======
        //top panel stuff
        top = new JPanel(new GridLayout(1, 1));
        top.add(topLeftPanel);
        top.add(topRightPanel);

        //background stuff
        bgColors = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5));
        bgColors.add(new JLabel("Background Color"));
        bgChooser = new JButton("Select");
        bgColors.add(bgChooser);

        //foreground stuff
        fgColors = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5));
        fgColors.add(new JLabel("  Foreground Color"));
        fgChooser = new JButton("Select");
        fgColors.add(fgChooser);

        //lineColor stuff
        lineColors = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5));
        lineColors.add(new JLabel("             Line Colors"));
        lineChooser = new JButton("Select");
        lineColors.add(lineChooser);
        
//==========


        //timer 
        timers = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 5));
        JLabel timeTitle=new JLabel("Time Limit");
        timeTitle.setFont(mediumFont);
        timers.add(timeTitle);
        minutes = new JSpinner(new SpinnerNumberModel(10, 1, 60, 1));
        timers.add(minutes);
//Theme Selector 
        JPanel ComboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 10));
        profileSelector = new JComboBox(themes2);

        ComboPanel.add(timers);
        


        //middle 
        middle = new JPanel(new GridLayout(0, 2));
        JPanel middleLeftHolder = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        middleLeftHolder.add(bgColors);
        middleLeftHolder.add(fgColors);
        middleLeftHolder.add(lineColors);
        JPanel middleRightHolder = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        middleRightHolder.add(ComboPanel);
        middle.add(middleLeftHolder);
        middle.add(ComboPanel);


        //bottom stuff

        bottom = new JPanel(new GridLayout(0, 2));
        begin = new JButton("Start New Game");

        JPanel preview2 = new preview();
        JPanel beginPanel = new JPanel();
        beginPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        beginPanel.add(begin, CENTER_ALIGNMENT);
        bottom.add(preview2);
        bottom.add(beginPanel);


        //Action listeners

        p1Chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", profile.getP1Color());
                profile.setP1Color(temp);
                preview2.repaint();

            }
        });
        p2Chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", profile.getP2Color());
                profile.setP2Color(temp);
                preview2.repaint();

            }
        });
        bgChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", profile.getBackground());
                profile.setBackGround(temp);
                preview2.repaint();

            }
        });
        fgChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Chosoe Player 1 Color", Color.LIGHT_GRAY);
                profile.setForeGround(temp);
                preview2.repaint();

            }
        });

        lineChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color temp = JColorChooser.showDialog(null, "Choose Line Color", profile.getLineColor());
                profile.setLineColor(temp);
                preview2.repaint();

            }
        });

        profileSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseProfile();
                preview2.repaint();
            }
        });

        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile.setMinutes((int) minutes.getValue());
                profile.setP1String(p1Name.getText());
                profile.setP2String(p2Name.getText());
                core.start(profile);

                //core.getBoardPanel().setProfile(profile);
                boardFrame = core.getBoardFrame();
                //core.getBoardPanel().setProfile(profile);
                boardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                boardFrame.setSize(900, 700);
                boardFrame.setVisible(true);
                setVisible(false);
            }
        });


        add(TITLE);
        add(top);
        add(middle);
        add(bottom);

        this.setPreferredSize(new Dimension(600, 600));
        this.pack();
        this.setResizable(false);
        setVisible(true);


    }

    private void chooseProfile() {
        this.profile = themes[profileSelector.getSelectedIndex()];
    }

    //Preview Panel inner class
    class preview extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(profile.getBackground());
            int xOffset = 45;
            int yOffset = 10;
            g2.fill(new Rectangle2D.Double(xOffset, yOffset, 200, 100));

            g2.setColor(profile.getLineColor());
            g2.setStroke(new BasicStroke(2));
            g2.drawLine(0 + xOffset, 50 + yOffset, 200 + xOffset, 50 + yOffset);
            g2.drawLine(50 + xOffset, 0 + yOffset, 50 + xOffset, 100 + yOffset);
            g2.drawLine(150 + xOffset, 0 + yOffset, 150 + xOffset, 100 + yOffset);


            g2.setColor(profile.getForeGround());
            g2.fill(new Ellipse2D.Double(xOffset + 10, 10 + yOffset, 80, 80));
            g2.fill(new Ellipse2D.Double(xOffset + 110, 10 + yOffset, 80, 80));

            g2.setStroke(new BasicStroke(5));
            g2.setColor(profile.getP1Color());
            g2.draw(new Ellipse2D.Double(10 + xOffset, 10 + yOffset, 80, 80));
            g2.drawString("General", 27 + xOffset, 55 + yOffset);

            g2.setColor(profile.getP2Color());
            g2.draw(new Ellipse2D.Double(110 + xOffset, 10 + yOffset, 80, 80));
            g2.drawString("Elephant", 125 + xOffset, 55 + yOffset);
        }

    }

}
