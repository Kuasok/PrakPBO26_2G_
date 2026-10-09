# LAPORAN JOBSHEET 7 — OVERLOADING DAN OVERRIDING

## Identitas

- **Nama** : Muhammad Ferdi Afiyanto
- **NIM** : 254107020122
- **Kelas** : 2G
- **Mata Kuliah** : Praktikum Pemrograman Berbasis Objek
- **Pertemuan** : 7
- **Materi** : Method Overloading dan Method Overriding

---

# 1. Tujuan

1. Memahami konsep *overloading* dan *overriding*.
2. Memahami perbedaan keduanya.
3. Tepat mengidentifikasi method *overloading* dan *overriding* pada suatu source code.
4. Mempraktikkan instruksi pada jobsheet.
5. Mengimplementasikan method *overloading* dan *overriding*.

# 2. Dasar Teori Singkat

### Overloading

*Overloading* adalah menuliskan kembali method dengan **nama sama** pada satu class (atau
antara parent dan subclass) dengan **daftar parameter berbeda**. Syaratnya:

- Nama method harus sama.
- Daftar parameter harus berbeda (jumlah, tipe, atau urutan).
- Return type boleh sama, boleh berbeda.

Perbedaan **penamaan variabel** parameter **tidak** membuat dua method dianggap berbeda.

### Overriding

*Overriding* adalah subclass yang mendeklarasikan ulang method milik superclass untuk
memodifikasi tingkah lakunya. Kesamaan yang wajib dipenuhi: **nama**, **return type**
(boleh *covariant*, yaitu subclass dari return type asli), dan **daftar parameter**
(jumlah, tipe, urutan). Aturannya:

- Mode akses overriding method harus sama atau lebih luas.
- Subclass hanya boleh meng-override satu kali, tidak boleh ada dua method dengan tanda
  tangan persis sama.
- Overriding method tidak boleh melempar *checked exception* yang tidak dideklarasikan
  overridden method.

| Aspek | Overloading | Overriding |
|---|---|---|
| Nama method | sama | sama |
| Daftar parameter | **berbeda** | **sama** |
| Class | satu class / parent-subclass | subclass menimpa superclass |
| Return type | bebas | sama / covariant |
| Kaitan | *compile-time* (static) | *runtime* (*dynamic dispatch*) |

---

# 3. Percobaan 1 — Karyawan, Staff, Manager, Utama

Untuk kasus ini `Karyawan` menjadi superclass bagi `Manager` dan `Staff`. Masing-masing
subclass menghitung gaji dengan caranya sendiri.

## 3.1 Kode Program

### `Karyawan.java`

```java
package id.ac.polinema.overloading.percobaan1;

public class Karyawan {
    private String nama;
    private String nip;
    private String golongan;
    private double gaji;

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setGolongan(String golongan) {
        this.golongan = golongan;

        switch (golongan.charAt(0)) {
            case '1':
                this.gaji = 5000000;
                break;
            case '2':
                this.gaji = 3000000;
                break;
            case '3':
                this.gaji = 2000000;
                break;
            case '4':
                this.gaji = 1000000;
                break;
            case '5':
                this.gaji = 750000;
                break;
        }
    }

    public void setGaji(double gaji) {
        this.gaji = gaji;
    }

    public String getNama() {
        return nama;
    }

    public String getNip() {
        return nip;
    }

    public String getGolongan() {
        return golongan;
    }

    public double getGaji() {
        return gaji;
    }
}
```

### `Staff.java`

```java
package id.ac.polinema.overloading.percobaan1;

public class Staff extends Karyawan {
    private int lembur;
    private double gajiLembur;

    public void setLembur(int lembur) {
        this.lembur = lembur;
    }

    public int getLembur() {
        return lembur;
    }

    public void setGajiLembur(double gajiLembur) {
        this.gajiLembur = gajiLembur;
    }

    public double getGajiLembur() {
        return gajiLembur;
    }

    // Overloading: nama method sama dengan getGaji(), daftar parameter berbeda.
    public double getGaji(int lembur, double gajiLembur) {
        return super.getGaji() + lembur * gajiLembur;
    }

    // Overriding: menimpa getGaji() milik superclass Karyawan.
    @Override
    public double getGaji() {
        return super.getGaji() + lembur * gajiLembur;
    }

    public void lihatInfo() {
        System.out.println("NIP :" + this.getNip());
        System.out.println("Nama :" + this.getNama());
        System.out.println("Golongan :" + this.getGolongan());
        System.out.println("Jml Lembur :" + this.getLembur());
        System.out.printf("Gaji Lembur :%.0f\n", this.getGajiLembur());
        System.out.printf("Gaji :%.0f\n", this.getGaji());
    }
}
```

