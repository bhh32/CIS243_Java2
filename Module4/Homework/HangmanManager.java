import java.util.*;

public class HangmanManager {
   private int guessesLeft;
   private Set<String> wordList = new TreeSet<String>();
   private Set<Character> guesses = new TreeSet<Character>();
   private Map<String, Set<String>> familyMap = new TreeMap<String, Set<String>>();
   private String pattern = "";
   public String chosenWord = "";

   public HangmanManager(Collection<String> dictionary, int length, int max) {

      // 1.) Throw Exceptions
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException("Either length was less than 1, or max was "
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

      pattern = updatePattern(length);

      familyMap.put(pattern, wordList);
   }

   public Set<String> words() {
      Set<String> wordList = new TreeSet<String>(this.wordList);

      return wordList;
   }

   public int guessesLeft() {
      int guessesLeft = this.guessesLeft;
      return guessesLeft;
   }

   public Set<Character> guesses() {

      Set<Character> guesses = new TreeSet<Character>(this.guesses);

      return guesses;
   }

   public String pattern() {
      if (words().isEmpty()) {
         throw new IllegalArgumentException("The word list is empty!");
      }

      return pattern;
   }

   public int record(char guess) {
       /*
         This is the method that does most of the work.
         The client calls this method to record that the
         player made a guess. Using this guess, this method
         should decide what set of words to use going forward.
         It should return the number of occurrences of the
         guessed letter in the new pattern and it should
         appropriately update the number of guesses left.
         This method should throw an IllegalStateException if
         the number of guesses left is not at least 1 or if the
         set of words is empty at the start of the call.  It should
         throw an IllegalArgumentException if the set of words is
         nonempty and the character being guessed was guessed previously.
         You may assume that all guesses passed to record are lowercase letters.

         Note: Alphabetically means towards key, not value!
      */

      // 1.) Throw Exceptions
      if (guessesLeft < 1 || words().isEmpty()) {
         throw new IllegalStateException("Either you're out of guesses or the wordList is empty, Guesses Left: "
         + guessesLeft + " wordList empty: " + words().isEmpty());
      }

      if (guesses.contains(guess)) {
         throw new IllegalArgumentException("You have already guessed this letter: " + guess);
      }



      // 1.) Create discard word list and current word list
      Set<String> discardWordSet = new TreeSet<String>();
      Set<String> currentWordList = familyMap.get(pattern);

      // 2.) See what words do not contain that letter
      while (!currentWordList.contains(new Character(guess).toString()) && !currentWordList.isEmpty()) {
        // Wrong guess decrements guessesLeft
        Set<String> nextWordList = new TreeSet<String>();

        for (String pat : currentWordList) {
          nextWordList.addAll(familyMap.get(pat));
        }

        discardWordSet.addAll(currentWordList);
        nextWordList.removeAll(discardWordSet);
      }

      // 2.) Check to see if the discardWordSet is bigger or smaller
      //     

      // 2.) Sort between words that do and don't have the guess
      /*Set<String> doesHave = new TreeSet<String>();
      Set<String> doNotHave = new TreeSet<String>();
      for (String word : words()) {
         if (word.contains(new Character(guess).toString())) {
            doesHave.add(word);
         }
         else {
            doNotHave.add(word);
         }
      }

      // 3.) Check which is longer
      if (doNotHave.size() > doesHave.size()) {
         wordList = new TreeSet<String>(doNotHave);
         familyMap.put(updatePattern(wordList.size()), wordList);
      }
      else {
         // TODO: Figure out what to do next
         // 4.) Since doesHave is longer
         // Create more sets to find out how many each
         // particular "family" sets have the character n amount of times.

         // Map the possible solutions
         familyMap = getPossibilities(doesHave);

         // Create a list of keys
         List<String> patterns = new ArrayList<String>(familyMap.keySet());

         // Get the best key
         String bestPattern = getBestPattern(familyMap, patterns);


         // Update the pattern
         pattern = updatePattern(bestPattern.length(), bestPattern);

         // Update the word list
         wordList = familyMap.get(pattern);*/

         // Update the chosen word when the wordList is size 1
         /*if (wordList.size() == 1) {
            chosenWord = possibilities.get(pattern);
         }


      }*/

      guessesLeft--;

      return guessesLeft;
   }

   // Helper Methods

   // Updates the word pattern if there is no chosen word
   // or if we're first creating a pattern.
   private String updatePattern(int length) {
      String tempPattern = new String();
      if (!pattern.equals("")) {
         // 1.) Get the current pattern and make it an array
         char[] chosenArray = pattern.toCharArray();

         // 2.) Ensure that the chosen words space pattern is the
         //     same as the current patterns.
         String wordTemp = null;
         for (int k = 0; k < chosenWord.length(); ++k) {
            if (k == 0) {
               wordTemp = new String(new Character(chosenWord.charAt(k)).toString() + " ");
            }
            else {
            wordTemp += chosenWord.charAt(k) + " ";
            }
         }

         wordTemp = wordTemp.trim();

         // 3.) Loop through the pattern array adding the
         //     guesses to it if they're part of the chosen
         //     word.
         for (int j = 0; j < chosenWord.length(); ++j) {
            if (guesses.contains(wordTemp.charAt(j))) {
               chosenArray[j] = wordTemp.charAt(j);
            }
         }

         // 4.) Set the pattern to the updated array
         tempPattern = new String(chosenArray);
      }
      // Initial pattern building
      else {
         for (int i = 0; i < length; ++i) {
            if (i == 0 || i == (length - 1) / 2
                || i == length - 1) {
               tempPattern += "_";
            }
            else {
               tempPattern += " _ ";
            }
         }
      }

      return tempPattern;
   }

   private String updatePattern(int length, String tempChosenWord) {
      String tempPattern = new String();

      // 1.) Get the current pattern and make it an array
      char[] chosenArray = pattern().toCharArray();

      // 2.) Ensure that the chosen words space pattern is the
      //     same as the current patterns.
      String wordTemp = null;
      for (int k = 0; k < tempChosenWord.length(); ++k) {
         if (k == 0) {
            wordTemp = new String(new Character(tempChosenWord.charAt(k)).toString() + " ");
         }
         else {
            wordTemp += tempChosenWord.charAt(k) + " ";
         }
      }

      wordTemp = wordTemp.trim();

      // 3.) Loop through the pattern array adding the
      //     guesses to it if they're part of the chosen
      //     word.
      for (int j = 0; j < tempChosenWord.length(); ++j) {
         if (guesses.contains(wordTemp.charAt(j))) {
            chosenArray[j] = wordTemp.charAt(j);
         }
      }

      // 4.) Set the pattern to the updated array
      tempPattern = new String(chosenArray);

      return tempPattern;
   }

   private Map<String, Set<String>> getPossibilities(Set<String> currentSet) {
      Map<String, Set<String>> possibilities = new TreeMap<String, Set<String>>();

      while (!currentSet.isEmpty()) {
         // Get and remove the first word in the set
         String temp = (String)((TreeSet)currentSet).pollLast();

         // Update the pattern for that word
         String tempPattern = updatePattern(temp.length(), temp);

         if (!possibilities.keySet().contains(tempPattern)) {
            Set<String> tempSet = new TreeSet<String>();
            tempSet.add(temp);
            possibilities.put(tempPattern, tempSet);
         }
         else {
            possibilities.get(tempPattern).add(temp);
         }
      }

      return possibilities;
   }

   // Returns the best pattern to use
   private String getBestPattern(Map<String, Set<String>> possibilities, List<String> keyList) {
      String bestPattern = "";
      for (int i = 0; i < keyList.size(); ++i) {
         // Compare set sizes
         if (i + 1 < keyList.size() - 1 && possibilities.get(keyList.get(i)).size() > possibilities.get(keyList.get(i + 1)).size()) {
            bestPattern = keyList.get(i);
         }
      }

      return bestPattern;
   }

   // Test main method
  public static void main(String[] args) {
      List<String> words = new ArrayList<>();
      words.add("brain");
      words.add("lists");
      words.add("that");
      words.add("a");
      words.add("supercalifragolistic");
      HangmanManager hm = new HangmanManager(words, 5, 10);
      System.out.println(hm.wordList);
      hm.chosenWord = new String("brain");
      hm.guesses.add('c');
      hm.guesses.add('b');
      hm.updatePattern(5, "brain");
      System.out.println(hm.pattern());

      hm.guesses.add('a');
      hm.updatePattern(5, "brain");
      System.out.println(hm.pattern());

      hm.guesses.add('r');
      hm.updatePattern(5, "brain");
      System.out.println(hm.pattern());

      hm.guesses.add('e');
      hm.updatePattern(5, "brain");
      System.out.println(hm.pattern());
   }
}
