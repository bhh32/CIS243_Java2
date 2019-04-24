// This program reads a Graphviz graph file of friendships and allows the user
// to find the distance between two people in the graph.

import java.io.*;
import java.util.*;

public class Friends {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the cse143 friend finder.");
        Scanner input = new Scanner(new File("friends.dot"));
        Map<String, Set<String>> friends = readFile(input);
        Scanner console = new Scanner(System.in);
        System.out.print("starting name? ");
        String name1 = console.next();
        if (!friends.containsKey(name1)) {
            System.out.println("That person is not in the data file.");
        } else {
            System.out.print("target name? ");
            String name2 = console.next();
            showMatches(friends, name1, name2);
        }
    }

    // pre : input is open and contains a legal Graphviz file of friendship
    //       connections where each friend is listed as a single token
    // post: returns a map that contains for each person the set of friends for
    //       that person
    public static Map<String, Set<String>> readFile(Scanner input) {
        Map<String, Set<String>> friends = new TreeMap<String, Set<String>>();
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (line.contains("--")) {
                Scanner lineData = new Scanner(line);
                String name1 = lineData.next();
                lineData.next();  // this skips the "--" token
                String name2 = lineData.next();
                addTo(friends, name1, name2);
                addTo(friends, name2, name1);
            }
        }
        return friends;
    }

    // post: computes the distance between two people, printing the various
    //       lists of friends with their distance from name1
    public static void showMatches(Map<String, Set<String>> friends, 
                                   String name1, String name2) {
    
     Set<String> alreadySeen = new TreeSet<String>();
     Set<String> currentGroup = friends.get(name1);
     alreadySeen.add(name1); // Don't revist name1
     int dist = 1;
      
      while (!currentGroup.contains(name2) && !currentGroup.isEmpty()) {
      dist++;
      Set<String> nextGroup = new TreeSet<String>();
      
      for (String friend : currentGroup) {
         nextGroup.addAll(friends.get(friend)); // friends of friends
      }
      alreadySeen.addAll(currentGroup);
      nextGroup.removeAll(alreadySeen); // Remove friends already seen
      currentGroup = nextGroup;
     }
     
     if (!currentGroup.isEmpty()) {
     System.out.println(name1 + " is " + dist + " from " + name2);
     }
     else {
      System.out.println(name1 + " has no introduction to " + name2);
     }
    }

    // post: records a friendship for name1.  If name1 is not in the map, a new
    //       set is added to the map
    public static void addTo(Map<String, Set<String>> friends, String name1, 
                             String name2) {
                               
        Set<String> myFriends;
        if (!friends.containsKey(name1)) {
           myFriends = new TreeSet<String>();
           friends.put(name1, myFriends);
        }
        else {
         myFriends = friends.get(name1);
        }
        
        myFriends.add(name2);
    }  
}