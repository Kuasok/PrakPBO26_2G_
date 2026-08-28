public class Lampu {
    private String jenis;
    private int tingkatKecerahan;
    public Lampu(String jenis, int tingkatKecerahan) { this.jenis = jenis; this.tingkatKecerahan = tingkatKecerahan; }
    public void nyalakan() { System.out.println("Lampu dinyalakan."); }
    public void aturKecerahan(int tingkatBaru) { tingkatKecerahan = tingkatBaru; System.out.println("Kecerahan lampu diatur menjadi " + tingkatKecerahan + "%." ); }
    public void cetakInformasi() { System.out.println("Lampu - Jenis: " + jenis + ", Kecerahan: " + tingkatKecerahan + "%"); }
}
