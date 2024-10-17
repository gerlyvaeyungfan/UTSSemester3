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
        System.out.println("Dokumen terverifikasi '" + document + "' berhasil ditambahkan.");
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

    // Method untuk mengedit profil dengan switch case
    public void editProfile() {
        Scanner sc = new Scanner(System.in);
        boolean continueEditing = true;

        while (continueEditing) {
            System.out.println("\nPilih atribut yang ingin diedit:");
            System.out.println("1. User ID");
            System.out.println("2. Password");
            System.out.println("3. Nama");
            System.out.println("4. Umur");
            System.out.println("5. Email");
            System.out.println("6. Dokumen");
            System.out.println("7. Selesai");

            int choice = sc.nextInt();
            sc.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan User ID baru: ");
                    int newUserId = sc.nextInt();
                    sc.nextLine();
                    setUserId(newUserId);
                    System.out.println("User ID berhasil diperbarui.");
                    break;
                case 2:
                    System.out.print("Masukkan Password baru: ");
                    String newPassword = sc.nextLine();
                    setPassword(newPassword);
                    System.out.println("Password berhasil diperbarui.");
                    break;
                case 3:
                    System.out.print("Masukkan Nama baru: ");
                    String newName = sc.nextLine();
                    setName(newName);
                    System.out.println("Nama berhasil diperbarui.");
                    break;
                case 4:
                    System.out.print("Masukkan Umur baru: ");
                    int newAge = sc.nextInt();
                    sc.nextLine();
                    setAge(newAge);
                    System.out.println("Umur berhasil diperbarui.");
                    break;
                case 5:
                    System.out.print("Masukkan Email baru: ");
                    String newEmail = sc.nextLine();
                    setEmail(newEmail);
                    System.out.println("Email berhasil diperbarui.");
                    break;
                case 6:
                    System.out.print("Masukkan jumlah dokumen baru: ");
                    int docCount = sc.nextInt();
                    sc.nextLine();
                    String[] newDocs = new String[docCount];
                    for (int i = 0; i < docCount; i++) {
                        System.out.print("Masukkan dokumen ke-" + (i + 1) + ": ");
                        newDocs[i] = sc.nextLine();
                    }
                    setDocuments(newDocs);
                    break;
                case 7:
                    continueEditing = false;
                    System.out.println("Profil berhasil diperbarui.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        sc.close();
    }

    public String displayInfo() {
        return "UserProfile\n" +
                "  User ID: " + userId + "\n" +
                "  Password: '" + password + "'\n" +
                "  Name: '" + name + "'\n" +
                "  Age: " + age + "\n" +
                "  Email: '" + email + "'\n" +
                "  Verified Documents: " + Arrays.toString(verifiedDocs); // Menampilkan dokumen yang terverifikasi
    }
}
