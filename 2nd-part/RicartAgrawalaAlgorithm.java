import java.util.concurrent.Semaphore;

class Process extends Thread {
    private int id;
    private int numProcesses;
    private Semaphore mutex;
    private Semaphore[] csEntered;
    private boolean inCS;
    private int[] requestQueue;

    public Process(int id, int numProcesses, Semaphore mutex, Semaphore[] csEntered, int[] requestQueue) {
        this.id = id;
        this.numProcesses = numProcesses;
        this.mutex = mutex;
        this.csEntered = csEntered;
        this.inCS = false;
        this.requestQueue = requestQueue;
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
                // Handle the exception
            }
        }
    }

    private void requestCS() throws InterruptedException {
        mutex.acquire();
        requestQueue[id] = 1;
        mutex.release();

        for (int j = 0; j < numProcesses; j++) {
            if (j != id) {
                while (requestQueue[j] == 1 || (inCS && csEntered[j].availablePermits() == 0)) {
                    // Wait until it's our turn
                    Thread.sleep(10);
                }
            }
        }
    }

    private void enterCS() throws InterruptedException {
        System.out.println("Process " + id + " enters critical section.");
        inCS = true;
    }

    private void leaveCS() throws InterruptedException {
        mutex.acquire();
        requestQueue[id] = 0;
        for (int j = 0; j < numProcesses; j++) {
            if (j != id) {
                csEntered[j].release();
            }
        }
        mutex.release();
        inCS = false;
        System.out.println("Process " + id + " leaves critical section.");
    }
}

public class RicartAgrawalaAlgorithm {
    public static void main(String[] args) {
        int numProcesses = 5;
        Semaphore mutex = new Semaphore(1);
        Semaphore[] csEntered = new Semaphore[numProcesses];
        int[] requestQueue = new int[numProcesses];

        for (int i = 0; i < numProcesses; i++) {
            csEntered[i] = new Semaphore(0);
            requestQueue[i] = 0;
        }

        Process[] processes = new Process[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            processes[i] = new Process(i, numProcesses, mutex, csEntered, requestQueue);
            processes[i].start();
        }
    }
}
