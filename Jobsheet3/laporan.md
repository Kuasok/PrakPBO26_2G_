# LAPORAN JOBSHEET 3 – ENKAPSULASI PBO

**Nama:** Muhammad Ferdi Afiyanto  
**NIM:** 254107020122  
**Absen:** 16  
**Kelas:** 2G

---

## 1. Tujuan Praktikum

Setelah melakukan percobaan pada modul ini, mahasiswa memahami konsep:

1. Konstruktor.
2. Access Modifier.
3. Atribut/method pada class.
4. Instansiasi atribut/method.
5. Setter dan getter.
6. Notasi pada UML Class Diagram.

## 2. Pendahuluan

Enkapsulasi disebut juga *information-hiding*. Dalam berinteraksi dengan objek, pengguna tidak harus mengetahui kompleksitas proses internal objek. Pada Java, konsep ini diterapkan antara lain dengan menyembunyikan atribut menggunakan `private` dan menyediakan method untuk mengakses atau memanipulasinya.

### 2.1 Access Modifier

| Modifier | Within Class | Within Package | Outside Package by Subclass | Outside Package |
|---|---|---|---|---|
| `private` | Y | N | N | N |
| default | Y | Y | N | N |
| `protected` | Y | Y | Y | N |
| `public` | Y | Y | Y | Y |

Notasi UML:

- `+` = public
- `#` = protected
- `-` = private
- `~` = default

### 2.2 Getter dan Setter

Getter merupakan method `public` yang memiliki nilai return untuk mendapatkan nilai atribut `private`. Setter merupakan method `public` tanpa return untuk memanipulasi nilai atribut `private`.

### 2.3 Konstruktor

Konstruktor dijalankan ketika objek dibuat dengan `new`. Nama konstruktor harus sama dengan nama class dan konstruktor tidak memiliki tipe return. Konstruktor tidak boleh menggunakan modifier `abstract`, `static`, `final`, dan `synchronized`.

---

# 3. Percobaan

## 3.1 Percobaan 1 – Enkapsulasi

Pada tahap pertama atribut motor masih dapat diakses langsung dari luar class. Hal ini menunjukkan masalah information hiding karena `kecepatan` dapat diubah walaupun kontak motor masih OFF.

### `Motor.java`

```java
package motorencapsulation;

public class Motor {
    public int kecepatan = 0;
    public boolean kontakOn = false;

    public void printStatus(){
        if (kontakOn == true){
            System.out.println("Kontak On");
        }
        else{
            System.out.println("Kontak Off");
        }
        System.out.println("Kecepatan " + kecepatan+"\n");
    }
}
```

### `MotorDemo.java`

```java
package motorencapsulation;

public class MotorDemo {
    public static void main(String[] args) {
        Motor motor = new Motor();
        motor.printStatus();
        motor.kecepatan = 50;
        motor.printStatus();
    }
}
```

### Output

```text
Kontak Off
Kecepatan 0

Kontak Off
Kecepatan 50

```

**Analisis:** Kecepatan dapat berubah langsung menjadi 50 meskipun kontak masih OFF. Inilah masalah yang kemudian diperbaiki pada Percobaan 2.

---

## 3.2 Percobaan 2 – Access Modifier

Atribut `kecepatan` dan `kontakOn` diubah menjadi `private`. Akses terhadap keduanya dikendalikan melalui method `nyalakanMesin()`, `matikanMesin()`, `tambahKecepatan()`, `kurangiKecepatan()`, dan `printStatus()`.

### `Motor.java`

