import java.util.Scanner;
import java.util.Arrays;

public class UserProfile {
    private int userId;
    private String password;
    private String name;
    private int age;
    private String email;
    private String[] verifiedDocs; // Array untuk menyimpan dokumen yang terverifikasi
    private boolean verificationStatus;

    // Constructor
    public UserProfile(int userId, String password, String name, int age, String email, String[] doc) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        setDocuments(doc);
        this.verifiedDocs = new String[0]; // Inisialisasi dokumen terverifikasi
        this.verificationStatus = false;
    }

    public boolean getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(boolean status) {
        this.verificationStatus = status;
    }

    public boolean verifyUserDocs() {
        if (validateDocuments(verifiedDocs)) {
            setVerificationStatus(true); // Set status verifikasi menjadi true
            for (String document : verifiedDocs) {
                addVerifiedDocument(document); // Tambahkan dokumen satu per satu
            }
            return true;
        }
        setVerificationStatus(false); // Set status verifikasi menjadi false jika tidak valid
        return false;
    }

    // Metode untuk menambahkan satu dokumen terverifikasi
    public void addVerifiedDocument(String document) {
        // Menambah dokumen terverifikasi ke dalam array verifiedDocs
        verifiedDocs = Arrays.copyOf(verifiedDocs, verifiedDocs.length + 1); // Tambah ukuran array
        verifiedDocs[verifiedDocs.length - 1] = document; // Tambahkan dokumen baru
    }

    public int getUserID() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String[] getDoc() {
        return verifiedDocs;
    }

    public String[] getVerifiedDocs() {
        return verifiedDocs; // Getter untuk dokumen yang terverifikasi
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDocuments(String[] verifiedDocs) {
        if (validateDocuments(verifiedDocs)) {
            this.verifiedDocs = verifiedDocs;
        } else {
            this.verifiedDocs = new String[0]; // Set dokumen kosong jika tidak valid
        }
    }

    // Validasi dokumen hanya memperbolehkan doc, jpg, png, pdf
    private boolean validateDocuments(String[] documents) {
        if (documents == null || documents.length == 0) {
            return false; // Kembalikan false jika dokumen kosong
        }
        for (String file : documents) {
            String fileExtension = getFileExtension(file);
            if (!fileExtension.equals("doc") && !fileExtension.equals("jpg") &&
                !fileExtension.equals("png") && !fileExtension.equals("pdf")) {
                return false; // Jika ada file yang tidak valid
            }
        }
        return true;
    }

    // Mendapatkan ekstensi file
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return ""; // Tidak ada ekstensi
    }

    public String toString() {
        return "UserProfile\n" +
            "  User ID: " + userId + "\n" +
            "  Password: '" + password + "'\n" +
            "  Name: '" + name + "'\n" +
            "  Age: " + age + "\n" +
            "  Email: '" + email + "'\n" +
            "  Verified Documents: " + Arrays.toString(verifiedDocs);
    }
}
