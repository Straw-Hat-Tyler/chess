import java.util.ArrayList;

public class Knight extends Piece {
	ArrayList<PositionPair> viableMoves = new ArrayList();
	
	
	protected Knight(String c, int v, int h) {
		super(c, "knight", v, h);
	}	

	@Override
	public ArrayList<PositionPair> getMoves (Board board) {
		//since this may not be the first time we are calling this we need to make sure it is cleared
		viableMoves.clear(); 
		//get current position
		int currV = this.getVPos();
		int currH = this.getHPos();
		
		
		//moveset is the same for both colors just need to check 
		
		//Below 8 are for taking pieces
		//2 spaces vertical set
		if (board.pieceAt(currV + 2, currH + 1) &&
				board.selectPiece(new PositionPair (currV + 2, currH + 1)).getColor() != this.color) {
			viableMoves.add(new PositionPair(currV + 2, currH + 1));
		}
		
		if (board.pieceAt(currV + 2, currH - 1) &&
				board.selectPiece(new PositionPair (currV + 2, currH - 1)).getColor() != this.color) {
			viableMoves.add(new PositionPair(currV + 2, currH - 1));
		}
		
		if (board.pieceAt(currV - 2, currH + 1) &&
				board.selectPiece(new PositionPair (currV - 2, currH + 1)).getColor() != this.color) {
			viableMoves.add(new PositionPair(currV - 2, currH + 1));
		}
		
		if (board.pieceAt(currV - 2, currH - 1) &&
				board.selectPiece(new PositionPair (currV - 2, currH - 1)).getColor() != this.color) {
			viableMoves.add(new PositionPair(currV - 2, currH - 1));
		}
		
		//2 spaces horizontal set
		if (board.pieceAt(currV + 1, currH + 2) &&
				board.selectPiece(new PositionPair (currV + 1, currH + 2)).getColor() != this.color) {
			viableMoves.add(new PositionPair(currV + 1, currH + 2));
		}
		
		if (board.pieceAt(currV - 1, currH + 2) &&
				board.selectPiece(new PositionPair (currV - 1, currH + 2)).getColor() != this.color) {
			viableMoves.add(new PositionPair(currV - 1, currH + 2));
		}
		
		if (board.pieceAt(currV + 1, currH - 2) &&
				board.selectPiece(new PositionPair (currV + 1, currH - 2)).getColor() != this.color) {
			viableMoves.add(new PositionPair(currV + 1, currH - 2));
		}
		
		if (board.pieceAt(currV - 1, currH - 2) &&
				board.selectPiece(new PositionPair (currV -1 , currH - 2)).getColor() != this.color) {
			viableMoves.add(new PositionPair(currV - 1, currH - 2));
		}
		
		//-----------------------------------Below 8 are for when there is not a piece at location
		//2 spaces vertical set
		if (!board.pieceAt(currV + 2, currH + 1)) {
			viableMoves.add(new PositionPair(currV + 2, currH + 1));
		}
		
		if (!board.pieceAt(currV + 2, currH - 1) ) {
			viableMoves.add(new PositionPair(currV + 2, currH - 1));
		}
		
		if (!board.pieceAt(currV - 2, currH + 1)) {
			viableMoves.add(new PositionPair(currV - 2, currH + 1));
		}
		
		if (!board.pieceAt(currV - 2, currH - 1)) {
			viableMoves.add(new PositionPair(currV - 2, currH - 1));
		}
		
		//2 spaces horizontal set
		if (!board.pieceAt(currV + 1, currH + 2)) {
			viableMoves.add(new PositionPair(currV + 1, currH + 2));
		}
		
		if (!board.pieceAt(currV - 1, currH + 2)) {
			viableMoves.add(new PositionPair(currV - 1, currH + 2));
		}
		
		if (!board.pieceAt(currV + 1, currH - 2)) {
			viableMoves.add(new PositionPair(currV + 1, currH - 2));
		}
		
		if (!board.pieceAt(currV - 1, currH - 2)) {
			viableMoves.add(new PositionPair(currV - 1, currH - 2));
		}

		return viableMoves;
	}
	
	
}//end of knight class
