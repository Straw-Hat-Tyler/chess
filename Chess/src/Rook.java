import java.util.ArrayList;

public class Rook extends Piece {
	ArrayList<PositionPair> viableMoves = new ArrayList();
	
	
	protected Rook(String c, int v, int h) {
		super(c, "rook", v, h);
	}	

	@Override
	public ArrayList<PositionPair> getMoves (Board board) {
		//since this may not be the first time we are calling this we need to make sure it is cleared
		viableMoves.clear(); 
		//get current position
		int currV = this.getVPos();
		int currH = this.getHPos();
		int counter;
		
		//down movement
		for (counter = 1; (currV + counter) < 8; counter++) {
			//if open space
			if (!board.pieceAt(currV + counter, currH)) {
				viableMoves.add(new PositionPair (currV + counter, currH));
			}
			
			//if space occupied
			if (board.pieceAt(currV + counter, currH)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV + counter, currH)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV + counter, currH));
					break;
				}
				else {
					break;
				}
			
			}
		}
		
		//right movement
		for (counter = 1; (currH + counter) < 8; counter++) {
			//if open space
			if (!board.pieceAt(currV, currH + counter)) {
				viableMoves.add(new PositionPair (currV, currH + counter));
			}
			
			//if space occupied
			if (board.pieceAt(currV, currH + counter)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV, currH + counter)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV, currH + counter));
					break;
				}
				else {
					break;
				}
				
			}
		}	
		
		//up movement
		for (counter = 1; (currV - counter) > -1; counter++) {
			//if open space
			if (!board.pieceAt(currV - counter, currH)) {
				viableMoves.add(new PositionPair (currV - counter, currH));
			}
			
			//if space is occupied
			if (board.pieceAt(currV - counter, currH)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV - counter, currH)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV - counter, currH));
					break;
				}
				else {
					break;
				}
			
			}
		}			
		
		//left movement
		for (counter = 1; (currH - counter) > -1; counter++) {
			//if open space
			if (!board.pieceAt(currV, currH - counter)) {
				viableMoves.add(new PositionPair (currV, currH - counter));
			}
			
			//if space occupied
			if (board.pieceAt(currV, currH - counter)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV, currH - counter)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV, currH - counter));
					break;
				}
				else {
					break;
				}
				
			}
		}			
		
		return viableMoves;
	}//end of getMoves
	
}//end of rook class
