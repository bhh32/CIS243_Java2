import java.util.*;

public class Monkey {
  public Monkey(List<String> grammar) {
    /*
      This method will be passed a grammar as a list of strings.  Each string is
      one BNF rule. You should use regular expressions (see below) to break
      apart the rules and store them into a Map so that you can look up parts
      of the grammar efficiently later. You should not modify the list passed
      in. You should throw an IllegalArgumentException if grammar is null or an
      empty list.

      If the grammar contains more than one line for the same non-terminal, the
      rule in the grammar for that non-terminal should be the concatenation of
      all of them. For example, if a grammar has a line "s ::= sally|bob" and
      then later has the line "s ::= sam|jim", your class should treat it the
      same as if it originally got the rule "s ::= sally|bob|sam|jim".
    */


  }

  public boolean isNonTerminal(String symbol) {
    /*
      Returns true if the given symbol is a non-terminal of the grammar; returns
      false otherwise. You should throw an IllegalArgumentException if the string
      is null or has length 0.

      For example, for the grammar above, isNonTerminal("sentence") would return
      true and isNonTerminal("swim") or isNonTerminal("boy") would return false.
      Note swim is not used in the language, and boy is used as a terminal.
    */
    return false;
  }

  // Hardest method to write
  // Will require recursion
  // Use Random class not Math.random().
  // Close to addDash in the lab, but not exact!
  // Look at Crawler (directory recursion) for ideas for recursion.
  public String getRandom(String nonterminal) {
    /*
    This method generates a transformation of the given non-terminal into a
    string containing only terminals and returns it.

    If nonterminal is not a non-terminal in your grammar or it is null, you
    should throw an IllegalArgumentException.

    If nonterminal is a non-terminal in your grammar, you should apply one of
    its rules (each with equal probability). Use the Random class in java.util
    to help you make random choices between rules. Do not use Math.random().
    You must keep applying rules until you have a string containing only
    terminals.`More on this below.

    For example, calling getRandom("sentence") could possibly return
    (as influenced by the random choices made): "the boy walks" or "a boy runs".
    */
    return new String();
  }

  public String toString() {
    /*
    This method should return a string representation of all non-terminal
    symbols from the grammar in alphabetical order. You will want to use the
    keySet of your map.

    For example, calling toString() for the example grammar above would
    return: "[article, noun, noun_phrase, sentence, verb]"
    */
    return new String();
  }
}
