package id.ac.polinema;

public class Motor {
    private int kecepatan;
    private boolean kontakOn;

    public void nyalakanMesin() {
        kontakOn = true;
        System.out.println("Mesin dinyalakan.");
    }

    public void matikanMesin() {
        kontakOn = false;
        kecepatan = 0;
        System.out.println("Mesin dimatikan.");
    }

    public void tambahKecepatan(int tambah) {
        if (!kontakOn) {
            System.out.println("Kecepatan tidak bisa bertambah karena Mesin Off!");
            return;
        }
        kecepatan += tambah;
        if (kecepatan > 100) {
            kecepatan = 100;
        }
    }

    public void kurangiKecepatan(int kurang) {
        kecepatan -= kurang;
        if (kecepatan < 0) {
            kecepatan = 0;
        }
    }

    public int getKecepatan() {
        return kecepatan;
    }

    public boolean isKontakOn() {
        return kontakOn;
    }

    public void printStatus() {
        System.out.println("Status Motor:");
        System.out.println("Kontak : " + (kontakOn ? "ON" : "OFF"));
        System.out.println("Kecepatan : " + kecepatan + " km/h");
    }
}
