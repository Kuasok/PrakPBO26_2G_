package id.ac.polinema.polimorfisme.zombie;

/**
 * Bukti Tugas 3. Baris yang diperkirakan gagal dikompilasi ditulis sebagai komentar
 * dan pesan errornya didokumentasikan pada out/pesan-error.txt.
 */
public class Tugas3Bukti {
    public static void main(String[] args) {
        // Prediksi: OK
        Zombie z = new WalkingZombie(100, 1);
        System.out.println("1. Zombie z = new WalkingZombie(100, 1); -> OK ("
                + z.getClass().getSimpleName() + ")");

        // Prediksi: OK
        Destroyable d = z;
        System.out.println("2. Destroyable d = z; -> OK ("
                + d.getClass().getSimpleName() + ")");

        // Prediksi: error kompilasi
        // Zombie z2 = d;

        // Prediksi: OK
        Zombie z3 = (Zombie) d;
        System.out.println("4. Zombie z3 = (Zombie) d; -> OK ("
                + z3.getClass().getSimpleName() + ")");

        // Prediksi: error kompilasi (Zombie abstract)
        // Zombie z4 = new Zombie(100, 1);

        // Prediksi: error kompilasi (Plant tidak implements Destroyable)
        // Destroyable d2 = new Plant();

        // Prediksi: kompilasi OK, error saat dijalankan (ClassCastException)
        Zombie z5 = new JumpingZombie(100, 2);
        try {
            WalkingZombie w = (WalkingZombie) z5;
            System.out.println("7. WalkingZombie w = (WalkingZombie) z5; -> OK ("
                    + w.getClass().getSimpleName() + ")");
        } catch (ClassCastException ex) {
            System.out.println("7. WalkingZombie w = (WalkingZombie) z5; -> "
                    + ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}
