Feature: Kullanıcı şifresini yenilemek istiyor
  Scenario: Anonim kullanıcı şifresini sıfırlama işlemi başlatıyor
    Given Uygulamada kayıtlı kullanıcı var
    Given Kullanıcı "mete@voila.com" adresi ile işlem yapmak istiyor
    Given Kullanıcı şifre unuttum ekranında
    When  Kullanıcı adı için "mete@voila.com" girildiğinde
    When Şifremi yenile butonuna tıklandığında
    Then Kullanıcıya e-posta gidiyor
  @US03Last
  Scenario: Kullanıcı gelen mail ile yeni şifre oluşturma ekranına geliyor ve yeni şifresini belirliyor
    Given Uygulamada kullanıcı yok
    Given Kullanıcı "mete@voila.com" adresi ile işlem yapmak istiyor
    Given Kullanıcı eposta ile gelen linke tıklıyor ve uygulama şifre sıfırlama ekranı ile açılıyor
    When Kullanıcı şifresini "mete1234" olarak giriyor
    When Kullanıcı Şifreyi Değiştir tuşuna basıyor
    Then Şifre başarılı bir şekilde güncelleniyor
  #Scenario: Kullanıcı gelen e-posta ile yeni şifre oluşturma ekranına geliyor belirlemeye çalıştığında link süresinin dolduğunu öğreniyor
  #  Given Kullanıcı "mete@voila.com" adresi ile işlem yapmak istiyor
  #  Given Kullanıcının reset token süresi dolmuş
  #  Given Kullanıcı eposta ile gelen linke tıklıyor ve uygulama şifre sıfırlama ekranı ile açılıyor
  #  When Kullanıcı şifresini "mete1234" olarak giriyor
  #  When Kullanıcı Şifreyi Değiştir tuşuna basıyor
  #  Then Şifre değiştirme linkinin süresinin dolduğu bilgisi veriliyor
  Scenario: Kullanıcının sıfırlamak istediği kullanıcı adında kullanıcı bulunmaması durumunda
    Given Uygulamada kullanıcı yok
    Given Kullanıcı şifre unuttum ekranında
    When  Kullanıcı adı için "mete@voila.com" girildiğinde
    When Şifremi yenile butonuna tıklandığında
    Then Kullanıcıya genel bir bu eposta ile bağlantılı kullanıcı varsa şifre sıfırlama epostası gönderilmiştir mesajı
