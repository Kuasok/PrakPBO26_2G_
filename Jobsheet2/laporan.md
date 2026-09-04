# LAPORAN JOBSHEET 2 – KELAS DAN OBJEK

**Nama:** Muhammad Ferdi Afiyanto  
**NIM:** 254107020122  
**Absen:** 16  
**Kelas:** 2G

---

## 1. Tujuan Praktikum

Setelah melakukan praktikum ini, mahasiswa mampu:

1. Mendefinisikan kelas Java dengan atribut, konstruktor, dan method.
2. Membuat dan memanipulasi objek menggunakan `new`, serta memahami perilaku referensi.
3. Menerjemahkan diagram kelas UML sederhana menjadi kode Java.

---

## 2. Pendahuluan

### 2.1 Class dan Object

Class merupakan cetakan atau blueprint yang menjelaskan atribut dan perilaku yang dimiliki oleh objek. Object merupakan hasil pembuatan dari sebuah class menggunakan keyword `new`.

Pada jobsheet ini konsep class dan object diperkenalkan melalui class `Rectangle`, kemudian dilanjutkan dengan method, constructor, `this`, reference, aliasing, `null`, class `Student`, dan array of objects.

### 2.2 Constructor dan `this`

Constructor merupakan bagian dari class yang dijalankan ketika objek dibuat. Constructor dapat digunakan untuk memberikan nilai awal pada atribut objek.

Keyword `this` digunakan untuk merujuk pada atribut atau method milik objek yang sedang digunakan. Hal ini berguna ketika nama parameter constructor sama dengan nama atribut.

### 2.3 Reference Object

Variabel objek pada Java menyimpan reference yang menunjuk ke objek di memori. Karena itu, dua variabel dapat menunjuk ke objek yang sama dan perubahan melalui salah satu reference akan terlihat melalui reference lainnya.

### 2.4 Array of Objects

Satu class dapat digunakan untuk membuat banyak objek. Java juga memungkinkan beberapa reference objek disimpan dalam sebuah array, misalnya `Rectangle[]`.

---

# 3. Percobaan

## 3.1 Langkah 1 – Membuat Project

Seluruh class ditempatkan pada package `id.ac.polinema`.

Struktur project:

```text
Jobsheet2
└── src
    └── id
        └── ac
            └── polinema
                └── Main.java
```

Pengecekan versi Java dapat dilakukan menggunakan:

```text
java -version
javac -version
```

Program dapat dikompilasi dan dijalankan melalui terminal menggunakan:

```text
javac -d out src/id/ac/polinema/*.java
java -cp out id.ac.polinema.Main
```

---

## 3.2 Langkah 2 – Kelas Rectangle Minimal dan Objek Pertama

Pada langkah ini dibuat class `Rectangle` yang memiliki dua atribut, yaitu `width` dan `height`.

### 3.2.1 Kode Class `Rectangle`

```java
package id.ac.polinema;

public class Rectangle {
    int width;
    int height;
}
```

Class tersebut menyimpan ukuran persegi panjang melalui atribut `width` dan `height`.

### 3.2.2 Kode Class `Main`

```java
package id.ac.polinema;

public class Main {
    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.width = 6;
        r.height = 4;

        System.out.println("Rectangle " + r.width + "x" + r.height);
    }
}
```

Object `Rectangle` dibuat menggunakan `new Rectangle()`. Setelah object dibuat, nilai atribut diisi satu per satu.

### 3.2.3 Hasil Percobaan

```text
Rectangle 6x4
```

Hasil tersebut sesuai dengan checkpoint pada jobsheet.

---

## 3.3 Langkah 3 – Menambahkan Method

Pada langkah ini class `Rectangle` diberi method `area()` dan `perimeter()`.

### 3.3.1 Kode Class `Rectangle`

```java
package id.ac.polinema;

public class Rectangle {
    int width;
    int height;

    public int area() {
        return width * height;
    }

    public int perimeter() {
        return 2 * (width + height);
    }
}
```

