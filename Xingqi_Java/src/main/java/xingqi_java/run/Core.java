package xingqi_java.run;

import xingqi_java.GUI.*;
import xingqi_java.GameLogic.Board;
import xingqi_java.GameLogic.Move;
import xingqi_java.GameLogic.MoveLogger;
import xingqi_java.GameLogic.Pieces.Piece;
import xingqi_java.GameLogic.Player;


import javax.swing.*;



public class Core {

    //    private static StartFrame startFrame;
    private BoardFrame boardFrame;
    private BoardPanel boardPanel;
    private TurnTimerPanel timerPanel;
    private BoardMenu boardMenu;
    private Board board;
    private int counter;
    private Player player1;
    private Player player2;
    private static StartFrame startFrame;
    private EndScreen endScreen;
    private Profile profile;



    /**
     * Upon instantiation the start menu runs, which in turn starts everything else.
     */
    public Core() {
//        System.out.print(getClass().getClassLoader().getResource("images/logo.png"));
        startFrame = new StartFrame(this);
    }

    /**
     * Takes in a profile object to instantiate the rest of the application with the users start menu options.
     *
     * @param profile
     */
    public void start(Profile profile) {
        this.profile = profile;
        player1 = new Player(1, profile.getP1String(), Piece.Side.DOWN, this);
        player2 = new Player(2, profile.getP2String(), Piece.Side.UP, this);
        board = new Board();
        boardPanel = new BoardPanel(this);
        boardMenu = new BoardMenu(this);
        timerPanel = new TurnTimerPanel(player1, player2, profile);
        boardFrame = new BoardFrame(this);
        counter = 0;
        player1.startTurnTimer(timerPanel);


    }

    /**
     * Takes a move and tries to run it. if the move goes through then it switches the timer and player.
     *
     * @param move move received from the gui.
     */
    public void playMove(Move move) {
//        System.out.println(counter);

        if (counter % 2 == 0) {
            if (board.tryMove3(move, player1)) {
                //first round
                player1.stopTurnTimer();
                player2.startTurnTimer(timerPanel);


                counter++;
            }
        } else if (counter % 2 == 1) {
            //player2.startTurnTimer(timerPanel);
            if (board.tryMove3(move, player2)) {
                player2.stopTurnTimer();
                player1.startTurnTimer(timerPanel);
                counter++;
            }
            //player2.stopTurnTimer();
        }
//         Broken Win Screen
        getBoardPanel().userRepaint();
        if (board.getWinner() != Board.NA) {
            System.out.println("GAME OVER");
            callEnd();
        }

    }
    /**
     * Calls an end to the game and opens the endScreen.
     */
    public void callEnd() {
        player1.stopTurnTimer();
        player2.stopTurnTimer();
        endScreen = new EndScreen(this, board.getWinner(), profile);

    }
    /**
     * Saves the game to the computer as a .txt file.
     */
    public void saveGame() throws Exception {
        String os = System.getProperty("os.name").toLowerCase();
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        if (os.contains("mac"))
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Specify where to save game");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        parentFrame.setVisible(true);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            System.out.println("Current Directory: " + fileChooser.getCurrentDirectory());
            System.out.println("Selected File" + fileChooser.getSelectedFile());
            MoveLogger.saveAllMoves(player1, player2, fileChooser.getSelectedFile());
            System.out.println("Game Saved");
            parentFrame.setVisible(false);
        } else {
            System.out.println("No Selection");
            parentFrame.setVisible(false);
        }
    }
    public void setInvisible(){
        boardFrame.setVisible(false);
    }
    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public BoardFrame getBoardFrame() {
        return boardFrame;
    }

    public TurnTimerPanel getTurnTimerPanel() {
        return timerPanel;
    }

    public Board getBoard() {
        return board;
    }

    public BoardMenu getBoardMenu() {
        return boardMenu;
    }

    public void setProfile(Profile newProfile) {
        this.profile = newProfile;
    }

    public Profile getProfile() {
        return this.profile;
    }
}