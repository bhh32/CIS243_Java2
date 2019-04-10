import java.util.*;

public class ListSetExample {

    public static void main(String[] args) {
        List<String> words1 = new ArrayList<String>();
        
        List<String> words2 = new LinkedList<String>();
        
        words1.add("hello");
        words2.add("hello");
        
        System.out.println(words1);
        System.out.println(words2);
        
        
        addWords(words1);
        addWords(words2);
        
        List<Integer> numbers = new ArrayList<Integer>();
        // addWords(numbers);
        int index = 0;
        for (String word : words2) {
            System.out.println(word);
            index++; // very bad style, use a old-style for loop
        }
        
        Iterator<String> iter = words2.iterator();
        while (iter.hasNext()) {
            String word = iter.next();
            System.out.println(word);
        }
        
        // why we have remove...
        try {
           for (String word : words2) {
               System.out.println(word);
               if (word.equals("this")) {
                   words2.remove("this"); // if you remove "is", get wrong results
               }
           }
        } catch (ConcurrentModificationException cme) {
            cme.printStackTrace();
        }

        iter = words2.iterator();
        while (iter.hasNext()) {
            String word = iter.next();
            System.out.println(word);
            if (word.equals("is")) {
                iter.remove(); // this works. note "this" is still gone.
            }
        }
        

    }

    public static void addWords(List<String> words) {
        words.add("this");
        words.add("is");
        words.add("Java");
    }

}