package id.ac.polinema.overloading.tugas1;

public class MainTugas1 {
    public static void main(String[] args) {
        Segitiga segitiga = new Segitiga();

        System.out.println("== Overloading totalSudut ==");
        System.out.println("totalSudut(60)       : " + segitiga.totalSudut(60));
        System.out.println("totalSudut(90, 45)   : " + segitiga.totalSudut(90, 45));

        System.out.println("== Overloading keliling ==");
        System.out.println("keliling(3, 4, 5)    : " + segitiga.keliling(3, 4, 5));
        System.out.printf("keliling(3, 4)       : %.2f%n", segitiga.keliling(3, 4));
    }
}
