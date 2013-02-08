package Zuul;

/**
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * -------
 * 
 * @version 3 (02.06.2013) 
 * @author Evan O'Connell
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String validCommands[] = {
        "go", 
        "quit", 
        "help", 
        "look", 
        "eat"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment... 
    	// Hopefully never. Why would you want to instantiate a class containing an array of constants?
    }

    /**
     * Check whether a given String is a valid command word. 
     * Return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     * @returns A string list of all valid commands defined in the CommandWords class
     * 
     * The string returned does not end with a space
     */
    public static String getCommandStringList(){
    	String str = "";
    	for(String cmd : validCommands){
    		str += cmd+" ";
    	}
    	str = str.trim();
    	return str;
    }

}
