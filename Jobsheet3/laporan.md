# LAPORAN JOBSHEET 3 – ENKAPSULASI PBO

**Nama:** Muhammad Ferdi Afiyanto  
**NIM:** 254107020122  
**Absen:** 16  
**Kelas:** 2G

---

## 1. Tujuan Praktikum

Setelah melakukan praktikum ini, mahasiswa mampu:

1. Memahami konsep enkapsulasi (*information hiding*) pada PBO.
2. Menggunakan *access modifier* (`private`, `protected`, `public`, default) untuk mengontrol akses atribut dan method.
3. Membuat dan menggunakan *getter* serta *setter*.
4. Membuat konstruktor dengan parameter untuk inisialisasi object.
5. Memahami notasi pada UML Class Diagram.

---

## 2. Pendahuluan

### 2.1 Enkapsulasi

Enkapsulasi disebut juga *information-hiding*. Dalam berinteraksi dengan objek, seringkali kita tidak perlu mengetahui kompleksitas yang ada di dalamnya. Contohnya, ketika mengganti gear pada sepeda, kita hanya menekan tuas gear tanpa perlu mengetahui bagaimana gear berpindah secara teknis.

Konsep ini diterapkan dengan cara:
1. Menyembunyikan atribut internal dari pengguna (*class* lain) menggunakan modifier `private`.
2. Menyediakan method khusus (*getter* dan *setter*) untuk mengakses dan memanipulasi atribut.

### 2.2 Access Modifier

Terdapat 4 *access modifier* di Java:

| Modifier | Within Class | Within Package | Outside Package (Subclass) | Outside Package |
|---|---|---|---|---|
| `private` | ✓ | ✗ | ✗ | ✗ |
| default | ✓ | ✓ | ✗ | ✗ |
| `protected` | ✓ | ✓ | ✓ | ✗ |
| `public` | ✓ | ✓ | ✓ | ✓ |

Notasi pada UML Class Diagram:
- `+` → public
- `#` → protected
- `-` → private
- `~` → default

### 2.3 Getter dan Setter

- **Getter** adalah method *public* yang memiliki tipe data return, berfungsi untuk mendapatkan nilai dari atribut `private`.
- **Setter** adalah method *public* yang tidak memiliki tipe data return, berfungsi untuk memanipulasi nilai dari atribut `private`.

### 2.4 Konstruktor

Konstruktor adalah blok kode yang dijalankan ketika object dibuat menggunakan keyword `new`. Aturan pembuatan konstruktor:
1. Nama konstruktor harus sama dengan nama class.
2. Konstruktor tidak memiliki tipe data return.
3. Konstruktor tidak boleh menggunakan modifier `abstract`, `static`, `final`, dan `synchronized`.

---

# 3. Percobaan

## 3.1 Percobaan 1 – Enkapsulasi

Percobaan pertama mendemonstrasikan masalah ketika atribut tidak dilindungi dengan *access modifier*. Atribut `kecepatan` dan `kontakOn` pada class `Motor` dapat diakses dan diubah secara langsung dari luar class, sehingga kecepatan bisa berubah tanpa menyalakan mesin terlebih dahulu.

### UML Class Diagram

```text
+-------------------+
|      Motor        |
+-------------------+
| - kecepatan       |
| - kontakOn        |
+-------------------+
| + printStatus()   |
+-------------------+
```

---

## 3.2 Percobaan 2 – Access Modifier

Pada percobaan ini, atribut `kecepatan` dan `kontakOn` diubah menjadi `private`. Ditambahkan method `nyalakanMesin()`, `matikanMesin()`, `tambahKecepatan()`, dan `kurangiKecepatan()` untuk mengontrol akses terhadap atribut.

### UML Class Diagram

```text
+-----------------------------------+
|             Motor                 |
+-----------------------------------+
| - kecepatan: int                  |
| - kontakOn: boolean               |
+-----------------------------------+
| + nyalakanMesin(): void           |
| + matikanMesin(): void            |
| + tambahKecepatan(tambah): void   |
| + kurangiKecepatan(kurang): void  |
| + getKecepatan(): int             |
| + isKontakOn(): boolean           |
| + printStatus(): void             |
+-----------------------------------+
```

### Kode Class `Motor`

