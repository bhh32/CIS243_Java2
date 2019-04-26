/*
   Bryan Hyland
   CIS143 - Java II
   26Apr19

   This Lab was to ensure that we can use
   Maps appropriately.
   
   *Note: Warning Given And Don't Understand Why
   
    ----jGRASP exec: javac -g -Xlint:unchecked LabWeek4.java
 LabWeek4.java:67: warning: [unchecked] unchecked call 
 to add(E) as a member of the raw type TreeSet
             temp.add(keyList.get(i)); (Line 80)
                     ^
   where E is a type-variable:
     E extends Object declared in class TreeSet
 1 warning
*/

import java.util.*;

public class LabWeek4 {
   // Tests whether a map<String, String> has unique values
   // for each key. Returns true if it is unique, returns
   // false otherwise.
   public static boolean isUnique(Map <String, String> map) {
      // If the map is null or empty it's considered
      // unique.
      if (map == null || map.isEmpty()) {
         return true;
      }
      
      // Put all of the values in an ArrayList
      List<String> valueList = new ArrayList<>(map.values());
      
      // Create a set for the values used already
      Set<String> usedValues = new TreeSet<String>();
      
      // Walk the array to test if the
      // usedValues set contains the value or not.
      for (String value : valueList) {
         if (!usedValues.contains(value)) {
            usedValues.add(value);
         }
         else {
            return false;
         }
      }
      
      return true;
   }
   
   public static Map<String, TreeSet<Integer>> 
                 invert(Map<Integer, String> map) {
      // Create two arraylists that hold the keys and values
      List<String> valueList = new ArrayList<String>(map.values());
      List<Integer> keyList = new ArrayList<Integer>(map.keySet());
      
      // Create a map to hold the inverted key, and set of Integers
      Map<String, TreeSet<Integer>> newMap = new 
                  TreeMap<String, TreeSet<Integer>>();
      
      // Walk through the value list...
      for (int i = 0; i < valueList.size(); ++i) {
         // ... test if the invert map already contains the new key
         if (!newMap.containsKey(valueList.get(i))) {
            // ... if not, create a new set of Integers
            TreeSet<Integer> newKeySet = new TreeSet<Integer>();
            // ... add the old key to the new set
            newKeySet.add(keyList.get(i));
            // ... create the old value as a key, and
            // the created set as a value in the map.
            newMap.put(valueList.get(i), newKeySet);
         }
         else {
            // ... if not, get the set from the key
            TreeSet temp = newMap.get(valueList.get(i));
            // ... add the value to the set
            temp.add(keyList.get(i));
         }         
      }
      
      return newMap;
   }
}