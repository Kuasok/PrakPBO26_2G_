# LAPORAN JOBSHEET 4 — RELASI KELAS

## Identitas
- Nama : Muhammad Ferdi Afiyanto
- NIM : 254107020122
- Kelas : 2G
- Mata Kuliah : Praktikum Pemrograman Berbasis Objek
- Pertemuan : 4
- Materi : Aggregation, Composition, Dependency


---

## 1. Tujuan
Jobsheet ini membahas relasi antar kelas berupa Association, Aggregation, Composition, Dependency (uses-a), serta multiplicity. Implementasi dibangun dalam enam package terpisah sehingga class dengan nama sama, seperti `Laptop` atau `Mobil`, tidak saling bertabrakan.

## 2. Dasar Teori Singkat

### Aggregation
Aggregation adalah relasi has-a dengan objek part yang dibuat di luar objek whole lalu diberikan melalui constructor atau setter. Part dapat tetap berdiri sendiri tanpa whole.

### Composition
Composition adalah relasi has-a dengan kepemilikan yang lebih kuat. Part dibuat oleh whole, biasanya di dalam constructor atau method internal, sehingga lifecycle part terikat pada whole. Pada contoh jobsheet, tidak disediakan setter untuk mengganti part tersebut.

### Dependency
Dependency/uses-a terjadi ketika sebuah class hanya menggunakan object class lain sementara, misalnya melalui parameter method, tanpa menyimpannya sebagai atribut.

### Multiplicity
Multiplicity menyatakan jumlah object yang dapat berelasi, misalnya `1`, `0..1`, `1..*`, dan `0..*`. Jika jumlah part tidak tetap dan banyak, array of object dapat digunakan.

---

# 3. Percobaan 1 — Aggregation Satu-ke-Satu

## Hasil
Program menghasilkan tiga blok informasi laptop. Blok pertama memakai constructor berparameter dengan cache `3`, blok kedua memakai constructor default + setter dengan cache `4`, dan blok ketiga membuat `Processor` langsung sebagai argumen constructor.

### Output
```text
Merk Laptop = Thinkpad
Merk Processor = Intel i5
Cache Memory = 3.00
Merk Laptop = Thinkpad
Merk Processor = Intel i5
Cache Memory = 4.00
Merk Laptop = Thinkpad
Merk Processor = Intel i5
Cache Memory = 3.00
```

## Jawaban Pertanyaan

### 1. Kegunaan setter dan getter
Setter digunakan untuk memberikan atau mengubah nilai atribut private dari luar class melalui method yang terkontrol. Getter digunakan untuk mengambil nilai atribut private. Dengan demikian, akses ke data tidak dilakukan secara langsung dan tetap mengikuti enkapsulasi.

### 2. Perbedaan constructor default dan berparameter
Constructor default tidak menerima parameter sehingga object dapat dibuat tanpa memberikan nilai awal melalui constructor. Constructor berparameter menerima nilai ketika object dibuat, sehingga atribut dapat langsung diisi. Contohnya `new Processor("Intel i5", 3)` langsung mengisi `merk` dan `cache`.

### 3. Atribut Laptop yang bertipe object
Atribut `proc` bertipe object karena dideklarasikan sebagai `Processor`, sedangkan `merk` bertipe `String`. Baris yang menunjukkan relasi adalah:
```java
private Processor proc;
```
Baris tersebut menunjukkan bahwa `Laptop` mempunyai referensi ke object `Processor`.

### 4. Kegunaan `proc.info()`
`proc.info()` memanggil method `info()` milik object `Processor` yang sedang direferensikan oleh atribut `proc`. Laptop mendelegasikan pencetakan detail Processor kepada Processor itu sendiri.

### 5. Constructor dengan variabel vs anonymous object
Keduanya menghasilkan output yang sama karena keduanya membuat object `Processor` dengan data yang sama dan object tersebut diberikan kepada constructor `Laptop`. Perbedaannya hanya cara penulisan: satu menggunakan variabel `p`, sedangkan yang lain membuat object langsung di dalam argumen.

### 6. Aggregation atau Composition?
Relasi ini adalah **Aggregation**. Buktinya `Processor` dibuat di luar class `Laptop`, misalnya:
```java
Processor p = new Processor("Intel i5", 3);
Laptop l = new Laptop("Thinkpad", p);
```
Constructor `Laptop` menerima `Processor` sebagai parameter sehingga Laptop tidak membuat sendiri Processor tersebut.