```java
package id.ac.polinema;

public class Motor {
    private int kecepatan;
    private boolean kontakOn;

    public void nyalakanMesin() {
        kontakOn = true;
        System.out.println("Mesin dinyalakan.");
    }

    public void matikanMesin() {
        kontakOn = false;
        kecepatan = 0;
        System.out.println("Mesin dimatikan.");
    }

    public void tambahKecepatan(int tambah) {
        if (!kontakOn) {
            System.out.println("Kecepatan tidak bisa bertambah karena Mesin Off!");
            return;
        }
        kecepatan += tambah;
        if (kecepatan > 100) {
            kecepatan = 100;
        }
    }

    public void kurangiKecepatan(int kurang) {
        kecepatan -= kurang;
        if (kecepatan < 0) {
            kecepatan = 0;
        }
    }

    public int getKecepatan() {
        return kecepatan;
    }

    public boolean isKontakOn() {
        return kontakOn;
    }

    public void printStatus() {
        System.out.println("Status Motor:");
        System.out.println("Kontak : " + (kontakOn ? "ON" : "OFF"));
        System.out.println("Kecepatan : " + kecepatan + " km/h");
    }
}
```

### Kode Class `MotorDemo`

```java
package id.ac.polinema;

public class MotorDemo {
    public static void main(String[] args) {
        Motor motor = new Motor();

        motor.printStatus();

        motor.tambahKecepatan(50);
        System.out.println();

        motor.nyalakanMesin();
        motor.tambahKecepatan(30);
        motor.tambahKecepatan(20);
        motor.kurangiKecepatan(10);
        System.out.println();

        motor.printStatus();
        motor.matikanMesin();
        motor.printStatus();
    }
}
```

### Verifikasi Hasil Percobaan 2

```text
Status Motor:
Kontak : OFF
Kecepatan : 0 km/h
Kecepatan tidak bisa bertambah karena Mesin Off!

Mesin dinyalakan.

Status Motor:
Kontak : ON
Kecepatan : 40 km/h
Mesin dimatikan.
Status Motor:
Kontak : OFF
Kecepatan : 0 km/h
```

Ketika kontak masih OFF, percobaan menambah kecepatan menghasilkan peringatan. Kecepatan hanya dapat ditambah setelah mesin dinyalakan.

---

## 3.3 Percobaan 3 – Getter dan Setter

Pada percobaan ini dibuat class `Anggota` untuk sistem informasi koperasi. Atribut `nama`, `alamat`, dan `simpanan` dibuat `private`. Atribut `simpanan` tidak memiliki setter karena hanya berubah melalui method `setor()` dan `pinjam()`.

### UML Class Diagram

```text
+-----------------------------------+
|            Anggota                |
+-----------------------------------+
| - nama: String                    |
| - alamat: String                  |
| - simpanan: double                |
+-----------------------------------+
| + Anggota(nama, alamat)           |
| + getNama(): String               |
| + setNama(nama): void             |
| + getAlamat(): String             |
| + setAlamat(alamat): void         |
| + getSimpanan(): double           |
| + setor(jumlah): void             |
| + pinjam(jumlah): void            |
+-----------------------------------+
```

### Kode Class `Anggota`

```java
package id.ac.polinema;

public class Anggota {
    private String nama;
    private String alamat;
    private double simpanan;

    public Anggota(String nama, String alamat) {
        this.nama = nama;
        this.alamat = alamat;
        this.simpanan = 0;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public double getSimpanan() {
        return simpanan;
    }

    public void setor(double jumlah) {
        if (jumlah > 0) {
            simpanan += jumlah;
            System.out.println("Setor berhasil. Simpanan sekarang: Rp " + simpanan);
        } else {
            System.out.println("Jumlah setor harus lebih dari 0!");
        }
    }

    public void pinjam(double jumlah) {
        if (jumlah > simpanan) {
            System.out.println("Pinjaman melebihi simpanan!");
        } else if (jumlah > 0) {
            simpanan -= jumlah;
            System.out.println("Pinjam berhasil. Simpanan sekarang: Rp " + simpanan);
        } else {
            System.out.println("Jumlah pinjaman harus lebih dari 0!");
        }
    }
}
```

