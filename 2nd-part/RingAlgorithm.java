import java.util.*;

class RingNode {
    private int id;
    private RingNode successor;

    public RingNode(int id) {
        this.id = id;
    }

    public void setSuccessor(RingNode successor) {
        this.successor = successor;
    }

    public RingNode getSuccessor() {
        return successor;
    }

    public int getId() {
        return id;
    }
}

public class RingAlgorithm {
    public static void main(String[] args) {
        // Create ring nodes
        RingNode node1 = new RingNode(1);
        RingNode node2 = new RingNode(2);
        RingNode node3 = new RingNode(3);
        RingNode node4 = new RingNode(4);

        // Set successors
        node1.setSuccessor(node2);
        node2.setSuccessor(node3);
        node3.setSuccessor(node4);
        node4.setSuccessor(node1);

        // Start election from node 1
        electLeader(node1);
    }

    public static void electLeader(RingNode startNode) {
        RingNode currentNode = startNode;
        RingNode leaderNode = startNode;

        do {
            System.out.println("Node " + currentNode.getId() + " sends election message to node " + currentNode.getSuccessor().getId());
            currentNode = currentNode.getSuccessor();
            if (currentNode.getId() > leaderNode.getId()) {
                leaderNode = currentNode;
            }
        } while (currentNode != startNode);

        System.out.println("Leader elected: Node " + leaderNode.getId());
    }
}
