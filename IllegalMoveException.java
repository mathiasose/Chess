package chess;

public class IllegalMoveException extends Exception {
	public IllegalMoveException(String from, String to){
		super(from + "->" + to + " is an invalid move.");
	}
}
