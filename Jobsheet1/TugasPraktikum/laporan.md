# LAPORAN JOBSHEET 1 – PEMROGRAMAN BERORIENTASI OBJEK

Nama: Muhammad Ferdi Afiyanto  
NIM: 254107020122  
Absen: 16  
Kelas: 2G

---

## 1. Tujuan Praktikum

Setelah melakukan praktikum ini, mahasiswa mampu:

1. Mengidentifikasi objek, atribut, dan method di lingkungan sekitar.
2. Membuat class menggunakan bahasa pemrograman Java.
3. Menerapkan konsep pewarisan (*inheritance*) pada Java.
4. Membuat object dan menjalankan method melalui class Demo.

---

## 2. Pengamatan Objek

Objek yang diamati adalah televisi, radio, lampu, dan jam dinding. Televisi dan radio dipilih sebagai objek yang memiliki konsep pewarisan karena keduanya merupakan perangkat elektronik.

| Objek/Class | Atribut | Method |
|---|---|---|
| `Elektronik` | `merek`, `daya` | `nyalakan()`, `matikan()`, `cetakInformasi()` |
| `Televisi` | `ukuranLayar`, `volume` | `gantiSaluran()`, `aturVolume()`, `cetakInformasi()` |
| `Radio` | `frekuensi`, `menggunakanBaterai` | `cariFrekuensi()`, `putarMusik()`, `cetakInformasi()` |
| `Lampu` | `jenis`, `tingkatKecerahan` | `nyalakan()`, `aturKecerahan()`, `cetakInformasi()` |
| `JamDinding` | `bentuk`, `warna` | `tunjukkanWaktu()`, `gantiBaterai()`, `cetakInformasi()` |

`Televisi` dan `Radio` merupakan class turunan dari `Elektronik`. Atribut `merek` dan `daya`, serta method umum seperti `nyalakan()` dan `cetakInformasi()`, diwarisi dari class induk.

---

## 3. Percobaan – Pembuatan Class dan Inheritance

### 3.1 Kode Class `Elektronik`

```java
public class Elektronik {
    protected String merek;
    protected int daya;

    public Elektronik(String merek, int daya) { this.merek = merek; this.daya = daya; }
    public void nyalakan() { System.out.println("Perangkat elektronik dinyalakan."); }
    public void matikan() { System.out.println("Perangkat elektronik dimatikan."); }
    public void cetakInformasi() { System.out.println("Merek: " + merek + ", Daya: " + daya + " watt"); }
}
```

### 3.2 Kode Class `Televisi`

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
        System.out.println("Ukuran layar: " + ukuranLayar + " inci, Volume: " + volume);
    }
}
```

### 3.3 Kode Class `Radio`

```java
public class Radio extends Elektronik {
    private double frekuensi;
    private boolean menggunakanBaterai;

    public Radio(String merek, int daya, double frekuensi, boolean menggunakanBaterai) {
        super(merek, daya);
        this.frekuensi = frekuensi;
        this.menggunakanBaterai = menggunakanBaterai;
    }

    public void cariFrekuensi(double frekuensiBaru) {
        frekuensi = frekuensiBaru;
        System.out.println("Radio mencari frekuensi " + frekuensi + " MHz.");
    }

    public void putarMusik() {
        System.out.println("Radio memutar musik.");
    }

    @Override
    public void cetakInformasi() {
        System.out.println("Radio");
        super.cetakInformasi();
        System.out.println("Frekuensi: " + frekuensi + " MHz, Menggunakan baterai: " + menggunakanBaterai);
    }
}
```

### 3.4 Kode Class `Lampu`

```java
public class Lampu {
    private String jenis;
    private int tingkatKecerahan;

    public Lampu(String jenis, int tingkatKecerahan) {
        this.jenis = jenis;
        this.tingkatKecerahan = tingkatKecerahan;
    }