### Kode Class `KoperasiDemo`

```java
package id.ac.polinema;

public class KoperasiDemo {
    public static void main(String[] args) {
        Anggota anggota1 = new Anggota("Budi", "Jl. Merdeka No. 10");
        Anggota anggota2 = new Anggota("Siti", "Jl. Sudirman No. 5");

        System.out.println("=== Anggota 1 ===");
        System.out.println("Nama   : " + anggota1.getNama());
        System.out.println("Alamat : " + anggota1.getAlamat());
        anggota1.setor(500000);
        anggota1.pinjam(200000);
        System.out.println("Simpanan: Rp " + anggota1.getSimpanan());

        System.out.println();

        System.out.println("=== Anggota 2 ===");
        System.out.println("Nama   : " + anggota2.getNama());
        System.out.println("Alamat : " + anggota2.getAlamat());
        anggota2.setor(300000);
        anggota2.pinjam(100000);
        System.out.println("Simpanan: Rp " + anggota2.getSimpanan());
    }
}
```

### Verifikasi Hasil Percobaan 3

```text
=== Anggota 1 ===
Nama   : Budi
Alamat : Jl. Merdeka No. 10
Setor berhasil. Simpanan sekarang: Rp 500000.0
Pinjam berhasil. Simpanan sekarang: Rp 300000.0
Simpanan: Rp 300000.0

=== Anggota 2 ===
Nama   : Siti
Alamat : Jl. Sudirman No. 5
Setor berhasil. Simpanan sekarang: Rp 300000.0
Pinjam berhasil. Simpanan sekarang: Rp 200000.0
Simpanan: Rp 200000.0
```

---

## 3.4 Percobaan 4 – Konstruktor dan Instansiasi

Pada percobaan ini, konstruktor dengan parameter ditambahkan pada class `Anggota` sehingga atribut `nama` dan `alamat` langsung terisi ketika object dibuat. Atribut `simpanan` diinisialisasi dengan nilai default `0` karena tidak memerlukan nilai spesifik dari luar.

### Kode `EncapTest`

```java
package id.ac.polinema;

public class EncapTest {
    private String name;
    private String id;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
        if (newAge > 30) {
            age = 30;
        } else {
            age = newAge;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getId() {
        return id;
    }

    public void setId(String newId) {
        id = newId;
    }
}
```

### Kode `EncapTestDemo`

```java
package id.ac.polinema;

public class EncapTestDemo {
    public static void main(String[] args) {
        EncapTest encap = new EncapTest();

        encap.setName("Ricky");
        encap.setId("1234");
        encap.setAge(35);

        System.out.println("Name: " + encap.getName());
        System.out.println("Id  : " + encap.getId());
        System.out.println("Age : " + encap.getAge());
    }
}
```

### Verifikasi Hasil

```text
Name: Ricky
Id  : 1234
Age : 30
```

Ketika `setAge(35)` dipanggil, nilai yang tersimpan tetap `30` karena setter membatasi nilai maksimal menjadi 30.

---

# 4. Tugas Praktikum

## 4.1 Soal 2 – EncapTest

### Pertanyaan
Pada class `EncapTest`, saat mengeset `age` dengan nilai 35, namun pada saat ditampilkan ke layar nilainya 30. Jelaskan mengapa.

### Jawaban
Hal ini terjadi karena pada method `setAge()` terdapat validasi yang membatasi nilai `age` maksimal 30. Ketika parameter yang dikirimkan lebih dari 30, maka nilai `age` akan diset menjadi 30.

```java
public void setAge(int newAge) {
    if (newAge > 30) {
        age = 30;
    } else {
        age = newAge;
    }
}
```

---

## 4.2 Soal 3 – Batas Usia 18-30

### Pertanyaan
Ubah program agar atribut `age` dapat diberi nilai maksimal 30 dan minimal 18.

### Jawaban

```java
public void setAge(int newAge) {
    if (newAge < 18) {
        age = 18;
    } else if (newAge > 30) {
        age = 30;
    } else {
        age = newAge;
    }
}
```

---

## 4.3 Soal 4 – Class Kontainer

### Pertanyaan
Buatlah class `Kontainer` pada sistem manajemen pergudangan kargo ekspedisi dengan atribut `nomorResi`, `namaPemilik`, `kapasitasMaksimal`, dan `beratMuatanSaatIni`.