Method `area()` menghitung luas menggunakan perkalian `width` dan `height`. Method `perimeter()` menghitung keliling menggunakan rumus `2 × (width + height)`.

### 3.3.2 Kode Class `Main`

```java
package id.ac.polinema;

public class Main {
    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        r.width = 6;
        r.height = 4;

        System.out.println("Area: " + r.area());
        System.out.println("Perimeter: " + r.perimeter());
    }
}
```

Method non-static dipanggil melalui object `r`, yaitu `r.area()` dan `r.perimeter()`.

### 3.3.3 Hasil Percobaan

```text
Area: 24
Perimeter: 20
```

Hasil sesuai dengan checkpoint pada jobsheet.

---

## 3.4 Langkah 4 – Constructor dan `this`

Constructor digunakan agar object langsung memperoleh data yang diperlukan ketika dibuat.

### 3.4.1 Kode Class `Rectangle`

```java
package id.ac.polinema;

public class Rectangle {
    int width;
    int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int area() {
        return width * height;
    }

    public int perimeter() {
        return 2 * (width + height);
    }
}
```

Pada `this.width = width`, `this.width` merujuk kepada atribut milik object, sedangkan `width` di sebelah kanan merupakan parameter constructor. Hal yang sama berlaku pada atribut `height`.

### 3.4.2 Kode Class `Main`

```java
package id.ac.polinema;

public class Main {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(6, 4);

        System.out.println("Area: " + r.area());
        System.out.println("Perimeter: " + r.perimeter());
    }
}
```

Dengan constructor, pengisian atribut tidak perlu dilakukan secara terpisah.

### 3.4.3 Hasil Percobaan

```text
Area: 24
Perimeter: 20
```

Hasil sama dengan langkah sebelumnya, tetapi pembuatan object menjadi lebih ringkas.

---

## 3.5 Langkah 5 – Reference, Aliasing, dan `null`

Pada langkah ini dipelajari bahwa variabel objek merupakan reference yang menunjuk kepada object di memori.

### 3.5.1 Kode Pengujian Aliasing

```java
Rectangle original = new Rectangle(8, 5);
Rectangle copy = original;

copy.width = 10;

System.out.println("Via original: " + original.area());
System.out.println("Via copy: " + copy.area());
```

Variabel `original` dan `copy` menunjuk kepada object `Rectangle` yang sama. Ketika `copy.width` diubah menjadi `10`, perubahan tersebut juga terlihat ketika object diakses melalui `original`.

### 3.5.2 Hasil Pengujian

```text
Via original: 50
Via copy: 50
```

Kedua hasil sama karena `original` dan `copy` merupakan dua reference yang menunjuk ke object yang sama.

### 3.5.3 Pengujian `null`

Contoh pengujian yang sengaja menghasilkan error:

```java
Rectangle empty = null;
System.out.println(empty.area());
```

Hasilnya adalah error `NullPointerException` karena reference `empty` tidak menunjuk ke object mana pun.

```text
Exception in thread "main" java.lang.NullPointerException
```

Setelah pengujian tersebut dipahami, dua baris pengujian `null` dihapus agar program dapat berjalan kembali tanpa error.

---

## 3.6 Langkah 6 – Kelas Student dari Diagram UML

Pada langkah ini dibuat class `Student` berdasarkan diagram UML pada jobsheet. Atribut dibuat private dan object diuji menggunakan data Nadia, S001, dan GPA 3.8.

### 3.6.1 Kode Class `Student`

```java
package id.ac.polinema;

public class Student {
    private String name;
    private String studentId;
    private double gpa;

    public Student(String name, String studentId, double gpa) {
        this.name = name;
        this.studentId = studentId;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return name + " (" + studentId + ", GPA: " + gpa + ")";
    }
}
```

