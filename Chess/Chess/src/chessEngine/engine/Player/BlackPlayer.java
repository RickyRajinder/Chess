package chessEngine.engine.Player;

import chessBoard.Board;
import chessBoard.Move;
import chessEngine.Alliance;
import chessPieces.Piece;

import java.util.Collection;

/**
 * Created by Ricky Rajinder on 7/28/2017.
 */
public class BlackPlayer extends Player {
    public BlackPlayer(final Board board, final Collection<Move> whiteStandardLegalMoves, final Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }
}
