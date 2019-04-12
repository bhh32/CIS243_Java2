// This is a sample class that implements the Piano interface.  It is not well
// documented.

public class MouthHarp implements Piano {
    private Note stringA;
    private Note stringC;

    // create two guitar strings, for concert A and C
    public MouthHarp() {
        double CONCERT_A = 440.0;
        double CONCERT_C = CONCERT_A * Math.pow(2, 3.0/12.0);
        stringA = new Note(CONCERT_A);
        stringC = new Note(CONCERT_C);
    }

    public void playNote(int pitch) {
        if (pitch == 0) {
            stringA.play();
        } else if (pitch == 3) {
            stringC.play();
        }
    }

    public boolean hasKey(char string) {
        return (string == 'a' || string == 'c');
    }

    public void playKey(char string) {
        if (string == 'a') {
            stringA.play();
        } else if (string == 'c') {
            stringC.play();
        }
    }

    public double sample() {
        return stringA.sample() + stringC.sample();
    }

    public void tic() {
        stringA.tic();
        stringC.tic();
    }

    public int time() {
        return -1;  // not implemented
    }
}
