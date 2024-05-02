import java.io.*;
import java.net.*;

public class MulticastSender {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("239.0.0.1");
            MulticastSocket socket = new MulticastSocket();

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String message;

            while (true) {
                System.out.print("Sender: ");
                message = userInput.readLine();
                if (message.equals("exit")) break;

                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 12345);
                socket.send(packet);
            }

            userInput.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
