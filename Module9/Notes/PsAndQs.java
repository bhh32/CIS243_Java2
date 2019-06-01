import java.util.*;

public class PsAndQs {
   public static void main(String[] args) {
      Queue<String> words = new PriorityQueue<String>();
      
      words.add("banana");
      words.add("apricot");
      words.add("avacado");
      words.add("orange");
      words.add("kiwi");
      System.out.println(words);
      while (!words.isEmpty()) {
         System.out.println(words.remove());
      }
      
      Queue<Player> players = new PriorityQueue<Player>();
      players.add(new Ninja("Bob", 30));
      players.add(new Pirate("Bill", 30));
      players.add(new Wookie("Chewie"));
      
      System.out.println(players);
      
      
   }
}