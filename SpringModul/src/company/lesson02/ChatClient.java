package company.lesson02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ChatClient {

    private static final ConcurrentHashMap<String, String> users = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try {
            // Устанавливаем соединение с сервером, который слушает на порту 12345
            Socket socket = new Socket("localhost", 12345);

            // Создаем читателя для входящих сообщений от сервера
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // Создаем писателя для отправки сообщений серверу
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Поток для чтения сообщений от сервера
            Thread readerThread = new Thread(() -> {
                try {
                    String message;
                    // Бесконечный цикл для чтения сообщений от сервера
                    while ((message = reader.readLine()) != null) {
                        System.out.println("Сервер: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readerThread.start();

            // Чтение сообщений с консоли и отправка на сервер
            BufferedReader consoleReader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Бесконечный цикл работы приложения
            while (true) {
                // Небольшое меню
                System.out.println("Для регистрации - нажмите 1, для входа - нажмите 2");

                // Авторизация или регистрация клиента
                String menu = consoleReader.readLine();
                switch (menu) {
                    case "1" -> Handler.register();
                    case "2" -> Handler.start();
                }
                System.out.println("Добро пожаловать " + Handler.userName + " для выхода - нажмите q");
                // Бесконечный цикл для чтения сообщений с консоли и отправки на сервер
                boolean chatState = true;
                while (chatState) {
                    String userInput = consoleReader.readLine();
                    if (!"q".equals(userInput)) {
                        writer.println(userInput);
                    } else {
                        chatState = false;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Handler {
        private static final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        private static String userName;
        private static String password;

        public static void register() throws IOException {
            System.out.println("Создайте имя пользователя");
            userName = consoleReader.readLine();
            if (!users.containsKey(userName)) {
                System.out.println("Создайте пароль");
                password = consoleReader.readLine();
                users.put(userName, password);
            } else {
                System.out.println("Пользователь с таким именем существует!");
                start();
            }
        }

        public static void start() throws IOException {
            System.out.println("Введите имя зарегистрированного пользователя");
            userName = consoleReader.readLine();
            if (users.containsKey(userName)) {
                System.out.println("Введите пароль");
                password = consoleReader.readLine();
                if (!users.get(userName).contains(password)) {
                    System.out.println("Введен не корректный пароль");
                    start();
                }
            } else {
                System.out.println("Пользователя с таким именем не существует");
                register();
            }
        }
    }
}
