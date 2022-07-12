import java.util.ArrayList;

public class Pawn extends Piece {
	ArrayList<PositionPair> viableMoves = new ArrayList();
	
	
	protected Pawn(String c, int v, int h) {
		super(c, "pawn", v, h);
	}
	
	//TODO add return piece for reaching end of board (if v value is 0 for blue or 7 for red remove and ask user what piece they want) <- needs to be done on board
	//TODO en Passant would need to be implemented maybe on baord since individual pawn pieces don't have a log of moves
	@Override
	public ArrayList<PositionPair> getMoves (Board board) {
		//since this may not be the first time we are calling this we need to make sure it is cleared
		viableMoves.clear(); 
		//get current position
		int currV = this.getVPos();
		int currH = this.getHPos();
		
		//Since pawns are the only directional pieces (only forward) we need a distinction between which color is moving
		//Other classes will not need this enclosing if statement because they are allowed to move forward and back creating the same moveset
		if (this.color == "red") {
			//if there is a piece diagonal from the pawn we can take that piece
			if(board.pieceAt(currV + 1, currH + 1) && 
					board.selectPiece(new PositionPair (currV + 1, currH + 1)).getColor() != this.color) {
				viableMoves.add(new PositionPair(currV + 1, currH + 1));
			}
			if(board.pieceAt(currV + 1, currH - 1) &&
					board.selectPiece(new PositionPair (currV + 1, currH - 1)).getColor() != this.color) {
				viableMoves.add(new PositionPair(currV + 1, currH - 1));
			}
			
			
			//if there is NOT a piece in front of the pawn it is allowed to move forward
			//Other pieces will only need to check the color of the piece in their class, other wise they take
			if (!board.pieceAt(currV + 1, currH)) {
				viableMoves.add(new PositionPair(currV + 1, currH));
				
				//piece has not moved yet allow a jump for 2 spaces
				if (currV == 1 && !board.pieceAt(currV + 2, currH)) {
					viableMoves.add(new PositionPair(currV + 2, currH));
				}
			}
			
			
			return viableMoves;
		}
		
		else {
			
			
			//if there is a piece diagonal from the pawn we can take that piece
			if(board.pieceAt(currV - 1, currH + 1) &&
					board.selectPiece(new PositionPair (currV - 1, currH + 1)).getColor() != this.color) {
				viableMoves.add(new PositionPair(currV - 1, currH + 1));
			}
			if(board.pieceAt(currV - 1, currH - 1) &&
					board.selectPiece(new PositionPair (currV - 1, currH - 1)).getColor() != this.color) {
				viableMoves.add(new PositionPair(currV - 1, currH - 1));
			}
			
			
			//if there is NOT a piece in front of the pawn it is allowed to move forward
			if (!board.pieceAt(currV - 1, currH)) {
				viableMoves.add(new PositionPair(currV - 1, currH));
				
				//piece has not moved yet allow a jump for 2 spaces
				if (currV == 6 && !board.pieceAt(currV - 2, currH)) {
					viableMoves.add(new PositionPair(currV - 2, currH));
				}
			}
			
		
			return viableMoves;
		}
	}
	
	
	
}//end of class