### Kode Class `Kontainer`

```java
package id.ac.polinema;

public class Kontainer {
    private String nomorResi;
    private String namaPemilik;
    private double kapasitasMaksimal;
    private double beratMuatanSaatIni;

    public Kontainer(String nomorResi, String namaPemilik, double kapasitasMaksimal) {
        this.nomorResi = nomorResi;
        this.namaPemilik = namaPemilik;
        this.kapasitasMaksimal = kapasitasMaksimal;
        this.beratMuatanSaatIni = 0;
    }

    public String getNomorResi() {
        return nomorResi;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public double getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }

    public double getBeratMuatanSaatIni() {
        return beratMuatanSaatIni;
    }

    public void tambahMuatan(double berat) {
        if (berat < 0) {
            System.out.println("Berat muatan tidak boleh negatif!");
            return;
        }
        if (beratMuatanSaatIni + berat > kapasitasMaksimal) {
            System.out.println("Muatan melebihi kapasitas maksimal!");
            return;
        }
        beratMuatanSaatIni += berat;
        System.out.println("Muatan ditambahkan. Berat sekarang: " + beratMuatanSaatIni + " kg");
    }

    public void turunkanMuatan(double berat) {
        if (berat < 0) {
            System.out.println("Berat muatan tidak boleh negatif!");
            return;
        }
        if (berat > 0.5 * beratMuatanSaatIni) {
            System.out.println("Maaf, demi keselamatan, pembongkaran muatan satu kali jalan tidak boleh melebihi 50% dari muatan saat ini!");
            return;
        }
        if (berat > beratMuatanSaatIni) {
            System.out.println("Berat muatan yang diturunkan melebihi muatan saat ini!");
            return;
        }
        beratMuatanSaatIni -= berat;
        System.out.println("Muatan diturunkan. Berat sekarang: " + beratMuatanSaatIni + " kg");
    }

    public void cetakInfo() {
        System.out.println("Nomor Resi        : " + nomorResi);
        System.out.println("Nama Pemilik      : " + namaPemilik);
        System.out.println("Kapasitas Maksimal: " + kapasitasMaksimal + " kg");
        System.out.println("Muatan Saat Ini   : " + beratMuatanSaatIni + " kg");
    }
}
```

### Kode Class `TestLogistik`

```java
package id.ac.polinema;

import java.util.Scanner;

public class TestLogistik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nomor resi   : ");
        String nomorResi = scanner.nextLine();
        System.out.print("Masukkan nama pemilik : ");
        String namaPemilik = scanner.nextLine();
        System.out.print("Masukkan kapasitas maks (kg): ");
        double kapasitas = scanner.nextDouble();

        Kontainer kontainer = new Kontainer(nomorResi, namaPemilik, kapasitas);
        System.out.println();
        kontainer.cetakInfo();
        System.out.println();

        System.out.print("Masukkan berat muatan yang ditambahkan (kg): ");
        double tambah = scanner.nextDouble();
        kontainer.tambahMuatan(tambah);
        System.out.println();
        kontainer.cetakInfo();
        System.out.println();

        System.out.print("Masukkan berat muatan yang diturunkan (kg): ");
        double turun = scanner.nextDouble();
        kontainer.turunkanMuatan(turun);
        System.out.println();
        kontainer.cetakInfo();

        scanner.close();
    }
}
```

---

## 4.4 Soal 5 – Batas Turunkan Muatan 50%

### Pertanyaan
Modifikasi method `turunkanMuatan()` agar nominal berat muatan yang dibongkar/diturunkan dalam satu kali pemanggilan maksimal hanya boleh sebesar 50% dari total berat muatan saat ini.

### Jawaban

```java
public void turunkanMuatan(double berat) {
    if (berat < 0) {
        System.out.println("Berat muatan tidak boleh negatif!");
        return;
    }
    if (berat > 0.5 * beratMuatanSaatIni) {
        System.out.println("Maaf, demi keselamatan, pembongkaran muatan satu kali jalan tidak boleh melebihi 50% dari muatan saat ini!");
        return;
    }
    if (berat > beratMuatanSaatIni) {
        System.out.println("Berat muatan yang diturunkan melebihi muatan saat ini!");
        return;
    }
    beratMuatanSaatIni -= berat;
    System.out.println("Muatan diturunkan. Berat sekarang: " + beratMuatanSaatIni + " kg");
}
```

