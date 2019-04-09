/**
   AlphabetFrequency jUnit tests

	 @author agarripoli
	 @version S19

	 you can use these existing tests to check on your work;
	 and you should also consider and write additional tests
	 to verify that your AlphabetFrequency class meets the specification
	 in terms of the functionality of an instance of the class.

	 put your name as author of the test cases you add, individually.
	 add your tests at the start of the class below.
 */

// static imports let you call the static method directly
// without using the class name.
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Scanner;

public class AlphabetFrequencyTest {

  /************** Add your tests below this comment *******/
   @Test(expected = IllegalArgumentException.class)
   public void testGetNumberCharPassedAsArgument() {
      // Arrange
      AlphabetFrequency af = new AlphabetFrequency("RandomString");
      
      // Act
      af.get('2');
      
      // Assert
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void testGetSymbolCharPassedAsArgument() {
      // Arrange
      AlphabetFrequency af = new AlphabetFrequency("RandomString");
      
      // Act
      af.get('@');
      
      // Assert
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void testGetIntCastToCharPassedAsArgument() {
      // Arrange
      AlphabetFrequency af = new AlphabetFrequency("RandomString");
      
      // Act
      af.get((char)1);
      
      // Assert
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void testSetNumberCharPassedAsArgument() {
      // Arrange
      AlphabetFrequency af = new AlphabetFrequency("RandomString");
      
      // Act
      af.set('1', 2);
      
      // Assert
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void testSetSymbolCharPassedAsArgument() {
      // Arrange
      AlphabetFrequency af = new AlphabetFrequency("RandomString");
      
      // Act
      af.set('!', 5);
      
      // Assert
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void testSetNumberCastAsCharPassedAsArgument() {
      // Arrange
      AlphabetFrequency af = new AlphabetFrequency("RandomString");
      
      // Act
      af.set((char)12.4f, 0);
      
      // Assert
   }
   
   @Test(expected = IllegalArgumentException.class)
   public void testSetNegativeValuePassedAsArgument() {
      // Arrange
      AlphabetFrequency af = new AlphabetFrequency("RandomString");
      
      // Act
      af.set('g', -2);
      
      // Assert
   }
   
   @Test public void testSetNumberLargerThanSize() {
      // Arrange
      AlphabetFrequency af = new AlphabetFrequency("RandomString");
      int beforeSet = af.size();
      // Act
      af.set('g', af.size() + 1);
      int afterSet = beforeSet + 1;
      
      // Assert
      assertEquals("The size wasn't increased to " + beforeSet + 1, beforeSet + 1, afterSet); 
   }
   
	/************** Add your tests above this comment *******/

	/************** Provided Tests **************/
   // the tests below here pass on a correct implementation. They are not
   // sufficient to show your code is 100% correct! Consider what else
   // needs to be tested, and add your tests above where shown...

   // the tests below have Stage1, Stage2, and Stage3 in their names to
   // represent the design stages recommended. If you follow them, you should
   // aim to pass all Stage1 tests once you have coded Stage1, before moving
   // on to Stage2; you may also want to consider additional tests at that stage
   // before moving on. And so on, for Stage2 and Stage 3.

   /////////////////
   //// STAGE 1 ////
   /////////////////

   // input values for Stage1
   // we have 14 different inputs, and need to test each one's results on each 5 methods.
   // corresponding array entries are for corresponding expected results.
   private String[] stage1 =
   { "abc", "abcdefghijklmnopqrstuvwxyz", "hello", "Hello", "how are you?",
    "42", "ZZZ", "Do ALL CAPS bother you--when you read??",
    "xyzzy", ",,;^&@0123456789   --!!??", "", "   ", "foo BARbaz",
    "The.Rain.In.Spain.falls.MAINLY.in.THE.plain--!" };
   private String[] stage1ToString =
   { "[abc]", "[abcdefghijklmnopqrstuvwxyz]", "[ehllo]", "[ehllo]",
      "[aehooruwy]", "[]", "[zzz]", "[aaabcddeeehhllnooooprrstuuwyy]" ,
      "[xyyzz]", "[]", "[]", "[]",
      "[aabbfoorz]", "[aaaaaeefhhiiiiiillllmnnnnnnpprsstty]" };
   private int[] stage1Size = { 3, 26, 5, 5, 9, 0, 3, 29, 5, 0, 0, 0, 9, 35 };
   private boolean[] stage1Empty = {false, false, false, false, false, true, false, false, false, true, true, true, false, false };
   private int[][] stage1Counts = {
      { 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
      { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3 },
      { 3, 1, 1, 2, 3, 0, 0, 2, 0, 0, 0, 2, 0, 1, 4, 1, 0, 2, 1, 1, 2, 0, 1, 0, 2, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
      { 5, 0, 0, 0, 2, 1, 0, 2, 6, 0, 0, 4, 1, 6, 0, 2, 0, 1, 2, 2, 0, 0, 0, 0, 1, 0 }
   };

	@Test
	public void testFrequencyStage1Constructor() {
       // generally it's a bad idea to test more than one thing in a method,
       // but if I don't, this test will have 14*5 methods in it ... yikes
       for (int i=0; i<stage1.length; i++) {
          AlphabetFrequency frequency = new AlphabetFrequency(stage1[i]);
          // assert
          Assert.assertNotNull(frequency);
       }
       // if any exceptions are thrown, the test stops and fails immediately
       // (no further constructors are tried) that's why using the for loop
       // is a bad idea, with 14 separate tests you would see all the successes
       // & failures, not just the first failure & no later results.
   }

   @Test
	public void testFrequencyStage1Size() {
       // generally it's a bad idea to test more than one thing in a method,
       // but if I don't, this test will have 14*5 methods in it ... yikes
       for (int i=0; i<stage1.length; i++) {
          // arrange
          AlphabetFrequency frequency = new AlphabetFrequency(stage1[i]);
          // act
          int size = frequency.size();
          // assert
          Assert.assertEquals("did not get expected size",stage1Size[i],size);
       }
       // if any exceptions are thrown, or results are incorrect,
       // the test stops and fails immediately (no further checks are made)
       //  that's why using the for loop is a bad idea, with 14 separate
       // tests you would see all the successes & failures, not just the first
       // failure & no later results.
   }

   @Test
	public void testFrequencyStage1ToString() {
       // generally it's a bad idea to test more than one thing in a method,
       // but if I don't, this test will have 14*5 methods in it ... yikes
       for (int i=0; i<stage1.length; i++) {
          // arrange
          AlphabetFrequency frequency = new AlphabetFrequency(stage1[i]);
          // act
          String toString = frequency.toString();
          // assert
          Assert.assertEquals("did not get expected toString",stage1ToString[i],toString);
       }
       // if any exceptions are thrown, or results are incorrect,
       // the test stops and fails immediately (no further checks are made)
       //  that's why using the for loop is a bad idea, with 14 separate
       // tests you would see all the successes & failures, not just the first
       // failure & no later results.
   }

   @Test
	public void testFrequencyStage1Get() {
       // generally it's a bad idea to test more than one thing in a method,
       // but if I don't, this test will have 14*5 methods in it ... yikes
       for (int i=0; i<stage1.length; i++) {
          // arrange
          AlphabetFrequency frequency = new AlphabetFrequency(stage1[i]);
          for (char ch = 'a'; ch <= 'z'; ch++) {
                int correct = stage1Counts[i][ch-'a'];
                // act
                int countLower = frequency.get(ch);
                char upper = Character.toUpperCase(ch);
                int countUpper = frequency.get(upper);
                // assert
                Assert.assertEquals("incorrect count on "+ch,correct,countLower);
                Assert.assertEquals("incorrect count on "+upper,correct,countUpper);
          }
       }
       // if any exceptions are thrown, or results are incorrect,
       // the test stops and fails immediately (no further checks are made)
       //  that's why using the for loop is a bad idea, with 14 separate
       // tests you would see all the successes & failures, not just the first
       // failure & no later results.
   }

   @Test
	public void testFrequencyStage1Empty() {
       // generally it's a bad idea to test more than one thing in a method,
       // but if I don't, this test will have 14*5 methods in it ... yikes
       for (int i=0; i<stage1.length; i++) {
          // arrange
          AlphabetFrequency frequency = new AlphabetFrequency(stage1[i]);
          // act
          boolean empty = frequency.isEmpty();
          // assert
          Assert.assertEquals("did not get expected empty result",stage1Empty[i],empty);
       }
       // if any exceptions are thrown, or results are incorrect,
       // the test stops and fails immediately (no further checks are made)
       //  that's why using the for loop is a bad idea, with 14 separate
       // tests you would see all the successes & failures, not just the first
       // failure & no later results.
   }


   /////////////////
   //// STAGE 2 ////
   /////////////////

   // taking advantage of chars widening to ints here...
   int[][] stage2 = {
      { '\0', -1},  // first step checked has no set call...
      { 'g', 3 },
      { 'a', 2 },
      { 'h', 4 },
      { 'm', 1 },
      { 'g', 0 },
      { 'b', 2 },
      { 'z', 1 },
      { 'p', 4 },
      { 'z', 0 },
      { 'b', 0 },
      { 'a', 0 },
      { 'h', 0 },
      { 'p', 0 },
      { 'm', 0 },
      { 'G', 3 },
      { 'A', 2 },
      { 'H', 4 },
      { 'M', 1 }
   };

   int[] stage2Size = {
     0, 3, 5, 9, 10, 7, 9, 10, 14, 13, 11, 9, 5, 1, 0, 3, 5, 9, 10
   };

   String[] stage2ToString = {
     "[]","[ggg]","[aaggg]","[aaggghhhh]", "[aaggghhhhm]", "[aahhhhm]", "[aabbhhhhm]",
     "[aabbhhhhmz]", "[aabbhhhhmppppz]", "[aabbhhhhmpppp]", "[aahhhhmpppp]", "[hhhhmpppp]",
     "[mpppp]", "[m]", "[]", "[ggg]", "[aaggg]", "[aaggghhhh]", "[aaggghhhhm]"
   };

   boolean[] stage2Empty = {
      true, false, false, false, false, false, false, false, false, false, false, false,
      false, false, true, false, false, false, false
   };

   int[][] stage2Count = {
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 0, 0, 0, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 0, 0, 0, 0, 0, 3, 4, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 2, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 2, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
      { 2, 2, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
      { 2, 2, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 0, 0, 0, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 2, 0, 0, 0, 0, 0, 3, 4, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
   };

   @Test
   public void testFrequencyStage2Sets() {
       // generally it's a bad idea to test more than one thing in a method,
       // but if I don't, this test will have 18*5 methods in it ... yikes
       AlphabetFrequency frequency = new AlphabetFrequency("");
       checkFrequency("new AlphabetFrequency(\"\")",frequency,stage2Size[0],stage2ToString[0],stage2Empty[0],stage2Count[0]);

       for (int i=1; i<stage2.length; i++) {
          // arrange
          frequency.set((char)stage2[i][0],stage2[i][1]);
          // act and assert
          checkFrequency("set("+(char)stage2[i][0]+","+stage2[i][1]+")",frequency,stage2Size[i],stage2ToString[i],stage2Empty[i],stage2Count[i]);
       }
       // if any exceptions are thrown, or results are incorrect,
       // the test stops and fails immediately (no further checks are made)
       //  that's why using the for loop is a bad idea, with 14 separate
       // tests you would see all the successes & failures, not just the first
       // failure & no later results.
   }

   // generally not a good idea to check 29 different things in one test, but the explosion of tests
   // makes this ok. will stop at the first failure done this way.
   private void checkFrequency(String info, AlphabetFrequency frequency, int size, String toString, boolean empty, int[] counts) {
       Assert.assertEquals("toString not what was expected with "+info,toString,frequency.toString());
       Assert.assertEquals("size not what was expected with "+info,size,frequency.size());
       Assert.assertEquals("isEmpty not what was expected with "+info,empty,frequency.isEmpty());
       for (char ch = 'a'; ch <= 'z'; ch++) {
          Assert.assertEquals("incorrect count on "+ch+" with "+info,counts[ch-'a'],frequency.get(ch));
       }
   }

   /////////////////
   //// STAGE 3 ////
   /////////////////

   String[][] stage3 = {
      {
         "aaggghhhh",
         "ggg",
         "[aagggggghhhh] 12 false 2 0 0 0 0 0 6 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0",
         "[aahhhh] 6 false 2 0 0 0 0 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0",
         null
      }, {
         "hello",
         "",
         "[ehllo] 5 false 0 0 0 0 1 0 0 1 0 0 0 2 0 0 1 0 0 0 0 0 0 0 0 0 0 0",
         "[ehllo] 5 false 0 0 0 0 1 0 0 1 0 0 0 2 0 0 1 0 0 0 0 0 0 0 0 0 0 0",
         null,
      }, {
         "Four score and seven years ago",
         "See your greed!",
         "[aaacddeeeeeeeefggnnoooorrrrrssssuuvyy] 37 false 3 0 1 2 8 1 2 0 0 0 0 0 0 2 4 0 0 5 4 0 2 1 0 0 2 0",
         "[aaacfnnoorssv] 13 false 3 0 1 0 0 1 0 0 0 0 0 0 0 2 2 0 0 1 2 0 0 1 0 0 0 0",
         null,
      }, {
         "Mitt Romney",
         "inert",
         "[eeiimmnnorrttty] 15 false 0 0 0 0 2 0 0 0 2 0 0 0 2 2 1 0 0 2 0 3 0 0 0 0 1 0",
         "[mmoty] 5 false 0 0 0 0 0 0 0 0 0 0 0 0 2 0 1 0 0 0 0 1 0 0 0 0 1 0",
         null,
      }, {
         "Cellar Door",
         "Old Car Lore",
         "[aaccddeelllloooorrrr] 20 false 2 0 2 2 2 0 0 0 0 0 0 4 0 0 4 0 0 4 0 0 0 0 0 0 0 0",
         "[] 0 true 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0",
         "[] 0 true 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0",
      }, {
         "one day at a time",
         "a stich in time",
         "[aaaacdeeehiiiimmnnostttty] 25 false 4 0 1 1 3 0 0 1 4 0 0 0 2 2 1 0 0 0 1 4 0 0 0 0 1 0",
         null,
         null
      }
   };
   private static int FIRST_FREQUENCY = 0;
   private static int SECOND_FREQUENCY = 1;
   private static int ADD_RESULT = 2;
   private static int SUB12_RESULT = 3;
   private static int SUB21_RESULT = 4;

   @Test
   public void testFrequencyStage2Adds() {
       // generally it's a bad idea to test more than one thing in a method,
       // but if I don't, this test will have way too many methods...

       for (int step = 0; step < stage3.length; step++) {
          AlphabetFrequency frequency1 = new AlphabetFrequency(stage3[step][FIRST_FREQUENCY]);
          AlphabetFrequency frequency2 = new AlphabetFrequency(stage3[step][SECOND_FREQUENCY]);

          AlphabetFrequency add1and2 = frequency1.combineWith(frequency2);
          checkFrequency("combineWith("+frequency1+", "+frequency2+")",add1and2, stage3[step][ADD_RESULT]);

          AlphabetFrequency add2and1 = frequency2.combineWith(frequency1);
          checkFrequency("combineWith("+frequency2+", "+frequency1+")",add2and1, stage3[step][ADD_RESULT]); // addition is reflexive, same result
       }
       // if any exceptions are thrown, or results are incorrect,
       // the test stops and fails immediately (no further checks are made)
       //  that's why using the for loop is a bad idea, with 14 separate
       // tests you would see all the successes & failures, not just the first
       // failure & no later results.
   }

   @Test
   public void testFrequencyStage2Subs() {
       // generally it's a bad idea to test more than one thing in a method,
       // but if I don't, this test will have way too many methods...

       for (int step = 0; step < stage3.length; step++) {
          AlphabetFrequency frequency1 = new AlphabetFrequency(stage3[step][FIRST_FREQUENCY]);
          AlphabetFrequency frequency2 = new AlphabetFrequency(stage3[step][SECOND_FREQUENCY]);

          AlphabetFrequency sub1and2 = frequency1.minus(frequency2);
          checkFrequency("minus("+frequency1+", "+frequency2+")",sub1and2, stage3[step][SUB12_RESULT]);

          AlphabetFrequency sub2and1 = frequency2.minus(frequency1);
          checkFrequency("minus("+frequency2+", "+frequency1+")",sub2and1, stage3[step][SUB21_RESULT]);
       }
       // if any exceptions are thrown, or results are incorrect,
       // the test stops and fails immediately (no further checks are made)
       //  that's why using the for loop is a bad idea, with 14 separate
       // tests you would see all the successes & failures, not just the first
       // failure & no later results.
   }

   // because we make all of the checks at once, any failure hides other results...
   private void checkFrequency(String info, AlphabetFrequency result, String results) {
       if (results == null) {
           Assert.assertNull("need a null for "+info,result);
       } else {
          // assumes everything is present!
          Scanner scan = new Scanner(results);
          String toString = scan.next();
          int size = scan.nextInt();
          boolean empty = scan.nextBoolean();
          int[] counts = {
             scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(),
             scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(),
             scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(),
             scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(),
             scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(),
             scan.nextInt()
          };
          checkFrequency(info, result, size, toString, empty, counts);
      }
   }

}

