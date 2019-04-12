/*
   Bryan Hyland
   12Apr19
   PianoMan
   
   TODO: Enter a description of the class when done
*/

import java.util.*;

public class Note {
   private static final double DECAY_FACTOR = .996;
   
   // Can only use add(), remove, isEmpty, size, and peek
   private Queue<Integer> ringBuffer = LinkedList<>();
   
   public Note(double frequency) {
      /*
         Constructs a note of the given frequency.  It creates a ring buffer 
         of the desired capacity N (sampling rate divided by frequency, rounded 
         to the nearest integer), and initializes it to represent a string at 
         rest by enqueueing N zeros  (enqueueing means add to the end of the queue). 
         The sampling rate is specified by the constant StdAudio.SAMPLE_RATE.  
         If the frequency is less than or equal to 0 or if the resulting size of the 
         ring buffer would be less than 2, your method should throw an IllegalArgumentException.
      */ 
      /*
         Check frequency to ensure 
      */   
      if (frequency <= 0) {
         throw new IllegalArgumentException();
      }
      
      int capacity = Math.round(StdAudio.SAMPLE_RATE / frequency);
      
      if (capacity < 2) {
         throw new IllegalArgumentException();
      }
      
      while (ringBuffer.size() < capacity) {
         ringBuffer.add(0);
      }
   }
   
   public Note(double[] init) {
      /*
      Constructs a note and initializes the contents of the ring buffer to the values 
      in the array.  If the array has fewer than two elements, your constructor should 
      throw an IllegalArgumentException.  This constructor is used only for testing purposes, 
      but it must be present.
      */
   }
   
   public void play() {
      /*
         This method should replace the N elements in the ring buffer with N random values 
         between -0.5 inclusive and +0.5 exclusive (i.e. -0.5 <= value < 0.5).
      */
   }
   
   public void tic() {
      /*
         This method should apply the Karplus-Strong update once (performing one step).  
         It should delete the sample at the front of the ring buffer and add to the end of 
         the ring buffer the average of the first two samples (the one you deleted and the next one), 
         multiplied by the energy decay factor (0.996).  Your class should include a public 
         constant for the energy decay factor.
      */
   }
   
   public double sample() {
      /*
         This method should return the current sample (the value at the front of the ring buffer).
      */
   
      return ringBuffer.peek();
   }
}