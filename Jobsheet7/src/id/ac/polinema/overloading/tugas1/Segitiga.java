package id.ac.polinema.overloading.tugas1;

public class Segitiga {
    private int sudut;

    // Sudut ketiga diketahui dari satu sudut: sudut = 180 - sudutA
    public int totalSudut(int sudutA) {
        this.sudut = 180 - sudutA;
        return this.sudut;
    }

    // Sudut ketiga diketahui dari dua sudut: sudut = 180 - (sudutA + sudutB)
    public int totalSudut(int sudutA, int sudutB) {
        this.sudut = 180 - (sudutA + sudutB);
        return this.sudut;
    }

    // Keliling bila ketiga sisi diketahui: keliling = sisiA + sisiB + sisiC
    public int keliling(int sisiA, int sisiB, int sisiC) {
        return sisiA + sisiB + sisiC;
    }

    // Keliling bila hanya dua sisi diketahui (segitiga siku-siku):
    // c = akar(a^2 + b^2), lalu keliling = sisiA + sisiB + c
    public double keliling(int sisiA, int sisiB) {
        double sisiC = Math.sqrt(Math.pow(sisiA, 2) + Math.pow(sisiB, 2));
        return sisiA + sisiB + sisiC;
    }
}
