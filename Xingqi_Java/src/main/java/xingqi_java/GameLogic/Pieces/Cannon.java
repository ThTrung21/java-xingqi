
package xingqi_java.GameLogic.Pieces;
import xingqi_java.GameLogic.Move;
/**
 *
 * @author TRUNG
 */
public class Cannon extends Piece {
    public Cannon(Side side) {
        super(side);
        this.type = "Cannon";
        this.canWinAlone = false;
    }

    @Override

    public void checkPattern(Move move) {
        super.checkPattern(move);

        if (!(move.isHorizontal() || move.isVertical())) {
            move.setValid(false);
        }
    }
}
