package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NameInputFrame());
    }
}

// Giao diện nhập tên
class NameInputFrame extends JFrame {
    private JTextField nameField;

    public NameInputFrame() {
        setTitle("Nhập tên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null); // Center screen

        JPanel panel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("Hãy nhập tên:");
        nameField = new JTextField(10);
        JButton okButton = new JButton("OK");

        panel.add(label);
        panel.add(nameField);
        panel.add(okButton);
        add(panel);

        okButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                dispose(); 
                new ChatFrame(name); 
            }
        });

        // Enter cũng OK
        nameField.addActionListener(e -> okButton.doClick());

        setVisible(true);
    }
}

// Giao diện chat
class ChatFrame extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private String userName;

    public ChatFrame(String name) {
        this.userName = name;

        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center screen

        // Layout chính
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Khu chat
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        mainPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Nhập tin nhắn
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("SEND");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        add(mainPanel);

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    private void sendMessage() {
        String msg = inputField.getText().trim();
        if (!msg.isEmpty()) {
            chatArea.append(userName + ": " + msg + "\n");
            inputField.setText("");
            chatArea.setCaretPosition(chatArea.getDocument().getLength());

        }
    }
}
