import java.io.*;
import java.net.*;

public class MulticastReceiver {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("239.0.0.1");
            MulticastSocket socket = new MulticastSocket(12345);
            socket.joinGroup(group);

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Receiver: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
