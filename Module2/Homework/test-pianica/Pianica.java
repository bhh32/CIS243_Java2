/* Bryan Hyland
   CIS143
   Piano Man (Week 2)
   12Apr19
*/


public class Pianica implements Piano {
   public static final String KEYS =
       "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   private static final int CONCERT_A = 440;
   private static final int PITCH_CONSTANT = 24;  
   private Note[] notes = new Note[37];
   private int timePlaying;
   
   public Pianica() {
      notes[24] = new Note(CONCERT_A);
      
      for (int i = 0; i < notes.length; ++i) {
         if (i != 24) {
            notes[i] = new Note(CONCERT_A * Math.pow(2, (double)i/12.0));
         }
      }
   } 
       
   public void playNote(int pitch) {
      if (pitch + PITCH_CONSTANT > 0 && pitch + PITCH_CONSTANT < KEYS.length()) {
         notes[pitch + PITCH_CONSTANT].play();
      }
   }
   
   public boolean hasKey(char key) {
      for (int i = 0; i < KEYS.length(); ++i) {
         if (KEYS.charAt(i) == key) {
            return true;
         }
      }
      return false;
   }
   
   public void playKey(char key) {
      if (hasKey(key)) {
         notes[KEYS.indexOf(key)].play();
      }
      else {
         throw new IllegalArgumentException("An illegal key was input into playKey()!");
      }
   }
   
   public double sample() {
      double sample = 0.0;
      
      for (int i = 0; i < notes.length; ++i) {
         sample += notes[i].sample();
      }
      
      return sample;
   }
   
   public void tic() {
      for (int i = 0; i < notes.length; ++i) {
         notes[i].tic();
      }
      
      timePlaying++;
   }
   
   public int time() {
      return timePlaying;
   }
   
   public static void main(String[] args) {
      Pianica p = new Pianica();
   }
}
