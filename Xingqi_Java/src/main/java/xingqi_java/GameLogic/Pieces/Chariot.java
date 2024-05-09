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
public class Chariot extends Piece{

    public Chariot(Side side) {
        super(side);
        this.type = "Chariot";
        this.canWinAlone = true;
    }

    @Override

    public void checkPattern(Move move) {
        super.checkPattern(move);

        if (!move.isHorizontal() && !move.isVertical()) {
            move.setValid(false);
        }
    }
}
