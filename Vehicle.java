public class Vehicle {
    private int idKendaraan; // Atribut idKendaraan
    private String merek;
    private String warna;
    
    public enum JenisKendaraan {
        MOBIL, MOTOR
    }
    
    private JenisKendaraan jenisKendaraan;

    // Constructor untuk Vehicle
    public Vehicle(int idKendaraan, JenisKendaraan jenisKendaraan, String merek, String warna) {
        this.idKendaraan = idKendaraan;
        this.jenisKendaraan = jenisKendaraan;
        this.merek = merek;
        this.warna = warna;
    }
    
    // Getter untuk idKendaraan
    public int getIdKendaraan() {
        return idKendaraan;
    }

    public JenisKendaraan getJenisKendaraan() {
        return jenisKendaraan;
    }

    public String getMerek() {
        return merek;
    }

    public String getWarna() {
        return warna;
    }

    // Setter untuk idKendaraan (jika diperlukan)
    public void setIdKendaraan(int idKendaraan) {
        if (idKendaraan > 0) {
            this.idKendaraan = idKendaraan;
        } else {
            System.out.println("ID Kendaraan harus lebih dari 0.");
        }
    }
    
    public void setJenisKendaraan(JenisKendaraan jenisKendaraan) {
        if (jenisKendaraan != null) {
            this.jenisKendaraan = jenisKendaraan;
        } else {
            System.out.println("Jenis kendaraan tidak boleh kosong.");
        }
    }
    
    public void setMerek(String merek) {
        this.merek = merek;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String displayInfo() {
        return "  ID Kendaraan: " + idKendaraan + "\n" +
        "  Jenis Kendaraan: " + jenisKendaraan + "\n" +
        "  Merek: " + merek + "\n" +
        "  Warna: " + warna;
    }
}