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
public class Elephant extends Piece {
    public Elephant(Side side) {
        super(side);
        this.type = "Elephant";
        this.canWinAlone = false;
    }

    @Override

    public void checkPattern(Move move) {
        super.checkPattern(move);
        //constraint its move to be diagonal only
        if (!move.isDiagonal()) {
            move.setValid(false);
        }
        //set diagonal length
        if (Math.abs(move.getDx()) != 2) {
            move.setValid(false);
        }

        //river crossing prevention
        if (side == Side.UP) {
            if (move.getFinalY() > 4) {
                move.setValid(false);
            }
        }
        if (side == Side.DOWN) {
            if (move.getFinalY() < 5) {
                move.setValid(false);
            }
        }

    }
}