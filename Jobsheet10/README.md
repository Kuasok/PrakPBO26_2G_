# Jobsheet 10 — Abstract Class dan Interface

Berbasis pada PDF `Jobsheet 9 - Abstract Class dan Interface`. PDF tersebut salah penamaan:
materinya adalah pertemuan 10 (abstract class dan interface), sehingga folder ini diberi nama
`Jobsheet10`.

Isi:
1. Percobaan 1 — *Abstract class*: `Hewan` (abstract), `Kucing`, `Ikan`, `Orang`, dan driver `Program`
2. Percobaan 2 — *Interface*: `ICumlaude`, `Mahasiswa`, `Sarjana`, `PascaSarjana`, `Rektor`, dan `Program`
3. Percobaan 3 — *Multiple interface*: `IBerprestasi` dan driver `ProgramMawapres`
4. Tugas — `Binatang` (abstract), `IKarnivora`, `IHerbivora`, `Keledai`, `Gorilla`, `Singa`, `MainTugas`

Struktur direktori:

```text
Jobsheet10/
├── src/id/ac/polinema/abstrakinterface/
│   ├── abstractclass/     # Percobaan 1
│   ├── interfacelatihan/  # Percobaan 2 dan 3
│   └── tugas/             # Tugas
├── out/                   # output tiap percobaan + pesan error
├── laporan.md             # laporan lengkap (kode, output, jawaban pertanyaan, UML)
└── README.md
```

Contoh kode pada PDF memakai package `abstractclass`, `interfacelatihan`, dan `tugas`.
Di sini package tersebut diletakkan di bawah `id.ac.polinema.abstrakinterface.*` mengikuti
konvensi Jobsheet 7 dan 9, sehingga proyek dapat dibuka sebagai satu source tree tanpa error
*package mismatch* di VS Code.

## Cara kompilasi dan menjalankan

```bash
cd Jobsheet10
mkdir -p build
javac -d build $(find src -name "*.java")

java -cp build id.ac.polinema.abstrakinterface.abstractclass.Program
java -cp build id.ac.polinema.abstrakinterface.interfacelatihan.Program
java -cp build id.ac.polinema.abstrakinterface.interfacelatihan.ProgramMawapres
java -cp build id.ac.polinema.abstrakinterface.tugas.MainTugas
```

> Catatan: hasil setiap program tersimpan di folder `out/`. Pesan error seluruh eksperimen
> (abstract class tidak dapat diinstansiasi, method abstract yang belum di-override, modifier
> interface yang tidak sah, hingga `incompatible types`) tersimpan di `out/pesan-error.txt`.
>
> Catatan: eksperimen error dijalankan pada salinan source terpisah di `/tmp`, lalu source
> dikembalikan seperti semula. Karena itu `src/` hanya berisi kode final yang lolos kompilasi.
>
> Jobsheet diuji dengan JDK 21, sedangkan proyek ini dikompilasi dan dijalankan dengan
> OpenJDK 27 (`javac 27`). Seluruh kode hanya memakai fitur bahasa yang stabil sejak Java 17,
> sehingga hasilnya tetap sesuai checkpoint.
