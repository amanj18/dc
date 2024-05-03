import java.nio.channels.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMI_Server extends UnicastRemoteObject implements RMI_interface {

    protected RMI_Server() throws RemoteException {
        super();
    }

    

    public static void main(String[] args)throws RemoteException, AlreadyBoundException {
        try {
            Registry registry = LocateRegistry.createRegistry(1878);
            registry.bind("hello", new RMI_Server());
            System.out.println("The RMI_Server is running and ready...");
        } 
        catch (Exception e) {
            System.out.println("The RMI_Server is not running...");
        } 
    }

    @Override
    public double calculateSquareRoot(double number) throws RemoteException {
        double result = Math.sqrt(number);
        System.out.println("Square Root of "+ number+ " is : "+result);
        return result;
    }
}