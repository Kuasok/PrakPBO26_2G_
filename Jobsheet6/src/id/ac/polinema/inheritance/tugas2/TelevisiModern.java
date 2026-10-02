package id.ac.polinema.inheritance.tugas2;

public class TelevisiModern extends Televisi {
    private String modusTampilan;
    private String judulDVD;

    public TelevisiModern(String merk, int jumlahChannel) {
        super(merk, jumlahChannel);
        this.modusTampilan = "TV";
        this.judulDVD = "kosong";
    }

    public void gantiModusTampilan(String modus) {
        this.modusTampilan = modus;
    }

    public String getModusTampilan() {
        return modusTampilan;
    }

    public void masukkanDVD(String judul) {
        this.judulDVD = judul;
    }

    public void mainkanDVD() {
        System.out.println("Sedang memainkan DVD: " + judulDVD);
    }
}
