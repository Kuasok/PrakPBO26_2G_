package id.ac.polinema.overloading.tugas2;

public class MainTugas2 {
    public static void main(String[] args) {
        // Dynamic method dispatch: variabel bertipe Manusia menunjuk objek subclass.
        Manusia manusia = new Manusia();
        Manusia dosen = new Dosen();
        Manusia mahasiswa = new Mahasiswa();

        System.out.println("== Pemanggilan lewat referensi Manusia ==");
        manusia.makan();
        dosen.makan();
        mahasiswa.makan();

        System.out.println("== Perilaku yang diwarisi tetap dapat dipakai ==");
        dosen.bernafas();
        mahasiswa.bernafas();

        System.out.println("== Method khas subclass (butuh downcast) ==");
        ((Dosen) dosen).lembur();
        ((Mahasiswa) mahasiswa).tidur();
    }
}
