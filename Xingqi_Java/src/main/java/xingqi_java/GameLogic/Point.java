/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xingqi_java.GameLogic;
import xingqi_java.GameLogic.Pieces.Piece;
/**
 *point demonstrate the position of a piece on the CHinese chest board
 * @author TRUNG
 */

public class Point {
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
        if (y > 4) {
            this.side = riverSide.downRiver;
        } else {
            this.side = riverSide.upRiver;
        }
    }

    private int x;
    private int y;

    private int x2;
    private int y2;

    private Piece piece;
    private riverSide side;

    /**
     * upRiver = player on top (player 2)
     * downRiver = player on bottom (player 1)
     */
    enum riverSide {
        upRiver,
        downRiver
    }


    /**
     * @return the piece on the point
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * @param newPiece the piece to replace the current piece with
     */
    public void setPiece(Piece newPiece) {
        this.piece = newPiece;
    }


    /**
     * @return the x coordinate
     */
    public int getX() {
        return this.x2;
    }

    /**
     * @return the y coordinate
     */
    public int getY() {
        return this.y2;
    }

    
    public void setPosition(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

}