### `Manager.java`

```java
package id.ac.polinema.overloading.percobaan1;

public class Manager extends Karyawan {
    private double tunjangan;
    private String bagian;
    private Staff st[];

    public void setTunjangan(double tunjangan) {
        this.tunjangan = tunjangan;
    }

    public double getTunjangan() {
        return tunjangan;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getBagian() {
        return bagian;
    }

    public void setStaff(Staff st[]) {
        this.st = st;
    }

    public void viewStaff() {
        int i;
        System.out.println("-------------------");
        for (i = 0; i < st.length; i++) {
            st[i].lihatInfo();
        }
        System.out.println("-------------------");
    }

    public void lihatInfo() {
        System.out.println("Manager :" + this.getBagian());
        System.out.println("NIP :" + this.getNip());
        System.out.println("Nama :" + this.getNama());
        System.out.println("Golongan :" + this.getGolongan());
        System.out.printf("Tunjangan :%.0f\n", this.getTunjangan());
        System.out.printf("Gaji :%.0f\n", this.getGaji());
        System.out.println("Bagian :" + this.getBagian());
        this.viewStaff();
    }

    // Overriding: menimpa getGaji() milik superclass Karyawan.
    @Override
    public double getGaji() {
        return super.getGaji() + tunjangan;
    }
}
```

### `Utama.java`

```java
package id.ac.polinema.overloading.percobaan1;

public class Utama {
    public static void main(String[] args) {
        System.out.println("Program Testing Class Manager & Staff");
        Manager man[] = new Manager[2];
        Staff staff1[] = new Staff[2];
        Staff staff2[] = new Staff[3];

        // pembuatan manager
        man[0] = new Manager();
        man[0].setNama("Tedjo");
        man[0].setNip("101");
        man[0].setGolongan("1");
        man[0].setTunjangan(5000000);
        man[0].setBagian("Administrasi");

        man[1] = new Manager();
        man[1].setNama("Atika");
        man[1].setNip("102");
        man[1].setGolongan("1");
        man[1].setTunjangan(2500000);
        man[1].setBagian("Pemasaran");

        staff1[0] = new Staff();
        staff1[0].setNama("Usman");
        staff1[0].setNip("0003");
        staff1[0].setGolongan("2");
        staff1[0].setLembur(10);
        staff1[0].setGajiLembur(10000);

        staff1[1] = new Staff();
        staff1[1].setNama("Anugrah");
        staff1[1].setNip("0005");
        staff1[1].setGolongan("2");
        staff1[1].setLembur(10);
        staff1[1].setGajiLembur(55000);
        man[0].setStaff(staff1);

        staff2[0] = new Staff();
        staff2[0].setNama("Hendra");
        staff2[0].setNip("0004");
        staff2[0].setGolongan("3");
        staff2[0].setLembur(15);
        staff2[0].setGajiLembur(5500);

        staff2[1] = new Staff();
        staff2[1].setNama("Arie");
        staff2[1].setNip("0006");
        staff2[1].setGolongan("4");
        staff2[1].setLembur(5);
        staff2[1].setGajiLembur(100000);

        staff2[2] = new Staff();
        staff2[2].setNama("Mentari");
        staff2[2].setNip("0007");
        staff2[2].setGolongan("3");
        staff2[2].setLembur(6);
        staff2[2].setGajiLembur(20000);
        man[1].setStaff(staff2);

        // cetak informasi dari manager + staffnya
        man[0].lihatInfo();
        man[1].lihatInfo();
    }
}
```

## 3.2 Output

