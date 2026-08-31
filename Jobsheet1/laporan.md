# LAPORAN JOBSHEET 1 – PENGANTAR PEMROGRAMAN BERORIENTASI OBJEK

**Nama:** Muhammad Ferdi Afiyanto  
**NIM:** 254107020122  
**Absen:** 16  
**Kelas:** 2G

---

## 1. Tujuan Praktikum

Setelah melakukan praktikum ini, mahasiswa mampu:

1. Memahami perbedaan paradigma pemrograman berorientasi objek dengan paradigma pemrograman struktural atau prosedural.
2. Memahami konsep dasar Pemrograman Berorientasi Objek (PBO).
3. Membuat class dan object menggunakan bahasa pemrograman Java.
4. Memanggil method yang terdapat di dalam sebuah object.
5. Memahami konsep inheritance atau pewarisan pada Java.

---

## 2. Pendahuluan

### 2.1 Pemrograman Prosedural vs Berorientasi Objek

Pemrograman prosedural memecah program menjadi beberapa sub-program atau fungsi. Sedangkan pada Pemrograman Berorientasi Objek (PBO), program dipecah menjadi object yang membungkus state dan method.

PBO memiliki kelebihan dalam hal fleksibilitas dan modularitas. Apabila terdapat perubahan atau penambahan fitur, perubahan tersebut dapat dilakukan pada bagian tertentu tanpa harus mengganggu keseluruhan program.

Pada PBO dikenal beberapa konsep dasar, yaitu:

- **Object**, yaitu rangkaian dalam program yang terdiri dari state dan behaviour.
- **Class**, yaitu blueprint atau prototype yang digunakan sebagai dasar pembuatan object.
- **Enkapsulasi**, yaitu konsep penyembunyian informasi atau kompleksitas internal object.
- **Inheritance**, yaitu mekanisme pewarisan sifat dan method dari sebuah class induk kepada class turunannya.
- **Polimorfisme**, yaitu kemampuan sebuah object untuk memiliki bentuk atau implementasi yang berbeda.

Pada Jobsheet 1, konsep tersebut diperkenalkan melalui contoh object sepeda menggunakan class `Bike`, kemudian dilanjutkan dengan inheritance menggunakan class `RoadBike`.

---

# 3. Percobaan

## 3.1 Percobaan 1 – Membuat Class, Object, dan Memanggil Method

Percobaan pertama bertujuan untuk mendemonstrasikan proses pembuatan class, pembuatan object, dan pemanggilan method yang terdapat di dalam class tersebut.

### 3.1.1 Langkah-langkah Percobaan

1. Membuka NetBeans atau editor Java.
2. Membuat project dengan nama `BikeDemo`.
3. Membuat class `Bike`.
4. Menuliskan atribut dan method pada class `Bike`.
5. Membuat class `BikeDemo` sebagai class utama.
6. Membuat object dari class `Bike` menggunakan keyword `new`.
7. Memanggil method yang dimiliki oleh object `Bike`.
8. Menjalankan program dan mencocokkan hasilnya.

Urutan tersebut mengikuti langkah Percobaan 1 pada Jobsheet 1.

---

### 3.1.2 Kode Class `Bike`

```java
public class Bike {
    private String brand;
    private int speed;
    private int gear = 1;
    private final int[] GEAR_SPEEDS_LIMITS = {5, 10, 25, 30, 40, 60};

    public void setBrand(String brandName) {
        brand = brandName;
    }

    public void gearChange(int gearValue) {
        if (gearValue < 1 || gearValue > 6) {
            System.out.println("Invalid gear value. Please select a gear between 1 and 6.");
        } else {
            gear = gearValue;
        }
    }

    public int speedAcceleration(int increment) {
        speed += increment;

        if (speed > GEAR_SPEEDS_LIMITS[gear - 1]) {
            speed = GEAR_SPEEDS_LIMITS[gear - 1];
        }

        return speed;
    }

    public int speedDeceleration(int decrement) {
        speed -= decrement;

        if (speed < 0) {
            speed = 0;
        }

        return speed;
    }

    public void printInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Current Speed: " + speed + " km/h");
        System.out.println("Current Gear: " + gear);
    }
}
```

Class `Bike` memiliki atribut `brand`, `speed`, dan `gear`. Selain itu, terdapat beberapa method yang digunakan untuk mengubah merek, mengganti gigi, menambah kecepatan, mengurangi kecepatan, dan mencetak informasi object.

