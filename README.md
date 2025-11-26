Aplikasi Hydration Tracker,
Aplikasi Android sederhana ini bertujuan untuk mendemokan proses background, pengambilan data, dan local notification menggunakan Jetpack Compose.
Fokusnya adalah menunjukkan bagaimana data dapat diproses di background tanpa mengganggu UI, lalu memberi tahu pengguna ketika proses selesai.

-Arsitektur
1. Repository
   - FakeRepository membaca file JSON dari folder assets, memparsing data, dan mengirimkannya ke ViewModel.
   - Hydration adalah model data yang mewakili setiap item di JSON.
2. ViewModel & UI
   - MainViewModel mengelola state seperti isLoading, dataList, dan error. Semua proses berat dijalankan melalui Coroutine di Dispatchers.IO.
   - MainScreen hanya bertugas menampilkan UI berdasarkan state yang diberikan ViewModel.
3. Notifikasi
   - Notifikasi dikelola oleh utilitas bernama NotificationHelper.
   - ViewModel cukup memanggil satu fungsi ketika data berhasil diload, sementara helper mengurus pembuatan channel dan buildernya.

![Sebelum Proses](Snap_Tampilan%20sebelum%20proses.png) ![Setelah Proses](Snap_Tampilan%20setelah%20proses%20selesai%20%2B%20data%20muncul.png)

