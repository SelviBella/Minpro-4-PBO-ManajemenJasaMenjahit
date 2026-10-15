# Sistem Manajemen Jasa Menjahit

**Nama:** Selvi Bella Dwi Anita

**NIM:** 2509116053

**Kelas:** B

---
## Deskripsi Singkat Program

Program Manajemen Jasa Menjahit merupakan aplikasi berbasis Java yang digunakan untuk mengelola pesanan jahit pelanggan. Program ini menyediakan fitur untuk menambah pesanan, melihat daftar pesanan, mengubah status pengerjaan, dan menghapus pesanan. Program dikembangkan dari project sebelumnya dengan menambahkan koneksi ke database MySQL. Pengelolaan data dilakukan menggunakan JDBC `PreparedStatement` dan pola DAO (Data Access Object). Program juga menggunakan JPA dengan Hibernate sebagai framework ORM untuk mengambil data pesanan dari database. Data awal pelanggan dan pesanan dimasukkan melalui file SQL sehingga pengguna dapat langsung mencoba fitur menampilkan pesanan tanpa harus menambahkan data terlebih dahulu.

---
## Struktur Package

Program dibagi menjadi beberapa package agar setiap bagian program memiliki fungsi yang jelas.

1. config

   Berisi file Koneksi.java yang digunakan untuk mengatur koneksi antara program Java dan database MySQL.

2. model

   Berisi class yang merepresentasikan data dan layanan dalam program, seperti file Pelanggan.java, Layanan.java, LayananJahitBaru.java, LayananPermak.java, dan PesananEntity.java. Class Layanan digunakan sebagai class induk, sedangkan PesananEntity berfungsi sebagai entity yang dipetakan ke database menggunakan ORM.

3. model.dao

   Berisi file PesananDAO.java yang menangani proses pengolahan data pesanan, seperti menambahkan, menampilkan, memperbarui status, dan menghapus pesanan.

4. view

   Berisi file PenjahitView.java yang menampilkan menu program melalui terminal serta menerima input dari pengguna.

5. controller

   Berisi file PenjahitController.java yang menghubungkan bagian view dengan proses pengolahan data melalui DAO.

6. main
 
   Berisi class utama yang digunakan untuk menjalankan aplikasi.

---
## Alur Program

Saat dijalankan, program menampilkan menu utama yang berisi lima pilihan.

1. **Tambah Pesanan:** Pengguna mengisi data pelanggan, memilih layanan, menentukan jenis pakaian, ukuran, dan jumlah pesanan. Data kemudian disimpan ke database.

   <img width="392" height="285" alt="image" src="https://github.com/user-attachments/assets/6cdacece-02db-4891-b2e5-efa24828c491" />

2. **Tampilkan Semua Pesanan:** Program mengambil data pesanan dari database menggunakan JPA dan Hibernate. Nota dapat ditampilkan dalam format lengkap atau ringkas

   <img width="431" height="522" alt="image" src="https://github.com/user-attachments/assets/5581c95f-b2dc-4519-9aca-ad4da7dbca64" />

3. **Ubah Status Pesanan:** Pengguna memasukkan ID pesanan dan memilih status baru, yaitu Selesai atau Diambil.

   <img width="387" height="333" alt="image" src="https://github.com/user-attachments/assets/e73fb1ad-1e52-422a-8d7b-0e69f5a94eca" />

4. **Batalkan Pesanan:** Pengguna memasukkan ID pesanan yang ingin dihapus dari database.

   <img width="357" height="151" alt="image" src="https://github.com/user-attachments/assets/4906c3a4-0286-4505-a209-be2a20b72896" />

5. **Keluar:** Program menghentikan perulangan menu dan menutup `EntityManagerFactory` yang digunakan oleh ORM.

   <img width="350" height="130" alt="image" src="https://github.com/user-attachments/assets/b4401264-ed8e-469f-b212-81703ac54ad4" />

---
## Encapsulation dan Inheritance

### Encapsulation