---

### 3.1.3 Kode Class `BikeDemo`

```java
public class BikeDemo {
    public static void main(String[] args) {
        Bike mountainBike1 = new Bike();
        Bike mountainBike2 = new Bike();

        mountainBike1.setBrand("Trek");
        mountainBike1.speedAcceleration(10);
        mountainBike1.gearChange(2);
        mountainBike1.printInfo();

        mountainBike2.setBrand("Giant");
        mountainBike2.speedAcceleration(20);
        mountainBike2.gearChange(3);
        mountainBike2.printInfo();
    }
}
```

Pada class `BikeDemo`, dibuat dua object yaitu `mountainBike1` dan `mountainBike2`. Kedua object tersebut dibuat berdasarkan class `Bike`, tetapi masing-masing memiliki nilai dan keadaan yang dapat berbeda.

---

### 3.1.4 Verifikasi Hasil Percobaan 1

Program menjalankan dua object sepeda dengan merek yang berbeda. Object pertama menggunakan merek `Trek`, sedangkan object kedua menggunakan merek `Giant`.

Method `speedAcceleration()` digunakan untuk menambah kecepatan, method `gearChange()` digunakan untuk mengubah posisi gigi, dan method `printInfo()` digunakan untuk menampilkan informasi object.

Hasil program menunjukkan informasi berupa:

```text
Brand: Trek
Current Speed: 10 km/h
Current Gear: 2
Brand: Giant
Current Speed: 20 km/h
Current Gear: 3
```

---

## 3.2 Percobaan 2 – Inheritance pada Class `RoadBike`

Percobaan kedua membahas salah satu fitur penting dalam PBO, yaitu **inheritance** atau pewarisan.

Pada percobaan ini dibuat class `RoadBike` yang merupakan turunan dari class `Bike`. Dengan inheritance, `RoadBike` dapat menggunakan karakteristik dan method yang dimiliki oleh `Bike` tanpa harus membuat ulang seluruh kode dari awal.

Jobsheet menjelaskan bahwa `RoadBike` pada dasarnya memiliki kemampuan yang sama dengan `Bike`, tetapi dapat mempunyai fitur tambahan yang khusus untuk sepeda balap.

### 3.2.1 Langkah-langkah Percobaan

1. Masih menggunakan project `BikeDemo`.
2. Membuat class `RoadBike`.
3. Menambahkan `extends Bike` pada deklarasi class.
4. Melengkapi class `RoadBike` dengan atribut dan method tambahan.
5. Membuat object `RoadBike` pada class utama.
6. Memanggil method yang diwariskan dari class `Bike`.
7. Memanggil method tambahan milik `RoadBike`.
8. Menjalankan program dan mencocokkan hasilnya.

Urutan tersebut mengikuti Percobaan 2 pada Jobsheet 1.

---

### 3.2.2 Implementasi Inheritance

Konsep inheritance pada percobaan ini ditunjukkan melalui deklarasi:

```java
public class RoadBike extends Bike {
    // atribut dan method tambahan
}
```

Keyword `extends` menunjukkan bahwa `RoadBike` merupakan subclass dari `Bike`.

Dengan demikian, `RoadBike` dapat menggunakan kemampuan yang diwariskan dari `Bike`, seperti pengaturan gear, perubahan kecepatan, dan method lain yang tersedia pada class induknya.

---

### 3.2.3 Konsep Inheritance

Hubungan class pada percobaan dapat digambarkan sebagai berikut:

```text
        Bike
         │
         │ extends
         ▼
      RoadBike
```

`Bike` berperan sebagai class induk, sedangkan `RoadBike` berperan sebagai class turunan.

Keuntungan pendekatan ini adalah kode yang sudah terdapat pada `Bike` tidak perlu ditulis ulang pada `RoadBike`. Class turunan cukup menambahkan karakteristik atau behaviour yang belum dimiliki oleh class induknya.

---

### 3.2.4 Verifikasi Hasil Percobaan 2

Hasil percobaan menunjukkan bahwa object `RoadBike` dapat menggunakan kemampuan yang diwariskan dari class `Bike` sekaligus memiliki fitur tambahan yang khusus untuk `RoadBike`.

Hal tersebut menunjukkan penerapan konsep **inheritance** dalam pemrograman berorientasi objek.

---

# 4. Tugas Praktikum

## 4.1 Pengamatan Object di Sekitar

