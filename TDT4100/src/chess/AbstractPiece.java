package chess;

public abstract class AbstractPiece implements Piece {

	private PieceColor color;

	public AbstractPiece(PieceColor color){
		this.color = color;
	}

	@Override
	public PieceColor getPieceColor() {
		return this.color;
	}

	@Override
	public boolean canTake(String from, String to, Board board) throws IllegalPositionException {
		return canMove(from, to, board); // kan den bevege seg dit, kan den ta den (?)
	}

	@Override
	public boolean canMove(String from, String to, Board board) throws IllegalPositionException {
		return !board.isOccupiedBetween(from, to); // inneholder allerede en sjekk om diagonalt eller rett
	}
	
	public String getName(){
		return this.getClass().toString().substring("class chess.".length());
	}
	
	public char toChar(){
		char r = getName().charAt(0); 
		if ( this.getPieceColor() == PieceColor.BLACK )
			r = Character.toLowerCase(r);
		return r;
	}
	
}
