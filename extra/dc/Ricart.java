import java.util.Scanner;

class Site {
    private int id;

    public Site(int id) {
        this.id = id;
    }

    public void enterCriticalSection() {
        System.out.println("Site " + this.id + " enters critical section");
    }

    public void request(Site destination) {
        System.out.println("Site " + this.id + " requests access to Site " + destination.id);
    }
}

public class Ricart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of sites: ");
        int numSites = scanner.nextInt();
        scanner.close();

        Site[] sites = new Site[numSites];
        for (int i = 0; i < numSites; i++) {
            sites[i] = new Site(i + 1);
        }

        sites[0].request(sites[1]);
        sites[0].enterCriticalSection();
        sites[0].request(sites[2]);
        sites[0].enterCriticalSection();
    }
}

/*
 * PS C:\Users\Vedika\Desktop\DC> java .\Ricart.java
 * Enter the number of sites: 3
 * Site 1 requests access to Site 2
 * Site 1 enters critical section
 * Site 1 requests access to Site 3
 * Site 1 enters critical section
 */