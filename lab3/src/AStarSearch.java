import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSearch implements SearchAlgorithm {

    private Heuristics heuristics;
    private HashMap<Integer, ArrayList<State>> myHashMap;

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
//        frontierList.add(null);


        Node currenctNode = new Node(env.getCurrentState(), Integer.MAX_VALUE);
        frontierList.add(currenctNode);


        // finding best solution
        while (!solutionFound) {
            //TODO find node to expand

            // expand it

            // check if maxFrontierSize is  now larger, if so then update



        }
    }

    private Node findBestNodeInFrontier() {
        // check if null or empty frontierList
        if (frontierList == null || frontierList.isEmpty()) {
            return null;
        }else {
            // iterate through the frontier list and find the best evaluation
            int mini = 0;
            int thisNodeEval = 0;
            for (int i=0; i<frontierList.size(); i++) {
                thisNodeEval = frontierList.get(i).evaluation;
                if (frontierList.get(mini).evaluation > thisNodeEval) {
                    mini = i;
                }

            }
            return frontierList.get(mini);
        }
    }

    private void expandNode(Node n) {
        // check if null
        // expand it for each move that we can do
        // check if the new state already exists, check the cost and decide to go either left or right
//        checkIfStateExistsIfSoAddIt(n.state);
        // update the frontier, both remove the current node and add the new nodes

    }

    private boolean checkIfStateExistsIfSoAddIt(State s) {
        // check if we already have this state inside the hasshmap

        // if not then add it

        // if so we do not add it

        return false;
    }

    @Override
    public List<Action> getPlan() {
        List<Action> toRet = solutionNode.getPlan();
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
