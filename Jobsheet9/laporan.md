# LAPORAN JOBSHEET 9 — POLIMORFISME

## Identitas

- **Nama** : Muhammad Ferdi Afiyanto
- **NIM** : 254107020122
- **Kelas** : 2G
- **Mata Kuliah** : Praktikum Pemrograman Berbasis Objek
- **Pertemuan** : 9
- **Materi** : Polimorfisme, Virtual Method Invocation, `instanceof`, dan Object Casting

---

# 1. Tujuan

1. Menjelaskan konsep dan bentuk dasar polimorfisme, yaitu referensi bertipe superclass
   atau interface yang menunjuk objek subclass atau implementor.
2. Menjelaskan *virtual method invocation* dan membedakan tipe referensi (saat kompilasi)
   dari tipe objek (saat dijalankan).
3. Menerapkan polimorfisme pada *heterogeneous collection*, yaitu array yang berisi
   berbagai jenis objek.
4. Menerapkan polimorfisme pada parameter method (*polymorphic argument*).
5. Menggunakan operator `instanceof` dan *object casting* (upcasting dan downcasting)
   secara aman.

# 2. Dasar Teori Singkat

### Polimorfisme

Polimorfisme berarti satu objek dapat dipandang dalam banyak bentuk. Bentuk yang paling
umum adalah variabel bertipe superclass atau interface dipakai untuk menunjuk objek dari
subclass (relasi *is-a*) atau objek dari class yang meng-`implements` interface tersebut.

Sebuah referensi bertipe `T` boleh menunjuk objek bertipe `T` atau turunan `T`:
subclass bila `T` berupa class, atau implementor bila `T` berupa interface. Pemeriksaan
dilakukan oleh compiler berdasarkan deklarasi tipe.

### Tipe referensi dan tipe objek

- **Tipe referensi** adalah tipe yang tertulis pada deklarasi variabel. Tipe ini dikenal
  compiler dan menentukan anggota (atribut/method) apa yang boleh diakses.
- **Tipe objek** adalah class sebenarnya dari objek yang ditunjuk saat program berjalan.
  Tipe ini menentukan versi method hasil *overriding* yang dijalankan.

### Virtual method invocation dan dynamic method dispatch

*Virtual method invocation* adalah pemanggilan method hasil *overriding* melalui referensi
polimorfik. Disebut *virtual* karena method yang dikenali compiler (berdasarkan tipe
referensi) dapat berbeda dari method yang dijalankan JVM (berdasarkan tipe objek).
Pemilihan versi method saat program berjalan disebut *dynamic method dispatch*. Berbeda
dengan *overloading* yang dipilih saat kompilasi.

### Heterogeneous collection

Array bertipe superclass atau interface dapat berisi objek dari berbagai subclass atau
implementor. Array seperti ini disebut *heterogeneous collection*. Prinsip yang sama
berlaku pada koleksi lain, misalnya `ArrayList<Employee>`.

### Polymorphic argument

Parameter method yang bertipe superclass atau interface dapat menerima objek dari berbagai
subclass/implementor. Method cukup bergantung pada kontrak tipe tersebut, sehingga tidak
perlu menambah parameter untuk setiap jenis objek.

### `instanceof`, upcasting, dan downcasting

- **Upcasting** (subclass ke superclass/interface) terjadi otomatis dan selalu aman.
- **Downcasting** (superclass/interface ke subclass) harus ditulis eksplisit dengan tanda
  kurung, misalnya `(ElectricityBill) p`, dan hanya aman bila objek aslinya memang bertipe
  tersebut.
- `instanceof` memeriksa jenis objek sebenarnya. Karena itu downcasting sebaiknya
  didahului pemeriksaan `instanceof` agar tidak terjadi `ClassCastException`.

### Abstract class dan interface

Class abstract tidak dapat diinstansiasi, dan method abstract tidak memiliki isi serta
wajib di-override oleh subclass konkret. Interface adalah kontrak berisi method tanpa isi;
class yang meng-`implements` interface wajib mengisi semua method-nya. Bila class abstract
meng-implements interface tanpa mengisi method-nya, kewajiban tersebut diteruskan ke
subclass konkret.

---

# 3. Struktur Package

Kode diletakkan pada tiga package sesuai jobsheet:

```text
Jobsheet9/
├── src/id/ac/polinema/polimorfisme/
│   ├── gambaran/     # GambaranDasar
│   ├── pegawai/      # Percobaan 1-4
│   └── zombie/       # Tugas 1-3
├── out/              # output tiap checkpoint + pesan error
├── laporan.md
└── README.md
```

Kompilasi dan eksekusi:

```bash
cd Jobsheet9
mkdir -p build
javac -d build $(find src -name "*.java")

java -cp build id.ac.polinema.polimorfisme.gambaran.GambaranDasar
java -cp build id.ac.polinema.polimorfisme.pegawai.MainPercobaan1
java -cp build id.ac.polinema.polimorfisme.pegawai.MainPercobaan2
java -cp build id.ac.polinema.polimorfisme.pegawai.MainPercobaan3
java -cp build id.ac.polinema.polimorfisme.pegawai.MainPercobaan4
java -cp build id.ac.polinema.polimorfisme.zombie.MainTugas1
java -cp build id.ac.polinema.polimorfisme.zombie.MainTugas2
java -cp build id.ac.polinema.polimorfisme.zombie.Tugas3Bukti
```

Seluruh output checkpoint tersimpan di folder `out/`, sedangkan pesan error seluruh
eksperimen tersimpan di `out/pesan-error.txt`. Proyek diuji dengan OpenJDK 27.

---

# 4. Percobaan 1 — Bentuk Dasar Polimorfisme

## 4.1 Gambaran Konsep

`Deer` dan `Lion` mewarisi `Animal`, tetapi hanya `Deer` yang meng-implements
`Vegetarian`. Karena itu `Deer` dapat ditunjuk oleh referensi `Animal` maupun
`Vegetarian`, sedangkan `Lion` hanya oleh `Animal`.

### Kode Program

`gambaran/GambaranDasar.java`

```java
package id.ac.polinema.polimorfisme.gambaran;

public class GambaranDasar {
    interface Vegetarian { }
    static class Animal { }
    static class Deer extends Animal implements Vegetarian { }
    static class Lion extends Animal { }

    public static void main(String[] args) {
        Deer d = new Deer();
        Lion l = new Lion();

        Animal a = d;           // valid: Deer adalah Animal
        Animal a2 = l;          // valid: Lion adalah Animal
        Vegetarian v = d;       // valid: Deer implements Vegetarian
        // Vegetarian v2 = l;   // tidak valid: Lion tidak implements Vegetarian

        System.out.println("a     menunjuk objek " + a.getClass().getSimpleName());
        System.out.println("a2 menunjuk objek " + a2.getClass().getSimpleName());
        System.out.println("v     menunjuk objek " + v.getClass().getSimpleName());
    }
}
```

### Output

