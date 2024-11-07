import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private String sensorName;
    private boolean running;

    public Client(String sensorName) {
        this.sensorName = sensorName;
    }

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSending() {
        running = true;
        while (running) {
            sendTemperature();
            try {
                Thread.sleep(3000);  // 3 seconds delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendTemperature() {
        double temperature = Math.random()*30;
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String message = sensorName + "," + temperature + "," + timestamp;
        out.println(message);
        System.out.println("Sent: " + message);
    }

    public static void main(String[] args) {
        Client client = new Client(args[0]);
        client.startConnection("127.0.0.1", 6666);
        client.startSending();
    }
}