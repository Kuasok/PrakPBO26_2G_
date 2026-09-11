# Jobsheet 3 — Enkapsulasi PBO (AIO)

Versi ini disusun ulang dengan acuan langsung pada PDF Jobsheet 03 dan struktur kode yang ada di repository `Kuasok/PrakPBO26_2G_`.

## Isi

- `laporan.md` — laporan lengkap + jawaban seluruh pertanyaan/tugas.
- `src/percobaan/motorpublic/` — Percobaan 1, sesuai kode screenshot.
- `src/percobaan/motorprivate/` — Percobaan 2, sesuai kode screenshot.
- `src/percobaan/koperasigetter/` — Percobaan 3, sesuai kode screenshot.
- `src/percobaan/koperasikonstruktor/` — Percobaan 4, sesuai kode screenshot.
- `src/tugas/age/` — Tugas 1–3.
- `src/tugas/kontainer/` — Tugas 4, versi dasar tanpa aturan 50%.
- `src/tugas/kontainer50persen/` — Tugas 5, aturan maksimum bongkar 50%.
- `src/tugas/logistikscanner/` — Tugas 6, input dinamis dengan `Scanner`.
- `src/tugas/tiket/` — Tugas 7, class `Tiket` + `TestBioskop` sesuai instruksi.
- `out/` — hasil running program yang sudah diverifikasi.
- `CHECKLIST_PERBAIKAN.md` — daftar ketidaksesuaian yang diperbaiki.
- `reference/` — PDF jobsheet asli sebagai acuan.

## Cara compile

Setiap percobaan/tugas dibuat terpisah karena beberapa tahap memang memiliki class dengan nama sama (`Motor`, `Anggota`, `Kontainer`).

Contoh:

```bash
cd src/percobaan/motorprivate
javac *.java
java percobaan.motorprivate.MotorDemo
```

Untuk tugas tanpa package:

```bash
cd src/tugas/kontainer
javac *.java
java tugas.kontainer.TestLogistik
```

Untuk Tugas 6, program meminta input dari terminal menggunakan `Scanner`.
