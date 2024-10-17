import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Membuat array dokumen awal untuk admin dan customer
        String[] admin1Docs = {"certificate.jpg", "profile.png", "admin_license.pdf"};
        String[] customer1Docs = {"customer_idCard.jpg", "customer_insurance.pdf"};

        // Inisialisasi UserProfile untuk Admin
        UserProfile admin1 = new UserProfile(1, "1", "Admin User 1", 35, "admin1@example.com", admin1Docs);
        Admin admin = new Admin(admin1);

        // Inisialisasi UserProfile untuk Customer
        UserProfile customer1 = new UserProfile(2, "2", "Customer User 1", 28, "customer1@example.com", customer1Docs);
        Customer customer = new Customer(customer1);
        admin.addKendaraan(1, Vehicle.JenisKendaraan.MOBIL, "Toyota", "Merah"); // Menambahkan kendaraan pertama
        admin.addKendaraan(2, Vehicle.JenisKendaraan.MOTOR, "Yamaha", "Hitam"); // Menambahkan kendaraan kedua

        // Simulasi login untuk admin dan customer
        Scanner sc = new Scanner(System.in);

        // Menu Pilihan Login
        boolean loginMenu = true;
        while (loginMenu) {
            System.out.println("\n--- Pilih Login ---");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Customer");
            System.out.println("0. Keluar");
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
                            System.out.println("1. Update Detail Kendaraan");
                            System.out.println("2. Tambah Kendaraan");
                            System.out.println("3. Tampilkan Kendaraan");
                            System.out.println("4. Ambil Keluhan dari Customer");
                            System.out.println("5. Verifikasi Customer");
                            System.out.println("6. Tampilkan Profil");
                            System.out.println("0. Logout");
                            System.out.print("Pilih opsi: ");

                            int adminChoice = sc.nextInt();
                            sc.nextLine(); // Konsumsi newline

                            switch (adminChoice) {
                                case 1:
                                    System.out.print("Masukkan ID Kendaraan yang ingin diperbarui: ");
                                    int updateVehicleId = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Masukkan jenis kendaraan:");
                                    System.out.println("1. Mobil");
                                    System.out.println("2. Motor");
                                    System.out.print("Pilih opsi: ");
                                    int jenisKendaraanUpdate = sc.nextInt();
                                    sc.nextLine();

                                    Vehicle.JenisKendaraan jenisKendaraanEnumUpdate;
                                    switch (jenisKendaraanUpdate) {
                                        case 1:
                                            jenisKendaraanEnumUpdate = Vehicle.JenisKendaraan.MOBIL;
                                            break;
                                        case 2:
                                            jenisKendaraanEnumUpdate = Vehicle.JenisKendaraan.MOTOR;
                                            break;
                                        default:
                                            System.out.println("Jenis kendaraan tidak valid. Harap masukkan '1' untuk Mobil atau '2' untuk Motor.");
                                            continue; // Kembali ke awal loop atau lakukan penanganan kesalahan
                                    }

                                    System.out.print("Masukkan merek kendaraan: ");
                                    String merekUpdate = sc.nextLine();
                                    System.out.print("Masukkan warna kendaraan: ");
                                    String warnaUpdate = sc.nextLine();

                                    admin.updateDetailKendaraan(updateVehicleId, jenisKendaraanEnumUpdate, merekUpdate, warnaUpdate);
                                    break;

                                    case 2:
                                    System.out.print("Masukkan ID Kendaraan baru: ");
                                    int newVehicleId = sc.nextInt();
                                    sc.nextLine();
                                
                                    // Menampilkan pilihan jenis kendaraan
                                    System.out.println("Masukkan jenis kendaraan:");
                                    System.out.println("1. Mobil");
                                    System.out.println("2. Motor");
                                    System.out.print("Pilih opsi: ");
                                    int jenisKendaraanInput = sc.nextInt();
                                    sc.nextLine();
                                
                                    // Konversi input string menjadi enum
                                    Vehicle.JenisKendaraan jenisKendaraanEnum;
                                    switch (jenisKendaraanInput) {
                                        case 1:
                                            jenisKendaraanEnum = Vehicle.JenisKendaraan.MOBIL;
                                            break;
                                        case 2:
                                            jenisKendaraanEnum = Vehicle.JenisKendaraan.MOTOR;
                                            break;
                                        default:
                                            System.out.println("Jenis kendaraan tidak valid. Harap masukkan '1' untuk Mobil atau '2' untuk Motor.");
                                            continue; // Kembali ke awal loop atau lakukan penanganan kesalahan
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
                                    // Misalkan kita memiliki customer untuk diambil keluhannya
                                    System.out.println("Ambil keluhan dari customer.");
                                    System.out.println("ID Customer: " + customer1.getUserID());
                                    admin.retrieveComplain(customer);
                                    break;

                                    case 5: // Verifikasi Customer
                                    System.out.print("Masukkan User ID Customer yang akan diverifikasi: ");
                                    int customerUserIdForVerification = sc.nextInt();
                                    sc.nextLine(); // Konsumsi newline
                                
                                    // Cek apakah ID yang dimasukkan sesuai dengan Customer yang ada
                                    if (customerUserIdForVerification == customer.getUserProfile().getUserID()) {
                                        // Verifikasi Customer jika belum terverifikasi
                                        if (!customer.getVerificationStatus()) {
                                            if (customer.verifyUserDocs()) {
                                                // Tambahkan dokumen ke UserProfile setelah verifikasi berhasil
                                                for (String doc : customer.getDocuments()) {
                                                    customer.getUserProfile().addVerifiedDocument(doc); // Menambahkan dokumen yang diverifikasi
                                                }
                                                admin.verifyUser(customer);  // Verifikasi jika dokumen valid
                                                System.out.println("Customer dengan ID " + customerUserIdForVerification + " berhasil diverifikasi.");
                                            }
                                        } else {
                                            System.out.println("Customer dengan ID " + customerUserIdForVerification + " sudah terverifikasi.");
                                        }
                                    } else {
                                        System.out.println("Customer dengan ID " + customerUserIdForVerification + " tidak ditemukan.");
                                    }
                                    break;
                                

                                    
                                case 6:
                                    ArrayList<UserProfile> profiles = new ArrayList<>();
                                    profiles.add(admin1); // Tambahkan profil admin
                                    profiles.add(customer1); // Tambahkan profil customer

                                    // Menampilkan daftar ID pengguna yang ada
                                    System.out.println("Daftar ID Pengguna yang tersedia:");
                                    for (UserProfile profile : profiles) {
                                        System.out.println("ID: " + profile.getUserID() + ", Nama: " + profile.getName());
                                    }
                                    
                                    // Input ID profil yang ingin ditampilkan
                                    System.out.print("Masukkan ID profil yang ingin ditampilkan: ");
                                    int inputUserId = sc.nextInt();
                                    sc.nextLine(); // Konsumsi newline
                                    
                                    // Mencari profil berdasarkan ID
                                    boolean found = false;
                                    for (UserProfile profile : profiles) {
                                        if (profile.getUserID() == inputUserId) {
                                            System.out.println("\nProfil Ditemukan:");
                                            System.out.println(profile.displayInfo()); // Menampilkan detail profil
                                            found = true;
                                            break;
                                        }
                                    }
                                    
                                    // Jika tidak ditemukan
                                    if (!found) {
                                        System.out.println("Profil dengan ID " + inputUserId + " tidak ditemukan.");
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
                            System.out.println("1. Apply dokumen");
                            System.out.println("2. Kirim Keluhan");
                            System.out.println("3. Recovery Password");
                            System.out.println("0. Logout");
                            System.out.print("Pilih opsi: ");

                            int customerChoice = sc.nextInt();
                            sc.nextLine(); // Konsumsi newline

                            switch (customerChoice) {
                                case 1: // Apply dokumen untuk diverifikasi
                                    System.out.print("Masukkan nama file dokumen yang ingin diajukan untuk verifikasi (misal: dokumen.jpg): ");
                                    String documentName = sc.nextLine();
                                    // Memasukkan dokumen ke Customer
                                    customer.addDocument(documentName); // Sekarang metode ini sudah didefinisikan
                                    break;

                                case 2:
                                    System.out.print("Masukkan keluhan: ");
                                    String complain = sc.nextLine();
                                    customer.submitComplain(complain);
                                    break;
                                case 3:
                                    customer.recoverPassword();
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
                    // Keluar
                    System.out.println("Terima kasih! Keluar dari aplikasi.");
                    loginMenu = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }

        sc.close();
    }
}
