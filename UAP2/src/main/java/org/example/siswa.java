package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class siswa {
    // Membuka frame baru untuk Siswa
    static void openSiswaFrame() {
        JFrame frame = new JFrame("Informasi Siswa SMP Negeri 01 Merangin");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Panel kiri
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 185, 500);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(null);

        // Menambahkan logo
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\ADMIN\\Documents\\kuliah\\tugas\\pppp\\UAP2\\src\\main\\java\\foto\\Foto.png"); // Ganti dengan path gambar logo Anda
        Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(40, 20, 100, 100);
        leftPanel.add(logoLabel);

        // Menambahkan judul
        JLabel titleLabel = new JLabel("Informasi Siswa", JLabel.CENTER);
        titleLabel.setBounds(20, 120, 150, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.blue);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(titleLabel);

        JLabel titleLabel2 = new JLabel("SDN 04 Merangin");
        titleLabel2.setBounds(20, 140, 150, 40);
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 13));
        titleLabel2.setForeground(Color.blue);
        titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(titleLabel2);

        // Tombol menu "Biodata Siswa"
        JButton biodataButton = new JButton("Biodata Siswa");
        biodataButton.setBounds(0, 180, 200, 40);
        biodataButton.setFont(new Font("Arial", Font.BOLD, 14));
        biodataButton.setBackground(new Color(240, 240, 240));
        leftPanel.add(biodataButton);

        // Tombol menu "Nilai Siswa"
        JButton nilaiButton = new JButton("Nilai Siswa");
        nilaiButton.setBounds(0, 220, 200, 40);
        nilaiButton.setFont(new Font("Arial", Font.BOLD, 14));
        nilaiButton.setBackground(new Color(240, 240, 240));
        leftPanel.add(nilaiButton);

        // Tombol menu "Jadwal Mapel"
        JButton jadwalButton = new JButton("Jadwal Mapel");
        jadwalButton.setBounds(0, 260, 200, 40);
        jadwalButton.setFont(new Font("Arial", Font.BOLD, 14));
        jadwalButton.setBackground(new Color(240, 240, 240));
        leftPanel.add(jadwalButton);

        // Tombol Back
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 420, 80, 30);
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        backButton.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        // Panel kanan
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(185, 0, 515, 500);
        rightPanel.setBackground(new Color(0, 38, 77));

        // Listener untuk tombol Back
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Menutup frame saat ini
                // Anda dapat menambahkan logika untuk kembali ke frame sebelumnya di sini
            }
        });

        // Menambahkan panel ke frame
        frame.add(leftPanel);
        frame.add(rightPanel);

        // Menampilkan frame
        frame.setVisible(true);
    }
}
