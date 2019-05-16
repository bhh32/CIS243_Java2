import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

/**
 Tests for Twister. Note this cannot check design or style,
 only basic correct behavior. The order of results is based on
 input dictionary order, as specified.

 @author argoc, based on W19 student tests
 @version 2019-02-24
 */
public class TwisterTest {

   // the provided test dictionaries must be in this directory
   private static List<String> makeListFromFile(String fileName) {
      List<String> list = new LinkedList<String>();
      try {
         Scanner scanner = new Scanner(new File(fileName));
         while (scanner.hasNext()) {
            list.add(scanner.next());
         }
      } catch (FileNotFoundException error) {
         fail("couldn't find the file used to test: "+fileName);
      }
      return Collections.unmodifiableList(list);
   }

   //quick way to check first, last, and number of lines are as expected
   private static void checkFirstLastNumberOfLines(Scanner scanner, String first, String last, int numberOfLines) {
      String string = scanner.nextLine();
      assertEquals("first line did not match",first, string);

      //advances to last line - if there is a wrong number of lines an exception gets thrown and the test fails
      for (int i = 2; i < numberOfLines; i++) {
         try {
            string += System.lineSeparator() + scanner.nextLine() ;
         } catch (java.util.NoSuchElementException nse) {
             throw new java.util.NoSuchElementException("ran out of lines @ line " + i 
                    + " input lines were " + string + System.lineSeparator() );
         }
      }
      try {
         string = scanner.nextLine();
      } catch (java.util.NoSuchElementException nse) {
         throw new java.util.NoSuchElementException("ran out of lines @ line " + numberOfLines 
                 + " input lines were " + string + System.lineSeparator());
      }
      assertEquals("last line did not match",last, string);
      if (scanner.hasNextLine()) { // make sure it was the last line
         fail("should not be any more lines of output");
      }
   }

