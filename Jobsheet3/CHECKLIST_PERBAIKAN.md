# Checklist Perbaikan Jobsheet 3

Perbaikan dilakukan dengan membandingkan kode repository dengan kode yang tampak pada screenshot PDF Jobsheet 03.

## Percobaan 1

- Package dikembalikan menjadi `motorencapsulation`.
- `kecepatan` dan `kontakOn` kembali `public` pada tahap awal.
- `kecepatan` bernilai awal `0`.
- `MotorDemo` mengubah `motor.kecepatan = 50` secara langsung, sesuai tujuan demonstrasi enkapsulasi yang belum aman.
- Output menggunakan format `Kontak On/Off` dan `Kecepatan ...` seperti screenshot.

## Percobaan 2

- Package `motorencapsulation`.
- `kecepatan` dan `kontakOn` menjadi `private`.
- `tambahKecepatan()` dan `kurangiKecepatan()` tidak menerima parameter.
- Penambahan/pengurangan kecepatan dilakukan sebesar `5` setiap pemanggilan.
- Pesan mesin OFF dikembalikan sesuai screenshot.
- Getter tambahan dan batas maksimum 100 yang sebelumnya dimasukkan ke kode percobaan dihapus dari tahap screenshot; batas 100 dikerjakan sebagai jawaban pertanyaan.

## Percobaan 3

- Package `koperasigettersetter`.
- `simpanan` menggunakan `float`, sesuai screenshot.
- `Anggota` belum memiliki konstruktor pada tahap ini.
- `nama` dan `alamat` mempunyai getter + setter.
- `simpanan` hanya mempunyai getter dan berubah melalui `setor()` / `pinjam()`.
- `KoperasiDemo` menggunakan data `Iwan Setiawan`, `Jalan Sukarno Hatta no 10`, setor `100000`, pinjam `5000`.

## Percobaan 4

- Konstruktor `Anggota(String nama, String alamat)` menggunakan access modifier default (tanpa `public`).
- Konstruktor menginisialisasi `simpanan = 0`.
- `KoperasiDemo` menggunakan passing parameter `"Iwan"`, `"Jalan Mawar"` lalu menampilkan simpanan awal.

## Tugas 1–3

- Class model sesuai screenshot menggunakan `EncapDemo`.
- Driver sesuai screenshot menggunakan `EncapTest`.
- Tugas 3 menyediakan versi modifikasi dengan batas usia minimum 18 dan maksimum 30.

## Tugas 4

- `Kontainer` mempunyai empat atribut `private` dan getter.
- Kapasitas `5000` menerima `4000`, tetapi menolak `6000`.
- Tidak ada aturan 50% pada versi dasar Tugas 4.
- Pesan kapasitas dikembalikan ke bentuk yang sesuai hasil yang diharapkan pada PDF.

## Tugas 5

- Versi `Kontainer` dimodifikasi dengan batas bongkar maksimal 50% dari muatan saat ini.
- Pesan keselamatan dibuat persis seperti instruksi PDF.

## Tugas 6

- `java.util.Scanner` digunakan untuk input interaktif.
- Nilai `tambahMuatan` dan `turunkanMuatan` berasal dari input pengguna.

## Tugas 7

- `Tiket` memiliki `judulFilm`, `hargaDasar`, `statusPembayaran` sebagai `private`.
- Harga negatif otomatis menjadi `35000`.
- `statusPembayaran` hanya memiliki getter `isStatusPembayaran()` dan tidak memiliki setter.
- Pembayaran dilakukan melalui `lakukanPembayaran()`.
- `TestBioskop` mengikuti pola screenshot dengan `-50000` untuk menguji harga default.
