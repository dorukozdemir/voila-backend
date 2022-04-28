Feature: Kullanıcı bağlantılarını listeliyor
    Scenario: Kullanıcının hiç bağlantısı yok
        Given Kullanıcı1 "mete@voila.com" eposta ve "mete123" şifresi ile giriş yapmış 
        When Kullanıcı bağlantılarını görüntüle ekranına geçiyor
        Then Hiç bağlantısı olmadığını görüyor
    @UserConnectionsLast
    Scenario: Kullanıcı başka bir kullanıcının profiline bakıyor, kullanıcı bağlantılarına ekleniyor
        Given Kullanıcı1 "mete@voila.com" eposta ve "mete123" şifresi ile giriş yapmış
        Given Kullanıcı2yi "doruk@voila.com" eposta ve "doruk123" şifresi olan kullanıcının profiline bakıyor
        When Kullanıcı bağlantılarını görüntüle ekranına geçiyor
        Then Bağlantılar listesinde profiline baktığı kullanıcıyı görüntülüyor
