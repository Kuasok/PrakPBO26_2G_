package id.ac.polinema;

public class KoperasiDemo {
    public static void main(String[] args) {
        Anggota anggota1 = new Anggota("Budi", "Jl. Merdeka No. 10");
        Anggota anggota2 = new Anggota("Siti", "Jl. Sudirman No. 5");

        System.out.println("=== Anggota 1 ===");
        System.out.println("Nama   : " + anggota1.getNama());
        System.out.println("Alamat : " + anggota1.getAlamat());
        anggota1.setor(500000);
        anggota1.pinjam(200000);
        System.out.println("Simpanan: Rp " + anggota1.getSimpanan());

        System.out.println();

        System.out.println("=== Anggota 2 ===");
        System.out.println("Nama   : " + anggota2.getNama());
        System.out.println("Alamat : " + anggota2.getAlamat());
        anggota2.setor(300000);
        anggota2.pinjam(100000);
        System.out.println("Simpanan: Rp " + anggota2.getSimpanan());
    }
}
