# Jobsheet 6 — Inheritance (Pewarisan)

Berbasis pada PDF `Jobsheet 06 - Inheritance revisi 2`.

Isi:
1. Percobaan 1 — Single inheritance dengan `extends` (ClassA–ClassB)
2. Percobaan 2 — Hak akses `private` vs `protected` + getter (ClassA–ClassB)
3. Percobaan 3 — Kata kunci `this` dan `super`, shadowing atribut (Bangun–Tabung)
4. Percobaan 4 — Konstruktor dan multilevel inheritance (ClassA–ClassB–ClassC)
5. Percobaan 5 — Konstruktor berparameter, `super(...)`, dan `@Override` (Komputer–Desktop–Laptop), termasuk tantangan `Workstation`
6. Tugas 1 — Pegawai, Dosen, DaftarGaji (inheritance + aggregation)
7. Tugas 2 — Televisi dan TelevisiModern
8. Tugas 3 (pengayaan) — Hierarki karakter game (Character, Angel, Human, Wizard)

Struktur direktori:

```text
Jobsheet6/
├── src/id/ac/polinema/inheritance/
│   ├── percobaan1/ ... percobaan5/
│   ├── tugas1/  tugas2/
│   └── pengayaan/
├── out/          # output program tiap percobaan + pesan error
├── laporan.md    # laporan lengkap (kode, output, jawaban pertanyaan)
└── README.md
```

Struktur package mengikuti deklarasi `id.ac.polinema.inheritance.percobaanN` pada contoh kode PDF, sehingga proyek dapat dibuka sebagai satu source tree tanpa error *package mismatch* di VS Code.

## Cara kompilasi dan menjalankan

```bash
cd Jobsheet6
mkdir -p build
javac -d build $(find src -name "*.java")

java -cp build id.ac.polinema.inheritance.percobaan1.MainPercobaan1
java -cp build id.ac.polinema.inheritance.percobaan2.MainPercobaan2
java -cp build id.ac.polinema.inheritance.percobaan3.MainPercobaan3
java -cp build id.ac.polinema.inheritance.percobaan4.MainPercobaan4
java -cp build id.ac.polinema.inheritance.percobaan5.MainPercobaan5
java -cp build id.ac.polinema.inheritance.tugas1.MainTugas1
java -cp build id.ac.polinema.inheritance.tugas2.MainTugas2
java -cp build id.ac.polinema.inheritance.pengayaan.MainTugas3
```

> Catatan: jobsheet diuji dengan JDK 21. Pada JDK 25+ (mis. JDK 27) pernyataan sebelum
> `super(...)` menjadi legal karena *Flexible Constructor Bodies* (JEP 513). Lihat
> `out/pesan-error.txt` untuk detailnya.
