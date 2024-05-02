import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            // Get the registry
            Registry registry = LocateRegistry.getRegistry(null);

            // Look up the remote object
            RemoteInterface obj = (RemoteInterface) registry.lookup("RemoteObject");

            // Call the remote method and print the result
            String message = obj.getMessage();
            System.out.println("Message from server: " + message);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
