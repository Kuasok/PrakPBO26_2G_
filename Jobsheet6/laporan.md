# LAPORAN JOBSHEET 6 — INHERITANCE (PEWARISAN)

## Identitas

- **Nama** : Muhammad Ferdi Afiyanto
- **NIM** : 254107020122
- **Kelas** : 2G
- **Mata Kuliah** : Praktikum Pemrograman Berbasis Objek
- **Pertemuan** : 6
- **Materi** : Inheritance (single & multilevel), hak akses, `this`/`super`, konstruktor, dan overriding

---

# 1. Tujuan

Jobsheet ini membahas pewarisan (**inheritance**) sebagai relasi *is-a*. Lima percobaan dan dua tugas mandiri dibangun dalam package terpisah `id.ac.polinema.inheritance.percobaanN` serta `tugas1`, `tugas2`, dan `pengayaan` agar nama class yang sama (`ClassA`, `ClassB`, `ClassC`) tidak saling bentrok.

# 2. Dasar Teori Singkat

### Inheritance (is-a)

Subclass (`extends`) mewarisi seluruh member superclass kecuali member `private` dan konstruktor. Java hanya mengizinkan **satu** superclass langsung (*single inheritance*), tetapi satu superclass boleh memiliki banyak subclass (*hierarchical*). Bila subclass menjadi superclass bagi class lain, terbentuk *multilevel inheritance*.

### Hak akses

| Modifier | Class sama | Package sama | Subclass beda package | Class mana pun |
|---|---|---|---|---|
| `private` | ✓ | | | |
| default | ✓ | ✓ | | |
| `protected` | ✓ | ✓ | ✓ | |
| `public` | ✓ | ✓ | ✓ | ✓ |

### `this` dan `super`