### 7. Jika `Laptop` membuat Processor sendiri
Versi tersebut secara kriteria kode menjadi **Composition**, bukan Aggregation, karena class `Laptop` sendiri memanggil:
```java
this.proc = new Processor("Generic", 1);
```
Part dibuat oleh whole dan tidak diberikan dari luar.

---

# 4. Percobaan 2 — Aggregation Ganda

## Hasil
`Pelanggan` menyimpan dua object berbeda, yaitu `Mobil` dan `Sopir`, lalu menggunakan keduanya untuk menghitung biaya total.

### Output
```text
Biaya Total = 1100000
Avanza
```

Perhitungan:
```text
(350000 + 200000) × 2 = 1100000
```

## Jawaban Pertanyaan

### 1. Baris yang menunjukkan relasi
Relasi ditunjukkan oleh atribut:
```java
private Mobil mobil;
private Sopir sopir;
```
Kedua atribut tersebut membuat `Pelanggan` memiliki relasi has-a dengan dua class berbeda.

### 2. Mengapa method biaya menerima argument `hari`?
Karena jumlah hari merupakan data milik `Pelanggan`, sedangkan `Mobil` dan `Sopir` hanya menyimpan biaya per hari. `Pelanggan` mengirimkan nilai `hari` ketika meminta object tersebut menghitung biayanya.

### 3. Kegunaan `mobil.hitungBiayaMobil(hari)` dan `sopir.hitungBiayaSopir(hari)`
Keduanya meminta masing-masing object menghitung biaya berdasarkan jumlah hari. `Pelanggan` kemudian menjumlahkan kedua hasil tersebut.

### 4. Kegunaan `p.setMobil(m)` dan `p.setSopir(s)`
Kedua setter memasukkan referensi object `Mobil` dan `Sopir` yang telah dibuat di luar ke dalam object `Pelanggan`. Ini merupakan pola injection yang menunjukkan Aggregation.

### 5. Kegunaan `p.hitungBiayaTotal()`
Method tersebut menghitung keseluruhan biaya rental dengan menjumlahkan biaya mobil dan biaya sopir untuk jumlah hari yang dimiliki pelanggan.

### 6. Urutan `p.getMobil().getMerk()`
Pertama, `p.getMobil()` mengembalikan object `Mobil` yang disimpan dalam `Pelanggan`. Setelah object `Mobil` diperoleh, `.getMerk()` dipanggil pada object tersebut dan mengembalikan `"Avanza"`.

### 7. Jika `setMobil(m)` tidak dipanggil
Atribut `mobil` tetap bernilai `null`. Ketika program menjalankan `mobil.hitungBiayaMobil(hari)`, Java mencoba memanggil method pada referensi null sehingga terjadi `NullPointerException`.

---

# 5. Percobaan 3 — Aggregation Dua Role ke Class yang Sama

## Hasil normal
```text
Nama: Gaya Baru
Kelas: Bisnis
Masinis: Nip: 1234
Nama: Spongebob Squarepants

Asisten: Nip: 4567
Nama: Patrick Star
```

Pada versi tanpa asisten, guard clause membuat program tetap selesai tanpa mencetak baris Asisten.

## Jawaban Pertanyaan

### 1. Kegunaan `this.masinis.info()` dan `this.asisten.info()`
Keduanya memanggil method `info()` milik object `Pegawai` yang sedang direferensikan. Dengan demikian `KeretaApi` dapat menampilkan detail pegawai yang berperan sebagai masinis dan asisten.

### 2. Output sebelum diperbaiki
Sebelum guard clause, program berhenti dengan `NullPointerException` ketika mencapai:
```java
this.asisten.info()
```
Hal itu terjadi karena constructor tiga parameter tidak pernah mengisi atribut `asisten`.

### 3. Isi variabel `asisten`
Nilai `asisten` adalah `null`. Atribut tersebut belum menunjuk ke object `Pegawai` apa pun karena tidak ada assignment terhadapnya pada constructor tiga parameter.

### 4. Apakah `masinis` juga perlu dicek?
Tidak dalam desain yang diberikan. Kedua constructor selalu menerima `Pegawai masinis` sebagai parameter dan langsung melakukan `this.masinis = masinis`, sedangkan masalah yang sengaja diperagakan adalah `asisten` yang memang dapat tidak diisi.

