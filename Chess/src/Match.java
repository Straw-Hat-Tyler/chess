import java.util.ArrayList;
import java.util.List;

public class Match {
	
	public void matchStart () {
		//empty board
		Board eBoard = new Board();
		List<Piece> pieces = new ArrayList();
		
		//Blue pieces
		//Pawn blueP1 = new Pawn("blu", 6, 0);
		//pieces.add(blueP1);
		pieces.add(new Pawn("blu", 6, 0));
		pieces.add(new Pawn("blu", 6, 1));
		pieces.add(new Pawn("blu", 6, 2));
		pieces.add(new Pawn("blu", 6, 3));
		pieces.add(new Pawn("blu", 6, 4));
		pieces.add(new Pawn("blu", 6, 5));
		pieces.add(new Pawn("blu", 6, 6));
		pieces.add(new Pawn("blu", 6, 7));
		
		//Red pieces
		pieces.add(new Pawn("red", 1, 0));
		pieces.add(new Pawn("red", 1, 1));
		pieces.add(new Pawn("red", 1, 2));
		pieces.add(new Pawn("red", 1, 3));
		pieces.add(new Pawn("red", 1, 4));
		pieces.add(new Pawn("red", 1, 5));
		pieces.add(new Pawn("red", 1, 6));
		pieces.add(new Pawn("red", 1, 7));
		
		pieces.add(new King("blu", 2, 3));
		
		//active board
		Board aBoard = new Board(pieces);
		
		System.out.println("Welcome to a game of Chess!");
		
		System.out.println("-----------------\nTEST: Auto instantiated board output\n");
		eBoard.printBoard();
		
		System.out.println("-----------------\nTEST: active board output\n");
		aBoard.printBoard();
		
		System.out.println("-----------------\nTEST: King movement");
		//System.out.println("Is King active - " + );
		//aBoard.movePiece(aBoard.selectPiece(new PositionPair(3, 3)), aBoard, new PositionPair(2, 3));
		aBoard.printBoard();
		System.out.println(aBoard.checkMate((King) aBoard.selectPiece(new PositionPair(2, 3)), aBoard.listOpposer(aBoard.activePieces, "blu")));
		
		
		//int roundCount = 1;
		//System.out.println("-----------------\nTEST: while loop of rounds\n");
		
		//TODO add check for king in check or checkmate
		/*
		while (roundCount < 6) {
			System.out.println("Round: " + roundCount);
			System.out.println("Blue's turn\n");
			
			System.out.println("The selected piece is - " + blueP1.toString() + " at - " + blueP1.getVPos() + blueP1.getHPos() + " and is active? " + blueP1.activePiece());
			if (roundCount == 5) {
				System.out.println("Taking red pawn at 1,1.");
				System.out.println("Current pawn at 1,1 is " + aBoard.selectPiece(new PositionPair(1,1)));
				System.out.println(aBoard.movePiece(aBoard.selectPiece(blueP1.pos), aBoard, new PositionPair(1, 1)));
				System.out.println("Current pawn at 1,1 is " + aBoard.selectPiece(new PositionPair(1,1)));
				aBoard.printBoard();
				break;
			}
			aBoard.movePiece(aBoard.selectPiece(blueP1.pos), aBoard, new PositionPair(blueP1.getVPos() - 1, blueP1.getHPos()));
			System.out.println("The selected piece is - " + blueP1.toString() + " at - " + blueP1.getVPos() + blueP1.getHPos() + " and is active? " + blueP1.activePiece());
			aBoard.printBoard();
			roundCount++;
		}
		*/
	}
}
