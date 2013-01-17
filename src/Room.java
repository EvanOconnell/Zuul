
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
    private Room northExit;
    private Room eastExit;
    private Room southExit;
    private Room westExit;
    

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     */
    public Room(String description){
        setDescription(description);
    }
    
    /**
     * Remember to define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     */
    public Room(String description, Room northexit, Room eastexit, Room southexit, Room westexit){
    	setDescription(description);
    	setExits(northexit, eastexit, southexit, westexit);
    }
    
    /**
     * I may or may not use this 'blank' constructor. I have 
     * used something like it in other situations to avoid 
     * NPEs (ie, when adding to list), but I probably won't 
     * need it here.
     */
    public static Room Empty(){
    	return new Room("");
    }

    /**
     * Return the description of the room (the one that was defined
     * in the constructor).
     */
    public String getDescription(){
        return description;
    }
    
	public Room getNorthExit() {
		return northExit;
	}

	public Room getEastExit() {
		return eastExit;
	}

	public Room getSouthExit() {
		return southExit;
	}

	public Room getWestExit() {
		return westExit;
	}
    
	public boolean hasNorthExit() {
		return northExit!=null;
	}

	public boolean hasEastExit() {
		return eastExit!=null;
	}

	public boolean hasSouthExit() {
		return southExit!=null;
	}

	public boolean hasWestExit() {
		return westExit!=null;
	}
	
	public void setExits(Room north, Room east, Room south, Room west){
		setNorthExit(north);
        setEastExit(east);
        setSouthExit(south);
        setWestExit(west);
	}
	
    public void setDescription(String description){
    	this.description = description;
    }

	public void setNorthExit(Room northExit) {
		this.northExit = northExit;
	}
	
	public void setEastExit(Room eastExit) {
		this.eastExit = eastExit;
	}

	public void setSouthExit(Room southExit) {
		this.southExit = southExit;
	}

	public void setWestExit(Room westExit) {
		this.westExit = westExit;
	}

}
