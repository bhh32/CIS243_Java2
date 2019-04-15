/* Bryan Hyland
   CIS143
   Piano Man (Week 2)
   12Apr19
*/


public class Pianica implements Piano {
   public static final String KEYS =
       "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   private static final int CONCERT_A = 440;
   private static final int HIGHEST_PITCH_KEY_VALUE = KEYS.length() - 11;  
   private Note[] notes = new Note[37];
   
   public Pianica() {
      notes[24] = new Note(CONCERT_A);
      
      for (int i = 0; i < notes.length; ++i) {
         if (i != 24) {
            notes[i] = new Note(CONCERT_A * Math.pow(2, (double)i/12.0));
         }
      }
   } 
       
   public void playNote(int pitch) {
      /*if (pitch <= 0) {
         playKey(KEYS[pitch + 24]);
      }
      else {
         if (pitch > 0 && pitch < HIGHEST_PITCH_KEY_VALUE) {
            playKey(KEYS[KEYS.size() - pitch])
         }
      }*/
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
      return 0.0;
   }
   
   public void tic() {
   
   }
   
   public int time() {
      return 0;
   }
   
   public static void main(String[] args) {
      Pianica p = new Pianica();
      System.out.println(p.hasKey('e'));
      System.out.println(p.hasKey('3'));
   }
}
