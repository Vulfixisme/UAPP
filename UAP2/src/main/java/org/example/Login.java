package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    public static void main(String[] args) {
        JFrame frame = new JFrame("ISS Karang Baru");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(5, 230, 700, 50);
        JLabel headerLabel = new JLabel("Informasi Siswa SDN 04 Karang Baru", JLabel.CENTER);
        headerLabel.setForeground(Color.blue);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 13));
        headerPanel.add(headerLabel);

        JPanel HeaderPanel2 = new JPanel();
        HeaderPanel2.setBounds(5, 205, 700, 50);
        JLabel headerLabel2 = new JLabel("SDN 04 Karang Baru", JLabel.CENTER);
        headerLabel2.setForeground(Color.blue);
        headerLabel2.setFont(new Font("arial", Font.BOLD, 18));
        HeaderPanel2.add(headerLabel2);

        ImageIcon icon = new ImageIcon("C:\\Users\\ADMIN\\Documents\\kuliah\\tugas\\pppp\\UAP2\\src\\main\\java\\foto\\Foto.png");
        Image scaledImage = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(270, 15, 180, 180);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(225, 280, 80, 25);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(300, 280, 180, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(225, 330, 80, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(300, 330, 180, 25);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(325, 380, 100, 30);
        loginButton.setBackground(new Color(0, 74, 138));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel resultLabel = new JLabel("", JLabel.CENTER);
        resultLabel.setBounds(150, 425, 400, 30);
        resultLabel.setForeground(new Color(255, 69, 0));
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        // Menambahkan komponen ke frame
        frame.add(headerPanel);
        frame.add(HeaderPanel2);
        frame.add(imageLabel);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(resultLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validasi login
                if (username.equals("adm1n") && password.equals("adm1n")) {
                    resultLabel.setText("Login berhasil sebagai Admin");
                    admin.openAdminFrame();
                    frame.setVisible(false);
                } else if (username.equals("siswa") && password.equals("s1swa")) {
                    resultLabel.setText("Login berhasil sebagai Siswa");
                    siswa.openSiswaFrame();
                    frame.setVisible(false);
                } else {
                    resultLabel.setText("Username atau password salah");
                }
            }
        });

        // Menampilkan frame
        frame.setVisible(true);
    }
}