```java
package motorencapsulation;

public class Motor {
    private int kecepatan = 0;
    private boolean kontakOn = false;

    public void nyalakanMesin(){
        kontakOn = true;
    }

    public void matikanMesin(){
        kontakOn = false;
        kecepatan = 0;
    }

    public void tambahKecepatan(){
        if (kontakOn == true){
            kecepatan += 5;
        }
        else{
            System.out.println("Kecepatan tidak bisa bertambah karena Mesin Off!\n");
        }
    }

    public void kurangiKecepatan(){
        if (kontakOn == true){
            kecepatan -= 5;
        }
        else{
            System.out.println("Kecepatan tidak bisa berkurang karena Mesin Off!\n");
        }
    }

    public void printStatus(){
        if (kontakOn == true){
            System.out.println("Kontak On");
        }
        else{
            System.out.println("Kontak Off");
        }
        System.out.println("Kecepatan " + kecepatan+"\n");
    }
}
```

### `MotorDemo.java`

```java
package motorencapsulation;

public class MotorDemo {
    public static void main(String[] args) {
        Motor motor = new Motor();
        motor.printStatus();
        motor.tambahKecepatan();

        motor.nyalakanMesin();
        motor.printStatus();

        motor.tambahKecepatan();
        motor.printStatus();

        motor.tambahKecepatan();
        motor.printStatus();

        motor.tambahKecepatan();
        motor.printStatus();

        motor.matikanMesin();
        motor.printStatus();
    }
}
```

### Output

```text
Kontak Off
Kecepatan 0

Kecepatan tidak bisa bertambah karena Mesin Off!

Kontak On
Kecepatan 0

Kontak On
Kecepatan 5

Kontak On
Kecepatan 10

Kontak On
Kecepatan 15

Kontak Off
Kecepatan 0

```

### Pertanyaan

**1. Mengapa saat menambah kecepatan pertama kali muncul peringatan Mesin Off?**  
Karena `tambahKecepatan()` dipanggil sebelum `nyalakanMesin()`, sehingga `kontakOn` masih bernilai `false`. Method kemudian menjalankan bagian `else` dan menampilkan peringatan.

**2. Mengapa atribut `kecepatan` dan `kontakOn` diset `private`?**  
Agar atribut tidak dapat diubah langsung dari luar class. Dengan demikian perubahan state motor harus melalui method yang telah disediakan sehingga aturan penggunaan motor dapat dikontrol.

**3. Ubah class Motor sehingga kecepatan maksimal 100!**

Tambahkan validasi setelah penambahan kecepatan:

```java
public void tambahKecepatan(){
    if (kontakOn == true){
        kecepatan += 5;
        if (kecepatan > 100){
            kecepatan = 100;
        }
    }
    else{
        System.out.println("Kecepatan tidak bisa bertambah karena Mesin Off!\n");
    }
}
```

---

## 3.3 Percobaan 3 – Getter dan Setter

### `Anggota.java`

```java
package koperasigettersetter;

public class Anggota {
    private String nama;
    private String alamat;
    private float simpanan;

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getNama(){
        return nama;
    }

    public String getAlamat(){
        return alamat;
    }

    public float getSimpanan(){
        return simpanan;
    }

    public void setor(float uang){
        simpanan += uang;
    }

    public void pinjam(float uang){
        simpanan -= uang;
    }
}
```

### `KoperasiDemo.java`

```java
package koperasigettersetter;

public class KoperasiDemo {
    public static void main(String[] args) {
        Anggota anggota1 = new Anggota();
        anggota1.setNama("Iwan Setiawan");
        anggota1.setAlamat("Jalan Sukarno Hatta no 10");
        anggota1.setor(100000);
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());

        anggota1.pinjam(5000);
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());
    }
}
```

### Output

```text
Simpanan Iwan Setiawan : Rp 100000.0
Simpanan Iwan Setiawan : Rp 95000.0
```

---

## 3.4 Percobaan 4 – Konstruktor, Instansiasi

### Kondisi sebelum konstruktor

Objek `Anggota` dibuat tanpa parameter. Ketika `getNama()` dipanggil sebelum `setNama()`, hasilnya `null` karena atribut `nama` belum diberi nilai.

### `Anggota.java` setelah ditambahkan konstruktor

