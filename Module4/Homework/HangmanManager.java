/*
   Bryan Hyland
   01May19
   CIS143 - Java 2
   
   This class manages the word list,
   guesses, and the TreeMap that
   handles what words go into the word
   list.
*/

import java.util.*;

public class HangmanManager {
   private int guessesLeft;
   private Set<String> wordList = new TreeSet<String>();
   private Set<Character> guesses = new TreeSet<Character>();
   private Map<String, Set<String>> familyMap = 
                    new TreeMap<String, Set<String>>();
   private String pattern = "";
   
   // Sets up the wordList, familyMap,
   // and the best starting pattern.
   public HangmanManager(Collection<String> dictionary, int length, int max) {

      // 1.) Throw Exceptions
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException("Either "
         + "length was less than 1, or max was "
         + "less than 0. Length: " + length + " Max: " + max);
      }

      // Set the max amount of guesses
      guessesLeft = max;

      // Walk the dictionary to create the wordList Set
      for (String word : dictionary) {
         if (word.length() == length) {
            wordList.add(word);
         }
      }
      
      Set<String> wordListCopy = new TreeSet<String>();
      wordListCopy.addAll(wordList);
      familyMap = getPossibilities(wordListCopy);
      pattern = getBestPattern(familyMap, 
            new ArrayList<String>(familyMap.keySet()));
   }

   // Returns a copy of the current wordList Set
   public Set<String> words() {
      Set<String> wordList = new TreeSet<String>(this.wordList);

      return wordList;
   }
   
   // Returns how many guesses are left.
   public int guessesLeft() {
      return guessesLeft;
   }
   
   // Returns a copy of the guesses Set
   public Set<Character> guesses() {

      Set<Character> guesses = new TreeSet<Character>(this.guesses);

      return guesses;
   }

   // Throws an IllegalStateException
   // if the wordList is empty. Otherwise
   // returns the current pattern.
   public String pattern() {
      if (words().isEmpty()) {
         throw new IllegalStateException("The word list is empty!");
      }

      return pattern;
   }

   public int record(char guess) {
      // 1.) Throw Exceptions
      if (guessesLeft < 1 || words().isEmpty()) {
         throw new IllegalStateException("Either "
         + "you're out of guesses or the wordList is empty, Guesses Left: "
         + guessesLeft + " wordList empty: " + words().isEmpty());
      }

      if (guesses.contains(guess)) {
         throw new IllegalArgumentException("You have already "
         + "guessed this letter: " + guess);
      }
      
      // Add the guess to guesses
      guesses.add(guess);
      
      // 1.) Create discard word list and current word list
      Set<String> discardWordSet = new TreeSet<String>();
      Set<String> currentWordList = familyMap.get(pattern);
      List<String> keyList = new ArrayList(familyMap.keySet());

      // 2.) Update familyMap, pattern, and
      //     the wordList.     
      familyMap = getPossibilities(wordList);
      pattern = getBestPattern(familyMap, new ArrayList(familyMap.keySet()));
      wordList = familyMap.get(pattern);
      
      // Create a variable to count
      // the guess in the pattern.
      int guessCount = 0;
      
      // Count how many times the guess
      // is in the pattern.
      for (int i = 0; i < pattern.length(); ++i) {
         if (pattern.charAt(i) == guess) {
            ++guessCount;
         }
      }
      
      // Wrong guess decrements guessesLeft
      if (guessCount == 0) {         
         guessesLeft--;
      }
      
      return guessCount;
   }

   // Helper Methods

   // Updates the word pattern
   private String updatePattern(int length, String tempChosenWord) {
      String tempPattern = new String();
      
      // 1.) Ensure that the chosen words space pattern is the
      //     same as the current patterns.
      String wordTemp = null;
      for (int k = 0; k < tempChosenWord.length(); ++k) {
         if (k == 0) {
            wordTemp = new String(
            new Character(tempChosenWord.charAt(k)).toString());
         }
         else {
            wordTemp += tempChosenWord.charAt(k);
         }
      }

      wordTemp = wordTemp.trim();
      

      // 3.) Create and loop through the pattern array adding the
      //     guesses to it if they're part of the chosen
      //     word.
      
      char[] chosenArray = new char[wordTemp.length()];
      for (int j = 0; j < wordTemp.length(); ++j) {
         if (guesses.contains(wordTemp.charAt(j)) 
            || wordTemp.charAt(j) == ' ') {
            chosenArray[j] = wordTemp.charAt(j);
         }
         else {
            chosenArray[j] = '-';
         }
      }

      // 4.) Set the pattern to the updated array
      tempPattern = new String(chosenArray);

      return tempPattern;
   }
   
   // Sort out all of the current key and set possibilities
   private Map<String, Set<String>> getPossibilities(Set<String> currentSet) {
      // Create a new map to hold all of the possibilities
      Map<String, Set<String>> possibilities = 
            new TreeMap<String, Set<String>>();
      // Create a new set to hold the current set
      Set<String> tempSet = new TreeSet<String>();
      tempSet.addAll(currentSet);
      
      // Walk the set adding the words to their pattern
      while (!tempSet.isEmpty()) {
         // Get and remove the first word in the set
         String temp = (String) ((TreeSet) tempSet).pollLast();

         // Update the pattern for that word
         String tempPattern = updatePattern(temp.length(), temp);

         // If the pattern (key) is not already in the map add it
         // and the set
         if (!possibilities.keySet().contains(tempPattern)) {
            Set<String> newSet = new TreeSet<String>();
            newSet.add(temp);
            possibilities.put(tempPattern, newSet);
         }
         // Otherwise, add the word to the set
         else {
            possibilities.get(tempPattern).add(temp);
         }
      }

      return possibilities;
   }

   // Returns the best pattern to use
   private String getBestPattern(Map<String, 
         Set<String>> possibilities, List<String> keyList) {
      String bestPattern = "";
      
      // If there's only one key, 
      // set the best pattern to that
      // key.
      if (keyList.size() == 1) {
         bestPattern = keyList.get(0);
      }
      else {
         // If the keyList contains the
         // current pattern...
         if (keyList.contains(pattern)) {
            // Set bestPattern biggest set
            for (int i = 0; i < keyList.size(); ++i) {
               // Compare pattern sets
               if (possibilities.get(pattern).size() 
                  >= possibilities.get(keyList.get(i)).size()) {
                  bestPattern = pattern;
               }
               else {
                  bestPattern = keyList.get(i);
               }
            }
         }
         else {
            // Same as above, but the current
            // pattern is not in the most current
            // keyList.
            for (int i = 0; i < keyList.size(); ++i) {
               if (i + 1 < keyList.size() 
                  && possibilities.get(keyList.get(i)).size() 
                  >= possibilities.get(keyList.get(i + 1)).size()) {
                  bestPattern = keyList.get(i);
               }
               else if (i + 1 < keyList.size()) {
                  bestPattern = keyList.get(i + 1);
               }
            }
         }
      }
      
      return bestPattern;
   }
}
