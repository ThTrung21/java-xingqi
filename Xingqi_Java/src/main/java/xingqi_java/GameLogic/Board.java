
package xingqi_java.GameLogic;


import xingqi_java.GameLogic.Pieces.*;

/**

 * @author TRUNG
 */
public class Board {


    //test

    public static final int PLAYER1_WINS = 1;
    public static final int PLAYER2_WINS = 2;
    public static final int PLAYER1_TIMEOUT_WIN = 3;
    public static final int PLAYER2_TIMEOUT_WIN = 4;
    public static final int DRAW = 0;
    public static final int NA = -1;

    private Point[][] gBoard;
    private int upGeneralX = 4;
    private int upGeneralY = 0;
    private int downGeneralX = 4;
    private int downGeneralY = 9;
    private boolean upCheck = false; //up is in check
    private boolean downCheck = false; //down is in check

    private static int winner;



    public Board() {
        winner = NA;
        gBoard = new Point[10][9];
        initialize(gBoard);

    }

    public boolean tryMove3(Move move, Player player) {

        if (new MoveTester(this, move).isLegal()) {

            Piece curr = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
            Piece captured = this.gBoard[move.getFinalY()][move.getFinalX()].getPiece();

            int x = move.getOriginX();
            int y = move.getOriginY();
            int finalX = move.getFinalX();
            int finalY = move.getFinalY();


            if (true){//(curr.getSide() == player.getPlayerSide()) {
                doMove(move);
                testCheck();
                if (curr.getSide() == Piece.Side.UP && upCheck) {
                    System.out.println(" Illegal Move, you're in check");
                    undoMove(move, captured);
                    return false;
                }
                if (curr.getSide() == Piece.Side.DOWN && downCheck) {
                    System.out.println(" Illegal Move, you're in check");
                    undoMove(move, captured);
                    return false;


                } else {
                    //the move is legal, now lets see if it's a winning move.
                    if (upCheck && curr.getSide() == Piece.Side.DOWN) {
                        if (checkMate(Piece.Side.UP)) {
                            winner = PLAYER1_WINS;
                        }
                    } else if (downCheck && curr.getSide() == Piece.Side.UP) {
                        if (checkMate(Piece.Side.DOWN)) {

                        }


                        //the move is legal, check stalemate
                    } else if (curr.getSide() == Piece.Side.DOWN) {
                        if (checkMate(Piece.Side.UP) || separated()) {
                            winner = DRAW;
                        }
//                        return true;
                    } else if (curr.getSide() == Piece.Side.UP) {
                        if (checkMate(Piece.Side.DOWN) || separated()) {
                            winner = DRAW;
                        }
//                        return true;
                    }
                    System.out.println("Moved " + curr + " from (" + x + ", " + y + ") to (" + finalX + ", " + finalY + ")");
                    if (captured != null) {
                        //player.addPieceCaptured(captured);
                        System.out.println(captured + " Captured!");
                        MoveLogger.addMove(new Move(curr, captured, x, y, finalX, finalY));
                    } else {
                        MoveLogger.addMove(new Move(curr, x, y, finalX, finalY));
                    }

                    //DO OTHER THINGS =============

                    return true;
                    //   }

                }
            } else {
                System.out.println("That's not your piece");
                return false;
            }
        }
        System.out.println("Illegal Move");
        return false;


    }



    /**
     * 
     *
     * @param move the specified move
     */
    void doMove(Move move) {
        Piece curr = gBoard[move.getOriginY()][move.getOriginX()].getPiece();
        //Piece captured = this.gBoard[move.getFinalY()][move.getFinalX()].getPiece();
        this.gBoard[move.getFinalY()][move.getFinalX()].setPiece(curr);
        this.gBoard[move.getOriginY()][move.getOriginX()].setPiece(null);
    }


    /**
     * Physically undoes a move by altering the state of the pieces
     * Not for use in the undo button. It used for move testing reasons.
     *
     * @param move the current move
     * @param captured The piece that was previously captured, so that it can be restored
     */
    void undoMove(Move move, Piece captured) {
        Piece curr = getPoint(move.getFinalX(), move.getFinalY()).getPiece();
        getPoint(move.getOriginX(), move.getOriginY()).setPiece(curr);
        getPoint(move.getFinalX(), move.getFinalY()).setPiece(captured);
        //System.out.print(" Illegal Move");
    }





