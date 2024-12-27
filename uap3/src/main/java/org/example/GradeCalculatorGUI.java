package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class GradeCalculatorGUI extends JFrame {
    private JTextField tfNama, tfNIM, tfTugas, tfQuiz, tfUTS, tfUAS, tfFotoPath;
    private JLabel lblRataRata, lblGrade, lblHasil;
    private JButton btnHitung, btnReset, btnSimpan, btnEdit, btnHapus;
    private JTable tabelHasil;
    private DefaultTableModel modelTabel;
    private int barisTerpilih = -1;  // Untuk melacak baris yang dipilih untuk edit atau hapus

    public GradeCalculatorGUI() {
        setTitle("Sistem Penilaian Siswa");
        setSize(1200, 800);  // Lebar ditambah untuk menampung tabel
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Judul
        JLabel judulLabel = new JLabel("Kalkulator Nilai", JLabel.CENTER);
        judulLabel.setFont(new Font("Arial", Font.BOLD, 24));
        judulLabel.setForeground(Color.DARK_GRAY);
        add(judulLabel, BorderLayout.NORTH);

        // Panel Input
        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(10, 5, 10, 10));  // 8 baris, 2 kolom
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInput.setBackground(new Color(230, 230, 255)); // Latar belakang kuning muda

        panelInput.add(new JLabel("Nama:"));
        tfNama = new JTextField(2);
        panelInput.add(tfNama);

        panelInput.add(new JLabel("NIM:"));
        tfNIM = new JTextField(15);
        panelInput.add(tfNIM);

        panelInput.add(new JLabel("Nilai Tugas:"));
        tfTugas = new JTextField(10);
        panelInput.add(tfTugas);

        panelInput.add(new JLabel("Nilai Quiz:"));
        tfQuiz = new JTextField(10);
        panelInput.add(tfQuiz);

        panelInput.add(new JLabel("Nilai UTS:"));
        tfUTS = new JTextField(10);
        panelInput.add(tfUTS);

        panelInput.add(new JLabel("Nilai UAS:"));
        tfUAS = new JTextField(10);
        panelInput.add(tfUAS);
    }
}