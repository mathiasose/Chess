package chess;

public class Board {
	private Piece[][] board;
	public static final Piece[][] startBoard =
		{	{ new Rook(PieceColor.BLACK), new Knight(PieceColor.BLACK), new Bishop(PieceColor.BLACK), new King(PieceColor.BLACK), new Queen(PieceColor.BLACK), new Bishop(PieceColor.BLACK), new Knight(PieceColor.BLACK), new Rook(PieceColor.BLACK) },
		{ new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK), new Pawn(PieceColor.BLACK) },
		{ null, null, null, null, null, null, null, null },
		{ null, null, null, null, null, null, null, null },
		{ null, null, null, null, null, null, null, null },
		{ null, null, null, null, null, null, null, null },
		{ new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE), new Pawn(PieceColor.WHITE) },
		{ new Rook(PieceColor.WHITE), new Knight(PieceColor.WHITE), new Bishop(PieceColor.WHITE), new Queen(PieceColor.WHITE), new King(PieceColor.WHITE), new Bishop(PieceColor.WHITE), new Knight(PieceColor.WHITE), new Rook(PieceColor.WHITE) }
		};

	public Board(){
		board = new Piece[8][8];
		for ( int i = 0; i < 8; i++ ) {
			for ( int j = 0; j < 8; j++ ) {
				board[i][j] = startBoard[i][j];
			}
		}
	}

	static int index(String position, int i) throws IllegalPositionException {
		if ( position.length() != 2 )
			throw new IllegalPositionException(position);

		int r = (i == 1) ? position.charAt(0) - 'a' : 7 - (position.charAt(1) - '1');

		//		System.out.println(position + " => " + i + "->" + r);

		if ( r < 0 || r > 7 )
			throw new IllegalPositionException(position);
		else
			return r;
	}

	Piece getPiece(String position) throws IllegalPositionException {
		int x = index(position, 1);
		int y = index(position, 0);
		return ( isOccupied(position) ) ? board[y][x] : null;
	}

	void setPiece(String position, Piece piece) throws IllegalPositionException {
		board[index(position, 0)][index(position, 1)] = piece;
	}

	static boolean isStraight(String from, String to) throws IllegalPositionException{
		return ( ( index(from, 0) == index(to, 0) ) || ( index(from, 1) == index(to, 1) ) ); 
	}

	static boolean isDiagonal(String from, String to) throws IllegalPositionException{
		return ( Math.abs( index(from, 0) - index(to, 0) ) == Math.abs( index(from, 1) - index(to, 1) ) ); 
	}

	boolean isOccupiedBetween(String from, String to) throws IllegalPositionException{
		int fromX = index(from, 1);
		int fromY = index(from, 0);

		int toX = index(to, 1);
		int toY = index(to, 0);

		int diffX = toX - fromX;
		int diffY = toY - fromY;

		int stepX = (diffX == 0) ? 0 : diffX/Math.abs(diffX);
		int stepY = (diffY == 0) ? 0 : diffY/Math.abs(diffY);

		int x = fromX + stepX;
		int y = fromY + stepY;
		while ( ( isStraight(from, to) || isDiagonal(from, to) ) && !(x == toX && y == toY) ) {
			if ( isOccupied(x, y) ) return true;
			x += stepX;
			y += stepY;
		}
		return false;
	}

	boolean isOccupied(int x, int y) {
		return ( board[y][x] != null );
	}

	boolean isOccupied(String pos) throws IllegalPositionException {
		return isOccupied( index(pos, 1), index(pos, 0) );
	}

	boolean isLegalMove(PieceColor color, String from, String to) throws IllegalPositionException{
		if ( !isOccupied(from) || getPiece(from).getPieceColor() != color )
			return false;

		if ( !isOccupied(to) )
			return getPiece(from).canMove(from, to, this);

		if ( getPiece(from).getPieceColor() == getPiece(to).getPieceColor() )
			return false;

		return getPiece(from).canTake(from, to, this);
	}


	void movePiece(String from, String to) throws IllegalPositionException, IllegalMoveException {
		if ( isLegalMove(getPiece(from).getPieceColor(), from, to)){

			System.out.println(getPiece(from).getPieceColor() + " " + getPiece(from).getName() + " from " + from + " to " + to);

			board[index(to, 0)][index(to, 1)] = getPiece(from);
			board[index(from, 0)][index(from, 1)] = null;

			if ( board[index(to, 0)][index(to, 1)] instanceof Pawn )
				board[index(to, 0)][index(to, 1)] = new Queen( getPiece(to).getPieceColor() );
		} else throw new IllegalMoveException(from, to);
	}

	boolean isCheck(PieceColor color){
		// egentlig en sjakk matt sjekk
		for ( Piece[] row : board ) {
			for ( Piece col : row ) {
				if ( col instanceof King && col.getPieceColor() == color )
					return false;
			}
		}
		return true;
	}

	public String toString(){
		String r = "  a b c d e f g h\n";
		r += " ------------------\n";
		char y = '8';
		for ( Piece[] row : board ) {
			r += y + "|";
			for ( Piece col : row ) {
				r += ( col != null ) ? col.toChar() : ' ';
				r += " ";
			}
			r +=  "|" + y-- + "\n";
		}
		r += " ------------------\n";
		r += "  a b c d e f g h\n";
		return r;
	}
}
