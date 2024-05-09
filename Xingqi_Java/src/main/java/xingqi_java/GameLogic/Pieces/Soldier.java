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
public class Soldier extends Piece {
    


    public Soldier(Side side) {
        super(side);
        this.type = "Soldier";
        this.canWinAlone = true;
    }

    @Override



    public void checkPattern(Move move) {
        super.checkPattern(move);

        //finds which side of river it's on, and sets it as member data
        Side curSide;
        if (move.getOriginY() <= 4) {
            curSide = Side.UP;
        } else {
            curSide = Side.DOWN;
        }

        //checks for vertical forward moves only based on side
        if (side == curSide) {
            if (this.side == Side.UP) {
                if (move.getDy() != 1 || !move.isVertical()) {
                    move.setValid(false);
                }
            }
            if (side == Side.DOWN) {
                if (move.getDy() != -1 || !move.isVertical()) {
                    move.setValid(false);
                }
            }

        }
        //allows for horizontal movement once river is crossed
        if (this.side != curSide) {
            if (!move.isHorizontal() && !move.isVertical()) {
                move.setValid(false);
            }

            if (this.side == Side.UP) {
                if (!(move.getDy() == 1 || Math.abs(move.getDx()) == 1)) {
                    move.setValid(false);
                }
            }
            if (this.side == Side.DOWN) {
                if (!(move.getDy() == -1 || Math.abs(move.getDx()) == 1)) {
                    move.setValid(false);
                }
            }

        }
    }
}
