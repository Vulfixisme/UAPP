GradeCalculatorGUI adalah aplikasi berbasis Java yang menggunakan pustaka Swing untuk membangun antarmuka pengguna grafis (GUI). Aplikasi ini dirancang untuk membantu pengguna dalam menghitung nilai rata-rata, menentukan grade, dan status kelulusan siswa berdasarkan input data yang diberikan.

Input Data Siswa:
•	Nama
•	NIM
•	Nilai Tugas, Quiz, UTS, dan UAS
•	Path file foto siswa (dengan pratinjau langsung di tabel)
      
    panelInput.add(new JLabel("Nilai UAS:"));
    tfUAS = new JTextField(10);
    panelInput.add(tfUAS);

    panelInput.add(new JLabel("Path Foto:"));
    tfFotoPath = new JTextField(5);
    panelInput.add(tfFotoPath);

    JButton btnPilihFoto = new JButton("Pilih Foto");
    btnPilihFoto.addActionListener(e -> pilihPathFoto());
    panelInput.add(btnPilihFoto);

Operasi Data:
•	Hitung: Menghitung rata-rata nilai dan menentukan grade serta status kelulusan.
•	Reset: Menghapus semua data dari form input.
•	Hapus: Menghapus data siswa yang dipilih dari tabel.

      btnHitung = new JButton("Hitung");
    btnHitung.addActionListener(e -> hitungNilai());

    private void hitungNilai() {
    double tugas = Double.parseDouble(tfTugas.getText());
    double quiz = Double.parseDouble(tfQuiz.getText());
    double uts = Double.parseDouble(tfUTS.getText());
    double uas = Double.parseDouble(tfUAS.getText());

    double rataRata = (tugas * 0.3) + (quiz * 0.2) + (uts * 0.25) + (uas * 0.25);
    String grade = (rataRata >= 85) ? "A" : (rataRata >= 70) ? "B" : (rataRata >= 55) ? "C" : (rataRata >= 40) ? "D" : "E";
    String hasil = (rataRata >= 55) ? "Lulus" : "Tidak Lulus";

    modelTabel.addRow(new Object[]{tfNama.getText(), tfNIM.getText(), String.format("%.2f", rataRata), grade, hasil, tfFotoPath.getText()});
    resetField();
    }

    btnReset = new JButton("Reset");
    btnReset.addActionListener(e -> resetField());

    private void resetField() {
    tfNama.setText("");
    tfNIM.setText("");
    tfTugas.setText("");
    tfQuiz.setText("");
    tfUTS.setText("");
    tfUAS.setText("");
    tfFotoPath.setText("");
    }

    btnHapus = new JButton("Hapus");
    btnHapus.addActionListener(e -> hapusData());

    private void hapusData() {
    if (barisTerpilih != -1) {
        modelTabel.removeRow(barisTerpilih);
        resetField();
        barisTerpilih = -1;
    }
    }

    
    

Antarmuka Tabel:
•	Menampilkan data siswa yang telah dimasukkan, termasuk pratinjau foto.
•	Mendukung seleksi baris untuk mengedit atau menghapus data.
            
            String[] namaKolom = {"Nama", "NIM", "Rata-rata", "Grade", "Hasil", "Foto"};
            modelTabel = new DefaultTableModel(namaKolom, 0);
            tabelHasil = new JTable(modelTabel);

      tabelHasil.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
            if (value != null && value instanceof String) {
            ImageIcon icon = new ImageIcon((String) value);
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            return new JLabel(icon);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
    });


Validasi Input:
•	Memastikan nilai numerik berada dalam rentang 0–100.
•	Menampilkan pesan kesalahan untuk input yang tidak valid.

        try {
    double tugas = Double.parseDouble(tfTugas.getText());
    double quiz = Double.parseDouble(tfQuiz.getText());
    double uts = Double.parseDouble(tfUTS.getText());
    double uas = Double.parseDouble(tfUAS.getText());

    if (tugas < 0 || tugas > 100 || quiz < 0 || quiz > 100 || uts < 0 || uts > 100 || uas < 0 || uas > 100) {
        throw new Exception("Nilai harus antara 0 dan 100.");
    }
    } catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Silakan masukkan nilai numerik yang valid.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
    JOptionPane.showMessageDialog(this, e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
    }


Penggunaan Unit Testing:

Kode menggunakan anotasi 
@Test dari pustaka JUnit untuk membuat unit test, yang merupakan praktik pengembangan perangkat unit

Fungsi assertEquals membandingkan dua nilai (ekspektasi dan hasil aktual) dalam sebuah pengujian unit. 

    package org.example;

    import org.junit.Test;

    import static org.junit.Assert.assertEquals;

    public  class ResetFieldTest {

        @Test
        public void hitungNilai() {
            // Input values

                double tugas = 90;
                double quiz = 80;
                double uts = 85;
                double uas = 75;

                // Calculate expected average
                double expectedAverage = 82.0; // Sesuai rumus yang digunakan

                // Calculate actual average
                double actualAverage = (tugas * 0.2) + (quiz * 0.2) + (uts * 0.3) + (uas * 0.3);

                // Assert that the calculated average is correct
                assertEquals(expectedAverage, actualAverage, 0.01); // Toleransi 0.01 untuk nilai desimal


        }
      }

  Pada bagian class main
  terdapat main, sebagai fungsi utama dari sebuah program
  dan deklarasi dari  GradeCalculatorGUI();
  
    package org.example;

    public class Main {
    public static void main(String[] args) {
        new GradeCalculatorGUI();
    }
    }
