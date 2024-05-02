import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

// Define the remote interface
interface RemoteInterface extends Remote {
    String getMessage() throws RemoteException;
}

// Implement the remote interface
class RemoteImpl extends UnicastRemoteObject implements RemoteInterface {
    public RemoteImpl() throws RemoteException {
        super();
    }

    public String getMessage() throws RemoteException {
        return "Hello from the server!";
    }
}

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Create an instance of the remote implementation
            RemoteInterface obj = new RemoteImpl();

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("RemoteObject", obj);

            System.out.println("Server ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
