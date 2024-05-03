import java.rmi.Naming;
import java.util.Scanner;

public class RMI_Client {

    public static void main(String[] args) {
        Scanner sc = null; 
        try {
            RMI_interface remoteObject = (RMI_interface) Naming.lookup("rmi://localhost:1878/hello");

            sc = new Scanner(System.in);
            System.out.print(" Enter a number to calculate its square root : ");
            double number = sc.nextDouble();

            double result = remoteObject.calculateSquareRoot(number);
            
            System.out.println("Square root of " + number + " is : " + result);

        } catch (Exception e) {
            System.out.println("The RMI APP is Not running...");
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
