# Voila projesi için backend projesi

## Kullanılan teknolojiler:
Spring boot
Spring security
JWT
JPA
Hibernate

## Çalıştırma
Hızlı çalıştırma için Visual Studio Code'da Debug ile çalıştırılabilinir.
`./mnvw spring-boot` komutu ile çalışıtılabilinir
Docker ile çalıştırmak için klasör içindeyken docker-compose up kullanılabilir. 
localhost:8080 portunda uygulama çalışacaktır. 

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
