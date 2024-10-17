import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] admin1Docs = {"certificate.jpg", "profile.png", "admin_license.pdf"};
        // Inisialisasi UserProfile untuk Admin
        UserProfile admin1 = new UserProfile(1, "1", "Rahmalia Mutia Farda", 35, "1@gmail.com", admin1Docs);
        Admin admin = new Admin(admin1);

        // Verifikasi dokumen admin saat program dijalankan
        for (String doc : admin1Docs) {
            admin.getUserProfile().addVerifiedDocument(doc); // Menambahkan dokumen terverifikasi
        }
        admin.updateVerificationStatus(true); // Mengupdate status verifikasi admin

        // Inisialisasi UserProfile untuk Customer
        UserProfile customer1 = new UserProfile(2, "2", "Dimas Setyo Nugroho", 28, "2@gmail.com", null);
        Customer customer = new Customer(customer1);
        // Tampilkan profil
        ArrayList<UserProfile> profiles = new ArrayList<>();
        profiles.add(admin1); // Tambahkan profil admin
        profiles.add(customer1); // Tambahkan profil customer


        admin.addKendaraan(1, Vehicle.JenisKendaraan.Mobil, "Toyota", "Merah"); // Menambahkan kendaraan pertama
        admin.addKendaraan(2, Vehicle.JenisKendaraan.Motor, "Yamaha", "Hitam"); // Menambahkan kendaraan kedua

        // Simulasi login untuk admin dan customer
        Scanner sc = new Scanner(System.in);

        // Menu Pilihan Login
        boolean loginMenu = true;
        while (loginMenu) {
            System.out.println("\n--- Pilih Login ---");
            System.out.println("-/1. Login sebagai Admin");
            System.out.println("-/2. Login sebagai Customer");
            System.out.println("-/0. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1:
                    // Login Admin
                    System.out.println("\n--- Login Admin ---");
                    System.out.print("Masukkan User ID: ");
                    int adminUserId = sc.nextInt();
                    sc.nextLine(); // Konsumsi newline
                    System.out.print("Masukkan Password: ");
                    String adminPassword = sc.nextLine();

                    if (admin.login(adminUserId, adminPassword)) {
                        // Menu untuk Admin
                        boolean adminMenu = true;
                        while (adminMenu) {
                            System.out.println("\n--- Menu Admin ---");
                            System.out.println("-/1. Update Detail Kendaraan");
                            System.out.println("-/2. Tambah Kendaraan");
                            System.out.println("-/3. Tampilkan Kendaraan");
                            System.out.println("-/4. Ambil Keluhan dari Customer");
                            System.out.println("-/5. Verifikasi Customer");
                            System.out.println("-/6. Tampilkan Profil");
                            System.out.println("-/7. Edit Profil");
                            System.out.println("-/0. Logout");
                            System.out.print("Pilih opsi: ");

                            int adminChoice = sc.nextInt();
                            sc.nextLine(); // Konsumsi newline

                            switch (adminChoice) {
                                case 1:
                                    admin.displayVehicles();
                                    // Proses update kendaraan
                                    System.out.print("Masukkan ID Kendaraan yang ingin diperbarui: ");
                                    int updateVehicleId = sc.nextInt();
                                    sc.nextLine();
                                    admin.displayVehicleDetail(updateVehicleId);
                                    System.out.println("Edit jenis kendaraan:");
                                    System.out.println("1. Mobil");
                                    System.out.println("2. Motor");
                                    System.out.print("Pilih opsi: ");
                                    int jenisKendaraanUpdate = sc.nextInt();
                                    sc.nextLine();

                                    Vehicle.JenisKendaraan jenisKendaraanEnumUpdate;
                                    switch (jenisKendaraanUpdate) {
                                        case 1:
                                            jenisKendaraanEnumUpdate = Vehicle.JenisKendaraan.Mobil;
                                            break;
                                        case 2:
                                            jenisKendaraanEnumUpdate = Vehicle.JenisKendaraan.Motor;
                                            break;
                                        default:
                                            System.out.println("Jenis kendaraan tidak valid. Harap masukkan '1' untuk Mobil atau '2' untuk Motor.");
                                            continue;
                                    }

                                    System.out.print("Edit merek kendaraan: ");
                                    String merekUpdate = sc.nextLine();
                                    System.out.print("Edit warna kendaraan: ");
                                    String warnaUpdate = sc.nextLine();

                                    admin.updateDetailKendaraan(updateVehicleId, jenisKendaraanEnumUpdate, merekUpdate, warnaUpdate);
                                    break;

                                case 2:
                                    // Proses tambah kendaraan
                                    System.out.print("Masukkan ID Kendaraan baru: ");
                                    int newVehicleId = sc.nextInt();
                                    sc.nextLine();

                                    System.out.println("Masukkan jenis kendaraan:");
                                    System.out.println("1. Mobil");
                                    System.out.println("2. Motor");
                                    System.out.print("Pilih opsi: ");
                                    int jenisKendaraanInput = sc.nextInt();
                                    sc.nextLine();

                                    Vehicle.JenisKendaraan jenisKendaraanEnum;
                                    switch (jenisKendaraanInput) {
                                        case 1:
                                            jenisKendaraanEnum = Vehicle.JenisKendaraan.Mobil;
                                            break;
                                        case 2:
                                            jenisKendaraanEnum = Vehicle.JenisKendaraan.Motor;
                                            break;
                                        default:
                                            System.out.println("Pilihan tidak valid.");
                                            continue;
                                    }

                                    System.out.print("Masukkan merek kendaraan: ");
                                    String merek = sc.nextLine();
                                    System.out.print("Masukkan warna kendaraan: ");
                                    String warna = sc.nextLine();

                                    admin.addKendaraan(newVehicleId, jenisKendaraanEnum, merek, warna);
                                    break;

                                case 3: // Tampilkan Kendaraan
                                    admin.displayVehicles(); // Menampilkan daftar kendaraan
                                    System.out.print("Masukkan ID Kendaraan untuk melihat detail: ");
                                    int vehicleId = sc.nextInt();
                                    sc.nextLine();
                                    admin.displayVehicleDetail(vehicleId); // Menampilkan detail kendaraan berdasarkan ID
                                    break;

                                case 4:
                                    // Ambil keluhan dari customer
                                    System.out.println("Ambil keluhan dari customer.");
                                    System.out.println("ID Customer: " + customer1.getUserID());
                                    admin.retrieveComplain(customer);
                                    break;

                                case 5: // Verifikasi Customer
                                    if (!VerificationQueue.isEmpty()) {
                                        System.out.println("Daftar customer yang menunggu verifikasi:");
                                        for (int i = 0; i < VerificationQueue.getPendingQueueSize(); i++) {
                                            Customer c = VerificationQueue.getPendingCustomer(i);
                                            System.out.println((i + 1) + ". ID: " + c.getUserProfile().getUserID() + ", Nama: " + c.getUserProfile().getName());
                                        }
                                        System.out.print("Pilih customer yang akan diverifikasi: ");
                                        int selectedIndex = sc.nextInt() - 1; // Mengurangi 1 untuk mendapatkan indeks yang benar
                                        sc.nextLine();
                                
                                        if (selectedIndex >= 0 && selectedIndex < VerificationQueue.getPendingQueueSize()) {
                                            Customer selectedCustomer = VerificationQueue.getPendingCustomer(selectedIndex);
                                            if (!selectedCustomer.getVerificationStatus()) {
                                                if (selectedCustomer.verifyUserDocs()) {
                                                    for (String doc : selectedCustomer.getDocuments()) {
                                                        selectedCustomer.getUserProfile().addVerifiedDocument(doc); // Tambah dokumen yang terverifikasi
                                                    }
                                                    selectedCustomer.updateVerificationStatus(true); // Update status verifikasi
                                                    VerificationQueue.removePendingCustomer(selectedCustomer); // Hapus dari antrean
                                                    System.out.println("Customer berhasil diverifikasi.");
                                                } else {
                                                    System.out.println("User verification failed. No documents provided.");
                                                }
                                            } else {
                                                System.out.println("Customer sudah terverifikasi.");
                                            }
                                        } else {
                                            System.out.println("Pilihan tidak valid.");
                                        }
                                    } else {
                                        System.out.println("Tidak ada customer yang menunggu verifikasi.");
                                    }
                                    break;
                                
                                case 6:
                                    
                                    System.out.println("Daftar ID Pengguna yang tersedia:");
                                    for (UserProfile profile : profiles) {
                                        System.out.println("ID: " + profile.getUserID() + ", Nama: " + profile.getName());
                                    }

                                    System.out.print("Masukkan ID profil yang ingin ditampilkan: ");
                                    int inputUserId = sc.nextInt();
                                    sc.nextLine(); // Konsumsi newline

                                    boolean found = false;
                                    for (UserProfile profile : profiles) {
                                        if (profile.getUserID() == inputUserId) {
                                            System.out.println("\nProfil Ditemukan:");
                                            System.out.println(profile.toString()); // Menampilkan detail profil
                                            found = true;
                                            break;
                                        }
                                    }

                                    if (!found) {
                                        System.out.println("Profil dengan ID " + inputUserId + " tidak ditemukan.");
                                    }
                                    break;
                                
                                    case 7: // Edit Profil
                                    System.out.println("--- Edit Profil ---");
                                    System.out.println("-/1. Edit Profil Admin");
                                    System.out.println("-/2. Edit Profil Customer");
                                    System.out.print("Pilih opsi: ");
                                    int editOption = sc.nextInt();
                                    sc.nextLine(); // Konsumsi newline

                                    if (editOption == 1) {
                                        // Edit Profil Admin
                                        System.out.println("--- Edit Profil Admin ---");
                                        System.out.println("-/1. Ubah Nama");
                                        System.out.println("-/2. Ubah Usia");
                                        System.out.println("-/3. Ubah Email");
                                        System.out.print("Pilih data yang ingin diedit: ");
                                        int editChoice = sc.nextInt();
                                        sc.nextLine(); // Konsumsi newline

                                        switch (editChoice) {
                                            case 1:
                                                System.out.print("Masukkan nama baru: ");
                                                String newName = sc.nextLine();
                                                admin.editProfile("name", newName);
                                                break;
                                            case 2:
                                                System.out.print("Masukkan usia baru: ");
                                                int newAge = sc.nextInt();
                                                sc.nextLine();
                                                admin.editProfile("age", String.valueOf(newAge));
                                                break;
                                            case 3:
                                                System.out.print("Masukkan email baru: ");
                                                String newEmail = sc.nextLine();
                                                admin.editProfile("email", newEmail);
                                                break;
                                            default:
                                                System.out.println("Pilihan tidak valid.");
                                        }
                                    } else if (editOption == 2) {
                                        // Edit Profil Customer
                                        System.out.println("--- Edit Profil Customer ---");
                                        System.out.print("Masukkan ID Customer yang ingin diedit: ");
                                        int customerIdToEdit = sc.nextInt();
                                        sc.nextLine(); // Konsumsi newline

                                        // Menggunakan contoh customer statis. 
                                        if (customer1.getUserID() == customerIdToEdit) {
                                            System.out.println("-/1. Ubah Nama");
                                            System.out.println("-/2. Ubah Usia");
                                            System.out.println("-/3. Ubah Email");
                                            System.out.print("Pilih data yang ingin diedit: ");
                                            int editCustomerChoice = sc.nextInt();
                                            sc.nextLine(); // Konsumsi newline

                                            switch (editCustomerChoice) {
                                                case 1:
                                                    System.out.print("Masukkan nama baru: ");
                                                    String newCustomerName = sc.nextLine();
                                                    customer.editProfile("name", newCustomerName);
                                                    break;
                                                case 2:
                                                    System.out.print("Masukkan usia baru: ");
                                                    int newCustomerAge = sc.nextInt();
                                                    sc.nextLine();
                                                    customer.editProfile("age", String.valueOf(newCustomerAge));
                                                    break;
                                                case 3:
                                                    System.out.print("Masukkan email baru: ");
                                                    String newCustomerEmail = sc.nextLine();
                                                    customer.editProfile("email", newCustomerEmail);
                                                    break;
                                                default:
                                                    System.out.println("Pilihan tidak valid.");
                                            }
                                        } else {
                                            System.out.println("Customer dengan ID " + customerIdToEdit + " tidak ditemukan.");
                                        }
                                    } else {
                                        System.out.println("Pilihan tidak valid.");
                                    }
                                    break;
                                case 0:
                                    admin.logout();
                                    adminMenu = false;
                                    break;

                                default:
                                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                            }
                        }
                    }
                    break;

                case 2:
                    // Login Customer
                    System.out.println("\n--- Login Customer ---");
                    System.out.print("Masukkan User ID: ");
                    int customerUserId = sc.nextInt();
                    sc.nextLine(); // Konsumsi newline
                    System.out.print("Masukkan Password: ");
                    String customerPassword = sc.nextLine();

                    if (customer.login(customerUserId, customerPassword)) {
                        // Menu untuk Customer
                        boolean customerMenu = true;
                        while (customerMenu) {
                            System.out.println("\n--- Menu Customer ---");
                            System.out.println("-/1. Status Verifikasi");
                            System.out.println("-/2. Verifikasi Dokumen");
                            System.out.println("-/3. Ajukan Keluhan");
                            System.out.println("-/0. Logout");
                            System.out.print("Pilih opsi: ");

                            int customerChoice = sc.nextInt();
                            sc.nextLine(); // Konsumsi newline

                            switch (customerChoice) {
                                case 1:
                                        // Cek status verifikasi customer
                                if (customer.getVerificationStatus()) {
                                    System.out.println("Status Verifikasi Dokumen: Terverifikasi");
                                } else {
                                    System.out.println("Status Verifikasi Dokumen: Belum terverifikasi");
                            }
                            break;
                                case 2:
                                    // Tambah Dokumen dan Apply Verifikasi
                                    System.out.print("Masukkan nama dokumen (akhiri dengan .jpg, .png, atau .pdf): ");
                                    String documentName = sc.nextLine();
                                    customer.addDocument(documentName);
                                    
                                    // Mengajukan verifikasi setelah menambahkan dokumen
                                    if (customer.applyVerification()) {
                                        System.out.println("Pengajuan verifikasi berhasil. Tunggu untuk diverifikasi oleh admin.");
                                    }
                                    break;
                            
                                case 3:
                                    // Proses ajukan keluhan
                                    System.out.print("Masukkan keluhan: ");
                                    String keluhan = sc.nextLine();
                                    customer.submitComplain(keluhan); // Kirim keluhan ke admin
                                    break;


                                case 0:
                                    customer.logout();
                                    customerMenu = false;
                                    break;

                                default:
                                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                            }
                        }
                    }
                    break;

                case 0:
                    System.out.println("Terima kasih! Program dihentikan.");
                    loginMenu = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        sc.close(); // Menutup scanner
    }
}
