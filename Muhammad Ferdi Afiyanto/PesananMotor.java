public class PesananMotor extends Pesanan {
    public static final int TARIF_MINIMUM = 8000;

    public PesananMotor(String id, String namaPenumpang, double jarakKm) {
        super(id, namaPenumpang, jarakKm);
    }
    @Override
    public int hitungTarif() {
        return Math.max(super.hitungTarif(), TARIF_MINIMUM);
    }
    @Override
    public String info() {
        return super.info() + " [Motor]";
    }
}
