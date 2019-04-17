// This program tests the Note class.  The program reads from the file
// note.txt which has a series of test cases with the correct answers.

import java.util.*;
import java.io.*;

public class TestNote {
    private static final double EPSILON = 1E-12;

    public static void main(String[] args) {
        Scanner input = null;
        try {
            input = new Scanner(new File("note.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("You must copy note.txt to the current directory" +
                               " (the parent directory on Eclipse)" +
                               " before running the testing program.");
            System.exit(1);
        }
        input.useLocale(Locale.US);
        testWithArray(input);
        testWithFrequency(input);
        System.out.println("passed all tests");
    }

    // pre : input file has a series of test cases for specific arrays of
    //       values showing what the sample value should be as we make repeated
    //       calls on tic
    // post: program is halted if an error is encountered
    public static void testWithArray(Scanner input) {
        int cases = input.nextInt();
        for (int i = 0; i < cases; i++) {
            int size = input.nextInt();
            double[] data = new double[size];
            for (int j = 0; j < data.length; j++)
                data[j] = input.nextDouble();
            System.out.println("Testing Note with this array:");
            System.out.println(Arrays.toString(data));
            Note note = new Note(data);

            for (int time = 0; time < 10 * data.length; time++) {
                double sample = input.nextDouble();
                double sample2 = note.sample();
                if (Math.abs(sample - sample2) > EPSILON) {
                    System.out.println("ERROR: Sample mismatch");
                    System.out.println("  when time = " + time);
                    System.out.println("  sample should = " + sample);
                    System.out.println("  string reports sample = " + sample2);
                    System.exit(1);
                }
                note.tic();
            }
            System.out.println("passed");
            System.out.println();
        }
    }

    // pre : input file has a series of test cases listing a frequency and ring
    //       buffer size to test
    // post: program is halted if an error is encountered
    public static void testWithFrequency(Scanner input) {
        int cases = input.nextInt();
        for (int i = 0; i < cases; i++) {
            int freq = input.nextInt();
            int size = input.nextInt();
            System.out.println("Testing Note with frequency " + freq);
            Note note = new Note(freq);
            note.play();
            double sum1 = 0.0;
            double first = note.sample();
            boolean allZero = true;
            for (int j = 0; j < size - 1; j++) {
                double n = note.sample();
                sum1 = sum1 + n;
                if (n != 0.0) {
                    allZero = false;
                }
                if (n < -0.5 || n > 0.5) {
                    System.out.println("ERROR: sample #" + j + " = " + n);
                    System.exit(1);
                }
                note.tic();
            }
            if (allZero) {
                System.out.println("ERROR: all samples values are zero");
                System.exit(1);
            }
            double last = note.sample();
            sum1 = sum1 + last;
            note.tic();
            double sum2 = 0.0;
            for (int j = 0; j < size - 1; j++) {
                sum2 = sum2 + note.sample();
                note.tic();
            }
            double correctSum = (sum1 - first / 2.0 - last / 2.0) * 0.996;
            if (Math.abs(sum2 - correctSum) > EPSILON) {
                System.out.println("ERROR: sum of samples is not correct");
                System.out.println("ring buffer size should = " + size);
                System.exit(1);
            } else
                System.out.println("passed");
            System.out.println();
        }
    }
}
