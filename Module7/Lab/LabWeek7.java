import java.util.*;

/*
   Bryan Hyland
   17May19
   Lab Week 7
   Worked on for 2 hours
*/

public class LabWeek7 {
   // Gatekeeper to the path workhorse. Rules out any bad values or 0,0 coords
   public static void path(int x, int y) {
      if (x < 0 || y < 0) {
         throw new IllegalArgumentException("Either the x or y coordinate is negative!");
      }

      if (x == 0 && y == 0) {
         System.out.println("");
      }
      else {
         path(x, y, 0, 0, new ArrayList<String>());
      }
   }

   // Prints direction list once destination has been reached. Uses recursion
   // to check all possible paths to the destination.
   private static void path(int destX, int destY, int currX, int currY, List<String> coordList) {
      // Check to make sure destination hasn't been reached
      if (currX >= destX && currY >= destY) {
         System.out.println(coordList);
      }

      // Calc dist to x coordinate
      int distX = destX - currX;
      // Calc dist to y coordinate
      int distY = destY - currY;
      // Calc total dist
      int dist = distX + distY;

      // Check to see if the total dist is greater than 0
      if (dist > 0) {
         // Check current x is less than destX
         if (currX < destX) {
            // Add east to the list
            coordList.add("E");
            // Check all coords after
            path(destX, destY, currX + 1, currY, coordList);
            // Remove east from the list
            coordList.remove(coordList.size() - 1);
         }

         // Check current y is less than destY
         if (currY < destY) {
            // Add north to the list
            coordList.add("N");
            // Check all coords after
            path(destX, destY, currX, currY + 1, coordList);
            // Remove north from the list
            coordList.remove(coordList.size() - 1);
         }
         
         // Check to see if the distances are the same
         if (distX == distY){
            // Add northeast to the list
            coordList.add("NE");
            // Check all coords after
            path(destX, destY, currX + 1, currY + 1, coordList);
            // Remove northeast from the list
            coordList.remove(coordList.size() - 1);
         }
      }
   }
}