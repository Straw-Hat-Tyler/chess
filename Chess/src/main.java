import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		//Initial choice to user to figure out start
		//pvp = player's take turns; match = output the turn by turn board picture; test (unlisted) = testing;
		System.out.println("Please enter what type of match this is ... \n"
							+ "Player vs Player: Enter - pvp\n"
							+ "Output replay: Enter - replay\n");
		
		
		Scanner scnr = new Scanner(System.in);
		String choice = scnr.next();
		
		while (true) {
			switch (choice.toLowerCase()) {
				case "pvp":
					//pvp.start();
					//System.exit(2);
					break;
				case "replay":
					Replay replay = new Replay();
					replay.rStart();
					System.exit(2);
					break;
				case "test":
					Match match = new Match();
					match.matchStart();
					System.exit(2);
					break;
				default:
					System.out.println("Sorry didn't get that can you try again?");
					choice = scnr.next();
					break;
			}
			
		}
		
		
	} 

}//end of class
