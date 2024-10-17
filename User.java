public class User {
    UserProfile userProfile;
    protected String password;

    public User(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }
    // Method untuk login
    public boolean login(int userId, String password) {
        if (userProfile.getUserID() == userId && userProfile.getPassword().equals(password)) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid user ID or password.");
            return false;
        }
    }

    // Method untuk recover password
    public void recoverPassword() {
        // Simulasi mengirimkan email pemulihan
        String recoveryToken = generateRecoveryToken();
        System.out.println("Password recovery process initiated. Check your email: " + userProfile.getEmail());
        System.out.println("Recovery token: " + recoveryToken); // Dalam kenyataan, ini akan dikirim ke email
    }
    
    // Simulasi pembuatan token pemulihan
    private String generateRecoveryToken() {
        return Integer.toHexString((userProfile.getEmail() + System.currentTimeMillis()).hashCode());
    }
    

    // Method untuk logout
    public void logout() {
        System.out.println("User has been logged out.");
    }
}
