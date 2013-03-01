/**
 * @version 4 (02.28.2013) 
 * @author Evan O'Connell
 * 
 * Commits and repo can be seen at http://github.com/EvanOconnell/Zuul.git
 */

public class TransporterRoom extends Room{
	
	private Scenario scenario;
	
	public TransporterRoom(String description, Scenario sceneario){
		super(description);
		this.scenario = sceneario;
	}
	
	@Override
	public Room getExit(Direction dir){
		return findRandomRoom();
	}
	
	public Room findRandomRoom(){
		return scenario.getRandomRoom();
	}
	
	
}