Berdasarkan tugas praktikum pada Jobsheet 1, dilakukan pengamatan terhadap empat object di sekitar. Object yang digunakan dalam implementasi adalah:

1. Televisi
2. Radio
3. Lampu
4. Jam Dinding

Dari keempat object tersebut, `Televisi` dan `Radio` digunakan untuk menerapkan konsep inheritance melalui class induk `Elektronik`.

Struktur inheritance yang digunakan adalah:

```text
             Elektronik
              /       \
             /         \
        Televisi       Radio
```

Sedangkan `Lampu` dan `JamDinding` dibuat sebagai class tersendiri.

---

## 4.2 Class `Elektronik`

Class `Elektronik` digunakan sebagai class induk untuk object elektronik yang memiliki karakteristik umum berupa merek dan daya.

### Atribut

- `merek`
- `daya`

### Method

- `nyalakan()`
- `matikan()`
- `cetakInformasi()`

### Kode

```java
public class Elektronik {
    protected String merek;
    protected int daya;

    public Elektronik(String merek, int daya) {
        this.merek = merek;
        this.daya = daya;
    }

    public void nyalakan() {
        System.out.println("Perangkat elektronik dinyalakan.");
    }

    public void matikan() {
        System.out.println("Perangkat elektronik dimatikan.");
    }

    public void cetakInformasi() {
        System.out.println("Merek: " + merek + ", Daya: " + daya + " watt");
    }
}
```

Class `Elektronik` memiliki dua atribut berupa `merek` dan `daya`. Kedua atribut tersebut menggunakan modifier `protected` sehingga dapat digunakan oleh class turunannya. 

---

## 4.3 Class `Televisi`

`Televisi` merupakan subclass dari `Elektronik`.

### Atribut

- `ukuranLayar`
- `volume`

### Method

- `gantiSaluran()`
- `aturVolume()`
- `cetakInformasi()`

### Kode

```java
public class Televisi extends Elektronik {
    private int ukuranLayar;
    private int volume;

    public Televisi(String merek, int daya, int ukuranLayar, int volume) {
        super(merek, daya);
        this.ukuranLayar = ukuranLayar;
        this.volume = volume;
    }

    public void gantiSaluran(int saluran) {
        System.out.println("Televisi berpindah ke saluran " + saluran + ".");
    }

    public void aturVolume(int volumeBaru) {
        volume = volumeBaru;
        System.out.println("Volume televisi diatur menjadi " + volume + ".");
    }

    @Override
    public void cetakInformasi() {
        System.out.println("Televisi");
        super.cetakInformasi();
        System.out.println(
            "Ukuran layar: " + ukuranLayar +
            " inci, Volume: " + volume
        );
    }
}
```

`Televisi` mewarisi atribut dan method dari `Elektronik`. Selain itu, `Televisi` memiliki atribut tambahan `ukuranLayar` dan `volume`. Method `cetakInformasi()` juga dioverride untuk menampilkan informasi khusus televisi. 

---

## 4.4 Class `Radio`

`Radio` juga merupakan subclass dari `Elektronik`.

### Atribut

- `frekuensi`
- `menggunakanBaterai`

### Method

- `cariFrekuensi()`
- `putarMusik()`
- `cetakInformasi()`

### Kode

```java
public class Radio extends Elektronik {
    private double frekuensi;
    private boolean menggunakanBaterai;

    public Radio(
        String merek,
        int daya,
        double frekuensi,
        boolean menggunakanBaterai
    ) {
        super(merek, daya);
        this.frekuensi = frekuensi;
        this.menggunakanBaterai = menggunakanBaterai;
    }

    public void cariFrekuensi(double frekuensiBaru) {
        frekuensi = frekuensiBaru;
        System.out.println(
            "Radio mencari frekuensi " + frekuensi + " MHz."
        );
    }

    public void putarMusik() {
        System.out.println("Radio memutar musik.");
    }

    @Override
    public void cetakInformasi() {
        System.out.println("Radio");
        super.cetakInformasi();
        System.out.println(
            "Frekuensi: " + frekuensi +
            " MHz, Menggunakan baterai: " + menggunakanBaterai
        );
    }
}
```

`Radio` menggunakan `super()` untuk memanggil constructor class `Elektronik`. Radio juga memiliki atribut tambahan berupa `frekuensi` dan `menggunakanBaterai`. 

---

## 4.5 Class `Lampu`

