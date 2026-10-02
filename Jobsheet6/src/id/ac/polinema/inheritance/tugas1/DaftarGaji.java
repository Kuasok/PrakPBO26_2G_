package id.ac.polinema.inheritance.tugas1;

public class DaftarGaji {
    private Pegawai[] listPegawai;
    private int jumlahPegawai;

    public DaftarGaji(int kapasitas) {
        listPegawai = new Pegawai[kapasitas];
        jumlahPegawai = 0;
    }

    public void addPegawai(Pegawai pegawai) {
        if (jumlahPegawai < listPegawai.length) {
            listPegawai[jumlahPegawai] = pegawai;
            jumlahPegawai++;
        }
    }

    public void printSemuaGaji() {
        for (int i = 0; i < jumlahPegawai; i++) {
            System.out.println(
                    listPegawai[i].getNama() + " : " + listPegawai[i].getGaji());
        }
    }
}
