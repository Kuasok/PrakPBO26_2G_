public class JamDinding {
    private String bentuk;
    private String warna;
    public JamDinding(String bentuk, String warna) { this.bentuk = bentuk; this.warna = warna; }
    public void tunjukkanWaktu() { System.out.println("Jam dinding menunjukkan waktu."); }
    public void gantiBaterai() { System.out.println("Baterai jam dinding diganti."); }
    public void cetakInformasi() { System.out.println("Jam Dinding - Bentuk: " + bentuk + ", Warna: " + warna); }
}
