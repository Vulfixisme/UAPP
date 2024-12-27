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

        // Tambahkan Path Foto
        panelInput.add(new JLabel("Path Foto:"));
        tfFotoPath = new JTextField(5);
        panelInput.add(tfFotoPath);

        JButton btnPilihFoto = new JButton("Pilih Foto");
        btnPilihFoto.setForeground(Color.WHITE);
        btnPilihFoto.setFont(new Font("Arial", Font.BOLD, 14));
        btnPilihFoto.setBackground(new Color(70, 130, 180));
        btnPilihFoto.addActionListener(e -> pilihPathFoto());
        panelInput.add(btnPilihFoto);

        // Tabel Hasil (kanan)
        String[] namaKolom = {"Nama", "NIM", "Rata-rata", "Grade", "Hasil", "Foto"};
        modelTabel = new DefaultTableModel(namaKolom, 0);
        tabelHasil = new JTable(modelTabel);
        tabelHasil.setFillsViewportHeight(true);
        tabelHasil.setBackground(new Color(230, 230, 255)); // Latar belakang biru muda
        tabelHasil.setSelectionBackground(new Color(135, 206, 250)); // Biru langit muda untuk seleksi
        tabelHasil.getTableHeader().setBackground(new Color(100, 149, 237)); // Biru cornflower untuk header
        tabelHasil.getSelectionModel().addListSelectionListener(e -> pilihBarisUntukEditHapus());

        // Renderer kustom untuk menampilkan gambar di tabel
        tabelHasil.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value != null && value instanceof String) {
                    String pathFoto = (String) value;
                    ImageIcon icon = new ImageIcon(pathFoto);
                    Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);  // Resize gambar
                    icon = new ImageIcon(image);
                    return new JLabel(icon);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
        JScrollPane tabelScrollPane = new JScrollPane(tabelHasil);

        // Panel Pisah untuk menampilkan input di kiri dan tabel di kanan
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelInput, tabelScrollPane);
        splitPane.setDividerLocation(500);  // Menyesuaikan posisi pemisah
        add(splitPane, BorderLayout.CENTER);

        // Panel Tombol
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelTombol.setBackground(new Color(230, 230, 255)); // Sesuaikan warna latar belakang

        btnHitung = new JButton("Hitung");
        btnHitung.setBackground(new Color(70, 130, 180)); // Warna tombol hijau hutan
        btnHitung.setForeground(Color.WHITE);
        btnHitung.setFont(new Font("Arial", Font.BOLD, 14));
        btnHitung.addActionListener(e -> hitungNilai());
        panelTombol.add(btnHitung);

        btnReset = new JButton("Reset");
        btnReset.setBackground(new Color(70, 130, 180)); // Warna tombol tomat
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Arial", Font.BOLD, 14));
        btnReset.addActionListener(e -> resetField());
        panelTombol.add(btnReset);

        btnSimpan = new JButton("Simpan");
        btnSimpan.setBackground(new Color(70, 130, 180)); // Warna tombol biru baja
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFont(new Font("Arial", Font.BOLD, 14));
        btnSimpan.addActionListener(e -> simpanData());
        panelTombol.add(btnSimpan);

        btnEdit = new JButton("Edit");
        btnEdit.setBackground(new Color(70, 130, 180)); // Warna tombol emas
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Arial", Font.BOLD, 14));
        btnEdit.addActionListener(e -> editData());
        panelTombol.add(btnEdit);

        btnHapus = new JButton("Hapus");
        btnHapus.setBackground(new Color(70, 130, 180)); // Warna tombol merah
        btnHapus.setForeground(Color.WHITE);
        btnHapus.setFont(new Font("Arial", Font.BOLD, 14));
        btnHapus.addActionListener(e -> hapusData());
        panelTombol.add(btnHapus);

        add(panelTombol, BorderLayout.PAGE_END);

        // Menampilkan dan menyesuaikan lokasi
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void pilihBarisUntukEditHapus() {
        barisTerpilih = tabelHasil.getSelectedRow();
        if (barisTerpilih >= 0) {
            tfNama.setText((String) modelTabel.getValueAt(barisTerpilih, 0));
            tfNIM.setText((String) modelTabel.getValueAt(barisTerpilih, 1));
            tfFotoPath.setText((String) modelTabel.getValueAt(barisTerpilih, 5));
        }
    }

    private void pilihPathFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih Foto Siswa");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("File Gambar", "jpg", "jpeg", "png", "gif"));
        int hasil = fileChooser.showOpenDialog(this);
        if (hasil == JFileChooser.APPROVE_OPTION) {
            File fileTerpilih = fileChooser.getSelectedFile();
            tfFotoPath.setText(fileTerpilih.getAbsolutePath());
        }
    }

    private void hitungNilai() {
        try {
            String nama = tfNama.getText();
            String nim = tfNIM.getText();
            if (nama.isEmpty() || nim.isEmpty()) {
                throw new Exception("Nama dan NIM tidak boleh kosong.");
            }

            double tugas = Double.parseDouble(tfTugas.getText());
            double quiz = Double.parseDouble(tfQuiz.getText());
            double uts = Double.parseDouble(tfUTS.getText());
            double uas = Double.parseDouble(tfUAS.getText());

            if (tugas < 0 || quiz < 0 || uts < 0 || uas < 0 || tugas > 100 || quiz > 100 || uts > 100 || uas > 100) {
                throw new Exception("Nilai harus antara 0 dan 100.");
            }

            double rataRata = (tugas * 0.3) + (quiz * 0.2) + (uts * 0.25) + (uas * 0.25);
            lblRataRata = new JLabel(String.format("%.2f", rataRata));

            String grade;
            if (rataRata >= 85) {
                grade = "A";
            } else if (rataRata >= 70) {
                grade = "B";
            } else if (rataRata >= 55) {
                grade = "C";
            } else if (rataRata >= 40) {
                grade = "D";
            } else {
                grade = "E";
            }
            lblGrade = new JLabel(grade);

            lblHasil = new JLabel(rataRata >= 55 ? "Lulus" : "Tidak Lulus");

            String fotoPath = tfFotoPath.getText();

            // Tambahkan hasil ke tabel jika itu entri baru
            if (barisTerpilih == -1) {
                modelTabel.addRow(new Object[]{nama, nim, String.format("%.2f", rataRata), grade, lblHasil.getText(), fotoPath});
            } else {
                // Perbarui baris jika itu edit
                modelTabel.setValueAt(nama, barisTerpilih, 0);
                modelTabel.setValueAt(nim, barisTerpilih, 1);
                modelTabel.setValueAt(String.format("%.2f", rataRata), barisTerpilih, 2);
                modelTabel.setValueAt(grade, barisTerpilih, 3);
                modelTabel.setValueAt(lblHasil.getText(), barisTerpilih, 4);
                modelTabel.setValueAt(fotoPath, barisTerpilih, 5);
            }

            resetField();
            barisTerpilih = -1;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Silakan masukkan nilai numerik yang valid untuk nilai.", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetField() {
        tfNama.setText("");
        tfNIM.setText("");
        tfTugas.setText("");
        tfQuiz.setText("");
        tfUTS.setText("");
        tfUAS.setText("");
        tfFotoPath.setText("");
    }

    private void simpanData() {
        try {
            String nama = tfNama.getText();
            String nim = tfNIM.getText();
            double rataRata = Double.parseDouble(lblRataRata.getText());
            String grade = lblGrade.getText();
            String hasil = lblHasil.getText();
            String fotoPath = tfFotoPath.getText();

            Connection conn = DriverManager.getConnection("jdbc:sqlite:siswa.db");
            String sql = "INSERT INTO nilai_siswa (nama, nim, rata_rata, grade, hasil, foto) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nama);
            pstmt.setString(2, nim);
            pstmt.setDouble(3, rataRata);
            pstmt.setString(4, grade);
            pstmt.setString(5, hasil);
            pstmt.setString(6, fotoPath);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Kesalahan database: " + ex.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Silakan hitung nilai terlebih dahulu sebelum menyimpan.", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editData() {
        if (barisTerpilih != -1) {
            String nama = tfNama.getText();
            String nim = tfNIM.getText();
            double rataRata = Double.parseDouble(lblRataRata.getText());
            String grade = lblGrade.getText();
            String hasil = lblHasil.getText();
            String fotoPath = tfFotoPath.getText();

            modelTabel.setValueAt(nama, barisTerpilih, 0);
            modelTabel.setValueAt(nim, barisTerpilih, 1);
            modelTabel.setValueAt(String.format("%.2f", rataRata), barisTerpilih, 2);
            modelTabel.setValueAt(grade, barisTerpilih, 3);
            modelTabel.setValueAt(hasil, barisTerpilih, 4);
            modelTabel.setValueAt(fotoPath, barisTerpilih, 5);

            resetField();
            barisTerpilih = -1;
        }
    }

    private void hapusData() {
        if (barisTerpilih != -1) {
            modelTabel.removeRow(barisTerpilih);
            resetField();
            barisTerpilih = -1;
        }
    }
}
