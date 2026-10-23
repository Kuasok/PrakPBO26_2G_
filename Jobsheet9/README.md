# Jobsheet 9 — Polimorfisme

Berbasis pada PDF `Jobsheet 09 - Polimorfisme`.

Isi:
1. Percobaan 1 — Bentuk dasar polimorfisme: `GambaranDasar`, lalu studi kasus `Employee`/`Payable` dengan `PermanentEmployee`, `InternshipEmployee`, dan `ElectricityBill`
2. Percobaan 2 — *Virtual method invocation* dan *dynamic method dispatch* (`MainPercobaan2`)
3. Percobaan 3 — *Heterogeneous collection* pada `Employee[]` dan `Payable[]` (`MainPercobaan3`)
4. Percobaan 4 — *Polymorphic argument*, `instanceof`, dan casting objek (`Owner`, `MainPercobaan4`, `Eksperimen4`)
5. Tugas 1 — `Zombie` (abstract), `WalkingZombie`, `JumpingZombie`, `Barrier`, `Destroyable`, `Plant` (`MainTugas1`)
6. Tugas 2 — *Heterogeneous collection* `Destroyable[]`, `instanceof`, dan casting (`MainTugas2`)
7. Tugas 3 — Prediksi hasil kompilasi/eksekusi, dibuktikan dengan `Tugas3Bukti`

Struktur direktori:

```text
Jobsheet9/
├── src/id/ac/polinema/polimorfisme/
│   ├── gambaran/     # GambaranDasar
│   ├── pegawai/      # Percobaan 1-4
│   └── zombie/       # Tugas 1-3
├── out/              # output tiap checkpoint + pesan error
├── laporan.md        # laporan lengkap (kode, output, jawaban pertanyaan)
└── README.md
```

Struktur package mengikuti deklarasi `id.ac.polinema.polimorfisme.*` pada contoh kode PDF,
sehingga proyek dapat dibuka sebagai satu source tree tanpa error *package mismatch* di VS Code.

## Cara kompilasi dan menjalankan

```bash
cd Jobsheet9
mkdir -p build
javac -d build $(find src -name "*.java")

java -cp build id.ac.polinema.polimorfisme.gambaran.GambaranDasar
java -cp build id.ac.polinema.polimorfisme.pegawai.MainPercobaan1
java -cp build id.ac.polinema.polimorfisme.pegawai.MainPercobaan2
java -cp build id.ac.polinema.polimorfisme.pegawai.MainPercobaan3
java -cp build id.ac.polinema.polimorfisme.pegawai.MainPercobaan4
java -cp build id.ac.polinema.polimorfisme.zombie.MainTugas1
java -cp build id.ac.polinema.polimorfisme.zombie.MainTugas2
java -cp build id.ac.polinema.polimorfisme.zombie.Tugas3Bukti
```

> Catatan: `Eksperimen4` sengaja dipertahankan sebagai bukti eksperimen Percobaan 4.
> Class ini **lolos kompilasi** tetapi **gagal saat dijalankan** dengan `ClassCastException`,
> sesuai tujuan eksperimen. Stack trace-nya tersimpan di `out/percobaan4-eksperimen.txt`.
> Pesan error seluruh eksperimen dan Tugas 3 tersimpan di `out/pesan-error.txt`.
>
> Catatan: jobsheet diuji dengan JDK 21, sedangkan proyek ini dikompilasi dan dijalankan
> dengan OpenJDK 27 (`javac 27`); seluruh kode hanya memakai fitur bahasa yang stabil sejak
> Java 17 sehingga hasilnya tetap sesuai checkpoint.