Encapsulation diterapkan dengan menggunakan modifier `private` pada atribut kelas `Pelanggan`. Dengan begitu, atribut tidak dapat diakses secara langsung dari luar kelas. Akses terhadap atribut dilakukan melalui method yang disediakan.

<img width="831" height="262" alt="image" src="https://github.com/user-attachments/assets/433e8c1c-74b1-4f54-bab6-ee5fdae0ebc0" />


Kode tersebut menunjukkan bahwa atribut pelanggan menggunakan modifier `private`, sedangkan method getter digunakan untuk membaca nilainya. Pada kelas ini juga terdapat anotasi JPA seperti `@Entity`, `@Table`, dan `@Column` untuk memetakan kelas serta atributnya ke tabel database.

<img width="576" height="452" alt="image" src="https://github.com/user-attachments/assets/a6769f53-e282-4806-97f7-24c936817b94" />

### Inheritance

Inheritance diterapkan melalui kelas `Layanan` sebagai kelas induk yang diwariskan oleh `LayananJahitBaru` dan `LayananPermak`. Kedua kelas turunan tersebut memiliki jenis perhitungan biaya masing-masing.

* Kelas Induk Layanan
  
  <img width="630" height="315" alt="image" src="https://github.com/user-attachments/assets/4c816836-edcd-4ebe-a2a9-80769d75fc86" />


* Kelas Anak LayananJahitBaru

  <img width="705" height="163" alt="image" src="https://github.com/user-attachments/assets/8d2555bf-154c-4171-80f7-1bdd408cc842" />

  Kata kunci `extends` menunjukkan bahwa `LayananJahitBaru` mewarisi atribut dan perilaku dari kelas `Layanan`. Kelas `LayananPermak` juga menggunakan `extends Layanan`.


* Kelas Anak LayananPermak

  <img width="687" height="166" alt="image" src="https://github.com/user-attachments/assets/d225ae8a-cd7b-4713-bbaf-fc18c5094ed8" />

---
## Polymorphism dan Abstraction

### Polymorphic Overriding

Overriding diterapkan ketika kelas turunan menyediakan implementasi sendiri untuk method `hitungTotalBiaya()`.

* Override Kelas LayananJahitBaru

  <img width="761" height="142" alt="image" src="https://github.com/user-attachments/assets/f743d8e1-4ff5-463e-bcee-1982b57d20d8" />

* Override Kelas LayananPermak

  <img width="823" height="115" alt="image" src="https://github.com/user-attachments/assets/a5ad9101-ea81-4956-b5a9-1f64a6dd22a7" />

Kedua method tersebut memiliki nama dan parameter yang sama, tetapi menghasilkan perhitungan berbeda sesuai dengan jenis layanan. Pada jahit pakaian baru, bahan sutra mendapatkan tambahan biaya dan tersedia perhitungan diskon. Pada layanan permak, tingkat kesulitan berat mendapatkan tambahan biaya.

### Polymorphic Overloading

Overloading diterapkan melalui dua method `tampilkanInvoice()` yang memiliki nama sama, tetapi parameter berbeda. Overloading diterapkan di kelas Pesanan menggunakan dua method.

<img width="1547" height="452" alt="image" src="https://github.com/user-attachments/assets/857c4962-296c-4668-a959-0eec405c5ace" />

Method pertama menampilkan informasi pesanan dalam format lengkap, sedangkan method kedua menerima parameter `boolean` untuk menentukan format ringkas atau lengkap.

### Abstraction

Abstraction diterapkan melalui kelas abstrak `Layanan` dan method abstrak `hitungTotalBiaya()`. Method tersebut menentukan bahwa setiap jenis layanan harus memiliki perhitungan biaya, tetapi rumusnya ditentukan oleh kelas turunan.

<img width="615" height="222" alt="image" src="https://github.com/user-attachments/assets/2df6e7e3-06eb-4bb4-aeea-0ca5a47da7f1" />

---
## JDBC dan PreparedStatement

