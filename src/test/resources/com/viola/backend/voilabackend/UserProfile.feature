Feature: Kullanıcı profili
    Scenario: Kullanıcı sosyal medya hesapları
        Given Kullanıcı "mete@voila.com" eposta ve "mete123" şifresi ile oluşturulduğunda
        Given Örnek profil verisi kaydedildi
        Then Kullanıcının profil verisi örnek ile aynı
    Scenario: Kullanıcının sosyal medya hesapları güncellendiği zaman
        Given Kullanıcı "mete@voila.com" epostası ile var
        When Kullanıcının sosyal medya hesapları örnek veri ile güncellendiğinde
        Then Sosyal medya hesapları güncellenmiş olacak
    Scenario: Kullanıcının bio bilgisi değiştirildi
        Given Kullanıcı "mete@voila.com" epostası ile var
        When Kullanıcı biosunu "stratejist" olarak değiştirdiğinde
        Then Kullanıcının biosu "stratejist" olarak değişmiş olması gerekiyor
    Scenario: Kullanıcı iletişim bilgileri ekledi
        Given Kullanıcı "mete@voila.com" epostası ile var
        When Kullanıcı "+90" "2124539256" whatsapp numarası giriyor
        When Kullanıcı "+90" "2124539256" telefon numarası giriyor
        When Kullanıcı "+90" "5313271062" telefon numarası giriyor
        When Kullanıcı "mail@sirketim.com" email adresi giriyor
        Then Kullanıcı 4 adet iletişim bilgisi girmiş oluyor
    Scenario: Kullanıcı link bilgileri ekliyor    
        Given Kullanıcı "mete@voila.com" epostası ile var
        When Kullanıcı "sirketim.com" bağlantısını ekliyor
        When Kullanıcı "sahibinden.com/portal" bağlantısını ekliyor
        Then Kullanıcı 2 adet bağlantı eklemiş oluyor