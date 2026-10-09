package id.ac.polinema.overloading.tugas2;

public class Dosen extends Manusia {
    @Override
    public void makan() {
        System.out.println("Dosen makan di kantin kampus");
    }

    public void lembur() {
        System.out.println("Dosen lembur mengoreksi tugas mahasiswa");
    }
}
