Feature: Kullanıcı şifresini yenilemek istiyor
  @US03Last
  Scenario: Anonim kullanıcı şifresini sıfırlama işlemi başlatıyor
    Given Uygulamada kayıtlı kullanıcı var
    Given Kullanıcı şifre unuttum ekranında
    When  Kullanıcı adı için "mete@voila.com" girildiğinde
    When Şifremi yenile butonuna tıklandığında
    Then Kullanıcıya e-posta gidiyor
  Scenario: Kullanıcının sıfırlamak istediği kullanıcı adında kullanıcı bulunmaması durumunda
    Given Uygulamada kullanıcı yok
    Given Kullanıcı şifre unuttum ekranında
    When  Kullanıcı adı için "mete@voila.com" girildiğinde
    When Şifremi yenile butonuna tıklandığında
    Then Kullanıcıya genel bir bu eposta ile bağlantılı kullanıcı varsa şifre sıfırlama epostası gönderilmiştir mesajı