```text
a     menunjuk objek Deer
a2 menunjuk objek Lion
v     menunjuk objek Deer
```

### Tabel Pengamatan Langkah 4

| Perubahan | Pesan error |
|---|---|
| Aktifkan baris `Vegetarian v2 = l;` | `incompatible types: Lion cannot be converted to Vegetarian` |

Pesan lengkap:

```text
src/id/ac/polinema/polimorfisme/gambaran/GambaranDasar.java:16: error: incompatible types: Lion cannot be converted to Vegetarian
        Vegetarian v2 = l;   // tidak valid: Lion tidak implements Vegetarian
                        ^
1 error
```

## 4.2 Studi Kasus Pegawai

Pada studi kasus ini `PermanentEmployee` adalah sebuah `Employee` sekaligus sebuah
`Payable`, `ElectricityBill` hanya sebuah `Payable`, dan `InternshipEmployee` hanya
sebuah `Employee`.

### `Employee.java`

```java
package id.ac.polinema.polimorfisme.pegawai;

public class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getEmployeeInfo() {
        return "Name = " + name;
    }
}
```

### `Payable.java`

```java
package id.ac.polinema.polimorfisme.pegawai;

public interface Payable {
    int getPaymentAmount();
}
```

### `InternshipEmployee.java`

```java
package id.ac.polinema.polimorfisme.pegawai;

public class InternshipEmployee extends Employee {
    private int length;

    public InternshipEmployee(String name, int length) {
        super(name);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String getEmployeeInfo() {
        String info = super.getEmployeeInfo() + "\n";
        info += "Registered as internship employee for " + length + " month/s\n";
        return info;
    }
}
```

### `PermanentEmployee.java`

```java
package id.ac.polinema.polimorfisme.pegawai;

public class PermanentEmployee extends Employee implements Payable {
    private int salary;

    public PermanentEmployee(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int getPaymentAmount() {
        return (int) (salary + 0.05 * salary);          // gaji + bonus 5%
    }

    @Override
    public String getEmployeeInfo() {
        String info = super.getEmployeeInfo() + "\n";
        info += "Registered as permanent employee with salary " + salary + "\n";
        return info;
    }
}
```

### `ElectricityBill.java`

```java
package id.ac.polinema.polimorfisme.pegawai;

public class ElectricityBill implements Payable {
    private int kwh;
    private String category;

    public ElectricityBill(int kwh, String category) {
        this.kwh = kwh;
        this.category = category;
    }

    public int getKwh() {
        return kwh;
    }

    public void setKwh(int kwh) {
        this.kwh = kwh;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int getPaymentAmount() {
        return kwh * getBasePrice();
    }

    public int getBasePrice() {
        return switch (category) {
            case "R-1" -> 100;
            case "R-2" -> 200;
            default -> 0;
        };
    }

    public String getBillInfo() {
        return "kWH = " + kwh + "\n"
                + "Category = " + category + " (" + getBasePrice() + " per kWH)\n";
    }
}
```

## 4.3 MainPercobaan1

### Kode Program

```java
package id.ac.polinema.polimorfisme.pegawai;

public class MainPercobaan1 {
    public static void main(String[] args) {
        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        ElectricityBill eBill = new ElectricityBill(5, "R-1");
        Employee e;
        Payable p;

        e = pEmp;
        System.out.println("e menunjuk objek " + e.getClass().getSimpleName());
        e = iEmp;
        System.out.println("e menunjuk objek " + e.getClass().getSimpleName());
        p = pEmp;
        System.out.println("p menunjuk objek " + p.getClass().getSimpleName());
        p = eBill;
        System.out.println("p menunjuk objek " + p.getClass().getSimpleName());
    }
}
```

### Output

```text
e menunjuk objek PermanentEmployee
e menunjuk objek InternshipEmployee
p menunjuk objek PermanentEmployee
p menunjuk objek ElectricityBill
```

### Tabel Pengamatan Langkah 10

| Tambahan pada `main` | Pesan error |
|---|---|
| `p = iEmp;` | `incompatible types: InternshipEmployee cannot be converted to Payable` |
| `e = eBill;` | `incompatible types: ElectricityBill cannot be converted to Employee` |

Pesan lengkap:

```text
src/id/ac/polinema/polimorfisme/pegawai/MainPercobaan1.java:20: error: incompatible types: InternshipEmployee cannot be converted to Payable
        p = iEmp;
            ^
src/id/ac/polinema/polimorfisme/pegawai/MainPercobaan1.java:21: error: incompatible types: ElectricityBill cannot be converted to Employee
        e = eBill;
            ^
2 errors
```

## 4.4 Jawaban Pertanyaan Percobaan 1

### 1. Class apa saja yang merupakan turunan dari class `Employee`?

`InternshipEmployee` dan `PermanentEmployee`.

```java
public class InternshipEmployee extends Employee { ... }
public class PermanentEmployee extends Employee implements Payable { ... }
```

`ElectricityBill` bukan turunan `Employee` karena hanya meng-implements `Payable`.

### 2. Class apa saja yang meng-implements interface `Payable`?

`PermanentEmployee` dan `ElectricityBill`.

```java
public class PermanentEmployee extends Employee implements Payable { ... }
public class ElectricityBill implements Payable { ... }
```

### 3. Mengapa `e` dapat diisi dengan objek `pEmp` dan `iEmp`?

Karena `e` bertipe `Employee`, sedangkan `pEmp` bertipe `PermanentEmployee` dan `iEmp`
bertipe `InternshipEmployee`. Keduanya adalah **turunan** `Employee`, sehingga memenuhi
relasi *is-a*. Assignment dari subclass ke superclass (*upcasting*) dilakukan otomatis
oleh compiler:

```java
Employee e;
e = pEmp;   // PermanentEmployee adalah Employee
e = iEmp;   // InternshipEmployee adalah Employee
```

### 4. Mengapa `p` dapat diisi dengan objek `pEmp` dan `eBill`?

Karena `p` bertipe `Payable`, yaitu interface dengan satu method `getPaymentAmount()`.
`PermanentEmployee` dan `ElectricityBill` sama-sama meng-implements interface tersebut,
sehingga keduanya adalah **bentuk** dari `Payable`:

```java
Payable p;
p = pEmp;    // PermanentEmployee implements Payable
p = eBill;   // ElectricityBill implements Payable
```

### 5. Apa penyebab error pada `p = iEmp;` dan `e = eBill;`?

Keduanya gagal karena tidak ada relasi tipe yang sah antara objek dan tipe referensi:

- `iEmp` bertipe `InternshipEmployee`. Class tersebut hanya turunan `Employee` dan
  **tidak** meng-implements `Payable`, sehingga bukan bentuk dari `Payable`.
- `eBill` bertipe `ElectricityBill`. Class tersebut hanya meng-implements `Payable` dan
  **tidak** turunan `Employee`, sehingga bukan bentuk dari `Employee`.