```java
package koperasigettersetter;

public class Anggota {
    private String nama;
    private String alamat;
    private float simpanan;

    Anggota(String nama, String alamat){
        this.nama = nama;
        this.alamat = alamat;
        this.simpanan = 0;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getNama(){
        return nama;
    }

    public String getAlamat(){
        return alamat;
    }

    public float getSimpanan(){
        return simpanan;
    }

    public void setor(float uang){
        simpanan += uang;
    }

    public void pinjam(float uang){
        simpanan -= uang;
    }
}
```

### `KoperasiDemo.java`

```java
package koperasigettersetter;

public class KoperasiDemo {
    public static void main(String[] args) {
        Anggota anggota1 = new Anggota("Iwan", "Jalan Mawar");
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());

        anggota1.setNama("Iwan Setiawan");
        anggota1.setAlamat("Jalan Sukarno Hatta no 10");
        anggota1.setor(100000);
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());

        anggota1.pinjam(5000);
        System.out.println("Simpanan " + anggota1.getNama() + " : Rp " + anggota1.getSimpanan());
    }
}
```

### Output

```text
Simpanan Iwan : Rp 0.0
Simpanan Iwan Setiawan : Rp 100000.0
Simpanan Iwan Setiawan : Rp 95000.0
```

### Pertanyaan Percobaan 3 dan 4

**1. Apa yang dimaksud getter dan setter?**  
Getter adalah method untuk membaca nilai atribut private, sedangkan setter adalah method untuk mengubah nilai atribut private.

**2. Apa kegunaan `getSimpanan()`?**  
Untuk membaca nilai `simpanan` dari luar class karena atribut tersebut bersifat private.

**3. Method apa yang digunakan untuk menambah saldo?**  
`setor(float uang)`.

**4. Apa yang dimaksud konstruktor?**  
Konstruktor adalah method khusus yang dijalankan ketika object dibuat dan digunakan untuk inisialisasi awal object.

**5. Sebutkan aturan membuat konstruktor!**

1. Nama konstruktor sama dengan nama class.
2. Tidak memiliki tipe data return.
3. Tidak boleh menggunakan `abstract`, `static`, `final`, atau `synchronized`.

**6. Apakah boleh konstruktor bertipe private?**  
Boleh. Konstruktor `private` dapat digunakan untuk membatasi pembuatan object dari luar class, misalnya pada Singleton atau Utility Class.

**7. Kapan menggunakan konstruktor dengan passing parameter?**  
Ketika object membutuhkan nilai awal yang spesifik saat dibuat, misalnya `nama` dan `alamat`.

**8. Apa perbedaan inisialisasi atribut dan instansiasi atribut?**  
Inisialisasi atribut adalah memberikan nilai awal pada atribut, sedangkan instansiasi adalah membuat object dari sebuah class menggunakan `new`. Istilah "instansiasi atribut" sendiri kurang tepat; yang diinstansiasi adalah object/class.

**9. Apa perbedaan inisialisasi method dan instansiasi method?**  
Method tidak diinstansiasi. Method didefinisikan/dideklarasikan di dalam class lalu dipanggil pada object (atau class untuk method static). Instansiasi berlaku pada object, bukan method.

---

# 4. Tugas

## 4.1 Tugas 1 – EncapDemo

### `EncapDemo.java`

```java
public class EncapDemo {
    private String name;
    private int age;

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int newAge)
    {
        if(newAge > 30)
        {
            age = 30;
        }
        else
        {
            age = newAge;
        }
    }
}
```

### `EncapTest.java`

```java
public class EncapTest {
    public static void main(String args[])
    {
        EncapDemo encap = new EncapDemo();
        encap.setName("James");
        encap.setAge(35);

        System.out.println("Name : " + encap.getName());
        System.out.println("Age : " + encap.getAge());
    }
}
```

### Output

```text
Name : James
Age : 30
```

## 4.2 Tugas 2

**Mengapa age diset 35 tetapi hasilnya 30?**  
Karena `setAge()` memiliki validasi `if(newAge > 30)`. Ketika nilai 35 dikirim, kondisi terpenuhi sehingga atribut `age` justru diisi 30. Dengan demikian nilai maksimum age dibatasi 30.

