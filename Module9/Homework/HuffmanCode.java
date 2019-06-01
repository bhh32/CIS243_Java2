import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.PrintStream;

public class HuffmanCode {
   private static char defaultChar = '\0';
   private HuffmanNode overallRoot;
   
   public HuffmanCode(int[] frequencies) {
      // Create the priority queue
      Queue<HuffmanNode> treeQ = new PriorityQueue<HuffmanNode>();
      
      // Walk the queue adding new HuffmanNodes
      for (int i = 0; i < frequencies.length; ++i) {
         // Create a new HuffmanNode if the frequency isn't 0
         if (frequencies[i] != 0) {
            HuffmanNode tempNode = new HuffmanNode((char)i, frequencies[i]);
            treeQ.add(tempNode);
         }
      }
      
      // Set the overallRoot PriorityQueue element that is left
      // after all of the nodes have been processed.
      overallRoot = buildTree(treeQ);
      
      System.out.println();
   }
     
   public HuffmanCode(Scanner input) {
      /*
         This constructor should initialize a new HuffmanCode 
         object by reading in a previously constructed code from 
         a .code file. You may assume the Scanner is not null and 
         is attached to a legal, existing file in standard format. 
         Make sure to read entire lines of input using calls on 
         Scanner's nextLine.
      */
      overallRoot = buildTree(input, null, new HuffmanNode(defaultChar, -1));
   }
   
   // Builds the binary tree based on the elements in a PriorityQueue
   private HuffmanNode buildTree(Queue<HuffmanNode> currentQ) {            
      while(currentQ.size() > 1) {
         currentQ.add(buildTreeNodes(currentQ));
      }
      
      return currentQ.peek();
   }
   
   // Builds a tree off of a *.code file to decompress the file.
   private HuffmanNode buildTree(Scanner input, HuffmanNode node, HuffmanNode root) {
      if (!input.hasNextLine()) {
         return null;
      }
      
      // Set the node to the root so it can be appended
      node = root;
      
      // Get the data char and the path code
      char chara = (char) Integer.parseInt(input.nextLine());
      String code = input.nextLine();
      
      // Create a node holding the information
      HuffmanNode tempNode = new HuffmanNode(chara, -1);
      
      // Check to see if it's a single digit code
      if (code.length() > 1) {
         // Walk the code minus the last digit to add the
         // defaultChar nodes in the right place.
         for (int i = 0; i < code.length() - 1; ++i) {
            if(code.charAt(i) == '0') {
               if (node.zero == null) {
                  node.zero = new HuffmanNode(defaultChar, -1);
               }
               node = node.zero;
            }
            else if (code.charAt(i) == '1') {
               if (node.one == null) {
                  node.one = new HuffmanNode(defaultChar, -1);
               }
               node = node.one;
           }           
        }
        
        // Place the new node in the right position
        if (code.charAt(code.length() - 1) == '0') {
            node.zero = tempNode;
        }
        else if (code.charAt(code.length() - 1) == '1') {
           node.one = tempNode;
        }   
     }
     // If it is a single digit code place the
     // new node in the correct position.
     else {
         if (code.charAt(0) == '0') {
            node.zero = tempNode;
         }
         else if (code.charAt(0) == '1') {
            node.one = tempNode;
         }
     }
     
      // Recurse building the tree using node
      node = buildTree(input, node, root);
      
      return root;
   }
   
   // Processes the PriorityQueue Nodes, creating a tree based upon
   // the nodes frequencies. Returns the new "root node" that is 
   // created containing the two nodes on the zero and one paths.
   private HuffmanNode buildTreeNodes(Queue<HuffmanNode> currentQ) {
      HuffmanNode node1 = currentQ.poll();
      HuffmanNode node2 = currentQ.poll();
      int newFreq = node1.getFreq() + node2.getFreq();
      HuffmanNode connectionNode;
      
      if (node1.compareTo(node2) <= 0) {
         connectionNode = new HuffmanNode(defaultChar, newFreq, node1, node2);
      }
      else {
         connectionNode = new HuffmanNode(defaultChar, newFreq, node2, node1);
      }      
      
      return connectionNode;
   }
   
   // Gatekeeper for the private save method.
   public void save(PrintStream output) {
      save(output, new String(""), null, overallRoot);
   }
   
   // Prints the Code and ASCII character decimal in the output file
   private void save(PrintStream output, String path, 
                     HuffmanNode prevNode, HuffmanNode node) {
      // Add to the path String      
      if (prevNode != null) {
         if (prevNode.zero == node) {
            path += "0";
         }
         else if (prevNode.one == node) {
            path += "1";
         }
      }
      
      // Print the ASCII decimal code and path code to the
      // file.
      if (node.zero == null && node.one == null) {
         output.println((int) node.getData());
         output.println(path);
         path = "";
      }      
      else if (node.zero != null && node.one == null) {
         save(output, path, node, node.zero);
      }         
      else if (node.zero == null && node.one != null) {  
         save(output, path, node, node.one);
      }      
      else if (node.zero != null && node.one != null) {
         save(output, path, node, node.zero);
         save(output, path, node, node.one);
      }
   }
   
   // *NOTE: USE output.write() to print out the leaf!
   // *NOTE: Consider using loops instead of loops, but not required!
   public void translate(BitInputStream input, PrintStream output) {
      /*
         This method reads individual bits from the BitInputStream 
         and writes the corresponding characters to the output. It 
         reads until it reaches the end of the BitInputStream. You 
         may assume that the input stream contains a legal encoding 
         of characters for this tree's huffman code. You use the 
         huffman code currently specified in your instance (either 
         created by a constructor or loaded from a file by a 
         constructor). See the API for BitInputStream below for 
         information on how to access this stream. You may assume 
         the PrintStream is valid and writable.
      */
      String character = "";
      int count = 0;
      while (input.hasNextBit()) {
         char print = (char) input.nextBit();
         System.out.print(print);
      }
   }
   
   private class HuffmanNode implements Comparable<HuffmanNode> {
      private final char data;
      private final int freq;
      public HuffmanNode zero; // Left
      public HuffmanNode one;  // Right
      
      public HuffmanNode(char data, int freq) {
         this(data, freq, null, null);
      }
            
      public HuffmanNode(char data, int freq, HuffmanNode zero, 
                         HuffmanNode one) {
         this.data = data;
         this.freq = freq;
         this.zero = zero;
         this.one = one;
      }
      
      // For proper encapsulation
      public char getData() {
         return data;
      }
      
      public int getFreq() {
         return freq;
      }
      
      // Compares two HuffmanNodes based on their frequency
      @Override
      public int compareTo(HuffmanNode other) {
         if (other.freq > this.freq) {
            return -1;
         }
         else if (other.freq == this.freq) {
            return 0;
         }
         // If the other freq is less than this
         // return 1
         else {
            return 1;
         }
      }
   }
}
