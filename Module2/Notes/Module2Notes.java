import java.util.*;

public class Module2Notes {
   // Interfaces, Stacks, and Queues
      
   // Initial Testing
   public static void main(String[] args) {
      // Two Concrete Classes of List Interface
      List<String> words1 = new ArrayList<>();
      List<String> words2 = new LinkedList<>();
      
      words1.add("Hello!");
      words2.add("Hello!");
      
      printStringList(words1);
      printStringList(words2);
      
      addWords(words1, "this", "is", "Java");
      addWords(words2, "this", "is", "Java");
      
      //printStringList(words1);
      //printStringList(words2);
      
      Iterator<String> iter = words1.iterator();
      while (iter.hasNext()) {
         String word = iter.next();
         System.out.println(word);
      }
      
      // Why we have remove
      try {
         for (String word : words2) {
            System.out.println(word);
            if (word.equals("this")) {
               words2.remove("this"); // if you remove is, get wrong result
            }
         }
      }
      catch (ConcurrentModificationException cme) {
         cme.printStackTrace();
      }
      
      iter = words2.iterator();
      while(iter.hasNext()) {
         String word = iter.next();
         System.out.println(word);
         
         if (word.equals("is")) {
            iter.remove(); // this works. note "this" is still removed even though exception was thrown
         }
         
         printStringList(words2);
      }
   }
   
   public static void addWords(List<String> words, String ... word) {
      //Using forEach Loop
      for (String w : word) {
         words.add(w);
      }
      
   }
   
   public static void printStringList(List<String> list) {
      System.out.println(list);
   }
}