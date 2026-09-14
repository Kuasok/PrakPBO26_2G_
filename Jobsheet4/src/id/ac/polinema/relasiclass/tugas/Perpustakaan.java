package id.ac.polinema.relasiclass.tugas;
public class Perpustakaan {
    private Buku buku; // Aggregation
    private Rak rak;   // Composition

    public Perpustakaan(Buku buku) {
        this.buku = buku;
        this.rak = new Rak("RAK-01");
    }

    public void pinjam(Anggota anggota) { // Dependency
        System.out.println("Peminjaman oleh: " + anggota.getNama());
        System.out.println("Buku: " + buku.getJudul());
    }

    public void info() {
        System.out.println("Buku: " + buku.getJudul());
        System.out.println("Rak: " + rak.getKode());
    }
}
