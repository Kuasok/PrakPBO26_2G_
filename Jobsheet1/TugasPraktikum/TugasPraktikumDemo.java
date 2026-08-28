public class TugasPraktikumDemo {
    public static void main(String[] args) {
        Televisi televisi = new Televisi("LG", 100, 43, 15);
        Radio radio = new Radio("Polytron", 20, 98.7, true);
        Lampu lampu = new Lampu("LED", 80);
        JamDinding jamDinding = new JamDinding("Bulat", "Hitam");

        televisi.nyalakan(); televisi.gantiSaluran(7); televisi.cetakInformasi();
        radio.nyalakan(); radio.cariFrekuensi(98.7); radio.cetakInformasi();
        lampu.nyalakan(); lampu.aturKecerahan(60); lampu.cetakInformasi();
        jamDinding.tunjukkanWaktu(); jamDinding.gantiBaterai(); jamDinding.cetakInformasi();
    }
}