### 5. Dua object atau satu object dipakai dua kali?
Pada contoh Langkah 6 terdapat dua object `Pegawai`:
```java
Pegawai masinis = new Pegawai("1234", "Spongebob Squarepants");
Pegawai asisten = new Pegawai("4567", "Patrick Star");
```
Keduanya kemudian diberikan ke atribut berbeda, sehingga terdapat dua object `Pegawai` yang berbeda dengan dua role berbeda.

---

# 6. Percobaan 4 — Array of Object, Multiplicity, Composition + Aggregation

## Hasil
Gerbong A dibuat dengan 10 kursi. Kursi nomor 1 ditempati Mr. Krab, sedangkan kursi lainnya belum memiliki penumpang.

### Output
```text
Kode: A
Nomor: 1
Penumpang: Ktp: 12345
Nama: Mr. Krab

Nomor: 2
Nomor: 3
Nomor: 4
Nomor: 5
Nomor: 6
Nomor: 7
Nomor: 8
Nomor: 9
Nomor: 10
```

### Relasi
`Gerbong` → `Kursi` adalah **Composition** karena `Gerbong` membuat setiap `Kursi` sendiri:
```java
this.arrayKursi[i] = new Kursi(String.valueOf(i + 1));
```

`Kursi` → `Penumpang` adalah **Aggregation** karena `Penumpang` dibuat di luar dan diberikan melalui setter:
```java
gerbong.setPenumpang(p, 1);
```

## Jawaban Pertanyaan

### 1. Jumlah kursi Gerbong A
Jumlahnya **10 kursi**, karena object dibuat dengan:
```java
new Gerbong("A", 10)
```

### 2. Maksud `if (this.penumpang != null)`
Kode tersebut mengecek apakah kursi mempunyai penumpang. Jika tidak null, detail penumpang dicetak. Jika null, kursi dianggap kosong dan tidak terjadi pemanggilan method pada referensi null.

### 3. Mengapa `nomor - 1`?
Nomor kursi dimulai dari 1 agar mudah dipahami pengguna, sedangkan index array Java dimulai dari 0. Oleh karena itu kursi 1 dipetakan ke index 0, kursi 2 ke index 1, dan seterusnya.

### 4. Jika Budi ditempatkan ke kursi 1
Pada implementasi dasar, object Budi akan menggantikan referensi Mr. Krab pada kursi nomor 1. Java tidak memberi error karena assignment referensi tersebut valid.

### 5. Modifikasi agar kursi tidak dapat ditimpa
Tambahkan guard sebelum setter:
```java
if (arrayKursi[nomor - 1].getPenumpang() != null) {
    System.out.println("Maaf, kursi " + nomor + " sudah terisi.");
    return;
}
arrayKursi[nomor - 1].setPenumpang(penumpang);
```
Dengan begitu, object baru tidak akan menggantikan penumpang yang sudah ada.

### 6. Kapan memakai array dan kapan atribut bernama?
Array dipakai ketika satu object dapat memiliki banyak object sejenis dan jumlahnya dapat berubah atau perlu dikelola sebagai kumpulan, seperti banyak kursi dalam Gerbong. Atribut bernama satu-satu cocok ketika role-nya berbeda dan jumlahnya tetap, seperti `masinis` dan `asisten`.

### 7. Tentukan Aggregation dan Composition
`Gerbong-Kursi` adalah **Composition** karena `Gerbong` memanggil `new Kursi(...)` di `initKursi()`. `Kursi-Penumpang` adalah **Aggregation** karena `Kursi` tidak membuat `Penumpang`; object Penumpang dibuat dari luar dan diberikan melalui `setPenumpang()`.

---

# 7. Percobaan 5 — Composition

## Hasil
```text
Mobil: Avanza
Mesin: 4-silinder
```

## Jawaban Pertanyaan

### 1. Bukti kepemilikan eksklusif
Buktinya terdapat pada constructor `Mobil`:
```java
this.mesin = new Mesin();
```
Object `Mesin` dibuat langsung oleh `Mobil`.

### 2. Jika ditambahkan `setMesin(Mesin mesin)`
Penambahan setter akan membuka jalan bagi object luar untuk mengganti referensi `Mesin` milik `Mobil`. Hal tersebut melemahkan kepemilikan eksklusif yang menjadi karakteristik Composition. Karena itu pada desain jobsheet setter tersebut sengaja tidak dibuat.

### 3. Perbedaan dengan Percobaan 1
Pada Percobaan 1:
```java
public Laptop(String merk, Processor proc)
```
`Processor` diterima dari luar sehingga Aggregation. Pada Percobaan 5:
```java
this.mesin = new Mesin();
```
`Mesin` dibuat sendiri oleh `Mobil` sehingga Composition.

