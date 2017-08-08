package chessEngine.engine.Player;

import chessBoard.Board;
import chessBoard.Move;

/**
 * Created by Ricky Rajinder on 7/28/2017.
 */
public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    public MoveTransition (final Board transitionBoard,
                           final Move move,
                           final MoveStatus moveStatus){
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }
}
