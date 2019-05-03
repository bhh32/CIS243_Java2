import java.awt.*;

// CIS 143 S19 Module 5, Recursion
// Amelia Garripoli 2019-05-02
//
// Draws a recursive Mickey, dubbed "Mickey Moose" by Think Java
// original concept from here: https://books.trinket.io/thinkjava/appendix-b.html
// using Building Java Program's DisplayPanel

public class MickeyMoose {

   public static void main(String[] args) {
      DrawingPanel panel = new DrawingPanel(800,800);
      
      Graphics pen = panel.getGraphics();
      
      drawMickey(pen);
   }

   // draw Mickey Moose (fractal Mickey)
   public static void drawMickey(Graphics pen) {
      _drawMickey(pen, 200, 300, 400);
   }
   
   // draw fractal mickey with the head defined by x, y, size   
   private static void _drawMickey(Graphics pen, int x, int y, int size) {
   
      // Mickey's face:
   	pen.fillOval(x, y, size, size);
   
   	int half = size / 2; // ears are half the size
      int quarter = size / 4; // and 1/4 outside/up the face
      
      if (half > 10) {
         // left ear, offset to the left and up, half the size
         _drawMickey(pen, x - quarter, y - quarter, half);
         // right ear, offset to the right and up, half the size
         _drawMickey(pen, x + half + quarter, y - quarter, half);
      }
   }

}