### 4. Jika `mobil` di-set null
Referensi `mobil` di `main` tidak lagi menunjuk ke object `Mobil`. Jika tidak ada referensi lain ke object tersebut maupun ke `Mesin` miliknya, object tersebut pada akhirnya dapat menjadi kandidat garbage collection. `Processor` pada Aggregation berbeda karena object Processor dapat tetap memiliki referensi dari kode lain.

### 5. Constructor kedua yang menerima Mesin
Jika constructor yang digunakan adalah:
```java
public Mobil(String merek, Mesin mesin) {
    this.merek = merek;
    this.mesin = mesin;
}
```
maka berdasarkan kriteria kode jobsheet, relasi tersebut berubah menjadi **Aggregation**, karena object `Mesin` dibuat/dimiliki oleh pihak luar dan diberikan ke `Mobil`.

---

# 8. Percobaan 6 — Dependency / Uses-A

## Hasil
```text
Thinkpad mengirim dokumen ke printer...
[Epson L3110] Mencetak Laporan.pdf...
[Epson L3110] Selesai.
```

## Jawaban Pertanyaan

### 1. Apakah Laptop memiliki atribut Printer?
Tidak. `Laptop` hanya memiliki:
```java
private String merk;
```
`Printer` hanya muncul sebagai parameter method `cetakDokumen()`.

### 2. Apakah Laptop masih menyimpan referensi Printer?
Tidak. Parameter `printer` hanya digunakan selama pemanggilan method:
```java
public void cetakDokumen(Printer printer, String namaFile)
```
Tidak ada atribut `Printer` yang menyimpan referensi tersebut.

### 3. Mengapa Dependency?
Relasi disebut Dependency karena `Laptop` hanya menggunakan object `Printer` sementara melalui parameter method. Berbeda dengan Aggregation, object Printer tidak disimpan sebagai atribut Laptop.

### 4. Jika Printer disimpan sebagai atribut
Jika `Printer` menjadi atribut `Laptop` dan diisi melalui constructor/setter, maka Laptop menyimpan referensi terhadap Printer. Berdasarkan kriteria jobsheet, relasi tersebut menjadi **Aggregation**.

### 5. Tabel perbandingan

| Relasi | Part disimpan sebagai atribut? | Siapa yang memanggil `new`? |
|---|---|---|
| Aggregation | Ya | Umumnya kode di luar whole, lalu object di-inject |
| Composition | Ya | Whole membuat part sendiri |
| Dependency | Tidak | Kode pengguna membuat object lalu mengirimkannya sebagai parameter |

---

# 9. Tugas Mandiri — Studi Kasus Perpustakaan

## 9.1 Deskripsi
Studi kasus yang dipilih adalah sistem sederhana perpustakaan. Sebuah `Perpustakaan` mempunyai sebuah `Buku` yang diberikan dari luar, mempunyai `Rak` yang dibuat sendiri, dan mempunyai method `pinjam()` yang menggunakan object `Buku` hanya sebagai parameter.

Jumlah class yang dihitung:
1. `Buku`
2. `Rak`
3. `Perpustakaan`
4. `MainTugasMandiri`

Class `Main` tidak dihitung sebagai class studi kasus, sehingga terdapat tiga class domain. Agar memenuhi syarat minimal 4 class domain, implementasi diperluas dengan `Anggota`.

## 9.2 Implementasi final tugas mandiri

### Diagram Kelas (representasi teks)

```text
+----------------+          +----------------+
|  Perpustakaan  |◇-------->|      Buku      |
+----------------+          +----------------+
| - buku: Buku   |          | - judul:String |
| - rak: Rak     |          +----------------+
+-------+--------+
        ◆
        |
        v
   +----------+
   |   Rak    |
   +----------+

Perpustakaan .........> Anggota
        Dependency / uses-a
        melalui pinjam(Anggota)

+----------------+
|    Anggota     |
+----------------+
| - nama:String  |
+----------------+
```

## 9.3 Penandaan relasi pada kode

### Aggregation — `Perpustakaan` → `Buku`
Object `Buku` dibuat di luar `Perpustakaan`, kemudian diberikan ke constructor:
```java
Buku buku = new Buku("Pemrograman Berbasis Objek");
Perpustakaan perpustakaan = new Perpustakaan(buku);
```
dan disimpan:
```java
private Buku buku;
```

