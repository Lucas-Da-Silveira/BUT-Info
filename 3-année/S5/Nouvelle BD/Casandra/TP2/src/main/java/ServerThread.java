import com.datastax.driver.core.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        System.out.println("Thread created!");
    }

    public void run() {
        System.out.println("thread running!");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //connect to DB
            CassandraConnector connector = new CassandraConnector();
            connector.connect("127.0.0.1", 9042);
            Session session = connector.getSession();
            System.out.println("Session connected to DB!");
//for every received message
            while (true) {
                String message = in.readLine();
                System.out.println("received message: " + message);
                Temperature temperature = new Temperature(message);
                insertTemperature(temperature);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void insertTemperature(Temperature temperature) {
        StringBuilder sb = new StringBuilder("INSERT INTO")
                .append("sensor_data.temperatures_by_sensor").append(" (sensor, date, timestamp, value)")
                .append(" VALUES ('").append(temperature.getSensor())
                .append("', '").append(temperature.getDate()).append("', '").append(temperature.getTimestamp())
                .append("', ").append(temperature.getValue()).append(");");
        String query = sb.toString();
        System.out.println(query);
        session.execute(query);
    }
}