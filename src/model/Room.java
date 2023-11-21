package model;


public class Room {
   private int ID;
   private String namaPelatihan;
   private int kuota;

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        this.kuota = kuota;
    }
   
   public Coach pemateri;
   

    public String getNamaPelatihan() {
        return namaPelatihan;
    }

   

    public void setNamaPelatihan(String namaPelatihan) {
        this.namaPelatihan = namaPelatihan;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public void setPemateri(Coach pemateri) {
        this.pemateri = pemateri;
    }
    
}