## 4.3 Tugas 3 – Batas age 18 sampai 30

```java
public void setAge(int newAge)
{
    if(newAge > 30)
    {
        age = 30;
    }
    else if(newAge < 18)
    {
        age = 18;
    }
    else
    {
        age = newAge;
    }
}
```

Pada folder `src/tugas/1-3-age/` disediakan implementasi lengkap `EncapDemoMinMax.java` dan `EncapTestMinMax.java`.

---

## 4.4 Tugas 4 – Sistem Kontainer Logistik

### `Kontainer.java`

```java
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
        if (beratMuatanSaatIni + berat > kapasitasMaksimal) {
            System.out.println("Maaf, berat muatan melebihi kapasitas maksimal kontainer.");
        } else {
            beratMuatanSaatIni += berat;
        }
    }

    public void turunkanMuatan(double berat) {
        beratMuatanSaatIni -= berat;
    }
}
```

### `TestLogistik.java`

```java
public class TestLogistik {
    public static void main(String[] args) {
        Kontainer kontainerAlfa = new Kontainer("REQ-9988", "PT. Maju Bersama", 5000);

        System.out.println("Nama Pemilik Kontainer: " + kontainerAlfa.getNamaPemilik());
        System.out.println("Kapasitas Maksimal: " + kontainerAlfa.getKapasitasMaksimal() + " kg");

        System.out.println("\nMemasukkan muatan baru seberat 6.000 kg...");
        kontainerAlfa.tambahMuatan(6000);
        System.out.println("Berat muatan saat ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");

        System.out.println("\nMemasukkan muatan baru seberat 4.000 kg...");
        kontainerAlfa.tambahMuatan(4000);
        System.out.println("Berat muatan saat ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");

        System.out.println("\nMembongkar muat/menurunkan barang seberat 500 kg...");
        kontainerAlfa.turunkanMuatan(500);
        System.out.println("Berat muatan saat ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");

        System.out.println("\nMembongkar muat/menurunkan barang seberat 1.500 kg...");
        kontainerAlfa.turunkanMuatan(1500);
        System.out.println("Berat muatan saat ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");
    }
}
```

### Output yang diharapkan

```text
Nama Pemilik Kontainer: PT. Maju Bersama
Kapasitas Maksimal: 5000.0 kg

Memasukkan muatan baru seberat 6.000 kg...
Maaf, berat muatan melebihi kapasitas maksimal kontainer.
Berat muatan saat ini: 0.0 kg

Memasukkan muatan baru seberat 4.000 kg...
Berat muatan saat ini: 4000.0 kg

Membongkar muat/menurunkan barang seberat 500 kg...
Berat muatan saat ini: 3500.0 kg

Membongkar muat/menurunkan barang seberat 1.500 kg...
Berat muatan saat ini: 2000.0 kg
```

### Jawaban

Class `Kontainer` menerapkan enkapsulasi dengan menjadikan seluruh atribut `private`. Data dibaca melalui getter, sedangkan perubahan berat muatan dilakukan melalui `tambahMuatan()` dan `turunkanMuatan()`. Ketika tambahan muatan membuat berat melebihi kapasitas, perubahan ditolak sehingga state object tetap aman.

---

## 4.5 Tugas 5 – Batas Bongkar Maksimal 50%

Method `turunkanMuatan()` dimodifikasi sehingga satu kali pembongkaran tidak boleh lebih dari 50% berat muatan saat ini.

```java
public void turunkanMuatan(double berat) {
    if (berat > beratMuatanSaatIni) {
        System.out.println("Maaf, berat muatan yang diturunkan melebihi muatan saat ini!");
    } else if (berat > 0.5 * beratMuatanSaatIni) {
        System.out.println("Maaf, demi keselamatan, pembongkaran muatan satu kali jalan tidak boleh melebihi 50% dari muatan saat ini!");
    } else {
        beratMuatanSaatIni -= berat;
    }
}
```

