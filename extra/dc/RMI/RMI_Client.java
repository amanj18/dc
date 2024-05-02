import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.rmi.Naming;

public class RMI_Client {
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
        Scanner sc = null; // Declare Scanner outside try block
        try {
            RMI_Interface helloAPI = (RMI_Interface) Naming.lookup("rmi://localhost:1880/hello");
            sc = new Scanner(System.in); // Initialize Scanner
            System.out.println("Enter a number to find the factorial: ");
            int n = sc.nextInt();
            int ans = helloAPI.factorial(n); // Pass n instead of 5
            System.out.println("Factorial of " + n + " is " + ans);
        } catch (Exception e) {
            System.out.println("The RMI APP is not running...");
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close(); // Close Scanner in finally block
            }
        }
    }
}
