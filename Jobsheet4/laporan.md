# LAPORAN JOBSHEET 4 — RELASI KELAS

## Identitas

- **Nama** : Muhammad Ferdi Afiyanto
- **NIM** : 254107020122
- **Kelas** : 2G
- **Mata Kuliah** : Praktikum Pemrograman Berbasis Objek
- **Pertemuan** : 4
- **Materi** : Aggregation, Composition, Dependency

---

# 1. Tujuan

Jobsheet ini membahas relasi antar kelas berupa Association, Aggregation, Composition, Dependency (uses-a), serta Multiplicity. Implementasi dibangun dalam enam package terpisah sehingga class dengan nama sama, seperti `Laptop` atau `Mobil`, tidak saling bertabrakan.

# 2. Dasar Teori Singkat

### Aggregation

Aggregation adalah relasi *has-a* dengan objek *part* yang dibuat di luar objek *whole* lalu diberikan melalui constructor atau setter. Part dapat tetap berdiri sendiri tanpa whole.

### Composition

Composition adalah relasi *has-a* dengan kepemilikan yang lebih kuat. Part dibuat oleh whole, biasanya di dalam constructor atau method internal, sehingga lifecycle part terikat pada whole. Pada contoh jobsheet, tidak disediakan setter untuk mengganti part tersebut.

### Dependency

Dependency atau *uses-a* terjadi ketika sebuah class hanya menggunakan object class lain sementara, misalnya melalui parameter method, tanpa menyimpannya sebagai atribut.

### Multiplicity

Multiplicity menyatakan jumlah object yang dapat berelasi, misalnya `1`, `0..1`, `1..*`, dan `0..*`. Jika jumlah part tidak tetap dan banyak, array of object dapat digunakan.

---

# 3. Percobaan 1 — Aggregation Satu-ke-Satu

## 3.1 Kode Program

### `Processor.java`

```java
package id.ac.polinema.relasiclass.percobaan1;

public class Processor {
    private String merk;
    private double cache;

    public Processor() {}

    public Processor(String merk, double cache) {
        this.merk = merk;
        this.cache = cache;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getMerk() {
        return merk;
    }

    public void setCache(double cache) {
        this.cache = cache;
    }

    public double getCache() {
        return cache;
    }

    public void info() {
        System.out.printf("Merk Processor = %s\n", merk);
        System.out.printf("Cache Memory = %.2f\n", cache);
    }
}
```

### `Laptop.java`

```java
package id.ac.polinema.relasiclass.percobaan1;

public class Laptop {
    private String merk;
    private Processor proc;

    public Laptop() {}

    public Laptop(String merk, Processor proc) {
        this.merk = merk;
        this.proc = proc;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getMerk() {
        return merk;
    }

    public void setProc(Processor proc) {
        this.proc = proc;
    }

    public Processor getProc() {
        return proc;
    }

    public void info() {
        System.out.println("Merk Laptop = " + merk);
        proc.info();
    }
}
```

### `MainPercobaan1.java`

```java
package id.ac.polinema.relasiclass.percobaan1;

public class MainPercobaan1 {
    public static void main(String[] args) {
        Processor p = new Processor("Intel i5", 3);
        Laptop l = new Laptop("Thinkpad", p);
        l.info();

        Processor p1 = new Processor();
        p1.setMerk("Intel i5");
        p1.setCache(4);

        Laptop l1 = new Laptop();
        l1.setMerk("Thinkpad");
        l1.setProc(p1);
        l1.info();

        Laptop l2 = new Laptop(
            "Thinkpad",
            new Processor("Intel i5", 3)
        );
        l2.info();
    }
}
```

## 3.2 Output

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

## 3.3 Penjelasan

Program menghasilkan tiga blok informasi laptop.

Blok pertama menggunakan constructor berparameter dengan `Processor` yang memiliki cache `3`. Blok kedua menggunakan constructor default kemudian mengisi atribut menggunakan setter dengan cache `4`. Blok ketiga membuat object `Processor` secara langsung sebagai argument constructor `Laptop`.

Relasi antara `Laptop` dan `Processor` merupakan Aggregation karena object `Processor` dibuat di luar `Laptop` kemudian diberikan melalui constructor atau setter.

## 3.4 Jawaban Percobaan

### 1. Kegunaan setter dan getter