Contoh ketika muatan 4000 kg dan operator mencoba menurunkan 2500 kg:

```text
Mencoba menurunkan muatan 2.500 kg...
Maaf, demi keselamatan, pembongkaran muatan satu kali jalan tidak boleh melebihi 50% dari muatan saat ini!
Berat muatan saat ini: 4000.0 kg
```

Jika menurunkan tepat 2000 kg, operasi diperbolehkan karena 2000 kg = 50% dari 4000 kg.

---

## 4.6 Tugas 6 – Input Dinamis dengan Scanner

### `TestLogistik.java`

```java
import java.util.Scanner;

public class TestLogistik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nomor resi: ");
        String nomorResi = scanner.nextLine();
        System.out.print("Masukkan nama pemilik: ");
        String namaPemilik = scanner.nextLine();
        System.out.print("Masukkan kapasitas maksimal (kg): ");
        double kapasitas = scanner.nextDouble();

        Kontainer kontainer = new Kontainer(nomorResi, namaPemilik, kapasitas);

        System.out.print("Masukkan berat barang yang ditambahkan (kg): ");
        double tambahMuatan = scanner.nextDouble();
        kontainer.tambahMuatan(tambahMuatan);
        System.out.println("Berat muatan saat ini: " + kontainer.getBeratMuatanSaatIni() + " kg");

        System.out.print("Masukkan berat barang yang dibongkar (kg): ");
        double turunkanMuatan = scanner.nextDouble();
        kontainer.turunkanMuatan(turunkanMuatan);
        System.out.println("Berat muatan saat ini: " + kontainer.getBeratMuatanSaatIni() + " kg");

        scanner.close();
    }
}
```

`Scanner` memungkinkan nilai `tambahMuatan` dan `turunkanMuatan` tidak lagi ditentukan langsung di source code, tetapi dimasukkan pengguna melalui terminal.

---

## 4.7 Tugas 7 – Sistem Pemesanan Tiket Bioskop

### `Tiket.java`

```java
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

    public boolean isStatusPembayaran() {
        return statusPembayaran;
    }

    public void lakukanPembayaran() {
        statusPembayaran = true;
    }
}
```

### `TestBioskop.java`

```java
public class TestBioskop {
    public static void main(String[] args) {
        Tiket tiket1 = new Tiket("Avengers: Endgame", -50000);
        System.out.println("Film: " + tiket1.getJudulFilm());
        System.out.println("Harga Tiket: " + tiket1.getHargaDasar());
        System.out.println("Status Lunas? " + tiket1.isStatusPembayaran());

        System.out.println("\nMemproses pembayaran...");
        tiket1.lakukanPembayaran();
        System.out.println("Status Lunas Terbaru? " + tiket1.isStatusPembayaran());
    }
}
```

### Output

```text
Film: Avengers: Endgame
Harga Tiket: 35000.0
Status Lunas? false

Memproses pembayaran...
Status Lunas Terbaru? true
```

### Jawaban

Harga `-50000` tidak diterima karena harga dasar tidak boleh negatif, sehingga konstruktor mengubahnya menjadi nilai default `35000`. Status pembayaran pada awal object adalah `false`. Status tidak memiliki setter agar tidak dapat diubah sembarangan dari luar class. Setelah `lakukanPembayaran()` dipanggil, status berubah menjadi `true`.

---

# 5. Kesimpulan

Praktikum Jobsheet 3 menunjukkan penerapan enkapsulasi dalam Java. Atribut dapat dilindungi menggunakan access modifier `private`, kemudian diakses melalui getter atau dimodifikasi melalui setter dan method khusus. Konstruktor digunakan untuk memberikan nilai awal saat object dibuat. Praktikum juga memperlihatkan bagaimana validasi pada method dapat menjaga state object agar tidak berubah secara sembarangan. Selain itu, notasi access modifier pada UML Class Diagram membantu menggambarkan tingkat akses setiap atribut dan method.
