
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_Interface extends Remote {
    void displayMessage() throws RemoteException;

    int factorial(int n) throws RemoteException;
}

// On 1st terminal
/*
 * PS C:\Users\Vedika\Desktop\DC> javac RMI_Interface.java
 * >> javac RMI_Server.java
 * >> javac RMI_Client.java
 * >> start rmiregistry 1880
 * >> java RMI_Server
 * >>
 * The RMI_Server is running and ready...
 */

// On 2nd Terminal
/*
 * PS C:\Users\Vedika\Desktop\DC> java RMI_Client
 * Enter a number to find the factorial:
 * 5
 * Factorial of 5 is 120
 */