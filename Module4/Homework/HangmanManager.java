public class HangmanManager {
   private guessesLeft;
   
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      /*
         Your constructor is passed a dictionary of words, 
         a target word length, and the maximum number of wrong 
         guesses the player is allowed to make. It should use 
         these values to initialize the state of the game. 
         The set of words should initially contain all words 
         from the dictionary of the given length. It should 
         throw an IllegalArgumentException if length is less 
         than 1 or if max is less than 0.

         You may assume that the list of words passed to your 
         constructor will be a non-empty list of non-empty 
         strings composed entirely of lowercase letters.
      */
   }
   
   public Set<String> words() {
      /*
         The client calls this method to get access 
         to the current set of words being considered 
         by the HangmanManager instance. Do not leak 
         internal state (see what this means below - 
         it is often overlooked!).
      */
   }
   
   public int guessesLeft() {
      /*
         The client calls this method to 
         find out how many guesses the player 
         has left.
      */
   }
   
   public Set<Character> guesses() {
      /*
         The client calls this method to 
         find out the current set of letters 
         that have been guessed by the user. 
         Do not leak internal state (see what 
         this means below - it is often overlooked!).
      */
   }
   
   public String pattern() {
      /*
         This should return the current pattern to 
         be displayed for the hangman game taking 
         into account guesses that have been made.  
         Letters that have not yet been guessed should 
         be displayed as a dash.  There should be no 
         leading or trailing spaces. This operation 
         should be "fast" in the sense that it should 
         store the pattern rather than computing it 
         each time the method is called. This method 
         should throw an IllegalStateException if the 
         set of words is empty.

         Note: Save the pattern to avoid recomputation.
      */
   
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
   }
}