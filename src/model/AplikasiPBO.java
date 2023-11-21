package model;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import utils.CoachDAO;
import utils.DBUtils;
import utils.UserDAO;

public class AplikasiPBO {
    private Person currentPerson;
    private Scanner myObj = new Scanner(System.in);
    private int a = 1;
    private int z = 0;
    private Room[] seluruhRoom = new Room[100];
    private int[] joinedTrainings = new int[100];
    private User[] seluruhUser = new User[100];
    private Konselor[] seluruhKonselor = new Konselor[100];
    private Coach[] seluruhCoach = new Coach[100]; // Add an array for coaches
    Admin admin = new Admin("admin","123");
    public void slepp(){
            try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void joinUser(int userId) {
        ((User) currentPerson).join(seluruhRoom, joinedTrainings, userId, z, myObj);
    }

    // Inside the AplikasiPBO class

    public void createCoach() {
        ((Coach) currentPerson).create(seluruhRoom, a, z, myObj);
        a++;
        z++;
    }

    public void viewCoachParticipants() {
        ((Coach) currentPerson).viewParticipants(seluruhRoom, seluruhUser,joinedTrainings, z, myObj);
    }

    public void removeCoach() {
        ((Coach) currentPerson).remove(seluruhRoom,a, z, myObj);
    }


    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        int tUser = 0; // User index
        int tCoach = 0; // Coach index
        int tKonselor =0;
        int sign;
        String us;
        String ps;
        AplikasiPBO mainApp = new AplikasiPBO();
        
        do {
            
            System.out.println("Menu:");
            System.out.println("1. Daftar sebagai User");
            System.out.println("2. Login Admin dan Daftarkan akun Coach");
            System.out.println("3. Login");
            System.out.print("Pilih (1/2/3): ");
            sign = myObj.nextInt();

            switch (sign) {
                case 1:
                    System.out.println("**************************************************************************************************************");
                    mainApp.slepp();
                    System.out.println("Masukkan identitas User:");
                    mainApp.seluruhUser[tUser] = new User();
                    System.out.print("Nama: ");
                    mainApp.seluruhUser[tUser].setNama(myObj.next());
                    System.out.print("Password: ");
                    mainApp.seluruhUser[tUser].setPassword(myObj.next());
                    System.out.print("Jenis Kelamin: ");
                    mainApp.seluruhUser[tUser].setGender(myObj.next());
                    System.out.print("Usia: ");
                    mainApp.seluruhUser[tUser].setUsia(myObj.nextInt());
                    System.out.println("Akun User telah terbuat");
                    UserDAO dao=new UserDAO();
                    dao.save(mainApp.seluruhUser[tUser]);
                    tUser++;

                    System.out.println("**************************************************************************************************************");
                    mainApp.slepp();
                    break;

                case 2:
                    System.out.println("login admin");
                    System.out.println("silahkan konfirmasi bahwa anda admin");
                    System.out.print("Nama: ");
                    us = myObj.next();
                    System.out.print("Password: ");
                    ps = myObj.next();
                    if (mainApp.admin.login(us, ps)){
                    System.out.println("**************************************************************************************************************");
                    mainApp.slepp();
                    System.out.println("login berhasil, selamat bekerja admin");
                    System.out.println("1. tambah coach\n2. tambah konselor");
                    System.out.println("silahkan pilih jenis akun yang ingin dibuat(1/2):");
                    int pil= myObj.nextInt();
                    switch (pil){
                        case 1:
                            System.out.println("Masukkan identitas Coach:");
                            mainApp.seluruhCoach[tCoach] = new Coach();
                            System.out.print("Nama: ");
                            mainApp.seluruhCoach[tCoach].setNama(myObj.next());
                            System.out.print("Password: ");
                            mainApp.seluruhCoach[tCoach].setPassword(myObj.next());
                            System.out.print("Jenis Kelamin: ");
                            mainApp.seluruhCoach[tCoach].setGender(myObj.next());
                            System.out.print("Usia: ");
                            mainApp.seluruhCoach[tCoach].setUsia(myObj.nextInt());
                            System.out.println("Akun Coach telah terbuat");
                            CoachDAO daoCoach=new CoachDAO();
                            daoCoach.save(mainApp.seluruhCoach[tCoach]);
                            tCoach++;

                            System.out.println("**************************************************************************************************************");
                            mainApp.slepp();
                        break;
                        case 2:
                            System.out.println("Masukkan identitas Konselor:");
                            mainApp.seluruhKonselor[tKonselor] = new Konselor();
                            System.out.print("Nama: ");
                            mainApp.seluruhKonselor[tKonselor].setNama(myObj.next());
                            System.out.print("Password: ");
                            mainApp.seluruhKonselor[tKonselor].setPassword(myObj.next());
                            System.out.print("Jenis Kelamin: ");
                            mainApp.seluruhKonselor[tKonselor].setGender(myObj.next());
                            System.out.print("Usia: ");
                            mainApp.seluruhKonselor[tKonselor].setUsia(myObj.nextInt());
                            System.out.println("Akun Konselor telah terbuat");
                            tKonselor++;
                            System.out.println("**************************************************************************************************************");
                            mainApp.slepp();
                        break;
                        default:
                        System.out.println("Input tidak valid");
                        break;
                            }
                   }
                    else{
                        System.out.print("proses login admin ditolak silahkan coba lagi");
                    }
                    break;

                case 3:
                    System.out.println("**************************************************************************************************************");
                    mainApp.slepp();
                    System.out.println("Masukkan identitas:");
                    System.out.print("Nama: ");
                    us = myObj.next();
                    System.out.print("Password: ");
                    ps = myObj.next();

                    boolean loginSuccess = false;

                    for (int g = 0; g < tUser; g++) {
                        if (mainApp.seluruhUser[g].login(us, ps)) {
                            System.out.println("**************************************************************************************************************");
                            mainApp.slepp();
                            System.out.println("Login User berhasil");
                            // Pass the logged-in user to the currentUser
                            mainApp.currentPerson = mainApp.seluruhUser[g];
                            mainApp.joinUser(g);
                            loginSuccess = true;

                            break;
                        }
                    }

                    if (!loginSuccess) {
                        for (int g = 0; g < tCoach; g++) {
                            if (mainApp.seluruhCoach[g].login(us, ps)) {
                                System.out.println("**************************************************************************************************************");
                                mainApp.slepp();
                                System.out.println("Login Coach berhasil");
                                // Pass the logged-in coach to the currentUser
                                mainApp.currentPerson = mainApp.seluruhCoach[g];
                                System.out.println("1. Create\n2. Edit\n3. view data\n4. Logout");
                                int r;
                                r = myObj.nextInt();
                                if (r == 1) {
                                    System.out.println("**************************************************************************************************************");
                                    mainApp.slepp();
                                    mainApp.createCoach();
                                } else if (r == 2) {
                                    System.out.println("**************************************************************************************************************");
                                    mainApp.slepp();
                                    mainApp.removeCoach();
                                } else if (r == 3) {
                                    System.out.println("**************************************************************************************************************");
                                    mainApp.slepp();
                                    mainApp.viewCoachParticipants();
                                } else if (r == 4) {
                                    loginSuccess = true;
                                    break;
                                } else {
                                    System.out.println("Invalid input");
                                }
                                loginSuccess = true;
                                break;
                            }
                        }
                    }
                    if (!loginSuccess) {
                        for (int g = 0; g < tKonselor; g++) {
                            if (mainApp.seluruhKonselor[g].login(us, ps)) {
                                System.out.println("**************************************************************************************************************");
                                mainApp.slepp();
                                System.out.println("Login Konselor berhasil");
                                // Pass the logged-in coach to the currentUser
                                mainApp.currentPerson = mainApp.seluruhKonselor[g];
                                loginSuccess = true;
                            }
                        }
                    }
                    if (!loginSuccess) {
                        System.out.println("Nama dan password tidak ditemukan");
                        System.out.println("Harap mendaftar terlebih dahulu");
                    }
                    break;

                default:
                    System.out.println("Input tidak valid");
                    break;
            }
        } while (true);
    }
}