Penyebabnya sama dengan `Vegetarian v2 = l;` pada `GambaranDasar`: `Lion` tidak
meng-implements `Vegetarian`. Compiler menolak assignment karena tipe sumber bukan
subtype dari tipe tujuan, baik melalui `extends` maupun `implements`.

### 6. Pada `GambaranDasar`, variabel `a` bertipe `Animal`, tetapi `getClass()` menampilkan `Deer`. Apa beda tipe referensi dan tipe objek?

- **Tipe referensi** `a` adalah `Animal`. Ini tipe statis yang ditulis pada deklarasi dan
  dikenal compiler. Compiler hanya mengizinkan pemanggilan anggota yang dimiliki
  `Animal`.
- **Tipe objek** yang ditunjuk `a` adalah `Deer`. Ini tipe dinamis yang diketahui saat
  program berjalan. `getClass()` mengembalikan class sebenarnya, yaitu `Deer`.

Jadi `a` "berbentuk" `Animal` di mata compiler, tetapi objek nyatanya adalah `Deer`.
Tipe referensi menentukan apa yang boleh dipanggil, tipe objek menentukan implementasi
yang dijalankan.

### 7. Kesimpulan tentang konsep dan bentuk dasar polimorfisme

Polimorfisme memungkinkan satu variabel bertipe superclass atau interface menunjuk objek
dari berbagai subclass/implementor. Assignment hanya sah bila objek memenuhi relasi
*is-a* terhadap tipe referensi, yaitu subclass (`extends`) atau implementor
(`implements`). Compiler memeriksa hal itu, sehingga objek yang tidak memiliki hubungan
warisan/interface akan ditolak dengan `incompatible types`. Dengan polimorfisme, kode
cukup ditulis terhadap tipe umum, tetapi objek yang ditunjuk tetap menyimpan identitas
class aslinya.

---

# 5. Percobaan 2 — Virtual Method Invocation

## 5.1 Kode Program

`pegawai/MainPercobaan2.java`

```java
package id.ac.polinema.polimorfisme.pegawai;

public class MainPercobaan2 {
    public static void main(String[] args) {
        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        Employee e;
        e = pEmp;
        System.out.println(e.getEmployeeInfo());
        System.out.println("------------------");
        System.out.println(pEmp.getEmployeeInfo());

        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        e = iEmp;
        System.out.println("------------------");
        System.out.println(e.getEmployeeInfo());          // pernyataan sama, objek berbeda
    }
}
```

## 5.2 Output

```text
Name = Dedik
Registered as permanent employee with salary 500

------------------
Name = Dedik
Registered as permanent employee with salary 500

------------------
Name = Sunarto
Registered as internship employee for 5 month/s

```

Baris kosong muncul karena `getEmployeeInfo()` sudah mengakhiri teksnya dengan `\n`,
lalu hasilnya dicetak dengan `System.out.println()`.

## 5.3 Tabel Pengamatan Langkah 4

| Perubahan | Pesan error |
|---|---|
| Panggil `e.getSalary()` pada `e` bertipe `Employee` | `cannot find symbol ... method getSalary() ... location: variable e of type Employee` |

Pesan lengkap:

```text
src/id/ac/polinema/polimorfisme/pegawai/MainPercobaan2.java:14: error: cannot find symbol
        System.out.println(e.getSalary());
                            ^
  symbol:   method getSalary()
  location: variable e of type Employee
1 error
```

## 5.4 Penjelasan

Pada langkah 1, `e` menunjuk objek `PermanentEmployee`, sehingga pemanggilan
`e.getEmployeeInfo()` menjalankan versi `PermanentEmployee`, bukan versi `Employee`.
Hasilnya sama dengan `pEmp.getEmployeeInfo()` karena objek yang dipanggil memang sama.

Pada langkah 3, `e` diarahkan ke objek `InternshipEmployee`. Pernyataan
`e.getEmployeeInfo()` tidak berubah, tetapi method yang dijalankan berubah menjadi versi
`InternshipEmployee`. Inilah bukti *dynamic method dispatch*.

Pada langkah 4, compiler menolak `e.getSalary()` karena `getSalary()` tidak dideklarasikan
di `Employee`. Compiler hanya mengenal anggota milik tipe referensi, bukan anggota
subclass yang kebetulan menjadi objek nyata.

## 5.5 Jawaban Pertanyaan Percobaan 2

### 1. Mengapa `e.getEmployeeInfo()` dan `pEmp.getEmployeeInfo()` menghasilkan hasil yang sama?

Karena keduanya memanggil method yang sama pada objek yang sama. `e` dan `pEmp`
menunjuk objek `PermanentEmployee` yang sama, dan `PermanentEmployee` meng-override
`getEmployeeInfo()`. Saat `e.getEmployeeInfo()` dijalankan, JVM memilih versi
`PermanentEmployee` berdasarkan tipe objek, sehingga hasilnya identik dengan pemanggilan
langsung melalui `pEmp`.

### 2. Mengapa `e.getEmployeeInfo()` disebut pemanggilan method virtual, sedangkan `pEmp.getEmployeeInfo()` tidak?

Pada `e.getEmployeeInfo()`, tipe referensi adalah `Employee`, sedangkan objek nyatanya
bisa `PermanentEmployee`, `InternshipEmployee`, atau subclass lain. Compiler hanya tahu
method `Employee.getEmployeeInfo()`, sehingga JVM harus memilih implementasi berdasarkan
objek saat *runtime*. Pemilihan itulah yang disebut virtual dispatch.

Pada `pEmp.getEmployeeInfo()`, tipe referensi sudah `PermanentEmployee`, yaitu class yang
mendeklarasikan override. Tidak ada "jarak" antara tipe referensi dan implementasi yang
dipilih, sehingga pemanggilan tidak bergantung pada pemilihan versi lain saat runtime.
Secara teknis JVM tetap memakai mekanisme *virtual call*, tetapi tidak ada dispatch yang
mengubah hasil karena tipe referensi dan tipe objek berada pada level yang sama.

### 3. Jelaskan maksud virtual method invocation. Mengapa disebut virtual?

*Virtual method invocation* adalah pemanggilan method hasil overriding melalui referensi
polimorfik. Compiler memvalidasi pemanggilan berdasarkan tipe referensi, tetapi JVM
menjalankan method berdasarkan tipe objek sebenarnya. Pemilihan versi method saat runtime
ini disebut *dynamic method dispatch*.

Disebut *virtual* karena satu deklarasi method dapat memiliki banyak implementasi pada
subclass, dan implementasi yang dipakai tidak ditetapkan saat kompilasi. Hal ini berbeda
dengan *overloading* yang dipilih saat kompilasi berdasarkan tipe argumen.

### 4. Mengapa pernyataan `e.getEmployeeInfo()` yang sama menghasilkan keluaran berbeda pada langkah 3?

