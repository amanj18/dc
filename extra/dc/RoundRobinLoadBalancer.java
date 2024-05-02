import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoundRobinLoadBalancer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the processes separated by spaces: ");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        List<Integer> processes = new ArrayList<>();
        for (String str : inputArray) {
            processes.add(Integer.parseInt(str));
        }
        scanner.close();

        int numServers = 4;
        List<List<Integer>> servers = new ArrayList<>();
        for (int i = 0; i < numServers; i++) {
            servers.add(new ArrayList<>());
        }

        // Balance processes among servers using round-robin
        int serverIndex = 0;
        for (Integer process : processes) {
            servers.get(serverIndex).add(process);
            serverIndex = (serverIndex + 1) % numServers;
        }

        // Output the processes after balancing
        for (int i = 0; i < servers.size(); i++) {
            System.out.println("Server " + (i + 1) + " Processes: " + servers.get(i));
        }
    }
}

/*
 * PS C:\Users\Vedika\Desktop\DC> java .\RoundRobinLoadBalancer.java
 * Enter the processes separated by spaces: 1 2 3 4 5 6 7
 * Server 1 Processes: [1, 5]
 * Server 2 Processes: [2, 6]
 * Server 3 Processes: [3, 7]
 * Server 4 Processes: [4]
 * PS C:\Users\Vedika\Desktop\DC>
 */