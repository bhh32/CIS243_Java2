
// CS 143, Homework 3: Robin Hood
// Instructor-provided client program. Intentionally under-commented.

import java.io.*;
import java.util.*;

public class RobinHood {

	public static void main(String[] args) throws FileNotFoundException {
		// prompt for file name
		Scanner console = new Scanner(System.in);
		System.out.println("Welcome to Sherwood Forest!");
		System.out.println();
		System.out.print("What player file do you want to use? ");
		String fileName = console.nextLine();

		// read names into a list, using a Set to avoid duplicates
		Scanner input = new Scanner(new File(fileName));
		Set<String> names = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		List<String> names2 = new ArrayList<String>();
		while (input.hasNextLine()) {
			String name = input.nextLine().trim();
			if (name.length() > 0 && !names.contains(name)) {
				names.add(name);
				names2.add(name);
			}
		}

		// Shuffle if desired
		if (yesTo(console, "Do you want the players shuffled?")) {
			Collections.shuffle(names2);
		}
		// Make an immutable version and use it to build a GameManager
		List<String> names3 = Collections.unmodifiableList(names2);
		GameManager manager = new GameManager(names3);
		System.out.println();

		// Prompt the user for victims until the game is over
		while (!manager.isGameOver())
			oneRound(console, manager);

		// Report who won
		System.out.println(manager.winner()+" is Robin Hood!");
		System.out.println("Final stolen list is as follows:");
		manager.printStolenList();
	}

	// Handles the details of recording one victim. Shows the current kill
	// ring and graveyard to the user, prompts for a name and records the
	// kill if the name is legal.
	public static void oneRound(Scanner console, GameManager manager) {
		System.out.println("Current thief ring:");
		manager.printThiefRing();
		System.out.println("Current stolen list:");
		manager.printStolenList();
		System.out.println();
		System.out.print("Who's next? ");
		String name = console.nextLine().trim();
		if (manager.stolenListContains(name)) {
			System.out.println(name + " was already stolen from.");
		} else if (!manager.thiefRingContains(name)) {
			System.out.println("Unknown person.");
		} else {
			manager.steal(name);
		}
		System.out.println();
	}

	// post: asks the user a question, forcing an answer of "y" or "n";
	// returns true if the answer was yes, returns false otherwise
	public static boolean yesTo(Scanner console, String prompt) {
		System.out.print(prompt + " (y/n)? ");
		String response = console.nextLine().trim().toLowerCase();
		while (!response.equals("y") && !response.equals("n")) {
			System.out.println("Please answer y or n.");
			System.out.print(prompt + " (y/n)? ");
			response = console.nextLine().trim().toLowerCase();
		}
		return response.equals("y");
	}
}
