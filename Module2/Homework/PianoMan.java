// This program constructs a Piano object that it allows the user to play.

public class PianoMan {
    public static void main(String[] args) {
        Piano pianica = new MouthHarp(); // replace with Pianica once it is ready
        // this is an infinite loop--user must quit the application
        for (;;) {
            // check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                if (pianica.hasKey(key)) {
                    pianica.playKey(key);
                } else {
                    System.out.println("bad key: " + key);
                }
            }
            // send the result to the sound card
            StdAudio.play(pianica.sample());
            pianica.tic();
        }
    }
}
