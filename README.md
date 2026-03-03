Daffa Ismail 2406434090

------------------------------------------------------------------

Refleksi 1

Dengan tutorial ini saya lebih mengerti clean code. Semua code yang ditulis di tutorial ini sangat erat mengikuti prinsip-prinsip clean code. Contohnya setiap variable dan function mempunyai nama yang jelas. Kita juga tidak menulis komen karena code bersifat self-documenting dengan mengimplementasi code yang modular dan jelas.

Diluar prinsip-prinsip itu, kita juga melakukan best practices seperti membuat branch dan melakukan pull request untuk mengimplementasi fitur, commit rutin, dan penamaan commit yang informatif. Kita juga menstruktur project dengan baik dengan memisah-misah aplikasi berdasarkan struktur MVC.

Refleksi 2

Unit test sebaiknya mencapai 80% coverage, yang artinya 80% dari kode berhasil dijalankan saat test. Namun ini bukan berarti kodenya bebas dari bug, melainkan kode mengikuti alur dan ekspektasi dari tes yang kita buat. 

Iya, jika kita membuat test suite baru yang me-reuse kode dari test suite lain, kualitas kode akan berkurang. Melakukan hal tersebut melanggar beberapa prinsip clean code berupa: DRY (dont repeat yourself) karena kita menduplikasi kode, dan Maintainability karena jika ada perubahan kode kita harus mengubah setiap file secara manual.

------------------------------------------------------------------

Modul 2 https://modul-2-cicd-devops-ae8bdcd45b5b.herokuapp.com/product/list

Refleksi

Sonarcloud menemukan isu code quality berupa security hotspot. Penjelasan isunya cukup minim sehingga diperlukan google searching. Hasil searching menemukan bahwa isunya disebabkan oleh tidak adanya verifikasi metadata dalam bentuk file verification-metadata.xml. Fixnya adalah dengan mengenerate file tersebut menggunakan command `./gradlew --write-verification-metadata sha256 build`. Setelah commit dan push filenya, isu akhirnya hilang. NOTES: fix dilakukan di main karena sonarcloud tidak membolehkan melihat isu di branch selain main.

Berdasarkan script ci yang telah dibikin serta settings di Heroku, sudah bisa dibilang implementasi cicd proyek ini memenuhi definisi. CI jalan untuk melakukan pengecekan apakah code berkualitas melalui sonarcloud dan apakah terintegrasi dengan benar melalui tests. Lalu, proyek hanya akan di redeploy oleh Heroku jika pass checks github actions script ci.

------------------------------------------------------------------

Refleksi modul 3

1. I applied SOLID by refactoring code that was violating SOLID principles.

- SRP: CarController was previously embedded within the ProductController.java file, I refactored it to be in its own file.

- OCR: The project is already using interfaces in services correctly. Product and car services are extendable by adding new methods and their respective implementations in the existing implementation files.

- LSP: CarController was previously extending ProductController which violates LSP as CarController isnt meant to replace ProductController. The violation is fixed by simply not having CarController extend anything

- ISP: Services are small and already separated between product and car. No class is inhereting a method it doesnt need from any interfaces.

- DIP: CarController previously had carservice variable be of type CarServiceImpl, which violates DIP by using a concrete low level dependency as a type. Instead, I refactored it to use the abstracted CarService type.

2. Advantages of SOLID:

- Any future changes made will be easier as each file only has one reason to change due to SRP. Example: changing a route would be fully confined to one of the controller files.

- Future iterations adding new functionality would not require changing (possibly breaking) existing code, only extending it as the current code implements OCP principle. Example: adding a new feature for car by adding a method in car's service and serviceimpl file

- Proper inheritance allows for faster development as it follows the DRY principle, which is helped by first applying LSP. Example: When developing, we can easily inherit existing classes as they're implemented properly.

- No class inherits "dead" or useless methods as ISP is implemented. Example: car doesnt inherit any product methods it wont use

- Implementing changes to code logic should not be confusing as coupling is low due to following DIP. Example: We can change code logic in serviceImpl without touching controller as it is decoupled and abstracted through service.

3. Disadvantages of not applying SOLID:

- Hard to maintain classes: classes with more than one reason to change make debugging and feature development difficult. Example: a nested CarController inside ProductController creates a larger file that is prone to create merge conflicts in team work.

- Lengthier and harder development: future development would require coding sporadically as many files are affected by one change due to improper extending and modifying.

- Runtime bugs: a subclass that does not properly follow the superclass contract can change behavior unexpectedly. Example: if CarController inherits ProductController and then adds or changes routes, ProductController's behavior may become inconsistent when used as a substitute.

- Bloat: if an interface is too large, implementations must support methods they do not need, increasing complexity. Example: if CarService contains methods relevant to ProductService, the car implementation must include stubs or empty logic.

- Tight coupling: when classes depend on concrete implementations, changing implementation logic forces updates in many places and increases the risk of bugs. Example: CarController directly uses CarServiceImpl