```text
Program Testing Class Manager & Staff
Manager :Administrasi
NIP :101
Nama :Tedjo
Golongan :1
Tunjangan :5000000
Gaji :10000000
Bagian :Administrasi
-------------------
NIP :0003
Nama :Usman
Golongan :2
Jml Lembur :10
Gaji Lembur :10000
Gaji :3100000
NIP :0005
Nama :Anugrah
Golongan :2
Jml Lembur :10
Gaji Lembur :55000
Gaji :3550000
-------------------
Manager :Pemasaran
NIP :102
Nama :Atika
Golongan :1
Tunjangan :2500000
Gaji :7500000
Bagian :Pemasaran
-------------------
NIP :0004
Nama :Hendra
Golongan :3
Jml Lembur :15
Gaji Lembur :5500
Gaji :2082500
NIP :0006
Nama :Arie
Golongan :4
Jml Lembur :5
Gaji Lembur :100000
Gaji :1500000
NIP :0007
Nama :Mentari
Golongan :3
Jml Lembur :6
Gaji Lembur :20000
Gaji :2120000
-------------------
```

## 3.3 Penelusuran

- **Tedjo** (golongan 1): `getGaji()` di `Manager` = `super.getGaji() + tunjangan`
  = `5000000 + 5000000 = 10000000`.
- **Usman** (golongan 2, lembur 10): `getGaji()` di `Staff` = `3000000 + (10 × 10000) = 3100000`.
- **Hendra** (golongan 3, lembur 15): `2000000 + (15 × 5500) = 2082500`.

Di sinilah terlihat keduanya: pada `Staff` terdapat **overloading** (`getGaji(int, double)`
di samping `getGaji()`) sekaligus **overriding** (`getGaji()` menimpa milik `Karyawan`),
sedangkan pada `Manager` hanya terjadi **overriding** `getGaji()`.

---

# 4. Latihan — Analisis Source Coding

## 4.1 Overloading jumlah parameter

### Kode Program (`latihan1/PerkalianKu.java`)

```java
package id.ac.polinema.overloading.latihan1;

public class PerkalianKu {
    void perkalian(int a, int b) {
        System.out.println(a * b);
    }

    void perkalian(int a, int b, int c) {
        System.out.println(a * b * c);
    }

    public static void main(String args[]) {
        PerkalianKu objek = new PerkalianKu();
        objek.perkalian(25, 43);
        objek.perkalian(34, 23, 56);
    }
}
```

### Output

```text
1075
43792
```

### Jawaban

**4.1** Overloading terletak pada **class `PerkalianKu`**, yaitu pada dua method
`perkalian(int a, int b)` dan `perkalian(int a, int b, int c)`. Keduanya memiliki **nama
method yang sama** (`perkalian`) tetapi **daftar parameter berbeda**.

**4.2** Ada **2 method** dengan **jumlah parameter berbeda**: method pertama berjumlah
**2 parameter** (`int a, int b`), method kedua berjumlah **3 parameter** (`int a, int b, int c`).
Jumlah parameter yang berbeda = **dua** (2 dan 3), sehingga tercipta 2 versi method.

## 4.2 Overloading tipe parameter

### Kode Program (`latihan2/PerkalianKu.java`)

```java
package id.ac.polinema.overloading.latihan2;

public class PerkalianKu {
    void perkalian(int a, int b) {
        System.out.println(a * b);
    }

    void perkalian(double a, double b) {
        System.out.println(a * b);
    }

    public static void main(String args[]) {
        PerkalianKu objek = new PerkalianKu();
        objek.perkalian(25, 43);
        objek.perkalian(34.56, 23.7);
    }
}
```

### Output

```text
1075
819.072
```

### Jawaban

**4.3** Overloading terletak pada **class `PerkalianKu`**, yaitu pada method
`perkalian(int a, int b)` dan `perkalian(double a, double b)`. Nama method sama dan jumlah
parameter sama (dua), tetapi **tipe data parameternya berbeda**.

**4.4** Terdapat **2 tipe parameter yang berbeda**: `int` dan `double`. Pemanggilan
`objek.perkalian(25, 43)` memanggil versi `int`, sedangkan `objek.perkalian(34.56, 23.7)`
memanggil versi `double`.

## 4.3 Overriding

### Kode Program (`latihan3/`)

```java
package id.ac.polinema.overloading.latihan3;

public class Ikan {
    public void swim() {
        System.out.println("Ikan bisa berenang");
    }
}
```

```java
package id.ac.polinema.overloading.latihan3;

public class Piranha extends Ikan {
    @Override
    public void swim() {
        System.out.println("Piranha bisa makan daging");
    }
}
```

```java
package id.ac.polinema.overloading.latihan3;

public class Fish {
    public static void main(String[] args) {
        Ikan a = new Ikan();
        Ikan b = new Piranha();
        a.swim();
        b.swim();
    }
}
```

