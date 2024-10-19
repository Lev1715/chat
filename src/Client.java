import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private Socket socket;
    private Chat chat;
    public PrintStream out = null;

    Client(Socket socket, Chat chat) {
        this.socket = socket;
        this.chat = chat;
        new Thread(this).start();
    }

    public void receive(String massage) {
        out.println(massage);
    }

    @Override
    public void run() {

        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            Scanner in = new Scanner(is);
            out = new PrintStream(os);

            out.println("добро пожаловать в чат");
            String input = in.nextLine();
            while (!input.equals("bye")) {
                chat.sandAll(input);
                input = in.nextLine();
            }
            System.out.println("пользователь вышел из чата");
            socket.close();
        } catch (IOException e) {
            System.out.println("пользователя выкинуло с ошибкой");
        }
    }
}
