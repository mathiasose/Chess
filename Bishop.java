package chess;

public class Bishop extends AbstractPiece {

//	private PieceColor color;

	public Bishop(PieceColor color){
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
		return Board.isDiagonal(from, to) && super.canMove(from, to, board);
	}

}
