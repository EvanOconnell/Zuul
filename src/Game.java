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
 * @version 02.06.2013
 * @author Evan O'Connell
 * 
 * Commits and repo can be seen at http://github.com/EvanOconnell/Zuul.git
 */

class Game 
{
    private final Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
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
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office, workshop, basement, hallway, vault;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        workshop = new Room("in the craft/wood workshop");
        basement = new Room("in the workshop's storage Basement");
        hallway = new Room("in a dark hallway out of the basement you've never seen before...");
        vault = new Room("in a wonderful vault filled with old equipment, materials, and technological relics from years ago.");
        
//        outside.setExits(null, theatre, lab, pub, null, null);
//        theatre.setExits(null, null, null, outside, null, null);
//        pub.setExits(null, outside, null, null, null, null);
//        lab.setExits(outside, office, null, null, null, null);
//        office.setExits(null, null, workshop, lab, null, null);
//        workshop.setExits(office, null, null, null, null, basement);
//        basement.setExits(hallway, null, null, null, workshop, null);
//        hallway.setExits(vault, null, basement, null, null, null);
//        vault.setExits(null, null, hallway, null, null, null);
        
        
        //outside.setExits(null, theatre, lab, pub, null, null);
        
        outside.setExit(theatre, Direction.EAST);
        outside.setExit(lab, Direction.SOUTH);
        outside.setExit(pub, Direction.WEST);
        
        //theatre.setExits(null, null, null, outside, null, null);
        
        theatre.setExit(outside, Direction.WEST);
        
        //pub.setExits(null, outside, null, null, null, null);
        
        pub.setExit(outside, Direction.EAST);
        
        //lab.setExits(outside, office, null, null, null, null);
        
        lab.setExit(outside, Direction.NORTH);
        lab.setExit(office, Direction.EAST);
        
        //office.setExits(null, null, workshop, lab, null, null);
        
        office.setExit(workshop, Direction.SOUTH);
        office.setExit(lab, Direction.WEST);
        
        //workshop.setExits(office, null, null, null, null, basement);
        
        workshop.setExit(office, Direction.NORTH);
        workshop.setExit(basement, Direction.DOWN);
        
        //basement.setExits(hallway, null, null, null, workshop, null);
        
        basement.setExit(hallway, Direction.NORTH);
        basement.setExit(workshop, Direction.UP);
        
        //hallway.setExits(vault, null, basement, null, null, null);
        
        hallway.setExit(vault, Direction.NORTH);
        hallway.setExit(basement, Direction.SOUTH);
        
        //vault.setExits(null, null, hallway, null, null, null);
        
        vault.setExit(hallway, Direction.SOUTH);
        
        currentRoom = outside;
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
        System.out.println("\nWelcome to Adventure!" +
            "\nAdventure is a new, incredibly boring adventure game." +
            "\nType 'help' if you need help.\n"
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
        if (commandWord.equalsIgnoreCase("help"))
            printHelp();
        else if (commandWord.equalsIgnoreCase("go"))
            goRoom(command);
        else if (commandWord.equalsIgnoreCase("quit"))
            wantToQuit = quit(command);

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
        System.out.println("You are lost. You are alone. You wander" +
                "\naround at the university.\n" +
                "\nYour command words are:\n"
            );
        System.out.println("\tgo\t|\tquit\t|\thelp");
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
