/**
 * 
 */
package chessPieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;

import chessBoard.Board;
import chessBoard.BoardUtils;
import chessBoard.Move;
import chessBoard.Tile;
import chessEngine.Alliance;

/**
 * @author Ricky Singh
 *
 */
public class Knight extends Piece {

	private static final int[] POSSIBLE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
	
	/**
	 * @param piecePosition
	 * @param pieceAlliance
	 */
	public Knight(final Alliance pieceAlliance, final int piecePosition) {

		super(pieceAlliance, PieceType.KNIGHT,piecePosition);
	}
	
	public Collection<Move> calculateLegalMoves(final Board board){
		final List<Move> legalMoves = new ArrayList<>();
		for(final int currentCandidateOffset: POSSIBLE_MOVE_COORDINATES){
			int possibleDestinationCoordinate = this.piecePosition + currentCandidateOffset;
			if (BoardUtils.isValidTileCoordinate(possibleDestinationCoordinate)){
				
				if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) || isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset) || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)){
					continue;
				}
				
				final Tile possibleDestinationTile = board.getTile(possibleDestinationCoordinate);
				
				if (!possibleDestinationTile.isTileOccupied()){
					legalMoves.add(new Move.MajorMove(board, this, possibleDestinationCoordinate));
				}
				else {
					final Piece pieceAtDestination = possibleDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getAlliance();
					
					if (this.pieceAlliance != pieceAlliance){
						legalMoves.add(new Move.AttackMove(board, this, possibleDestinationCoordinate, pieceAtDestination));
					}
				}
			}
		}
		return ImmutableList.copyOf(legalMoves);
	}

	@Override
	public String toString(){
		return PieceType.KNIGHT.toString();
	}
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) || (candidateOffset == -6) || (candidateOffset == 15));
	}
	
	private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset ==6));
	}
	
	private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) || (candidateOffset == 10) || (candidateOffset == 17));
	}
	
	
}
