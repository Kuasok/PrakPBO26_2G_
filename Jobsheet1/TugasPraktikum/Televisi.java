public class Televisi extends Elektronik {
    private int ukuranLayar;
    private int volume;
    public Televisi(String merek, int daya, int ukuranLayar, int volume) { super(merek, daya); this.ukuranLayar = ukuranLayar; this.volume = volume; }
    public void gantiSaluran(int saluran) { System.out.println("Televisi berpindah ke saluran " + saluran + "."); }
    public void aturVolume(int volumeBaru) { volume = volumeBaru; System.out.println("Volume televisi diatur menjadi " + volume + "."); }
    @Override public void cetakInformasi() { System.out.println("Televisi"); super.cetakInformasi(); System.out.println("Ukuran layar: " + ukuranLayar + " inci, Volume: " + volume); }
}
