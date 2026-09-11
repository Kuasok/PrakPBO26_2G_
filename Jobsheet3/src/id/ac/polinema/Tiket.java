package id.ac.polinema;

public class Tiket {
    private String judulFilm;
    private double hargaDasar;
    private boolean statusPembayaran;

    public Tiket(String judulFilm, double hargaDasar) {
        this.judulFilm = judulFilm;
        if (hargaDasar < 0) {
            this.hargaDasar = 35000;
        } else {
            this.hargaDasar = hargaDasar;
        }
        this.statusPembayaran = false;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    public boolean getStatusPembayaran() {
        return statusPembayaran;
    }

    public void lakukanPembayaran() {
        statusPembayaran = true;
        System.out.println("Pembayaran berhasil untuk film: " + judulFilm);
    }

    public void cetakInfo() {
        System.out.println("Judul Film       : " + judulFilm);
        System.out.println("Harga Dasar      : Rp " + hargaDasar);
        System.out.println("Status Pembayaran: " + (statusPembayaran ? "Sudah Dibayar" : "Belum Dibayar"));
    }
}