   @Test(expected = IllegalArgumentException.class)
   public void testNegativeMax() {
      List<String> list = new ArrayList<String>();
      list.add("this");
      Twister twister = new Twister(list);
      twister.findAndPrint(System.out, "test", -1);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testNullPhrase() {
      List<String> list = new ArrayList<String>();
      list.add("this");
      Twister twister = new Twister(list);
      twister.findAndPrint(System.out, null, 0);
   }

   @Test public void testDictionaryUnchanged() {
      List<String> list = new ArrayList<String>();
      list.add("this");
      list.add("is");
      list.add("a");
      list.add("test");
      List<String> backStop = new ArrayList<String>();
      backStop.addAll(list);

      Twister twister = new Twister(list);
      twister.findAndPrint(System.out, "testthis", 0);
      assertEquals("original list should not have been changed by running the twister", list, backStop);
   }

   @Test public void testDictionaryOrderKept() {
      List<String> list = new ArrayList<String>();
      list.add("this");
      list.add("is");
      list.add("a");
      list.add("test");
      List<String> backStop = new ArrayList<String>();
      backStop.addAll(list);

      Twister twister = new Twister(list);
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(output);
      twister.findAndPrint(print, "testthis", 0);
      Scanner scanner = new Scanner(output.toString());
      checkFirstLastNumberOfLines(scanner, "[this, test]", "[test, this]", 2);
   }

   @Test public void testDeepMatch() {
      String[] abcd = {"a", "b", "c", "d"};
      Twister twister = new Twister(Arrays.asList(abcd));

      ByteArrayOutputStream output = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(output);
      twister.findAndPrint(print, "d c b a", 4);

      Scanner scanner = new Scanner(output.toString());
      checkFirstLastNumberOfLines(scanner, "[a, b, c, d]", "[d, c, b, a]", 24);
   }

   @Test public void testNoAnagramsToFind() {
      Twister twister = new Twister(makeListFromFile("dict1.txt"));

      ByteArrayOutputStream output = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(output);
      twister.findAndPrint(print, "john kerry", 0);

      assertEquals("there should be no output","",output.toString());
   }

   @Test public void testNoLettersInPhrase() {
      Twister twister = new Twister(makeListFromFile("dict2.txt"));

      ByteArrayOutputStream output = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(output);
      twister.findAndPrint(print, "1234", 0);

      assertEquals("no numbers gives empty set", "[]"+System.lineSeparator(), output.toString());
   }

   @Test public void testWordsMoreThanOnce() {
      Twister twister = new Twister(makeListFromFile("dict2.txt"));

      ByteArrayOutputStream output = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(output);
      twister.findAndPrint(print, "a an a an a an", 0);

      Scanner scanner = new Scanner(output.toString());
      checkFirstLastNumberOfLines(scanner, "[a, a, a, an, an, an]", "[an, an, an, a, a, a]", 20);
   }

   @Test public void testMax0DifferentNumberOfWordsAllowed() {
      Twister twister = new Twister(makeListFromFile("dict2.txt"));

      ByteArrayOutputStream output = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(output);
      twister.findAndPrint(print, "marty stepp", 0);

      Scanner scanner = new Scanner(output.toString());
      checkFirstLastNumberOfLines(scanner, "[empty, parts]", "[try, step, map]", 22);
   }

   // hairbrush results at 3, 4, and max
   private static String hairbrushResults3 = "[bar, huh, sir]"
      + System.lineSeparator() + "[bar, sir, huh]"
      + System.lineSeparator() + "[briar, hush]"
      + System.lineSeparator() + "[huh, bar, sir]"
      + System.lineSeparator() + "[huh, sir, bar]"
      + System.lineSeparator() + "[hush, briar]"
      + System.lineSeparator() + "[sir, bar, huh]"
      + System.lineSeparator() + "[sir, huh, bar]"
      + System.lineSeparator();

   private static String hairbrushResults2 = "[briar, hush]"
       + System.lineSeparator() + "[hush, briar]"
       + System.lineSeparator();

   private static String hairbrushResults1 = "";

    //Checks the hairbrush phrase with a 0 max
   @Test public void hairbrushMax0() {
      Twister twister = new Twister(makeListFromFile("dict1.txt"));

      ByteArrayOutputStream output = new ByteArrayOutputStream();
      PrintStream printout = new PrintStream(output);

      twister.findAndPrint(printout, "hairbrush", 0);

      assertEquals("hairbrush results not good at max",hairbrushResults3,
          output.toString());
     }

     @Test public void hairbrushMax4() {
        Twister twister = new Twister(makeListFromFile("dict1.txt"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printout = new PrintStream(output);

        twister.findAndPrint(printout, "hairbrush", 4);

        assertEquals("hairbrush results not good at 4",hairbrushResults3,
            output.toString());
     }

     //Check the hairbrush phrase with a 2 max
     @Test public void hairbrushMax2() {
        Twister twister = new Twister(makeListFromFile("dict1.txt"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printout = new PrintStream(output);

        twister.findAndPrint(printout, "hairbrush", 2);

        assertEquals("hairbrush results not good at 2",hairbrushResults2,
            output.toString());
     }

     //Check the hairbrush phrase with a 1 max
     @Test public void hairbrushMax1() {
        Twister twister = new Twister(makeListFromFile("dict1.txt"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printout = new PrintStream(output);

        twister.findAndPrint(printout, "hairbrush", 1);

        assertEquals("hairbrush results not good at 1",hairbrushResults1,
            output.toString());
     }

    //Checks the "eleven plus two" phrase with a 0 max
    @Test public void testElevenPlusTwo() {
      Twister twister = new Twister(makeListFromFile("eleven.txt"));

      ByteArrayOutputStream output = new ByteArrayOutputStream();
      PrintStream print = new PrintStream(output);

      twister.findAndPrint(print, "eleven plus two", 0);

      assertEquals("[one, plus, twelve]"
          + System.lineSeparator() + "[one, twelve, plus]"
          + System.lineSeparator() + "[plus, one, twelve]"
          + System.lineSeparator() + "[plus, twelve, one]"
          + System.lineSeparator() + "[twelve, one, plus]"
          + System.lineSeparator() + "[twelve, plus, one]"
          + System.lineSeparator(),
          output.toString());
     }
}