Karena isi variabel `e` berubah. Sebelumnya `e` menunjuk `PermanentEmployee`, kemudian
diisi dengan `iEmp` yang bertipe `InternshipEmployee`. Pernyataan yang dipanggil memang
sama, tetapi objek yang menerima panggilan berbeda. `InternshipEmployee` meng-override
`getEmployeeInfo()`, sehingga JVM menjalankan versi `InternshipEmployee`.

### 5. Mengapa `e.getSalary()` ditolak compiler padahal objek yang ditunjuk `e` adalah `PermanentEmployee`?

Karena compiler bekerja berdasarkan **tipe referensi**, bukan objek yang kebetulan diisi
saat runtime. `e` dideklarasikan sebagai `Employee`, dan `Employee` tidak memiliki method
`getSalary()`. Method itu hanya dideklarasikan di `PermanentEmployee`. Agar dapat
dipanggil, referensi harus di-*downcast* lebih dulu, misalnya:

```java
PermanentEmployee pe = (PermanentEmployee) e;
System.out.println(pe.getSalary());
```

---

# 6. Percobaan 3 — Heterogeneous Collection

## 6.1 Kode Program

`pegawai/MainPercobaan3.java`

```java
package id.ac.polinema.polimorfisme.pegawai;

public class MainPercobaan3 {
    public static void main(String[] args) {
        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        ElectricityBill eBill = new ElectricityBill(5, "R-1");

        Employee[] e = {pEmp, iEmp};
        Payable[] p = {pEmp, eBill};
        // Employee[] e2 = {pEmp, iEmp, eBill};             // tidak valid

        for (Employee emp : e) {
            System.out.println(emp.getEmployeeInfo());
        }

        int total = 0;
        for (Payable tagihan : p) {
            total += tagihan.getPaymentAmount();
        }
        System.out.println("Total tagihan bulan ini = " + total);
    }
}
```

## 6.2 Output

```text
Name = Dedik
Registered as permanent employee with salary 500

Name = Sunarto
Registered as internship employee for 5 month/s

Total tagihan bulan ini = 1025
```

Perhitungan total:

```text
PermanentEmployee : 500 + (5% × 500) = 500 + 25 = 525
ElectricityBill   : 5 kWH × 100 (R-1)        = 500
Total                                        = 1025
```

## 6.3 Tabel Pengamatan Langkah 3

| Perubahan | Pesan error |
|---|---|
| Aktifkan `Employee[] e2 = {pEmp, iEmp, eBill};` | `incompatible types: ElectricityBill cannot be converted to Employee` |

Pesan lengkap:

```text
src/id/ac/polinema/polimorfisme/pegawai/MainPercobaan3.java:11: error: incompatible types: ElectricityBill cannot be converted to Employee
        Employee[] e2 = {pEmp, iEmp, eBill};             // tidak valid
                                     ^
1 error
```

## 6.4 Jawaban Pertanyaan Percobaan 3

### 1. Mengapa array `e` dapat berisi `pEmp` dan `iEmp` yang bertipe berbeda?

Karena array `e` bertipe `Employee[]`, dan setiap elemen harus berupa `Employee` atau
turunannya. `PermanentEmployee` dan `InternshipEmployee` sama-sama turunan `Employee`,
sehingga keduanya sah menjadi elemen. Masing-masing elemen tetap menyimpan referensi ke
objek dengan class aslinya, sehingga saat `getEmployeeInfo()` dipanggil, versi method
masing-masing objek yang dijalankan.

### 2. Mengapa array `p` dapat berisi `pEmp` dan `eBill` yang bertipe berbeda?

Karena array `p` bertipe `Payable[]`, dan setiap elemen harus meng-implements `Payable`.
`PermanentEmployee` meng-implements `Payable` melalui deklarasi
`extends Employee implements Payable`, sedangkan `ElectricityBill` langsung
`implements Payable`. Keduanya memenuhi kontrak `getPaymentAmount()`, sehingga sah
menjadi elemen `Payable[]`.

### 3. Mengapa terjadi error pada `Employee[] e2 = {pEmp, iEmp, eBill};`?

Karena `eBill` bertipe `ElectricityBill`, yang **bukan** turunan `Employee`. Array
`Employee[]` hanya boleh berisi objek bertipe `Employee` atau subclass-nya. Meskipun
`ElectricityBill` meng-implements `Payable`, interface tersebut tidak membuatnya menjadi
`Employee`. Karena itu compiler menolak elemen ketiga dengan pesan
`incompatible types: ElectricityBill cannot be converted to Employee`.

### 4. Mengapa setiap elemen menghasilkan teks berbeda padahal pemanggilannya sama?

Karena `emp` bertipe `Employee`, tetapi objek yang ditunjuk berbeda:
`PermanentEmployee` pada elemen pertama dan `InternshipEmployee` pada elemen kedua.
Pemanggilan `emp.getEmployeeInfo()` adalah *virtual method invocation*, sehingga JVM
menjalankan override milik objek masing-masing. Prinsipnya sama dengan Percobaan 2:
pernyataan sama, objek berbeda, hasil berbeda.

---

# 7. Percobaan 4 — Polymorphic Argument, `instanceof`, dan Casting

## 7.1 Kode Program

### `pegawai/Owner.java`

```java
package id.ac.polinema.polimorfisme.pegawai;

public class Owner {
    public void pay(Payable p) {
        System.out.println("Total payment = " + p.getPaymentAmount());
        if (p instanceof ElectricityBill) {
            ElectricityBill eb = (ElectricityBill) p;
            System.out.println(eb.getBillInfo());
        } else if (p instanceof PermanentEmployee) {
            PermanentEmployee pe = (PermanentEmployee) p;
            System.out.println(pe.getEmployeeInfo());
        }
    }

    public void showMyEmployee(Employee e) {
        System.out.println(e.getEmployeeInfo());
        if (e instanceof PermanentEmployee) {
            System.out.println("You have to pay her/him monthly!!!");
        } else {
            System.out.println("No need to pay him/her :)");
        }
    }
}
```

### `pegawai/MainPercobaan4.java`

```java
package id.ac.polinema.polimorfisme.pegawai;

public class MainPercobaan4 {
    public static void main(String[] args) {
        Owner ow = new Owner();

        ElectricityBill eBill = new ElectricityBill(5, "R-1");
        ow.pay(eBill);                                // bayar tagihan listrik
        System.out.println("------------------------------");

        PermanentEmployee pEmp = new PermanentEmployee("Dedik", 500);
        ow.pay(pEmp);                                 // bayar pegawai tetap
        System.out.println("------------------------------");

        InternshipEmployee iEmp = new InternshipEmployee("Sunarto", 5);
        ow.showMyEmployee(pEmp);                      // info pegawai tetap
        System.out.println("------------------------------");
        ow.showMyEmployee(iEmp);                      // info pegawai magang
    }
}
```

## 7.2 Output

```text
Total payment = 500
kWH = 5
Category = R-1 (100 per kWH)

------------------------------
Total payment = 525
Name = Dedik
Registered as permanent employee with salary 500

------------------------------
Name = Dedik
Registered as permanent employee with salary 500

You have to pay her/him monthly!!!
------------------------------
Name = Sunarto
Registered as internship employee for 5 month/s

No need to pay him/her :)
```

