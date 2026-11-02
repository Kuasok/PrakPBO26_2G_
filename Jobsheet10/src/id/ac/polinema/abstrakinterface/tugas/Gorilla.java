package id.ac.polinema.abstrakinterface.tugas;

public class Gorilla extends Binatang implements IKarnivora, IHerbivora {
    public Gorilla(String nama, int jumlahKaki, String suara, String warnaBulu) {
        super(nama, jumlahKaki, suara, warnaBulu);
    }

    // Satu implementasi cukup untuk memenuhi displayMakan() pada IKarnivora,
    // IHerbivora, dan abstract method milik Binatang (signature-nya sama).
    @Override
    public void displayMakan() {
        System.out.println("Jenis: Karnivora + Herbivora");
        System.out.println("Makanan: Daging + Tumbuhan");
    }
}
