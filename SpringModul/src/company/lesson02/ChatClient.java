package company.lesson02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        Socket socket;

        {
            try {
                socket = new Socket("localhost", 12345);

                BufferedReader reader = new BufferedReader((new InputStreamReader(socket.getInputStream())));

                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                Thread readerThread = new Thread(() -> {
                    try {
                        String message = "";
                        while ((message = reader.readLine()) != null) {
                            System.out.println("Сервер: " + message);

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                readerThread.start();

                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

                String userInput;

                while ((userInput = consoleReader.readLine()) != null) {
                    writer.println(userInput);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
