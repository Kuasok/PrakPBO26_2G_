public class PesananMobil extends Pesanan {
    public static final int BIAYA_LAYANAN = 3000;

    private int kursi;

    public PesananMobil(String id, String namaPenumpang, double jarakKm, int kursi) {
        super(id, namaPenumpang, jarakKm);
        this.kursi = kursi;
    }

    @Override
    public int hitungTarif() {
        return super.hitungTarif() + BIAYA_LAYANAN;
    }

    @Override
    public String info() {
        return super.info() + " [Mobil, " + kursi + " kursi]";
    }

    public int getKursi() {
        return kursi;
    }
}