## 7.3 Tabel Pengamatan Langkah 4

| Perubahan | Hasil |
|---|---|
| Tambahkan `ow.pay(iEmp);` di akhir `main` | Error kompilasi: `incompatible types: InternshipEmployee cannot be converted to Payable` |
| Buat class `Eksperimen4` lalu jalankan | Cetak `Sebelum casting`, lalu `ClassCastException`; teks `Sesudah casting` tidak pernah tercetak |

Pesan error kompilasi:

```text
src/id/ac/polinema/polimorfisme/pegawai/MainPercobaan4.java:20: error: incompatible types: InternshipEmployee cannot be converted to Payable
        ow.pay(iEmp);
               ^
1 error
```

Kode `Eksperimen4`:

```java
package id.ac.polinema.polimorfisme.pegawai;

/**
 * Class ini sengaja dipertahankan sebagai bukti eksperimen Langkah 4 Percobaan 4.
 * Kode lolos kompilasi, tetapi gagal saat dijalankan karena objek yang ditunjuk e
 * sebenarnya InternshipEmployee, bukan PermanentEmployee.
 */
public class Eksperimen4 {
    public static void main(String[] args) {
        Employee e = new InternshipEmployee("Sunarto", 5);
        System.out.println("Sebelum casting");
        PermanentEmployee pe = (PermanentEmployee) e;
        System.out.println("Sesudah casting: " + pe.getSalary());
    }
}
```

Hasil eksekusi:

```text
Sebelum casting
Exception in thread "main" java.lang.ClassCastException: class id.ac.polinema.polimorfisme.pegawai.InternshipEmployee cannot be cast to class id.ac.polinema.polimorfisme.pegawai.PermanentEmployee (id.ac.polinema.polimorfisme.pegawai.InternshipEmployee and id.ac.polinema.polimorfisme.pegawai.PermanentEmployee are in unnamed module of loader 'app')
	at id.ac.polinema.polimorfisme.pegawai.Eksperimen4.main(Eksperimen4.java:12)
```

## 7.4 Jawaban Pertanyaan Percobaan 4

### 1. Mengapa `ow.pay(eBill)` dan `ow.pay(pEmp)` dapat dilakukan padahal parameter `pay()` bertipe `Payable`?

Karena `pay()` menerima parameter bertipe `Payable`, dan tipe itu adalah interface.
`ElectricityBill` dan `PermanentEmployee` sama-sama meng-implements `Payable`, sehingga
keduanya adalah bentuk dari `Payable`. Argumen di-*upcast* otomatis ke tipe parameter:

```java
public void pay(Payable p) { ... }
```

Dengan demikian `Owner` tidak perlu mengetahui class konkret pemanggilnya.

### 2. Apa tujuan membuat parameter bertipe `Payable` pada method `pay()`?

Agar method bersifat polimorfik dan hanya bergantung pada kontrak `Payable`, yaitu
`getPaymentAmount()`. Satu method dapat menerima pegawai tetap, tagihan listrik, atau
class lain yang meng-implements `Payable` di masa depan tanpa mengubah tanda tangan
method. Ini membuat `Owner` tidak terikat pada class konkret (*loose coupling*) dan
mencegah duplikasi method `pay` untuk setiap jenis tagihan.

### 3. Mengapa `ow.pay(iEmp)` menyebabkan error?

Karena `iEmp` bertipe `InternshipEmployee`, dan class tersebut hanya turunan `Employee`
serta **tidak** meng-implements `Payable`. Parameter `pay()` mensyaratkan objek yang
meng-implements `Payable`, sehingga compiler menolak argumen tersebut dengan
`incompatible types: InternshipEmployee cannot be converted to Payable`. Ini konsisten
dengan `p = iEmp;` pada Percobaan 1.

### 4. Untuk apa sintaks `p instanceof ElectricityBill` diperlukan pada `Owner`?

Untuk memeriksa jenis objek sebenarnya sebelum melakukan *downcasting*. `p` bertipe
`Payable`, sehingga bisa menunjuk `ElectricityBill` atau `PermanentEmployee`. Dengan
`instanceof`, program hanya melakukan cast ketika objek memang bertipe yang diharapkan,
sehingga terhindar dari `ClassCastException`. Pemeriksaan ini juga memilih informasi mana
yang akan dicetak: `getBillInfo()` untuk tagihan listrik atau `getEmployeeInfo()` untuk
pegawai tetap.

### 5. Untuk apa casting `(ElectricityBill) p` diperlukan? Mengapa `p` harus di-cast ke `eb` bertipe `ElectricityBill`?

Karena tipe `Payable` hanya mengenal method `getPaymentAmount()`. Method `getBillInfo()`
dideklarasikan di `ElectricityBill`, bukan di `Payable`, sehingga tidak dapat dipanggil
langsung melalui referensi `Payable`. Casting mempersempit tipe referensi dari `Payable`
ke `ElectricityBill`, sehingga compiler mengizinkan pemanggilan `eb.getBillInfo()`.
Casting hanya aman karena didahului `instanceof`; objek `p` memang benar-benar
`ElectricityBill` pada cabang tersebut.

### 6. Mengapa `Eksperimen4` lolos kompilasi tetapi gagal saat dijalankan? Bagaimana `instanceof` mencegah kegagalan tersebut?

Compiler hanya memeriksa bahwa casting **masuk akal secara tipe**, yaitu apakah
`PermanentEmployee` merupakan turunan `Employee`. Karena `e` bertipe `Employee` dan
`PermanentEmployee` adalah subclass-nya, cast dianggap mungkin. Compiler tidak mengetahui
objek nyata yang akan diisikan saat runtime.

Saat program dijalankan, objek yang ditunjuk `e` ternyata `InternshipEmployee`, bukan
`PermanentEmployee`. JVM memeriksa class objek sebenarnya dan melempar
`ClassCastException`. Karena `System.out.println("Sesudah casting: ...")` berada setelah
baris cast yang gagal, baris itu tidak pernah dieksekusi.

`instanceof` mencegah kegagalan dengan memastikan objek benar-benar bertipe target
sebelum cast:

```java
if (e instanceof PermanentEmployee) {
    PermanentEmployee pe = (PermanentEmployee) e;
    System.out.println(pe.getSalary());
}
```

Jika kondisi bernilai `false`, blok cast tidak dijalankan, sehingga tidak ada
`ClassCastException`.

---

# 8. Tugas Mandiri

## 8.1 Tugas 1 — Zombie, Barrier, dan Plant

### Kode Program

#### `zombie/Destroyable.java`

```java
package id.ac.polinema.polimorfisme.zombie;

public interface Destroyable {
    void destroyed();
}
```

#### `zombie/Zombie.java`

