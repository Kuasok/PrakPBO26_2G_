public class Radio extends Elektronik {
    private double frekuensi;
    private boolean menggunakanBaterai;
    public Radio(String merek, int daya, double frekuensi, boolean menggunakanBaterai) { super(merek, daya); this.frekuensi = frekuensi; this.menggunakanBaterai = menggunakanBaterai; }
    public void cariFrekuensi(double frekuensiBaru) { frekuensi = frekuensiBaru; System.out.println("Radio mencari frekuensi " + frekuensi + " MHz."); }
    public void putarMusik() { System.out.println("Radio memutar musik."); }
    @Override public void cetakInformasi() { System.out.println("Radio"); super.cetakInformasi(); System.out.println("Frekuensi: " + frekuensi + " MHz, Menggunakan baterai: " + menggunakanBaterai); }
}
