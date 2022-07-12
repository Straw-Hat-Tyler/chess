import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Replay {
	
	//to begin call rsStart to start
	public void rStart () {
		Scanner scan = new Scanner(System.in);
		Board set;
		
		try {
			System.out.println("First let me know if this is a full game (full pieces on both sides).\nyes or no");
			String choice = scan.nextLine();
			
			
			//if the board needs to have a certain number of pieces for each side a file has to be passed for the setup
			//the file is read in looking for the format (color),(piece name),(position*) <-without parenthesis
			//																* - in standard notation
			if (choice.trim().equals("no")) {
				System.out.println("Please check the readMe file to see the format for the file."
								+ "\nIf you're file is ready enter the full pathname now (if not enter quit):\n");
				
				File file = new File(scan.nextLine());
				
				if (!file.canRead()) {
					System.out.println("It looks like this file cannot be read.\nReturning...");
					return;
				}
				
				System.out.println("Found file - " + file.toString());
				System.out.println("How many pieces will be on the board?\n");
				
				int numPieces = scan.nextInt();
				scan.nextLine();
				
				if (numPieces < 1) {
					System.out.println("Sorry we need more at least one piece on the board."
										+ "\nReturning...");
					return;
				}
				
				set = replaySetUp(file);
			}
			
			//if we do not need a specific set up we use a full board
			else {
				set = new Board();
			}
			
			
			System.out.println("Now enter the full path name of the file containing the moves. "
							+ "\nRefer to the readMe.txt file to make sure that it matches the correct format."
							+ "\nIf you want to restart type quit.");
			
			File moveFile = new File(scan.nextLine());
			
			if (!moveFile.canRead()) {
				System.out.println("It looks like this file cannot be read.\nReturning...");
				return;
			}
			
			System.out.println("Found file - " + moveFile.toString());
			replayOutput(set, moveFile);
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.exit(1);
	}
	
	
	//to call the replaySetUp function make sure the file is in a specific format
	private Board replaySetUp(File file) {
	    Scanner scan;
	    
	    //This list will hold the pieces read from the file
	    List<Piece> pieces = new ArrayList();
	    
	    try {
	      scan = new Scanner(file);

	      while (scan.hasNextLine()) {
	    	  //the format of the expected file is color,piece,position (with position in standard notation)
	    	 String Piece = scan.nextLine();
	    	 String[] strArr = Piece.split(",");
	    	 
	    	 /* Output array to console to see what was captured
	    	 for (int i = 0; i < strArr.length; i++) {
	    		 System.out.println("This is spot " + i + " - " + strArr[i]);
	    	 }
	    	 */
	    	 
	    	 //strip is used on each piece to get rid of whitespace
	    	 strArr[0] = strArr[0].strip();
	    	 strArr[1] = strArr[1].strip();
	    	 strArr[2] = strArr[2].strip();
	    	 PositionPair pos = positionConvert(strArr[2]);
	    	 
	    	 pieces.add(new Piece (strArr[0] , strArr[1] , pos));
	      }
	      
	      return new Board (pieces);
	      
	    } 
	    
	    catch (FileNotFoundException e) {
	      e.printStackTrace();
	      
	      return null;
	    }
	  }
	
	//need to make sure that the file is in the correct format for this function
	private void replayOutput (Board b, File f) {
		Scanner scan;
		b.printBoard();
		
		//this try function will try to read the file and print the board after moving the selected piece
		//this does not take into account the legitimacy of each move but just tries to print the board 
		try {
		      scan = new Scanner(f);
		      int i = 1;
		      //iterating through the file 
		      while (scan.hasNextLine()) {
		    	  //the format of the expected file is color,piece,position (with position in standard notation)
		    	 String Piece = scan.nextLine();
		    	 String[] strArr = Piece.split("=");
		    	 
		    	 /* Output array to console to see what was captured
		    	 for (int i = 0; i < strArr.length; i++) {
		    		 System.out.println("This is spot " + i + " - " + strArr[i]);
		    	 }
		    	 */
		    	 
		    	 //strip is used on each piece to get rid of whitespace
		    	 strArr[0] = strArr[0].strip();
		    	 strArr[1] = strArr[1].strip();
		    	 PositionPair beginPos = positionConvert(strArr[0]);
		    	 PositionPair endPos = positionConvert(strArr[1]);
		    	 
		    	 b.movePiece(b.selectPiece(beginPos), b, endPos);
		    	 System.out.println("\nmove " + i);
		    	 b.printBoard();
		    	 i++;
		      }
		      
		     
		      
		    } 
		    
		    catch (FileNotFoundException e) {
		      e.printStackTrace();
		      
		    }
	}
	
	//this to return a Position Pair that makes sense to the 2d array from standard notation
	private PositionPair positionConvert (String str1) {
		//take in characters where horiz is given first as a character a - h
		//						   verti is given second as a number 1 - 8
		str1 = str1.strip();
		String[] position = str1.split("(?<=\\G.{1})");
		String horiz = position[0];
		String verti = position[1];
		
		//returning PositionPair
		PositionPair pp = new PositionPair();
		
		switch (horiz) {
			case "A":
				 pp.setHPos(0);
				break;
			case "B":
				pp.setHPos(1);
				break;
			case "C":
				pp.setHPos(2);
				break;
			case "D":
				pp.setHPos(3);
				break;
			case "E":
				 pp.setHPos(4);
				break;
			case "F":
				pp.setHPos(5);
				break;
			case "G":
				pp.setHPos(6);
				break;
			case "H":
				pp.setHPos(7);
				break;
			default:
				System.out.println("check input for horiz position");
			
		}
		
		switch (verti) {
		case "8":
			 pp.setVPos(0);
			break;
		case "7":
			pp.setVPos(1);
			break;
		case "6":
			pp.setVPos(2);
			break;
		case "5":
			pp.setVPos(3);
			break;
		case "4":
			 pp.setVPos(4);
			break;
		case "3":
			pp.setVPos(5);
			break;
		case "2":
			pp.setVPos(6);
			break;
		case "1":
			pp.setVPos(7);
			break;
		default:
			System.out.println("check input for verti position");
		
	}
		
		
		
		return pp;
	}
	
}//end of Replay class
