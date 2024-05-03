package pkg_RMI.RPC;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.rmi.NotBoundException;
import java.rmi.Naming;

public class RMI_FactorialClient {

    private static Scanner scan = null;

    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

        try {
            RMI_FactorialInterface factorialApi = (RMI_FactorialInterface) Naming
                    .lookup("rmi://localhost:1888/factorial");
            scan = new Scanner(System.in);

            for (;;) {
                System.out.println("*");
                System.out.println("The RMI APP for Factorial Calculation ");
                System.out.println("*");
                System.out.println();
                System.out.println("What do you want to do today?");
                System.out.println("1-Get factorial of a number");
                int choice = getChoice();
                System.out.println(factorialApi.getFactorial(getInput()));
            }
        } catch (Exception e) {
            System.out.println("The RMI Factorial APP is not running...");
            e.printStackTrace();
        }

    }

    public static int getChoice() {
        try {
            System.out.println("Enter Choice:");
            int input = scan.nextInt();
            if (input != 1)
                throw new Exception();
            return input;
        } catch (Exception e) {
            System.err.println("Please input the number 1");
            return getChoice();
        }
    }

    public static int getInput() {
        try {
            System.out.println("Enter input:");
            int input = scan.nextInt();
            return input;
        } catch (Exception e) {
            System.err.println("Please input a valid number");
            return getInput();
        }

    }

}