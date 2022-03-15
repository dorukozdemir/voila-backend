Feature: UserService sınıfı
    Scenario: Kullanıcı oluşturulduğunda profile token oluşturulacak
        When Kullanıcı "mete@voila.com" ve "mete123" ile oluşturulduğunda
        Then Kullanıcının profile tokenı oluşturulacak
    @UserServiceLast
    Scenario: Profile tokendan aramada kullanıcıya ulaşılabilecek
        Given Kullanıcı "mete@voila.com" adresi ile kaydoldu
        When Kullanıcı profile token ı ile arandığında
        Then "mete@voila.com" eposta adresli kullanıcı bulunabilecek