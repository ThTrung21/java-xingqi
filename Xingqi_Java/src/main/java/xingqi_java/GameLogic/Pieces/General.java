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

public class General extends Piece {
    public General(Side side) {
        super(side);

        this.type = "General";
        this.canWinAlone = false;
    }

    @Override

    public void checkPattern(Move move) {

        super.checkPattern(move);

        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
        if (Math.abs(move.getDx()) > 1 || Math.abs(move.getDy()) > 1) {
            move.setValid(false);
        }

        //stays in the box
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