/*
   Extra instructions: Nested Loops are NOT allowed! This includes calling a method from a loop
                       that has a loop!
*/

import java.util.*;

public class GameManager {
   private PlayerNode thiefFront;
   private PlayerNode stolenFront;
   
   public GameManager(List<String> names) {
      /*
         This is your constructor. It should add the names from the 
         list into the thief ring in the same order in which they appear 
         in the list.  For example, if the list contains {“Sam”, “Zach”, “Sarah”}, 
         in that order, then in the initial thief ring we should see that 
         Sam is targeting Zach who is targeting Sarah who is targeting 
         Sam (reported in that order).  You may assume that the names are 
         non-empty strings and that there are no duplicate names (ignoring case).  
         Your method should throw an IllegalArgumentException if the list is null or empty.
      */
      if (names == null || names.isEmpty()) {
         throw new IllegalArgumentException("The names list is null or empty!");
      }
      
      thiefFront = new PlayerNode(names.get(0));
      PlayerNode currentTheif = thiefFront;
      for (int i = 1; i < names.size(); ++i) {
         currentTheif.next = new PlayerNode(names.get(i));
         currentTheif = currentTheif.next;
      }
   }
   
   public void printThiefRing() {
      /*
         This method should print the names of the people in the thief ring, one per line, 
         with each line indented four spaces (not a tab!), with output of the form “<name> 
         will steal from <name>”, where each <name> is replaced by a player's name with no <>'s.  
         If the game is over, it should instead print (with the indentation still): "<name> 
         is the Prince of Thieves!". (Note, RobinHood never calls printThiefRing once the game 
         is won, so you have to test for this behavior yourself.)
      */
      PlayerNode current = thiefFront;
      while (current.next != null) {
         System.out.println(current.name + " will steal from " + current.next.name + "!");
         current = current.next;
      }
   }
   
   public void printStolenList() {
      /*
         This method should print the names of the people in the stolen list, one per line, 
         indented four spaces, with output of the form “<name> was stolen from by <name>”.  
         It should print the names in reverse order (most recently stolen from first, then 
         next more recently stolen from, and so on).  It should produce no output if the history 
         list is empty (no-one has been stolen from yet).
      */
      if (stolenFront != null) {
         PlayerNode current = stolenFront;
         
         while (current != null) {
            // TODO: Print out current player and the one that stole from it.
         }
      }
   }
   
   public boolean thiefRingContains(String name) {
      /*
         This should return true if the given name is in the current thief ring and should 
         return false otherwise.  It should ignore case in comparing names.
      */
      String nameWithoutCase = name.toLowerCase();
      PlayerNode current = thiefFront;
      while (current.next != null) {
         String temp = current.name.toLowerCase();
         if (temp.equals(nameWithoutCase)) {
            return true;
         }
         
         current = current.next;
      }      
      return false;
   }
   
   public boolean stolenListContains(String name) {
      /*
         This should return true if the given name is in the current stolen list and should 
         return false otherwise.  It should ignore case in comparing names.
      */
      
      return false;
   }
   
   public boolean isGameOver() {
      /*
         This should return true if the game is over (i.e., if the thief ring has just one 
         person in it) and should return false otherwise.
      */
      if (thiefFront.next == null) {
         return true;
      }
      
      return false;
   }
   
   public String winner() {
      /*
         This should return the name of the winner of the game.  It should return null 
         if the game is not over.
      */
      
      return "winner() not implemented!";
   }
   
   public void steal(String name) {
      /*
         This method records the stealing from the person with the given name, 
         transferring that person from the thief ring to the stolen from list.  This operation 
         should not change the thief ring order of printThiefRing (i.e., whoever used to be 
         printed first should still be printed first unless that’s the person who was stolen from, 
         in which case the person who used to be printed second should now be printed first).  It 
         should throw an IllegalArgumentException if the given name is not part of the current thief 
         ring and it should throw an IllegalStateException if the game is over (it doesn’t matter which 
         it throws if both are true).  It should ignore case in comparing names.  

         Note: Exceptions should be thrown as soon as possible in this method. 
      */
      
      if (isGameOver()) {
         throw new IllegalStateException("The game is over, you cannot continue to steal!");
      }
      if (!isNameThief(name)) {
         throw new IllegalArgumentException("The name provided isn't part of the current theif ring!");
      }
      
   }
   
   private boolean isNameThief(String name) {
      PlayerNode current = thiefFront;
      
      while (current != null) {
         if (current.name.equals(name)) {
            return true;
         }
         
         current = current.next;
      }
      
      return false;
   }
   
   
   public static void main(String[] args) {
      List<String> nameList = new ArrayList<String>();
      nameList.add("Bryan");
      nameList.add("Meghan");
      nameList.add("Zoe");
      GameManager newGame = new GameManager(nameList);
      newGame.printThiefRing();
   }
   
	// YOUR CODE GOES ABOVE THIS LINE ONLY (not below)

	//////// DO NOT MODIFY PlayerNode. You will lose points if you do. ////////
	/**
	 * Each PlayerNode object represents a single node in a linked list for a game
	 * of Robin Hood.
	 */
	private static class PlayerNode {
		public final String name; // this person's name
		public String thief; // name of who stole from this person (null if still playing)
		public PlayerNode next; // next node in the list (null if none)

		/**
		 * Constructs a new node to store the given name and no next node.
		 */
		public PlayerNode(String name) {
			this(name, null);
		}

		/**
		 * Constructs a new node to store the given name and a reference to the given
		 * next node.
		 */
		public PlayerNode(String name, PlayerNode next) {
			this.name = name;
			this.thief = null;
			this.next = next;
		}
	}
}
