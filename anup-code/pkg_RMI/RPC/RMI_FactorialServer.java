package pkg_RMI.RPC;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMI_FactorialServer extends UnicastRemoteObject implements RMI_FactorialInterface {

    public RMI_FactorialServer() throws RemoteException {
        super();
    }

    // @Override
    public int getFactorial(int input) throws RemoteException {
        if (input < 0)
            return -1; // Return -1 if input is negative

        int fact = 1;
        for (int i = 1; i <= input; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        try {
            Registry registry = LocateRegistry.createRegistry(1888);
            registry.bind("factorial", new RMI_FactorialServer());
            System.out.println("The RMI Factorial APP is running and ready...");
        } catch (Exception e) {
            System.out.println("The RMI Factorial APP is not running...");
        }

    }

}
