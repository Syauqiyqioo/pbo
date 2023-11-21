package model;

public class Person {
    private String nama;
    private String gender;
    private int usia;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public boolean login(String username, String password) {
        // Implement login logic for Person here
        // Return true if login is successful, false otherwise
        return this.nama.equals(username) && this.password.equals(password);
    }

    public static Person register(String nama, String gender, int usia, String password) {
        // Create a new Person instance and set the provided attributes
        Person person = new Person();
        person.setNama(nama);
        person.setGender(gender);
        person.setUsia(usia);
        person.setPassword(password);
        return person;
    }
}
