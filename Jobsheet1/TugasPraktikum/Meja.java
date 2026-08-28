public class Meja {
    protected String bahan;
    protected String warna;

    public Meja(String bahan, String warna) {
        this.bahan = bahan;
        this.warna = warna;
    }

    public void gunakan() {
        System.out.println("Meja digunakan untuk meletakkan barang.");
    }

    public void pindahkan() {
        System.out.println("Meja dipindahkan ke tempat lain.");
    }

    public void cetakInformasi() {
        System.out.println("Bahan: " + bahan);
        System.out.println("Warna: " + warna);
    }
}
