package chessEngine.engine.Player;

/**
 * Created by Ricky Rajinder on 7/28/2017.
 */
public enum MoveStatus {
    DONE {
        @Override
        boolean isDone() {
            return false;
        }
    },
    ILLEGAL_MOVE {
        @Override
        boolean isDone() {
            return false;
        }
    },
    LEAVE_PLAYER_IN_CHECK {
        @Override
        boolean isDone() {
            return false;
        }
    };

    abstract boolean isDone();
}
