// Anagrams contains a main program that prompts a user for the name of a
// dictionary file and then gives the user the opportunity to find anagrams of
// various phrases.  It constructs a Twister object to do the actual
// search for anagrams that match the user's phrases.

import java.io.*;
import java.util.*;

public class Anagrams {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the Word Twister!");
        System.out.println();

        // open the dictionary file
        Scanner console = new Scanner(System.in);
        System.out.print("What is the dictionary file to use? ");
        Scanner input = new Scanner(new File(console.nextLine()));

        // Read dictionary into a List
        List<String> dictionary = new ArrayList<String>();
        while (input.hasNextLine()) {
        	String line = input.nextLine();
        	if (line.length() > 0)
        		dictionary.add(line);
        }

        // Solve Anagrams
        List<String> dictionary2 = Collections.unmodifiableList(dictionary);
        Twister solver = new Twister(dictionary2);
        while (true) {
            System.out.println();
            System.out.print("Phrase to twist (Enter to quit)? ");
            String phrase = console.nextLine();
            if (phrase.isEmpty()) {
                break;
            }
            System.out.print("Maximum words to include (0 for any)? ");
            int max = console.nextInt();
            console.nextLine();  // to skip newline after int
            solver.findAndPrint(System.out, phrase, max);
        }
        console.close();
    }
}
