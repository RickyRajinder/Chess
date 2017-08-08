package chessPieces;

import chessEngine.Alliance;

import java.util.Collection;

import chessBoard.Board;
import chessBoard.Move;

/**
 * Models a chess piece
 * @author Ricky Singh
 *
 */
public abstract class Piece {

	protected final PieceType pieceType;
	protected final int piecePosition;
	protected final Alliance pieceAlliance;
	//TODO more work here
	protected final boolean isFirstMove;
	
	Piece(final Alliance pieceAlliance,
		  final PieceType pieceType,
		  final int piecePosition){
		this.pieceType = pieceType;
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
		this.isFirstMove = false;
	}
	
	public Alliance getAlliance(){

		return pieceAlliance;
	}
	public int getPiecePosition(){

		return this.piecePosition;
	}

	public boolean isFirstMove(){

		return this.isFirstMove;
	}

	public PieceType getPieceType(){
		return pieceType;
	}
	public abstract Collection<Move> calculateLegalMoves(final Board board);

	public enum PieceType {
		PAWN("P") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		KNIGHT("N") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		ROOK("R") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		QUEEN("Q") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		BISHOP("B") {
			@Override
			public boolean isKing() {
				return false;
			}
		},
		KING("K") {
			@Override
			public boolean isKing() {
				return true;
			}
		};

		private String pieceName;

		PieceType(final String pieceName) {
			this.pieceName = pieceName;
		}

		@Override
		public String toString(){
			return this.pieceName;
		}

		public abstract boolean isKing();
	}
}