Setter digunakan untuk memberikan atau mengubah nilai atribut private dari luar class melalui method yang terkontrol. Getter digunakan untuk mengambil nilai atribut private.

Dengan demikian, akses terhadap data tidak dilakukan secara langsung sehingga prinsip enkapsulasi tetap diterapkan.

### 2. Perbedaan constructor default dan constructor berparameter

Constructor default tidak menerima parameter sehingga object dapat dibuat tanpa memberikan nilai awal melalui constructor.

Constructor berparameter menerima nilai ketika object dibuat sehingga atribut dapat langsung diisi.

Contoh:

```java
new Processor("Intel i5", 3)
```

Constructor tersebut langsung mengisi `merk` dan `cache`.

### 3. Atribut Laptop yang bertipe object

Atribut `proc` bertipe object karena dideklarasikan sebagai `Processor`, sedangkan `merk` bertipe `String`.

```java
private Processor proc;
```

Baris tersebut menunjukkan bahwa `Laptop` mempunyai referensi terhadap object `Processor`.

### 4. Kegunaan `proc.info()`

`proc.info()` memanggil method `info()` milik object `Processor` yang sedang direferensikan oleh atribut `proc`.

Laptop mendelegasikan pencetakan detail Processor kepada object Processor tersebut.

### 5. Perbedaan constructor dengan variabel dan anonymous object

Keduanya menghasilkan output yang sama karena keduanya membuat object `Processor` dengan data yang sama dan object tersebut diberikan kepada constructor `Laptop`.

Perbedaannya hanya pada cara penulisan. Pada cara pertama object disimpan terlebih dahulu dalam variabel `p`, sedangkan pada cara kedua object dibuat langsung di dalam argument constructor.

### 6. Aggregation atau Composition?

Relasi tersebut adalah **Aggregation**.

Buktinya, object `Processor` dibuat di luar class `Laptop`:

```java
Processor p = new Processor("Intel i5", 3);
Laptop l = new Laptop("Thinkpad", p);
```

Constructor `Laptop` menerima `Processor` sebagai parameter sehingga `Laptop` tidak membuat sendiri object tersebut.

### 7. Jika Laptop membuat Processor sendiri

Jika `Laptop` membuat `Processor` sendiri:

```java
this.proc = new Processor("Generic", 1);
```

maka berdasarkan kriteria kode tersebut relasinya menjadi **Composition**, karena part dibuat oleh whole.

---

# 4. Percobaan 2 — Aggregation Ganda

## 4.1 Kode Program

### `Pelanggan.java`

```java
package id.ac.polinema.relasiclass.percobaan2;

public class Pelanggan {
    private String nama;
    private Mobil mobil;
    private Sopir sopir;
    private int hari;

    public Pelanggan() {}

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setMobil(Mobil mobil) {
        this.mobil = mobil;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public void setSopir(Sopir sopir) {
        this.sopir = sopir;
    }

    public Sopir getSopir() {
        return sopir;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }

    public int getHari() {
        return hari;
    }

    public int hitungBiayaTotal() {
        return mobil.hitungBiayaMobil(hari)
                + sopir.hitungBiayaSopir(hari);
    }
}
```

### `Mobil.java`

```java
package id.ac.polinema.relasiclass.percobaan2;

public class Mobil {
    private String merk;
    private int biaya;

    public Mobil() {}

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getMerk() {
        return merk;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public int getBiaya() {
        return biaya;
    }

    public int hitungBiayaMobil(int hari) {
        return biaya * hari;
    }
}
```

### `Sopir.java`

```java
package id.ac.polinema.relasiclass.percobaan2;

public class Sopir {
    private String nama;
    private int biaya;

    public Sopir() {}

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public int getBiaya() {
        return biaya;
    }

    public int hitungBiayaSopir(int hari) {
        return biaya * hari;
    }
}
```

### `MainPercobaan2.java`

```java
package id.ac.polinema.relasiclass.percobaan2;

public class MainPercobaan2 {
    public static void main(String[] args) {
        Mobil m = new Mobil();
        m.setMerk("Avanza");
        m.setBiaya(350000);

        Sopir s = new Sopir();
        s.setNama("John Doe");
        s.setBiaya(200000);

        Pelanggan p = new Pelanggan();
        p.setNama("Jane Doe");
        p.setMobil(m);
        p.setSopir(s);
        p.setHari(2);

        System.out.println("Biaya Total = " + p.hitungBiayaTotal());
        System.out.println(p.getMobil().getMerk());
    }
}
```

