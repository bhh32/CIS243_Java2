
/**
 * Interface describing abstract user interaction operations.
 * This interface is implemented by the graphical and text UIs for the game.
 * Your InfoTree interacts with the UI through this interface.
 * @author Marty Stepp
 * @version 2010/02/20
 * @version 2018/01/01 agarripoli for CS143W18
 */
public interface UserInteraction {
    /**
     * Waits for the user to input a hot/cold answer (by typing, clicking, etc.),
     * and returns that answer as a boolean value.
     * @return the answer selected by the user as a boolean (hot is true, cold is false)
     */
    boolean nextBoolean();

    /**
     * Waits for the user to input a text value, and returns that answer as a String.
     * @return the answer typed by the user as a String (empty string if no answer typed)
     */
    String nextLine();
    
    /**
     * Displays the given output message to the user.
     * @param message The message to display.  Assumes not null.
     */
    void print(String message);
    
    /**
     * Displays the given output message to the user.
     * If the UI is a text UI, also inserts a line break (\n).
     * @param message The message to display.  Assumes not null.
     */
    void println(String message);

    // various messages that are output by the user interface
    // (your InfoTree does not need to refer to these messages)
    final String PLAY_AGAIN_MESSAGE = "Play again?";
    final String SAVE_MESSAGE = "Shall I remember this game?";
    final String LOAD_MESSAGE = "Shall I recall a previous game?";
    final String SAVE_LOAD_FILENAME_MESSAGE = "Enter a file name:";
    final String BANNER_MESSAGE = "Think of an item, and I will guess it.";
}
