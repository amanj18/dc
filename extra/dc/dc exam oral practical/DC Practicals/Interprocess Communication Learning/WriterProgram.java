import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterProgram {
    public static void main(String[] args) {
        String filePath = "message.txt";
        String message = "Hello from Dhiraj!";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(message);

            System.out.println("Message written to " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



/*
PS C:\Users\Vedika\Desktop\DC> javac WriterProgram.java
PS C:\Users\Vedika\Desktop\DC> javac ReaderProgram.java
PS C:\Users\Vedika\Desktop\DC> java WriterProgram      
Message written to message.txt
PS C:\Users\Vedika\Desktop\DC> java ReaderProgram      
Message read from message.txt: Hello from Dhiraj!
PS C:\Users\Vedika\Desktop\DC>
 */