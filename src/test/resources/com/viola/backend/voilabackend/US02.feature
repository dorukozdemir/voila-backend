Feature: Kullanıcı giriş yapıyor
  Background:
    Given Uygulamada kayıtlı kullanıcı var
    Given Kullanıcı giriş ekranında
  Scenario: Anonim kullanıcının uygulamaya başarılı bir şekilde giriş yapması
    When  Kullanıcı adı "mete@voila.com" girildiğinde
    When Şifre "mete123" girildiğinde
    Then  Kullanıcı başarılı bir şekilde giriş yapması sağlanır
  Scenario: Anonim kullanııcının girdiği eposta ile kullanıcı olmaması
    When  Kullanıcı adı olarak "abc@voila.com" girildiğinde
    When Şifre olarak "mete1234" girildiğinde
    Then Bu epostaya ait kullanıcı bulunmadığına dair bilgi verilir
  @US02Last
  Scenario: Anonim kullanııcının girdiği şifrenin yanlış olması
    When  Kullanıcı adı olarak "mete@voila.com" girildiğinde
    When Şifre olarak "mete1234" girildiğinde
    Then Bu kullanıcının şifresinin yanlış olduğuna dair bilgi verilir
