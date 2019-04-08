import java.util.Arrays;

public class AlphabetFrequency {
   private static final int ALPHABET_COUNT = 26;
   
   private StringBuilder data;
   private int[] alphabet = new int[ALPHABET_COUNT];
   private int size;
   
   public AlphabetFrequency(String data) {
      this.data = new StringBuilder(data);
      for (int c = 0; c < data.length(); ++c) {
         if (!Character.isLetter(data.charAt(c))) {
            this.data.setCharAt(c, ' ');
         }
      }
      this.data = new StringBuilder(this.data.toString().replaceAll("\\s", "").toLowerCase());
      //this.data = this.data.toLowerCase();
      
      
      
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
      
      for (int j : alphabet) {
         size += j;
      }
   }
   
   public int get(char letter) {
      int returnInt = 0;
   
      if (Character.isLetter(letter)) {
         switch(letter) {
               case 'a':
               case 'A':
                  returnInt = alphabet[0];
               break;
               case 'b':
               case 'B':
                  returnInt = alphabet[1];
               break;
               case 'c':
               case 'C':
                  returnInt = alphabet[2];
               break;
               case 'd':
               case 'D':
                  returnInt = alphabet[3];
               break;
               case 'e':
               case 'E':
                  returnInt = alphabet[4];
               break;
               case 'f':
               case 'F':
                  returnInt = alphabet[5];
               break;
               case 'g':
               case 'G':
                  returnInt = alphabet[6];
               break;
               case 'h':
               case 'H':
                  returnInt = alphabet[7];
               break;
               case 'i':
               case 'I':
                  returnInt = alphabet[8];
               break;
               case 'j':
               case 'J':
                  returnInt = alphabet[9];
               break;
               case 'k':
               case 'K':
                  returnInt = alphabet[10];
               break;
               case 'l':
               case 'L':
                  returnInt = alphabet[11];
               break;
               case 'm':
               case 'M':
                  returnInt = alphabet[12];
               break;
               case 'n':
               case 'N':
                  returnInt = alphabet[13];
               break;
               case 'o':
               case 'O':
                  returnInt = alphabet[14];
               break;
               case 'p':
               case 'P':
                  returnInt = alphabet[15];
               break;
               case 'q':
               case 'Q':
                  returnInt = alphabet[16];
               break;
               case 'r':
               case 'R':
                  returnInt = alphabet[17];
               break;
               case 's':
               case 'S':
                  returnInt = alphabet[18];
               break;
               case 't':
               case 'T':
                  returnInt = alphabet[19];
               break;
               case 'u':
               case 'U':
                  returnInt = alphabet[20];
               break;
               case 'v':
               case 'V':
                  returnInt = alphabet[21];
               break;
               case 'w':
               case 'W':
                  returnInt = alphabet[22];
               break;
               case 'x':
               case 'X':
                  returnInt = alphabet[23];
               break;
               case 'y':
               case 'Y':
                  returnInt = alphabet[24];
               break;
               case 'z':
               case 'Z':
                  returnInt = alphabet[25];
               break;                  
            }
         }
         else {
          throw new IllegalArgumentException("Something other than a letter was passed into get()");
      }
      return returnInt;
   }
   
   public void set(char letter, int value) {
      if (value < 0)
         throw new IllegalArgumentException("You passed in a negative value to set()!");
   
      if (Character.isLetter(letter)) {
         switch(letter) {
            case 'a':
            case 'A':
               alphabet[0] = value;
               break;
            case 'b':
            case 'B':
               alphabet[1] = value;
               break;
            case 'c':
            case 'C':
               alphabet[2] = value;
               break;
            case 'd':
            case 'D':
               alphabet[3] = value;
               break;
            case 'e':
            case 'E':
               alphabet[4] = value;
               break;
            case 'f':
            case 'F':
               alphabet[5] = value;
               break;
            case 'g':
            case 'G':
               alphabet[6] = value;
               break;
            case 'h':
            case 'H':
               alphabet[7] = value;
               break;
            case 'i':
            case 'I':
               alphabet[8] = value;
               break;
            case 'j':
            case 'J':
               alphabet[9] = value;
               break;
            case 'k':
            case 'K':
               alphabet[10] = value;
               break;
            case 'l':
            case 'L':
               alphabet[11] = value;
               break;
            case 'm':
            case 'M':
               alphabet[12] = value;
               break;
            case 'n':
            case 'N':
               alphabet[13] = value;
               break;
            case 'o':
            case 'O':
               alphabet[14] = value;
               break;
            case 'p':
            case 'P':
               alphabet[15] = value;
               break;
            case 'q':
            case 'Q':
               alphabet[16] = value;
               break;
            case 'r':
            case 'R':
               alphabet[17] = value;
               break;
            case 's':
            case 'S':
               alphabet[18] = value;
               break;
            case 't':
            case 'T':
               alphabet[19] = value;
               break;
            case 'u':
            case 'U':
               alphabet[20] = value;
               break;
            case 'v':
            case 'V':
               alphabet[21] = value;
               break;
            case 'w':
            case 'W':
               alphabet[22] = value;
               break;
            case 'x':
            case 'X':
               alphabet[23] = value;
               break;
            case 'y':
            case 'Y':
               alphabet[24] = value;
               break;
            case 'z':
            case 'Z':
               alphabet[25] = value;
               break;
         }
            
       }
       else {
             throw new IllegalArgumentException("Something other than a letter was passed into set()");   
       }
            
       // Adds up all of the counts and puts them into size
       for (int j : alphabet) {
             size += j;
       }
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
      // Create a return String object
      String retString = "[";
      // Create a char array so that the chars can be sorted
      char[] sorted = new char[data.length()];
      
      // Put each char in the string into the sorting array
      for (int i = 0; i < data.length(); ++i) {
         if (Character.isLetter(data.charAt(i))) {
            sorted[i] = data.charAt(i);
         }
      }
      
      // Sort the chars
      Arrays.sort(sorted);
      
      // Add the sorted chars to the return String object
      for (int j = 0; j < size - 1; ++j) {
         retString += sorted[j];
      }
      
      // Add the last char in the array to the return String object
      retString += sorted[size - 1] + "]";
      
      return retString.trim();
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
   
   // TEST
   public static void main(String[] args)
   {
      AlphabetFrequency af = new AlphabetFrequency("Bryan  12!");
      System.out.println(af.size());
      System.out.println(af);
   }
}