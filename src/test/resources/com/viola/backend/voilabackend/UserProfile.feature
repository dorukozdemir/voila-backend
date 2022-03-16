Feature: Kullanıcı profili
    Scenario: Kullanıcı sosyal medya hesapları
        Given Kullanıcı "mete@voila.com" eposta ve "mete123" şifresi ile oluşturulduğunda
        Given Örnek profil verisi kaydedildi
        Then Kullanıcının profil verisi örnek ile aynı
    Scenario: Kullanıcının sosyal medya hesapları güncellendiği zaman
        Given Kullanıcı "mete@voila.com" epostası ile var
        When Kullanıcının sosyal medya hesapları örnek veri ile güncellendiğinde
        Then Sosyal medya hesapları güncellenmiş olacak