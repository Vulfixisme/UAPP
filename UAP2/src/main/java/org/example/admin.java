package org.example;

import javax.swing.*;
import java.awt.*;

public class admin {
    // Membuka frame baru untuk Admin
    static void openAdminFrame() {
        JFrame frame = new JFrame("Informasi Siswa SMP Negeri 01 Merangin");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Panel kiri
        JPanel leftPanel = new JPanel();
        leftPanel.setBounds(0, 0, 185, 600);
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

        JLabel titleLabel2 = new JLabel("SDN 04 Marangin");
        titleLabel2.setBounds(20, 140, 150, 40);
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 13));
        titleLabel2.setForeground(Color.blue);
        titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(titleLabel2);

        // Tombol menu "Biodata Siswa"
        JButton tambahSiswa = new JButton("Tambah Siswa");
        tambahSiswa.setBounds(0, 180, 200, 40);
        tambahSiswa.setFont(new Font("Arial", Font.BOLD, 14));
        tambahSiswa.setBackground(new Color(240, 240, 240));
        leftPanel.add(tambahSiswa);

        // Tombol menu "Nilai Siswa"
        JButton nilaiButton = new JButton("Nilai Siswa");
        nilaiButton.setBounds(0, 220, 200, 40);
        nilaiButton.setFont(new Font("Arial", Font.BOLD, 14));
        nilaiButton.setBackground(new Color(240, 240, 240));
        leftPanel.add(nilaiButton);

        JButton jadwalBaru = new JButton("Jadwal Mapel");
        jadwalBaru.setBounds(0,260,200,40);
        jadwalBaru.setFont(new Font("Arial", Font.BOLD,14));
        jadwalBaru.setBackground(new Color(240, 240,240));
        leftPanel.add(jadwalBaru);

        // Panel kanan
        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(90, 0, 600, 600);
        rightPanel.setBackground(new Color(0, 38, 77));

        // Menambahkan panel ke frame
        frame.add(leftPanel);
        frame.add(rightPanel);

        // Menampilkan frame
        frame.setVisible(true);
    }
}
