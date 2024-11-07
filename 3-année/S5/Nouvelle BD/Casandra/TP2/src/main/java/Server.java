import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException{
        serverSocket = new ServerSocket(port);

        while (true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("New sensor connected!");
            ServerThread serverThread=new ServerThread(clientSocket);
            serverThread.start();
        }
    }

    public void main(String[] args){
        Server server = new Server();
        try{
            server.start(6666);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
