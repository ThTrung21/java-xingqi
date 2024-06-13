/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xingqi_java.GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import xingqi_java.run.Core;
/**
 *
 * @author TRUNG
 */
public class BoardMenu extends JMenuBar {
    private String languageKey;

    public BoardMenu(Core core) {
        
        JMenuItem saveItem = new JMenuItem("Export Game Log");
        saveItem.setMnemonic('E');
        saveItem.addActionListener(new ActionListener() {
            //Saves the board when the player presses saveItem
            public void actionPerformed(ActionEvent event) {
                try {
                    core.saveGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        fileMenu.add(saveItem);


        //creates options to change the appearance of the board pieces
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic('V');
        String[] language = {"English", "Original"};
        JMenu languageMenu = new JMenu("Skin");
        languageMenu.setMnemonic('a');
        ButtonGroup languageButtonGroup = new ButtonGroup(); // manages languages
        JRadioButtonMenuItem englishButton = new JRadioButtonMenuItem("English"); // create item

        JRadioButtonMenuItem englishPicButton = new JRadioButtonMenuItem("Original");
        languageMenu.add(englishButton); // add item to language menu
        languageButtonGroup.add(englishButton);

        languageMenu.add(englishPicButton);
        languageButtonGroup.add(englishPicButton);
        ActionListener itemHandler = new ActionListener() {
            // process language selection to change the appearance of the pieces
            public void actionPerformed(ActionEvent event) {
                // draws each piece and its english name with user chosen colors
                if (englishButton.isSelected()) {
                    core.getBoardPanel().setLanguage("English");
                    core.getBoardPanel().userRepaint();
                }
                // displays images of each piece's chinese character
                if (englishPicButton.isSelected()) {
                    core.getBoardPanel().setLanguage("Original");
                    core.getBoardPanel().userRepaint();
                }
            }
        };
        englishButton.addActionListener(itemHandler);
        englishPicButton.addActionListener(itemHandler);
        englishButton.setSelected(true); // select first language item

        viewMenu.add(languageMenu);
        //viewMenu.addSeparator();
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        JMenuItem rules = new JMenuItem("How to Play...");
        rules.addActionListener(new ActionListener() {
            //opens up the wikipedia page for the rules of Chinese Chess
            public void actionPerformed(ActionEvent e) {
                try {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://en.wikipedia.org/wiki/Xiangqi"));
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });
        helpMenu.add(rules);
        add(fileMenu); // add file menu to menu bar
        add(viewMenu);
        add(helpMenu);
    }

    public String getLanguage() {
        return languageKey;
    }
}
