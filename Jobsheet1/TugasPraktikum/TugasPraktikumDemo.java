public class TugasPraktikumDemo {
    public static void main(String[] args) {
        Kulkas kulkas = new Kulkas("Samsung", 4);
        Kursi kursi = new Kursi("Kayu", "Cokelat");
        Meja meja = new Meja("Kayu", "Hitam");
        MejaRuangTamu mejaRuangTamu = new MejaRuangTamu("Kaca", "Putih", 4, true);
        MejaBelajar mejaBelajar = new MejaBelajar("Kayu", "Cokelat", 3, true);

        kulkas.dinginkan();
        kulkas.bukaPintu();
        kulkas.cetakInformasi();

        kursi.duduk();
        kursi.angkat();
        kursi.cetakInformasi();

        meja.gunakan();
        meja.pindahkan();
        meja.cetakInformasi();

        mejaRuangTamu.letakkanDekorasi();
        mejaRuangTamu.bukaLaci();
        mejaRuangTamu.cetakInformasi();

        mejaBelajar.belajar();
        mejaBelajar.nyalakanLampu();
        mejaBelajar.cetakInformasi();
    }
}
