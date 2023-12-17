//Максим Николенко, модуль №12, задание 12.3.3, курс "Андроид-разработчик"
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    //создаем массив в котором хранятся все клиенты,
    // чтобы клиенты могли обмениваться м/у собой:
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    ChatServer() throws IOException {
        // создаем серверный сокет(разъем) на порту 1234:
        serverSocket = new ServerSocket(1234);
    }
    //создадим функцию(метод), который будет принимать
    //некую строку и раздавать всем клиентам:
    void sendAll(String message) {
        for (Client client: clients) {
            client.receive(message);
        }
    }
        public void run() {
            while (true) {
                System.out.println("Waiting...");
                try {
                    // ждем клиента из сети
                    Socket socket = serverSocket.accept();
                    System.out.println("Client connected!");
                    // создаем клиента на своей стороне
                    clients.add(new Client(socket, this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        public static void main(String[] args) throws IOException {
            new ChatServer().run(); //создаем объект сервера
        }
    }