Logika validasi yang diterapkan:
1. Jika berat negatif → ditolak.
2. Jika berat melebihi 50% muatan saat ini → ditolak dengan peringatan keselamatan.
3. Jika berat melebihi muatan saat ini → ditolak.
4. Jika lolos semua validasi → muatan dikurangi.

---

## 4.5 Soal 6 – Input Dinamis dari Pengguna

### Pertanyaan
Modifikasi kelas `Main TestLogistik` agar parameter jumlah berat barang yang dimasukkan maupun yang dibongkar dapat menerima input nilai dinamis dari pengguna secara interaktif melalui terminal menggunakan `java.util.Scanner`.

### Jawaban

Kode `TestLogistik` pada bagian 4.3 sudah menggunakan `java.util.Scanner` untuk menerima input dinamis dari pengguna. Seluruh atribut (`nomorResi`, `namaPemilik`, `kapasitasMaksimal`, `tambahMuatan`, `turunkanMuatan`) diinput melalui terminal.

---

## 4.6 Soal 7 – Class Tiket Bioskop

### Pertanyaan
Buatlah class `Tiket` untuk mengelola data pemesanan tiket bioskop dengan atribut `judulFilm`, `hargaDasar`, dan `statusPembayaran`.

### Kode Class `Tiket`

```java
package id.ac.polinema;

public class Tiket {
    private String judulFilm;
    private double hargaDasar;
    private boolean statusPembayaran;

    public Tiket(String judulFilm, double hargaDasar) {
        this.judulFilm = judulFilm;
        if (hargaDasar < 0) {
            this.hargaDasar = 35000;
        } else {
            this.hargaDasar = hargaDasar;
        }
        this.statusPembayaran = false;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    public boolean getStatusPembayaran() {
        return statusPembayaran;
    }

    public void lakukanPembayaran() {
        statusPembayaran = true;
        System.out.println("Pembayaran berhasil untuk film: " + judulFilm);
    }

    public void cetakInfo() {
        System.out.println("Judul Film       : " + judulFilm);
        System.out.println("Harga Dasar      : Rp " + hargaDasar);
        System.out.println("Status Pembayaran: " + (statusPembayaran ? "Sudah Dibayar" : "Belum Dibayar"));
    }
}
```

### Kode Class `TestBioskop`

```java
package id.ac.polinema;

public class TestBioskop {
    public static void main(String[] args) {
        Tiket tiket1 = new Tiket("Avengers: Endgame", 50000);
        Tiket tiket2 = new Tiket("Oppenheimer", -10000);

        System.out.println("=== Tiket 1 ===");
        tiket1.cetakInfo();
        tiket1.lakukanPembayaran();
        tiket1.cetakInfo();

        System.out.println();

        System.out.println("=== Tiket 2 ===");
        tiket2.cetakInfo();
        tiket2.lakukanPembayaran();
        tiket2.cetakInfo();
    }
}
```

### Verifikasi Hasil

```text
=== Tiket 1 ===
Judul Film       : Avengers: Endgame
Harga Dasar      : Rp 50000.0
Status Pembayaran: Belum Dibayar
Pembayaran berhasil untuk film: Avengers: Endgame
Judul Film       : Avengers: Endgame
Harga Dasar      : Rp 50000.0
Status Pembayaran: Sudah Dibayar

=== Tiket 2 ===
Judul Film       : Oppenheimer
Harga Dasar      : Rp 35000.0
Status Pembayaran: Belum Dibayar
Pembayaran berhasil untuk film: Oppenheimer
Judul Film       : Oppenheimer
Harga Dasar      : Rp 35000.0
Status Pembayaran: Sudah Dibayar
```

Pada tiket 2, harga dasar yang dimasukkan `-10000` (negatif) secara otomatis diset menjadi `Rp 35000` sesuai ketentuan.

---

# 5. Jawaban Pertanyaan

## 5.1 Apa yang dimaksud getter dan setter?

