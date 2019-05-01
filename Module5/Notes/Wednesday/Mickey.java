import java.awt.*;

public class Mickey {
   public static void main(String[] args) {
      DrawingPanel panel = new DrawingPanel(800, 800);
      
      Graphics pen = panel.getGraphics();
      
      drawMickey(pen);
   }
   
   public static void drawMickey(Graphics pen) {
      drawMickey(pen, 300, 300, 200);
   }
   
   // Draws fractal Mickey with the head defined by x, y, and diameter
   private static void drawMickey(Graphics pen, int x, int y, int diameter) {
      
      pen.fillOval(x, y, diameter, diameter);
      
      // Draw Ears If There's Enough Room
      if (diameter > 10) {
         diameter = diameter / 2;
         // Draw Left Ear
         drawMickey(pen, (int)Math.round(diameter * .4), (int)Math.rint(diameter * .4), diameter);
         //pen.fillOval(250, 250, 100, 100);
      
      // Draw Right Ear
      drawMickey(pen, (int)Math.round(diameter * .222), (int)Math.rint(diameter * .4), diameter);
      //pen.fillOval(450, 250, 100, 100);
      }
      
      
   }
}