Feature: Kullanıcı giriş yapıyor
  Background:
    Given Uygulamada kayıtlı kullanıcı var
    Given Kullanıcı giriş ekranında
  Scenario: Anonim kullanıcının uygulamaya başarılı bir şekilde giriş yapması
    When  Kullanıcı adı "mete@voila.com" girildiğinde
    When Şifre "mete123" girildiğinde
    Then  Kullanıcı başarılı bir şekilde giriş yapması sağlanır
  @US02Last
  Scenario: Anonim kullanııcının uygulamaya girişi başarısız olması
    When  Kullanıcı adı olarak "mete1@voila.com" girildiğinde
    When Şifre olarak "mete123" girildiğinde
    Then Bu eposta ve şifre ile kullanıcı bulunmadığına dair bilgi verilir
