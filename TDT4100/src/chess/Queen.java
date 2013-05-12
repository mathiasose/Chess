package chess;

public class Queen extends AbstractPiece {

//	private PieceColor color;

	public Queen(PieceColor color){
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
//
//	@Override
//	public boolean canMove(String from, String to, Board board) {
//		return !board.isOccupiedBetween(from, to); // inneholder allerede en sjekk om diagonalt eller rett
//	}

}