## 4.2 Output

```text
Biaya Total = 1100000
Avanza
```

Perhitungan:

```text
(350000 + 200000) × 2 = 1100000
```

## 4.3 Penjelasan

`Pelanggan` menyimpan dua object berbeda, yaitu `Mobil` dan `Sopir`. Kedua object tersebut dibuat di luar object `Pelanggan`, kemudian diberikan melalui setter.

Karena `Pelanggan` hanya menyimpan referensi terhadap object yang telah dibuat dari luar, relasi tersebut merupakan Aggregation.

## 4.4 Jawaban Percobaan

### 1. Baris yang menunjukkan relasi

```java
private Mobil mobil;
private Sopir sopir;
```

Kedua atribut tersebut menunjukkan bahwa `Pelanggan` mempunyai relasi *has-a* dengan dua class berbeda.

### 2. Mengapa method biaya menerima argument `hari`?

Karena jumlah hari merupakan data milik `Pelanggan`, sedangkan `Mobil` dan `Sopir` hanya menyimpan biaya per hari.

`Pelanggan` mengirimkan nilai `hari` ketika meminta object tersebut menghitung biaya.

### 3. Kegunaan `mobil.hitungBiayaMobil(hari)` dan `sopir.hitungBiayaSopir(hari)`

Kedua method tersebut meminta masing-masing object menghitung biaya berdasarkan jumlah hari.

Hasil dari kedua perhitungan kemudian dijumlahkan oleh `Pelanggan`.

### 4. Kegunaan `p.setMobil(m)` dan `p.setSopir(s)`

Kedua setter memasukkan referensi object `Mobil` dan `Sopir` yang telah dibuat di luar ke dalam object `Pelanggan`.

Hal tersebut merupakan salah satu ciri Aggregation.

### 5. Kegunaan `p.hitungBiayaTotal()`

Method tersebut menghitung keseluruhan biaya rental dengan menjumlahkan biaya mobil dan biaya sopir berdasarkan jumlah hari.

### 6. Urutan `p.getMobil().getMerk()`

Pertama, `p.getMobil()` mengembalikan object `Mobil` yang disimpan dalam `Pelanggan`.

Setelah object `Mobil` diperoleh, method `getMerk()` dipanggil sehingga menghasilkan:

```text
Avanza
```

### 7. Jika `setMobil(m)` tidak dipanggil

Atribut `mobil` tetap bernilai `null`.

Ketika program menjalankan:

```java
mobil.hitungBiayaMobil(hari)
```

Java akan mencoba memanggil method pada referensi `null` sehingga terjadi `NullPointerException`.

---

# 5. Percobaan 3 — Aggregation Dua Role ke Class yang Sama

## 5.1 Kode Program

### `Pegawai.java`

```java
package id.ac.polinema.relasiclass.percobaan3;

public class Pegawai {
    private String nip;
    private String nama;

    public Pegawai(String nip, String nama) {
        this.nip = nip;
        this.nama = nama;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public String info() {
        String info = "";
        info += "Nip: " + this.nip + "\n";
        info += "Nama: " + this.nama + "\n";
        return info;
    }
}
```

### `KeretaApi.java`

```java
package id.ac.polinema.relasiclass.percobaan3;

public class KeretaApi {
    private String nama;
    private String kelas;
    private Pegawai masinis;
    private Pegawai asisten;

    public KeretaApi(String nama, String kelas, Pegawai masinis) {
        this.nama = nama;
        this.kelas = kelas;
        this.masinis = masinis;
    }

    public KeretaApi(
        String nama,
        String kelas,
        Pegawai masinis,
        Pegawai asisten
    ) {
        this.nama = nama;
        this.kelas = kelas;
        this.masinis = masinis;
        this.asisten = asisten;
    }

    public void setMasinis(Pegawai masinis) {
        this.masinis = masinis;
    }

    public Pegawai getMasinis() {
        return masinis;
    }

    public void setAsisten(Pegawai asisten) {
        this.asisten = asisten;
    }

    public Pegawai getAsisten() {
        return asisten;
    }

    public String info() {
        String info = "";

        info += "Nama: " + this.nama + "\n";
        info += "Kelas: " + this.kelas + "\n";
        info += "Masinis: " + this.masinis.info() + "\n";

        if (this.asisten != null) {
            info += "Asisten: " + this.asisten.info() + "\n";
        }

        return info;
    }
}
```

