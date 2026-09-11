package tugas.logistikscanner;

import java.util.Scanner;

public class TestLogistik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nomor resi: ");
        String nomorResi = scanner.nextLine();
        System.out.print("Masukkan nama pemilik: ");
        String namaPemilik = scanner.nextLine();
        System.out.print("Masukkan kapasitas maksimal (kg): ");
        double kapasitas = scanner.nextDouble();

        Kontainer kontainer = new Kontainer(nomorResi, namaPemilik, kapasitas);

        System.out.print("Masukkan berat barang yang ditambahkan (kg): ");
        double tambahMuatan = scanner.nextDouble();
        kontainer.tambahMuatan(tambahMuatan);
        System.out.println("Berat muatan saat ini: " + kontainer.getBeratMuatanSaatIni() + " kg");

        System.out.print("Masukkan berat barang yang dibongkar (kg): ");
        double turunkanMuatan = scanner.nextDouble();
        kontainer.turunkanMuatan(turunkanMuatan);
        System.out.println("Berat muatan saat ini: " + kontainer.getBeratMuatanSaatIni() + " kg");

        scanner.close();
    }
}
