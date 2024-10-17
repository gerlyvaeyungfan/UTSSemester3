import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Customer extends User {
    private String complain; // Atribut untuk menyimpan keluhan dari customer
    private boolean verificationStatus; // Status verifikasi user
    private ArrayList<String> documents; // Menyimpan daftar dokumen
    private boolean isPendingVerification; // Menandai jika verifikasi dalam antrean

    // Constructor
    public Customer(UserProfile userProfile) {
        super(userProfile);
        this.documents = new ArrayList<>();
        this.verificationStatus = false; // Status verifikasi awal
        this.isPendingVerification = false; // Status pending
    }

    // Method untuk memperbarui status verifikasi
    public void updateVerificationStatus(boolean status) {
        this.verificationStatus = status;
        this.isPendingVerification = false; // Reset status antrean setelah verifikasi
        System.out.println("Status verifikasi telah diperbarui: " + (status ? "Terverifikasi" : "Belum terverifikasi"));
    }

    // Metode untuk mendapatkan dokumen
    public String[] getDocuments() {
        return documents.toArray(new String[0]); // Mengonversi ArrayList menjadi array
    }

    // Metode untuk menambahkan dokumen
    public void addDocument(String document) {
        if (document != null && !document.isEmpty() && (document.endsWith(".jpg") || document.endsWith(".png") || document.endsWith(".pdf"))) {
            documents.add(document);
            System.out.println("Dokumen " + document + " berhasil ditambahkan untuk verifikasi.");
        } else {
            System.out.println("Nama dokumen tidak valid. Harap masukkan nama yang benar dan akhiri dengan .jpg, .png, atau .pdf.");
        }
    }
    
    // Method untuk mendapatkan status verifikasi
    public boolean getVerificationStatus() {
        return verificationStatus;
    }

    // Method untuk mengajukan verifikasi dokumen tanpa meminta input User ID lagi
    public boolean applyVerification() {
    // Pastikan dokumen tidak kosong
    if (documents.isEmpty()) {
        System.out.println("Tidak ada dokumen yang diajukan untuk verifikasi.");
        return false;
    }

    // Validasi dokumen
    boolean isValid = validateDocuments(documents.toArray(new String[0]));
    if (isValid) {
        if (!isPendingVerification) { // Cek jika belum dalam antrean
            VerificationQueue.addPendingVerification(this); // Tambahkan ke antrean
            isPendingVerification = true; // Tandai bahwa customer menunggu verifikasi
            System.out.println("Dokumen telah diajukan untuk verifikasi.");
        } else {
            System.out.println("Customer sudah berada dalam antrean verifikasi.");
        }
        return true;
    } else {
        System.out.println("Dokumen tidak valid. Verifikasi gagal.");
        return false;
    }
}

    // Method untuk memulihkan password
    @Override
    public void recoverPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Recover password for customer ID: " + userProfile.getUserID());
        System.out.print("Enter your registered email: ");
        String enteredEmail = sc.nextLine();

        // Verifikasi email yang dimasukkan
        if (enteredEmail.equals(userProfile.getEmail())) {
            System.out.println("Email verified successfully.");
            System.out.print("Enter new password: ");
            String newPassword = sc.nextLine();
            userProfile.setPassword(newPassword); // Reset password
            System.out.println("Your password has been updated.");
        } else {
            System.out.println("Email not recognized. Cannot recover password.");
        }
    }

    // Method untuk menyimpan keluhan dari customer
    public void submitComplain(String complain) {
        if (complain == null || complain.trim().isEmpty()) {
            System.out.println("Keluhan tidak boleh kosong.");
            return;
        }
        this.complain = complain;
        System.out.println("Keluhan berhasil dikirim oleh Customer ID: " + userProfile.getUserID() + ": " + this.complain);
    }

    // Method untuk mengambil keluhan (retrieve complain)
    public String retrieveComplain() {
        return this.complain == null ? "Tidak ada keluhan yang disimpan." : this.complain;
    }

    // Method untuk verifikasi user berdasarkan dokumen yang diinputkan
    public boolean verifyUserDocs() {
        String[] documents = getDocuments(); // Mengambil dokumen yang ada di Customer
        if (documents == null || documents.length == 0) {
            System.out.println("User verification failed. No documents provided.");
            return false;
        }

        boolean valid = validateDocuments(documents);
        if (valid) {
            verificationStatus = true;
            System.out.println("User successfully verified based on documents.");
        } else {
            verificationStatus = false;
            System.out.println("User verification failed. Some documents have invalid formats.");
        }
        return verificationStatus;
    }

    // Method untuk validasi dokumen, hanya memperbolehkan format doc, jpg, png, pdf
    private boolean validateDocuments(String[] documents) {
        for (String file : documents) {
            String fileExtension = getFileExtension(file);
            if (!fileExtension.equals("jpg") && !fileExtension.equals("png") && !fileExtension.equals("pdf")) {
                return false; // Jika ada file yang tidak valid
            }
        }
        return true;
    }

    // Method untuk mendapatkan ekstensi file
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return ""; // Jika file tidak memiliki ekstensi
    }
}
class VerificationQueue {
    private static Queue<Customer> pendingQueue = new LinkedList<>();

    // Method untuk menambahkan customer ke antrean
    public static void addPendingVerification(Customer customer) {
        pendingQueue.add(customer);
        System.out.println("Customer ID: " + customer.getUserProfile().getUserID() + " telah ditambahkan ke antrean verifikasi.");
    }

    // Method untuk memeriksa apakah antrean kosong
    public static boolean isEmpty() {
        return pendingQueue.isEmpty();
    }

    // Method untuk mendapatkan ukuran antrean
    public static int getPendingQueueSize() {
        return pendingQueue.size();
    }

    // Method untuk mendapatkan customer di antrean berdasarkan indeks
    public static Customer getPendingCustomer(int index) {
        if (index < 0 || index >= pendingQueue.size()) {
            throw new IndexOutOfBoundsException("Indeks di luar jangkauan.");
        }
        return (Customer) pendingQueue.toArray()[index]; // Ambil customer berdasarkan indeks
    }

    // Method untuk menghapus customer dari antrean
    public static void removePendingCustomer(Customer customer) {
        pendingQueue.remove(customer); // Hapus customer dari antrean
    }

    // Method untuk mengambil dan memverifikasi dokumen customer
    public static void verifyNextCustomerInQueue() {
        if (!pendingQueue.isEmpty()) {
            Customer customer = pendingQueue.poll(); // Ambil customer dari antrean
            System.out.println("Memverifikasi dokumen Customer ID: " + customer.getUserProfile().getUserID());
            // Lakukan verifikasi dokumen (validasi manual oleh admin)
            boolean isValid = customer.verifyUserDocs(); // Memverifikasi dokumen
            customer.updateVerificationStatus(isValid); // Update status verifikasi

            if (isValid) {
                System.out.println("Dokumen Customer ID: " + customer.getUserProfile().getUserID() + " telah berhasil diverifikasi.");
            } else {
                System.out.println("Dokumen Customer ID: " + customer.getUserProfile().getUserID() + " gagal diverifikasi.");
            }
        } else {
            System.out.println("Tidak ada antrean verifikasi yang tersisa.");
        }
    }
}