### `MainPercobaan3.java`

```java
package id.ac.polinema.relasiclass.percobaan3;

public class MainPercobaan3 {
    public static void main(String[] args) {
        Pegawai masinis =
            new Pegawai("1234", "Spongebob Squarepants");

        Pegawai asisten =
            new Pegawai("4567", "Patrick Star");

        KeretaApi keretaApi =
            new KeretaApi(
                "Gaya Baru",
                "Bisnis",
                masinis,
                asisten
            );

        System.out.println(keretaApi.info());
    }
}
```

### `MainPertanyaan.java`

```java
package id.ac.polinema.relasiclass.percobaan3;

public class MainPertanyaan {
    public static void main(String[] args) {
        Pegawai masinis =
            new Pegawai("1234", "Spongebob Squarepants");

        KeretaApi keretaApi =
            new KeretaApi(
                "Gaya Baru",
                "Bisnis",
                masinis
            );

        System.out.println(keretaApi.info());
    }
}
```

## 5.2 Output

### Output `MainPercobaan3`

```text
Nama: Gaya Baru
Kelas: Bisnis
Masinis: Nip: 1234
Nama: Spongebob Squarepants

Asisten: Nip: 4567
Nama: Patrick Star
```

### Output `MainPertanyaan`

```text
Nama: Gaya Baru
Kelas: Bisnis
Masinis: Nip: 1234
Nama: Spongebob Squarepants
```

## 5.3 Penjelasan

Class `KeretaApi` memiliki dua atribut yang sama-sama bertipe `Pegawai`, yaitu `masinis` dan `asisten`.

Kedua atribut tersebut memiliki role berbeda walaupun menggunakan class yang sama. Object `Pegawai` dibuat di luar `KeretaApi` kemudian diberikan melalui constructor.

Pada constructor yang hanya menerima `masinis`, atribut `asisten` tidak memiliki object sehingga bernilai `null`. Karena itu digunakan guard clause:

```java
if (this.asisten != null)
```

agar program tidak mencoba memanggil method pada object `null`.

## 5.4 Jawaban Percobaan

### 1. Kegunaan `this.masinis.info()` dan `this.asisten.info()`

Keduanya memanggil method `info()` milik object `Pegawai` yang sedang direferensikan.

Dengan demikian `KeretaApi` dapat menampilkan informasi pegawai yang berperan sebagai masinis dan asisten.

### 2. Output sebelum diperbaiki

Sebelum menggunakan guard clause, program akan mengalami `NullPointerException` ketika menjalankan:

```java
this.asisten.info()
```

Hal tersebut terjadi karena constructor tiga parameter tidak mengisi atribut `asisten`.

### 3. Isi variabel `asisten`

Nilai `asisten` adalah:

```java
null
```

Atribut tersebut belum menunjuk ke object `Pegawai` karena tidak ada assignment terhadapnya pada constructor tiga parameter.

### 4. Apakah `masinis` juga perlu dicek?

Tidak dalam desain yang diberikan.

Kedua constructor selalu menerima `Pegawai masinis` sebagai parameter dan langsung melakukan:

```java
this.masinis = masinis;
```

Sedangkan `asisten` dapat tidak diberikan sehingga perlu dilakukan pengecekan `null`.

### 5. Dua object atau satu object digunakan dua kali?

Pada contoh tersebut terdapat dua object `Pegawai`:

```java
Pegawai masinis =
    new Pegawai("1234", "Spongebob Squarepants");

Pegawai asisten =
    new Pegawai("4567", "Patrick Star");
```

Keduanya merupakan object berbeda dan digunakan pada role yang berbeda, yaitu masinis dan asisten.

---

# 6. Percobaan 4 — Array of Object, Multiplicity, Composition + Aggregation

## 6.1 Kode Program

### `Gerbong.java`

```java
package id.ac.polinema.relasiclass.percobaan4;

public class Gerbong {
    private String kode;
    private Kursi[] arrayKursi;

    public Gerbong(String kode, int jumlah) {
        this.kode = kode;
        this.arrayKursi = new Kursi[jumlah];
        this.initKursi();
    }

    private void initKursi() {
        for (int i = 0; i < arrayKursi.length; i++) {
            this.arrayKursi[i] =
                new Kursi(String.valueOf(i + 1));
        }
    }

    public void setPenumpang(
        Penumpang penumpang,
        int nomor
    ) {
        this.arrayKursi[nomor - 1]
            .setPenumpang(penumpang);
    }

    public String info() {
        String info = "";

        info += "Kode: " + kode + "\n";

        for (Kursi kursi : arrayKursi) {
            info += kursi.info();
        }

        return info;
    }
}
```

