/* Bryan Hyland
   CIS143
   Piano Man (Week 2)
   12Apr19
   
   Implements the Piano class and 
   uses the Note class to play a
   tune. Simulates a 37 key (pianica)
   piano.
*/


public class Pianica implements Piano {
   public static final String KEYS =
       "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   
   // Frequency played at Concert A
   private static final int CONCERT_A = 440;
   // Added to pitch in playNote() 
   private static final int PITCH_CONSTANT = 24;
   // Array of the 37 notes  
   private Note[] notes = new Note[37];
   // Time after each tic call
   private int timePlaying;
   
   public double debugMath;
   
   public static void main(String[] args) {
      Piano p = new Pianica();
   }
   
   // Constructs the 37 notes that the pianica can play
   public Pianica() {
      for (int i = 0; i < notes.length; ++i) {
         debugMath = Math.pow((double)2, ((double)i - (double)PITCH_CONSTANT) / (double)12) * (double)CONCERT_A;
         //debugMath = ((double)CONCERT_A * Math.pow(2, (((double)i - (double)PITCH_CONSTANT) / (double)12)));
         notes[i] = new Note(debugMath);
      }
      
      // 440 × 2(i - 24) / 12
   } 
   
   // Adds 24 to the pitch to play the correct note
   // ignores notes not in the array.
   public void playNote(int pitch) {
      if (pitch + PITCH_CONSTANT >= 0 && pitch + PITCH_CONSTANT < KEYS.length()) {
         notes[pitch + PITCH_CONSTANT].play();
      }
   }
   
   // Returns true if the key is a valid key on the
   // pianica keyboard (KEYS), and false if it isn't.
   public boolean hasKey(char key) {
      for (int i = 0; i < KEYS.length(); ++i) {
         if (KEYS.charAt(i) == key) {
            return true;
         }
      }
      return false;
   }
   
   // If the key is valid it plays the note for that key
   // otherwise throws an IllegalArgumentException.
   public void playKey(char key) {
      if (hasKey(key)) {
         notes[KEYS.indexOf(key)].play();
      }
      else {
         throw new IllegalArgumentException("An illegal key was input into playKey()!");
      }
   }
   
   // Adds up and returns all of the note samples.
   public double sample() {
      double sample = 0.0;
      
      for (int i = 0; i < notes.length; ++i) {
         sample += notes[i].sample();
      }
      
      return sample;
   }
   
   // Calls all of the notes tic method and
   // then moves timePlaying forward one value.
   public void tic() {
      timePlaying++;
   
      for (int i = 0; i < notes.length; ++i) {
         notes[i].tic();
      }
   }
   
   // Returns how many tic calls there have been.
   public int time() {
      return timePlaying;
   }
}
