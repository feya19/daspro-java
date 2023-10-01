
## Screenshots

(./image/ssan.jpeg)


# Fitur

beberapa fitur yang terdapat dalam program ini:

Autentikasi dengan PIN: Program meminta pengguna untuk memasukkan PIN yang harus sesuai dengan nilai yang telah ditetapkan dalam variabel SECRET_PIN. Jika PIN yang dimasukkan benar, pengguna diberikan akses ke program. Jika salah, program akan berakhir.

Input Data Barang: Program meminta pengguna untuk memasukkan nama barang, harga barang, diskon (dalam persen), dan PPN (dalam persen). Data ini digunakan untuk menghitung total transaksi.

Perhitungan Harga Diskon: Program menghitung nilai harga diskon dengan mengalikan harga barang dengan persentase diskon yang dimasukkan.

Perhitungan Total Diskon: Program menghitung nilai total harga setelah diskon dengan mengurangkan harga diskon dari harga barang.

Perhitungan Total PPN: Program menghitung nilai PPN (Pajak Pertambahan Nilai) dengan mengalikan total harga setelah diskon dengan persentase PPN yang dimasukkan.

Perhitungan Total Transaksi: Program menghitung total transaksi dengan menambahkan total harga setelah diskon dan total PPN.

Penggunaan String.format: Program menggunakan String.format untuk menghasilkan hasil akhir dengan dua desimal, memberikan format uang yang lebih baik.

Penggunaan Scanner: Program menggunakan kelas Scanner untuk menerima input dari pengguna melalui keyboard.

Penutupan Input Scanner: Program menutup objek Scanner setelah selesai menggunakannya dengan memanggil input.close().

Program ini memberikan fungsionalitas sederhana untuk menghitung total transaksi belanja berdasarkan harga barang, diskon, dan PPN yang dimasukkan oleh pengguna setelah autentikasi dengan PIN yang benar.

# Anggota :
    5.  Alvino Valerian Daniswara Rahman (2341720027)
    10. Fahmi Yahya (2341720089)
    17. Michelle Dorani Shiba (2341720113)
    
# Deskripsi :
     Sistem kantin adalah platform transaksi yang 
menggunakan antarmuka teks atau baris perintah 
berbasis command line sebagai sarana untuk 
berinteraksi dengan sistem penjualan kantin. Ruang 
lingkup sistem ini melibatkan sejumlah aspek yang penting dalam operasi penjualan produk. Pertama, dalam
     sistem ini, penjual dapat melakukan sejumlah tindakan dasar seperti menampilkan informasi produk, cek stok produk, menambah deskripsi produk, menambahkan diskon serta ppn dalam penjualannya. Kedua, sistem ini memungkinkan penjual untuk mengakses riwayat transaksi dan laporan keuntungan tanpa perlu mencatat dan menghitung secara manual. Ini sangat menguntungkan bagi mereka yang lebih nyaman dengan penggunaan perintah teks atau bagi pengguna yang ingin mempersingkat waktu serta mendapatkan hasil yang efisien dalam penjualan mereka.

# Ruang Lingkup :
    1. Antarmuka Command Line: 
        Sistem ini akan menggunakan antarmuka     teks atau baris perintah sebagai sarana utama untuk berinteraksi dengan pengguna, khususnya penjual. Ini berarti semua perintah dan input akan dimasukkan melalui teks atau perintah yang dapat dieksekusi.
    2. Keamanan: 
        Meskipun berbasis command line, sistem ini harus memiliki lapisan keamanan untuk melindungi data pelanggan dan informasi transaksi.
    3. Informasi Produk:
        Menampilkan Informasi Produk: Penjual dapat menggunakan sistem ini untuk menampilkan informasi tentang produk yang tersedia, termasuk nama, harga, stok, HPP, kategori produk, deskripsi produk.
    4. Transaksi:
        a. Pembuatan Transaksi Penjualan: Sistem ini memungkinkan penjual untuk membuat transaksi penjualan dari pelanggan melalui perintah teks.
        b. Perhitungan Total Transaksi: Sistem akan menghitung total harga pesanan, termasuk diskon dan PPN jika ada.
        c. Pencatatan Riwayat Transaksi: Sistem akan mencatat setiap transaksi yang terjadi, mencakup detail seperti item yang dibeli, jumlah, harga, dan total transaksi, tanggal dan waktu, nama pelanggan.
    5. Laporan:
        a. Laporan Keuntungan: Sistem ini akan menghasilkan laporan keuntungan berdasarkan data transaksi yang dicatat, membantu penjual untuk melihat kinerja bisnis mereka.
        b. Riwayat Transaksi: Penjual dapat mengakses riwayat transaksi sebelumnya untuk referensi.
    6. Pengoptimalan dan Efisiensi: 
        Ruang lingkup sistem mencakup pengoptimalan dan efisiensi dalam operasi penjualan, memungkinkan penjual untuk menghemat waktu dan upaya dalam pengelolaan bisnis mereka.
    7. Dokumentasi: 
        Sistem dilengkapi dengan dokumentasi yang memadai, seperti panduan pengguna, agar penjual dapat memahami cara menggunakan sistem dengan baik.




