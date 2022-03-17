Feature: Kullanıcı kendi profilini görüntülüyor
    @After("@UserProfileLast")
    Scenario: Kullanıcı kendi profilini görüntülüyor
        Given Kullanan kullanıcı "harun2@voila.com" "harun123" ile kayıtlı
        Given Kullanıcı "harun2@voila.com" epostası ile var
        Given Örnek sosyal medya verisi kaydedildi
        Given Profilini görecek kullanıcı "harun2@voila.com" ve "harun123" ile giriş yapmış
        When Kullanıcı adını "Atilla" soyadını "Çağlar" biosunu "stratejist" olarak değiştirdiğinde
        When Kullanıcı "+90" "2124539256" whatsapp numarası giriyor
        When Kullanıcı "+90" "2124539256" telefon numarası giriyor
        When Kullanıcı "+90" "5313271062" telefon numarası giriyor
        When Kullanıcı "mail@sirketim.com" email adresi giriyor
        When Kullanıcı "sirketim.com" bağlantısını ekliyor
        When Kullanıcı "sahibinden.com/portal" bağlantısını ekliyor
        When Kullanıcı "Coza Dijital Akıllı Çözümler Ltd. Şti.", "Baki Dede Sok. Balat Mah. No:19 34087 Fatih/İstanbul", "2140664898", "Fatih" şirketini ekliyor
        When Kullanıcı "Keys Danışmanlık", "Ankara", "123456789101", "Başkent" şirketini ekliyor
        When Kullanıcı "Garanti Bankası", "Mehmet Çelik" ve "TR38 7654 4343 2132 3213 9932 00" banka hesabını ekliyor
        When Kullanıcı "Denizbank", "Mehmet Çelik" ve "TR38 7654 4343 2132 3213 9932 00" banka hesabını ekliyor
        When Kullanıcı profilini görüntülüyor
        Then Kullanıcının profili ekrana geliyor
