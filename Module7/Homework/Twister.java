import java.util.*;
import java.io.PrintStream;

/*
   Author: Bryan Hyland
   Date:   22May19
   Homework: Module 7, Twister
   
   This class handles creating anagrams from a given
   phrase and printing them out. The amount printed
   can be limited or exhaustive.
*/

public class Twister {
   private Map<LetterInventory, String> inventories;
   private List<String> dictionary;
   
   /*
      Creates a Twister object and creates the inventories
      and dictionary.
   */
   public Twister(List<String> dictionary) {
      // Set the passed list as the instance dictionary
      this.dictionary = new ArrayList<String>(dictionary);
      
      // Create a new HashMap at the size of the dictionary
      inventories = new HashMap<LetterInventory, String>(this.dictionary.size());
      
      // Populate the inventories
      for (String s : dictionary) {
         inventories.put(new LetterInventory(s), s);
      }
   }
   
   /*
      Gatekeeper for the recursive backtracking methods
      with the same name. Processes bad information, as well
      as prunes the dictionary down to possible choices.
   */
   public void findAndPrint(PrintStream out, String text, int max) {
      /*
         This method must use recursive backtracking to find 
         combinations of words that have the same letters as the 
         given string. It should print all combinations of words 
         from the dictionary that are anagrams of text and that 
         include at most max words (or an unlimited number of words 
         if max is 0) to the PrintStream out (not to System.out).

         You should throw an IllegalArgumentException if max is 
         less than 0 or if text is null.
      */
      if (text == null || max < 0) {
         throw new IllegalArgumentException("text wall null or max was"
            + "negative!" + "Text: " + text + ", Max: " + max);
      }
      
      // Create the original LetterInventory instance
      LetterInventory orig = new LetterInventory(text);
      
      /*
         Prune down the dictionary by first creating a pruned inventory map.
         Then, using the pruned map, create a new pruned dictionary for
         use in the backtracking recursion.
      */
      
      // Create pruned inventory
      Map<LetterInventory, String> pruned = new HashMap<LetterInventory, String>(inventories.size());
      
      // Create boolean to test if the tested key has all of the same letters in it
      boolean containsAll = false;
      
      // Walk the keys in the current dictionary
      for (LetterInventory key : inventories.keySet()) {
      
         // Test each letter in the key to see if the text letter inventory has
         // that letter in it.         
         for (int i = 0; i < key.size(); ++i) {
         
           // Create a String for the char in the key
           String checker = key.toString().charAt(i) + "";
           
           // Test if the letter inventory has that char
           if (orig.toString().contains(checker)) {
           
             // If it does, set the boolean to true
             containsAll = true;
           }
           // If the loop runs across a char not contained
           // set containsAll = false and get a new key.
           else {
             containsAll = false;
             break;
           }
         }
               
         // If the loop finished and containsAll is true
         // put the key : value into the pruned inventory.
         if (containsAll) { 
            pruned.put(key, inventories.get(key)); 
         }               
      }
      
      // Create a list for both the dictionary and the pruned values
      List<String> prunedDictionary = 
         new ArrayList<String>(pruned.keySet().size());
      List<String> prunedValues = new ArrayList<String>(pruned.values());
      
      // Walk the full dictionary adding the pruned values to the
      // pruned dictionary.
      for (int i = 0; i < dictionary.size(); ++i) {
         if (!prunedDictionary.contains(dictionary.get(i)) && 
             prunedValues.contains(dictionary.get(i))) {
            prunedDictionary.add(dictionary.get(i));
         }
      }
      
      // Call counting version of recursion findAndPrint
      if (max != 0) {   
         findAndPrint(out, orig, max, prunedDictionary, 0, orig, new ArrayList<String>());
      }
      // Call exhaustive verison of recursion findAndPrint
      else {
         findAndPrint(out, orig, prunedDictionary, orig, new ArrayList<String>());
      }
   }

   // Looks for the limited amount of anagrams in the given phrase
   private void findAndPrint(PrintStream out, LetterInventory orig, int max,
           List<String> prunedDictionary, int count, LetterInventory current, 
           List<String> printList) { 
      
      // Dead End
      if (current == null) {
        return;
      }       
      
      // LetterInventory object to test the print case
      LetterInventory checker = new LetterInventory(printList.toString());
      
      // Print case
      if (orig.subtract(checker) != null 
          && orig.subtract(checker).isEmpty()) {                     
         
         out.println(printList);                    
      } 
      
      // Only loop if the count is less than max - 1 (started at 0)      
      if (count < max) {
         for (int i = 0; i < prunedDictionary.size(); ++i) {                                    
                     
            // Choose
            String thisWord = prunedDictionary.get(i);
            LetterInventory nextCurrent = new LetterInventory(thisWord);
            printList.add(thisWord);                             
                            
            // Recurse to build the list from this key
            findAndPrint(out, orig, max, prunedDictionary, count + 1, current.subtract(nextCurrent), printList);     
              
            // Unchoose
            int lastIdxOfWord = printList.lastIndexOf(thisWord);
            printList.remove(lastIdxOfWord);
         }
      }              
   }
   
   // Exhaustive search of anagrams in the given phrase
   private void findAndPrint(PrintStream out, LetterInventory orig,
           List<String> prunedDictionary, LetterInventory current, 
           List<String> printList) {
       
       // Dead End
       if (current == null) {
         return;
       }       
      
      // LetterInventory object to test the print case
      LetterInventory checker = new LetterInventory(printList.toString());
      
      // Print case
      if (orig.subtract(checker) != null 
          && orig.subtract(checker).isEmpty()) {                    
         
         out.println(printList);                    
      }
           
      for (int i = 0; i < prunedDictionary.size(); ++i) { 
                      
         // Choose
         String thisWord = prunedDictionary.get(i);
         LetterInventory nextCurrent = new LetterInventory(thisWord);
         printList.add(thisWord);                             
                            
         // Recurse to build the list from this key
         findAndPrint(out, orig, prunedDictionary, 
                      current.subtract(nextCurrent), printList);     
              
         // Unchoose
         int lastIdxOfWord = printList.lastIndexOf(thisWord);
         printList.remove(lastIdxOfWord);
      }           
   }
}