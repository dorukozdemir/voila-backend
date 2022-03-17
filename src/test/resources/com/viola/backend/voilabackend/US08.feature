Feature: Kullanıcı sosyal medya hesaplarını güncelliyor 
    Scenario: Kullanıcı kendi profiline gidip sosyal medya hesaplarını düzenliyor
        Given Kullanan kullanıcı "mete3@voila.com" "mete123" ile kayıtlı
        Given Uygulamaya "mete3@voila.com" adresi ve "mete123" şifresi ile giriş yapmış kullanıcı var
        Given Kullanıcı kendi profilini görüntülüyor
        When Kullanıcı değiştirmek istediği alanlara giriş yapıyor
        When kaydet tuşuna bastığında
        Then Kullanıcı sosyal medya hesaplarını başarılı bir şekilde güncelleniyor
        Then Kullanıcı güncel sosyal medya hesap bilgilerini görüyor

