/*
   Bryan Hyland
   24Apr19
   CIS143 - Java 2
   
   This class manages the Thief Ring, Stolen
   List, and win state of the Robin Hood game.
*/

import java.util.*;

public class GameManager {
   private PlayerNode thiefFront;
   private PlayerNode stolenFront;
   
   /*
      Adds the elements of the passed in list to the 
      thief ring in the order they are in the list.
      Throws an IllegalArugumentException if the list
      is either empty or full of nulls.
   */
   public GameManager(List<String> names) {
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
   
   /*
      Prints the names of the people in the
      thief ring; line by line. If there is
      only one person in the thiefRing that
      <name> is the Prince of Thieves! is 
      printed out instead.
   */
   public void printThiefRing() {
      
      if (thiefFront.next != thiefFront) {
         printMessage(" will steal from ", thiefFront);
      }
      else {
         printMessage(" is The Prince of Thieves!", thiefFront);
      }
   }
   
   /*
      Prints out the people in the stolenList,
      most recently stolen from first.
      No output if list is empty.
   */
   public void printStolenList() {
      if (stolenFront != null) {
         printMessage(" was stolen from by ", stolenFront);
      }         
   }
   
   // Returns true if the name is in the thiefRing,
   // false otherwise. Ignores case in the name.
   public boolean thiefRingContains(String name) {      
      return doesListContain(name, thiefFront);
   }
   
   // Returns true if the name is in the stolenList,
   // false otherwise. Ignores case in the name.
   public boolean stolenListContains(String name) {
      return doesListContain(name, stolenFront);
   }
   
   // Returns true if there is only one person in 
   // the thiefRing; false otherwise.
   public boolean isGameOver() {
      return thiefFront.next == thiefFront;
   }
   
   // Returns the name of the winner if there is
   // only one person in the thiefRing; null
   // otherwise.
   public String winner() {      
      return thiefFront.next == thiefFront ? thiefFront.name : null;
   }
   
   // Takes in the victims name. Moves the 
   // victims name to the front of the stolen
   // list, and updates the thief ring.
   public void steal(String name) {    
      if (isGameOver()) {
         throw new IllegalStateException("The game "
            + "is over, you cannot continue to steal!");
      }
      if (!thiefRingContains(name)) {
         throw new IllegalArgumentException("The name "
            + "provided isn't part of the current theif ring!");
      }
      
      PlayerNode current = thiefFront;
      String noCaseName = current.next.name.toLowerCase();
      
      // Current count of how many thieves
      // are in the thiefRing.
      int currentThiefCount = countThieves(thiefFront);
      
      // Used to see how far into the
      // thiefRing the stealing thief is.
      int thisThiefIdx = 0;
      
      // Flag for the thief being in the
      // middle of the thiefRing.
      boolean isMiddle = false;
      
      // 1.) Move to thief
      while (current.next != thiefFront 
             && !noCaseName.equals(name.toLowerCase())) {
         current = current.next;
         noCaseName = current.next.name.toLowerCase();
         thisThiefIdx++;
      }
      
      // 2.) Add thiefs name to victims thief field
      if (current.next == thiefFront) {
         thiefFront.thief = current.name;
      }
      else {
         current.next.thief = current.name;
      }
      
      // 3.) Move victim to stolenList
      if (thisThiefIdx < currentThiefCount) {
         isMiddle = true;
      }
      
      addToFrontOfStolenList(
         removeFromThiefRing(current, name, isMiddle)
      );      
   }
   
   // Helper Methods
   
   // Removes a name (node) from the linked list
   private PlayerNode removeFromThiefRing(PlayerNode thiefNode, 
                      String name, boolean isMiddle) {
      // the victim being cut out
      PlayerNode victim = thiefNode.next; 
      
      // Move link the thief to the thief after the victim
      thiefNode.next = thiefNode.next.next;
           
      // Update the thiefFront
      if (thiefNode.next == thiefFront.next && !isMiddle) {
         thiefFront = thiefFront.next;
      }
      else if (thiefNode.next != thiefFront) {
         thiefFront = thiefNode;
      } 
      
      // Make sure the victim has no ties to
      // the thiefRing.
      victim.next = null;
      
      return victim;
   }
   
   // Adds a name (node) to the linked list
   private void addToFrontOfStolenList(
                  PlayerNode nodeToAdd) {
      PlayerNode temp = stolenFront;
      stolenFront = nodeToAdd;
      stolenFront.next = temp;
   }
   
   private int countThieves(PlayerNode firstThief) {
      int count = 0;
      PlayerNode current = firstThief;
      while (current.next != firstThief) {
         count++;
         current = current.next;
      }
      
      return count;
   }
   
   /* Depends on the list front node it gets on where the message
      ends up in the prinln call. The given string is printed out
      as the message.
   */
   private void printMessage(String msg, PlayerNode frontNode) {
      PlayerNode current = frontNode;
      
      // Check to see which Linked list it's handed
      if (frontNode == thiefFront) {
         // Prints out the message if there is a winner.
         if (frontNode.next == frontNode) {
            System.out.println("    " + frontNode.name + msg);
         }
         // Walk the list printing out who is stealing from whom.
         else {            
            while (current.next != frontNode 
                   && current.next != null) {               
               System.out.println("    " 
                     + current.name + msg + current.next.name);
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
      if (frontNode == null || name == null) {
         return false;
      }
      
      PlayerNode current = frontNode;      
      // String variable added for readability
      String currentName = current.name.toLowerCase();
      
      // Flag for checking if all nodes have
      // been checked.
      boolean checkedAll = false;
      
      while (!checkedAll) {
         if (currentName.equals(name.toLowerCase())) {
            return true;
         }
         else {
            current = current.next;
            //  For stealList       for thiefRing
            if (current == null || current == frontNode) {
               checkedAll = true;
            }
            else {
               currentName = current.name.toLowerCase();
            }
         }
      }
      
      return false;
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
