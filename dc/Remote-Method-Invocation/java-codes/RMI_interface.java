import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_interface extends Remote {
    double calculateSquareRoot(double number) throws RemoteException;
}
