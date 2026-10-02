//Nama :Muhammad Ferdi Afiyanto
//NIM : 254107020122
package Quiz1;

public class Operator {
    private String nama;
    private double biaya_layanan_tiket_perjam;

    public Operator(String nama, double biaya_layanan_tiket_perjam) {
        this.nama = nama;
        this.biaya_layanan_tiket_perjam = biaya_layanan_tiket_perjam;
    }

    public String getNama() {
        return nama;
    }

    public double getBiayaLayananTiketPerJam() {
        return biaya_layanan_tiket_perjam;
    }
}
