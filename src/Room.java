import java.util.HashMap;
import java.util.Map.Entry;



/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Room 
{
    private String description;
//    private Room northExit;
//    private Room eastExit;
//    private Room southExit;
//    private Room westExit;
    
    private HashMap<Direction, Room> exits;
    

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" should be able to follow the words 
     * "You are in" and is something like "a kitchen" or "an 
     * open court yard".
     */
    public Room(String description){
        setDescription(description);
    }

    /**
     * @returns the description of the room
     */
    public String getDescription(){
        return description;
    }
    
    /**
     * This may return a null value. Use hasExit() to verify if an
     * exit has been defined already.
     */
    public Room getExit(Direction dir){
    	return exits.get(dir);
    }
    
    public boolean hasExit(Direction dir){
    	return exits.get(dir)!=null;
    }
    
    public Direction isAnExit(Room room){
    	if(exits.containsValue(room)){
    		for(Entry<Direction, Room> entry : exits.entrySet()){
    			if(entry.getValue().equals(room)) return entry.getKey();
    		}
    	}
    	return Direction.NULL; 
    	/* returns null in case I want to read the result of this method 
    	 * in a situation where I don't want an actual null value.*/
    }
	
    public void setDescription(String description){
    	this.description = description;
    }
    
    public void setExits(Room north, Room east, Room south, Room west, Room up, Room down){
		setExit(north, Direction.NORTH);
		setExit(east, Direction.EAST);
		setExit(south, Direction.SOUTH);
		setExit(west, Direction.WEST);
		setExit(up, Direction.UP);
		setExit(down, Direction.DOWN);
	}
    
    /**
     * This calls the Hashmap.put() method, meaning it will
     * override any existing exit in the same key (direction).
     */
	public void setExit(Room exit, Direction dir){
		exits.put(dir, exit);
	}
	
	public enum Direction{
		NORTH, EAST, SOUTH, WEST, UP, DOWN, NULL;
	}

}
