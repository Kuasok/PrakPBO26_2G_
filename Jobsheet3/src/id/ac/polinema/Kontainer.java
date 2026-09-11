package id.ac.polinema;

public class Kontainer {
    private String nomorResi;
    private String namaPemilik;
    private double kapasitasMaksimal;
    private double beratMuatanSaatIni;

    public Kontainer(String nomorResi, String namaPemilik, double kapasitasMaksimal) {
        this.nomorResi = nomorResi;
        this.namaPemilik = namaPemilik;
        this.kapasitasMaksimal = kapasitasMaksimal;
        this.beratMuatanSaatIni = 0;
    }

    public String getNomorResi() {
        return nomorResi;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public double getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }

    public double getBeratMuatanSaatIni() {
        return beratMuatanSaatIni;
    }

    public void tambahMuatan(double berat) {
        if (berat < 0) {
            System.out.println("Berat muatan tidak boleh negatif!");
            return;
        }
        if (beratMuatanSaatIni + berat > kapasitasMaksimal) {
            System.out.println("Muatan melebihi kapasitas maksimal!");
            return;
        }
        beratMuatanSaatIni += berat;
        System.out.println("Muatan ditambahkan. Berat sekarang: " + beratMuatanSaatIni + " kg");
    }

    public void turunkanMuatan(double berat) {
        if (berat < 0) {
            System.out.println("Berat muatan tidak boleh negatif!");
            return;
        }
        if (berat > 0.5 * beratMuatanSaatIni) {
            System.out.println("Maaf, demi keselamatan, pembongkaran muatan satu kali jalan tidak boleh melebihi 50% dari muatan saat ini!");
            return;
        }
        if (berat > beratMuatanSaatIni) {
            System.out.println("Berat muatan yang diturunkan melebihi muatan saat ini!");
            return;
        }
        beratMuatanSaatIni -= berat;
        System.out.println("Muatan diturunkan. Berat sekarang: " + beratMuatanSaatIni + " kg");
    }

    public void cetakInfo() {
        System.out.println("Nomor Resi        : " + nomorResi);
        System.out.println("Nama Pemilik      : " + namaPemilik);
        System.out.println("Kapasitas Maksimal: " + kapasitasMaksimal + " kg");
        System.out.println("Muatan Saat Ini   : " + beratMuatanSaatIni + " kg");
    }
}
