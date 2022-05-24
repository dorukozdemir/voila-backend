Feature: Kullanıcı voila card üzerinden kişi bilgisi okutuyor
    @US05Last
    Scenario: Kullanıcı voila card üzerinden NFC ile okutunca kullanıcı diğer kullanıcının profil bilgilerine erişiyor
        Given Kullanan kullanıcı "harun3@voila.com" "harun123" ile kayıtlı
        Given Kullanan kullanıcı giriş yapmış durumda
        Given Kullanıcı "mete2@voila.com" eposta ve "mete123" şifresi ile oluşturulduğunda
        Given Kullanıcı "mete2@voila.com" epostası ile var
        Given Örnek sosyal medya verisi kaydedildi
        Given Kullanıcı içeri aktarma ekranında
        Given Profilini görüntüleyen kullanıcı "mete2@voila.com" tokenını göstermiş
        When Kullanıcı adını "Atilla" soyadını "Çağlar" biosunu "stratejist" olarak değiştirdiğinde
        When Kullanıcı "+90" "2124539256" whatsapp numarası giriyor
        When Kullanıcı "+90" "2124539256" telefon numarası giriyor
        When Kullanıcı "+90" "5313271062" telefon numarası giriyor
        When Kullanıcı "mail@sirketim.com" email adresi giriyor
        When Kullanıcı "sirketim.com" bağlantısını ekliyor
        When Kullanıcı "sahibinden.com/portal" bağlantısını ekliyor
        When Kullanıcı "Coza Dijital Akıllı Çözümler Ltd. Şti.", "Baki Dede Sok. Balat Mah. No:19 34087 Fatih/İstanbul", "2140664898", "Fatih" şirketini ekliyor
        #When Kullanıcı "Keys Danışmanlık", "Ankara", "123456789101", "Başkent" şirketini ekliyor
        When Kullanıcı "Garanti Bankası", "Mehmet Çelik" ve "TR38 7654 4343 2132 3213 9932 00" banka hesabını ekliyor
        #When Kullanıcı "Denizbank", "Mehmet Çelik" ve "TR38 7654 4343 2132 3213 9932 00" banka hesabını ekliyor
        When Kullanıcı kartı okutuyor
        Then Kartta tanımlı kullanıcının profil bilgileri ekrana geliyor
    @US05Last
    Scenario: Kullanıcı voila card üzerinden NFC ile okutunca kullanıcı diğer kullanıcının profil bilgileri bulunamadığına dair uyarı ekranı çıkıyor   
        Given Kullanan kullanıcı "harun@voila.com" "harun123" ile kayıtlı
        Given Kullanan kullanıcı giriş yapmış durumda
        Given Kullanıcı içeri aktarma ekranında
        When Kullanıcı kartı okutuyor ve "123412" idli profili görüntülemek istiyor
        Then Kullanıcının olmadığına dair ekran geliyor