`Lampu` merupakan class yang dibuat secara mandiri dan tidak diturunkan dari class lain.

### Atribut

- `jenis`
- `tingkatKecerahan`

### Method

- `nyalakan()`
- `aturKecerahan()`
- `cetakInformasi()`

### Kode

```java
public class Lampu {
    private String jenis;
    private int tingkatKecerahan;

    public Lampu(String jenis, int tingkatKecerahan) {
        this.jenis = jenis;
        this.tingkatKecerahan = tingkatKecerahan;
    }

    public void nyalakan() {
        System.out.println("Lampu dinyalakan.");
    }

    public void aturKecerahan(int tingkatBaru) {
        tingkatKecerahan = tingkatBaru;
        System.out.println(
            "Kecerahan lampu diatur menjadi " +
            tingkatKecerahan + "%."
        );
    }

    public void cetakInformasi() {
        System.out.println(
            "Lampu - Jenis: " + jenis +
            ", Kecerahan: " + tingkatKecerahan + "%"
        );
    }
}
```

Class `Lampu` memiliki dua atribut dan tiga method yang digunakan untuk mengatur behaviour object lampu. 

---

## 4.6 Class `JamDinding`

`JamDinding` merupakan class mandiri yang digunakan untuk memodelkan object jam dinding.

### Atribut

- `bentuk`
- `warna`

### Method

- `tunjukkanWaktu()`
- `gantiBaterai()`
- `cetakInformasi()`

### Kode

```java
public class JamDinding {
    private String bentuk;
    private String warna;

    public JamDinding(String bentuk, String warna) {
        this.bentuk = bentuk;
        this.warna = warna;
    }

    public void tunjukkanWaktu() {
        System.out.println("Jam dinding menunjukkan waktu.");
    }

    public void gantiBaterai() {
        System.out.println("Baterai jam dinding diganti.");
    }

    public void cetakInformasi() {
        System.out.println(
            "Jam Dinding - Bentuk: " +
            bentuk + ", Warna: " + warna
        );
    }
}
```

Class `JamDinding` menggunakan dua atribut dan tiga method sesuai dengan kebutuhan tugas praktikum. 

---

## 4.7 Class `TugasPraktikumDemo`

Class `TugasPraktikumDemo` digunakan sebagai class utama untuk membuat satu object dari setiap class dan menjalankan method-method yang tersedia.

### Kode

```java
public class TugasPraktikumDemo {
    public static void main(String[] args) {
        Televisi televisi =
            new Televisi("LG", 100, 43, 15);

        Radio radio =
            new Radio("Polytron", 20, 98.7, true);

        Lampu lampu =
            new Lampu("LED", 80);

        JamDinding jamDinding =
            new JamDinding("Bulat", "Hitam");

        televisi.nyalakan();
        televisi.gantiSaluran(7);
        televisi.cetakInformasi();

        radio.nyalakan();
        radio.cariFrekuensi(98.7);
        radio.cetakInformasi();

        lampu.nyalakan();
        lampu.aturKecerahan(60);
        lampu.cetakInformasi();

        jamDinding.tunjukkanWaktu();
        jamDinding.gantiBaterai();
        jamDinding.cetakInformasi();
    }
}
```

Pada class `TugasPraktikumDemo`, dibuat satu object dari masing-masing class, yaitu:

- `televisi` dari class `Televisi`
- `radio` dari class `Radio`
- `lampu` dari class `Lampu`
- `jamDinding` dari class `JamDinding`

Setiap object kemudian menjalankan method yang dimilikinya. Implementasi ini sesuai dengan kode yang terdapat pada repository. 

---

## 4.8 Penerapan Konsep PBO

Berdasarkan tugas praktikum yang telah dibuat, konsep PBO yang diterapkan adalah:

### 1. Class

Class digunakan sebagai blueprint untuk membentuk object. Contohnya adalah `Elektronik`, `Televisi`, `Radio`, `Lampu`, dan `JamDinding`.

### 2. Object

Object merupakan instance dari sebuah class. Pada `TugasPraktikumDemo`, dibuat object `televisi`, `radio`, `lampu`, dan `jamDinding`.

### 3. Encapsulation

Atribut pada beberapa class dibuat menggunakan modifier `private`, sehingga data tidak dapat diakses secara langsung dari luar class dan digunakan melalui method yang tersedia.

### 4. Inheritance

Inheritance diterapkan pada class `Televisi` dan `Radio` yang menggunakan:

