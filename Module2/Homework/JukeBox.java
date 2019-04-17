import java.io.*;
import java.util.*;
import javax.swing.*;

// This program can be used to play a song recorded with RecordATune.
// It plays the song using a Pianica object.
// Start the program and choose a song to play from a previous recording.

public class JukeBox {
    public static void main(String[] args) throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser(new File("./tunes"));
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            Scanner input = new Scanner(chooser.getSelectedFile());
            Piano pianica = new Pianica();
            while (input.hasNextInt()) {
                int pitch = input.nextInt();
                double duration = input.nextDouble();
                pianica.playNote(pitch);
                advance(duration, pianica);
            }
        }
    }

    public static void advance(double duration, Piano pianica) {
        int tics = (int) Math.round(duration * StdAudio.SAMPLE_RATE);
        for (int i = 0; i < tics; i++) {
            StdAudio.play(pianica.sample());
            pianica.tic();
        }
    }
}
