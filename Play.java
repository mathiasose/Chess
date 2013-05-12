package chess;

import java.util.Scanner;

public class Play {
	public static void main(String[] args) {
		Board board = new Board();

		Scanner in = new Scanner(System.in);
		PieceColor nowPlaying = PieceColor.WHITE;

		while( true ){
			System.out.println(board);
			if ( board.isCheck(nowPlaying) ) {
				System.out.println(nowPlaying.getOtherColor() + " wins!");
				break;
			}
			System.out.println(nowPlaying + "'s turn:");
			String move = in.nextLine();

			try {
				if ( move.length() == 5 && move.charAt(2) == '-' ) {
					String from = move.substring(0, 2);
					String to = move.substring(3, 5);
					board.movePiece(from, to);
					nowPlaying = nowPlaying.getOtherColor();
				} else
					throw new IllegalPositionException(move);
			}
			catch ( IllegalPositionException | IllegalMoveException e ){
				System.out.println(e);
			}
		}

		in.close();
	}
}
