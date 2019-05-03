import java.io.*;
import java.util.*;

public class Crawler {
   public static void main(String[] args) throws IOException {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("directory or file name: ");
      String name = keyboard.nextLine();
      File f = new File(name);
      if (!f.exists()) {
         System.out.println("That file does not exist");
      } else {
         print(f);
      }
   }
   
   // outputs the file and all of its substructure
   public static void print3Deep(File f) throws IOException {
      System.out.println(f.getCanonicalPath());
      
      if (f.isDirectory()) {
         
         File[] files = f.listFiles();
         //for (int i = 0; i < files.length; i++) {
            // do something with files[i]
         //}
         
         for (File file : files) {
            // do something with file
            System.out.println(file.getCanonicalPath());
            
            if (file.isDirectory()) {
               File[] files2 = file.listFiles();
               for (File file2 : files2) {
                   System.out.println(file2.getCanonicalPath());
                   
                   if (file2.isDirectory()) {
                      File[] files3 = file2.listFiles();
                      for (File file3 : files3) {
                         System.out.println(file3.getCanonicalPath());
                      }
                   }
               }
            }
         }
      }
   }
   
   
   // outputs the file and all of its substructure
   public static void print(File f) throws IOException {
      System.out.println(f.getCanonicalPath());
      
      if (f.isDirectory()) {
         File[] files = f.listFiles();         
         for (File file : files) {
            print(file);
         }
      }
   }
}