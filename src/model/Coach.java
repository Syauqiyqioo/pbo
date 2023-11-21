package model;

import java.util.Scanner;
import utils.RoomDAO;

public class Coach extends Person {
    // Coach-specific attributes or methods can be added here

    public void create(Room[] seluruhRoom, int a, int z, Scanner myObj) {
        System.out.println("Membuat pelatihan");
        seluruhRoom[z] = new Room();
        seluruhRoom[z].setID(a);
        System.out.println("ID pelatihan: " + seluruhRoom[z].getID());
        myObj.nextLine(); // Clear the newline
        System.out.print("Nama pelatihan: ");
        seluruhRoom[z].setNamaPelatihan(myObj.nextLine());
        seluruhRoom[z].setPemateri(this);
        System.out.print("Kuota pelatihan: ");
        seluruhRoom[z].setKuota(myObj.nextInt());
        RoomDAO daoRoom=new RoomDAO();
        daoRoom.save(seluruhRoom[z]);
        a++;
        z++;
        System.out.println("Pelatihan berhasil dibuat");
        System.out.println("**************************************************************************************************************");
    }

    public void viewParticipants(Room[] seluruhRoom, User[] seluruhUser,int[] joinedTrainings, int z, Scanner myObj) {
        System.out.println("Melihat peserta pelatihan");

        System.out.println("Pelatihan yang Anda buat:");
        for (int x = 0; x < z; x++) {
            if (seluruhRoom[x] != null) {
                System.out.println("ID pelatihan         : " + seluruhRoom[x].getID());
                System.out.println("Nama pelatihan   : " + seluruhRoom[x].getNamaPelatihan());
                System.out.println("Pemateri           : " + seluruhRoom[x].pemateri.getNama());
                System.out.println("--------------------------------------------------");
            }
        }

        System.out.print("Pilih ID pelatihan untuk melihat peserta: ");
        int selectedTrainingId = myObj.nextInt();
        System.out.println("**************************************************************************************************************");
        //slepp();

        boolean trainingExists = false;
        for (int x = 0; x < z; x++) {
            if (seluruhRoom[x] != null && seluruhRoom[x].getID() == selectedTrainingId) {
                trainingExists = true;
                break;
            }
        }

        if (trainingExists) {
            System.out.println("Peserta pelatihan " + seluruhRoom[selectedTrainingId - 1].getNamaPelatihan() + ":");
            for (int i = 0; i < seluruhUser.length; i++) {
                if (seluruhUser[i] != null && joinedTrainings[i] == selectedTrainingId) {
                    System.out.println("Nama: " + seluruhUser[i].getNama());
                    System.out.println("Jenis Kelamin: " + seluruhUser[i].getGender());
                    System.out.println("Usia: " + seluruhUser[i].getUsia());
                    System.out.println("--------------------------------------------------");
                }
            }
            System.out.println("**************************************************************************************************************");
            //slepp();
        } else {
            System.out.println("ID pelatihan tidak valid.");
            System.out.println("**************************************************************************************************************");
            //slepp();
        }
    }

    public void remove(Room[] seluruhRoom, int a, int z, Scanner myObj) {
        System.out.println("Mengedit & menghapus pelatihan");
        for (int x = 0; x < z; x++) {
            if (seluruhRoom[x] != null) {
                System.out.println("ID pelatihan: " + seluruhRoom[x].getID());
                System.out.println("Nama pelatihan:" + seluruhRoom[x].getNamaPelatihan());
                System.out.println("--------------------------------------------------");
            }
        }
        System.out.println("1. Hapus\n2. Edit\n3. Kembali\nPilih: ");
        int pil = myObj.nextInt();
        if (pil == 1) {
            System.out.println("Pilih ID pelatihan yang mau dihapus: ");
            int v = myObj.nextInt();
            if (v <= 0 || v > z) {
                System.out.println("Invalid ID");
                System.out.println("**************************************************************************************************************");
                //slepp();
            } else {
                for (int x = v - 1; x < z - 1; x++) {
                    seluruhRoom[x] = seluruhRoom[x + 1];
                }
                seluruhRoom[z - 1] = null;
                z--;
                a--;
                System.out.println("Pelatihan yang dipilih telah dihapus");
                System.out.println("**************************************************************************************************************");
                //slepp();
            }
        } else if (pil == 2) {
            int ed;
            do {
                System.out.println("1. Nama pelatihan\nPilih untuk diedit: ");
                ed = myObj.nextInt();
                System.out.println("--------------------------------------------------");
                if (ed == 1) {
                    System.out.println("Pilih ID pelatihan yang mau diedit: ");
                    int p = myObj.nextInt();
                    int w = 0;
                    String br;
                    while (w < z) {
                        if (seluruhRoom[w] != null && seluruhRoom[w].getID() == p) {
                            System.out.println("Nama pelatihan terakhir: " + seluruhRoom[w].getNamaPelatihan());
                            myObj.nextLine(); // Clear the newline
                            System.out.print("Nama pelatihan baru: ");
                            br = myObj.nextLine();
                            seluruhRoom[w].setNamaPelatihan(br);
                            System.out.println("Nama pelatihan telah diperbarui");
                            System.out.println("**************************************************************************************************************");
                            //slepp();
                            break; // Exit the loop after editing
                        } else if (p > z) {
                            System.out.println("Invalid ID");
                            System.out.println("**************************************************************************************************************");
                            //slepp();
                            break; // Exit the loop if ID is invalid
                        }
                        w++;
                    }
                } else {
                    System.out.println("Invalid input");
                    System.out.println("**************************************************************************************************************");
                    //slepp();
                }
            } while (ed != 3);
        } else if (pil != 3) {
            System.out.println("Invalid input");
            System.out.println("**************************************************************************************************************");
            //slepp();
        }
    }
}