### Composition — `Perpustakaan` → `Rak`
`Perpustakaan` membuat sendiri `Rak` di constructor:
```java
this.rak = new Rak("RAK-01");
```
Tidak ada setter `setRak()`.

### Dependency — `Perpustakaan` → `Anggota`
`Anggota` hanya muncul sebagai parameter method:
```java
public void pinjam(Anggota anggota) {
    System.out.println("Peminjaman oleh: " + anggota.getNama());
}
```
Tidak ada atribut `Anggota` di `Perpustakaan`.

## 9.4 Output tugas mandiri
```text
Buku: Pemrograman Berbasis Objek
Rak: RAK-01
Peminjaman oleh: Budi
Buku: Pemrograman Berbasis Objek
```

## 9.5 Jawaban singkat tugas nomor 2
Dalam menentukan relasi, pertama tanyakan apakah object lain perlu disimpan sebagai atribut atau hanya digunakan sementara. Jika disimpan, tanyakan lagi siapa yang membuat object part: jika dibuat dari luar dan diberikan ke whole, gunakan Aggregation; jika whole membuat sendiri, gunakan Composition. Jika object hanya diperlukan selama pemanggilan method dan tidak disimpan, gunakan Dependency. Pertanyaan kuncinya adalah: “Apakah object ini disimpan?”, “Siapa yang membuatnya?”, dan “Seberapa kuat kepemilikannya?”

---

# 10. Kesimpulan
Jobsheet 4 menunjukkan bahwa relasi antar class tidak cukup dilihat hanya dari adanya atribut bertipe object. Aggregation ditunjukkan oleh part yang dibuat di luar whole dan diberikan melalui constructor/setter, Composition oleh part yang dibuat sendiri oleh whole, sedangkan Dependency terjadi ketika object hanya digunakan sementara melalui parameter method. Percobaan 3 dan 4 juga memperlihatkan pentingnya pengecekan `null`, array of object, serta multiplicity. Struktur package pada proyek ini dibuat mengikuti deklarasi package Java agar dapat dibuka sebagai satu source tree tanpa error package mismatch di VS Code.



## 3.1 Percobaan 1 – Aggregation Satu-ke-Satu

### `Processor.java`

```java
package id.ac.polinema.relasiclass.percobaan1;
public class Processor {
 private String merk; private double cache;
 public Processor() {}
 public Processor(String merk,double cache) { this.merk=merk; this.cache=cache; }
 public void setMerk(String merk) { this.merk=merk; } public String getMerk() { return merk; }
 public void setCache(double cache) { this.cache=cache; } public double getCache() { return cache; }
 public void info() { System.out.printf("Merk Processor = %s\n",merk); System.out.printf("Cache Memory = %.2f\n",cache); }
}
```

### `Laptop.java`

```java
package id.ac.polinema.relasiclass.percobaan1;
public class Laptop {
 private String merk; private Processor proc;
 public Laptop() {}
 public Laptop(String merk,Processor proc) { this.merk=merk; this.proc=proc; }
 public void setMerk(String merk) { this.merk=merk; } public String getMerk() { return merk; }
 public void setProc(Processor proc) { this.proc=proc; } public Processor getProc() { return proc; }
 public void info() { System.out.println("Merk Laptop = "+merk); proc.info(); }
}
```

### `MainPercobaan1.java`

```java
package id.ac.polinema.relasiclass.percobaan1;
public class MainPercobaan1 {
 public static void main(String[] args) {
  Processor p=new Processor("Intel i5",3); Laptop l=new Laptop("Thinkpad",p); l.info();
  Processor p1=new Processor(); p1.setMerk("Intel i5"); p1.setCache(4); Laptop l1=new Laptop(); l1.setMerk("Thinkpad"); l1.setProc(p1); l1.info();
  Laptop l2=new Laptop("Thinkpad",new Processor("Intel i5",3)); l2.info();
 }
}
```

### Output

```text
Merk Laptop = Thinkpad
Merk Processor = Intel i5
Cache Memory = 3.00
Merk Laptop = Thinkpad
Merk Processor = Intel i5
Cache Memory = 4.00
Merk Laptop = Thinkpad
Merk Processor = Intel i5
Cache Memory = 3.00
```

### Analisis

Object `Processor` dibuat di luar `Laptop`, kemudian diberikan melalui constructor atau setter. Karena `Laptop` hanya menyimpan referensi ke object `Processor`, hubungan ini merupakan aggregation.


