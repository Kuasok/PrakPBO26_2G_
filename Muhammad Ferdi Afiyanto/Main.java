public class Main {
    public static void main(String[] args) {
        Driver budi = new Driver("Budi", 2500);
        Driver sari = new Driver("Sari", 4000);
        Promo hemat = new Promo("HEMAT20", 20);

        PesananMotor p1 = new PesananMotor("P-001", "Dina", 3.0);
        p1.tugaskan(budi);

        System.out.println(p1.info());
        System.out.println("Tarif P-001: " + p1.hitungTarif());
        System.out.println("P-001 dengan promo: " + p1.hitungTotal(hemat));

        p1.tugaskan(sari);
        System.out.println("P-001 ganti driver, dengan promo: " + p1.hitungTotal(hemat));

        PesananMobil p2 = new PesananMobil("P-002", "Raka", 10.5, 4);
        p2.tugaskan(sari);
        System.out.println(p2.info());
        System.out.println("P-002 tanpa promo: " + p2.hitungTotal(null));
    }
}

// Tugas 3 - Extensibilitas (PesananBajaj):
// Yang dipakai ulang adalah enkapsulasi atribut Pesanan (id, namaPenumpang, jarakKm, driver),
// asosiasi ke Driver lewat tugaskan(), method info(), dan hitungTotal()
// yang memanggil hitungTarif() sehingga otomatis ikut memakai tarif baru.
// PesananBajaj cukup extends Pesanan dan meng-override hitungTarif() saja, yaitu memakai
// super.hitungTarif() lalu mengurangi 10% jika jarakKm == 3.0.
// Rumus tarif tidak disalin dari Pesanan karena salinan akan menduplikasi logika tarif dasar
// (jarakKm x tarifPerKm driver); dengan pewarisan + override hanya perbedaan aturan tarif yang
// ditulis, sehingga perubahan aturan dasar cukup diubah di satu tempat.
