import java.util.ArrayList;
import java.util.List;

class Server {
    private int id;
    private int load;

    public Server(int id) {
        this.id = id;
        this.load = 0;
    }

    public int getId() {
        return id;
    }

    public int getLoad() {
        return load;
    }

    public void incrementLoad() {
        load++;
    }
}

public class RoundRobinLoadBalancer {
    private List<Server> servers;
    private int currentIndex;

    public RoundRobinLoadBalancer() {
        servers = new ArrayList<>();
        currentIndex = 0;
    }

    public void addServer(Server server) {
        servers.add(server);
    }

    public Server getNextServer() {
        Server nextServer = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size(); // Move to the next server in a circular manner
        return nextServer;
    }

    public static void main(String[] args) {
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer();

        // Add some servers
        loadBalancer.addServer(new Server(1));
        loadBalancer.addServer(new Server(2));
        loadBalancer.addServer(new Server(3));

        // Simulate requests
        for (int i = 0; i < 10; i++) {
            Server server = loadBalancer.getNextServer();
            server.incrementLoad();
            System.out.println("Request assigned to Server " + server.getId());
        }

        // Print server loads
        System.out.println("\nServer Loads:");
        for (Server server : loadBalancer.servers) {
            System.out.println("Server " + server.getId() + ": " + server.getLoad());
        }
    }
}