JDBC digunakan untuk menghubungkan aplikasi Java dengan database MySQL. Koneksi database diatur melalui kelas `Koneksi.java`, sedangkan operasi penyimpanan, perubahan, dan penghapusan data ditangani oleh `PesananDAO.java`.

### Koneksi Database

<img width="837" height="382" alt="image" src="https://github.com/user-attachments/assets/7f544119-f0f6-4405-ac6a-f01faf80adf7" />

Method `getConnection()` digunakan untuk memperoleh koneksi ke database `db_menjahit`. Informasi URL, username, dan password disesuaikan dengan konfigurasi MySQL yang digunakan.

### Create Menggunakan PreparedStatement

<img width="986" height="581" alt="image" src="https://github.com/user-attachments/assets/af8cb11d-be2e-4b31-9791-f0a24f830952" />

Pada bagian tersebut, data pelanggan dimasukkan menggunakan `PreparedStatement`. Tanda tanya (`?`) berfungsi sebagai placeholder yang nilainya diisi melalui method `setString()`. Cara ini membantu memisahkan data input dari struktur perintah SQL.

### Update Menggunakan PreparedStatement

<img width="790" height="180" alt="image" src="https://github.com/user-attachments/assets/7f366fa6-1fd1-4d44-921a-8ac7fe06d812" />

Method `updateStatus()` mengubah status pesanan berdasarkan ID yang dimasukkan pengguna. Hasil `executeUpdate()` digunakan untuk mengetahui apakah ada baris yang berhasil diubah.

### Delete Menggunakan PreparedStatement

<img width="650" height="158" alt="image" src="https://github.com/user-attachments/assets/4580edda-3477-4fb9-bad2-4737de31d875" />

Method `delete()` menghapus pesanan berdasarkan ID. Jika tidak ada baris yang terhapus, method mengembalikan nilai `false`.

## Framework ORM

Program ini menggunakan Jakarta Persistence API (JPA) dengan Hibernate sebagai implementasi ORM. ORM digunakan untuk memetakan objek Java ke tabel database sehingga data dapat diolah sebagai objek tanpa harus melakukan pemetaan setiap baris hasil query secara manual.

### Konfigurasi ORM

File `persistence.xml` berisi konfigurasi persistence unit, kelas entitas yang digunakan, serta informasi koneksi database.

<img width="1302" height="497" alt="image" src="https://github.com/user-attachments/assets/5f34e40c-f30f-4995-b179-92a62d5f716f" />

Isi file tersebut memperlihatkan nama persistence unit `menjahitPU`, provider Hibernate, entitas yang dipetakan, dan pengaturan koneksi database.

### Pemetaan Entitas ke Tabel Database

<img width="602" height="812" alt="image" src="https://github.com/user-attachments/assets/2b59b22a-e74c-4872-b36f-5f3b14277e73" />

Anotasi `@Entity` menandai kelas sebagai entitas JPA, sedangkan `@Table` menentukan tabel yang dipetakan. Anotasi `@Id` menunjukkan primary key dan `@ManyToOne` serta `@JoinColumn` digunakan untuk memetakan hubungan pesanan dengan pelanggan.

### Pengambilan Data Menggunakan ORM

* Entity Manager Factory

<img width="1003" height="55" alt="image" src="https://github.com/user-attachments/assets/a3df83c2-ffb9-4d51-9f2c-67cddea80c5b" />

* Pembuatan EntityManager dan Query JPQL

<img width="887" height="192" alt="image" src="https://github.com/user-attachments/assets/a8d037a5-e3c5-433d-b7e7-39eb5a8b626c" />

Method `getAllPesanan()` mengambil seluruh data dari entitas `PesananEntity` menggunakan JPQL. Hasil query dikembalikan dalam bentuk `List<PesananEntity>`, sehingga data dapat ditampilkan melalui method invoice. Berbeda dari operasi Create, Update, dan Delete yang menggunakan JDBC `PreparedStatement`, fitur Read menggunakan JPA/Hibernate.
