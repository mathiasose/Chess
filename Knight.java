package chess;

public class Knight extends AbstractPiece {

//	private PieceColor color;

	public Knight(PieceColor color){
		super(color);
	}

//	@Override
//	public PieceColor getPieceColor() {
//		return this.color;
//	}
//
//	@Override
//	public boolean canTake(String from, String to, Board board) {
//		return canMove(from, to, board);
//	}

	@Override
	public boolean canMove(String from, String to, Board board) throws IllegalPositionException {
		int fromX = Board.index(from, 1);
		int fromY = Board.index(from, 0);

		int toX = Board.index(to, 1);
		int toY = Board.index(to, 0);

		int absDiffX = Math.abs(toX - fromX);
		int absDiffY = Math.abs(toY - fromY);
		
		return ( ( absDiffX == 2 && absDiffY == 1 ) || ( absDiffX == 1 && absDiffY == 2 ) );
	}
	
	public char toChar(){
		return this.getPieceColor() == PieceColor.WHITE ? 'H' : 'h';
	}

}
