# Voila projesi için backend projesi

## Kullanılan teknolojiler:
Spring boot
Spring security
JWT
JPA
Hibernate

## Çalıştırma
Çalıştırmadan önce src/main/resources/application.properties oluşturulmalı. 
* Eğer docker'da çalışan postgresql kullanılacaksa application-docker.properties'deki içerik kopyalanıp application.properties oluşturulup içine eklenmeli.
* Eğer localhost'ta 5432 portunda çalışan bir postgresql varsa application-local.properties'deli içerik kopyalanıp application.properties oluşturulup içine eklenmeli.
* Bunlar dışında bir bağlantı sağlanacaksa da gerekli bağlantı bilgileri application.properties oluşturulup sağlanmalı.

Hızlı çalıştırma için Visual Studio Code'da Debug ile çalıştırılabilinir.
`./mnvw spring-boot` komutu ile çalışıtılabilinir
Docker ile çalıştırmak için klasör içindeyken docker-compose up kullanılabilir. 
* 5432 portunda postgresql çalışacak ve test verileri hazır olacak
* localhost:8080 portunda uygulama çalışacaktır. 

**Örnek:**
localhost:8080/login üzerinden token oluşturulabilir.
```
header: Content-Type: application/json
body:
{
    "username": "doruk",
    "password": "doruk123"
}
```
**Veritabanındaki Test Kullanıcıları**
Eposta  | Şifre
------------- | -------------
mete@voila.com  | mete123
harun@voila.com  | harun123
doruk@voila.com  | doruk123