## 3.2 Percobaan 2 – Aggregation Ganda

### `Pelanggan.java`

```java
package id.ac.polinema.relasiclass.percobaan2;
public class Pelanggan {
 private String nama; private Mobil mobil; private Sopir sopir; private int hari; public Pelanggan() {}
 public void setNama(String nama){this.nama=nama;} public String getNama(){return nama;}
 public void setMobil(Mobil mobil){this.mobil=mobil;} public Mobil getMobil(){return mobil;}
 public void setSopir(Sopir sopir){this.sopir=sopir;} public Sopir getSopir(){return sopir;}
 public void setHari(int hari){this.hari=hari;} public int getHari(){return hari;}
 public int hitungBiayaTotal(){return mobil.hitungBiayaMobil(hari)+sopir.hitungBiayaSopir(hari);}
}
```

### `Mobil.java`

```java
package id.ac.polinema.relasiclass.percobaan2;
public class Mobil {
 private String merk; private int biaya; public Mobil() {}
 public void setMerk(String merk){this.merk=merk;} public String getMerk(){return merk;}
 public void setBiaya(int biaya){this.biaya=biaya;} public int getBiaya(){return biaya;}
 public int hitungBiayaMobil(int hari){return biaya*hari;}
}
```

### `Sopir.java`

```java
package id.ac.polinema.relasiclass.percobaan2;
public class Sopir {
 private String nama; private int biaya; public Sopir() {}
 public void setNama(String nama){this.nama=nama;} public String getNama(){return nama;}
 public void setBiaya(int biaya){this.biaya=biaya;} public int getBiaya(){return biaya;}
 public int hitungBiayaSopir(int hari){return biaya*hari;}
}
```

### `MainPercobaan2.java`

```java
package id.ac.polinema.relasiclass.percobaan2;
public class MainPercobaan2 {
 public static void main(String[] args) {
  Mobil m=new Mobil();m.setMerk("Avanza");m.setBiaya(350000); Sopir s=new Sopir();s.setNama("John Doe");s.setBiaya(200000);
  Pelanggan p=new Pelanggan();p.setNama("Jane Doe");p.setMobil(m);p.setSopir(s);p.setHari(2);
  System.out.println("Biaya Total = "+p.hitungBiayaTotal()); System.out.println(p.getMobil().getMerk());
 }
}
```

### Output

```text
Biaya Total = 1100000
Avanza
```

### Analisis

Class `Pelanggan` memiliki relasi dengan `Mobil` dan `Sopir`. Kedua object dibuat dari luar kemudian diberikan menggunakan setter, sehingga keduanya merupakan bagian yang teragregasi.


## 3.3 Percobaan 3 – Aggregation Dua Role ke Class yang Sama

### `Pegawai.java`

```java
package id.ac.polinema.relasiclass.percobaan3;
public class Pegawai {
 private String nip,nama; public Pegawai(String nip,String nama){this.nip=nip;this.nama=nama;}
 public void setNip(String nip){this.nip=nip;} public String getNip(){return nip;}
 public void setNama(String nama){this.nama=nama;} public String getNama(){return nama;}
 public String info(){String info="";info+="Nip: "+this.nip+"\n";info+="Nama: "+this.nama+"\n";return info;}
}
```

### `KeretaApi.java`

```java
package id.ac.polinema.relasiclass.percobaan3;
public class KeretaApi {
 private String nama,kelas; private Pegawai masinis,asisten;
 public KeretaApi(String nama,String kelas,Pegawai masinis){this.nama=nama;this.kelas=kelas;this.masinis=masinis;}
 public KeretaApi(String nama,String kelas,Pegawai masinis,Pegawai asisten){this.nama=nama;this.kelas=kelas;this.masinis=masinis;this.asisten=asisten;}
 public void setMasinis(Pegawai masinis){this.masinis=masinis;} public Pegawai getMasinis(){return masinis;}
 public void setAsisten(Pegawai asisten){this.asisten=asisten;} public Pegawai getAsisten(){return asisten;}
 public String info(){String info="";info+="Nama: "+this.nama+"\n";info+="Kelas: "+this.kelas+"\n";info+="Masinis: "+this.masinis.info()+"\n";if(this.asisten!=null)info+="Asisten: "+this.asisten.info()+"\n";return info;}
}
```

### `MainPercobaan3.java`

