package company.lesson02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
    // Список писателей для всех клиентов
    private static final List<PrintWriter> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Создаем серверный сокет, который слушает на порту 12345
            ServerSocket serverSocket = new ServerSocket(12345);

            while (true) {
                System.out.println("Ожидание подключения клиента...");

                // Принимаем клиентское соединение
                Socket clientSocket = serverSocket.accept();

                // Создаем писателя для отправки сообщений клиенту
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(writer);

                System.out.println("Клиент подключен.");

                // Поток для чтения сообщений от клиента
                Thread clientThread = new Thread(() -> {
                    try {
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));

                        String message;
                        // Бесконечный цикл для чтения сообщений от клиента
                        while ((message = reader.readLine()) != null) {
                            System.out.println("Получено от клиента: " + message);
                            broadcastMessage(message); // Рассылка сообщения всем клиентам
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для рассылки сообщения всем клиентам
    private static void broadcastMessage(String message) {
        for (PrintWriter client : clients) {
            client.println(message);
        }
    }
}