**Getter** adalah method *public* yang memiliki tipe data return, berfungsi untuk mendapatkan (membaca) nilai dari atribut `private`.

**Setter** adalah method *public* yang tidak memiliki tipe data return, berfungsi untuk mengubah (menulis) nilai dari atribut `private`.

---

## 5.2 Apa kegunaan dari method `getSimpanan()`?

Method `getSimpanan()` digunakan untuk membaca nilai atribut `simpanan` yang bersifat `private`. Karena atribut `simpanan` tidak memiliki setter, nilai simpanan hanya dapat dibaca dari luar class melalui method ini dan diubah melalui method `setor()` dan `pinjam()`.

---

## 5.3 Method apa yang digunakan untuk menambah saldo?

Method yang digunakan untuk menambah saldo adalah `setor(double jumlah)`.

---

## 5.4 Apa yang dimaksud konstruktor?

Konstruktor adalah blok kode yang dijalankan secara otomatis ketika sebuah object dibuat menggunakan keyword `new`. Konstruktor digunakan untuk melakukan inisialisasi awal pada atribut object.

---

## 5.5 Sebutkan aturan dalam membuat konstruktor!

1. Nama konstruktor harus sama dengan nama class.
2. Konstruktor tidak memiliki tipe data return.
3. Konstruktor tidak boleh menggunakan modifier `abstract`, `static`, `final`, dan `synchronized`.

---

## 5.6 Apakah boleh konstruktor bertipe private?

Ya, boleh. Konstruktor `private` digunakan untuk membatasi akses instansiasi dari luar class, misalnya pada pola *Singleton* atau *Utility Class* yang hanya berisi method *static*.

---

## 5.7 Kapan menggunakan konstruktor dengan passing parameter?

Konstruktor dengan *passing parameter* digunakan ketika object membutuhkan nilai awal yang spesifik saat pertama kali dibuat, misalnya `nama` dan `alamat` pada class `Anggota`. Jika suatu atribut tidak memerlukan nilai spesifik dari luar (misalnya `simpanan` yang selalu diinisialisasi `0`), maka atribut tersebut tidak perlu dijadikan parameter konstruktor.

---

## 5.8 Apa perbedaan inisialisasi atribut dan instansiasi atribut?

- **Inisialisasi atribut** adalah pemberian nilai awal pada atribut saat deklarasi, misalnya `private int simpanan = 0;`.
- **Instansiasi atribut** adalah pembuatan object dari sebuah class menggunakan keyword `new`, misalnya `Anggota a = new Anggota("Budi", "Jl. Merdeka");`.

---

## 5.9 Apa perbedaan inisialisasi method dan instansiasi method?

- **Inisialisasi method** adalah pendefinisian isi dari sebuah method (menuliskan kode di dalam method).
- **Instansiasi method** adalah pemanggilan method melalui object, misalnya `anggota.setor(50000);`.

---

# 6. Kesimpulan

Berdasarkan praktikum Jobsheet 3, telah dipelajari konsep enkapsulasi pada Pemrograman Berorientasi Objek. Enkapsulasi diterapkan dengan menggunakan *access modifier* `private` pada atribut sehingga data tidak dapat diakses atau diubah secara langsung dari luar class. Akses terhadap atribut dilakukan melalui method *getter* (untuk membaca) dan *setter* (untuk mengubah).

Percobaan pada jobsheet ini menunjukkan bahwa tanpa enkapsulasi, atribut dapat diakses dan dimanipulasi secara sembarangan (misalnya kecepatan motor berubah tanpa menyalakan mesin). Dengan menerapkan *access modifier* `private` dan method kontrol, akses terhadap atribut menjadi terkontrol dan sesuai dengan logika bisnis yang diinginkan.

Selain itu, telah dipelajari konsep konstruktor yang digunakan untuk inisialisasi object saat pertama kali dibuat, serta notasi pada UML Class Diagram yang menggunakan simbol `+`, `-`, `#`, dan `~` untuk menunjukkan *access modifier*.

Pada tugas praktikum, konsep enkapsulasi diterapkan pada class `Kontainer` untuk sistem logistik dan class `Tiket` untuk sistem pemesanan tiket bioskop, lengkap dengan validasi data dan pembatasan akses terhadap atribut.