```java
package id.ac.polinema.polimorfisme.zombie;

public abstract class Zombie implements Destroyable {
    protected int health;
    protected int level;

    public Zombie(int health, int level) {
        this.health = health;
        this.level = level;
    }

    public abstract void heal();

    public String getZombieInfo() {
        return "Health = " + health + "\n"
                + "Level = " + level + "\n";
    }
}
```

`Zombie` tidak mengisi `destroyed()`, sehingga method itu tetap abstract dan wajib
diimplementasikan oleh `WalkingZombie` dan `JumpingZombie`.

#### `zombie/WalkingZombie.java`

```java
package id.ac.polinema.polimorfisme.zombie;

public class WalkingZombie extends Zombie {
    public WalkingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        int percentage = switch (level) {
            case 1 -> 10;
            case 2 -> 30;
            case 3 -> 40;
            default -> 0;
        };
        health = Math.min(100, health + health * percentage / 100);
    }

    @Override
    public void destroyed() {
        health -= health * 20 / 100;
    }

    @Override
    public String getZombieInfo() {
        return "Walking Zombie Data = \n" + super.getZombieInfo();
    }
}
```

#### `zombie/JumpingZombie.java`

```java
package id.ac.polinema.polimorfisme.zombie;

public class JumpingZombie extends Zombie {
    public JumpingZombie(int health, int level) {
        super(health, level);
    }

    @Override
    public void heal() {
        int percentage = switch (level) {
            case 1 -> 30;
            case 2 -> 40;
            case 3 -> 50;
            default -> 0;
        };
        health = Math.min(100, health + health * percentage / 100);
    }

    @Override
    public void destroyed() {
        health -= health * 10 / 100;
    }

    @Override
    public String getZombieInfo() {
        return "Jumping Zombie Data = \n" + super.getZombieInfo();
    }
}
```

#### `zombie/Barrier.java`

```java
package id.ac.polinema.polimorfisme.zombie;

public class Barrier implements Destroyable {
    private int strength;

    public Barrier(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public void destroyed() {
        strength = strength * 9 / 10;
    }

    public String getBarrierInfo() {
        return "Barrier Strength = " + strength + "\n";
    }
}
```

#### `zombie/Plant.java`

```java
package id.ac.polinema.polimorfisme.zombie;

public class Plant {
    public void doDestroy(Destroyable d) {
        d.destroyed();
    }
}
```

#### `zombie/MainTugas1.java`

```java
package id.ac.polinema.polimorfisme.zombie;

public class MainTugas1 {
    public static void main(String[] args) {
        WalkingZombie wz = new WalkingZombie(100, 1);
        JumpingZombie jz = new JumpingZombie(100, 2);
        Barrier b = new Barrier(100);
        Plant p = new Plant();

        System.out.println(wz.getZombieInfo());
        System.out.println(jz.getZombieInfo());
        System.out.println(b.getBarrierInfo());
        System.out.println("----------------------");

        for (int i = 0; i < 4; i++) {            // hancurkan 4 kali
            p.doDestroy(wz);
            p.doDestroy(jz);
            p.doDestroy(b);
        }

        System.out.println(wz.getZombieInfo());
        System.out.println(jz.getZombieInfo());
        System.out.println(b.getBarrierInfo());
    }
}
```

### Output

```text
Walking Zombie Data = 
Health = 100
Level = 1

Jumping Zombie Data = 
Health = 100
Level = 2

Barrier Strength = 100

----------------------
Walking Zombie Data = 
Health = 42
Level = 1

Jumping Zombie Data = 
Health = 66
Level = 2

Barrier Strength = 64

```

### Penelusuran Perhitungan

Semua pembagian memakai bilangan bulat, sehingga hasilnya dibulatkan ke bawah.

**WalkingZombie (health 100, level 1), `destroyed()` mengurangi 20% dari health saat ini:**

| Penghancuran | Perhitungan | Health |
|---|---|---|
| Awal | — | 100 |
| 1 | 100 − (100 × 20 / 100) = 100 − 20 | 80 |
| 2 | 80 − (80 × 20 / 100) = 80 − 16 | 64 |
| 3 | 64 − (64 × 20 / 100) = 64 − 12 | 52 |
| 4 | 52 − (52 × 20 / 100) = 52 − 10 | 42 |

**JumpingZombie (health 100, level 2), `destroyed()` mengurangi 10% dari health saat ini:**

| Penghancuran | Perhitungan | Health |
|---|---|---|
| Awal | — | 100 |
| 1 | 100 − 10 | 90 |
| 2 | 90 − 9 | 81 |
| 3 | 81 − 8 (8,1 dibulatkan ke bawah) | 73 |
| 4 | 73 − 7 (7,3 dibulatkan ke bawah) | 66 |

**Barrier (strength 100), `destroyed()` menyisakan 90%:**

| Penghancuran | Perhitungan | Strength |
|---|---|---|
| Awal | — | 100 |
| 1 | 100 × 9 / 10 = 90 | 90 |
| 2 | 90 × 9 / 10 = 81 | 81 |
| 3 | 81 × 9 / 10 = 72 (72,9 dibulatkan ke bawah) | 72 |
| 4 | 72 × 9 / 10 = 64 (64,8 dibulatkan ke bawah) | 64 |

### Jawaban Pertanyaan Analisis Tugas 1

#### (a) Mengapa `Plant.doDestroy()` dapat menerima `WalkingZombie`, `JumpingZombie`, dan `Barrier` padahal ketiganya tidak berada dalam satu hierarki class?

Karena parameter `doDestroy()` bertipe `Destroyable`, yaitu interface. Ketiga class
tersebut sama-sama meng-implements `Destroyable`:

```java
public abstract class Zombie implements Destroyable { ... }
public class Barrier implements Destroyable { ... }
```

`WalkingZombie` dan `JumpingZombie` mewarisi `implements Destroyable` dari `Zombie`,
sedangkan `Barrier` meng-implements langsung. Walaupun `Barrier` bukan `Zombie`, ia tetap
sebuah `Destroyable`, sehingga sah dikirim sebagai argumen (*upcasting* ke interface).
Inilah *polymorphic argument*.

#### (b) Pada `d.destroyed()` di dalam `Plant`, apa tipe referensi dan tipe objek, dan versi `destroyed()` mana yang dijalankan?

- **Tipe referensi** `d` adalah `Destroyable`. Compiler hanya tahu bahwa `d` memiliki
  method `destroyed()`.
- **Tipe objek** bergantung pada argumen yang dikirim: `WalkingZombie`, `JumpingZombie`,
  atau `Barrier`.
- **Versi method** yang dijalankan adalah override milik objek sebenarnya
  (*dynamic method dispatch*):

| Pemanggilan di `MainTugas1` | Tipe objek | `destroyed()` yang dijalankan |
|---|---|---|
| `p.doDestroy(wz)` | `WalkingZombie` | `WalkingZombie.destroyed()` → health −20% |
| `p.doDestroy(jz)` | `JumpingZombie` | `JumpingZombie.destroyed()` → health −10% |
| `p.doDestroy(b)` | `Barrier` | `Barrier.destroyed()` → strength × 9 / 10 |

