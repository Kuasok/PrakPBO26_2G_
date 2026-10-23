public class Promo {
    private final String kode;
    private final int persen;

    public Promo(String kode, int persen) {
        this.kode = kode;
        this.persen = persen;
    }

    public String getKode() {
        return kode;
    }

    public int getPersen() {
        return persen;
    }
}
