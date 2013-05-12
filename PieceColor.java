
package chess;

public enum PieceColor {
	BLACK, WHITE;
	
	public PieceColor getOtherColor(){
		return this == BLACK ? WHITE : BLACK;
	}
}