```java
extends Elektronik
```

Dengan demikian, kedua class tersebut mendapatkan karakteristik umum dari class `Elektronik`.

### 5. Method Overriding

Method `cetakInformasi()` pada `Televisi` dan `Radio` menggunakan `@Override`. Method tersebut menggantikan implementasi `cetakInformasi()` dari class `Elektronik` dengan implementasi yang lebih spesifik.

---

# 5. Jawaban Pertanyaan

## 5.1 Jelaskan perbedaan antara object dengan class!

**Class** adalah blueprint atau rancangan yang mendefinisikan atribut dan method yang dimiliki oleh suatu object.

Sedangkan **object** adalah instance atau hasil nyata yang dibuat berdasarkan sebuah class.

Contohnya pada percobaan:

```java
Bike mountainBike1 = new Bike();
```

`Bike` merupakan class, sedangkan `mountainBike1` merupakan object dari class `Bike`.

---

## 5.2 Jelaskan alasan `gear` dan `brand` dapat menjadi atribut dari object `Bike`!

`gear` dan `brand` dapat menjadi atribut karena keduanya merupakan karakteristik atau state yang menggambarkan sebuah sepeda.

Setiap sepeda dapat mempunyai merek yang berbeda dan posisi gear yang berbeda. Oleh karena itu, kedua data tersebut sesuai untuk menjadi atribut dari class `Bike`.

Jobsheet menjelaskan bahwa state merupakan ciri-ciri atau atribut dari object, sedangkan pada contoh sepeda state tersebut antara lain `brand`, `speed`, dan `gear`.

---

## 5.3 Sebutkan salah satu kelebihan utama dari pemrograman berorientasi objek dibandingkan dengan pemrograman prosedural!

Salah satu kelebihan utama PBO adalah **program lebih modular dan kode dapat digunakan kembali**.

Melalui konsep class dan inheritance, bagian program yang sudah dibuat dapat digunakan kembali tanpa harus menulis kode yang sama dari awal. Contohnya `RoadBike` dapat mewarisi kemampuan dari `Bike`.

---

## 5.4 Apakah diperbolehkan melakukan pendefinisian dua buah atribut dalam satu baris kode seperti `public String nama, alamat;`?

Ya, diperbolehkan selama kedua atribut tersebut memiliki tipe data yang sama.

Contohnya:

```java
public String nama, alamat;
```

Penulisan tersebut setara dengan:

```java
public String nama;
public String alamat;
```

---

## 5.5 Pada class `RoadBike`, jelaskan alasan atribut `brand`, `speed`, dan `gear` tidak lagi ditulis di dalam class tersebut!

Atribut `brand`, `speed`, dan `gear` tidak perlu ditulis ulang karena `RoadBike` merupakan turunan dari `Bike`.

Melalui inheritance, `RoadBike` dapat mewarisi karakteristik dan behaviour yang sudah dimiliki oleh `Bike`. Oleh karena itu, penulisan ulang atribut tersebut akan menyebabkan duplikasi kode.

`RoadBike` cukup menambahkan atribut atau method yang bersifat khusus untuk sepeda balap.

---

# 6. Kesimpulan

Berdasarkan praktikum Jobsheet 1, Pemrograman Berorientasi Objek menggunakan konsep class dan object untuk memodelkan objek ke dalam program. Class berfungsi sebagai blueprint, sedangkan object merupakan instance yang dibuat berdasarkan class tersebut.

Pada Percobaan 1 telah diterapkan pembuatan class `Bike`, pembuatan object, serta pemanggilan method yang terdapat pada object.

Pada Percobaan 2 diperkenalkan konsep inheritance melalui class `RoadBike` yang merupakan turunan dari class `Bike`. Dengan inheritance, class turunan dapat menggunakan kembali kemampuan yang telah tersedia pada class induknya tanpa perlu membuat kode dari awal.

Pada tugas praktikum, konsep tersebut diterapkan pada beberapa object di sekitar, yaitu `Televisi`, `Radio`, `Lampu`, dan `JamDinding`. Class `Televisi` dan `Radio` menggunakan `Elektronik` sebagai class induk sehingga dapat menerapkan konsep inheritance. Selain itu, implementasi juga menggunakan class, object, encapsulation, inheritance, dan method overriding.

Dengan demikian, praktikum ini memberikan dasar pemahaman mengenai bagaimana konsep Pemrograman Berorientasi Objek diterapkan dalam program Java.