### Output

```text
Ikan bisa berenang
Piranha bisa makan daging
```

### Jawaban

**4.5** Overriding terletak pada **class `Piranha`**, yaitu method `swim()`. Method
`swim()` milik `Ikan` (*overridden method*) dideklarasikan ulang di `Piranha` (*overriding
method*) dengan nama, return type (`void`), dan daftar parameter (tanpa parameter) yang sama.

**4.6** Penjabaran alur program pada `Fish`:

1. `Ikan a = new Ikan();` lalu `a.swim();` → karena objeknya benar-benar `Ikan`, dipanggil
   `swim()` milik `Ikan` → mencetak **"Ikan bisa berenang"**.
2. `Ikan b = new Piranha();` → referensi bertipe `Ikan`, tetapi objek sebenarnya `Piranha`.
3. `b.swim();` → Java memilih method berdasarkan **tipe objek saat runtime**
   (*dynamic method dispatch*), bukan tipe referensinya. Karena objeknya `Piranha`, yang
   dijalankan adalah `swim()` hasil overriding → mencetak **"Piranha bisa makan daging"**.

Jadi meskipun `a` dan `b` bertipe sama (`Ikan`), hasilnya berbeda: inilah bukti overriding
bekerja secara polimorfik pada saat runtime.

---

# 5. Tugas

## 5.1 Tugas 1 — Overloading pada class `Segitiga`

### Kode Program

`tugas1/Segitiga.java`

```java
package id.ac.polinema.overloading.tugas1;

public class Segitiga {
    private int sudut;

    // Sudut ketiga diketahui dari satu sudut: sudut = 180 - sudutA
    public int totalSudut(int sudutA) {
        this.sudut = 180 - sudutA;
        return this.sudut;
    }

    // Sudut ketiga diketahui dari dua sudut: sudut = 180 - (sudutA + sudutB)
    public int totalSudut(int sudutA, int sudutB) {
        this.sudut = 180 - (sudutA + sudutB);
        return this.sudut;
    }

    // Keliling bila ketiga sisi diketahui: keliling = sisiA + sisiB + sisiC
    public int keliling(int sisiA, int sisiB, int sisiC) {
        return sisiA + sisiB + sisiC;
    }

    // Keliling bila hanya dua sisi diketahui (segitiga siku-siku):
    // c = akar(a^2 + b^2), lalu keliling = sisiA + sisiB + c
    public double keliling(int sisiA, int sisiB) {
        double sisiC = Math.sqrt(Math.pow(sisiA, 2) + Math.pow(sisiB, 2));
        return sisiA + sisiB + sisiC;
    }
}
```

`tugas1/MainTugas1.java`

```java
package id.ac.polinema.overloading.tugas1;

public class MainTugas1 {
    public static void main(String[] args) {
        Segitiga segitiga = new Segitiga();

        System.out.println("== Overloading totalSudut ==");
        System.out.println("totalSudut(60)       : " + segitiga.totalSudut(60));
        System.out.println("totalSudut(90, 45)   : " + segitiga.totalSudut(90, 45));

        System.out.println("== Overloading keliling ==");
        System.out.println("keliling(3, 4, 5)    : " + segitiga.keliling(3, 4, 5));
        System.out.printf("keliling(3, 4)       : %.2f%n", segitiga.keliling(3, 4));
    }
}
```

### Output

```text
== Overloading totalSudut ==
totalSudut(60)       : 120
totalSudut(90, 45)   : 45
== Overloading keliling ==
keliling(3, 4, 5)    : 12
keliling(3, 4)       : 12.00
```

### Penjelasan

- `totalSudut(int)` meng-overload `totalSudut(int, int)`: nama sama, jumlah parameter berbeda.
- `keliling(int, int, int)` meng-overload `keliling(int, int)`: nama sama, jumlah parameter
  berbeda. Versi dua parameter memakai rumus Pythagoras untuk mencari sisi miring, sesuai
  catatan `c = √(a² + b²)` pada diagram.
- Pemanggilan `keliling(3, 4)` menghasilkan `3 + 4 + 5 = 12.00` (sisi miring `5` dari
  `√(3² + 4²)`), sedangkan `keliling(3, 4, 5)` menghasilkan `12` bertipe `int`.

