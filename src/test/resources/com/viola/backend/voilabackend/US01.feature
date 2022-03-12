Feature: Kullanıcı kaydoluyor
  Background:
    Given Uygulamada kullanıcı yok
    Given Kullanıcı kayıt ekranında
  Scenario: Anonim kullanıcının uygulamaya başarılı bir şekilde kaydolması
    When  Kullanıcı adı "mete@voila.com" girildiğinde
    When Şifre "mete123" girildiğinde
    Then  Kullanıcı başarılı bir şekilde kaydolduğuna dair mesaj görüntülenir ve kullanıcı girişi sağlanır
  @US01Last
  Scenario: Anonim kullanıcının uygulamaya kaydolmak istediğinde daha önce aynı eposta ile kaydolduğu için başarısız oluyor
    When  Kullanıcı adı "mete@voila.com" girildiğinde
    When Şifre "mete123" girildiğinde
    Then Aynı eposta ile kaydolunduğuna dair cevap döner