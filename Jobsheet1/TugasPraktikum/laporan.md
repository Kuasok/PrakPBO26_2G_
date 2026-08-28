# Tugas Praktikum PBO

## 1. Objek yang Diamati

Objek yang digunakan adalah televisi, radio, lampu, dan jam dinding. Televisi dan radio memiliki konsep pewarisan karena keduanya termasuk perangkat elektronik.

| Objek | Atribut | Method |
|---|---|---|
| Elektronik | `merek`, `daya` | `nyalakan()`, `matikan()`, `cetakInformasi()` |
| Televisi | `ukuranLayar`, `volume` | `gantiSaluran()`, `aturVolume()`, `cetakInformasi()` |
| Radio | `frekuensi`, `menggunakanBaterai` | `cariFrekuensi()`, `putarMusik()`, `cetakInformasi()` |
| Lampu | `jenis`, `tingkatKecerahan` | `nyalakan()`, `aturKecerahan()`, `cetakInformasi()` |
| JamDinding | `bentuk`, `warna` | `tunjukkanWaktu()`, `gantiBaterai()`, `cetakInformasi()` |

## 2. Penerapan Inheritance

Class `Televisi` dan `Radio` mewarisi class `Elektronik` dengan kata kunci `extends`. Atribut dan method umum diletakkan di class `Elektronik`, sedangkan atribut dan method khusus berada pada class turunannya.

Java tidak mendukung satu class mewarisi dua class sekaligus. Karena itu, satu class induk bersama (`Elektronik`) digunakan untuk kedua class turunan tersebut.

## 3. Class Demo

Class `TugasPraktikumDemo` berisi `main()`, membuat satu objek untuk setiap objek pengamatan, dan memanggil seluruh method objek tersebut.

## 4. Cara Menjalankan

```bash
javac *.java
java TugasPraktikumDemo
```
