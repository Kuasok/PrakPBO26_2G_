# Jobsheet 3 — Enkapsulasi PBO (AIO)

Versi ini disusun ulang dengan acuan langsung pada PDF Jobsheet 03 dan struktur kode yang ada di repository `Kuasok/PrakPBO26_2G_`.

## Isi

- `laporan.md` — laporan lengkap + jawaban seluruh pertanyaan/tugas.
- `src/percobaan/1-enkapsulasi/` — Percobaan 1, sesuai kode screenshot.
- `src/percobaan/2-access-modifier/` — Percobaan 2, sesuai kode screenshot.
- `src/percobaan/3-getter-setter/` — Percobaan 3, sesuai kode screenshot.
- `src/percobaan/4-konstruktor/` — Percobaan 4, sesuai kode screenshot.
- `src/tugas/1-3-age/` — Tugas 1–3.
- `src/tugas/4-kontainer/` — Tugas 4, versi dasar tanpa aturan 50%.
- `src/tugas/5-kontainer-50persen/` — Tugas 5, aturan maksimum bongkar 50%.
- `src/tugas/6-logistik-scanner/` — Tugas 6, input dinamis dengan `Scanner`.
- `src/tugas/7-tiket/` — Tugas 7, class `Tiket` + `TestBioskop` sesuai instruksi.
- `out/` — hasil running program yang sudah diverifikasi.
- `CHECKLIST_PERBAIKAN.md` — daftar ketidaksesuaian yang diperbaiki.
- `reference/` — PDF jobsheet asli sebagai acuan.

## Cara compile

Setiap percobaan/tugas dibuat terpisah karena beberapa tahap memang memiliki class dengan nama sama (`Motor`, `Anggota`, `Kontainer`).

Contoh:

```bash
cd src/percobaan/2-access-modifier
javac *.java
java motorencapsulation.MotorDemo
```

Untuk tugas tanpa package:

```bash
cd src/tugas/4-kontainer
javac *.java
java TestLogistik
```

Untuk Tugas 6, program meminta input dari terminal menggunakan `Scanner`.
