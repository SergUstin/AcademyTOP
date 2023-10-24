package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
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

            String username;
            String password;

            System.out.println("Enter username: ");
            username = consoleReader.readLine();
            System.out.println("Enter password: ");
            password = consoleReader.readLine();

            writer.println(username);
            writer.println(password);

            Thread writerThread = new Thread(() -> {
                try {
                    String userInput;
                    while ((userInput = consoleReader.readLine()) != null) {
                        writer.println(userInput);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writerThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
