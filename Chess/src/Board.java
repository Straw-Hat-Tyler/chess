import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.*;

public class Board {
	Piece[][] board;
	List<Piece> activePieces = new ArrayList<Piece>();
	
	
	//colors for console output
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[36m";	//this is actually CYAN because it is easier to read than the darker blue ([34m)
	public static final String ANSI_RED = "\u001B[31m";
	
	public Board (List<Piece> pieces) {
		activePieces = pieces;
		board = new Piece[8][8];
	
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				board[i][j] = new Piece();
			}
		}

		setBoard();
	}
	
	public Board () {
		board = new Piece[8][8];
		
		//fill slots with inactive piece objects so no null values
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				board[i][j] = new Piece();
			}
		}
		
		//now creating a standard chess game
		//instantiate 8 pawns for blue and red
		//for red
		for (int i = 0; i < 8; i++) {
			activePieces.add(new Pawn ("red", 1, i));				
		}
		
		//for blue
		for (int i = 0; i < 8; i++) {
			activePieces.add(new Pawn ("blu", 6, i));						
		}
		
		//add 2 knights for each side
		//for red
		activePieces.add(new Knight("red", 0, 1));
		activePieces.add(new Knight("red", 0, 6));
		//for blue
		activePieces.add(new Knight("blu", 7, 1));
		activePieces.add(new Knight("blu", 7, 6));
		
		//add 2 rooks for each side
		//for red
		activePieces.add(new Rook("red", 0, 0));
		activePieces.add(new Rook("red", 0, 7));
		//for blue
		activePieces.add(new Rook("blu", 7, 0));
		activePieces.add(new Rook("blu", 7, 7));
		
		//add 2 rooks for each side
		//for red
		activePieces.add(new Bishop("red", 0, 2));
		activePieces.add(new Bishop("red", 0, 5));
		//for blue
		activePieces.add(new Bishop("blu", 7, 1));
		activePieces.add(new Bishop("blu", 7, 6));
		
		//add 1 queen for each side
		//for red
		activePieces.add(new Queen("red", 0, 3));
		//for blue
		activePieces.add(new Queen("blu", 7, 3));
		
		//add 1 queen for each side
		//for red
		activePieces.add(new King("red", 0, 4));
		//for blue
		activePieces.add(new King("blu", 7, 4));
		
		setBoard();
	}
	
	//sets pieces on board
	public boolean setBoard () {
		//if list of active pieces is empty then there is nothing to do
		if (activePieces.isEmpty()) {
			System.out.println("No active piecees on the board.");
			return false;
		}
		
		Iterator it = activePieces.iterator();
		
		//initialize board by iterating through list of active pieces
		while (it.hasNext()) {
			Piece curr = (Piece) it.next();
			this.board[curr.getVPos()][curr.getHPos()] = curr;
			
		}
		
		return true;
	}
	
	//given a coordinate, check board and check if piece is active
	public boolean pieceAt(int v, int h) {
		try {
			//if there is no active piece on the position return false
			if (this.board[v][h].active) {
				return true;
			}
		}
		catch (Exception e) {
			return false;
		}
		return false;
	}
	
	//TODO add check, checkmate here...mmake help method that returns list of opponents pieces (space vs performance this prefers space since we will only need 1 list)
	//moving a piece
	public boolean movePiece (Piece piece, Board b, PositionPair pp) {
		//the passed piece is not a part of active list we return right away
		if (!b.activePieces.contains(piece)) {
			System.out.println("That piece is not active.");
			return false;
		}
		
		ArrayList<PositionPair> viable = piece.getMoves(b);
		
		//if the moving piece is the king we need to remove options that would put the king in check
		if (piece.getClass() == King.class) {
			List<Piece> attackers = listOpposer(activePieces , piece.getColor());
			Iterator it = attackers.iterator();
			
			while (it.hasNext()) {
				Piece curr = (Piece) it.next();
				List<PositionPair> currMoves = curr.getMoves(b);
				
				//Special check for pawns since they don't show diagonal moves as viable unless a piece is there
				if (curr.getClass() == Pawn.class) {
					if (curr.getColor() == "red") {
						currMoves.add(new PositionPair(curr.getVPos()+1 , curr.getHPos()+1));
						currMoves.add(new PositionPair(curr.getVPos()+1 , curr.getHPos()-1));
					}
					else {
						currMoves.add(new PositionPair(curr.getVPos()-1 , curr.getHPos()+1));
						currMoves.add(new PositionPair(curr.getVPos()-1 , curr.getHPos()-1));
					}
				}
				
				Iterator cIt = currMoves.iterator();
				
				while (cIt.hasNext()) {
					Iterator vIt = viable.iterator();
					PositionPair danger = (PositionPair) cIt.next();
					PositionPair dangerMove = null;
					
					while (vIt.hasNext()) {
						PositionPair viableMove = (PositionPair) vIt.next();
						
						if (danger.getVPos() == viableMove.getVPos() && danger.getHPos() == viableMove.getHPos()) {
							dangerMove = viableMove;
						}
					}
					
					if (dangerMove != null) {
						viable.remove(dangerMove);
					}
				}
			}
		}//end of special king move list
		
		//if no legal moves return false
		if (viable.isEmpty()) {
			System.out.println("Cannot move " + piece.getName() + " because it has no legal moves.");
			return false;
		}
		
		//if desired position is legal (inside of viable list) check if we are taking or just moving
		else {
			Iterator it = viable.iterator();
			while(it.hasNext()) {
				PositionPair look = (PositionPair) it.next();
				
				if (look.getVPos() == pp.getVPos() && look.getHPos() == pp.getHPos()) {
					if (b.selectPiece(pp).active) {
						System.out.println(piece.name + " takes " + this.selectPiece(pp).name);
						this.removePiece(pp.getVPos(), pp.getHPos());
						this.removePiece(piece.getVPos(), piece.getHPos());
						piece.setPos(pp);
						this.board[pp.getVPos()][pp.getHPos()] = piece;
						this.activePieces.add(piece);
						return true;
					}
					else {
						this.removePiece(piece.getVPos(), piece.getHPos());
						piece.setPos(pp);
						this.board[pp.getVPos()][pp.getHPos()] = piece;
						this.activePieces.add(piece);
						return true;
					}
					
				}//end of viability check
				
			}//end of while loop iterator
			
			System.out.println("Sorry moving to " + pp.getVPos() + ", " + pp.getHPos() + " is not a viable move");
		}//end of else statement
		
		return true;
	}
	
	//Using a list of the opposing team, we check if their viable moves encompass the kings position
	private boolean checkCheck (King atRisk , List<Piece> attackers) {
		Iterator it = attackers.iterator();
		List<PositionPair> underAttack = new ArrayList<PositionPair>();;
		
		while (it.hasNext()) {
			Piece curr = (Piece) it.next();
			
			underAttack.addAll(curr.getMoves(this));
		}
		
		Iterator ua = underAttack.iterator();
		
		while (ua.hasNext()) {
			PositionPair curr = (PositionPair) ua.next();
			
			if (curr.getVPos() == atRisk.getVPos() && curr.getHPos() == atRisk.getHPos()) {
				System.out.println("The king is in check!");
				return true;
			}
		}
		return false;
	}
	
	//using a list of the opposing pieces we'll check if the King is in check
	//if not we return 0 = not in check/mate, 
	//if we are in check but able to move (king's viable moves are not covered by attackers) return 1 = check
	//if the king is in check and can not move then we return 2 = mate
	public int checkMate (King atRisk , List<Piece> attackers) {
		if (!checkCheck(atRisk , attackers)) {
			return 0;
		}
		
		List<PositionPair> kingMoves = atRisk.getMoves(this);
		
		//if kingMoves is empty and we've reached this far we are in checkmate
		if (kingMoves.isEmpty()) {
			System.out.println("Checkmate!");
			return 2;
		}
		
		System.out.println("Move King from " + atRisk.getVPos() + ", " + atRisk.getHPos() + ".");
		return 1;
	}
	
	//Creates a list of only the opposing teams pieces based off of a color given
 	public List<Piece> listOpposer (List<Piece> active , String myColor) {
		List<Piece> opposers = new ArrayList<Piece>();
		
		Iterator it = activePieces.iterator();
		
		while (it.hasNext()) {
			Piece curr = (Piece) it.next();
			
			if (curr.getColor() != myColor) {
				opposers.add(curr);
			}
		}
		
		return opposers;
	}
	
 	//return piece object at a location (position pair object)
	public Piece selectPiece (PositionPair pp) {
			return this.board[pp.getVPos()][pp.getHPos()];
	}
	
	//given a coordinate, remove piece from active list and set board position active to false
	public void removePiece (int v, int h) {
		Piece temp = this.selectPiece(new PositionPair (v, h));
		
		//remove the piece from the list of active pieces and the board
		this.activePieces.remove(temp);
		this.board[v][h] = new Piece();
	}
	
	//iterate through array to print board and pieces
	public void printBoard() {
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				//check position to see if active
				boolean inUse = this.board[i][j].active;
				
				//if active the position has a piece
				if (inUse) {
					Piece found = this.board[i][j];
					
					switch (found.name) {
						case "pawn":
							//set color specific to piece
							if (found.color == "blu") {
								System.out.print(ANSI_BLUE + "[P]" + ANSI_RESET);
								break;
							}
							System.out.print(ANSI_RED + "[P]" + ANSI_RESET); 
							break;
						case "knight":
							//Going with n for knight since K needs to be for king
							//set color specific to piece
							if (found.color == "blu") {
								System.out.print(ANSI_BLUE + "[n]" + ANSI_RESET);
								break;
							}
							System.out.print(ANSI_RED + "[n]" + ANSI_RESET); 
							break;
						case "rook":
							//set color specific to piece
							if (found.color == "blu") {
								System.out.print(ANSI_BLUE + "[R]" + ANSI_RESET);
								break;
							}
							System.out.print(ANSI_RED + "[R]" + ANSI_RESET); 
							break;
						case "bishop":
							//set color specific to piece
							if (found.color == "blu") {
								System.out.print(ANSI_BLUE + "[B]" + ANSI_RESET);
								break;
							}
							System.out.print(ANSI_RED + "[B]" + ANSI_RESET); 
							break;
						case "queen":
							//set color specific to piece
							if (found.color == "blu") {
								System.out.print(ANSI_BLUE + "[Q]" + ANSI_RESET);
								break;
							}
							System.out.print(ANSI_RED + "[Q]" + ANSI_RESET); 
							break;
						case "king":
							//set color specific to piece
							if (found.color == "blu") {
								System.out.print(ANSI_BLUE + "[K]" + ANSI_RESET);
								break;
							}
							System.out.print(ANSI_RED + "[K]" + ANSI_RESET); 
							break;
						default: System.out.print("[ ]"); break;
					}
						
				}//found piece
				
				//if no piece is found
				else {
					System.out.print("[ ]");
					
				}//no piece found
			}//inner for
			
			//Move to next print line
			System.out.println();
		}//outer for
		
		
	}//end of printboard
	
	
}//end of class
