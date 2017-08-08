/**
 * 
 */
package chessPieces;

import chessEngine.Alliance;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.ImmutableList;

import chessBoard.Board;
import chessBoard.BoardUtils;
import chessBoard.Move;
import chessBoard.Tile;

/**
 * @author Ricky Singh
 *
 */
public class Queen extends 	Piece {

	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9,-8,-7,-1,1,7,8,9};

	/**
	 * @param piecePosition
	 * @param pieceAlliance
	 */
    public Queen(final Alliance pieceAlliance, final int piecePosition) {

    	super(pieceAlliance, PieceType.QUEEN, piecePosition);
	}
	
	@Override
	public Collection<Move> calculateLegalMoves(Board board) {
		final ArrayList<Move> legalMoves = new ArrayList<>();
		
		for (final int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES){
			int possibleDestinationCoordinate = this.piecePosition;
			
			while (BoardUtils.isValidTileCoordinate(possibleDestinationCoordinate)){
				if (isFirstColumnExclusion(possibleDestinationCoordinate, candidateCoordinateOffset) ||
						isEighthColumnExclusion(possibleDestinationCoordinate, candidateCoordinateOffset)){
					break;
				}
				possibleDestinationCoordinate += candidateCoordinateOffset;
				if (BoardUtils.isValidTileCoordinate(possibleDestinationCoordinate)){
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
						break;
					}
				}
			}
		}			
		return ImmutableList.copyOf(legalMoves);
	}

	@Override
	public String toString(){
		return PieceType.QUEEN.toString();
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1 || candidateOffset == -9 || candidateOffset == 7);	
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == 1 || candidateOffset == -7 || candidateOffset == 9);
	}
}
