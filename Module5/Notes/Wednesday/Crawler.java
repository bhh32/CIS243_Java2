import java.io.*;
import java.util.*;

public class Crawler {
   public static void main(String[] args) throws IOException {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("directory or file name: ");
      String name = keyboard.nextLine();
      File f = new File(name);
      if (!f.exists()) {
         System.out.println("That file doesn't exist!");
      }
      else {
         print(f);
      }
   }
   
   public static void print(File f) throws IOException {
      System.out.println(f.getCanonicalPath());
      
      if (f.isDirectory()) {
      
         File[] files = f.listFiles();
         
         // Ok if is a collection
         for (File file : files) {
            print(file);
         }
      }
   }
}