Atribut menggunakan modifier `private` sehingga tidak dapat diakses langsung dari luar class. Constructor digunakan untuk mengisi data student ketika object dibuat.

### 3.6.2 Kode Pengujian

```java
Student student = new Student("Nadia", "S001", 3.8);
System.out.println(student);
```

### 3.6.3 Hasil Percobaan

```text
Nadia (S001, GPA: 3.8)
```

Hasil tersebut sesuai dengan checkpoint pada jobsheet.

---

## 3.7 Langkah 7 – Array of Objects

Pada langkah terakhir dibuat array `Rectangle[]` yang berisi beberapa object `Rectangle`. Setiap object memiliki ukuran berbeda.

### 3.7.1 Kode Class `Main`

```java
package id.ac.polinema;

public class Main {
    public static void main(String[] args) {
        Rectangle[] shapes = {
            new Rectangle(6, 4),
            new Rectangle(5, 3),
            new Rectangle(8, 2)
        };

        for (Rectangle shape : shapes) {
            System.out.println("Area: " + shape.area());
            System.out.println("Perimeter: " + shape.perimeter());
        }

        Student student = new Student("Nadia", "S001", 3.8);
        System.out.println(student);
    }
}
```

Array `shapes` menyimpan tiga object `Rectangle`. Perulangan `for-each` digunakan untuk mengakses setiap object dan menjalankan method `area()` serta `perimeter()`.

### 3.7.2 Hasil Percobaan

```text
Area: 24
Perimeter: 20
Area: 15
Perimeter: 16
Area: 16
Perimeter: 20
Nadia (S001, GPA: 3.8)
```

Tiga object memiliki hasil luas dan keliling yang berbeda karena masing-masing dibuat dengan ukuran yang berbeda.

---

# 4. Tugas Praktikum

## 4.1 Membuat Class Circle

Class `Circle` dibuat sesuai tugas mandiri. Class memiliki atribut radius serta method `area()` dan `circumference()` yang mengembalikan tipe `double`.

### 4.1.1 Kode Class `Circle`

```java
package id.ac.polinema;

public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double circumference() {
        return 2 * Math.PI * radius;
    }
}
```

Method `area()` menggunakan rumus `Math.PI * radius * radius`, sedangkan method `circumference()` menggunakan rumus `2 * Math.PI * radius`.

### 4.1.2 Pengujian pada `Main`

```java
Circle circle = new Circle(5);

System.out.println("Circle Area: " + circle.area());
System.out.println("Circle Circumference: " + circle.circumference());
```

### 4.1.3 Hasil Pengujian

```text
Circle Area: 78.53981633974483
Circle Circumference: 31.41592653589793
```

Hasil tersebut diperoleh dari object `Circle` dengan radius `5`.

---

## 4.2 Jawaban Pertanyaan

### 4.2.1 Apa bedanya objek dengan referensi ke objek?

Objek adalah instance sebenarnya yang dibuat dari sebuah class dan berada di memori. Referensi adalah variabel yang menyimpan acuan menuju objek tersebut, sehingga beberapa reference dapat menunjuk ke satu objek yang sama.

### 4.2.2 Tepatnya kapan konstruktor sebuah kelas dijalankan?

Constructor dijalankan ketika sebuah object dibuat menggunakan keyword `new`. Constructor dijalankan sebagai bagian dari proses pembuatan object dan digunakan untuk melakukan inisialisasi awal object tersebut.

---

# 5. Kesimpulan

Praktikum Pertemuan 2 memperkenalkan penerapan class dan object pada Java melalui class `Rectangle` dan `Student`. Dari percobaan yang dilakukan dapat dipahami penggunaan atribut, method, constructor, keyword `this`, reference, aliasing, `null`, serta array of objects.

Selain itu, tugas mandiri `Circle` menunjukkan bahwa sebuah class dapat memiliki atribut dan method yang digunakan untuk melakukan perhitungan berdasarkan data yang dimiliki oleh object.
