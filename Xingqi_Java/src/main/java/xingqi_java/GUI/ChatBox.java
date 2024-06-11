/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xingqi_java.GUI;
import xingqi_java.GameLogic.Board;
import xingqi_java.run.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
/**
 *
 * @author TRUNG
 */
class ChatBox extends JPanel {
    //    private JLabel systemOutput;
    private JTextArea systemOutput;

    public ChatBox(Core core) {
        JLabel title = new JLabel("Chat Log");
        systemOutput = new JTextArea(14, 18);
        JScrollPane scrollPane = new JScrollPane(systemOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        systemOutput.setCaretPosition(systemOutput.getDocument().getLength());
        setPreferredSize(new Dimension(220, 400));


        //setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        JButton endGameBtn = new JButton("End Game");
        endGameBtn.addActionListener(new ActionListener() {
            //calls an end to the game by setting the game as a stalemate
            @Override
            public void actionPerformed(ActionEvent e) {
                Board.setWinner(0);
                core.callEnd();
            }
        });
        add(endGameBtn, BorderLayout.SOUTH);
    }
    /**
     * Sets the text of the JTextArea as the same as the system outputted statements.
     *
     * @param text The text to be displayed in the chat box
     */
    public void appendText(final String text) {
        systemOutput.setText(systemOutput.getText() + text);
    }
}

/**
 * Redirects the output stream of System.out to the board frame's chat box.
 *
 * @author Michael Yu
 */
class StreamIntake extends OutputStream {

    private String string = "";
    private ChatBox chat;
    private PrintStream system;

    public StreamIntake(ChatBox chat, PrintStream system) {
        this.system = system;
        this.chat = chat;
    }

    @Override
    public void write(int b) throws IOException {
        char c = (char) b;
        String value = Character.toString(c);
        string += value;
        if (value.equals("\n")) {
            chat.appendText(string);
            string = "";
        }
        system.print(c);
    }
}
