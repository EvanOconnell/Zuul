


/**
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *  
 *  NEW CHANGES - 1/17/13 Evan O'Connell:
 *  Added workshop, basement, hallway and vault. Workshop seems like just 
 *  another room, until you remember that there's always been that basement
 *  used for storage there. When you look in the basement, you see a hallway
 *  you've never seen before. The dark hallway leads to the vault full of
 *  old pieces of technology and relics of the past the school has saved.
 *  
 *  
 *  
 * -------
 * 
 * @version 3 (02.06.2013) 
 * @author Evan O'Connell
 * 
 * Commits and repo can be seen at http://github.com/EvanOconnell/Zuul.git
 */

public class Game 
{
    private final Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();//TODO : use Scenario constructor instead
        parser = new Parser();
    }
    
    /**
     * This is used for testing in jar form or eclipse.
     */
    public static void main(String[] args){
        Game game = new Game();
        game.play();
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("Welcome to Adventure!" +
            "\nAdventure is a new, incredibly boring adventure game." +
            "\nType 'help' if you need help.\n"+
            "\n-----------------------------\n"
        );
        printRoomDescription();
    }
    
    /**
     * Retrieves and prints description info from currentRoom object. 
     */
    private void printRoomDescription(){
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        
        String commandWord = command.getCommandWord();
        if(commandWord.equalsIgnoreCase("help")){
        	printHelp();
        }
        if(commandWord.equalsIgnoreCase("go")){
        	goRoom(command);
        }
        if(commandWord.equalsIgnoreCase("quit")){
        	wantToQuit = quit(command);
        }
        if(commandWord.equalsIgnoreCase("look")){
        	look();
        }
        if(commandWord.equalsIgnoreCase("eat")){
        	eat();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander"+
                "\naround at the university."+
                "\n\nYour command words are:"
            );
        System.out.println("\n"+CommandWords.getCommandStringList());
    }
    
    /** Look around the room.
     * 
     * Prints room description.
     */
    private void look(){
    	printRoomDescription();
    }
    
    /**
     * Restores food level. 
     */
    private void eat(){
    	System.out.println("You have eaten now and you are not hungry anymore!");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        Room.Direction dir = Room.Direction.parse(command.getSecondWord());
        
        Room nextRoom = null;
        
        if(!currentRoom.hasExit(dir)){
            System.out.println("There is no exit that way!");
        } else {
            nextRoom = currentRoom.getExit(dir);
            currentRoom = nextRoom;
            printRoomDescription();
        }
        
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()){
            System.out.println("Quit what?");
            return false;
        }
        return true;  // signal that we want to quit
    }
}