    void updateGenerals() {
        //finds location of generals

        for (int x = 3; x < 6; x++) {
            for (int y = 0; y < 3; y++) {
                Piece curr = getPoint(x, y).getPiece();
                if (curr != null && curr.toString().equals("General")) {
                    setUpGeneralX(x);
                    setUpGeneralY(y);
                }

            }

            for (int y = 7; y < 10; y++) {
                Piece curr = getPoint(x, y).getPiece();
                if (curr != null && curr.toString().equals("General")) {
                    setDownGeneralX(x);
                    setDownGeneralY(y);
                }
            }
        }
    }


    private void testCheck() {
        updateGenerals();
        downCheck = false;
        upCheck = false;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {
                if (getPoint(x, y).getPiece() != null) {

                    if (!downCheck && getPoint(x, y).getPiece().getSide() == Piece.Side.UP) {
                        //if (new MoveTester(this, new Move(x, y, downGeneralX, downGeneralY), 0).isLegal()) {
                            downCheck = true;
//                            System.out.println("Down is in check");
                        //}
                    } else if (!upCheck && getPoint(x, y).getPiece().getSide() == Piece.Side.DOWN) {
                        //if (new MoveTester(this, new Move(x, y, upGeneralX, upGeneralY), 0).isLegal()) {
                            upCheck = true;
//                            System.out.println("up is in check");
                        //}
                    }
                }
            }
        }
    }


    private boolean checkMate(Piece.Side loserSide) {
        updateGenerals();


        //running through every loser piece
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {

                if (getPoint(x, y).getPiece() != null && getPoint(x, y).getPiece().getSide() == loserSide) {

                    //running through every possible point to generate every possible move
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 10; j++) {
                            Move tempMove = new Move(x, y, i, j); //generating the temporary move
                            Piece tempCaptured = getPoint(i, j).getPiece();
                            //if that move is legal then attempt it.
                            if (new MoveTester(this, tempMove).isLegal()) { //trying every possible move for the piece
                                doMove(tempMove); //doing the temporary move
                                testCheck(); //updates check status
                                //if any of these moves were both legal, and result with us not being in check, we aren't in checkmate.
                                if (loserSide == Piece.Side.DOWN) {
                                    if (!downCheck) {
                                        undoMove(tempMove, tempCaptured);
                                        return false;
                                    }
                                }
                                if (loserSide == Piece.Side.UP) {
                                    if (!upCheck) {
                                        undoMove(tempMove, tempCaptured);
                                        return false;
                                    }
                                }
                                undoMove(tempMove, tempCaptured);

                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 
     *
     * @return true if we're at a stalemate from peices that can never cause checkmate
     */
    private boolean separated() {

        int cannonCounter = 0;
        int pieceCounter = 0;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 10; y++) {
                if (getPoint(x, y).getPiece() != null) {
                    pieceCounter++;
                    if (getPoint(x, y).getPiece().canWinAlone()) {
                        return false;
                    }
                    if (getPoint(x, y).getPiece().toString().equals("Cannon")) {
                        cannonCounter++;
                    }
                }
            }
        }

        if (pieceCounter > 3 && cannonCounter >= 1) {
            return false;
        }
        return true;
    }


    /**
     *
     * @param board a board object to be initialized
     */
    private static void initialize(Point[][] board) {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                board[y][x] = new Point(x, y);
            }
        }

        //Chariots
        board[0][0].setPiece(new Chariot(Piece.Side.UP));
        board[0][8].setPiece(new Chariot(Piece.Side.UP));

        board[9][0].setPiece(new Chariot(Piece.Side.DOWN));
        board[9][8].setPiece(new Chariot(Piece.Side.DOWN));

        //Cannons
        board[2][1].setPiece(new Cannon(Piece.Side.UP));
        board[2][7].setPiece(new Cannon(Piece.Side.UP));

        board[7][1].setPiece(new Cannon(Piece.Side.DOWN));
        board[7][7].setPiece(new Cannon(Piece.Side.DOWN));

        //Horses
        board[0][1].setPiece(new Horse(Piece.Side.UP));
        board[0][7].setPiece(new Horse(Piece.Side.UP));

        board[9][1].setPiece(new Horse(Piece.Side.DOWN));
        board[9][7].setPiece(new Horse(Piece.Side.DOWN));

        //Elephants
        board[0][2].setPiece(new Elephant(Piece.Side.UP));
        board[0][6].setPiece(new Elephant(Piece.Side.UP));

        board[9][2].setPiece(new Elephant(Piece.Side.DOWN));
        board[9][6].setPiece(new Elephant(Piece.Side.DOWN));

        //Guard/
        board[0][3].setPiece(new Guard(Piece.Side.UP));
        board[0][5].setPiece(new Guard(Piece.Side.UP));

        board[9][3].setPiece(new Guard(Piece.Side.DOWN));
        board[9][5].setPiece(new Guard(Piece.Side.DOWN));

        //General/King
        board[0][4].setPiece(new General(Piece.Side.UP));
        board[9][4].setPiece(new General(Piece.Side.DOWN));

        //Solider/pawns
        board[3][0].setPiece(new Soldier(Piece.Side.UP));
        board[3][2].setPiece(new Soldier(Piece.Side.UP));
        board[3][4].setPiece(new Soldier(Piece.Side.UP));
        board[3][6].setPiece(new Soldier(Piece.Side.UP));
        board[3][8].setPiece(new Soldier(Piece.Side.UP));

        board[6][0].setPiece(new Soldier(Piece.Side.DOWN));
        board[6][2].setPiece(new Soldier(Piece.Side.DOWN));
        board[6][4].setPiece(new Soldier(Piece.Side.DOWN));
        board[6][6].setPiece(new Soldier(Piece.Side.DOWN));
        board[6][8].setPiece(new Soldier(Piece.Side.DOWN));

    }

    /**
     * An Ascii board used for testing purposes
     */
    protected void printBoard() {
        System.out.println("       0         1        2         3         4         5         6         7         8     ");
        String hLine = "  -------------------------------------------------------------------------------------------";
        System.out.println(hLine);

        for (int y = 0; y < 10; y++) {
            System.out.print(y + " |");
            for (int x = 0; x < 9; x++) {

                if (gBoard[y][x].getPiece() == null) {
                    System.out.printf("%8s%2s", "", "|");
                } else {
                    System.out.printf("%8s%2s", gBoard[y][x].getPiece(), "|");
                }

            }
            System.out.println();
            System.out.println(hLine);

        }
    }

    /**
     * Returns a point object at a specified board location (not array index, but coordinate instead.
     *
     * @param x the x coordinate on the board where the left most point is 0. (note not indecies)
     * @param y the y coordinate on the board where the top most point is 0. (note not indecies)
     * @return the point object at the specified location
     */
    public Point getPoint(int x, int y) {
        return gBoard[y][x];
    }

    /**
     * @return Player 2's generalX position.
     */
    int getUpGeneralX() {
        return upGeneralX;
    }

    /**
     * Update the boards knowledge of where Player 2's general is on the x axis. Does not actually move the general.
     *
     * @param upGeneralX Player 2's General's X coordinate
     */
    private void setUpGeneralX(int upGeneralX) {
        this.upGeneralX = upGeneralX;
    }

    /**
     * Retrieve the boards knowledge of where Player 2's general is on the y axis.
     *
     * @return Player 2's General's X coord
     */
    int getUpGeneralY() {
        return upGeneralY;
    }

    /**
     * Update the boards knowledge of where Player 2's general is on the y axis. Does not actually move the general.
     * @param upGeneralY Player 2's General's Y coord
     */
    private void setUpGeneralY(int upGeneralY) {
        this.upGeneralY = upGeneralY;
    }

    /**
     * Retrieve the boards knowledge of where Player 1's general is on the x axis.
     * @return Player 1's gneral's x coord
     */
    int getDownGeneralX() {
        return downGeneralX;
    }

    /**
     * Update the boards knowledge of where Player 1's general is on the x axis. Does not actually move the general.
     * @param downGeneralX Player 2 General x position
     */
    private void setDownGeneralX(int downGeneralX) {
        this.downGeneralX = downGeneralX;
    }

    /**
     * Retrieve the boards knowledge of where Player 1's general is on the y axis.
     * @return Player 1 General's Y position
     */
    int getDownGeneralY() {
        return downGeneralY;
    }

    /**
     * Update the boards knowledge of where Player 1's general is on the x axis. Does not actually move the general.
     * @param downGeneralY Player 1 General Y position
     */
    private void setDownGeneralY(int downGeneralY) {
        this.downGeneralY = downGeneralY;
    }


    /**
     * Sets the winner
     <ul>
     *                  <li>-1 Game still running</li>
     *                  <li>0 Game is draw</li>
     *                  <li>1 Player 1 wins</li>
     *                  <li>Player 2 Wins</li>
     * </ul>
     * @param winnerNum uses the static finals from board
     *
     *
     */
    public static void setWinner(int winnerNum) {
        winner = winnerNum;
    }

    /**
     * returns the win status
     * @return an integer representing the current winner, stalemate, or that no game ending condition has been met. (-1, 0, 1, 2)
     */
    public int getWinner() {
        return winner;
    }
}