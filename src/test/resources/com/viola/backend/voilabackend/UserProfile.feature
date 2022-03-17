Feature: Kullanıcı profili
    Scenario: Kullanıcı sosyal medya hesapları
        Given Kullanıcı "mete@voila.com" eposta ve "mete123" şifresi ile oluşturulduğunda
        Given Örnek sosyal medya verisi kaydedildi
        Then Kullanıcının sosyal medya verisi örnek ile aynı
    Scenario: Kullanıcının sosyal medya hesapları güncellendiği zaman
        Given Kullanıcı "mete@voila.com" epostası ile var
        When Kullanıcının sosyal medya hesapları örnek veri ile güncellendiğinde
        Then Sosyal medya hesapları güncellenmiş olacak
    Scenario: Kullanıcının adı, soyadı ve bio bilgisi değiştirildi
        Given Kullanıcı "mete@voila.com" epostası ile var
        When Kullanıcı adını "Atilla" soyadını "Çağlar" biosunu "stratejist" olarak değiştirdiğinde
        Then Kullanıcının adı "Atilla" soyadı "Çağlar" biosu "stratejist" olarak değişmiş olması gerekiyor
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
    Scenario: Kullanıcı şirket bilgileri ekliyor    
        Given Kullanıcı "mete@voila.com" epostası ile var
        When Kullanıcı "Coza Akıllı Çözümler Ltd Şti", "Baki Dede Sok. Balat Mah. No:19 34087 Fatih/İstanbul", "2140664898", "Fatih" şirketini ekliyor
        When Kullanıcı "Keys Danışmanlık", "Ankara", "123456789101", "Başkent" şirketini ekliyor
        Then Kullanıcı 2 adet şirket eklemiş oluyor
    @UserProfileLast
    Scenario: Kullanıcı banka bilgileri ekliyor    
        Given Kullanıcı "mete@voila.com" epostası ile var
        When Kullanıcı "Garanti Bankası", "Mehmet Çelik" ve "TR38 7654 4343 2132 3213 9932 00" banka hesabını ekliyor
        When Kullanıcı "Denizbank", "Mehmet Çelik" ve "TR38 7654 4343 2132 3213 9932 00" banka hesabını ekliyor
        Then Kullanıcı 2 adet banka bilgisi eklemiş oluyor
    