import java.util.ArrayList;

public class Customer extends User {
    private String complain; // Atribut untuk menyimpan keluhan dari customer
    private boolean verificationStatus; // Status verifikasi user
    private ArrayList<String> documents; // Menyimpan daftar dokumen

    // Constructor
    public Customer(UserProfile userProfile) {
        super(userProfile);
        this.documents = new ArrayList<>();
        this.verificationStatus = false; // Status verifikasi awal
    }

    // Metode untuk mendapatkan dokumen
    public String[] getDocuments() {
        return documents.toArray(new String[0]); // Mengonversi ArrayList menjadi array
    }

    // Metode untuk mengatur dokumen
    public void setDocuments(String[] newDocuments) {
        if (newDocuments != null && newDocuments.length > 0) {
            documents.clear(); // Menghapus dokumen lama
            for (String doc : newDocuments) {
                if (doc != null && !doc.isEmpty()) {
                    documents.add(doc); // Menambahkan dokumen baru
                }
            }
            System.out.println("Dokumen berhasil diperbarui.");
        } else {
            System.out.println("Daftar dokumen tidak valid. Harap masukkan dokumen yang benar.");
        }
    }

    // Metode untuk menambahkan dokumen
    public void addDocument(String document) {
        if (document != null && !document.isEmpty()) {
            documents.add(document);
            System.out.println("Dokumen " + document + " berhasil ditambahkan untuk verifikasi.");
        } else {
            System.out.println("Nama dokumen tidak valid. Harap masukkan nama yang benar.");
        }
    }

    // Method untuk mendapatkan status verifikasi
    public boolean getVerificationStatus() {
        return verificationStatus;
    }

    // Method untuk mengajukan verifikasi dokumen
    public void applyVerification() {
        // Pastikan documents tidak kosong
        if (documents.isEmpty()) {
            System.out.println("Tidak ada dokumen yang diajukan untuk verifikasi.");
            return;
        }

        // Validasi dokumen baru
        boolean isValid = validateDocuments(documents.toArray(new String[0]));
        if (isValid) {
            userProfile.setDocuments(documents.toArray(new String[0]));
            verificationStatus = true;
            System.out.println("Dokumen berhasil diverifikasi dan diterapkan.");
        } else {
            verificationStatus = false;
            System.out.println("Dokumen tidak valid. Verifikasi gagal.");
        }
    }

    // Method untuk menyimpan keluhan dari customer
    public void submitComplain(String complain) {
        if (complain == null || complain.trim().isEmpty()) {
            System.out.println("Keluhan tidak boleh kosong.");
            return;
        }
        this.complain = complain;
        System.out.println("Keluhan berhasil dikirim: " + this.complain);
    }

    // Method untuk mengambil keluhan (retrieve complain)
    public String retrieveComplain() {
        return this.complain;
    }

    // Method untuk verifikasi user berdasarkan dokumen yang diinputkan
    public boolean verifyUserDocs() {
        String[] documents = userProfile.getDoc();
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
            if (!fileExtension.equals("doc") && !fileExtension.equals("jpg") &&
                !fileExtension.equals("png") && !fileExtension.equals("pdf")) {
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