## 5.2 Tugas 2 — Overriding dengan *dynamic method dispatch*

### Kode Program

`tugas2/Manusia.java`

```java
package id.ac.polinema.overloading.tugas2;

public class Manusia {
    public void bernafas() {
        System.out.println("Manusia bernafas menggunakan paru-paru");
    }

    public void makan() {
        System.out.println("Manusia makan nasi");
    }
}
```

`tugas2/Dosen.java`

```java
package id.ac.polinema.overloading.tugas2;

public class Dosen extends Manusia {
    @Override
    public void makan() {
        System.out.println("Dosen makan di kantin kampus");
    }

    public void lembur() {
        System.out.println("Dosen lembur mengoreksi tugas mahasiswa");
    }
}
```

`tugas2/Mahasiswa.java`

```java
package id.ac.polinema.overloading.tugas2;

public class Mahasiswa extends Manusia {
    @Override
    public void makan() {
        System.out.println("Mahasiswa makan di kantin kampus");
    }

    public void tidur() {
        System.out.println("Mahasiswa tidur di kos");
    }
}
```

`tugas2/MainTugas2.java`

```java
package id.ac.polinema.overloading.tugas2;

public class MainTugas2 {
    public static void main(String[] args) {
        // Dynamic method dispatch: variabel bertipe Manusia menunjuk objek subclass.
        Manusia manusia = new Manusia();
        Manusia dosen = new Dosen();
        Manusia mahasiswa = new Mahasiswa();

        System.out.println("== Pemanggilan lewat referensi Manusia ==");
        manusia.makan();
        dosen.makan();
        mahasiswa.makan();

        System.out.println("== Perilaku yang diwarisi tetap dapat dipakai ==");
        dosen.bernafas();
        mahasiswa.bernafas();

        System.out.println("== Method khas subclass (butuh downcast) ==");
        ((Dosen) dosen).lembur();
        ((Mahasiswa) mahasiswa).tidur();
    }
}
```

### Output

```text
== Pemanggilan lewat referensi Manusia ==
Manusia makan nasi
Dosen makan di kantin kampus
Mahasiswa makan di kantin kampus
== Perilaku yang diwarisi tetap dapat dipakai ==
Manusia bernafas menggunakan paru-paru
Manusia bernafas menggunakan paru-paru
== Method khas subclass (butuh downcast) ==
Dosen lembur mengoreksi tugas mahasiswa
Mahasiswa tidur di kos
```

### Penjelasan

- `Dosen` dan `Mahasiswa` adalah subclass `Manusia` dan meng-override method `makan()`.
- Ketiga variabel dideklarasikan bertipe `Manusia`, tetapi objeknya berbeda. Saat `makan()`
  dipanggil, Java menentukan versi method berdasarkan **tipe objek sebenarnya pada runtime**
  — inilah *dynamic method dispatch* (polimorfisme *runtime*).
- Method `bernafas()` tidak di-override (`final`-nya perilaku), sehingga versi `Manusia`
  yang dipakai oleh semua.
- Method khas subclass (`lembur()`, `tidur()`) tidak terlihat dari referensi `Manusia`,
  sehingga perlu **downcast** agar dapat dipanggil.

---

# 6. Kesimpulan

1. **Overloading** menyediakan banyak method dengan nama sama tetapi daftar parameter
   berbeda (jumlah/tipe/urutan); pemilihannya dilakukan saat **compile-time**.
2. **Overriding** mendeklarasikan ulang method superclass di subclass dengan tanda tangan
   yang sama agar perilakunya lebih spesifik; pemilihannya dilakukan saat **runtime**
   (*dynamic method dispatch*).
3. Perbedaan nama variabel parameter **tidak** membuat method dianggap overload; yang
   menentukan adalah tipe dan jumlah/urutan parameter.
4. Subclass dapat memanggil versi asli superclass melalui `super.method()` saat melakukan
   overriding, seperti `super.getGaji()` pada `Staff` dan `Manager`.
5. Overloading dan overriding dapat terjadi bersamaan dalam satu subclass, contohnya
   `Staff` yang memiliki `getGaji(int, double)` (overload) sekaligus `getGaji()` (override).
6. `@Override` membantu compiler memverifikasi bahwa method benar-benar menimpa method
   superclass, sehingga kesalahan tanda tangan terdeteksi lebih dini.
