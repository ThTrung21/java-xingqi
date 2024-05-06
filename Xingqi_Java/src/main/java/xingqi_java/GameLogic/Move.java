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
public class Move {
    private Piece piece;
    private Piece capturedPiece;
    
    private int originX;
    private int originY;
    private int finalX;
    private int finalY;
     
    private int dx;
    private int dy;
    private boolean isHorizontal;
    private boolean isVertical;
    private boolean isDiagonal;
    private boolean isValid;
    
    private int numOfObstacles;
    
    
    
    //a simple move construct, no checking for move validation
    public Move(int originX, int originY, int finalX, int finalY) {
        this.originX = originX;
        this.originY = originY;
        this.finalX = finalX;
        this.finalY = finalY;

        this.dx = finalX - originX;
        this.dy = finalY - originY;
        if (dx == 0 && dy != 0) {
            this.isVertical = true;
        }
        if (dy == 0 && dx != 0) {
            this.isHorizontal = true;
        }
        if (Math.abs(dx) == Math.abs(dy) && dx != 0) {
            this.isDiagonal = true;
        }
    }
    
    //use for the logger.
    public Move(Piece piece, int originX, int originY, int finalX, int finalY) {
        //this iteration makes the move object hold the piece? Not sure how to structure
        this.piece = piece;
        this.originX = originX;
        this.originY = originY;
        this.finalX = finalX;
        this.finalY = finalY;

        this.dx = finalX - originX;
        this.dy = finalY - originY;
        if (dx == 0 && dy != 0) {
            this.isVertical = true;
        }
        if (dy == 0 && dx != 0) {
            this.isHorizontal = true;
        }
        if (Math.abs(dx) == Math.abs(dy) && dx != 0) {
            this.isDiagonal = true;
        }
    }
    
    
    //used in move logger for logging an attack move. logging the attacker and the attacked.
    public Move(Piece piece, Piece capturedPiece, int originX, int originY, int finalX, int finalY) {

        this.piece = piece;
        this.capturedPiece = capturedPiece;
        this.originX = originX;
        this.originY = originY;
        this.finalX = finalX;
        this.finalY = finalY;

        this.dx = finalX - originX;
        this.dy = finalY - originY;
        if (dx == 0 && dy != 0) {
            this.isVertical = true;
        }
        if (dy == 0 && dx != 0) {
            this.isHorizontal = true;
        }
        if (Math.abs(dx) == Math.abs(dy) && dx != 0) {
            this.isDiagonal = true;
        }
    }
    
    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public boolean isDiagonal() {
        return isDiagonal;
    }

    boolean isValid() {
        return isValid;
    }
    
    public void setValid(boolean valid) {
        this.isValid = valid;
    }

    public Piece getPiece() {
        return piece;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    @Override
    public String toString() {
        return originX + ", " + originY + ", " + finalX + ", " + finalY;
    }

}