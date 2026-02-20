Daffa Ismail 2406434090

Refleksi 1

Dengan tutorial ini saya lebih mengerti clean code. Semua code yang ditulis di tutorial ini sangat erat mengikuti prinsip-prinsip clean code. Contohnya setiap variable dan function mempunyai nama yang jelas. Kita juga tidak menulis komen karena code bersifat self-documenting dengan mengimplementasi code yang modular dan jelas.

Diluar prinsip-prinsip itu, kita juga melakukan best practices seperti membuat branch dan melakukan pull request untuk mengimplementasi fitur, commit rutin, dan penamaan commit yang informatif. Kita juga menstruktur project dengan baik dengan memisah-misah aplikasi berdasarkan struktur MVC.

Refleksi 2

Unit test sebaiknya mencapai 80% coverage, yang artinya 80% dari kode berhasil dijalankan saat test. Namun ini bukan berarti kodenya bebas dari bug, melainkan kode mengikuti alur dan ekspektasi dari tes yang kita buat. 

Iya, jika kita membuat test suite baru yang me-reuse kode dari test suite lain, kualitas kode akan berkurang. Melakukan hal tersebut melanggar beberapa prinsip clean code berupa: DRY (dont repeat yourself) karena kita menduplikasi kode, dan Maintainability karena jika ada perubahan kode kita harus mengubah setiap file secara manual.



Modul 2 https://modul-2-cicd-devops-ae8bdcd45b5b.herokuapp.com/product/list

Refleksi

Sonarcloud menemukan isu code quality berupa security hotspot. Penjelasan isunya cukup minim sehingga diperlukan google searching. Hasil searching menemukan bahwa isunya disebabkan oleh tidak adanya verifikasi metadata dalam bentuk file verification-metadata.xml. Fixnya adalah dengan mengenerate file tersebut menggunakan command `./gradlew --write-verification-metadata sha256 build`. Setelah commit dan push filenya, isu akhirnya hilang.

Berdasarkan script ci yang telah dibikin serta settings di Heroku, sudah bisa dibilang implementasi cicd proyek ini memenuhi definisi. CI jalan untuk melakukan pengecekan apakah code berkualitas melalui sonarcloud dan apakah terintegrasi dengan benar melalui tests. Lalu, proyek hanya akan di redeploy oleh Heroku jika pass checks github actions script ci.
