package id.ac.polinema;

public class Anggota {
    private String nama;
    private String alamat;
    private double simpanan;

    public Anggota(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        this.simpanan = 0;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public double getSimpanan() {
        return simpanan;
    }

    public void setor(double jumlah) {
        if (jumlah > 0) {
            simpanan += jumlah;
            System.out.println("Setor berhasil. Simpanan sekarang: Rp " + simpanan);
        } else {
            System.out.println("Jumlah setor harus lebih dari 0!");
        }
    }

    public void pinjam(double jumlah) {
        if (jumlah > simpanan) {
            System.out.println("Pinjaman melebihi simpanan!");
        } else if (jumlah > 0) {
            simpanan -= jumlah;
            System.out.println("Pinjam berhasil. Simpanan sekarang: Rp " + simpanan);
        } else {
            System.out.println("Jumlah pinjaman harus lebih dari 0!");
        }
    }
}
