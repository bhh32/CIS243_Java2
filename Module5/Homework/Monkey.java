import java.util.*;
/*
   Author: Bryan Hyland
   Date:   6May19
   Homework: Module 5, MonkeyTypewriter
   
   This class creates a language map that includes
   the non-terminals and their rules. A random word/phrase
   is built up based upon the rules of the language that
   is passed into it.
*/
public class Monkey {
   // Holds the language non-terminals and their rules.
   private Map<String, Set<String>> langMap = 
      new TreeMap<String, Set<String>>();
  
  /*
     Constructs the langMap by taking in a list of Strings.
     Each element in the list is broken up between non-terminals
     and their rules. The non-terminals are the keys in the map.
     The rules are a set of that non-terminal key. Throws an 
     IllegalArgumentException if the list is null or empty. There
     is a nested loop, but it doesn't seem to affect efficiency
     very much.
  */
   public Monkey(List<String> grammar) {
      if (grammar == null || grammar.isEmpty()) {
         throw new IllegalArgumentException("The list is null or empty!");
      }
      List<String[]> ruleList = new ArrayList<String[]>();
      int ruleListIndex = 0;
      for (String rule : grammar) {
         String[] splitString = rule.split("::=");
         String nonTerminal = splitString[0].trim();
      
         // Set the non-terminals as keys for the language map
         if (!langMap.keySet().contains(splitString[0])) {
            langMap.put(nonTerminal, new TreeSet<String>());
         }
      
         // Split the Rules up into an array for processing
         splitString = splitString[1].split("[|]+");
      
         // Add the rules to the map for the nonTerminal
         for (String rules : splitString) {
            langMap.get(nonTerminal).add(rules.trim());
         }     
      }
   }
  
   /* Returns true if symbol is a non-terminal,
      and false if it is a terminal or not in the
      language. Throws an IllegalArgumentException 
      if the String is null or empty.
   */
   public boolean isNonTerminal(String symbol) {
      if (symbol == null || symbol.length() < 1) {
         throw new IllegalArgumentException("A null "
         + "or empty string was passed in!");
      }
    
      if (!langMap.keySet().contains(symbol)) {
         return false;
      }
    
      return true;
   }

   /*
     Throws an IllegalArgumentException if nonterminal
     is null or is a terminal. Builds and returns a 
     "sentence" based on the rules of the language for
     the nonterminal it is passed. Uses a helper method
     called getWordPhase() to recurse and build the 
     sentence.
   */
   public String getRandom(String nonterminal) {
      if (nonterminal == null || !isNonTerminal(nonterminal)) {
         throw new IllegalArgumentException("The given String was either null"
         + " or not a nonterminal in the language!");
      }
    
      // 1.) Create the sentence string to add to
      String sentence = new String();
    
      // 2.) Get the rules for the non-terminal
      List<String> rules = new ArrayList<String>(langMap.get(nonterminal));
  
      // 3.) Get a random rule to apply
      sentence += getWordPhrase(rules);   
        
      return sentence;
   }
  
   // Returns a String with of all of the
   // nonterminals.
   public String toString() {
      List<String> nonTerminals = 
         new ArrayList<String>(langMap.keySet());
      return nonTerminals.toString();
   }
  
   // Helper Methods
  
   /*
      Does all of the heavy lifting and recursion for
      getRandom(). Builds a random word/phrase based
      upon the rules it is handed. It then returns
      the finished word/phrase back to getRandom().
      getRandom() is the gatekeeper for this method.
   */
   private String getWordPhrase(List<String> rules) {
      String wordPhrase = new String();
     
      // Get a random rule from the list
      Random rand = new Random();
      int randIndex = rand.nextInt(rules.size());
      String rule = rules.get(randIndex);
     
      // Create an array to hold the separate rules
      // if it's a multi tier rule
      String[] multiRule = rule.split("\\s+");
     
      // Check and see if it is a multi tier rule
      if (multiRule.length > 1) {
         for (String r : multiRule) {
            // Check to see if the rule is a terminal
            if (!isNonTerminal(r)) {
               wordPhrase += r + " ";
            }
            // When it's not a terminal call getRandom again
            else {
               wordPhrase += getRandom(r) + " ";
            }
         }
      }
      // If it's a single tier rule, but not a terminal
      else if (isNonTerminal(rule)) {
         wordPhrase += getRandom(rule) + " ";
      }
      // A single tier rule and is a terminal
      else {
         wordPhrase += rule + " ";
      }
     
      return wordPhrase.trim();
   }
}
