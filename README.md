Online Chat Application Documentation
Introduction
This project is a simple online chat application implemented in Java using socket programming. The application allows multiple users to connect to a central server, send messages, and receive messages from other users. The solution includes a server class, a client class, and a simple text-based user interface.
________________________________________
How to Run
1. Compile the Source Files
Open a terminal in the example directory and run:
  javac ChatServer.java ChatClient.java

2. Start the Server
Run the following command to start the server:
java org.example.ChatServer
 

<img width="975" height="172" alt="image" src="https://github.com/user-attachments/assets/252f1c92-3038-4f93-b4ed-b50d539a30b3" />




3. Start the Clients
Open multiple terminals and run the following command in each to start a client and you will be given a separate ID in ChatServer:
java org.example.ChatClient
<img width="1020" height="540" alt="image" src="https://github.com/user-attachments/assets/637e5f41-9f13-45d6-b544-5fa5c782df67" />











4. Usage
•	Type messages in the client window; messages will be broadcast to all other clients.
•	Type exit to disconnect a client.
<img width="827" height="588" alt="image" src="https://github.com/user-attachments/assets/6707194c-6515-4d24-882a-6fc0f0ed6f6a" />