```java
package id.ac.polinema.relasiclass.percobaan3;
public class MainPercobaan3 { public static void main(String[] args) { Pegawai masinis=new Pegawai("1234","Spongebob Squarepants");Pegawai asisten=new Pegawai("4567","Patrick Star");KeretaApi keretaApi=new KeretaApi("Gaya Baru","Bisnis",masinis,asisten);System.out.println(keretaApi.info()); } }
```

### `MainPertanyaan.java`

```java
package id.ac.polinema.relasiclass.percobaan3;
public class MainPertanyaan { public static void main(String[] args) { Pegawai masinis=new Pegawai("1234","Spongebob Squarepants");KeretaApi keretaApi=new KeretaApi("Gaya Baru","Bisnis",masinis);System.out.println(keretaApi.info()); } }
```

### Output

```text
Nama: Gaya Baru
Kelas: Bisnis
Masinis: Nip: 1234
Nama: Spongebob Squarepants

Asisten: Nip: 4567
Nama: Patrick Star
```

### Analisis

Class `KeretaApi` menggunakan class `Pegawai` pada dua role berbeda, yaitu `masinis` dan `asisten`. Guard `if (this.asisten != null)` memungkinkan object kereta tetap menampilkan informasi ketika asisten tidak diberikan.


## 3.4 Percobaan 4 – Array of Object, Multiplicity, Composition + Aggregation

### `Gerbong.java`

```java
package id.ac.polinema.relasiclass.percobaan4;
public class Gerbong {
 private String kode; private Kursi[] arrayKursi;
 public Gerbong(String kode,int jumlah){this.kode=kode;this.arrayKursi=new Kursi[jumlah];this.initKursi();}
 private void initKursi(){for(int i=0;i<arrayKursi.length;i++)this.arrayKursi[i]=new Kursi(String.valueOf(i+1));}
 public void setPenumpang(Penumpang penumpang,int nomor){this.arrayKursi[nomor-1].setPenumpang(penumpang);}
 public String info(){String info="";info+="Kode: "+kode+"\n";for(Kursi kursi:arrayKursi)info+=kursi.info();return info;}
}
```

### `Kursi.java`

```java
package id.ac.polinema.relasiclass.percobaan4;
public class Kursi { private String nomor; private Penumpang penumpang; public Kursi(String nomor){this.nomor=nomor;} public void setPenumpang(Penumpang penumpang){this.penumpang=penumpang;} public Penumpang getPenumpang(){return penumpang;} public String info(){String info="";info+="Nomor: "+nomor+"\n";if(this.penumpang!=null)info+="Penumpang: "+penumpang.info()+"\n";return info;} }
```

### `Penumpang.java`

```java
package id.ac.polinema.relasiclass.percobaan4;
public class Penumpang { private String ktp,nama; public Penumpang(String ktp,String nama){this.ktp=ktp;this.nama=nama;} public String getKtp(){return ktp;} public String getNama(){return nama;} public String info(){String info="";info+="Ktp: "+ktp+"\n";info+="Nama: "+nama+"\n";return info;} }
```

### `MainPercobaan4.java`

```java
package id.ac.polinema.relasiclass.percobaan4;
public class MainPercobaan4 { public static void main(String[] args) { Penumpang p=new Penumpang("12345","Mr. Krab");Gerbong gerbong=new Gerbong("A",10);gerbong.setPenumpang(p,1);System.out.println(gerbong.info()); } }
```

### Output

```text
Kode: A
Nomor: 1
Penumpang: Ktp: 12345
Nama: Mr. Krab

Nomor: 2
Nomor: 3
Nomor: 4
Nomor: 5
Nomor: 6
Nomor: 7
Nomor: 8
Nomor: 9
Nomor: 10
```

### Analisis

`Gerbong` membuat array `Kursi` dan menginisialisasi setiap elemennya dengan `new Kursi(...)`, sehingga hubungan Gerbong–Kursi merupakan composition. Object `Penumpang` diberikan ke kursi dari luar, sehingga hubungan Kursi–Penumpang merupakan aggregation.


## 3.5 Percobaan 5 – Composition

### `Mesin.java`

```java
package id.ac.polinema.relasiclass.percobaan5;
public class Mesin { private String tipe; public Mesin(){this.tipe="4-silinder";} public String getTipe(){return tipe;} }
```

### `Mobil.java`

```java
package id.ac.polinema.relasiclass.percobaan5;
public class Mobil { private String merek; private Mesin mesin; public Mobil(String merek){this.merek=merek;this.mesin=new Mesin();} public void tampilkanInfo(){System.out.println("Mobil: "+merek);System.out.println("Mesin: "+mesin.getTipe());} }
```

