import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Chat {
    ServerSocket server;
    ArrayList<Client> clientList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Chat().run();

    }

    public void sandAll(String massage) {
        for (Client clientList : clientList) {
            clientList.receive(massage);
        }
    }

    public void run() throws IOException {
        server = new ServerSocket(1234);
        while (true) {
            System.out.println("ожидаем подключение клиента");
            Socket socket = server.accept();
            System.out.println("клиент подключился к чату");
            Client client = new Client(socket, this);
            clientList.add(client);
        }
    }
}
