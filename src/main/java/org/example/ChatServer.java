package org.example;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clientHandlers = ConcurrentHashMap.newKeySet();
    private static int clientIdCounter = 1;

    public static void main(String[] args) {
        System.out.println("Server is running on port " + PORT + "...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                String clientId = "User" + clientIdCounter++;
                System.out.println(clientId + " connected.");
                ClientHandler handler = new ClientHandler(socket, clientId);
                clientHandlers.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Broadcast message to all clients
    public static void broadcast(String message, ClientHandler excludeUser) {
        for (ClientHandler client : clientHandlers) {
            if (client != excludeUser) {
                client.sendMessage(message);
            }
        }
    }

    // Remove client when disconnected
    public static void removeClient(ClientHandler client) {
        clientHandlers.remove(client);
        System.out.println(client.getClientId() + " disconnected.");
    }

    // Inner class for handling clients
    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientId;

        public ClientHandler(Socket socket, String clientId) {
            this.socket = socket;
            this.clientId = clientId;
        }

        public String getClientId() {
            return clientId;
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Welcome " + clientId + "! You can start chatting now.");
                broadcast(clientId + " has joined the chat!", this);

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    }
                    System.out.println(clientId + ": " + message);
                    broadcast(clientId + ": " + message, this);
                }
            } catch (IOException e) {
                System.out.println("Connection error with " + clientId);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                removeClient(this);
                broadcast(clientId + " has left the chat!", this);
            }
        }
    }
}
