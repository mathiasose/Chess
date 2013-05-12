package chess;

public interface Piece {
	public PieceColor getPieceColor();
	public boolean canTake(String from, String to, Board board) throws IllegalPositionException;
	public boolean canMove(String from, String to, Board board) throws IllegalPositionException;
	public String getName();
	public char toChar();
}
