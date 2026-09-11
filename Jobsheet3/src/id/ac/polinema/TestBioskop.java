package id.ac.polinema;

public class TestBioskop {
    public static void main(String[] args) {
        Tiket tiket1 = new Tiket("Avengers: Endgame", 50000);
        Tiket tiket2 = new Tiket("Oppenheimer", -10000);

        System.out.println("=== Tiket 1 ===");
        tiket1.cetakInfo();
        tiket1.lakukanPembayaran();
        tiket1.cetakInfo();

        System.out.println();

        System.out.println("=== Tiket 2 ===");
        tiket2.cetakInfo();
        tiket2.lakukanPembayaran();
        tiket2.cetakInfo();
    }
}
