public class Driver {
    private String nama;
    private int tarifPerKm;

    public Driver(String nama, int tarifPerKm) {
        this.nama = nama;
        this.tarifPerKm = tarifPerKm;
    }

    public String getNama() {
        return nama;
    }

    public int getTarifPerKm() {
        return tarifPerKm;
    }
}
