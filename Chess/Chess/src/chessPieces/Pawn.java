/**
 * 
 */
package chessPieces;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.ImmutableList;

import chessBoard.Board;
import chessBoard.BoardUtils;
import chessEngine.Alliance;
import chessBoard.Move;
/**
 * @author Ricky Singh
 *
 */
public class Pawn extends Piece {
	
	private final static int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};

	/**
	 * @param piecePosition
	 * @param pieceAlliance
	 */
	public Pawn(final Alliance pieceAlliance, final int piecePosition) {

		super(pieceAlliance, PieceType.PAWN, piecePosition);
	}
	
	@Override
	public Collection<Move> calculateLegalMoves(Board board){
		
		final ArrayList<Move> legalMoves = new ArrayList<>();
	
		for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE){
			final int candidateDestinationCoordinate = this.piecePosition + (this.getAlliance().getDirection() + currentCandidateOffset );
			if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
				continue;
			}
			
			if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
				//Need to make pawnMove method later
				legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
			}
			else if (currentCandidateOffset == 16 && this.isFirstMove() && 
					(BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceAlliance.isBlack()) || 
					(BoardUtils.SEVENTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite())){
					final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection()*8);
					if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && 
							!board.getTile(candidateDestinationCoordinate).isTileOccupied() ) {
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));

			}
		}
			else if (currentCandidateOffset ==7 &&
				!(BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
				 BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()){
				if (board.getTile(candidateDestinationCoordinate).isTileOccupied()){
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if (this.pieceAlliance != pieceOnCandidate.getAlliance()){
						//TODO more to todo
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
					}
				}
				
				
			}
			else if (currentCandidateOffset == 9 &&
					!(BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
					 BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()){
				if (board.getTile(candidateDestinationCoordinate).isTileOccupied()){
					final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
					if (this.pieceAlliance != pieceOnCandidate.getAlliance()){
						//TODO more to todo
						legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}

	@Override
	public String toString(){
		return PieceType.PAWN.toString();
	}
}
