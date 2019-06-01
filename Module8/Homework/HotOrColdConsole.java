// CS 143 Homework 8: Hot or Cold?
//
// To use the jGRASP debugger with this program, set a breakpoint
// and once the execution breaks, open 'this' or 'tq' on the left,
// then look at its variable 'tree'.  That's your InfoTree.
// Drag your 'tree' over to the right to see a visualization of it.
// 
// (Your InfoTree is constructed by this file in load(), lines 95 or 98.
// The overall loop to play games is in run(), starting at line 70.)

import java.io.*;
import java.util.Scanner;

/** A basic text user interface for the Hot or Cold? game. */
public class HotOrColdConsole implements UserInteraction {
	
	private static final String HOT_LETTER = "h";
	private static final String YES_LETTER = "y";
	
    public static void main(String[] args) {
        HotOrColdConsole tq = new HotOrColdConsole();
        tq.run();
    }
    
    
    // fields
    private Scanner console;
    private InfoTree tree;
    
    /** Constructs a text user interface and its info tree. */
    public HotOrColdConsole() {
        console = new Scanner(System.in);
    }
    
    /**
     * Returns the user's response as a String.
     */
    public String nextLine() {
        return console.nextLine();
    }

    /** Prints the given string to the console. */
    public void print(String message) {
        System.out.print(message);
        System.out.print(" ");
    }

    /** Prints the given string to the console. */
    public void println(String message) {
        System.out.println(message);
    }

    /** Prints a blank line to the console. */
    public void println() {
        System.out.println();
    }

    /**
     * Waits for the user to answer a yes/no question on the console and returns the
     * user's response as a boolean (true for anything that starts with "y" or "Y").
     */
    public boolean nextBoolean() {
		System.out.print("Hot or Cold? ");
        String answer = console.nextLine();
        return answer.trim().toLowerCase().startsWith(HOT_LETTER);
    }

    public boolean nextYes() {
        String answer = console.nextLine();
        return answer.trim().toLowerCase().startsWith(YES_LETTER);
    }
    
    // private helper for overall game(s) loop
    private void run() {
        println("Welcome to Hot or Cold!");
        load();
        
        // "Think of an item, and I will guess it."
        println("\n" + BANNER_MESSAGE);
            
        do {
            // play one complete game
            println();      // blank line between games
            tree.playGame(this);
            print(PLAY_AGAIN_MESSAGE);
        } while (nextYes());   // prompt to play again
        
        save();
    }
    
    // common code for asking the user whether they want to save or load
    private void load() {
        print(LOAD_MESSAGE);
        if (nextYes()) {
            print(SAVE_LOAD_FILENAME_MESSAGE);
            String filename = nextLine();
            try {
                Scanner in = new Scanner(new File(filename));
                tree = new InfoTree(in);
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
				    tree = new InfoTree("computer");
            }
        } else {
				tree = new InfoTree("computer");
        }
    }
    
    // common code for asking the user whether they want to save or load
    private void save() {
        print(SAVE_MESSAGE);
        if (nextYes()) {
            print(SAVE_LOAD_FILENAME_MESSAGE);
            String filename = nextLine();
            try {
                PrintStream out = new PrintStream(new File(filename));
                tree.saveGame(out);
                out.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
