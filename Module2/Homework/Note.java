/*
   Bryan Hyland
   12Apr19
   Week 2 - PianoMan
   
   Stores and manipulates the musical notes that
   are played in the MouthHarp and Pianica classes.
*/

import java.util.*;

public class Note {
   public static final double DECAY_FACTOR = .996;
   private static final double MAX_RANDOM_VALUE = 0.5;
   private static final double MIN_RANDOM_VALUE = -0.5;
   private Queue<Double> ringBuffer = new LinkedList<Double>();
   
   /*
      Constructs a note of the given frequency.  It creates a ring buffer 
      of the desired capacity N (sampling rate divided by frequency, rounded 
      to the nearest integer), and initializes it to represent a string at 
      rest by enqueueing N zeros. 
      
      The sampling rate is specified by the constant StdAudio.SAMPLE_RATE.  
      If the frequency is less than or equal to 0 or if the resulting size 
      of the ring buffer would be less than 2, throws an 
      IllegalArgumentException.
   */
   public Note(double frequency) {
         
      if (frequency <= 0) {
         throw new IllegalArgumentException("The frequency passed into "
            + "Note(freqency) was less than or equal to zero.");
      }
      
      // Caclulate ringBuffer capacity
      int capacity = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
      
      if (capacity < 2) {
         throw new IllegalArgumentException("The capacity for the ring buffer "
            + "calculated out to be less than 2.");
      }
      
      // Initialize ringBuffer
      while (ringBuffer.size() < capacity) {
         ringBuffer.add(0.0);
      }
   }
   
   /*
      Constructs a note and initializes the contents of 
      the ring buffer to the values in the array.  If
      the array has fewer than two elements, throws
      an IllegalArgumentException.
   */
   public Note(double[] init) {      
      if (init.length < 2) {
         throw new IllegalArgumentException("The array passed "
            + "into Note(double[] init) has less than "
            + "2 elements.");
      }
      
      for (int i = 0; i < init.length; ++i) {
         ringBuffer.add(init[i]);
      }
   }
   
   /*
      Replaces the elements in the ring buffer with random values 
      between -0.5 inclusive and +0.5 exclusive.
   */
   public void play() {      
      int bufferSize = ringBuffer.size();
      while (!ringBuffer.isEmpty()) {
         ringBuffer.remove();
      }
      
      for (int i = 0; i < bufferSize; ++i) {
         ringBuffer.add(Math.random() 
            * (MAX_RANDOM_VALUE - MIN_RANDOM_VALUE) 
            + MIN_RANDOM_VALUE);
      }
   }
   
   /*
      Deletes the sample at the front of the ring buffer 
      and adds to the end of the ring buffer the average 
      of the first two samples multiplied by the energy 
      decay factor (0.996).
   */
   public void tic() {      
      ringBuffer.add(
         ((ringBuffer.remove() + ringBuffer.peek()) / 2)
         * DECAY_FACTOR);      
   }
   
   // Returns the frequency of the first element of the ringBuffer.
   public double sample() { 
      return ringBuffer.peek();
   }
}
