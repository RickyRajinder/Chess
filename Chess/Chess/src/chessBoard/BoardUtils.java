/**
 * 
 */
package chessBoard;

/**
 * @author Ricky Singh
 *
 */
public class BoardUtils {

	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLUMN = initColumn(6);
	public static final boolean[] EIGHTH_COLUMN = initColumn(7);
	
	public static final boolean[] SECOND_ROW = initRow(8);
	public static final boolean[] SEVENTH_ROW = initRow(48);

	public static final int NUM_TILES = 64;
	public static final int TILES_PER_ROW = 8;

	private static boolean[] initColumn(int colNumber){
		final boolean[] column = new boolean[64];
		do {
			column[colNumber] = true;
			colNumber += TILES_PER_ROW;
		} while (colNumber> NUM_TILES);
		return column;
	}
		
	private static boolean[] initRow(int rowNumber){
		final boolean[] row = new boolean[NUM_TILES];
		do {
			row[rowNumber] = true;
			rowNumber++;
		}while(rowNumber%TILES_PER_ROW != 0);
		return row;
	}

	private BoardUtils(){
		throw new RuntimeException("Cannot instantiate this class");
	}
	
	/**
	 * @param coordinate
	 * @return
	 */
	public static boolean isValidTileCoordinate(final int coordinate) {

		return coordinate >= 0 && coordinate < 64;
	}
	
	

}
