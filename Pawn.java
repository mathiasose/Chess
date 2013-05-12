package chess;

public class Pawn extends AbstractPiece {

	public Pawn(PieceColor color) {
		super(color);
	}

	@Override
	public boolean canTake(String from, String to, Board board) throws IllegalPositionException {
		int fromX = Board.index(from, 1);
		int fromY = Board.index(from, 0);

		int toX = Board.index(to, 1);
		int toY = Board.index(to, 0);

		int diffX = toX - fromX;
		int diffY = toY - fromY;

		if ( getPieceColor() == PieceColor.BLACK )
			return diffY == 1 && Math.abs(diffX) == 1;
		else
			return diffY == -1 && Math.abs(diffX) == 1;
	}

	@Override
	public boolean canMove(String from, String to, Board board) throws IllegalPositionException {
		int fromX = Board.index(from, 1);
		int fromY = Board.index(from, 0);

		int toX = Board.index(to, 1);
		int toY = Board.index(to, 0);

		int diffX = toX - fromX;
		int diffY = toY - fromY;

		boolean legalMove = false;
		if ( diffX == 0 ) {
			if ( getPieceColor() == PieceColor.BLACK ){
				if ( fromY == 1 && diffY == 2 )
					legalMove = true;
				else if ( diffY == 1)
					legalMove = true;
			} else if ( getPieceColor() == PieceColor.WHITE ) {
				if ( fromY == 6 && diffY == -2 )
					legalMove = true;
				else if ( diffY == -1)
					legalMove = true;
			}
		}

		return legalMove && !board.isOccupied(to) && super.canMove(from, to, board);

	}

}
