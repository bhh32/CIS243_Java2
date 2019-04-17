// This class is used for debugging the Pianica class.  It is not an example
// to be emulated.  When a note is played, it is set to the integer part of
// the note's frequency plus 0.25.  It goes down by 10 each time tic is
// called until it becomes less than or equal to 10 when it is set to 0.

public class Note {
    double value;
    double freq;

    public Note(double frequency) {
        freq = frequency;
    }

    public void play() {
        value = (int) freq + 0.25;
    }

    public void tic() {
        if (value <= 10) {
            value = 0.0;
        } else {
            value = value - 10;
        }
    }

    public double sample() {
        return value;
    }
}