`this` merujuk member class sendiri; `super` merujuk member superclass. `super` menjadi wajib ketika subclass mendeklarasikan atribut/method dengan nama sama (*shadowing*/*overriding*).

### Konstruktor dan overriding

Konstruktor tidak diwariskan, tetapi konstruktor superclass selalu dijalankan lebih dulu. `super(...)` wajib menjadi baris pertama pada konstruktor subclass (pada JDK 21). `@Override` membuat compiler memverifikasi bahwa sebuah method benar-benar menimpa method superclass.

---

# 3. Percobaan 1 — Single Inheritance dengan `extends`

## 3.1 Kode Program

### `ClassA.java`

```java
package id.ac.polinema.inheritance.percobaan1;

public class ClassA {
    public int x;
    public int y;

    public void getNilai() {
        System.out.println("nilai x: " + x);
        System.out.println("nilai y: " + y);
    }
}
```

### `ClassB.java` (setelah perbaikan Langkah 6)

```java
package id.ac.polinema.inheritance.percobaan1;

public class ClassB extends ClassA {
    public int z;

    public void getNilaiZ() {
        System.out.println("nilai z: " + z);
    }

    public void getJumlah() {
        System.out.println("jumlah: " + (x + y + z));
    }
}
```

### `MainPercobaan1.java`

```java
package id.ac.polinema.inheritance.percobaan1;

public class MainPercobaan1 {
    public static void main(String[] args) {
        ClassB hitung = new ClassB();
        hitung.x = 20;
        hitung.y = 30;
        hitung.z = 5;
        hitung.getNilai();
        hitung.getNilaiZ();
        hitung.getJumlah();
    }
}
```

## 3.2 Output

```text
nilai x: 20
nilai y: 30
nilai z: 5
jumlah: 55
```

## 3.3 Jawaban Pertanyaan

### 1. Mengapa kompilasi Langkah 5 gagal? Pesan error dan lokasinya?

Kompilasi gagal karena `ClassB` **belum** diturunkan dari `ClassA`, sehingga `getJumlah()` memakai `x` dan `y` yang tidak dikenal di `ClassB`. Pesan error pertama yang muncul:

```text
ClassB.java:11: error: cannot find symbol
        System.out.println("jumlah: " + (x + y + z));
                                         ^
  symbol:   variable x
  location: class ClassB
```

Baris 11 kolom penunjuk pertama adalah variabel `x` (diikuti error serupa untuk `y`). Semuanya di file `ClassB.java`.

### 2. Baris yang diubah pada Langkah 6 dan artinya?

Baris deklarasi class diubah dari:

```java
public class ClassB {
```

menjadi:

```java
public class ClassB extends ClassA {
```

Artinya `ClassB` mewarisi seluruh member `ClassA`. **ClassA = superclass**, **ClassB = subclass**.

### 3. Atribut/method yang dapat dipakai objek `hitung`

| Dideklarasikan di | Anggota |
|---|---|
| `ClassA` (diwarisi) | `x`, `y`, `getNilai()` |
| `ClassB` (milik sendiri) | `z`, `getNilaiZ()`, `getJumlah()` |

### 4. Mengapa `hitung.x = 20` boleh padahal `x` tidak dideklarasikan di `ClassB`?

Karena `x` dideklarasikan di `ClassA` sebagai `public` dan diwariskan kepada `ClassB`. Objek `ClassB` secara internal membawa bagian `ClassA`, sehingga `x` dapat diakses melalui objek `hitung`.

### 5. Apa risiko atribut `public`?

Atribut `public` dapat diubah langsung dari class mana pun tanpa validasi. Ini melanggar **enkapsulasi**: nilai bisa diisi dengan data tidak valid dan detail implementasi `ClassA` menjadi terbuka. Alternatifnya adalah `private`/`protected` dengan getter/setter (ditelusuri di Percobaan 2).

### 6. `extends ClassA, ClassD`?

Java menolak multiple inheritance untuk class; deklarasi tersebut menghasilkan error kompilasi (`class cannot extend multiple classes`). **Kesimpulan:** Java hanya mengizinkan satu superclass langsung.

---

# 4. Percobaan 2 — Hak Akses (`private` vs `protected`)

## 4.1 Kode Program

### `ClassA.java` (Perbaikan B: `private` + getter)

```java
package id.ac.polinema.inheritance.percobaan2;

public class ClassA {
    private int x;
    private int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void getNilai() {
        System.out.println("nilai x: " + x);
        System.out.println("nilai y: " + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
```

### `ClassB.java` (Perbaikan B: memakai getter)

```java
package id.ac.polinema.inheritance.percobaan2;

public class ClassB extends ClassA {
    private int z;

    public void setZ(int z) {
        this.z = z;
    }

    public void getNilaiZ() {
        System.out.println("nilai z: " + z);
    }

    public void getJumlah() {
        System.out.println("jumlah: " + (getX() + getY() + z));
    }
}
```

### `MainPercobaan2.java`

```java
package id.ac.polinema.inheritance.percobaan2;

public class MainPercobaan2 {
    public static void main(String[] args) {
        ClassB hitung = new ClassB();
        hitung.setX(20);
        hitung.setY(30);
        hitung.setZ(5);
        hitung.getNilai();
        hitung.getNilaiZ();
        hitung.getJumlah();
    }
}
```

## 4.2 Output

```text
nilai x: 20
nilai y: 30
nilai z: 5
jumlah: 55
```

## 4.3 Jawaban Pertanyaan

### 1. Di file/baris mana error Langkah 5 dan mengapa tidak muncul di `MainPercobaan2`?

Error muncul di `ClassB.java` baris 15:

```text
ClassB.java:15: error: x has private access in ClassA
ClassB.java:15: error: y has private access in ClassA
```

`MainPercobaan2` tidak error karena hanya memanggil **method public** (`setX`, `setY`, `setZ`, `getNilai`, `getNilaiZ`, `getJumlah`). Pelanggaran justru terjadi **di dalam `ClassB`** yang mencoba membaca atribut `private` milik `ClassA` secara langsung.

### 2. Penyebab error bila dikaitkan dengan tabel hak akses

Menurut tabel, member `private` hanya dapat diakses oleh **class itu sendiri** (`ClassA`). Member `private` tidak diwariskan, sehingga `ClassB` tidak berhak mengakses `x` dan `y` secara langsung.

### 3. Mengapa `hitung.setX(20)` tidak error meski `x` private? Di mana nilai tersimpan?

`setX(int)` adalah **method public** milik `ClassA`, sehingga pemanggilannya dari luar legal. Di dalam method itulah `x` diakses (masih di dalam `ClassA`). Nilai `20` disimpan pada atribut `x` milik `ClassA` yang menjadi bagian dari objek `ClassB` (karena setiap objek subclass membawa bagian superclass-nya).

### 4. Mana yang lebih baik, Perbaikan A (`protected`) atau B (`private` + getter)?

Untuk program sungguhan saya memilih **Perbaikan B (`private` + getter)**. Alasan: enkapsulasi terjaga—`x` dan `y` hanya bisa dibaca lewat API terkontrol, sedangkan `protected` membuka state ke semua subclass di package manapun sehingga perubahan internal `ClassA` lebih berisiko. `protected` tetap berguna bila subclass memang perlu mengubah atribut induk secara langsung (seperti `phi`/`r` pada Percobaan 3).

### 5. Jika `ClassA` dan `ClassB` beda package?

`protected` **tetap dapat** diakses oleh `ClassB` walaupun berbeda package (karena `ClassB` adalah subclass). Sebaliknya, atribut **default** (tanpa modifier) **tidak dapat** diakses subclass di package berbeda—hanya bisa diakses class lain dalam package yang sama.

---

# 5. Percobaan 3 — Kata Kunci `this` dan `super`

## 5.1 Kode Program

### `Bangun.java`

```java
package id.ac.polinema.inheritance.percobaan3;

public class Bangun {
    protected double phi;
    protected int r;
}
```

### `Tabung.java` (final, termasuk Eksperimen 2)

```java
package id.ac.polinema.inheritance.percobaan3;

public class Tabung extends Bangun {
    protected int t;
    protected int r = 5;

    public void setSuperPhi(double phi) {
        super.phi = phi;
    }

    public void setSuperR(int r) {
        super.r = r;
    }

    public void setT(int t) {
        this.t = t;
    }

    public void volume() {
        System.out.println("Volume Tabung adalah: "
                + (super.phi * super.r * super.r * this.t));
    }

    public void cekR() {
        System.out.println("r       = " + r);
        System.out.println("this.r  = " + this.r);
        System.out.println("super.r = " + super.r);
    }
}
```

### `MainPercobaan3.java`

```java
package id.ac.polinema.inheritance.percobaan3;

public class MainPercobaan3 {
    public static void main(String[] args) {
        Tabung tabung = new Tabung();
        tabung.setSuperPhi(3.14);
        tabung.setSuperR(10);
        tabung.setT(3);
        tabung.volume();
        tabung.cekR();
    }
}
```

## 5.2 Output

```text
Volume Tabung adalah: 942.0
r       = 5
this.r  = 5
super.r = 10
```

## 5.3 Jawaban Pertanyaan

### 1. Fungsi `super` pada `setSuperPhi()` dan `setSuperR()`

`super.phi = phi;` mengisi atribut `phi` milik **superclass `Bangun`** dengan parameter, dan `super.r = r;` mengisi atribut `r` milik `Bangun`. `super` dipakai karena kedua atribut tersebut dideklarasikan di `Bangun`, bukan di `Tabung`.

### 2. Fungsi `super` dan `this` pada `volume()`

Pada ekspresi `super.phi * super.r * super.r * this.t`, `super.phi` dan `super.r` merujuk atribut warisan milik `Bangun` (bernilai `3.14` dan `10`), sedangkan `this.t` merujuk atribut `t` milik `Tabung` (bernilai `3`). `super` memperjelas asal member superclass; `this` menegaskan member milik class sendiri.

### 3. Mengapa `Tabung` tetap bisa mengakses `phi` dan `r`? Bagaimana bila `private`?

`Tabung` tidak mendeklarasikan `phi`/`r` tetapi mewarisinya karena `Bangun` mendeklarasikannya sebagai `protected`. Bila keduanya diubah menjadi `private`, `Tabung` **tidak lagi bisa** mengaksesnya langsung (error saat kompilasi: member tidak dapat diakses). Solusinya adalah menyediakan getter/setter `public` pada `Bangun`.

### 4. Eksperimen 1: apakah output berubah saat `super.phi` jadi `this.phi`?

Output **tetap** `Volume Tabung adalah: 942.0`. Pada saat Eksperimen 1, `Tabung` belum memiliki atribut `phi` sendiri, sehingga `this.phi` dan `super.phi` merujuk atribut yang **sama** (warisan `Bangun`). Nama atribut cukup unik saat itu.

### 5. Eksperimen 2: mengapa `r`, `this.r`, `super.r` berbeda? Kapan `super.` wajib?

`Tabung` kini mendeklarasikan `r = 5` sendiri, sehingga `r` dan `this.r` merujuk atribut `Tabung` (= 5), sementara `super.r` merujuk atribut `Bangun` (= 10, dari `setSuperR(10)`). Kondisi seperti ini disebut **shadowing**. Awalan `super.` menjadi **wajib** ketika subclass mendeklarasikan atribut dengan nama yang sama dan kita perlu mengakses versi superclass.

---

# 6. Percobaan 4 — Konstruktor dan Multilevel Inheritance

## 6.1 Kode Program

### `ClassA.java`

```java
package id.ac.polinema.inheritance.percobaan4;

public class ClassA {
    ClassA() {
        System.out.println("konstruktor A dijalankan");
    }
}
```

### `ClassB.java`

```java
package id.ac.polinema.inheritance.percobaan4;

public class ClassB extends ClassA {
    ClassB() {
        System.out.println("konstruktor B dijalankan");
    }
}
```

### `ClassC.java` (setelah Modifikasi 1)

```java
package id.ac.polinema.inheritance.percobaan4;

public class ClassC extends ClassB {
    ClassC() {
        super();
        System.out.println("konstruktor C dijalankan");
    }
}
```

### `MainPercobaan4.java`

```java
package id.ac.polinema.inheritance.percobaan4;

public class MainPercobaan4 {
    public static void main(String[] args) {
        ClassC test = new ClassC();
    }
}
```

## 6.2 Output

```text
konstruktor A dijalankan
konstruktor B dijalankan
konstruktor C dijalankan
```

## 6.3 Jawaban Pertanyaan

### 1. Superclass, subclass, dan mengapa `ClassB` berperan ganda?

- `ClassA` = superclass bagi `ClassB`.
- `ClassB` = subclass dari `ClassA` **sekaligus** superclass bagi `ClassC` → berperan ganda.
- `ClassC` = subclass dari `ClassB`.

`ClassB` berperan ganda karena berada di tengah hierarki *multilevel inheritance*.

### 2. Mengapa satu `new ClassC()` mencetak tiga baris?

Karena pembuatan objek subclass selalu menjalankan konstruktor superclass **lebih dulu**. `ClassC` memanggil konstruktor `ClassB`, dan `ClassB` memanggil konstruktor `ClassA`. Jadi bagian `ClassA`, `ClassB`, dan `ClassC` pada objek semuanya diinisialisasi.

### 3. Mengapa Modifikasi 1 outputnya sama?

`super();` pada Modifikasi 1 hanyalah menuliskan **secara eksplisit** pemanggilan konstruktor `ClassB` yang sebelumnya sudah dilakukan **implisit** oleh compiler. Menuliskannya eksplisit tidak mengubah urutan eksekusi.

### 4. Aturan apa yang dilanggar pada Modifikasi 2?

`super();` diletakkan setelah statement `System.out.println(...)`. Ini melanggar aturan bahwa **pemanggilan konstruktor superclass (`super(...)`/`this(...)`) harus menjadi baris pertama** di dalam konstruktor subclass.

> **Catatan versi compiler.** Pada JDK 21 pesan yang muncul adalah
> `call to super must be first statement in constructor`. Mesin ini memakai
> JDK 27 yang mengaktifkan *Flexible Constructor Bodies* (JEP 513); dengan
> `-source 21` pesannya menjadi `flexible constructors is not supported in -source 21`.
> Detail lengkap ada di `out/pesan-error.txt`.

Java menetapkan aturan ini agar bagian superclass objek **selesai diinisialisasi lebih dulu** sebelum kode subclass memakai state tersebut—mencegah subclass mengakses atribut superclass yang belum siap.

### 5. Urutan proses `new ClassC()`

1. JVM mengeksekusi `new ClassC()`.
2. Konstruktor `ClassC` mulai dan memanggil `super()` → masuk ke konstruktor `ClassB`.
3. Konstruktor `ClassB` memanggil `super()` → masuk ke konstruktor `ClassA`.
4. Konstruktor `ClassA` mencetak **"konstruktor A dijalankan"**.
5. Kembali ke konstruktor `ClassB`, mencetak **"konstruktor B dijalankan"**.
6. Kembali ke konstruktor `ClassC`, mencetak **"konstruktor C dijalankan"**.

---

# 7. Percobaan 5 — Konstruktor Berparameter dan Overriding

## 7.1 Kode Program

### `Komputer.java`

```java
package id.ac.polinema.inheritance.percobaan5;

public class Komputer {
    protected String merk;
    protected int kapasitasMemory;
    protected int kecepatanCPU;

    public Komputer(String merk, int memory, int cpu) {
        this.merk = merk;
        this.kapasitasMemory = memory;
        this.kecepatanCPU = cpu;
    }

    public void showInfo() {
        System.out.println("Merk            : " + merk);
        System.out.println("Kapasitas Memory: " + kapasitasMemory + " MB");
        System.out.println("Kecepatan CPU   : " + kecepatanCPU + " MHz");
    }

    public void nyalakanKomputer() {
        System.out.println("Komputer " + merk + " dinyalakan");
    }
}
```

### `Desktop.java`

```java
package id.ac.polinema.inheritance.percobaan5;

public class Desktop extends Komputer {
    protected String printer;

    public Desktop(String merk, int memory, int cpu, String printer) {
        super(merk, memory, cpu);
        this.printer = printer;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Printer         : " + printer);
    }
}
```

### `Laptop.java`

```java
package id.ac.polinema.inheritance.percobaan5;

public class Laptop extends Komputer {
    protected int resolusiLayar;

    public Laptop(String merk, int memory, int cpu, int resolusi) {
        super(merk, memory, cpu);
        this.resolusiLayar = resolusi;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Resolusi Layar  : " + resolusiLayar + "p");
    }
}
```

### `MainPercobaan5.java`

```java
package id.ac.polinema.inheritance.percobaan5;

public class MainPercobaan5 {
    public static void main(String[] args) {
        Desktop desk = new Desktop("Dell", 2048, 3500, "Canon");
        Laptop lap = new Laptop("Asus", 4096, 2500, 720);

        desk.showInfo();
        System.out.println();
        lap.showInfo();
        System.out.println();
        desk.nyalakanKomputer();
    }
}
```

### `Workstation.java` (Tantangan)

```java
package id.ac.polinema.inheritance.percobaan5;

public class Workstation extends Desktop {
    protected String gpu;

    public Workstation(String merk, int memory, int cpu, String printer, String gpu) {
        super(merk, memory, cpu, printer);
        this.gpu = gpu;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("GPU             : " + gpu);
    }
}
```

## 7.2 Output

### `MainPercobaan5`

```text
Merk            : Dell
Kapasitas Memory: 2048 MB
Kecepatan CPU   : 3500 MHz
Printer         : Canon

Merk            : Asus
Kapasitas Memory: 4096 MB
Kecepatan CPU   : 2500 MHz
Resolusi Layar  : 720p

Komputer Dell dinyalakan
```

### `MainTantangan5` (Workstation)

```text
Merk            : HP
Kapasitas Memory: 8192 MB
Kecepatan CPU   : 4200 MHz
Printer         : Epson
GPU             : RTX 4090
```

## 7.3 Jawaban Pertanyaan

### 1. Fungsi `super(merk, memory, cpu)` pada konstruktor `Desktop`

Baris tersebut memanggil konstruktor `Komputer` dan mengisi atribut **`merk`, `kapasitasMemory`, `kecepatanCPU`** pada bagian superclass objek. Baris berikutnya, `this.printer = printer;`, mengisi atribut **`printer`** milik `Desktop`.

### 2. Mengapa Eksperimen 1 error, padahal Percobaan 4 tidak?

Pada Percobaan 4, `ClassA`/`ClassB`/`ClassC` memiliki konstruktor **tanpa parameter**, sehingga `super()` implisit cocok. Pada Percobaan 5, `Komputer` **hanya** punya konstruktor berparameter `(String, int, int)`. Tanpa `super(merk, memory, cpu)`, compiler menyisipkan `super()` tanpa argumen yang tidak ada:

```text
Desktop.java:6: error: constructor Komputer in class Komputer cannot be applied to given types;
  required: String,int,int
  found:    no arguments
```

### 3. Istilah penulisan `showInfo()` di `Komputer` dan `Desktop`

Kondisi ini disebut **overriding**. Bila baris `super.showInfo();` pada `Desktop` dihapus, maka saat `desk.showInfo()` dipanggil hanya baris **`Printer : Canon`** yang tercetak—informasi `Merk`, `Kapasitas Memory`, dan `Kecepatan CPU` dari `Komputer` tidak tampil.

### 4. Perbedaan kompilasi dengan dan tanpa `@Override`

- **Dengan `@Override`** dan nama method salah ketik (`showinfo()`), compiler menolak: `showinfo() in Desktop does not override or implement a method from a supertype`.
- **Tanpa `@Override`**, kompilasi lolos karena `showinfo()` dianggap method **baru**. Akibatnya `desk.showInfo()` memanggil versi `Komputer` dan baris `Printer` tidak tercetak.

**Manfaat `@Override`:** mendeteksi kesalahan penulisan nama atau tanda tangan method saat kompilasi, sehingga kesalahan *silent* seperti method baru yang tidak menimpa apa pun bisa dicegah.

### 5. Tantangan — `Workstation`

`Workstation` adalah turunan `Desktop`; `showInfo()`-nya menimpa method tersebut, memanggil `super.showInfo()` (versi `Desktop`, yang juga memanggil `super.showInfo()` versi `Komputer`) lalu menambahkan baris `GPU`. Saat `new Workstation(...)` dibuat, konstruktor yang terpanggil berurutan: **`Komputer` → `Desktop` → `Workstation`** (dimulai dari `super(merk, memory, cpu, printer)`).

---

# 8. Tugas 1 — Pegawai, Dosen, dan DaftarGaji

## 8.1 Kode Program

### `Pegawai.java`

```java
package id.ac.polinema.inheritance.tugas1;

public class Pegawai {
    protected String nip;
    protected String nama;
    protected String alamat;

    public Pegawai(String nip, String nama, String alamat) {
        this.nip = nip;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getNip() { return nip; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat; }

    public int getGaji() {
        return 1500000;
    }
}
```

### `Dosen.java`

```java
package id.ac.polinema.inheritance.tugas1;

public class Dosen extends Pegawai {
    protected int jumlahSKS;
    protected static final int TARIF_SKS = 100000;

    public Dosen(String nip, String nama, String alamat, int jumlahSKS) {
        super(nip, nama, alamat);
        this.jumlahSKS = jumlahSKS;
    }

    @Override
    public int getGaji() {
        return super.getGaji() + (jumlahSKS * TARIF_SKS);
    }

    public int getJumlahSKS() {
        return jumlahSKS;
    }
}
```

### `DaftarGaji.java`

```java
package id.ac.polinema.inheritance.tugas1;

public class DaftarGaji {
    private Pegawai[] listPegawai;
    private int jumlahPegawai;

    public DaftarGaji(int kapasitas) {
        listPegawai = new Pegawai[kapasitas];
        jumlahPegawai = 0;
    }

    public void addPegawai(Pegawai pegawai) {
        if (jumlahPegawai < listPegawai.length) {
            listPegawai[jumlahPegawai] = pegawai;
            jumlahPegawai++;
        }
    }

    public void printSemuaGaji() {
        for (int i = 0; i < jumlahPegawai; i++) {
            System.out.println(
                    listPegawai[i].getNama() + " : " + listPegawai[i].getGaji());
        }
    }
}
```

### `MainTugas1.java`

```java
package id.ac.polinema.inheritance.tugas1;

public class MainTugas1 {
    public static void main(String[] args) {
        Pegawai budi = new Pegawai("P001", "Budi", "Malang");
        Dosen siti = new Dosen("D001", "Siti", "Surabaya", 12);

        DaftarGaji daftar = new DaftarGaji(10);
        daftar.addPegawai(budi);
        daftar.addPegawai(siti);

        daftar.printSemuaGaji();
    }
}
```

## 8.2 Output

```text
Budi : 1500000
Siti : 2700000
```

Perhitungan Siti: `1.500.000 + (12 × 100.000) = 2.700.000`.

## 8.3 Jawaban Analisis

**(a) Mengapa `Pegawai[]` dapat menampung objek `Dosen`?**

Karena `Dosen` adalah **subtype** dari `Pegawai` (hubungan is-a). Java mengizinkan *upcasting*: referensi bertipe superclass dapat menunjuk objek subclass, sehingga array `Pegawai[]` boleh menyimpan objek `Dosen` tanpa cast eksplisit.

**(b) Saat `printSemuaGaji()` memanggil `getGaji()` pada objek `Dosen`, versi mana yang dijalankan?**

Versi **`Dosen`**. Java menggunakan *dynamic method dispatch*: meskipun variabel bertipe `Pegawai`, method yang dieksekusi mengikuti tipe objek yang sebenarnya (`Dosen`), sehingga `getGaji()` milik `Dosen` (yang memanggil `super.getGaji()`) yang dijalankan.

---

# 9. Tugas 2 — Televisi dan TelevisiModern

## 9.1 Kode Program

### `Televisi.java`

```java
package id.ac.polinema.inheritance.tugas2;

public class Televisi {
    private String merk;
    private int jumlahChannel;
    private int channelAktif;

    public Televisi(String merk, int jumlahChannel) {
        this.merk = merk;
        this.jumlahChannel = jumlahChannel;
        this.channelAktif = 1;
    }

    public String getMerk() { return merk; }
    public int getJumlahChannel() { return jumlahChannel; }
    public int getChannelAktif() { return channelAktif; }

    public void pindahChannel(int channel) {
        if (channel >= 1 && channel <= jumlahChannel) {
            this.channelAktif = channel;
        }
    }

    public void info() {
        System.out.println("Merk           : " + merk);
        System.out.println("Jumlah Channel : " + jumlahChannel);
        System.out.println("Channel Aktif  : " + channelAktif);
    }
}
```

### `TelevisiModern.java`

```java
package id.ac.polinema.inheritance.tugas2;

public class TelevisiModern extends Televisi {
    private String modusTampilan;
    private String judulDVD;

    public TelevisiModern(String merk, int jumlahChannel) {
        super(merk, jumlahChannel);
        this.modusTampilan = "TV";
        this.judulDVD = "kosong";
    }

    public void gantiModusTampilan(String modus) {
        this.modusTampilan = modus;
    }

    public String getModusTampilan() {
        return modusTampilan;
    }

    public void masukkanDVD(String judul) {
        this.judulDVD = judul;
    }

    public void mainkanDVD() {
        System.out.println("Sedang memainkan DVD: " + judulDVD);
    }
}
```

### `MainTugas2.java`

```java
package id.ac.polinema.inheritance.tugas2;

public class MainTugas2 {
    public static void main(String[] args) {
        TelevisiModern tv = new TelevisiModern("Samsung", 100);
        System.out.println("Channel aktif: " + tv.getChannelAktif());
        tv.pindahChannel(20);
        System.out.println("Channel aktif sekarang: " + tv.getChannelAktif());
        tv.gantiModusTampilan("HDMI");
        tv.mainkanDVD();
        tv.masukkanDVD("The Matrix");
        tv.mainkanDVD();

        tv.pindahChannel(150);
        System.out.println("Channel aktif setelah pindahChannel(150): "
                + tv.getChannelAktif());
    }
}
```

## 9.2 Output

```text
Channel aktif: 1
Channel aktif sekarang: 20
Sedang memainkan DVD: kosong
Sedang memainkan DVD: The Matrix
Channel aktif setelah pindahChannel(150): 20
```

## 9.3 Jawaban Uji Tambahan

`tv.pindahChannel(150)` **tidak mengubah** `channelAktif`—nilainya tetap `20`, karena `150` berada di luar rentang `1..jumlahChannel` (`1..100`). Method hanya mengubah channel bila nilainya valid.

`channelAktif` **tidak dapat diubah langsung** dari `MainTugas2` karena atributnya `private`. `MainTugas2` hanya bisa membacanya lewat `getChannelAktif()` dan mengubahnya lewat `pindahChannel()`. Ini melindungi *invariant* (channel selalu dalam rentang valid) dan merupakan penerapan enkapsulasi.

---

# 10. Tugas 3 (Pengayaan) — Hierarki Karakter Game

## 10.1 Kode Program

### `Character.java`

```java
package id.ac.polinema.inheritance.pengayaan;

public class Character {
    protected String nama;
    protected int level;
    protected int health;

    public Character(String nama, int level, int health) {
        this.nama = nama;
        this.level = level;
        this.health = health;
    }

    public void attack(Character target) {
        target.health -= 10;
    }

    public void showStatus() {
        System.out.println("Nama   : " + nama);
        System.out.println("Level  : " + level);
        System.out.println("Health : " + health);
    }
}
```

### `Angel.java`

```java
package id.ac.polinema.inheritance.pengayaan;

public class Angel extends Character {
    private int potion;

    public Angel(String nama, int level, int health, int potion) {
        super(nama, level, health);
        this.potion = potion;
    }

    public void cure(Character target) {
        if (potion > 0) {
            target.health = 100;
            potion--;
        }
    }
}
```

### `Human.java`

```java
package id.ac.polinema.inheritance.pengayaan;

public class Human extends Character {
    private int strength;

    public Human(String nama, int level, int health, int strength) {
        super(nama, level, health);
        this.strength = strength;
    }

    public void specialAttack(Character target) {
        target.health -= (10 + strength);
    }
}
```

### `Wizard.java`

```java
package id.ac.polinema.inheritance.pengayaan;

public class Wizard extends Character {
    private int spell;

    public Wizard(String nama, int level, int health, int spell) {
        super(nama, level, health);
        this.spell = spell;
    }

    public void magic(Character target) {
        if (spell > 0) {
            target.health -= 50;
            spell--;
        }
    }
}
```

### `MainTugas3.java`

```java
package id.ac.polinema.inheritance.pengayaan;

public class MainTugas3 {
    public static void main(String[] args) {
        Angel esther = new Angel("Esther", 10, 100, 5);
        Human jackal = new Human("Jackal", 13, 100, 7);
        Wizard quistis = new Wizard("Quistis", 20, 100, 3);

        System.out.println("Begin game...");
        esther.showStatus();
        jackal.showStatus();
        quistis.showStatus();

        System.out.println("Jackal special attack to quistis, "
                + "quistis cast magic to jackal,");
        System.out.println("esther cure jackal, quistis attack esther...");
        jackal.specialAttack(quistis);
        quistis.magic(jackal);
        esther.cure(jackal);
        quistis.attack(esther);

        esther.showStatus();
        jackal.showStatus();
        quistis.showStatus();
    }
}
```

## 10.2 Output (setelah serangan)

```text
Nama   : Esther
Level  : 10
Health : 90
Nama   : Jackal
Level  : 13
Health : 100
Nama   : Quistis
Level  : 20
Health : 83
```

Penelusuran: `jackal.specialAttack(quistis)` → Quistis `100 - (10 + 7) = 83`; `quistis.magic(jackal)` → Jackal `100 - 50 = 50`; `esther.cure(jackal)` → Jackal kembali `100`; `quistis.attack(esther)` → Esther `100 - 10 = 90`. Hasil akhir sesuai checkpoint: **Esther 90, Jackal 100, Quistis 83**.

> **Info:** nama `Character` menimpa class bawaan `java.lang.Character`. Pada proyek nyata sebaiknya dipakai nama lain (mis. `Karakter`); di sini dipertahankan agar sesuai jobsheet.

---

# 11. Tugas 4 — Jawab Singkat

### 1. Perbedaan hubungan *is-a* (inheritance) dan *has-a* (aggregation/composition)

- **is-a (inheritance):** subclass *adalah* sebuah superclass; hubungan dinyatakan dengan `extends`. Contoh pada jobsheet ini: `Dosen` **adalah** `Pegawai` (Tugas 1) dan `Tabung` **adalah** `Bangun` (Percobaan 3).
- **has-a (aggregation/composition):** sebuah objek *memiliki* objek lain yang disimpan sebagai atribut. Contoh pada jobsheet ini: `DaftarGaji` **memiliki** sekumpulan `Pegawai` dalam array (Tugas 1, agregasi).

### 2. Ringkasan aturan pewarisan

Member **`private`** tidak diwariskan sehingga tidak dapat diakses langsung oleh subclass; untuk keperluan itu disediakan getter/setter `public`. Member **`protected`** diwariskan dan dapat diakses subclass—termasuk subclass di package berbeda—tetapi tidak oleh class lain di luar hierarki. **Konstruktor tidak diwariskan**, namun konstruktor superclass selalu dijalankan lebih dulu saat objek subclass dibuat. Karena itu subclass wajib memanggil konstruktor superclass yang sesuai dengan `super(...)`, dan `super(...)` harus menjadi baris pertama konstruktor. Dengan cara ini bagian superclass objek selesai diinisialisasi sebelum subclass menambahkan state-nya sendiri.

---

# 12. Kesimpulan

1. `extends` membuat subclass mewarisi member superclass, sehingga kode tidak ditulis berulang (reusable).
2. Member `private` tidak diwariskan; `protected` dapat diakses subclass (bahkan beda package); `public` dapat diakses siapa saja. Enkapsulasi terbaik dicapai dengan `private` + getter/setter.
3. `this` merujuk member class sendiri, `super` merujuk member superclass; `super` wajib saat terjadi *shadowing* atau saat memanggil versi method superclass pada *overriding*.
4. Konstruktor superclass selalu dijalankan lebih dulu, baik implisit maupun lewat `super(...)`; `super(...)` harus di baris pertama. Pada *multilevel inheritance*, satu objek memicu rantai konstruktor dari class paling dasar.
5. `@Override` membantu compiler memverifikasi penimpaan method, sehingga kesalahan nama/tanda tangan terdeteksi lebih dini.
6. Pewarisan (*is-a*) dapat dikombinasikan dengan agregasi (*has-a*), seperti pada Tugas 1 (`Dosen` adalah `Pegawai`, `DaftarGaji` memiliki kumpulan `Pegawai`).
