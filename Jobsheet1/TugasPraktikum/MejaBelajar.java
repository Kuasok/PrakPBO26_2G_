public class MejaBelajar extends Meja {
    private int jumlahLaci;
    private boolean memilikiLampu;

    public MejaBelajar(String bahan, String warna, int jumlahLaci, boolean memilikiLampu) {
        super(bahan, warna);
        this.jumlahLaci = jumlahLaci;
        this.memilikiLampu = memilikiLampu;
    }

    public void belajar() {
        System.out.println("Meja belajar digunakan untuk belajar.");
    }

    public void nyalakanLampu() {
        System.out.println(memilikiLampu ? "Lampu meja belajar dinyalakan." : "Meja belajar tidak memiliki lampu.");
    }

    @Override
    public void cetakInformasi() {
        System.out.println("Meja Belajar");
        super.cetakInformasi();
        System.out.println("Jumlah laci: " + jumlahLaci);
        System.out.println("Memiliki lampu: " + memilikiLampu);
    }
}
