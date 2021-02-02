import java.util.ArrayList;
import java.util.Collection;

public class Main {
	
	/**
	 * starts the game player and waits for messages from the game master <br>
	 * Command line options: [port]
	 */
	public static void main(String[] args){

		try{
			// // initialize
			// Collection<String> cTemp = new ArrayList<String>(){{
			// 	add("(ORIENTATION NORTH)");
			// 	add("(HOME 1 1)");
			// 	add("(STEP 0)");
			// 	add("(AT DIRT 1 1)");
			// 	add("(AT OBSTACLE 1 2)");
			// 	add("(POINTS 25)");
			// }};
			// Environment env = new Environment(cTemp); // initialize new Environment, the environment has currentState in it.
			// // tests
			// // testing TurnLeft
			// env.currentState.orientation = 0;
			// if (env.getNextState(env.currentState, Action.TURN_LEFT).orientation != 3){
			// 	System.out.println("Incorrect TurnLeft, should be 3 but is " + env.getNextState(env.currentState, Action.TURN_LEFT).orientation);
			// }
			// env.currentState.orientation = 1;
			// if (env.getNextState(env.currentState, Action.TURN_LEFT).orientation != 0){
			// 	System.out.println("Incorrect TurnLeft, should be 0 but is " + env.getNextState(env.currentState, Action.TURN_LEFT).orientation);
			// }

			// // testing TurnRight
			// if (env.getNextState(env.currentState, Action.TURN_RIGHT).orientation != 2){
			// 	System.out.println("Incorrect TurnRight, should be 2 but is " + env.getNextState(env.currentState, Action.TURN_RIGHT).orientation);
			// }
			// env.currentState.orientation = 3;
			// if (env.getNextState(env.currentState, Action.TURN_RIGHT).orientation != 0){
			// 	System.out.println("Incorrect TurnRight, should be 0 but is " + env.getNextState(env.currentState, Action.TURN_RIGHT).orientation);
			// }
			// // testing TurnOn
			// env.currentState.turned_on = false;
			// if (env.getNextState(env.currentState, Action.TURN_ON).turned_on == false){
			// 	System.out.println("Incorrect TurnOn, should be true but is " + env.getNextState(env.currentState, Action.TURN_ON).turned_on);
			// }
			// // testing TurnOff
			// env.currentState.turned_on = true;
			// if (env.getNextState(env.currentState, Action.TURN_OFF).turned_on == true){
			// 	System.out.println("Incorrect TurnOff, should be false but is " + env.getNextState(env.currentState, Action.TURN_ON).turned_on);
			// }
			// // testing GO
			// env.currentState.position = new Coordinates(3, 3);
			// env.currentState.orientation = 0; // north
			// if (!(env.getNextState(env.currentState, Action.GO).position.equals(new Coordinates(3, 4)))){
			// 	System.out.println("Incorrect GO, should be (3, 4) but is " + env.getNextState(env.currentState, Action.GO).position);
			// }
			// env.currentState.orientation = 2; // south
			// if (!(env.getNextState(env.currentState, Action.GO).position.equals(new Coordinates(3, 2)))){
			// 	System.out.println("Incorrect GO, should be (3, 2) but is " + env.getNextState(env.currentState, Action.GO).position);
			// }
			// env.currentState.orientation = 1; // east
			// if (!(env.getNextState(env.currentState, Action.GO).position.equals(new Coordinates(4, 3)))){
			// 	System.out.println("Incorrect GO, should be (4, 3) but is " + env.getNextState(env.currentState, Action.GO).position);
			// }
			// env.currentState.orientation = 3; // west
			// if (!(env.getNextState(env.currentState, Action.GO).position.equals(new Coordinates(2, 3)))){
			// 	System.out.println("Incorrect GO, should be (2, 3) but is " + env.getNextState(env.currentState, Action.GO).position);
			// }
			// // testing Suck
			// if(!(env.getNextState(env.currentState, Action.SUCK).equals(env.currentState))){
			// 	System.out.println("Incorrect Suck, should not have cleaned anything");
			// }
			// env.currentState.position = new Coordinates(1, 1);
			// if((env.getNextState(env.currentState, Action.SUCK).equals(env.currentState))){
			// 	System.out.println("Incorrect Suck, should have removed some dirt");
			// }
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