### `Kursi.java`

```java
package id.ac.polinema.relasiclass.percobaan4;

public class Kursi {
    private String nomor;
    private Penumpang penumpang;

    public Kursi(String nomor) {
        this.nomor = nomor;
    }

    public void setPenumpang(Penumpang penumpang) {
        this.penumpang = penumpang;
    }

    public Penumpang getPenumpang() {
        return penumpang;
    }

    public String info() {
        String info = "";

        info += "Nomor: " + nomor + "\n";

        if (this.penumpang != null) {
            info += "Penumpang: "
                    + penumpang.info()
                    + "\n";
        }

        return info;
    }
}
```

### `Penumpang.java`

```java
package id.ac.polinema.relasiclass.percobaan4;

public class Penumpang {
    private String ktp;
    private String nama;

    public Penumpang(String ktp, String nama) {
        this.ktp = ktp;
        this.nama = nama;
    }

    public String getKtp() {
        return ktp;
    }

    public String getNama() {
        return nama;
    }

    public String info() {
        String info = "";

        info += "Ktp: " + ktp + "\n";
        info += "Nama: " + nama + "\n";

        return info;
    }
}
```

### `MainPercobaan4.java`

```java
package id.ac.polinema.relasiclass.percobaan4;

public class MainPercobaan4 {
    public static void main(String[] args) {
        Penumpang p =
            new Penumpang("12345", "Mr. Krab");

        Gerbong gerbong =
            new Gerbong("A", 10);

        gerbong.setPenumpang(p, 1);

        System.out.println(gerbong.info());
    }
}
```

## 6.2 Output

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

## 6.3 Penjelasan

Object `Gerbong` memiliki array yang berisi object `Kursi`. Jumlah kursi ditentukan ketika `Gerbong` dibuat.

Pada contoh:

```java
new Gerbong("A", 10)
```

terdapat 10 kursi.

Relasi `Gerbong` → `Kursi` merupakan **Composition** karena `Gerbong` membuat object `Kursi` sendiri:

```java
this.arrayKursi[i] =
    new Kursi(String.valueOf(i + 1));
```

Sedangkan relasi `Kursi` → `Penumpang` merupakan **Aggregation** karena object `Penumpang` dibuat dari luar kemudian diberikan melalui setter:

```java
gerbong.setPenumpang(p, 1);
```

## 6.4 Jawaban Percobaan

### 1. Jumlah kursi Gerbong A

Jumlahnya adalah **10 kursi**, karena object dibuat menggunakan:

```java
new Gerbong("A", 10)
```

### 2. Maksud `if (this.penumpang != null)`

Kode tersebut mengecek apakah kursi memiliki penumpang.

Jika tidak `null`, detail penumpang dicetak. Jika `null`, kursi dianggap kosong sehingga method `info()` milik `Penumpang` tidak dipanggil.

### 3. Mengapa menggunakan `nomor - 1`?

Nomor kursi dimulai dari 1 agar mudah dipahami pengguna, sedangkan index array Java dimulai dari 0.

```text
Kursi 1 → index 0
Kursi 2 → index 1
Kursi 3 → index 2
```

dan seterusnya.

### 4. Jika Budi ditempatkan ke kursi 1

Pada implementasi dasar, object Budi akan menggantikan referensi Mr. Krab pada kursi nomor 1.

Java tidak menghasilkan error karena assignment referensi tersebut valid.

### 5. Modifikasi agar kursi tidak dapat ditimpa

Tambahkan guard sebelum setter:

```java
if (arrayKursi[nomor - 1].getPenumpang() != null) {
    System.out.println(
        "Maaf, kursi " + nomor + " sudah terisi."
    );
    return;
}

arrayKursi[nomor - 1]
    .setPenumpang(penumpang);
```

Dengan begitu object baru tidak akan menggantikan penumpang yang sudah ada.

### 6. Kapan memakai array dan kapan atribut bernama?

Array digunakan ketika satu object dapat memiliki banyak object sejenis dan jumlahnya dapat berubah atau perlu dikelola sebagai kumpulan.

