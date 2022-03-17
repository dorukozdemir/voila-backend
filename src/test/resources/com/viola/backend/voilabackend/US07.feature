Feature: Kullanıcı temel profil bilgilerini güncelliyor
    Scenario: Kullanıcı kendi profiline gidip temel bilgilerini başarılı bir şekilde değiştiriyor
        Given Kullanan kullanıcı "mete2@voila.com" "mete123" ile kayıtlı
        Given Uygulamaya "mete2@voila.com" adresi ve "mete123" şifresi ile giriş yapmış kullanıcı var
        Given Kullanıcı kendi profilini görüntülüyor
        When Kullanıcı değiştirmek istediği alanlara giriş yapıyor
        When kaydet tuşuna bastığında
        Then Kullanıcı temel bilgileri başarılı bir şekilde güncelleniyor
        Then Kullanıcı güncel bilgilerini görüyor
    Scenario: Kullanıcı temel bilgilerini değiştirmeden kaydediyor
        Given Uygulamaya "mete2@voila.com" adresi ve "mete123" şifresi ile giriş yapmış kullanıcı var
        Given Kullanıcı kendi profilini görüntülüyor
        When Kullanıcı temel bilgilerinde güncelleme yapmıyor
        When kaydet tuşuna bastığında
        Then Olumsuz mesaj görüyor 

