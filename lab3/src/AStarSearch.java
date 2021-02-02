import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSearch implements SearchAlgorithm {

    private Heuristics heuristics;
    private HashMap<Integer, ArrayList<State>> myHashMap = new HashMap<Integer, ArrayList<State>>();
    // private HashMap<Integer, State> myHashMap = new HashMap<Integer, State>();

    public AStarSearch(Heuristics h) {
        this.heuristics = h;
    }

    boolean solutionFound = false;
    int totalNodeExpansions = 0;
    int maxFrontierSize = 0;
    int counter = 0;
    Node solutionNode = null;
    int tTableSize = 1000;
    private ArrayList<Node> frontierList;


    @Override
    public void doSearch(Environment env) {
        heuristics.init(env); //initialize 

		Node currentNode = new Node(env.getCurrentState(), heuristics.eval(env.getCurrentState())); //the val represent the cost until then
		frontierList = new ArrayList<Node>();
		
		frontierList.add(currentNode);


		while(!solutionFound) {
            Node nextNode = findBestNodeInFrontier();
            // System.out.println(frontierList);
            // if (counter < 6){
            //     System.out.println("FL: "+ frontierList);
            //     counter++;
            // }
			//find node to expand
            //expand the note
            //TODO test this
			if(nextNode.evaluation == 1 && nextNode.state.turned_on == false && nextNode.action == Action.TURN_OFF){
                System.out.println("A huebito krnal");
                solutionFound = true;
				solutionNode = nextNode;
				// getPlan();
				System.out.println("Node expansions: " + totalNodeExpansions + ", Maximum size of the frontier: " + getMaxFrontierSize() + ", Cost of solution: " + getPlanCost());
			}else{
				expandNode(nextNode, env);
			}
			
			
			//check if max frontiersize is now larger
			//if so update and detect dublicat states (lab2)


			//expansions
			//too much: if state is home and no dirts the solfound 
			// use evaluation function instead --> take into account that all dirts have been removed in the eval func
		}
    }

    private Node findBestNodeInFrontier() {
        // check if null or empty frontierList
        /*if(frontierList.isEmpty()) {
            return null;
        } */
        // iterate through the frontier list and find the best evaluation
        int minEval = Integer.MAX_VALUE;

		int bestIndex = -1;
        
        if (!frontierList.isEmpty()) {
            for (Node n : frontierList) {
                if (n.evaluation == 1){
                    if(n.action == Action.TURN_OFF){
                        return n;
                    }
                } else {
                    if(heuristics.eval(n.state) <= minEval){

                        minEval = heuristics.eval(n.state);
                        bestIndex = frontierList.indexOf(n);
                    }
                }
            }
        }
        // System.out.println("size FL"+frontierList.size());		
        return frontierList.get(bestIndex);
		// check if null or empty
		//iterate through frontier and compare the eval function
    }

    private void expandNode(Node n, Environment env) {
        // check if null
        totalNodeExpansions++;

        if(n != null) {
            List<Action> moves = env.legalMoves(n.state);
            for (Action a : moves) {
                // Node(Node parent, State state, Action action, int val)
                Node newNode = new Node(n, env.getNextState(n.state, a), a, heuristics.eval(n.state) + n.depth);
                if(checkIfStateExistsIfSoAddIt(newNode.state)) {
                    frontierList.add(newNode);
                    if (counter < 6) {
                        System.out.println("Adding node " + newNode);
                    }
                }else {
                    // System.out.println("Cannot add " + a);
                }

            }
            if (counter < 6){
                System.out.println("Removing node " + n);
                counter++;
            }
            frontierList.remove(n); //here wth is goin on

            if (frontierList.size() > maxFrontierSize) {
                maxFrontierSize = frontierList.size();
            }

        }
        // expand it for each move that we can do
        // check if the new state already exists, check the cost and decide to go either left or right
        // update the frontier, both remove the current node and add the new nodes

    }

    private boolean checkIfStateExistsIfSoAddIt(State s) {

        int key = bucketIndex(s.hashCode(), tTableSize);
        ArrayList<State> tList = myHashMap.get(key);

        if (tList == null){
            tList = new ArrayList<State>();
            // System.out.println("tList is null");
            tList.add(s);
            myHashMap.put(key, tList);
            return true;
        }
        if (tList.contains(s)) {
            // System.out.println("List already has s: "+ s);
            return false;
        }else {
            // System.out.println("added state");
            tList.add(s);
            myHashMap.put(key, tList);
            return true;
        }
        // int hash = s.hashCode();

        // if(myHashMap.get(hash) != null){
		// 	if ((myHashMap.get(hash)).equals(s)){
        //         return true;
		// 	}
		// }	
        // myHashMap.put(hash, s);
		// return false;
    }

    public static int bucketIndex(int hashCode, int tableSize) {
        return (hashCode ^ (hashCode >>> 16)) & (tableSize-1);
    }

    @Override
    public List<Action> getPlan() {
        List<Action> toRet = solutionNode.getPlan();
        System.out.println("inside getPlan");
        if(toRet != null && !toRet.isEmpty()){
            return toRet;
        }
        if(toRet == null) {
            System.out.println("Inside getPlan() : solutionNodes getPlan is null");
        } else if(toRet.isEmpty()){
            System.out.println("Inside getPlan() : solutionNodes getPlan is empty");
        }
        return null;
    }

    @Override
    public int getNbNodeExpansions() {
        return totalNodeExpansions;
    }

    @Override
    public int getMaxFrontierSize() {
        return maxFrontierSize;
    }

    @Override
    public int getPlanCost() {
        if (solutionNode != null) {
            return getPlan().size();
        }
        System.out.println("getPlanCost() : solution node is still null.");
        return -1;
    }

}