Karena perulangan berjalan 4 kali, masing-masing method dipanggil 4 kali sesuai objeknya.

#### (c) Apa yang terjadi bila menulis `new Zombie(100, 1)`? Tuliskan pesan errornya.

Terjadi error kompilasi karena `Zombie` adalah abstract class dan tidak dapat
diinstansiasi. Kutipan pesan errornya:

```text
src/id/ac/polinema/polimorfisme/zombie/Tugas3ErrorKompilasi.java:9: error: Zombie is abstract; cannot be instantiated
        Zombie z4 = new Zombie(100, 1);
                    ^
```

## 8.2 Tugas 2 — Heterogeneous Collection, `instanceof`, dan Casting

### Kode Program

`zombie/MainTugas2.java`

```java
package id.ac.polinema.polimorfisme.zombie;

public class MainTugas2 {
    public static void main(String[] args) {
        Destroyable[] targets = {
                new WalkingZombie(100, 1),
                new JumpingZombie(100, 2),
                new Barrier(100)
        };
        Plant plant = new Plant();

        for (int i = 0; i < 4; i++) {            // hancurkan semua elemen 4 kali
            for (Destroyable target : targets) {
                plant.doDestroy(target);
            }
        }

        for (Destroyable target : targets) {
            if (target instanceof Zombie) {
                Zombie z = (Zombie) target;
                z.heal();
                System.out.println(z.getZombieInfo());
            } else if (target instanceof Barrier) {
                Barrier b = (Barrier) target;
                System.out.println(b.getBarrierInfo());
            }
        }
    }
}
```

### Output

```text
Walking Zombie Data = 
Health = 46
Level = 1

Jumping Zombie Data = 
Health = 92
Level = 2

Barrier Strength = 64

```

### Penelusuran Perhitungan

Setelah 4 kali penghancuran, nilai health/strength sama dengan Tugas 1: WalkingZombie 42,
JumpingZombie 66, Barrier 64. Lalu `heal()` dijalankan sekali pada setiap zombie:

| Objek | Sebelum `heal()` | Persentase level | Perhitungan | Sesudah `heal()` |
|---|---|---|---|---|
| `WalkingZombie` | 42 | level 1 → 10% | 42 + (42 × 10 / 100) = 42 + 4 | 46 |
| `JumpingZombie` | 66 | level 2 → 40% | 66 + (66 × 40 / 100) = 66 + 26 | 92 |
| `Barrier` | 64 | — | tidak memiliki `heal()` | 64 |

### Jawaban Pertanyaan Analisis Tugas 2

#### (a) Mengapa array harus bertipe `Destroyable`, bukan `Zombie`?

Karena isi array terdiri atas dua zombie dan satu `Barrier`. `Barrier` bukan `Zombie`
dan tidak berada dalam hierarki class `Zombie`, sehingga tidak dapat menjadi elemen
`Zombie[]`. Satu-satunya tipe umum yang dimiliki ketiganya adalah interface
`Destroyable`. Dengan `Destroyable[]`, ketiga objek dapat disimpan dalam satu array dan
langsung dikirim ke `Plant.doDestroy(Destroyable)` tanpa casting.

#### (b) Mengapa casting ke `Zombie` diperlukan sebelum memanggil `heal()`?

Karena tipe elemen array adalah `Destroyable`, yang hanya mendeklarasikan `destroyed()`.
Method `heal()` dideklarasikan di `Zombie`, bukan di `Destroyable`, sehingga tidak
terlihat oleh compiler melalui referensi `Destroyable`. Setelah `instanceof Zombie`
bernilai benar, cast `(Zombie) target` mempersempit tipe referensi sehingga `heal()`
dapat dipanggil. Cast ini aman karena hanya dijalankan untuk elemen yang benar-benar
`Zombie`.

#### (c) Apa yang terjadi bila pemeriksaan `instanceof` dihilangkan dan elemen `Barrier` di-cast ke `Zombie`?

Kode tetap **lolos kompilasi**, karena `Zombie` adalah class yang meng-implements
`Destroyable` dan bukan `final`, sehingga cast dari referensi `Destroyable` ke `Zombie`
masih mungkin secara tipe. Namun saat dijalankan, elemen `Barrier` bukan objek `Zombie`,
sehingga JVM melempar `ClassCastException`. Hasil pengujian pada class uji terpisah
(tidak disertakan dalam `src` karena sengaja gagal saat dijalankan):

```text
Exception in thread "main" java.lang.ClassCastException: class id.ac.polinema.polimorfisme.zombie.Barrier cannot be cast to class id.ac.polinema.polimorfisme.zombie.Zombie (id.ac.polinema.polimorfisme.zombie.Barrier and id.ac.polinema.polimorfisme.zombie.Zombie are in unnamed module of loader 'app')
	at id.ac.polinema.polimorfisme.zombie.Tugas2TanpaInstanceof.main(Tugas2TanpaInstanceof.java:6)
```

Karena itu `instanceof` diperlukan sebagai penjaga sebelum downcasting.

## 8.3 Tugas 3 — Prediksi Hasil Kompilasi

Prediksi dibuat berdasarkan class pada Tugas 1, lalu dibuktikan dengan
`zombie/Tugas3Bukti.java` serta class uji terpisah untuk baris yang gagal dikompilasi.
Pesan error lengkap tersimpan di `out/pesan-error.txt`.

### Tabel Prediksi

| Pernyataan | Prediksi | Alasan |
|---|---|---|
| `Zombie z = new WalkingZombie(100, 1);` | **OK** | `WalkingZombie` adalah subclass `Zombie`; terjadi upcasting otomatis. |
| `Destroyable d = z;` | **OK** | `Zombie` meng-implements `Destroyable`; referensi superclass di-upcast ke interface. |
| `Zombie z2 = d;` | **Error kompilasi** | `d` bertipe `Destroyable`, yaitu supertype dari `Zombie`. Assignment ke tipe yang lebih sempit membutuhkan downcast eksplisit. |
| `Zombie z3 = (Zombie) d;` | **OK** | Downcast eksplisit. Objek nyata `d` adalah `WalkingZombie`, yang merupakan `Zombie`, sehingga berhasil saat runtime. |
| `Zombie z4 = new Zombie(100, 1);` | **Error kompilasi** | `Zombie` abstract; tidak dapat diinstansiasi. |
| `Destroyable d2 = new Plant();` | **Error kompilasi** | `Plant` tidak meng-implements `Destroyable`; tidak ada relasi *is-a*. |
| `Zombie z5 = new JumpingZombie(100, 2);`<br>`WalkingZombie w = (WalkingZombie) z5;` | **Kompilasi OK, error saat dijalankan** | Cast antar-subclass `Zombie` masih mungkin secara tipe, tetapi objek nyata `z5` adalah `JumpingZombie`, bukan `WalkingZombie`, sehingga terjadi `ClassCastException`. |

