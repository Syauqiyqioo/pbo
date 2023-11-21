package model;

import java.util.Scanner;

public class User extends Person {
    // User-specific attributes or methods can be added here
    
    public void join(Room[] seluruhRoom, int[] joinedTrainings, int userId, int z, Scanner myObj) {
        System.out.println("Bergabung dengan pelatihan");
        System.out.println("Pelatihan yang tersedia:");
        for (int x = 0; x < z; x++) {
            if (seluruhRoom[x] != null) {
                System.out.println("ID pelatihan         : " + seluruhRoom[x].getID());
                System.out.println("Nama pelatihan   : " + seluruhRoom[x].getNamaPelatihan());
                System.out.println("Kuota tersedia     : " + seluruhRoom[x].getKuota());
                System.out.println("--------------------------------------------------");
            }
        }

        System.out.print("Pilih ID pelatihan untuk bergabung: ");
        int selectedTrainingId = myObj.nextInt();
        System.out.println("**************************************************************************************************************");

        // The rest of the join method logic as you had it in AplikasiPBO
         boolean trainingExists = false;
    for (int x = 0; x < z; x++) {
        if (seluruhRoom[x] != null && seluruhRoom[x].getID() == selectedTrainingId) {
            trainingExists = true;
            if (seluruhRoom[x].getKuota() > 0) {
                seluruhRoom[x].setKuota(seluruhRoom[x].getKuota() - 1);
                joinedTrainings[userId] = selectedTrainingId;
                System.out.println("Anda telah bergabung dengan pelatihan tersebut.");
                System.out.println("**************************************************************************************************************");
                //slepp();
            } else {
                System.out.println("Kuota pelatihan telah terpenuhi.");
                System.out.println("**************************************************************************************************************");
                //slepp();
            }
            break;
        }
    }

    if (!trainingExists) {
        System.out.println("ID pelatihan tidak valid.");
        System.out.println("**************************************************************************************************************");
        //slepp();
    }
    }
}