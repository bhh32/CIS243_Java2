// This program prompts the user for the name of a file and then counts the
// occurrences of words in the file (ignoring case).  It then reports the
// frequencies using a cutoff supplied by the user that limits the output
// to just those words with a certain minimum frequency.

import java.util.*;
import java.io.*;

public class WordCount {
    public static void main(String[] args) throws FileNotFoundException {
        // open the file
        Scanner console = new Scanner(System.in);
        System.out.print("What is the name of the text file? ");
        String fileName = console.nextLine();
        Scanner input = new Scanner(new File(fileName));

        // count occurrences
        Map<String, Integer> wordCounts = new TreeMap<String, Integer>();
        while (input.hasNext()) {
            String next = input.next().toLowerCase();
            if (!wordCounts.containsKey(next)) {
                wordCounts.put(next, 1);
            } else {
                wordCounts.put(next, wordCounts.get(next) + 1);
            }
        }
        
        //countWithMap(input);

        // get cutoff and report frequencies
        System.out.println("Total words = " + wordCounts.size());
        System.out.print("Minimum number of occurrences for printing? ");
        int min = console.nextInt();
        for (String word : wordCounts.keySet()) {
            int count = wordCounts.get(word);
            if (count >= min)
                System.out.println(count + "\t" + word);
        }
    }
    
    public static void countWithMap(Scanner input) {
      Map<String, Integer> map = new TreeMap<String, Integer>();
      
      while (input.hasNext()) {
         String word = input.next().toLowerCase();
         
         // if the word is not in the map, add it with 1 (first occurance)
         // if the word is in the map, get the last count, update it by 1 in the map.
         if (!map.containsKey(word)) {
            map.put(word, 1);
         }
         else {
            map.put(word, map.get(word + 1));
         }
      }
      
      System.out.println("file has " + map.size() + " different words!");
      
      int max = 0;
      String maxWord = "";
      
      for (String word : map.keySet()) {
         System.out.println(word + " " + max);
         if (map.get(word) != null){
            int count = map.get(word);
            if (count > max) {
               max = count;
               maxWord = word;
            }
         }
      }
    }
}
