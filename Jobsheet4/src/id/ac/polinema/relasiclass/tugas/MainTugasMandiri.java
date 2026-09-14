package id.ac.polinema.relasiclass.tugas;
public class MainTugasMandiri {
    public static void main(String[] args) {
        Buku buku = new Buku("Pemrograman Berbasis Objek");
        Anggota anggota = new Anggota("Budi");
        Perpustakaan perpustakaan = new Perpustakaan(buku);
        perpustakaan.info();
        perpustakaan.pinjam(anggota);
    }
}
