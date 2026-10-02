package id.ac.polinema.inheritance.tugas1;

public class MainTugas1 {
    public static void main(String[] args) {
        Pegawai budi = new Pegawai("P001", "Budi", "Malang");
        Dosen siti = new Dosen("D001", "Siti", "Surabaya", 12);

        DaftarGaji daftar = new DaftarGaji(10);
        daftar.addPegawai(budi);
        daftar.addPegawai(siti);

        daftar.printSemuaGaji();
    }
}
