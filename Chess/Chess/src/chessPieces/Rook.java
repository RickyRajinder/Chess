/**
 * 
 */
package chessPieces;

import java.util.ArrayList;
import java.util.Collection;

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
public class Rook extends Piece {
	
	private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8,-1,1,8};


	/**
	 * @param piecePosition
	 * @param pieceAlliance
	 */
    public Rook(final Alliance pieceAlliance, final int piecePosition) {

    	super(pieceAlliance, PieceType.ROOK, piecePosition);
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
		return PieceType.ROOK.toString();
	}
	
	private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);	
	}
	
	private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
		return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == 1);
	}

}
