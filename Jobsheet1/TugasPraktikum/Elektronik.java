public class Elektronik {
    protected String merek;
    protected int daya;

    public Elektronik(String merek, int daya) { this.merek = merek; this.daya = daya; }
    public void nyalakan() { System.out.println("Perangkat elektronik dinyalakan."); }
    public void matikan() { System.out.println("Perangkat elektronik dimatikan."); }
    public void cetakInformasi() { System.out.println("Merek: " + merek + ", Daya: " + daya + " watt"); }
}