Atribut bernama lebih cocok digunakan ketika role berbeda dan jumlahnya tetap, seperti:

```java
private Pegawai masinis;
private Pegawai asisten;
```

### 7. Tentukan Aggregation dan Composition

`Gerbong` → `Kursi` adalah **Composition** karena `Gerbong` memanggil:

```java
new Kursi(...)
```

di dalam `initKursi()`.

`Kursi` → `Penumpang` adalah **Aggregation** karena `Kursi` tidak membuat `Penumpang`. Object `Penumpang` dibuat dari luar dan diberikan melalui `setPenumpang()`.

---

# 7. Percobaan 5 — Composition

## 7.1 Kode Program

### `Mesin.java`

```java
package id.ac.polinema.relasiclass.percobaan5;

public class Mesin {
    private String tipe;

    public Mesin() {
        this.tipe = "4-silinder";
    }

    public String getTipe() {
        return tipe;
    }
}
```

### `Mobil.java`

```java
package id.ac.polinema.relasiclass.percobaan5;

public class Mobil {
    private String merek;
    private Mesin mesin;

    public Mobil(String merek) {
        this.merek = merek;
        this.mesin = new Mesin();
    }

    public void tampilkanInfo() {
        System.out.println("Mobil: " + merek);
        System.out.println("Mesin: " + mesin.getTipe());
    }
}
```

### `MainPercobaan5.java`

```java
package id.ac.polinema.relasiclass.percobaan5;

public class MainPercobaan5 {
    public static void main(String[] args) {
        Mobil mobil = new Mobil("Avanza");
        mobil.tampilkanInfo();
    }
}
```

## 7.2 Output

```text
Mobil: Avanza
Mesin: 4-silinder
```

## 7.3 Penjelasan

Pada constructor `Mobil`, object `Mesin` dibuat secara langsung:

```java
this.mesin = new Mesin();
```

Artinya `Mobil` bertanggung jawab membuat object `Mesin`.

Tidak terdapat setter yang memungkinkan object luar mengganti `Mesin`. Hal tersebut menunjukkan kepemilikan yang lebih kuat sehingga relasi Mobil → Mesin merupakan **Composition**.

## 7.4 Jawaban Percobaan

### 1. Bukti kepemilikan eksklusif

Buktinya terdapat pada constructor `Mobil`:

```java
this.mesin = new Mesin();
```

Object `Mesin` dibuat langsung oleh `Mobil`.

### 2. Jika ditambahkan `setMesin(Mesin mesin)`

Penambahan setter memungkinkan object dari luar mengganti referensi `Mesin` milik `Mobil`.

Hal tersebut melemahkan kepemilikan eksklusif yang menjadi karakteristik Composition. Karena itu pada desain jobsheet setter tersebut tidak dibuat.

### 3. Perbedaan dengan Percobaan 1

Pada Percobaan 1:

```java
public Laptop(String merk, Processor proc)
```

`Processor` diterima dari luar sehingga relasinya adalah Aggregation.

Sedangkan pada Percobaan 5:

```java
this.mesin = new Mesin();
```

`Mesin` dibuat sendiri oleh `Mobil` sehingga relasinya adalah Composition.

### 4. Jika `mobil` di-set `null`

Jika referensi `mobil` tidak lagi menunjuk ke object `Mobil` dan tidak terdapat referensi lain terhadap object tersebut maupun `Mesin` miliknya, object tersebut pada akhirnya dapat menjadi kandidat garbage collection.

### 5. Constructor kedua yang menerima Mesin

Jika digunakan constructor:

```java
public Mobil(String merek, Mesin mesin) {
    this.merek = merek;
    this.mesin = mesin;
}
```

maka berdasarkan kriteria kode jobsheet, relasi tersebut berubah menjadi **Aggregation**, karena object `Mesin` dibuat atau dimiliki pihak luar kemudian diberikan kepada `Mobil`.

---

# 8. Percobaan 6 — Dependency / Uses-A

## 8.1 Kode Program

### `Laptop.java`

```java
package id.ac.polinema.relasiclass.percobaan6;

public class Laptop {
    private String merk;

    public Laptop(String merk) {
        this.merk = merk;
    }

    public void cetakDokumen(
        Printer printer,
        String namaFile
    ) {
        System.out.println(
            merk + " mengirim dokumen ke printer..."
        );

        printer.cetak(namaFile);
    }
}
```

