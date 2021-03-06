
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;



/**
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighbouring room, or null if there is no exit in that direction.
 * 
 * -------
 * 
 * @version 4 (02.28.2013) 
 * @author Evan O'Connell
 * 
 * Commits and repo can be seen at http://github.com/EvanOconnell/Zuul.git
 */

public class Room 
{
    private String description;
    private HashMap<Direction, Room> exits;
    private ArrayList<Item> items;
    

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" should be able to follow the words 
     * "You are in" and is something like "a kitchen" or "an 
     * open court yard".
     */
    public Room(String description){
        this.description = description;
        exits = new HashMap<Room.Direction, Room>();
        items = new ArrayList<Item>();
    }
    
    public Room(String description, Item ... items){
    	this(description);
    	addItems(items);
    }
    
    /**
     * An empty Room object, for use instead of 'null' in the setExits()
     * method. Since a hashmap is used, this will help avoid NPEs.
     * @return An empty Room object.
     */
    @Deprecated
    public static Room empty(){
        return new Room(""); 
    }

    /**
     * @returns the description of the room
     */
    public String getDescription(){
        return description;
    }
    
    /** This method creates a string returnable, concatenates all exit's directions and returns the variable. 
     * 
     * @return String representation of a list of existing exit *directions*
     */
    public String getExitLocationsString(){
        String str = "Exits: ";
        for(Direction exitDir : exits.keySet()){
            str += exitDir.toString()+" ";
        }
        return str;
    }
    
    boolean numbered_list = false;
    
    public String getLongDescription(){
    	String items = "\nItems:";
    	for(Item item : this.items){
    		items += "\n  - "+item;
    	}
        return "You are "+description+"."+(hasItems() ? items+"\n" :"")+"\n"+getExitLocationsString();
    }
    
    /**
     * This may return a null value. Use hasExit() to verify if an
     * exit has been defined already.
     */
    public Room getExit(Direction dir){
        return exits.get(dir);
    }
    
    /**
	 * @return The list of items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
	
	/**
	 * @return true if the Room instance has one of more Items.
	 */
	public boolean hasItems(){
		return !items.isEmpty();
	}
    
    /**
     * @returns true if the room has an exit in that direction
     */
    public boolean hasExit(Direction dir){
        return !(exits.get(dir)==null||exits.get(dir).isEmpty());
    }
    
    public boolean isEmpty(){
        return description==""&&(exits.isEmpty()||exits==null);
    }
    
    /**
     * @returns The direction of an exit room if it exists, if not returns Direction.NULL
     */
    public Direction isAnExit(Room room){
        if(exits.containsValue(room)){
            for(Entry<Direction, Room> entry : exits.entrySet()){
                if(entry.getValue().equals(room)) return entry.getKey();
            }
        }
        return null; 
    }
    
    
    /**
     * This calls the Hashmap.put() method, meaning it will
     * override any existing exit in the same key (direction).
     * 
     * Nulls should be okay as parameters since they will be
     * caught here and replaced by Room.empty()
     */
    public void setExit(Room exit, Direction dir){
        exits.put(dir, exit==null ? Room.empty() : exit);
    }
    
    /**
     * Adds an item to the item list for this room.
     */
    public void addItem(Item item){
    	items.add(item);
    }
    
    /**
     * @param Multiple items to add to room
     */
	public void addItems(Item ... args){
		for(Item item : args){
			this.items.add(item);
		}	
	}

	/**
     * Represents cardinal directions, as well as up, down, and null.
     */
    public enum Direction{
        NORTH, EAST, SOUTH, WEST, UP, DOWN, @Deprecated NULL;
        
        @Override
        public String toString(){
            return super.toString().toLowerCase();
        }
        
        /** Parses a string for convenience
         * 
         * @return a Direction enum constant (eg. NORTH)
         */
        public static Direction parse(String str){
            return
                str.equalsIgnoreCase("north") ? Direction.NORTH :
                str.equalsIgnoreCase("east") ? Direction.EAST :
                str.equalsIgnoreCase("south") ? Direction.SOUTH :
                str.equalsIgnoreCase("west") ? Direction.WEST :
                str.equalsIgnoreCase("up") ? Direction.UP :
                str.equalsIgnoreCase("down") ? Direction.DOWN :
                null;
        }
    }

}
