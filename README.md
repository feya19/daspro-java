
# Flowcart

![App Screenshot](./image/ssan.jpeg)


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