### `Printer.java`

```java
package id.ac.polinema.relasiclass.percobaan6;

public class Printer {
    private String merk;

    public Printer(String merk) {
        this.merk = merk;
    }

    public void cetak(String namaFile) {
        System.out.println(
            "[" + merk + "] Mencetak "
            + namaFile + "..."
        );

        System.out.println(
            "[" + merk + "] Selesai."
        );
    }
}
```

### `MainPercobaan6.java`

```java
package id.ac.polinema.relasiclass.percobaan6;

public class MainPercobaan6 {
    public static void main(String[] args) {
        Laptop laptop =
            new Laptop("Thinkpad");

        Printer printer =
            new Printer("Epson L3110");

        laptop.cetakDokumen(
            printer,
            "Laporan.pdf"
        );
    }
}
```

## 8.2 Output

```text
Thinkpad mengirim dokumen ke printer...
[Epson L3110] Mencetak Laporan.pdf...
[Epson L3110] Selesai.
```

## 8.3 Penjelasan

Class `Laptop` tidak menyimpan object `Printer` sebagai atribut.

Object `Printer` hanya digunakan sebagai parameter pada method:

```java
public void cetakDokumen(
    Printer printer,
    String namaFile
)
```

Karena object hanya digunakan sementara dan tidak disimpan sebagai atribut, relasi Laptop → Printer merupakan **Dependency / Uses-A**.

## 8.4 Jawaban Percobaan

### 1. Apakah Laptop memiliki atribut Printer?

Tidak.

`Laptop` hanya memiliki atribut:

```java
private String merk;
```

`Printer` hanya muncul sebagai parameter method `cetakDokumen()`.

### 2. Apakah Laptop menyimpan referensi Printer?

Tidak.

Parameter `printer` hanya digunakan selama pemanggilan method dan tidak disimpan sebagai atribut.

### 3. Mengapa disebut Dependency?

Relasi disebut Dependency karena `Laptop` hanya menggunakan object `Printer` sementara melalui parameter method.

Berbeda dengan Aggregation, object Printer tidak disimpan sebagai atribut Laptop.

### 4. Jika Printer disimpan sebagai atribut

Jika `Printer` menjadi atribut `Laptop` dan diisi melalui constructor atau setter, maka Laptop menyimpan referensi terhadap Printer.

Berdasarkan kriteria jobsheet, relasi tersebut menjadi **Aggregation**.

### 5. Tabel perbandingan

| Relasi | Part disimpan sebagai atribut? | Siapa yang memanggil `new`? |
|---|---|---|
| Aggregation | Ya | Umumnya kode di luar whole, lalu object di-inject |
| Composition | Ya | Whole membuat part sendiri |
| Dependency | Tidak | Kode pengguna membuat object lalu mengirimkannya sebagai parameter |

---

# 9. Tugas Mandiri — Studi Kasus Perpustakaan

## 9.1 Deskripsi

Studi kasus yang dipilih adalah sistem sederhana perpustakaan.

Sebuah `Perpustakaan` mempunyai sebuah `Buku` yang diberikan dari luar, mempunyai `Rak` yang dibuat sendiri, dan mempunyai method `pinjam()` yang menggunakan object `Anggota` sebagai parameter.

Empat class yang digunakan dalam implementasi adalah:

1. `Buku`
2. `Rak`
3. `Anggota`
4. `Perpustakaan`

Class `MainTugasMandiri` digunakan sebagai class untuk menjalankan program dan tidak dihitung sebagai class domain.

## 9.2 Kode Program

### `Buku.java`

```java
package id.ac.polinema.relasiclass.tugas;

public class Buku {
    private String judul;

    public Buku(String judul) {
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }
}
```

### `Rak.java`

```java
package id.ac.polinema.relasiclass.tugas;

public class Rak {
    private String kode;

    public Rak(String kode) {
        this.kode = kode;
    }

    public String getKode() {
        return kode;
    }
}
```

### `Anggota.java`

```java
package id.ac.polinema.relasiclass.tugas;

public class Anggota {
    private String nama;

    public Anggota(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
}
```

### `Perpustakaan.java`

