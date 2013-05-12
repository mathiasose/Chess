package chess;

public class King extends AbstractPiece {

//	private PieceColor color;

	public King(PieceColor color){
		super(color);
	}

//	@Override
//	public PieceColor getPieceColor() {
//		return this.color;
//	}
//
//	@Override
//	public boolean canTake(String from, String to, Board board) {
//		return canMove(from, to, board); // kan den bevege seg dit, kan den ta den (?)
//	}

	@Override
	public boolean canMove(String from, String to, Board board) throws IllegalPositionException {
		int stepX = Math.abs(Board.index(to, 1) - Board.index(from, 1));
		int stepY = Math.abs(Board.index(to, 0) - Board.index(from, 0));
		boolean stepIsShort = (stepX  <= 1) && (stepY  <= 1);
		return stepIsShort && super.canMove(from, to, board); // inneholder allerede en sjekk om diagonalt eller rett
	}

}
