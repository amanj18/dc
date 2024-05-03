import java.util.ArrayList;
import java.util.List;

class Node {
    private int id;
    private long timeOffset;

    public Node(int id) {
        this.id = id;
        this.timeOffset = 0;
    }

    public int getId() {
        return id;
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(long timeOffset) {
        this.timeOffset = timeOffset;
    }

    // Simulating clock drift by adding an offset to the current time
    public long getCurrentTime() {
        return System.currentTimeMillis() + timeOffset;
    }
}

public class BerkeleyAlgorithm {
    public static void main(String[] args) {
        // Create nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        // Add nodes to a list
        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);

        // Calculate average time
        long averageTime = calculateAverageTime(nodes);

        // Synchronize clocks
        synchronizeClocks(nodes, averageTime);

        // Print synchronized times
        System.out.println("Synchronized Times:");
        for (Node node : nodes) {
            System.out.println("Node " + node.getId() + ": " + node.getCurrentTime());
        }
    }

    public static long calculateAverageTime(List<Node> nodes) {
        long sum = 0;
        for (Node node : nodes) {
            sum += node.getCurrentTime();
        }
        return sum / nodes.size();
    }

    public static void synchronizeClocks(List<Node> nodes, long averageTime) {
        for (Node node : nodes) {
            long timeOffset = averageTime - node.getCurrentTime();
            node.setTimeOffset(timeOffset);
        }
    }
}
