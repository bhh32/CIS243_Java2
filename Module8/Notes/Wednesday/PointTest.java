import java.awt.Point;

public class PointTest {
   public static void main(String[] args) {
      Point x = new Point(2, 8);
      System.out.println("x: " + x);
      change(x);
      System.out.println("now x: " + x);
      
      x = change2(x);
      System.out.println("new x: " + x);
   }
   
   public static void change(Point p) {
      p.translate(3, 5);
      System.out.println("p: " + p);
      p = new Point(-7, -14);
      System.out.println("now p: " + p);
   }
   
   public static Point change2(Point p) {
      p.translate(3, 5);
      System.out.println("p: " + p);
      p = new Point(-7, -14);
      System.out.println("now p: " + p);
      
      return p;
   }
}