package company.lesson02;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private final BufferedReader scannerLogin;
    private BufferedReader fromServer;
    private PrintWriter toServer;

    public ChatClient() {
        scannerLogin = new BufferedReader(new InputStreamReader(System.in));
    }

    private void run() throws IOException {
        boolean chatWorks = true;
        while (chatWorks) {
            try (Socket socket = new Socket("localhost", 12345)) {

                fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                toServer= new PrintWriter(socket.getOutputStream(), true);

                new Thread(() -> {
                    boolean userWantsToChat = true;
                    Scanner scanner = new Scanner(System.in);
                    while (userWantsToChat) {
                        String lineFromChat = scanner.nextLine();
                        if ("q".equals(lineFromChat)) {
                            userWantsToChat = false;
                        }
                        toServer.println(lineFromChat);
                    }
                }).start();

                while (true) {
                    String line = fromServer.readLine();
                    if (line.startsWith(ChatServer.MENU_CODE)) {
                        System.out.println(line.replace(ChatServer.MENU_CODE, ""));
                    } else if (line.startsWith(ChatServer.START_CHAT_CODE)) {
                        System.out.println(line.replace(ChatServer.START_CHAT_CODE, ""));
                    } else if (line.startsWith(ChatServer.MSG_CODE)) {
                        System.out.println(line.replace(ChatServer.MSG_CODE, ""));
                    }
                }
            } catch (IOException ignore) {
                System.out.println("Нет соединения с сервером.\n Хотите продолжить или нажмете на выход?\n Нажмите 1 - продолжить или q для выхода");
                boolean userAnswered = handleUserAnswer(scannerLogin.readLine());
                if (!userAnswered) {
                    chatWorks = false;
                }
            }
        }
    }

    private boolean handleUserAnswer(String userAnswer) {
        if ("q".equals(userAnswer)) {
            System.out.println("До свидания!");
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        ChatClient chatClient = new ChatClient();
        chatClient.run();
    }

}
