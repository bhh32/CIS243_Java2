import java.util.Scanner;
import java.io.PrintStream;

/*
   Bryan Hyland
   CIS143 Java2
   29May19
   
   This class manages the Statements and Guesses
   of the Hot and Cold game in a Binary Tree. It
   also handles saving the tree to a file when the
   game loses, and the player says they want to.
*/

public class InfoTree {
   private InfoNode overallRoot;
   
   /*
      This constructor sets overallRoot to a
      single guess node.
   */
   public InfoTree(String object) {      
      overallRoot = new InfoNode(object);
   }
   
   /*
      This calls the buildTree recursive method
      to build the statement/guess tree using
      the Scanner parameter.
   */
   public InfoTree(Scanner input) {
      overallRoot = buildTree(input, null);
   }
   
   // Builds the statement/guess tree.
   private InfoNode buildTree(Scanner input, InfoNode node) {
      // If the scanner stream is empty
      if (!input.hasNext()) {
         return null;
      }
      
      // Get the type (statement or guess) of node
      String type = input.nextLine();
      // Get the actual statement or guess
      String phrase = input.nextLine();
      
      // Build the tree in preorder
      if (type.equals("S:")) {
         node = new InfoNode(phrase);
         node.hot = buildTree(input, node.hot);
         node.cold = buildTree(input, node.cold);
      }
      
      // Set the guesses as leaves
      if (type.equals("G:")) {
         node = new InfoNode(phrase);
      }
      
      return node;
   }
   
   // Gatekeeper for the saveGame recursive method
   public void saveGame(PrintStream output) {
      saveGame(output, overallRoot);
   }
   
   // Uses the PrintStream to print the current statement tree
   // to a file.
   private void saveGame(PrintStream output, InfoNode root) {
      if (root == null) {
         return;
      }
      
      // Save the guesses
      if (root.hot == null && root.cold == null) {
         output.println("G:");
         output.println(root.data);
      }
      // Save the hot only statements
      else if (root.hot != null && root.cold == null) {
         output.println("S:");
         output.println(root.data);
         saveGame(output, root.hot);
      }
      // Save the cold only statements
      else if (root.hot == null && root.cold != null) {
         output.println("S:");
         output.println(root.data);
         saveGame(output, root.cold);
      }
      // Save the full statements
      else {
         output.println("S:");
         output.println(root.data);
         saveGame(output, root.hot);
         saveGame(output, root.cold);
      }
   }
   
   // Gatekeeper for the playGame recursive method
   public void playGame(UserInteraction user) {
      overallRoot = playGame(user, null, overallRoot);
   }
   
   /*
      Walks the tree asking statements and guessing guesses.
      Uses UserInteraction to ask what it was if it loses and
      then adds that object to the tree in the correct place.
      Uses the previous node and the current node to determine
      placement in the tree.
   */
   public InfoNode playGame(UserInteraction user, 
         InfoNode prevNode, InfoNode node) {
      // No current correct guess
      if (node == null) {
         user.println("What was your answer?");
         String object = user.nextLine();
         user.println("What is a statement that distinguishes "
            + "it from my guess?");
         String statement = user.nextLine();
         user.println("Is that statement hot or cold for the object?");
         String hotCold = user.nextLine();
         
         // Creates a new statement node
         node = new InfoNode(statement);
         
         // Checks which side the new guess should go.
         if (hotCold.equals("hot")) {
            node.hot = new InfoNode(object);
         }
         else {
            node.cold = new InfoNode(object);
         }
      }
      else {
         // Boolean to report hot or cold
         boolean isHot = false;
         
         // End Game Condition
         if (node.hot == null && node.cold == null) {
            user.print("Is it a " + node.data + " ");
            isHot = user.nextBoolean();
            
            // If the guess was correct print the win
            // message and return the current node.
            if (isHot) {
               user.println("I Won! Thanks for the game!");
               return node;
            }
            // If the guess was wrong...
            else {
               // Recurse to get a new statement node and
               // its corresponding hot or cold guess.
               InfoNode newStatement = playGame(user, null, null);
               
               // Put the current node on the correct
               // side of the new statement.
               if (newStatement.hot != null) {
                  newStatement.cold = node;
               }
               else {
                  newStatement.hot = node;
               }
               
               // Ensure this isn't the overallRoot node.
               if (prevNode != null) {
                  // Check which side the new statement
                  // should be on and return the previous
                  // node.
                  if (prevNode.hot == node) {
                     prevNode.hot = newStatement;
                  }
                  else {
                     prevNode.cold = newStatement;
                  }
                  
                  return prevNode;
               }
               // If it is the overallRoot node
               // set the new statement as the
               // root.
               else {
                  node = newStatement;
                  return node;
               }
            }
         }
         
         // Print the statement/guess and ask
         // if it is hot or cold.
         user.print(node.data + " ");
         isHot = user.nextBoolean();
         
         // If it's hot...
         if (isHot) {
            // Recurse down the hot branch
            node = playGame(user, node, node.hot);
            
            // If this isn't the overallRoot node...
            if (prevNode != null) {
               // Set this node back to the correct side
               // if needed.
               if (prevNode.hot != node) {
                  prevNode.cold = node;
               }
               
               // Set the node back to the previous node.
               node = prevNode;
            }
         }
         // Do the same as above just to the cold side.
         else {
            node = playGame(user, node, node.cold);
            
            if (prevNode != null) {
               if (node != prevNode.cold) {
                  prevNode.hot = node;
               }
               
               node = prevNode;
            }
         }
      }
      
      return node;
   }
   
   /*
      This is the class that holds
      the node data for the tree.
   */
   private static class InfoNode {
      public final String data;
      public InfoNode hot;
      public InfoNode cold;
      
      public InfoNode(String data) {
         this(data, null, null);
      }
      
      public InfoNode(String data, InfoNode hot, InfoNode cold) {
         this.data = data;
         this.hot = hot;
         this.cold = cold;
      }
   }
}
