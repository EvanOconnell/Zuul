
/**
 * @version 4 (02.28.2013) 
 * @author Evan O'Connell
 * 
 * Commits and repo can be seen at http://github.com/EvanOconnell/Zuul.git
 */

public class TransporterRoom extends Room{
	
	private Scenario scenario;
	
	/**
	 * @param description String description of this room.
	 * @param scenario The Scenario that's used in the Game class.
	 */
	public TransporterRoom(String description, Scenario scenario){
		super(description);
		this.scenario = scenario;
	}
	
	/**
	 * @return a random room using the findRandomRoom() method.
	 */
	@Override
	public Room getExit(Direction dir){
		return findRandomRoom();
	}
	
	/**
	 * @return a random room using the Scenario given in this class's constructor
	 */
	public Room findRandomRoom(){
		return scenario.getRandomRoom();
	}
	
	/**
	 * @return similar string as Room class, but with an extra message
	 */
	@Override
	public String getExitLocationsString() {
		return super.getExitLocationsString() + "\nAlso, the doorknob seems to be glowing...";
	}
	
	
}
