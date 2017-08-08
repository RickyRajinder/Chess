package chessEngine;

import chessBoard.Board;

/**
 * Created by Ricky Rajinder on 6/18/2017.
 */
public class JChess {
    public static void main(String[] args){
        Board board = Board.createStandardBoard();
        System.out.println(board);
    }
}
