//Nama :Muhammad Ferdi Afiyanto
//NIM : 254107020122
package Quiz1;

public class Reservasi {
    private String kodeReservasi;
    private int jumlahTiket;
    private Operator operator;
    private Studio studio;
    private int durasiJam;

    public Reservasi(String kodeReservasi, int jumlahTiket, Operator operator,
                     Studio studio, int durasiJam) {
        this.kodeReservasi = kodeReservasi;
        this.jumlahTiket = jumlahTiket;
        this.operator = operator;
        this.studio = studio;
        this.durasiJam = durasiJam;
    }

    public String getKodeReservasi() {
        return kodeReservasi;
    }

    public int getJumlahTiket() {
        return jumlahTiket;
    }

    public Operator getOperator() {
        return operator;
    }

    public Studio getStudio() {
        return studio;
    }

    public int getDurasiJam() {
        return durasiJam;
    }
}

