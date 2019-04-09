import java.util.Arrays;

public class AlphabetFrequency {
   // int to hold the number of letters in the english alphabet
   private static final int ALPHABET_COUNT = 26;
   // int const to hold a 0 for if something isn't found
   // mainly here for code readability
   private static final int NOT_FOUND = 0;
   
   // Holds the sorted String after manipulation
   private String data;
   // Holds the counts for each letter
   private int[] alphabet = new int[ALPHABET_COUNT];
   // Holds the sum of all of the letter counts
   private int size;
   
   public AlphabetFrequency(String data) {
      /* Check to see if the incoming String object
         is not null so operations can be done with
         it.
      */    
      if (data != null) {
         // Set this.data to a lowercase version of data
         this.data = data.toLowerCase();
         // Call the sortData utility method
         sortData();
         
         // Create ints for the index and iterator
         int idx = 0;
         int counter = 0;
         
         // Make sure that this.data isn't an empty String
         if (!this.data.isEmpty()) {
            while (counter < this.data.length()) { 
               // Set the current index              
               idx = this.data.charAt(counter) - 'a';
               // Add to the letter count for that index
               alphabet[idx]++;
               // Increase the counter
               counter++;               
            }
         } 
         
         // Call the calculateSize utility method        
         calculateSize();
      }
   }
   
   public int get(char letter) {
      // Make sure that the parameter is a letter      
      if (!Character.isLetter(letter)) {
         throw new IllegalArgumentException("Parameter passed "
            + "into get() was not a letter!");
      }
      
      // Set the letter to a lowercase version
      letter = Character.toLowerCase(letter);
      
      // Return the letter count for the indicated letter
      return alphabet[letter - 'a'];
   }
   
   public void set(char letter, int value) {
      /* 
         Ensure that the value is greater than 0
         and that the char is a letter.   
      */
      if (value < 0 | !Character.isLetter(letter)) {
         throw new IllegalArgumentException("You passed in a negative "
            + "value or a non-letter character to set()!");
      }
      
      // Set the letter to a lowercase version
      letter = Character.toLowerCase(letter);
      // Set the letter count to the value
      alphabet[letter - 'a'] = value;
      
      // Ensure value isn't 0
      if (value != 0) {
         // Check to see if data is null
         if (data == null) {
            // If data is null create a temp char array
            // the size of value.
            char[] temp = new char[value];
            
            // and populate the array with the letter value - 1 times
            for (int i = 0; i < temp.length; ++i) {
                  temp[i] = letter;
            }
               
            // Set data to a new String object with
            // temp as the value.
            data = new String(temp);
         }
         // If data isn't null...
         else {
            // add the letter to data value - 1 times
            for (int i = 0; i < value; ++i) {         
               data += letter;
            }
         }
      }
      // If value is 0...
      else {
         // Create a temp char array of size data.length()
         char[] temp = new char[data.length()];
         
         // Cycle though each element checking to see if
         // the element is not the same as letter
         for (int j = 0; j < data.length(); ++j) {
            if (data.charAt(j) != letter) {
               // if it isn't add it to the array
               temp[j] = data.charAt(j);
            }
         }
         
         // Set data to a new String object with the temp
         // array as the value.
         data = new String(temp);
      }
      
      // Sort data using the sortData utility method
      sortData();
      // Calculate the size using the calculateSize utility method
      calculateSize();
   }
   
   // Returns the size
   public int size() {
      return size;
   }
   
   // Returns true if the size is 0
   public boolean isEmpty() {
      return size == 0;
   }
   
   @Override
   public String toString() {
      // Create a temp String object
      String temp = "[";
      
      // Check to see if data.length() is greater than 0
      if (data.length() > 0) {
         // loop through to data.length() - 1 and add it to temp
         for (int i = 0; i < data.length() - 1; ++i) {
            temp += data.charAt(i);
         }
         
         // Add the last element + ] to temp
         temp += data.charAt(data.length() - 1) + "]";      
      }      
      // if data is an empty String add ] to temp 
      else {
         temp += "]";
      }
      
      return temp;
   }
   
   public AlphabetFrequency combineWith(AlphabetFrequency other) {
      if (other == null) {
         other = (AlphabetFrequency)other;
         other = new AlphabetFrequency("");
      }
      else if (!(other instanceof AlphabetFrequency)) {
         throw new IllegalArgumentException();
      }
      
      // Create a new AlphabetFrequency object with this and other combined
      AlphabetFrequency temp = new AlphabetFrequency(this.data.toString() 
         + other.data.toString());
      
      return temp;
   }
   
   public AlphabetFrequency minus(AlphabetFrequency other) {
      if (other == null) {
         other = (AlphabetFrequency) other;
         other = new AlphabetFrequency("");
      }
      else if (!(other instanceof AlphabetFrequency)) { 
         throw new IllegalArgumentException();
      }
   
      // Create an essentially empty AlphabetFrequency temp object
      AlphabetFrequency temp = new AlphabetFrequency("");
            
      // Subtract other.alphabet counts from this.alphabet counts
      for (int i = 0; i < ALPHABET_COUNT; ++i) {
         temp.alphabet[i] = this.alphabet[i] - other.alphabet[i];
         
         // Return null if the operation resulted in a negative
         if (temp.alphabet[i] < 0) {
            return null;
         }
      }
      
      // Loop for each letter
      for (int i = 0; i < ALPHABET_COUNT; ++i) {
         // Loop for each letter count
         for (int j = 0; j < temp.alphabet[i]; ++j) {
            // Adds the specified letter
            // temp.alphabet[i] (letter count) times
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
               default:
                  System.out.println("Something went wrong in minus()");
                  break;
            }
         }
      }
      // Sort temp.data using its sortData utility method
      temp.sortData();
      // Calculate temp.size using its calculateSize utility method
      temp.calculateSize();
      return temp;
   }
   
   /***** Utility Methods *****/
   private void sortData() {
      // Create a temporary String object
      String temp = " ";
      // Create a sorting array with size data.length()
      char[] sortingArr = new char[data.length()];
      
      // Loop through adding each character in data
      // to the sorting array
      for (int i = 0; i < data.length(); ++i) {
         sortingArr[i] = data.charAt(i);
      }
      
      // Use Arrays.sort to sort the sorting array
      Arrays.sort(sortingArr);
      
      // Loop through and add each letter char to the
      // temp string.
      for (int j = 0; j < sortingArr.length; ++j) {
         if (Character.isLetter(sortingArr[j])) {
            temp += sortingArr[j];
         }
      }
      
      // Take out all of the whitespace characters in temp
      temp = temp.replaceAll("\\s+", "");
      
      // Set data to temp
      data = temp;
   }
   
   private void calculateSize() {
      // Set size to 0
      size = 0;
      
      // set size to the sum of all of the letter counts
      for (int i = 0; i < ALPHABET_COUNT; ++i) {
         size += alphabet[i];
      }
   }
}