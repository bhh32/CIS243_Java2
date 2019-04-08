import java.util.Arrays;

public class AlphabetFrequency {
   private static final int ALPHABET_COUNT = 26;
   
   private String data;
   private int[] alphabet = new int[ALPHABET_COUNT];
   private int size;
   
   public AlphabetFrequency(String data) {
      this.data = data.toLowerCase();
      sortData();
      
      for (int i = 0; i < this.data.length(); ++i) {
         if(Character.isLetter(this.data.charAt(i))) {
            switch(data.charAt(i)) {
               case 'a':
               case 'A':
                  alphabet[0]++;
               break;
               case 'b':
               case 'B':
                  alphabet[1]++;
               break;
               case 'c':
               case 'C':
                  alphabet[2]++;
               break;
               case 'd':
               case 'D':
                  alphabet[3]++;
               break;
               case 'e':
               case 'E':
                  alphabet[4]++;
               break;
               case 'f':
               case 'F':
                  alphabet[5]++;
               break;
               case 'g':
               case 'G':
                  alphabet[6]++;
               break;
               case 'h':
               case 'H':
                  alphabet[7]++;
               break;
               case 'i':
               case 'I':
                  alphabet[8]++;
               break;
               case 'j':
               case 'J':
                  alphabet[9]++;
               break;
               case 'k':
               case 'K':
                  alphabet[10]++;
               break;
               case 'l':
               case 'L':
                  alphabet[11]++;
               break;
               case 'm':
               case 'M':
                  alphabet[12]++;
               break;
               case 'n':
               case 'N':
                  alphabet[13]++;
               break;
               case 'o':
               case 'O':
                  alphabet[14]++;
               break;
               case 'p':
               case 'P':
                  alphabet[15]++;
               break;
               case 'q':
               case 'Q':
                  alphabet[16]++;
               break;
               case 'r':
               case 'R':
                  alphabet[17]++;
               break;
               case 's':
               case 'S':
                  alphabet[18]++;
               break;
               case 't':
               case 'T':
                  alphabet[19]++;
               break;
               case 'u':
               case 'U':
                  alphabet[20]++;
               break;
               case 'v':
               case 'V':
                  alphabet[21]++;
               break;
               case 'w':
               case 'W':
                  alphabet[22]++;
               break;
               case 'x':
               case 'X':
                  alphabet[23]++;
               break;
               case 'y':
               case 'Y':
                  alphabet[24]++;
               break;
               case 'z':
               case 'Z':
                  alphabet[25]++;
               break;
            }
         }
      }
      
      calculateSize();
   }
   
   public int get(char letter) {
      // Make sure that the parameter is a letter      
      if(!Character.isLetter(letter)) {
         throw new IllegalArgumentException("Parameter passed into get() was not a letter!");
      }
      letter = Character.toLowerCase(letter);
      int idx = 0;
      for (int i = 0; i < data.length(); ++i) {
         if (data.charAt(i) == letter) {   
            idx = data.charAt(i) - 'a';
         }
      }
      
      return alphabet[idx];
   }
   
   public void set(char letter, int value) {
      if (value < 0 | !Character.isLetter(letter)) {
         throw new IllegalArgumentException("You passed in a negative value or a non-letter character to set()!");
      }
      
      int idx = letter - 'a';
      
      alphabet[idx] = value;
      
      calculateSize();
   }
   
   // Returns the size
   public int size() {
      return size;
   }
   
   // Returns true if the size is 0
   public boolean isEmpty() {
      return size < 1;
   }
   
   @Override
   public String toString() {
      String temp = "[";
      
      for (int i = 0; i < data.length() - 1; ++i) {
         temp += data.charAt(i);
      }
      
      temp += data.charAt(data.length() - 1) + "]";      
      
      return temp;
   }
   
   public AlphabetFrequency combineWith(AlphabetFrequency other) {
      // Create a new AlphabetFrequency object with this and other combined
      AlphabetFrequency temp = new AlphabetFrequency(this.data.toString() + other.data.toString());
      
      return temp;
   }
   
   public AlphabetFrequency minus(AlphabetFrequency other) {
      AlphabetFrequency temp = new AlphabetFrequency("TempString");
      
      // Reset all counts to 0
      for (int c : temp.alphabet) {
         c = 0;
      }
      
      // Reset size to 0
      temp.size = 0;
      
      // Subtract other.alphabet[idx] from this.alphabet[idx]
      for (int i = 0; i < ALPHABET_COUNT; ++i) {
         temp.alphabet[i] = this.alphabet[i] - other.alphabet[i];
         
         // Return null if the operation resulted in a negative
         if (temp.alphabet[i] < 0) {
            return null;
         }
      }
      
      return temp;
   }
   
   private void sortData() {
      
      String temp = " ";
      char[] sortingArr = new char[data.length()];
      
      for (int i = 0; i < data.length(); ++i) {
         sortingArr[i] = data.charAt(i);
      }
      
      Arrays.sort(sortingArr);
      
      for (int j = 0; j < sortingArr.length; ++j) {
         if (Character.isLetter(sortingArr[j])) {
            temp += sortingArr[j];
         }
      }
      
      temp = temp.replaceAll("\\s+", "");
      
      data = temp;
   }
   
   private static void calculateSize() {
      for (int j : alphabet) {
         size += j;
      }
   }
   
   // TEST
   public static void main(String[] args)
   {
      AlphabetFrequency af = new AlphabetFrequency("Bryan  12!");
      System.out.println(af.size());
      System.out.println(af);
      System.out.println(af.get('B'));
   }
}