import java.util.Arrays;

public class AlphabetFrequency {
   private static final int ALPHABET_COUNT = 26;
   private static final int NOT_FOUND = 0;
   
   private String data;
   private int[] alphabet = new int[ALPHABET_COUNT];
   private int size;
   
   public AlphabetFrequency(String data) {      
      if (data != null) {
         this.data = data.toLowerCase();
         sortData();
         
         int idx = 0;
         int counter = 0;
         if (!this.data.isEmpty()) {
            while (counter < this.data.length()) {               
               idx = this.data.charAt(counter) - 'a';
               alphabet[idx]++;
               counter++;               
            }
         }         
         calculateSize();
      }
   }
   
   public int get(char letter) {
      // Make sure that the parameter is a letter      
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException("Parameter passed into get() was not a letter!");
      }
      
      letter = Character.toLowerCase(letter);
      int idx = 0;
      int counter = 0;
      while (counter < data.length()) {
         if (data.charAt(counter) == letter) {  
            idx = (data.charAt(counter) - 'a');
            return alphabet[idx];
         }
         else {
            counter++;
         }
      }
      
      return NOT_FOUND;
   }
   
   public void set(char letter, int value) {
      if (value < 0 | !Character.isLetter(letter)) {
         throw new IllegalArgumentException("You passed in a negative value or a non-letter character to set()!");
      }
      
      char lowerCaseLetter = Character.toLowerCase(letter);
      alphabet[lowerCaseLetter - 'a'] = value;
      
      if (value != 0) {
         if (data == null) {
               char[] temp = new char[value];
               for (int i = 0; i < temp.length; ++i) {
                  temp[i] = lowerCaseLetter;
               }
               
               data = new String(temp);
         }
         else {
            for (int i = 0; i < value; ++i) {         
               data += lowerCaseLetter;
            }
         }
      }
      else {
         char[] temp = new char[data.length()];
         for (int j = 0; j < data.length(); ++j) {
            if (data.charAt(j) != lowerCaseLetter) {
               temp[j] = data.charAt(j);
            }
         }
         
         data = new String(temp);
      }
      
      sortData();      
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
      
      if (data.length() > 0) {
         for (int i = 0; i < data.length() - 1; ++i) {
            temp += data.charAt(i);
         }
         
         temp += data.charAt(data.length() - 1) + "]";      
      }
      else {
         temp += "]";
      }
      
      return temp;
   }
   
   public AlphabetFrequency combineWith(AlphabetFrequency other) {
      // Create a new AlphabetFrequency object with this and other combined
      AlphabetFrequency temp = new AlphabetFrequency(this.data.toString() + other.data.toString());
      
      return temp;
   }
   
   public AlphabetFrequency minus(AlphabetFrequency other) {
      AlphabetFrequency temp = new AlphabetFrequency("");
            
      // Subtract other.alphabet[idx] from this.alphabet[idx]
      for (int i = 0; i < ALPHABET_COUNT; ++i) {
         temp.alphabet[i] = this.alphabet[i] - other.alphabet[i];
         
         // Return null if the operation resulted in a negative
         if (temp.alphabet[i] < 0) {
            return null;
         }
      }
      
      for (int i = 0; i < ALPHABET_COUNT; ++i) {
         for (int j = 0; j < temp.alphabet[i]; ++j) {
            switch(i) {
               case 0:
                  temp.data += 'a';
                  break;
               case 1:
                  temp.data += 'b';
                  break;
               case 2:
                  temp.data += 'c';
                  break;
               case 3:
                  temp.data += 'd';
                  break;
               case 4:
                  temp.data += 'e';
                  break;
               case 5:
                  temp.data += 'f';
                  break;
               case 6:
                  temp.data += 'g';
                  break;
               case 7:
                  temp.data += 'h';
                  break;
               case 8:
                  temp.data += 'i';
                  break;
               case 9:
                  temp.data += 'j';
                  break;
               case 10:
                  temp.data += 'k';
                  break;
               case 11:
                  temp.data += 'l';
                  break;
               case 12:
                  temp.data += 'm';
                  break;
               case 13:
                  temp.data += 'n';
                  break;
               case 14:
                  temp.data += 'o';
                  break;
               case 15:
                  temp.data += 'p';
                  break;
               case 16:
                  temp.data += 'q';
                  break;
               case 17:
                  temp.data += 'r';
                  break;
               case 18:
                  temp.data += 's';
                  break;
               case 19:
                  temp.data += 't';
                  break;
               case 20:
                  temp.data += 'u';
                  break;
               case 21:
                  temp.data += 'v';
                  break;
               case 22:
                  temp.data += 'w';
                  break;
               case 23:
                  temp.data += 'x';
                  break;
               case 24:
                  temp.data += 'y';
                  break;
               case 25:
                  temp.data += 'z';
                  break;
            }
         }
      }
      temp.sortData();
      temp.calculateSize();
      return temp;
   }
   
   // Utility Methods
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
   
   private void calculateSize() {
      size = 0;
      for (int i = 0; i < ALPHABET_COUNT; ++i) {
         size += alphabet[i];
      }
   }
   
   // TEST
   public static void main(String[] args)
   {      
      AlphabetFrequency af = new AlphabetFrequency("Bryan 23@!");
      System.out.println(af.get('c'));
      System.out.println("Before set size: " + af.size());
      af.set('a', 5);
      System.out.println("Data after set: " + af);
      System.out.println("After set size: " + af.size());
      
   }
}