### Bukti Kode

`zombie/Tugas3Bukti.java`

```java
package id.ac.polinema.polimorfisme.zombie;

/**
 * Bukti Tugas 3. Baris yang diperkirakan gagal dikompilasi ditulis sebagai komentar
 * dan pesan errornya didokumentasikan pada out/pesan-error.txt.
 */
public class Tugas3Bukti {
    public static void main(String[] args) {
        // Prediksi: OK
        Zombie z = new WalkingZombie(100, 1);
        System.out.println("1. Zombie z = new WalkingZombie(100, 1); -> OK ("
                + z.getClass().getSimpleName() + ")");

        // Prediksi: OK
        Destroyable d = z;
        System.out.println("2. Destroyable d = z; -> OK ("
                + d.getClass().getSimpleName() + ")");

        // Prediksi: error kompilasi
        // Zombie z2 = d;

        // Prediksi: OK
        Zombie z3 = (Zombie) d;
        System.out.println("4. Zombie z3 = (Zombie) d; -> OK ("
                + z3.getClass().getSimpleName() + ")");

        // Prediksi: error kompilasi (Zombie abstract)
        // Zombie z4 = new Zombie(100, 1);

        // Prediksi: error kompilasi (Plant tidak implements Destroyable)
        // Destroyable d2 = new Plant();

        // Prediksi: kompilasi OK, error saat dijalankan (ClassCastException)
        Zombie z5 = new JumpingZombie(100, 2);
        try {
            WalkingZombie w = (WalkingZombie) z5;
            System.out.println("7. WalkingZombie w = (WalkingZombie) z5; -> OK ("
                    + w.getClass().getSimpleName() + ")");
        } catch (ClassCastException ex) {
            System.out.println("7. WalkingZombie w = (WalkingZombie) z5; -> "
                    + ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
}
```

### Output Bukti

```text
1. Zombie z = new WalkingZombie(100, 1); -> OK (WalkingZombie)
2. Destroyable d = z; -> OK (WalkingZombie)
4. Zombie z3 = (Zombie) d; -> OK (WalkingZombie)
7. WalkingZombie w = (WalkingZombie) z5; -> java.lang.ClassCastException: class id.ac.polinema.polimorfisme.zombie.JumpingZombie cannot be cast to class id.ac.polinema.polimorfisme.zombie.WalkingZombie (id.ac.polinema.polimorfisme.zombie.JumpingZombie and id.ac.polinema.polimorfisme.zombie.WalkingZombie are in unnamed module of loader 'app')
```

### Bukti Error Kompilasi

Baris yang diprediksi gagal dikompilasi diuji pada class terpisah agar pesan error yang
muncul hanya berasal dari baris tersebut:

```java
package id.ac.polinema.polimorfisme.zombie;

public class Tugas3ErrorKompilasi {
    public static void main(String[] args) {
        Zombie z = new WalkingZombie(100, 1);
        Destroyable d = z;

        Zombie z2 = d;
        Zombie z4 = new Zombie(100, 1);
        Destroyable d2 = new Plant();
    }
}
```

Hasil kompilasi:

```text
src/id/ac/polinema/polimorfisme/zombie/Tugas3ErrorKompilasi.java:8: error: incompatible types: Destroyable cannot be converted to Zombie
        Zombie z2 = d;
                    ^
src/id/ac/polinema/polimorfisme/zombie/Tugas3ErrorKompilasi.java:9: error: Zombie is abstract; cannot be instantiated
        Zombie z4 = new Zombie(100, 1);
                    ^
src/id/ac/polinema/polimorfisme/zombie/Tugas3ErrorKompilasi.java:10: error: incompatible types: Plant cannot be converted to Destroyable
        Destroyable d2 = new Plant();
                         ^
3 errors
```

### Pembahasan

- **`Zombie z2 = d;`** gagal karena `Destroyable` adalah interface yang lebih umum.
  Compiler tidak dapat menjamin objek di dalamnya adalah `Zombie` (bisa saja `Barrier`),
  sehingga diperlukan cast eksplisit.
- **`Zombie z4 = new Zombie(100, 1);`** gagal karena `Zombie` abstract. Method `heal()`
  dan `destroyed()` belum memiliki implementasi lengkap.
- **`Destroyable d2 = new Plant();`** gagal karena `Plant` bukan implementor
  `Destroyable`. `Plant` hanya *menggunakan* `Destroyable` sebagai parameter
  (*dependency*), bukan bagian dari hierarki tersebut.
- **`WalkingZombie w = (WalkingZombie) z5;`** lolos kompilasi karena `WalkingZombie` dan
  `JumpingZombie` sama-sama subclass `Zombie`, sehingga cast masih mungkin. Namun objek
  nyata `z5` adalah `JumpingZombie`, sehingga pemeriksaan runtime gagal dan muncul
  `ClassCastException`. Ini menunjukkan bahwa compiler hanya memeriksa kemungkinan tipe,
  sedangkan kecocokan objek sebenarnya diperiksa JVM saat program berjalan.

---

# 9. Kesimpulan

1. Polimorfisme memungkinkan referensi bertipe superclass atau interface menunjuk objek
   subclass/implementor. Assignment hanya sah bila ada relasi *is-a* melalui `extends`
   atau `implements`.
2. Tipe referensi menentukan anggota yang boleh dipanggil compiler, sedangkan tipe objek
   menentukan versi method yang dijalankan JVM. Keduanya dapat berbeda, seperti
   `Animal a = new Deer()`.
3. *Virtual method invocation* dan *dynamic method dispatch* menyebabkan pernyataan yang
   sama dapat menghasilkan keluaran berbeda ketika objek yang ditunjuk berbeda.
4. *Heterogeneous collection* memungkinkan satu array bertipe superclass/interface berisi
   berbagai objek turunannya. Array tetap memeriksa tipe elemen, sehingga objek yang tidak
   memiliki relasi *is-a* ditolak compiler.
5. *Polymorphic argument* membuat satu method dapat menerima berbagai jenis objek yang
   memenuhi kontrak tipe parameter, seperti `Owner.pay(Payable)` dan
   `Plant.doDestroy(Destroyable)`.
6. `instanceof` memeriksa tipe objek sebenarnya dan harus dipakai sebelum *downcasting*
   untuk mencegah `ClassCastException`. *Upcasting* terjadi otomatis, sedangkan
   *downcasting* memerlukan cast eksplisit.
7. Abstract class seperti `Zombie` tidak dapat diinstansiasi dan mewariskan kewajiban
   mengimplementasikan method abstract kepada subclass konkret.
8. Kesalahan tipe pada polimorfisme terbagi dua: error kompilasi untuk relasi tipe yang
   tidak mungkin (misalnya `Plant` ke `Destroyable`), dan error runtime untuk cast yang
   mungkin secara tipe tetapi tidak cocok dengan objek nyata (misalnya `JumpingZombie` ke
   `WalkingZombie`).
