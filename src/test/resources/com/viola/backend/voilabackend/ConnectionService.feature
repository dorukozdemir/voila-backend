Feature: İki kullanıcı arasındaki bağlantılar
    @ConnectionServiceLast
    Scenario: Bir kullanıcı diğer kullanıcıyı ekliyor
        Given Kullanıcı 1 "mete@voila.com" eposta ve "mete123" şifresi ile oluşturulduğunda
        Given Kullanıcı 2 "doruk@voila.com" eposta ve "doruk123" şifresi ile oluşturulduğunda
        Given Kullanıcı 1 kullanıcı 2 yi takip ediyor
        Then Kullanıcı 1 bağlantılarını sorguladığında kullanıcı 2 yi görüyor
    