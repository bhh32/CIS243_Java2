import java.util.*;

public class SetListExample {
   public static void main(String[] args) {
      String[] words = { "To", "be", "or", "not",
                         "to", new String("be") };
                         
      List<String> list = new LinkedList<String>();
      // Populate the list
      buildIt(words, list);
      
      Set<String> set = new TreeSet<String>();
      // Populate the set
      buildIt(words, set);
      
      // Print them out
      System.out.println("(" + list.size() + ")" + list);
      System.out.println("(" + set.size() + ")" +set);
      
   }
   
   public static void buildIt(String[] words, Collection collection) {
      for (String word : words) {
         boolean found = collection.add(word);
         
         if (!found) {
            System.out.println(word + " was already in the set!");
         }
      }
   }
}