### `MainPercobaan5.java`

```java
package id.ac.polinema.relasiclass.percobaan5;
public class MainPercobaan5 { public static void main(String[] args) { Mobil mobil=new Mobil("Avanza");mobil.tampilkanInfo(); } }
```

### Output

```text
Mobil: Avanza
Mesin: 4-silinder
```

### Analisis

Object `Mesin` dibuat langsung di dalam constructor `Mobil` menggunakan `new Mesin()`. Hal ini menunjukkan bahwa `Mobil` memiliki dan mengelola object `Mesin` sebagai bagian internalnya, sehingga relasinya merupakan composition.


## 3.6 Percobaan 6 – Dependency / Uses-A

### `Laptop.java`

```java
package id.ac.polinema.relasiclass.percobaan6;
public class Laptop { private String merk; public Laptop(String merk){this.merk=merk;} public void cetakDokumen(Printer printer,String namaFile){System.out.println(merk+" mengirim dokumen ke printer...");printer.cetak(namaFile);} }
```

### `Printer.java`

```java
package id.ac.polinema.relasiclass.percobaan6;
public class Printer { private String merk; public Printer(String merk){this.merk=merk;} public void cetak(String namaFile){System.out.println("["+merk+"] Mencetak "+namaFile+"...");System.out.println("["+merk+"] Selesai.");} }
```

### `MainPercobaan6.java`

```java
package id.ac.polinema.relasiclass.percobaan6;
public class MainPercobaan6 { public static void main(String[] args) { Laptop laptop=new Laptop("Thinkpad");Printer printer=new Printer("Epson L3110");laptop.cetakDokumen(printer,"Laporan.pdf"); } }
```

### Output

```text
Thinkpad mengirim dokumen ke printer...
[Epson L3110] Mencetak Laporan.pdf...
[Epson L3110] Selesai.
```

### Analisis

`Printer` tidak disimpan sebagai atribut pada `Laptop`. Object printer hanya diterima sebagai parameter method `cetakDokumen()`, sehingga relasi Laptop–Printer merupakan dependency.


## 4.1 Tugas Mandiri – Studi Kasus Perpustakaan

### `Buku.java`

```java
package id.ac.polinema.relasiclass.tugas;
public class Buku { private String judul; public Buku(String judul){this.judul=judul;} public String getJudul(){return judul;} }
```

### `Rak.java`

```java
package id.ac.polinema.relasiclass.tugas;
public class Rak { private String kode; public Rak(String kode){this.kode=kode;} public String getKode(){return kode;} }
```

### `Anggota.java`

```java
package id.ac.polinema.relasiclass.tugas;
public class Anggota { private String nama; public Anggota(String nama) { this.nama = nama; } public String getNama() { return nama; } }
```

### `Perpustakaan.java`

```java
package id.ac.polinema.relasiclass.tugas;
public class Perpustakaan {
    private Buku buku; // Aggregation
    private Rak rak;   // Composition
    public Perpustakaan(Buku buku) {
        this.buku = buku;
        this.rak = new Rak("RAK-01");
    }
    public void pinjam(Anggota anggota) { // Dependency
        System.out.println("Peminjaman oleh: " + anggota.getNama());
        System.out.println("Buku: " + buku.getJudul());
    }
    public void info() {
        System.out.println("Buku: " + buku.getJudul());
        System.out.println("Rak: " + rak.getKode());
    }
}
```

### `MainTugasMandiri.java`

```java
package id.ac.polinema.relasiclass.tugas;
public class MainTugasMandiri {
    public static void main(String[] args) {
        Buku buku = new Buku("Pemrograman Berbasis Objek");
        Anggota anggota = new Anggota("Budi");
        Perpustakaan perpustakaan = new Perpustakaan(buku);
        perpustakaan.info();
        perpustakaan.pinjam(anggota);
    }
}
```

### Output

```text
Buku: Pemrograman Berbasis Objek
Rak: RAK-01
Peminjaman oleh: Budi
Buku: Pemrograman Berbasis Objek
```

### Analisis

`Perpustakaan` menyimpan `Buku` yang dibuat dari luar sehingga merupakan aggregation, membuat `Rak` sendiri sehingga merupakan composition, dan menerima `Anggota` hanya sebagai parameter method `pinjam()` sehingga merupakan dependency.
