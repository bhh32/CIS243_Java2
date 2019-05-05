import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LabWeek5Test {
   @Test
   public void testDigitDiffFirstNumLonger() {
      int num1 = 1234;
      int num2 =  244;
      
      int result = LabWeek5.digitDiff(num1, num2);
      
      assertEquals("The return should be 2.", 2, result);
   }
   
   @Test
   public void testDigitDiffSecondNumLonger() {
      int num1 =     1432;
      int num2 = 34532115;
      
      int result = LabWeek5.digitDiff(num1, num2);
      
      assertEquals("The return should be 8.", 8, result);
   }   
   
   @Test
   public void testDigitDiffSame() {
      int num1 = 123;
      int num2 = 123;
      
      int result = LabWeek5.digitDiff(num1, num2);
      
      assertEquals("The return should be 0.", 0, result);
   }
}
