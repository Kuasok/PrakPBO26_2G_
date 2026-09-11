package id.ac.polinema;

import java.util.Scanner;

public class TestLogistik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nomor resi   : ");
        String nomorResi = scanner.nextLine();
        System.out.print("Masukkan nama pemilik : ");
        String namaPemilik = scanner.nextLine();
        System.out.print("Masukkan kapasitas maks (kg): ");
        double kapasitas = scanner.nextDouble();

        Kontainer kontainer = new Kontainer(nomorResi, namaPemilik, kapasitas);
        System.out.println();
        kontainer.cetakInfo();
        System.out.println();

        System.out.print("Masukkan berat muatan yang ditambahkan (kg): ");
        double tambah = scanner.nextDouble();
        kontainer.tambahMuatan(tambah);
        System.out.println();
        kontainer.cetakInfo();
        System.out.println();

        System.out.print("Masukkan berat muatan yang diturunkan (kg): ");
        double turun = scanner.nextDouble();
        kontainer.turunkanMuatan(turun);
        System.out.println();
        kontainer.cetakInfo();

        scanner.close();
    }
}
