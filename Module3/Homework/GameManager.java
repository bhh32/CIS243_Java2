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
         in the list.  For example, if the list contains {"Sam", "Zach", "Sarah"}, 
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
      PlayerNode currentThief = thiefFront;
      for (int i = 1; i < names.size(); ++i) {
         currentThief.next = new PlayerNode(names.get(i));
         currentThief = currentThief.next;
      }
      
      currentThief.next = thiefFront;
   }
   
   public void printThiefRing() {
      /*
         This method should print the names of the people in the thief ring, one per line, 
         with each line indented four spaces (not a tab!), with output of the form "<name> 
         will steal from <name>", where each <name> is replaced by a player's name with no <>'s.  
         If the game is over, it should instead print (with the indentation still): "<name> 
         is the Prince of Thieves!". (Note, RobinHood never calls printThiefRing once the game 
         is won, so you have to test for this behavior yourself.)
      */
      if (thiefFront.next != null) {
         printMessage(" will steal from ", thiefFront);
      }
      else {
         printMessage(" is the Prince of Thieves!", thiefFront);
      }
   }
   
   public void printStolenList() {
      /*
         This method should print the names of the people in the stolen list, one per line, 
         indented four spaces, with output of the form "<name> was stolen from by <name>".  
         It should print the names in reverse order (most recently stolen from first, then 
         next more recently stolen from, and so on).  It should produce no output if the history 
         list is empty (no-one has been stolen from yet).
      */
      if (stolenFront != null) {
         printMessage(" was stolen from by ", stolenFront);
      }         
   }
   
   public boolean thiefRingContains(String name) {
      /*
         This should return true if the given name is in the current thief ring and should 
         return false otherwise.  It should ignore case in comparing names.
      */
      
      return doesListContain(name, thiefFront);
   }
   
   public boolean stolenListContains(String name) {
      /*
         This should return true if the given name is in the current stolen list and should 
         return false otherwise.  It should ignore case in comparing names.
      */
      
      return doesListContain(name, stolenFront);
   }
   
   public boolean isGameOver() {
      /*
         This should return true if the game is over (i.e., if the thief ring has just one 
         person in it) and should return false otherwise.
      */
      
      return thiefFront.next == thiefFront;
   }
   
   public String winner() {
      /*
         This should return the name of the winner of the game.  It should return null 
         if the game is not over.
      */
      
      return thiefFront.next == null ? thiefFront.name : null;
   }
   
   // Takes in the victims name. Moves the victims name to the stolen list, and updates
   // the thief ring.
   public void steal(String name) {    
      if (isGameOver()) {
         throw new IllegalStateException("The game is over, you cannot continue to steal!");
      }
      if (!thiefRingContains(name)) {
         throw new IllegalArgumentException("The name provided isn't part of the current theif ring!");
      }
      
      PlayerNode current = thiefFront;
      String noCaseName = current.next.name.toLowerCase();
      
      // 1.) Move to thief
      while (current.next != thiefFront && !noCaseName.equals(name.toLowerCase())) {
         current = current.next;
         noCaseName = current.next.name.toLowerCase();
      }
      
      // 2.) Add thiefs name to victims thief field
      if (current.next == thiefFront) {
         thiefFront.thief = current.name;
      }
      else {
         current.next.thief = current.name;
      }
      
      // 3.) Move victim to stolenList
      
      // First steal
      if (stolenFront == null) {
         // The last theif
         if (current.next == thiefFront) {
            stolenFront = thiefFront;
            thiefFront = thiefFront.next;
            stolenFront.next = null;
         }
         // Not last thief
         else {
            stolenFront = current.next;
            current.next = current.next.next;
            stolenFront.next = null;
         }
      }
      // Not first steal
      else {
      // Why is code making the lists the same thing?
         PlayerNode temp = stolenFront;
         PlayerNode temp2 = current.next;
         current.next = current.next.next;
         stolenFront = temp2;
         stolenFront.next = temp;
         stolenFront.next.next = null;
         
         
         PlayerNode temp3 = thiefFront;
         while (temp3.next != null) {
            temp3 = temp3.next;
         }
         
         temp3.next = current;
         
      /* Good Code Below Don't Delete!
         PlayerNode currentStolen = stolenFront;
         while (currentStolen.next != null) {
            currentStolen = currentStolen.next;
         }
         
         currentStolen.next = current.next;
         current.next = current.next.next;
         currentStolen.next.next = null;
*/
      }
  }
   
   // Helper Methods
   
   /* Depends on the list front node it gets on where the message
      ends up in the prinln call. The given string is printed out
      as the message.
   */
   private void printMessage(String msg, PlayerNode frontNode) {
      PlayerNode current = frontNode;
      
      // Check to see which Linked list it's handed
      if (frontNode == thiefFront) {
         // Prints out the message if there is a winner.
         if (frontNode.next == null) {
            System.out.println("    " + frontNode.name + msg);
         }
         // Walk the list printing out who is stealing from whom.
         else {            
            while (current.next != frontNode && current.next != null) {               
               System.out.println("    " + current.name + msg + current.next.name);
               if (current.next != null) {
                  current = current.next;
               }
            }            
            // Print out the last nodes message.
            System.out.println("    " + current.name + msg + thiefFront.name);
         }
         
      }
      else {
         // Walk the stolen list printing out the messages for each node.
         while (current != null) {
            System.out.println("    " + current.name + msg 
               + current.thief);
            current = current.next;
         }
      }
   }
   
   // Returns false if the list doesn't contain the name, and true if
   // it does. A name and a lists' front node is passed in.
   private boolean doesListContain(String name, PlayerNode frontNode) {
      if (frontNode == null) {
         return false;
      }
      
      PlayerNode current = frontNode;      
      // String variable added for readability
      String currentName = current.name.toLowerCase();
      
      while (current != null) {
         if (currentName.equals(name.toLowerCase())) {
            return true;
         }         
         current = current.next;
         currentName = current.name.toLowerCase();
      }
      
      return false;
   }
   
   
   public static void main(String[] args) {
      List<String> nameList = new ArrayList<String>();
      nameList.add("Bryan");
      nameList.add("Meghan");
      nameList.add("Zoe");
      GameManager newGame = new GameManager(nameList);
      //newGame.printThiefRing();
      
      // Asserts for contains methods
      /*assert newGame.thiefRingContains("Meghan") : "Something went wrong in thiefRingContains";
      assert newGame.thiefRingContains("Bryan") : "Something went wrong in thiefRingContains";
      assert newGame.thiefRingContains("Zoe") : "Something went wrong in thiefRingContains";
      assert !newGame.thiefRingContains("Bob") : "Something went wrong in thiefRingContains";
      assert !newGame.stolenListContains("Bryan") : "Something went wrong in stolenListContains";*/
      
      
      newGame.steal("Zoe");
      newGame.printThiefRing();
      newGame.printStolenList();
      
      newGame.steal("Bryan");
      newGame.printThiefRing();
      newGame.printStolenList();
   }
   
   
   /*******************************************************************/
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
