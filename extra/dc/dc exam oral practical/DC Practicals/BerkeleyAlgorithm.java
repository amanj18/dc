import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Machine {
    private int id;
    private int clock;

    public Machine(int id) {
        this.id = id;
        this.clock = new Random().nextInt(100);
    }// Initialize clock with random value}

    public int getId() {
        return id;

    }

    public int getClock() {
        return clock;

    }

    public void setClock(int clock) {
        this.clock = clock;
    }

}

public class BerkeleyAlgorithm {
    private List<Machine> machines;

    public BerkeleyAlgorithm(int numMachines) {
        machines = new ArrayList<>();
        for (int i = 0; i < numMachines; i++) {
            machines.add(new Machine(i));

        }

    }

    public void synchronizeClocks() {
        int sum = 0;
        for (Machine machine : machines) {
            sum += machine.getClock();

        }
        int average = sum /
                machines.size();
        for (Machine machine : machines) {
            machine.setClock(average);
        }
    }

    public void printClocks() {
        for (Machine machine : machines) {
            System.out.println("Machine " + machine.getId() + ": Clock = " + machine.getClock());
        }
    }

    public static void main(String[] args) {
        BerkeleyAlgorithm algorithm = new BerkeleyAlgorithm(5); // Number of machines
        System.out.println("Before synchronization:");
        algorithm.printClocks();
        algorithm.synchronizeClocks();
        System.out.println("\nAfter synchronization:");
        algorithm.printClocks();

    }
}

/*
 * PS C:\Users\Vedika\Desktop\DC> javac BerkeleyAlgorithm.java
 * PS C:\Users\Vedika\Desktop\DC> java BerkeleyAlgorithm.java
 * Before synchronization:
 * Machine 0: Clock = 87
 * Machine 1: Clock = 43
 * Machine 2: Clock = 12
 * Machine 3: Clock = 44
 * Machine 4: Clock = 46
 * 
 * After synchronization:
 * Machine 0: Clock = 46
 * Machine 1: Clock = 46
 * Machine 2: Clock = 46
 * Machine 3: Clock = 46
 * Machine 4: Clock = 46
 * PS C:\Users\Vedika\Desktop\DC>
 */