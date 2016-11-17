# Tugas 2 IF3110 Pengembangan Aplikasi Berbasis Web

### Tujuan Pembuatan Tugas

Diharapkan dengan tugas ini anda dapat mengerti:
* Produce dan Consume REST API
* Mengimplementasikan service Single Sign-On (SSO) sederhana
* Produce dan Consume Web Services dengan protokol SOAP
* Membuat web application dengan menggunakan JSP yang akan memanggil web services dengan SOAP dan REST.

## Petunjuk Pengerjaan

1. Fork pada repository ini dengan organisasi yang telah dibuat pada Tugas Besar 1.
2. Ubah hak akses repository hasil Fork anda menjadi **private**.
3. [DELIVERABLE] Buat tugas sesuai spesifikasi dan silakan commit pada repository anda (hasil fork). Lakukan berberapa commit dengan pesan yang bermakna, contoh: `fix css`, `create post done`, jangan seperti `final`, `benerin dikit`. Disarankan untuk tidak melakukan commit dengan perubahan yang besar karena akan mempengaruhi penilaian (contoh: hanya melakukan satu commit kemudian dikumpulkan). Sebaiknya commit dilakukan setiap ada penambahan fitur. **Commit dari setiap anggota tim akan mempengaruhi penilaian individu.** Jadi, setiap anggota tim harus melakukan sejumlah commit yang berpengaruh terhadap proses pembuatan aplikasi.
7. Hapus bagian yang tidak perlu dari *readme* ini.
8. [DELIVERABLE] Berikan penjelasan mengenai hal di bawah ini pada bagian **Penjelasan** dari *readme* repository git Anda:
    - Basis data dari sistem yang Anda buat.
    - Konsep *shared session* dengan menggunakan REST.
    - Pembangkitan token dan expire time pada sistem yang anda buat.
    - Kelebihan dan kelemahan dari arsitektur aplikasi tugas ini, dibandingkan dengan aplikasi monolitik (login, CRUD DB, dll jadi dalam satu aplikasi)
9. Pada *readme* terdapat penjelasan mengenai pembagian tugas masing-masing anggota (lihat formatnya pada bagian **pembagian tugas**).
10. Merge request dari repository anda ke repository ini dengan format **Nama kelompok** - **NIM terkecil** - **Nama Lengkap dengan NIM terkecil** sebelum **Minggu, 13 November 2016 23.59**.

