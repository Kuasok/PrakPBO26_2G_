# Jobsheet 7 — Overloading dan Overriding

Berbasis pada PDF `Jobsheet 07 - Overloading dan Overriding`.

Isi:
1. Percobaan 1 — Karyawan (superclass) dengan Staff dan Manager; `getGaji()` di-overload dan di-override, `Utama` sebagai driver
2. Latihan 1 (soal 4.1–4.2) — `PerkalianKu`, overloading berdasarkan **jumlah** parameter
3. Latihan 2 (soal 4.3–4.4) — `PerkalianKu`, overloading berdasarkan **tipe** parameter
4. Latihan 3 (soal 4.5–4.6) — `Ikan`/`Piranha`/`Fish`, overriding + *dynamic method dispatch*
5. Tugas 1 (5.1) — `Segitiga`, implementasi overloading pada `totalSudut(...)` dan `keliling(...)`
6. Tugas 2 (5.2) — `Manusia`/`Dosen`/`Mahasiswa`, overriding dengan teknik *dynamic method dispatch*

Struktur direktori:

```text
Jobsheet7/
├── src/id/ac/polinema/overloading/
│   ├── percobaan1/   # Karyawan, Staff, Manager, Utama
│   ├── latihan1/     # PerkalianKu (overload jumlah parameter)
│   ├── latihan2/     # PerkalianKu (overload tipe parameter)
│   ├── latihan3/     # Ikan, Piranha, Fish
│   ├── tugas1/       # Segitiga, MainTugas1
│   └── tugas2/       # Manusia, Dosen, Mahasiswa, MainTugas2
├── out/              # output program tiap percobaan
├── laporan.md        # laporan lengkap (kode, output, jawaban pertanyaan)
└── README.md
```

Contoh kode pada PDF tidak memakai deklarasi `package`, tetapi di sini setiap potongan
diletakkan di package terpisah (`id.ac.polinema.overloading.*`) mengikuti konvensi Jobsheet 6,
agar class bernama sama (`PerkalianKu`) tidak bentrok dan proyek dapat dibuka sebagai satu
source tree tanpa error *package mismatch* di VS Code.

## Cara kompilasi dan menjalankan

```bash
cd Jobsheet7
mkdir -p build
javac -d build $(find src -name "*.java")

java -cp build id.ac.polinema.overloading.percobaan1.Utama
java -cp build id.ac.polinema.overloading.latihan1.PerkalianKu
java -cp build id.ac.polinema.overloading.latihan2.PerkalianKu
java -cp build id.ac.polinema.overloading.latihan3.Fish
java -cp build id.ac.polinema.overloading.tugas1.MainTugas1
java -cp build id.ac.polinema.overloading.tugas2.MainTugas2
```

> Catatan: jobsheet diuji dengan JDK 27 (`javac 27`).
