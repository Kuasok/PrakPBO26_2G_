//Nama :Muhammad Ferdi Afiyanto
//NIM : 254107020122
package Quiz1;

public class Studio {
    private String namaStudio;
    private double tarifStudioPerJam;

    public Studio(String namaStudio, double tarifStudioPerJam) {
        this.namaStudio = namaStudio;
        this.tarifStudioPerJam = tarifStudioPerJam;
    }

    public String getNamaStudio() {
        return namaStudio;
    }

    public double getTarifStudioPerJam() {
        return tarifStudioPerJam;
    }
}
