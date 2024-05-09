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
public class Horse extends Piece {
    public Horse(Side side) {
        super(side);
        this.type = "Horse";
        this.canWinAlone = true;
    }

    @Override

    public void checkPattern(Move move) {
        super.checkPattern(move);

        //constraint the Horse move
        if (!((Math.abs(move.getDx()) == 1 && Math.abs(move.getDy()) == 2) || (Math.abs(move.getDx()) == 2 && Math.abs(move.getDy()) == 1))) {
            move.setValid(false);
        }
    }
}
