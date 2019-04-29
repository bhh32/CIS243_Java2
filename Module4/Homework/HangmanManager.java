import java.util.*;

public class HangmanManager {
   private int guessesLeft;
   public Set<String> wordList = new TreeSet<String>();
   public Set<Character> guesses = new TreeSet<Character>();
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
      
      updatePattern(length);
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
      
      if (!words().isEmpty() && guesses.contains(guess)) {
         throw new IllegalArgumentException("You have already guessed this letter: " + guess);
      }
      
      // 1.) Set the map to the current pattern and wordList
      Map<String, Set<String>> familyMap = new TreeMap<String, Set<String>>();
      familyMap.put(pattern(), words());
      
      // 2.) Sort between words that do and don't have the guess
      Set<String> doesHave = new TreeSet<String>();
      Set<String> doNotHave = new TreeSet<String>();
      for (String word : words()) {
         if (word.contains(new Character(guess).toString())) {
            doesHave.add(word);
         }
         else {
            doNotHave.add(word);
         }
      }
      
      
      return -1;
   }
   
   // Helper Methods
   
   // Updates the word pattern
   public void updatePattern(int length) {
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
         pattern = new String(chosenArray);      
      }
      // Initial pattern building
      else {
         for (int i = 0; i < length; ++i) {
            if (i == 0 || i == (length - 1) / 2 
                || i == length - 1) {
               pattern += "_";
            }
            else {
               pattern += " _ ";
            }
         }
      }      
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
      hm.updatePattern(5);
      System.out.println(hm.pattern());
      
      hm.guesses.add('a');
      hm.updatePattern(5);
      System.out.println(hm.pattern());
      
      hm.guesses.add('r');
      hm.updatePattern(5);
      System.out.println(hm.pattern());
      
      hm.guesses.add('e');
      hm.updatePattern(5);
      System.out.println(hm.pattern());
   }
}