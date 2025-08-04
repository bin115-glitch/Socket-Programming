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
 




3. Start the Clients
Open multiple terminals and run the following command in each to start a client and you will be given a separate ID in ChatServer:
java org.example.ChatClient











4. Usage
•	Type messages in the client window; messages will be broadcast to all other clients.
•	Type exit to disconnect a client.
 

