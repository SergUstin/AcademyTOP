//package company.lesson02;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//import static company.lesson02.ChatServer.MENU_CODE;
//
//public class Helper {
//
//    private static BufferedReader in;
//    private static PrintWriter out;
//    private static Socket clientSocket = new Socket();
//    private static String command;
//    private static ChatState chatState;
//    private static String userName = null;
//
//    public Helper(Socket clientSocket) {
//        this.clientSocket = clientSocket;
//    }
//
//    private static void inAndOutMethod() throws IOException {
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//    }
//
//    public static void start() throws IOException {
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//        out.println("Нажмите 1 для входа или 2 для регистрации");
//        command = in.readLine();
//        if ("1".equals(command)) {
//            login();
//        } else if ("2".equals(command)) {
//            register();
//        } else {
//            out.println("Пожалуйста, ");
//        }
//    }
//
//    public static void login() throws IOException {
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//        out.println("Нажмите enter для ввода имени или q для выхода");
//        command = in.readLine();
//        if ("q".equals(command)) {
//            start();
//        } else if (ChatServer.users.containsKey(command)) {
//            userName = command;
//            typePassword();
//        } else {
//            out.println("Нет ользователя с именем " + command + ". Проверьте ваше имя и попробуйте снова ");
//        }
//    }
//
//    public static void typePassword() throws IOException {
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//        out.println("Пожалуйста введите пароль или нажмите q для выхода");
//        command = in.readLine();
//        if ("q".equals(command)) {
//            login();
//        } else if (ChatServer.users.get(userName).equals(command)) {
//            chat();
//        } else {
//            out.println("Указан неверный пароль");
//        }
//    }
//
//    public static void register() throws IOException {
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//        out.println("Пожалуйста введите имя или нажмите q для выхода");
//        command = in.readLine();
//        if ("q".equals(command)) {
//            start();
//        } else if (!ChatServer.users.containsKey(command)) {
//            userName = command;
//            createPassword();
//        } else {
//            out.println("Такой пользователь уже существует, ");
//        }
//    }
//
//    public static void createPassword() throws IOException {
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//        out.println("Пожалуйста создайте пароль или нажмите q для выхода");
//        command = in.readLine();
//        if ("q".equals(command)) {
//            register();
//        } else {
//            ChatServer.users.put(userName, command);
//            chat();
//        }
//    }
//
//    public static void chat() throws IOException {
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//
//        ChatServer.writers.add(out);
//
//        out.println(MENU_CODE + "Добро пожаловать в чат, " + userName + "! Для выхода нажмите q и enter");
//        boolean chatIsOn = true;
//        while (chatIsOn) {
//            System.out.println("Введите текст");
//            String input;
//            boolean isReady = in.ready();
//            while (!isReady) {
//                isReady = in.ready();
//            }
//            input = in.readLine();
//
//            System.out.println("Принял " + input);
//            if ("q".equals(input)) {
//                chatIsOn = false;
//                ChatServer.writers.remove(out);
//                start();
//            } else {
//                for (PrintWriter writer : ChatServer.writers) {
//                    writer.println(MENU_CODE + userName + ": " + input);
//                    System.out.println("отправил обратно " + input);
//                }
//            }
//        }
//    }
//}
