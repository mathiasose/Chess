package chess;

public class IllegalPositionException extends Exception {
		
	public IllegalPositionException(String position){
		super(position + " is an invalid position.");
	}

}
