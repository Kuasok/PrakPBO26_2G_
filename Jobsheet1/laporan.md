# Laporan Jobsheet 1 PBO

## Jawaban Pertanyaan

### 1. Perbedaan object dan class

Class adalah cetak biru atau rancangan yang mendefinisikan atribut dan method yang dimiliki suatu objek. Object adalah wujud nyata atau instance yang dibuat berdasarkan class. Sebagai contoh, `Bike` merupakan class, sedangkan `mountainBike1` dan `mountainBike2` merupakan object dari class tersebut.

### 2. Alasan `gear` dan `brand` dapat menjadi atribut object `Bike`

`gear` dan `brand` merupakan data yang menggambarkan keadaan atau karakteristik sebuah sepeda. Setiap sepeda dapat memiliki merek (`brand`) dan gigi yang sedang digunakan (`gear`) yang berbeda. Oleh karena itu, keduanya tepat dijadikan atribut pada object `Bike`.

### 3. Kelebihan utama pemrograman berorientasi objek

Salah satu kelebihannya adalah kode lebih mudah digunakan kembali (*reusable*) melalui class dan pewarisan (*inheritance*). Hal ini dapat mengurangi pengulangan kode serta memudahkan pengembangan dan pemeliharaan program.

### 4. Pendefinisian dua atribut dalam satu baris

Diperbolehkan. Dalam Java, dua atribut dengan tipe data yang sama dapat didefinisikan dalam satu baris, seperti berikut:

```java
public String nama, alamat;
```

Penulisan tersebut setara dengan:

```java
public String nama;
public String alamat;
```

### 5. Alasan atribut tidak ditulis ulang pada class `RoadBike`

`RoadBike` merupakan turunan dari class `Bike`, sehingga dapat mewarisi atribut dan method dari `Bike`. Atribut `brand`, `speed`, dan `gear` sudah didefinisikan di class induk (`Bike`), sehingga tidak perlu ditulis ulang di `RoadBike`. Hal ini menerapkan konsep pewarisan (*inheritance*) dan menghindari duplikasi kode.
