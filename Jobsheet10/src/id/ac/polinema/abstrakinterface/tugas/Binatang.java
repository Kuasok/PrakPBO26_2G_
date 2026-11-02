package id.ac.polinema.abstrakinterface.tugas;

public abstract class Binatang {
    protected String nama;
    protected int jumlahKaki;
    protected String suara;
    protected String warnaBulu;

    public Binatang(String nama, int jumlahKaki, String suara, String warnaBulu) {
        this.nama = nama;
        this.jumlahKaki = jumlahKaki;
        this.suara = suara;
        this.warnaBulu = warnaBulu;
    }

    public abstract void displayMakan();

    public void displayData() {
        System.out.println("Nama: " + nama);
        System.out.println("Jumlah Kaki: " + jumlahKaki);
        System.out.println("Suara: " + suara);
        System.out.println("Warna Bulu: " + warnaBulu);
    }
}
