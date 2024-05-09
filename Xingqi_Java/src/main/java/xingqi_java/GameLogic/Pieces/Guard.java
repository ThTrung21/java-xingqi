/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xingqi_java.GameLogic.Pieces;
import xingqi_java.GameLogic.Move;
/**
 *
 * @author TRUNG
 */
public class Guard extends Piece {

    public Guard(Side side) {
        super(side);
        this.type = "Guard";
        this.canWinAlone = false;
    }

    @Override

    public void checkPattern(Move move) {
        super.checkPattern(move);

        if (!move.isDiagonal()) {
            move.setValid(false);
        }
        //limit to 1 diag each only
        if (Math.abs(move.getDx()) != 1) {
            move.setValid(false);
        }
        
        //constraint guard move to be inside the box
        if (move.getFinalX() < 3 || move.getFinalX() > 5) {
            move.setValid(false);
        }

        if (side == Side.UP) {
            if (move.getFinalY() > 2) {
                move.setValid(false);
            }
        }
        if (side == Side.DOWN) {
            if (move.getFinalY() < 7) {
                move.setValid(false);
            }
        }


    }
}
