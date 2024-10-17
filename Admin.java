import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    private ArrayList<Vehicle> vehicles; // List untuk menyimpan kendaraan
    private Scanner sc = new Scanner(System.in);
    // Tambahkan di class Admin atau di dalam method main():
    ArrayList<Customer> pendingQueue = new ArrayList<>(); // Antrean verifikasi

    public Admin(UserProfile userProfile) {
        super(userProfile);
        this.vehicles = new ArrayList<>(); // Inisialisasi list kendaraan
    }

    // Method untuk menampilkan menu verifikasi dokumen customer
    public void verifyCustomerDocuments() {
        System.out.println("Memeriksa antrean verifikasi...");
        VerificationQueue.verifyNextCustomerInQueue(); // Verifikasi customer berikutnya
    }
    
    // Dalam kelas Admin
    public void verifyCustomer() {
        if (VerificationQueue.isEmpty()) {
            System.out.println("Tidak ada customer yang menunggu verifikasi.");
            return;
        }

        // Tampilkan daftar customer yang menunggu verifikasi
        System.out.println("Daftar customer yang menunggu verifikasi:");
        for (int i = 0; i < VerificationQueue.getPendingQueueSize(); i++) {
            Customer customer = VerificationQueue.getPendingCustomer(i);
            System.out.println((i + 1) + ". ID: " + customer.getUserProfile().getUserID() + ", Nama: " + customer.getUserProfile().getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Pilih customer yang akan diverifikasi: ");
        int choice = scanner.nextInt() - 1; // Mengurangi 1 untuk indeks

        if (choice >= 0 && choice < VerificationQueue.getPendingQueueSize()) {
            VerificationQueue.verifyNextCustomerInQueue(); // Memverifikasi customer yang dipilih
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }


    // Method untuk menambahkan kendaraan baru
    public Vehicle addKendaraan(int idKendaraan, Vehicle.JenisKendaraan jenisKendaraan, String merek, String warna) {
        if (findVehicleById(idKendaraan) != null) {
            return null;
        }
        Vehicle newVehicle = new Vehicle(idKendaraan, jenisKendaraan, merek, warna);
        vehicles.add(newVehicle);
        return newVehicle;
    }
    
    // Method untuk mengupdate detail kendaraan
    public void updateDetailKendaraan(int idKendaraan, Vehicle.JenisKendaraan jenisKendaraan, String merek, String warna) {
        Vehicle vehicleToUpdate = findVehicleById(idKendaraan);
        if (vehicleToUpdate != null) {
            vehicleToUpdate.setJenisKendaraan(jenisKendaraan); // Update jenis kendaraan
            vehicleToUpdate.setMerek(merek); // Update merek
            vehicleToUpdate.setWarna(warna); // Update warna
            System.out.println("Detail kendaraan berhasil diperbarui. ID Kendaraan: " + idKendaraan);
        } else {
            System.out.println("Kendaraan dengan ID " + idKendaraan + " tidak ditemukan.");
        }
    }

    // Method untuk mencari kendaraan berdasarkan ID
    private Vehicle findVehicleById(int idKendaraan) {
        for (Vehicle v : vehicles) {
            if (v.getIdKendaraan() == idKendaraan) {
                return v;
            }
        }
        return null;
    }

    // Method untuk menampilkan daftar kendaraan
    public void displayVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("Belum ada kendaraan yang ditambahkan.");
        } else {
            System.out.println("Daftar Kendaraan:");
            for (Vehicle v : vehicles) {
                System.out.println(" ID Kendaraan: " + v.getIdKendaraan() + ", Jenis: " + v.getJenisKendaraan());
            }
        }
    }

    // Method untuk menampilkan detail kendaraan berdasarkan ID
    public void displayVehicleDetail(int idKendaraan) {
        Vehicle vehicleToDisplay = findVehicleById(idKendaraan);
        if (vehicleToDisplay != null) {
            System.out.println(vehicleToDisplay.displayInfo());
        } else {
            System.out.println("Kendaraan dengan ID " + idKendaraan + " tidak ditemukan.");
        }
    }

    // Method untuk menampilkan daftar profil dan memungkinkan memilih profil berdasarkan ID
    public void displayProfiles(ArrayList<UserProfile> profiles) {
        if (profiles.isEmpty()) {
            System.out.println("Belum ada profil yang tersedia.");
            return;
        }
    
        System.out.println("Daftar Profil:");
        for (UserProfile profile : profiles) {
            System.out.println("ID: " + profile.getUserID() + ", Nama: " + profile.getName());
        }
    
        System.out.print("Masukkan ID profil yang ingin ditampilkan: ");
        int profileId = sc.nextInt();
        sc.nextLine(); // Konsumsi newline
    
        UserProfile foundProfile = null;
        for (UserProfile profile : profiles) {
            if (profile.getUserID() == profileId) {
                foundProfile = profile;
                break;
            }
        }
    
        if (foundProfile != null) {
            System.out.println(foundProfile.displayInfo());
        } else {
            System.out.println("Profil dengan ID " + profileId + " tidak ditemukan.");
        }
    }
    
    // Method untuk mengambil keluhan (complain) dari customer
    public String retrieveComplain(Customer customer) {
        String complain = customer.retrieveComplain();

        if (complain == null || complain.trim().isEmpty()) {
            complain = "Belum ada keluhan dari Customer"; // Mengubah null menjadi pesan yang sesuai
        }

        System.out.println("Keluhan dari customer: \n- " + complain);
        return complain;
    }

    // Method untuk memverifikasi user
    public boolean verifyUser(Customer customer) {
        boolean isVerified = customer.verifyUserDocs();
        if (isVerified) {
            System.out.println("User verified by admin.");
        } else {
            System.out.println("User verification failed by admin.");
        }
        return isVerified;
    }
}
