import java.util.ArrayList;

public class King extends Piece {
	ArrayList<PositionPair> viableMoves = new ArrayList();
	
	
	protected King(String c, int v, int h) {
		super(c, "king", v, h);
	}	

	//TODO should check, checkmate be here? Viable move list must be compared to other viable move lists
	@Override
	public ArrayList<PositionPair> getMoves (Board board) {
		//since this may not be the first time we are calling this we need to make sure it is cleared
		viableMoves.clear(); 
		
		//get current position
		int currV = this.getVPos();
		int currH = this.getHPos();
		
		//Similar movement to queen but no longer needs a for loop
		
		//down movement
		if (currV + 1 < 8) {
			//if space occupied
			if (board.pieceAt(currV + 1, currH)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV + 1, currH)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV + 1, currH));
				}
			}
			//else space is empty
			else {
				viableMoves.add(new PositionPair (currV + 1, currH));
			}
		}
		
		//down right movement
		if (currV + 1 < 8 && currH + 1 < 8) {
			//if space occupied
			if (board.pieceAt(currV + 1, currH + 1)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV + 1, currH + 1)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV + 1, currH + 1));
				}
			}
			//else space is empty
			else {
				viableMoves.add(new PositionPair (currV + 1, currH + 1));
			}
		}
		
		//right movement
		if (currH + 1 < 8) {
			//if space occupied
			if (board.pieceAt(currV, currH + 1)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV, currH + 1)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV, currH + 1));
				}
			}
			//else space is empty
			else {
				viableMoves.add(new PositionPair (currV, currH + 1));
			}
		}
		
		//up right movement
		if (currV - 1 > -1 && currH + 1 < 8) {
			//if space occupied
			if (board.pieceAt(currV - 1, currH + 1)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV - 1, currH + 1)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV - 1, currH + 1));
				}
			}
			//else space is empty
			else {
				viableMoves.add(new PositionPair (currV - 1, currH + 1));
			}
		}
		
		//up movement
		if (currV - 1 > -1) {
			//if space occupied
			if (board.pieceAt(currV - 1, currH)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV - 1, currH)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV - 1, currH));
				}
			}
			//else space is empty
			else {
				viableMoves.add(new PositionPair (currV - 1, currH));
			}
		}
		
		//up left movement
		if (currV - 1 > -1 && currH - 1 > -1) {
			//if space occupied
			if (board.pieceAt(currV - 1, currH - 1)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV - 1, currH - 1)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV - 1, currH - 1));
				}
			}
			//else space is empty
			else {
				viableMoves.add(new PositionPair (currV - 1, currH - 1));
			}
		}
		
		//left movement
		if (currH - 1 > -1) {
			//if space occupied
			if (board.pieceAt(currV, currH - 1)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV, currH - 1)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV, currH - 1));
				}
			}
			//else space is empty
			else {
				viableMoves.add(new PositionPair (currV, currH - 1));
			}
		}
		
		//down left movement
		if (currV + 1 < 8 && currH - 1 > -1) {
			//if space occupied
			if (board.pieceAt(currV + 1, currH - 1)) {
				//add move to list to take piece
				if (board.selectPiece(new PositionPair (currV + 1, currH - 1)).getColor() != this.color) {
					viableMoves.add(new PositionPair (currV + 1, currH - 1));
				}
			}
			//else space is empty
			else {
				viableMoves.add(new PositionPair (currV + 1, currH - 1));
			}
		}
		
		return viableMoves;
	}//end of getMoves

}//end of King class