### Arsitektur Umum Server
![Gambar Arsitektur Umum Server](http://gitlab.informatika.org/IF3110_WebBasedDevelopment_2016/TugasBesar2_JavaAndWebService/raw/3747ba2499396d04f742a589a024876964383159/arsitektur_umum.png)

Tugas 2 ini terdiri dari berberapa komponen yang harus dibuat:
* `Web app`: digunakan untuk menangani HTTP request dari web browser dan menghasilkan HTTP response. Bagian yang diimplementasi dengan JSP ini juga bertugas untuk meng-generate tampilan web layaknya PHP. Bagian ini wajib dibuat dengan **Java + Java Server Pages**. Maka dari itu, konversi seluruh kode PHP pada tugas 1 menjadi kode JSP.
* `Marketplace Web Service`: digunakan sebagai interface yang dipanggil oleh aplikasi melalui protokol SOAP. melakukan query ke database, operasi insert, dan operasi update untuk entitas User, Question, dan Answer. Webservice ini akan dipanggil oleh aplikasi dengan menggunakan SOAP. Webservice ini wajib dibuat dengan **JAX-WS dengan protokol SOAP atau Webservice lain** yang basisnya menggunakan **SOAP dan Java**.
* `Identity service`: dipanggil oleh aplikasi untuk menerima email (sebagai username) dan password pengguna, dan menghasilkan *access token*. Identity Service juga dipanggil oleh *marketplace web service* untuk melakukan validasi terhadap *access token* yang sedang dipegang oleh *web app*. Service ini dibuat menggunakan REST. Identity service ini wajib dibuat dengan menggunakan **Java Servlet**.
* `Database`: pisahkan basis data yang telah Anda buat pada tugas 1 menjadi basis data khusus manajemen *account* (menyimpan username, password, dkk) dan basis data marketplace tanpa manajemen *account*. Basis data *account* digunakan secara khusus oleh Identity Service. **Marketplace Web Service tidak boleh secara langsung mengakses basis data account untuk melakukan validasi token** (harus melalui Identity Service).

Perhatikan bahwa Anda tidak perlu menggunakan banyak mesin untuk membuat aplikasi ini. Contohnya, pada satu mesin anda bisa menggunakan port 8000 untuk JSP, port 8001 untuk identity service, dan port 8002 untuk marketplace web service.

### Deskripsi Tugas

Anda diminta untuk membuat marketplace sederhana seperti tugas 1.  Kebutuhan fungsional dan non-fungsional tugas yang harus dibuat adalah sebagai berikut.

1. Halaman registrasi, login, catalog, purchase confirmation, your products, add product, edit product, sales, dan purchases seperti pada tugas 1.
2. Marketplace web service dengan fungsi-fungsi sesuai kebutuhan sistem dalam mengakses data (kecuali login, register, dan logout).
3. Identity service yang menangani manajemen *account* seperti login dan register, serta validasi access token.
4. Fitur like tidak perlu diimplementasikan dengan menggunakan AJAX.
3. Tidak perlu melakukan validasi javascript.
4. Tampilan pada tugas ini **tidak akan dinilai**. Sangat disarankan untuk menggunakan asset dan tampilan dari tugas sebelumnya. Boleh menggunakan CSS framework seperti Bootstrap atau javascript library seperti jQuery.
5. Tidak perlu memperhatikan aspek keamanan dan etika penyimpanan data.


### Skenario

#### Login
1. Pengguna mengakses halaman login, contoh: `/login.jsp` dan mengisi form.
2. JSP akan membuka HTTP request ke Identity Service, contoh `POST /login` dengan body data email dan password.
3. Identity service akan melakukan query ke DB untuk mengetahui apakah email dan password tersebut valid.
4. Identity service akan memberikan HTTP response `access token` dan `expiry time` jika email dan password ada di dalam DB, atau error jika tidak ditemukan data. Silakan definisikan `expiry time` yang menurut Anda sesuai.
5. Access token ini digunakan sebagai representasi state dari session pengguna dan harus dikirimkan ketika pengguna ingin melakukan semua aktivitas, kecuali search (catalog), login, register, dan logout. 
6. Access token dibangkitkan secara random. Silakan definisikan sendiri panjang tokennya.
7. Cara penyimpanan access token dibebaskan.
6. Silakan definisikan format request dan response sesuai kebutuhan anda. Anda dapat menggunakan JSON atau XML untuk REST.

#### Register
1. Pengguna mengakses halaman register, contoh: `/register.jsp` dan mengisi form.
2. JSP akan melakukan HTTP request ke Identity Service, contoh `POST /register` dengan body data yang dibutuhkan untuk registrasi.
3. Identity service akan query DB untuk melakukan validasi bahwa email dan username belum pernah terdaftar sebelumnya.
4. Identity service akan menambahkan user ke DB bila validasi berhasil, atau memberi HTTP response error jika username sudah ada atau confirm password salah.
4. Identity service akan memberikan HTTP response `access token` dan `expiry time` dan user akan ter-login ke halaman catalog.
6. Silakan definisikan format request dan response sesuai kebutuhan anda. Anda dapat menggunakan JSON atau XML untuk REST.

#### Logout
1. Pengguna menekan tombol logout.
2. JSP akan melakukan HTTP request ke Identity Service, contoh `POST /logout` dengan body data yang dibutuhkan.
3. Identity service akan menghapus atau melakukan invalidasi terhadap access token yang diberikan.
4. Identity service akan mengembalikan HTTP response berhasil.
5. Halaman di-*redirect* ke halaman login.

#### Add Product, Search Product, Purchase Product dll
1. Pengguna mengakses halaman add product, misal `/add-product.jsp` dan mengisi form.
2. JSP akan memanggil fungsi pada *marketplace web service* dengan SOAP, misalnya `addProduct(access_token, name, description, price, image)`. Contohnya, dapat dilihat pada
[link berikut](http://www.mkyong.com/webservices/jax-ws/jax-ws-hello-world-example/)
Perhatikan pemanggilan pada contoh ini seperti melakukan remote procedure call.
3. Fungsi tersebut akan melakukan HTTP request ke Identity Service, untuk mengenali user dengan `access_token` yang diberikan.
    - Jika `access_token` **kadaluarsa**, maka `addProduct` akan memberikan response expired token.
    - Jika `access_token` **tidak valid**, maka `addProduct` akan memberikan response error ke JSP.
    - Jika `access_token` **valid**, maka `addProduct` akan memasukan produk ke DB, dan memberikan response kesuksesan ke JSP.
4. Jika `access_token` sudah kadaluarsa atau tidak valid (yang diketahui dari response error marketplace web service), sistem akan me-redirect user ke halaman login.
4. Untuk purchase product, like product, unlike product, edit product, delete product, get purchased products, get sold products kira-kira memiliki mekanisme yang sama dengan add product di atas.
5. Silakan definisikan format object request dan response sesuai kebutuhan anda.

## Web Service: SOAP & REST API

### Basis data dari sistem yang Anda buat.

Basis data dibagi menjadi dua, yaitu basis data khusus menyimpan informasi pribadi pengguna `karenawbd_akun` dan basis data untuk menyimpan objek lain selain pengguna `karenawbd_marketplace`. Pada `karenawbd_akun` terdapat `token` dan `expire time` yang tidak ada pada tugas sebelumnya.

### Konsep shared session dengan menggunakan `REST`

Client melakukan request `token` ke server dengan mengirimkan parameter `username` dan `password`. `Token` dapat digunakan untuk mengakses data pengguna tanpa harus mengirimkan username dan password lagi. Tetapi `token` memiliki batasan `waktu`, dalam beberapa saat kedepan akan `expired` jika tidak diperbaharui.

### Pembangkitan `token` dan expire time pada sistem yang anda buat.

Token dibangkitkan hanya pada saat login pertama (tidak dalam keadaan login sebelumnya). Pengguna yang telah login akan mendapatkan token yang sama jika memaksakan melakukan login lagi namun dengan expire time yang berbeda (lebih update). Ketika pengguna logout maka token akan dihapus. Setiap kali pengguna melakukan transaksi harus mengirimkan token yang dimilikinya, layanan SOAP akan melakukan request pada layanan REST untuk memeriksa integritas token dengan akun pengguna. Setiap kali pengecekan dilakukan oleh layanan REST dan berhasil, nilai expire token akan direset lagi sejak waktu pengaksesan tersebut (diperbaharui).


### Kelebihan dan kelemahan dari arsitektur aplikasi tugas ini, dibandingkan dengan aplikasi monolitik (login, CRUD DB, dll jadi dalam satu aplikasi)

Kelebihannya adalah dapat menambah performansi dapat dilakukan cukup dengan menambah server
Kekurangannya adalah sulit untuk integrasi


# Pembagian Kerja

### REST
* Koneksi ke basis data : 13514082
* Modul fungsionalitas pendukung (serializeVector, getNextDay, etc) : 13514082
* POST data parser : 13514082
* Token Generator : 13514082
* Expiry Time Updater : 13514082
* Login API : 13514082
* Register API : 13514082
* Logout API : 13514082
* Check Token Status API : 13514082

### SOAP
* Add product : 13514038
* Get product : 13514038
* Product of : 13514038
* Like : 13514038
* Search : 13514038
* Edit product : 13514038
* Delete product : 13514038
* Get sale/purchases : 13514038
* Validasi token ke REST : 13514038
* Koneksi ke web services : 13514060

### Web app (JSP)
* Halaman add product : 13514060,13514038
* Halaman buy : 13514060
* Halaman catalog : 13514060
* Halaman delete : 13514060
* Halaman edit product : 13514060,13514038
* Halaman purchases : 13514060
* Halaman login : 13514060
* Halaman register : 13514060
* Halaman sales : 13514060
* Halaman your products : 13514060


## Tim KarenaWBD

1. Nugroho 13514038

2. Raja 13514060

3. Rio 13514082