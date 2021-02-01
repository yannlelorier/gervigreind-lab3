import java.util.ArrayList;
import java.util.Collection;

public class Main {
	
	/**
	 * starts the game player and waits for messages from the game master <br>
	 * Command line options: [port]
	 */
	public static void main(String[] args){
		try{

			Collection<String> cTemp = new ArrayList<String>(){{
				add("(ORIENTATION NORTH)");
				add("(HOME 2 1)");
				add("(STEP 0)");
				add("(AT DIRT 1 1)");
				add("(AT OBSTACLE 1 2)");
				add("(POINTS 25)");
			}};
			Environment env = new Environment(cTemp); // initialize new Environment, the environment has currentState in it.

			// testing Suck
			if(!(env.getNextState(env.currentState, Action.SUCK).equals(env.currentState))){
				System.out.println("Incorrect Suck, should not have cleaned anything");
			}
			env.currentState.position = new Coordinates(1, 1);
			if((env.getNextState(env.currentState, Action.SUCK).equals(env.currentState))){
				System.out.println("Incorrect Suck, should have removed some dirt");
			}

			// TODO: choose a different search method and/or heuristics here
			SearchAlgorithm search = new AStarSearch(new SimpleHeuristics()); 
			Agent agent = new VaccuumCleanerAgent(search);

			int port=4001;
			if(args.length>=1){
				port=Integer.parseInt(args[0]);
			}
			GamePlayer gp=new GamePlayer(port, agent);
			gp.waitForExit();
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
