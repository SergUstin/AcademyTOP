package company.lesson02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {

    public static String MENU_CODE = "010";
    public static String MSG_CODE = "020";
    public static String START_CHAT_CODE = "030";

    private static final int PORT = 12345;
    private static final ConcurrentHashMap<String, String> users = new ConcurrentHashMap<>();
    private static final HashSet<PrintWriter> writers = new HashSet<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Сервер запущен....");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new Handler(serverSocket.accept()).start();
            }
        }
    }

    private static class Handler extends Thread {

        private BufferedReader in;
        private final Socket clientSocket;
        private PrintWriter out;

        public Handler (Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                String userName = null;

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                ChatState chatState = ChatState.START;

                String command;
                while (true) {
                    switch (chatState) {
                        // Приветствие пользователя, вход или регистрация
                        case START:
                            out.println(MENU_CODE + "Нажмите 1 для входа или 2 для регистрации");
                            command = in.readLine();
                            if ("1".equals(command)) {
                                chatState = ChatState.LOGIN;
                            } else if ("2".equals(command)) {
                                chatState = ChatState.REGISTER;
                            } else {
                                out.println(MENU_CODE + "Пожалуйста, ");
                            }
                            break;
                        // Вход уже зарегистрировавшегося пользователя
                        case LOGIN:
                            out.println(MENU_CODE + "Нажмите enter для ввода имени или q для выхода");
                            command = in.readLine();
                            if ("q".equals(command)) {
                                chatState = ChatState.START;
                            } else if (users.containsKey(command)) {
                                userName = command;
                                chatState = ChatState.TYPE_PASSWORD;
                            } else {
                                out.println(MENU_CODE + "Нет ользователя с именем " + command + ". Проверьте ваше имя и попробуйте снова ");
                            }
                            break;
                        // Ввод пароля зарегистрированного пользователя
                        case TYPE_PASSWORD:
                            out.println(MENU_CODE + "Пожалуйста введите пароль или нажмите q для выхода");
                            command = in.readLine();
                            if ("q".equals(command)) {
                                chatState = ChatState.LOGIN;
                            } else if (users.get(userName).equals(command)) {
                                chatState = ChatState.CHAT;
                            } else {
                                out.println(MENU_CODE + "Указан неверный пароль");
                            }
                            break;
                        // Регистрация пользователя и ввод имени
                        case REGISTER:
                            out.println(MENU_CODE + "Пожалуйста введите имя или нажмите q для выхода");
                            command = in.readLine();
                            if ("q".equals(command)) {
                                chatState = ChatState.START;
                            } else if (!users.containsKey(command)) {
                                userName = command;
                                chatState = ChatState.CREATE_PASSWORD;
                            } else {
                                out.println(MENU_CODE + "Такой пользователь уже существует, ");
                            }
                            break;
                        // Создание пароля при регистрации
                        case CREATE_PASSWORD:
                            out.println(MENU_CODE + "Пожалуйста создайте пароль или нажмите q для выхода");
                            command = in.readLine();
                            if ("q".equals(command)) {
                                chatState = ChatState.REGISTER;
                            } else {
                                users.put(userName, command);
                                chatState = ChatState.CHAT;
                            }
                            break;
                        // Чат пользователей
                        case CHAT:
                            writers.add(out);

                            out.println(MENU_CODE + "Добро пожаловать в чат, " + userName + "! Для выхода нажмите q и enter");
                            boolean chatIsOn = true;
                            while (chatIsOn) {
                                System.out.println("Введите текст");
                                String input;
                                boolean isReady = in.ready();
                                while (!isReady) {
                                    isReady = in.ready();
                                }
                                input = in.readLine();

                                System.out.println("Принял " + input);
                                if ("q".equals(input)) {
                                    chatIsOn = false;
                                    writers.remove(out);
                                    chatState = ChatState.START;
                                } else {
                                    for (PrintWriter writer : writers) {
                                        writer.println(MENU_CODE + userName + ": " + input);
                                        System.out.println("отправил обратно " + input);
                                    }
                                }
                            }
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
