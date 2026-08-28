# Tugas Praktikum PBO

## 1. Objek yang Diamati

Objek yang digunakan adalah kulkas, kursi, meja ruang tamu, dan meja belajar. Meja ruang tamu serta meja belajar memiliki konsep pewarisan karena keduanya merupakan jenis dari objek meja.

| Objek         | Atribut                       | Method                                                 |
|---------------|-------------------------------|--------------------------------------------------------|
| Kulkas        | `merek`, `suhu`               | `dinginkan()`, `bukaPintu()`, `cetakInformasi()`       |
| Kursi         | `bahan`, `warna`              | `duduk()`, `angkat()`, `cetakInformasi()`              |
| Meja          | `bahan`, `warna`              | `gunakan()`, `pindahkan()`, `cetakInformasi()`         |
| MejaRuangTamu | `jumlahKaki`, `memilikiLaci`  | `letakkanDekorasi()`, `bukaLaci()`, `cetakInformasi()` |
| MejaBelajar   | `jumlahLaci`, `memilikiLampu` | `belajar()`, `nyalakanLampu()`, `cetakInformasi()`     |

## 2. Penerapan Inheritance

Class `MejaRuangTamu` dan `MejaBelajar` mewarisi class `Meja` menggunakan kata kunci `extends`. Dengan demikian, keduanya dapat menggunakan atribut dan method umum dari class `Meja`, sekaligus memiliki atribut dan method khusus masing-masing.

Java tidak mendukung satu class mewarisi dua class sekaligus. Karena itu, satu class induk bersama (`Meja`) digunakan untuk kedua class turunan tersebut.



