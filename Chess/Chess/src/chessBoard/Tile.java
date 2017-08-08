package chessBoard;

import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

import chessPieces.Piece;

/**
 * Models a tile on a chess board
 * @author Ricky Singh
 *
 */
public abstract class Tile {

	protected final int tileCoordinate;
	
	private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles(); 
	
	private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		for (int i = 0; i < BoardUtils.NUM_TILES; i++){
			emptyTileMap.put(i, new EmptyTile(i));
		}
		return ImmutableMap.copyOf(emptyTileMap);
		// or you can do the following:
		// return Collections.unmodifiableMap(emptyTileMap);
	}
	
	public static Tile createTile(final int tileCoordinate, final Piece piece){
		return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
	}
	
	private Tile (final int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;	
		}
	
	public abstract boolean isTileOccupied();
	
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile{
		private EmptyTile(final int tileCoordinate) {
			super(tileCoordinate);
		}

		@Override
		public String toString(){
			return "-";
		}
		
		@Override
		public boolean isTileOccupied(){
			return false;
	}
		@Override
		public Piece getPiece(){
			return null;
		}
}
	
	public static final class OccupiedTile extends Tile {
		private final Piece pieceOnTile;
		private OccupiedTile(final int tileCoordinate, final Piece pieceOnTile){
			super(tileCoordinate);
			this.pieceOnTile = pieceOnTile;
		}

		@Override
		public String toString(){
			return getPiece().getAlliance().isBlack() ? getPiece().toString().toLowerCase() :
					getPiece().toString();
		}
		@Override
		public boolean isTileOccupied() {
			return true;
		}
		@Override
		public Piece getPiece() {
			return pieceOnTile;
		}
		
		
	}
}
