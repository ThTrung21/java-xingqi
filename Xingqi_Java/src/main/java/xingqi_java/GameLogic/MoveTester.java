/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xingqi_java.GameLogic;
import xingqi_java.GameLogic.Pieces.Piece;
/**
 *
 * @author TRUNG
 */
class MoveTester {
    private Board board;
    private Move move;


    private int obstacleCount; //number of pieces in the way
    private boolean isClear; //if obstacles = 0
    private boolean attack;
    private boolean legal;

    /**
     * First test: 
     * move pattern validation
     * if its attacking move or not
     * count obstacles to validate the move 
     * if 2 generals facing each other
     * 
     * @param board the current board object
     * @param move  the move to be validated
     */
    
    
    
    
    MoveTester(Board board, Move move) {
        this.board = board;
        this.move = move;
        this.legal = true;

        // check if movement pattern is legal 
        CheckPiece();
        Piece curr = board.getPoint(move.getOriginX(), move.getOriginY()).getPiece();
        Piece captured = board.getPoint(move.getFinalX(), move.getFinalY()).getPiece();

        //  check if move is an attack + if the end point is blocked by friendly piece
        if (legal) {
            isAttack();
        }

        //  3. Check if the path is clear
        if (legal) {
            obstacleStats();

            if (!isClear) {
                if (board.getPoint(move.getOriginX(), move.getOriginY()).getPiece().toString().equals("Cannon")) {
                    if (!(obstacleCount == 1 && attack)) {
                        legal = false;
                    }
                } else {
                    legal = false;
                }
            } else {
                if (board.getPoint(move.getOriginX(), move.getOriginY()).getPiece().toString().equals("Cannon")) {
                    if (attack) {
                        legal = false;
                    }
                }
            }
        }

        //post move check
        if (legal) {
            board.doMove(move);
            if (!approveGenerals()) {
                legal = false;
            }
            board.undoMove(move, captured);
            board.updateGenerals();
        }


    }


    


    /**
     * Returns that the generals aren't facing each other by counting the obstacles between them if they're in line.
     *
     * @return True if they are facing each other (illegal)
     */
    private boolean approveGenerals() {

        //uses location to determine if generals are facing each other
        board.updateGenerals();

        if (board.getUpGeneralX() != board.getDownGeneralX()) {
            return true;
        } else {
            for (int i = board.getUpGeneralY() + 1; i < board.getDownGeneralY(); i++) {
                if (board.getPoint(board.getDownGeneralX(), i).getPiece() != null) {
                    obstacleCount++;
                }
            }

            // System.out.print(" Generals Exposed!");
            return obstacleCount != 0;
        }

    }


    /**
     * Checks if the move pattern is a valid move pattern and if there's a piece present.
     * If not, terminates the process
     */
    private void CheckPiece() {
        Piece temp = board.getPoint(move.getOriginX(), move.getOriginY()).getPiece();

        if (temp == null) {
            this.legal = false;
        } else {
            temp.checkPattern(move);
            if (!move.isValid()) {
                this.legal = false;
            }
        }

    }

    /**
     * Checks the destination piece to see if we're attacking or self blocked
     */
    private void isAttack() {
        if (board.getPoint(move.getFinalX(), move.getFinalY()).getPiece() == null) {
            attack = false;
        } else {
            Piece.Side origin = board.getPoint(move.getOriginX(), move.getOriginY()).getPiece().getSide();
            Piece.Side dest = board.getPoint(move.getFinalX(), move.getFinalY()).getPiece().getSide();
            if (origin != dest) {
                attack = true;
            }
            if (origin == dest) {
                this.attack = false;
                this.legal = false;
               
            }
        }

    }

    /**
     * Finds out if there are obstacles, and if so, how many?
     *  seeing if we have one obstacle for the cannon
     * 
     */
    private void obstacleStats() {

        isClear = true;
        this.obstacleCount = 0;

        //vertical move
        if (move.isVertical()) {
            if (move.getDy() > 0) {
                for (int y = move.getOriginY() + 1; y < move.getFinalY(); y++) {
                    if (board.getPoint(move.getOriginX(), y).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            } else if (move.getDy() < 0) {
                for (int y = move.getOriginY() - 1; y > move.getFinalY(); y--) {
                    if (board.getPoint(move.getOriginX(), y).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }


        }
        //horizontal move
        else if (move.isHorizontal()) {
            if (move.getDx() > 0) {
                for (int x = move.getOriginX() + 1; x < move.getFinalX(); x++) {
                    if (board.getPoint(x, move.getOriginY()).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            } else if (move.getDx() < 0) {
                for (int x = move.getOriginX() - 1; x > move.getFinalX(); x--) {
                    if (board.getPoint(x, move.getOriginY()).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }
            //diagonal move
        }
        //diagonal move
        else if (move.isDiagonal()) {

            //left up
            if (move.getDx() < 0 && move.getDy() < 0) {
                for (int x = 1; x < move.getDx(); x++) {
                    if (board.getPoint(move.getOriginX() - x, move.getOriginY() - x).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }
            //left down
            else if (move.getDx() < 0 && move.getDy() > 0) {
                for (int x = 1; x < move.getDx(); x++) {
                    if (board.getPoint(move.getOriginX() - x, move.getOriginY() + x).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }
            //right down
            else if (move.getDx() > 0 && move.getDy() > 0) {
                for (int x = 1; x < move.getDx(); x++) {
                    if (board.getPoint(move.getOriginX() + x, move.getOriginY() + x).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }

            //right up
            else {// (move.getDx() > 0 && move.getDy() > 0) {
                for (int x = 1; x < move.getDx(); x++) {
                    if (board.getPoint(move.getOriginX() + x, move.getOriginY() - x).getPiece() != null) {
                        obstacleCount++;
                    }
                }
            }
        }
        //only for knights, as only knights have non linear moves. Knights are only blocked by the nearest pieces.
        else {

            if (move.getDx() == 2) {
                if (board.getPoint(move.getOriginX() + 1, move.getOriginY()).getPiece() != null) {
                    obstacleCount++;
                }
            } else if (move.getDx() == -2) {
                if (board.getPoint(move.getOriginX() - 1, move.getOriginY()).getPiece() != null) {
                    obstacleCount++;
                }
            } else if (move.getDy() == 2) {
                if (board.getPoint(move.getOriginX(), move.getOriginY() + 1).getPiece() != null) {
                    obstacleCount++;
                }
            } else if (move.getDy() == -2) {
                if (board.getPoint(move.getOriginX(), move.getOriginY() - 1).getPiece() != null) {
                    obstacleCount++;
                }
            }


        }
        if (obstacleCount != 0) {
            isClear = false;
        }


    }

    boolean isLegal() {
        return legal;
    }
}