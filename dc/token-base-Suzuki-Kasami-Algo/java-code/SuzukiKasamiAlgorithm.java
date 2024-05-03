import java.util.concurrent.Semaphore;

class Process extends Thread {
    private int id;
    private int numProcesses;
    private Semaphore mutex;
    private Semaphore[] reply;
    private int[] request;

    public Process(int id, int numProcesses, Semaphore mutex, Semaphore[] reply, int[] request) {
        this.id = id;
        this.numProcesses = numProcesses;
        this.mutex = mutex;
        this.reply = reply;
        this.request = request;
    }

    public void run() {
        while (true) {
            try {
                // Non-critical section
                Thread.sleep((long) (Math.random() * 1000));

                // Entering the critical section
                requestCS();
                enterCS();
                leaveCS();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void requestCS() throws InterruptedException {
        mutex.acquire();
        request[id] = 1;
        mutex.release();

        for (int j = 0; j < numProcesses; j++) {
            if (j != id) {
                reply[j].release();
            }
        }

        for (int j = 0; j < numProcesses; j++) {
            if (j != id) {
                reply[j].acquire();
            }
        }
    }

    private void enterCS() throws InterruptedException {
        System.out.println("Process " + id + " enters critical section.");
    }

    private void leaveCS() throws InterruptedException {
        mutex.acquire();
        request[id] = 0;
        for (int j = 0; j < numProcesses; j++) {
            if (request[j] == 1) {
                reply[j].release();
            }
        }
        mutex.release();
        System.out.println("Process " + id + " leaves critical section.");
    }
}

public class SuzukiKasamiAlgorithm {
    public static void main(String[] args) {
        int numProcesses = 5;
        Semaphore mutex = new Semaphore(1);
        Semaphore[] reply = new Semaphore[numProcesses];
        int[] request = new int[numProcesses];

        for (int i = 0; i < numProcesses; i++) {
            reply[i] = new Semaphore(0);
            request[i] = 0;
        }

        Process[] processes = new Process[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            processes[i] = new Process(i, numProcesses, mutex, reply, request);
            processes[i].start();
        }
    }
}

