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
# 4. Percobaan 2 — Aggregation Ganda

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

Perhitungan:
```text
(350000 + 200000) × 2 = 1100000
```
# 5. Percobaan 3 — Aggregation Dua Role ke Class yang Sama

### Penjelasan normal
```text
Nama: Gaya Baru
Kelas: Bisnis
Masinis: Nip: 1234
Nama: Spongebob Squarepants

Asisten: Nip: 4567
Nama: Patrick Star
```

Pada versi tanpa asisten, guard clause membuat program tetap selesai tanpa mencetak baris Asisten.
# 6. Percobaan 4 — Array of Object, Multiplicity, Composition + Aggregation

# 3.4 Percobaan 4 – Array of Object, Multiplicity, Composition + Aggregation

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

### Relasi
`Gerbong` → `Kursi` adalah **Composition** karena `Gerbong` membuat setiap `Kursi` sendiri:
```java
this.arrayKursi[i] = new Kursi(String.valueOf(i + 1));
```

`Kursi` → `Penumpang` adalah **Aggregation** karena `Penumpang` dibuat di luar dan diberikan melalui setter:
```java
gerbong.setPenumpang(p, 1);
```
# 7. Percobaan 5 — Composition

### Penjelasan
```text
Mobil: Avanza
Mesin: 4-silinder
```
# 8. Percobaan 6 — Dependency / Uses-A

### Penjelasan
```text
Thinkpad mengirim dokumen ke printer...
[Epson L3110] Mencetak Laporan.pdf...
[Epson L3110] Selesai.
```
# 9. Tugas Mandiri — Studi Kasus Perpustakaan

Studi kasus yang dipilih adalah sistem sederhana perpustakaan. Sebuah `Perpustakaan` mempunyai sebuah `Buku` yang diberikan dari luar, mempunyai `Rak` yang dibuat sendiri, dan mempunyai method `pinjam()` yang menggunakan object `Buku` hanya sebagai parameter.

Jumlah class yang dihitung:
1. `Buku`
2. `Rak`
3. `Perpustakaan`
4. `MainTugasMandiri`

Class `Main` tidak dihitung sebagai class studi kasus, sehingga terdapat tiga class domain. Agar memenuhi syarat minimal 4 class domain, implementasi diperluas dengan `Anggota`.

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

```text
Buku: Pemrograman Berbasis Objek
Rak: RAK-01
Peminjaman oleh: Budi
Buku: Pemrograman Berbasis Objek
```

Dalam menentukan relasi, pertama tanyakan apakah object lain perlu disimpan sebagai atribut atau hanya digunakan sementara. Jika disimpan, tanyakan lagi siapa yang membuat object part: jika dibuat dari luar dan diberikan ke whole, gunakan Aggregation; jika whole membuat sendiri, gunakan Composition. Jika object hanya diperlukan selama pemanggilan method dan tidak disimpan, gunakan Dependency. Pertanyaan kuncinya adalah: “Apakah object ini disimpan?”, “Siapa yang membuatnya?”, dan “Seberapa kuat kepemilikannya?”

---

# 10. Kesimpulan
Jobsheet 4 menunjukkan bahwa relasi antar class tidak cukup dilihat hanya dari adanya atribut bertipe object. Aggregation ditunjukkan oleh part yang dibuat di luar whole dan diberikan melalui constructor/setter, Composition oleh part yang dibuat sendiri oleh whole, sedangkan Dependency terjadi ketika object hanya digunakan sementara melalui parameter method. Percobaan 3 dan 4 juga memperlihatkan pentingnya pengecekan `null`, array of object, serta multiplicity. Struktur package pada proyek ini dibuat mengikuti deklarasi package Java agar dapat dibuka sebagai satu source tree tanpa error package mismatch di VS Code.
