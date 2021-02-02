import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AStarSearch implements SearchAlgorithm {

    private Heuristics heuristics;
    private HashMap<Integer, ArrayList<State>> myHashMap;
    private HashSet<State> myHashSet;

    public AStarSearch(Heuristics h) {
        this.heuristics = h;
    }

    boolean solutionFound = false;
    int totalNodeExpansions = 0;
    int maxFrontierSize = 0;
    Node solutionNode = null;
    private ArrayList<Node> frontierList;


    @Override
    public void doSearch(Environment env) {
        heuristics.init(env);
        solutionFound = false;
        totalNodeExpansions = 0;
        maxFrontierSize = 0;
        frontierList = new ArrayList<Node>();
        myHashMap = new HashMap<>();
        myHashSet = new HashSet<>();
//        frontierList.add(null);


        /*Node currentNode = new Node(env.getCurrentState(), heuristics.eval(env.getCurrentState()));
        frontierList.add(currentNode);


        // finding best solution
        while (!solutionFound) {
            // find node to expand
            currentNode = findBestNodeInFrontier();
            // expand it
            expandNode(currentNode, env);
            // check if maxFrontierSize is  now larger, if so then update
            if(currentNode!=null) {
                if(currentNode.state.dirt.isEmpty() && currentNode.state.position.x == 1 && currentNode.state.position.y == 1) {
                    System.out.println("found a solution!!!!!");
                    solutionFound = true;

                    solutionNode = currentNode;
                }
            }

            System.out.println(frontierList.size());

            } */

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
            if (nextNode.evaluation == 1 && !nextNode.state.turned_on && nextNode.action == Action.TURN_OFF) {
                System.out.println("A huebito krnal");
                solutionFound = true;
                solutionNode = nextNode;
                // getPlan();
                System.out.println("Node expansions: " + totalNodeExpansions + ", Maximum size of the frontier: " + getMaxFrontierSize() + ", Cost of solution: " + getPlanCost());
            } else {
                expandNode(nextNode, env);


            }
        }
        }

    private Node findBestNodeInFrontier() {
        // check if null or empty frontierList
        /*if(frontierList.isEmpty()) {
            return null;
        } */
        // iterate through the frontier list and find the best evaluation
        int minNumberOfSteps = Integer.MAX_VALUE;
        Node bestNode = null;
        for (Node n : frontierList) {
            if(heuristics.eval(n.state) < minNumberOfSteps) {
                bestNode = n;
            }
        }
        return bestNode;
    }

    private void expandNode(Node n, Environment env) {
        // check if null
        if(n != null) {
            List<Action> moves = env.legalMoves(n.state);
            for (Action a : moves) {
                if(!checkIfStateExistsIfSoAddIt(env.getNextState(n.state, a))) {
                    Node newNode = new Node(n, env.getNextState(n.state, a), a, heuristics.eval(n.state)+n.depth); //
                    frontierList.add(newNode);
                    totalNodeExpansions++;
                }

            }
            frontierList.remove(n);
            if (frontierList.size() > maxFrontierSize) {
                maxFrontierSize = frontierList.size();
            }


        }
        // expand it for each move that we can do
        // check if the new state already exists, check the cost and decide to go either left or right
//        checkIfStateExistsIfSoAddIt(n.state);
        // update the frontier, both remove the current node and add the new nodes

    }

    private boolean checkIfStateExistsIfSoAddIt(State s) {
        // check if we already have this state inside the hasshmap
        System.out.println("-------------");
        System.out.println(s);
        System.out.println("-------------");
        int hash = s.hashCode();
        /*if(!myHashMap.containsKey(hash)) {
            ArrayList<State> states= new ArrayList<>();
            states.add(s);
            myHashMap.put(hash, states);
            return false;
        } */
        if(!myHashSet.contains(s)) {
            myHashSet.add(s);
            return false;
        }
        // if not then add it

        // if so we do not add it

        return true;
    }

    @Override
    public List<Action> getPlan() {
        List<Action> toRet = solutionNode.getPlan();
        toRet.add(Action.TURN_OFF);
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
