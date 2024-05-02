import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMI_Server extends UnicastRemoteObject implements RMI_Interface {
    public RMI_Server() throws RemoteException {
        super();
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        try {
            Registry registry = LocateRegistry.createRegistry(1880);
            registry.bind("hello", new RMI_Server());
            System.out.println("The RMI_Server is running and ready...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The RMI_Server is not running...");
        }
    }

    @Override
    public void displayMessage() throws RemoteException {
        System.out.println("----------------------------------------------");
        System.out.println("Hello Akash!");
        System.out.println("----------------------------------------------");

    }

    @Override
    public int factorial(int n) throws RemoteException {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