```java
package id.ac.polinema.relasiclass.tugas;

public class Perpustakaan {
    private Buku buku;
    private Rak rak;

    public Perpustakaan(Buku buku) {
        this.buku = buku;
        this.rak = new Rak("RAK-01");
    }

    public void pinjam(Anggota anggota) {
        System.out.println(
            "Peminjaman oleh: " + anggota.getNama()
        );

        System.out.println(
            "Buku: " + buku.getJudul()
        );
    }

    public void info() {
        System.out.println(
            "Buku: " + buku.getJudul()
        );

        System.out.println(
            "Rak: " + rak.getKode()
        );
    }
}
```

### `MainTugasMandiri.java`

```java
package id.ac.polinema.relasiclass.tugas;

public class MainTugasMandiri {
    public static void main(String[] args) {
        Buku buku =
            new Buku("Pemrograman Berbasis Objek");

        Anggota anggota =
            new Anggota("Budi");

        Perpustakaan perpustakaan =
            new Perpustakaan(buku);

        perpustakaan.info();
        perpustakaan.pinjam(anggota);
    }
}
```

## 9.3 Output

```text
Buku: Pemrograman Berbasis Objek
Rak: RAK-01
Peminjaman oleh: Budi
Buku: Pemrograman Berbasis Objek
```

## 9.4 Penjelasan

Studi kasus ini menggabungkan tiga jenis relasi.

### Aggregation — `Perpustakaan` → `Buku`

Object `Buku` dibuat di luar `Perpustakaan`:

```java
Buku buku =
    new Buku("Pemrograman Berbasis Objek");
```

Kemudian object tersebut diberikan ke constructor:

```java
Perpustakaan perpustakaan =
    new Perpustakaan(buku);
```

Di dalam `Perpustakaan`, object tersebut disimpan:

```java
private Buku buku;
```

Karena `Buku` dibuat dari luar dan diberikan kepada `Perpustakaan`, relasinya adalah **Aggregation**.

### Composition — `Perpustakaan` → `Rak`

Object `Rak` dibuat langsung di dalam constructor `Perpustakaan`:

```java
this.rak = new Rak("RAK-01");
```

Karena `Perpustakaan` membuat sendiri object `Rak`, relasinya adalah **Composition**.

Tidak terdapat setter untuk mengganti `Rak`.

### Dependency — `Perpustakaan` → `Anggota`

Object `Anggota` hanya digunakan sebagai parameter:

```java
public void pinjam(Anggota anggota)
```

Tidak terdapat atribut:

```java
private Anggota anggota;
```

Oleh karena itu relasi `Perpustakaan` → `Anggota` merupakan **Dependency**.

## 9.5 Diagram Kelas

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

## 9.6 Jawaban Tugas Percobaan

### 1. Bagaimana menentukan jenis relasi?

Untuk menentukan jenis relasi, dapat dilihat dari bagaimana object class lain digunakan.

Jika object disimpan sebagai atribut, periksa siapa yang membuat object tersebut.

Jika object dibuat dari luar dan diberikan kepada whole, maka relasinya adalah **Aggregation**.

Jika whole membuat sendiri object tersebut, maka relasinya adalah **Composition**.

Jika object hanya digunakan sementara melalui parameter method dan tidak disimpan sebagai atribut, maka relasinya adalah **Dependency**.

### 2. Pertanyaan utama dalam menentukan relasi

Beberapa pertanyaan yang dapat digunakan adalah:

- Apakah object tersebut disimpan sebagai atribut?
- Siapa yang membuat object tersebut?
- Apakah object dapat berdiri sendiri tanpa whole?
- Seberapa kuat kepemilikan whole terhadap part?

Dengan menjawab pertanyaan tersebut, jenis relasi antar class dapat ditentukan berdasarkan struktur kode.

---

# 10. Kesimpulan

Jobsheet 4 menunjukkan bahwa relasi antar class tidak cukup dilihat hanya dari adanya atribut bertipe object.

**Aggregation** ditunjukkan oleh part yang dibuat di luar whole dan diberikan melalui constructor atau setter.

**Composition** ditunjukkan oleh part yang dibuat sendiri oleh whole.

Sedangkan **Dependency** terjadi ketika object hanya digunakan sementara melalui parameter method dan tidak disimpan sebagai atribut.

Percobaan 3 dan 4 juga memperlihatkan pentingnya pengecekan `null`, array of object, serta multiplicity.

Struktur package pada proyek ini dibuat mengikuti deklarasi package Java agar dapat dibuka sebagai satu source tree tanpa error package mismatch di VS Code.
