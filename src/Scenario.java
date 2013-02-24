import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a game scenario including connected rooms and items
 * 
 * @author Tang
 * @version 2013.02.16
 */
public class Scenario
{
    private ArrayList<Room> rooms;
    private Room startRoom;
    
    private Random random;

    /**
     * Constructor for objects of class Scenario
     */
    public Scenario()
    {
        random = new Random();
        
        Room outside, theatre, pub, lab, office, workshop, basement, hallway, vault;
        
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        workshop = new Room("in the craft/wood workshop");
        basement = new Room("in the workshop's storage Basement");
        hallway = new Room("in a dark hallway out of the basement you've never seen before...");
        vault = new Room("in a wonderful vault filled with old equipment, materials, and technological relics from years ago.");
        
        outside.setExit(theatre, Room.Direction.EAST);
        outside.setExit(lab, Room.Direction.SOUTH);
        outside.setExit(pub, Room.Direction.WEST);
        theatre.setExit(outside, Room.Direction.WEST);
        pub.setExit(outside, Room.Direction.EAST);
        lab.setExit(outside, Room.Direction.NORTH);
        lab.setExit(office, Room.Direction.EAST);
        office.setExit(workshop, Room.Direction.SOUTH);
        office.setExit(lab, Room.Direction.WEST);
        workshop.setExit(office, Room.Direction.NORTH);
        workshop.setExit(basement, Room.Direction.DOWN);
        basement.setExit(hallway, Room.Direction.NORTH);
        basement.setExit(workshop, Room.Direction.UP);
        hallway.setExit(vault, Room.Direction.NORTH);
        hallway.setExit(basement, Room.Direction.SOUTH);
        vault.setExit(hallway, Room.Direction.SOUTH);
        
        lab.addItems(new Item("A potted plant", 7), new Item("A trashcan", 4.5));
        pub.addItems(new Item("A mug of coffee", .2), new Item("A half empty bottle of beer", .2));
        office.addItems(new Item("A laptop", .9), new Item("A cart of laptops", 59));
        workshop.addItems(new Item("A sheet of plywood", 7), new Item("A drywall saw", .5));
        basement.addItems(new Item("A sheet of plywood", 7), new Item("A box of posters", 4.5));
        vault.addItems(
    			new Item("An *old* CRT monitor", 11), 
    			new Item("A box FULL of old 256 MB RAM", 7.5),
    			new Item("A mechanical keyboard", 2.3),
    			new Item("An old overhead projector", 3)
			);
        
        
        
        startRoom = outside;
    }

    /**
     * @return  the start room for this scenario
     */
    public Room getStartRoom(){
        return this.startRoom;
    }
    
    /**
     * @return  a random room from this scenario
     */
    public Room getRandomRoom(){
        return rooms.get(random.nextInt(rooms.size()));
    }
}
