package chessBoard;

import chessPieces.Piece;

/**
 * Models a legal move 
 * @author Ricky Singh
 *
 */
public abstract class Move {

	final Board board;
	final Piece movedPiece;
	final int destinationCoordinate;
	
	public Move (final Board board,
		  final Piece piece,
		  final int destinationCoordinate) {
		this.board = board;
		this.movedPiece = piece;
		this.destinationCoordinate = destinationCoordinate;
	}

	public int getDestinationCoordinate(){
		return this.destinationCoordinate;
	}

    public abstract Board execute();

    public static final class MajorMove extends Move {

		/**
		 * @param board
		 * @param piece
		 * @param destinationCoordinate
		 */
		public MajorMove(final Board board, 
				final Piece piece, 
				final int destinationCoordinate) {
			super(board, piece, destinationCoordinate);
		}

		@Override
        public Board execute() {
		    final Board.Builder builder = new Board.Builder();
		    for (final Piece piece : board.currentPlayer().getActivePieces()){
		       //TODO hashcode and equals methods for Piece class
		         if (!this.movedPiece.equals(piece))
		            builder.setPiece(piece);
            }
            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()){
		        builder.setPiece(piece);
            }

            builder.setPiece(null);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
		    return builder.build();
        }
	}
	public static final class AttackMove extends Move {
		final Piece attackedPiece;
		/**
		 * @param board
		 * @param piece
		 * @param destinationCoordinate
		 */
		public AttackMove(final Board board, 
				final Piece piece, 
				final int destinationCoordinate, final Piece attackedPiece) {
			super(board, piece, destinationCoordinate);
			this.attackedPiece = attackedPiece;
		}
        @Override
        public Board execute() {
            return null;
        }
	}
	
}
