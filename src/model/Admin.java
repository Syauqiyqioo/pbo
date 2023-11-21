package model;

public class Admin {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean login(String us, String ps) {
        // Implement login logic for Person here
        // Return true if login is successful, false otherwise
        return this.username.equals(us) && this.password.equals(ps);
    }
    
    public  Admin (String us, String ps) {
        // Create a new Person instance and set the provided attributes
        this.username = us;
        this.password = ps;
    }
}
