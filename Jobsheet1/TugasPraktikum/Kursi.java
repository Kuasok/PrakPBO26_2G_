public class Kursi {
    private String bahan;
    private String warna;

    public Kursi(String bahan, String warna) {
        this.bahan = bahan;
        this.warna = warna;
    }

    public void duduk() {
        System.out.println("Kursi digunakan untuk duduk.");
    }

    public void angkat() {
        System.out.println("Kursi diangkat.");
    }

    public void cetakInformasi() {
        System.out.println("Kursi - Bahan: " + bahan + ", Warna: " + warna);
    }
}
