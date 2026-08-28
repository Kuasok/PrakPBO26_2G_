public class MejaRuangTamu extends Meja {
    private int jumlahKaki;
    private boolean memilikiLaci;

    public MejaRuangTamu(String bahan, String warna, int jumlahKaki, boolean memilikiLaci) {
        super(bahan, warna);
        this.jumlahKaki = jumlahKaki;
        this.memilikiLaci = memilikiLaci;
    }

    public void letakkanDekorasi() {
        System.out.println("Dekorasi diletakkan di meja ruang tamu.");
    }

    public void bukaLaci() {
        System.out.println(memilikiLaci ? "Laci meja ruang tamu dibuka." : "Meja ruang tamu tidak memiliki laci.");
    }

    @Override
    public void cetakInformasi() {
        System.out.println("Meja Ruang Tamu");
        super.cetakInformasi();
        System.out.println("Jumlah kaki: " + jumlahKaki);
        System.out.println("Memiliki laci: " + memilikiLaci);
    }
}
