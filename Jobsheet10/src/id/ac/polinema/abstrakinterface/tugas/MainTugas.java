package id.ac.polinema.abstrakinterface.tugas;

public class MainTugas {
    public static void main(String[] args) {
        Binatang[] binatang = {
                new Keledai("Keledai", 4, "Hehehehe", "Abu-Abu"),
                new Gorilla("Gulali", 4, "Haaum Hauum", "Hitam Manis"),
                new Singa("CingaCing", 4, "Roaar Roaaar", "Coklat")
        };

        for (int i = 0; i < binatang.length; i++) {
            binatang[i].displayMakan();
            binatang[i].displayData();
            if (i < binatang.length - 1) {
                System.out.println();
            }
        }
    }
}
