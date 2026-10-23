package id.ac.polinema.polimorfisme.pegawai;

/**
 * Class ini sengaja dipertahankan sebagai bukti eksperimen Langkah 4 Percobaan 4.
 * Kode lolos kompilasi, tetapi gagal saat dijalankan karena objek yang ditunjuk e
 * sebenarnya InternshipEmployee, bukan PermanentEmployee.
 */
public class Eksperimen4 {
    public static void main(String[] args) {
        Employee e = new InternshipEmployee("Sunarto", 5);
        System.out.println("Sebelum casting");
        PermanentEmployee pe = (PermanentEmployee) e;
        System.out.println("Sesudah casting: " + pe.getSalary());
    }
}
