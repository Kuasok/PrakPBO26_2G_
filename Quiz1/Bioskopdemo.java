//Nama :Muhammad Ferdi Afiyanto
//NIM : 254107020122
package Quiz1;

public class Bioskopdemo {

    public static double hitungTotal(Reservasi reservasi) {
        double biayaStudio = reservasi.getStudio().getTarifStudioPerJam()
                * reservasi.getDurasiJam();
        double biayaLayanan = reservasi.getOperator().getBiayaLayananTiketPerJam()
                * reservasi.getJumlahTiket() * reservasi.getDurasiJam();

        return biayaStudio + biayaLayanan;
    }

    public static void main(String[] args) {
        Studio studio = new Studio("IndoXXI", 150000);
        Operator operator = new Operator("Ferdi", 10000);
        Reservasi reservasi = new Reservasi("RSV001", 20, operator, studio, 2);

        double total = hitungTotal(reservasi);

        System.out.println("Kode reservasi : " + reservasi.getKodeReservasi());
        System.out.println("Studio         : " + studio.getNamaStudio());
        System.out.println("Operator       : " + operator.getNama());
        System.out.println("Jumlah tiket   : " + reservasi.getJumlahTiket());
        System.out.println("Durasi         : " + reservasi.getDurasiJam() + " jam");
        System.out.println("Total biaya    : Rp" + total);
    }
}
