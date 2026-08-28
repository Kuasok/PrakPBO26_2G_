public class Kulkas {
    private String merek;
    private int suhu;

    public Kulkas(String merek, int suhu) {
        this.merek = merek;
        this.suhu = suhu;
    }

    public void dinginkan() {
        suhu--;
        System.out.println("Kulkas mendinginkan makanan. Suhu: " + suhu + " derajat Celsius.");
    }

    public void bukaPintu() {
        System.out.println("Pintu kulkas dibuka.");
    }

    public void cetakInformasi() {
        System.out.println("Kulkas - Merek: " + merek + ", Suhu: " + suhu + " derajat Celsius");
    }
}
