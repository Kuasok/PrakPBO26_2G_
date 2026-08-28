# LAPORAN JOBSHEET 1 – PEMROGRAMAN BERORIENTASI OBJEK

Nama: Muhammad Ferdi Afiyanto  
NIM: 254107020122  
Absen: 16  
Kelas: 2G

---

## 1. Tujuan Praktikum

Setelah melakukan praktikum ini, mahasiswa mampu memahami konsep dasar pemrograman berorientasi objek, meliputi class, object, atribut, method, dan inheritance pada Java.

---

## 2. Percobaan – Class `Bike` dan Object

Pada percobaan ini, class `Bike` digunakan sebagai cetak biru untuk membuat object sepeda. Class tersebut memiliki atribut `brand`, `speed`, dan `gear`, serta method untuk mengubah merek, mengganti gigi, mempercepat, memperlambat, dan mencetak informasi.

### 2.1 Kode Class `Bike`

```java
public class Bike {
    private String brand;
    private int speed;
    private int gear = 1;
    private final int[] GEAR_SPEEDS_LIMITS = {5, 10, 25, 30, 40, 60};

    public void setBrand(String brandName) { brand = brandName; }

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
        if (speed < 0) speed = 0;
        return speed;
    }

    public void printInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Current Speed: " + speed + " km/h");
        System.out.println("Current Gear: " + gear);
    }
}
```

### 2.2 Kode Class `BikeDemo`

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

---

## 3. Jawaban Pertanyaan

### 3.1 Perbedaan Object dan Class

Class adalah cetak biru atau rancangan yang mendefinisikan atribut dan method. Object adalah instance atau wujud nyata yang dibuat berdasarkan class. Pada program ini, `Bike` adalah class, sedangkan `mountainBike1` dan `mountainBike2` adalah object dari class tersebut.

### 3.2 Alasan `gear` dan `brand` Menjadi Atribut `Bike`

`gear` dan `brand` merupakan data yang menggambarkan karakteristik serta keadaan sebuah sepeda. Setiap object sepeda dapat memiliki merek dan posisi gigi yang berbeda, sehingga keduanya tepat dijadikan atribut class `Bike`.

### 3.3 Kelebihan Pemrograman Berorientasi Objek

Salah satu kelebihan utama pemrograman berorientasi objek adalah kode dapat digunakan kembali melalui class dan inheritance. Hal ini mengurangi pengulangan kode serta memudahkan pengembangan dan pemeliharaan program.

### 3.4 Pendefinisian Dua Atribut dalam Satu Baris

Diperbolehkan jika kedua atribut memiliki tipe data yang sama. Contohnya:

```java
public String nama, alamat;
```

Penulisan tersebut setara dengan:

```java
public String nama;
public String alamat;
```

### 3.5 Atribut pada Class `RoadBike`

Jika `RoadBike` merupakan turunan dari class `Bike`, atribut `brand`, `speed`, dan `gear` tidak perlu ditulis ulang karena sudah diwarisi dari class induk `Bike`. Hal ini menerapkan inheritance dan menghindari duplikasi kode. Atribut tersebut tetap bersifat `private` pada `Bike`, sehingga akses langsung dari subclass perlu dilakukan melalui method yang disediakan class induk.

---

## 4. Kesimpulan

Class merupakan rancangan, sedangkan object merupakan instance dari class. Melalui class `Bike`, beberapa object sepeda dapat dibuat dengan keadaan yang berbeda. Konsep enkapsulasi dan inheritance membantu menyusun program agar lebih terstruktur, mudah digunakan kembali, dan mudah dipelihara.