    public void nyalakan() { System.out.println("Lampu dinyalakan."); }
    public void aturKecerahan(int tingkatBaru) {
        tingkatKecerahan = tingkatBaru;
        System.out.println("Kecerahan lampu diatur menjadi " + tingkatKecerahan + "%.");
    }
    public void cetakInformasi() {
        System.out.println("Lampu - Jenis: " + jenis + ", Kecerahan: " + tingkatKecerahan + "%");
    }
}
```

### 3.5 Kode Class `JamDinding`

```java
public class JamDinding {
    private String bentuk;
    private String warna;

    public JamDinding(String bentuk, String warna) {
        this.bentuk = bentuk;
        this.warna = warna;
    }

    public void tunjukkanWaktu() { System.out.println("Jam dinding menunjukkan waktu."); }
    public void gantiBaterai() { System.out.println("Baterai jam dinding diganti."); }
    public void cetakInformasi() {
        System.out.println("Jam Dinding - Bentuk: " + bentuk + ", Warna: " + warna);
    }
}
```

### 3.6 Kode Class Demo

```java
public class TugasPraktikumDemo {
    public static void main(String[] args) {
        Televisi televisi = new Televisi("LG", 100, 43, 15);
        Radio radio = new Radio("Polytron", 20, 98.7, true);
        Lampu lampu = new Lampu("LED", 80);
        JamDinding jamDinding = new JamDinding("Bulat", "Hitam");

        televisi.nyalakan(); televisi.gantiSaluran(7); televisi.cetakInformasi();
        radio.nyalakan(); radio.cariFrekuensi(98.7); radio.cetakInformasi();
        lampu.nyalakan(); lampu.aturKecerahan(60); lampu.cetakInformasi();
        jamDinding.tunjukkanWaktu(); jamDinding.gantiBaterai(); jamDinding.cetakInformasi();
    }
}
```

---

## 4. Verifikasi Hasil Percobaan

Program dikompilasi dan dijalankan dengan perintah berikut:

```bash
javac *.java
java TugasPraktikumDemo
```

Hasil keluaran:

```text
Perangkat elektronik dinyalakan.
Televisi berpindah ke saluran 7.
Televisi
Merek: LG, Daya: 100 watt
Ukuran layar: 43 inci, Volume: 15
Perangkat elektronik dinyalakan.
Radio mencari frekuensi 98.7 MHz.
Radio
Merek: Polytron, Daya: 20 watt
Frekuensi: 98.7 MHz, Menggunakan baterai: true
Lampu dinyalakan.
Kecerahan lampu diatur menjadi 60%.
Lampu - Jenis: LED, Kecerahan: 60%
Jam dinding menunjukkan waktu.
Baterai jam dinding diganti.
Jam Dinding - Bentuk: Bulat, Warna: Hitam
```

---

## 5. Analisis

1. Class `Elektronik` digunakan sebagai class induk yang menyimpan atribut dan method umum perangkat elektronik.
2. Class `Televisi` dan `Radio` menggunakan `extends Elektronik`, sehingga dapat menggunakan method `nyalakan()` dan atribut `merek` serta `daya` dari class induk.
3. Method `cetakInformasi()` pada `Televisi` dan `Radio` dioverride agar dapat menampilkan informasi khusus masing-masing objek.
4. Class `Lampu` dan `JamDinding` dibuat sebagai class mandiri karena tidak memiliki hubungan pewarisan dengan objek lain pada pengamatan ini.
5. Class `TugasPraktikumDemo` menginstansiasikan satu objek untuk setiap objek pengamatan dan menerapkan method-methodnya.

---

## 6. Kesimpulan

Pemrograman berorientasi objek memungkinkan objek di sekitar direpresentasikan sebagai class yang memiliki atribut dan method. Konsep inheritance dapat mengurangi pengulangan kode dengan menempatkan karakteristik umum pada class induk `Elektronik`, kemudian mewariskannya kepada class `Televisi` dan `Radio`.
