import java.util.*;

public class LabWeek7 {
   public static void main(String[] args) {
      path(2, 1);
      /*
         Output: *Any Order
            [N, E, E]
            [NE, E]
            [E, N, E]
            [E, NE]
            [E, E, N]      
      */
   }
   
   public static void path(int x, int y) {
      if (x < 0 || y < 0) {
         throw new IllegalArgumentException("A negative value was passed in for either x or y, X: " + x + " Y: " + y);
      }
      
      
      if (x == 0 && y == 0) {
         System.out.println(new ArrayList<String>());
      }
      else {
         path(new Grid(x, y), 0, 0, new ArrayList<String>());
      }
   }
   
   private static void path(Grid grid, int startX, int startY, List<String> coordList) {
      // Base Case: start is the same as destination
      if (startX > grid.size().getX() && startY > grid.size().getY()) {
         System.out.println(coordList);
      }
      else {
         for (int xCoord = 0; xCoord <= grid.size().getX(); ++xCoord) {
            if (grid.safe(xCoord, grid.size().getY()) {
               coordList.add(
            }
         }
      }
   }
   
   // Modeled off of Board Class
   private class Grid {
      private Vector2 startCoords = new Vector2(); // 0, 0 starting point
      private Vector2 destCoords; // dest coords
      private int gridX; // grid x size
      private int gridY; // grid y size
      private static final int UNASSIGNED = 100; // Not a good coord
      
      public Grid(destX, destY) {
         
         
         if (destX < 0 || destY < 0) throw new IllegalArgumentException();
         
         destCoords = new Vector2(destX, destY);
         gridX = new int[destX];
         gridY = new int[destY];
         
         for (int i = 0; i < sizeX; ++i) {
            gridX[i] = UNASSIGNED;
         }
         
         for (int i = 0; i < sizeY; ++i) {
            gridY[i] = UNASSIGNED;
         }
      }
      
      public boolean closer(int xAxis, int yAxis) {
         xAxis++;
         yAxis++;
         
         // Make sure we're still in the grid bounds
         if (isOffGrid(xAxis, yAxis)) 
            throw new IllegalArgumentException("Off Grid!");
         
         // Check to make sure a move to the North is valid
         if (gridY[yAxis] != UNASSIGNED) {
            return false;
         }
         
         // Check to make sure a move to the East is valid
         if (gridX[xAxis] != UNASSIGNED) {
            return false;
         }
         
         // Check Avaiability to all directions
         int distX = 
      }
      
      // Returns true either axis is off the grid
      public boolean isOffGrid(int xAxis, int yAxis) {
         if (xAxis > gridX.length - 1 || yAxis > gridY.length)
            return true;
         else
            return false;
      }
      
      // Places the new coordiantes
      public void place(int xCoord, int yCoord) {
         if (xCoord + 1 < gridX.length)
            gridX[xCoord + 1] = yCoord + 1;
         
         if (yCoord + 1 < gridY.length)
            gridY[yCoord + 1] = xCoord + 1;
      }
      
      public void remove(int xCoord, int yCoord) {
         if (!legal(xCoord - 1, yCoord - 1) || gridY[yCoord - 1] != xCoord - 1
            || gridX[xCoord - 1] != yCoord - 1) {
            throw new IllegalArgumentException();
         }
         
         gridX[xCoord - 1] = UNASSIGNED;
         gridY[yCoord - 1] = UNASSIGNED;
      }
      
      // Returns true if the (x, y) coords are on the grid
      private boolean legal(int xCoord, int yCoord) {
         return xCoord >= 0 && xCoord < gridX.length
            && yCoord >= 0 && yCoord < gridY.length;
      }
      
      public Vector2 size() {
         return new Vector2(gridX.length, gridY.length);
      }
   }
   
   private class Vector2 {
      private int x;
      private int y;
      
      public Vector2() { this(0, 0); }
      public Vector2(int x, int y) {
         this.x = x;
         this.y = y;
      }
      
      public int getX() { return x; }
      public void setX(int x) { this.x = x; }
      public int getY() { return y; }
      public void setY(int y) { this.y = y; }
      
      @Override
      public String toString() {
         String retString = x + ", " + y;
         return retString;
      }
   }
}