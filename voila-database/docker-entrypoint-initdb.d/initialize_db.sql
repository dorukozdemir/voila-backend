\c voiladb;

DROP TABLE IF EXISTS v_users_t;
CREATE TABLE v_users_t (
  id serial PRIMARY KEY,
  adSoyad varchar(100) DEFAULT NULL,
  kayitTarihi TIMESTAMP NOT NULL,
  password varchar(255) NOT NULL,
  eposta VARCHAR( 255 ) UNIQUE NOT NULL
);

insert into v_users_t (adSoyad, kayitTarihi, password, eposta) values ('mete', now(), '$2a$10$B3A0YH1z0U56y.lz6ixjOejJLtrh8lHa4q.Kf.VALWCMAww0id88e','mete@voila.com');
insert into v_users_t (adSoyad, kayitTarihi, password, eposta) values ('harun', now(), '$2a$10$PIFRI1nZi8kKWiQrDgROteFu2qb9yxCBmCPF9r1OwgYANsun6jCgC','harun@voila.com');
insert into v_users_t (adSoyad, kayitTarihi, password, eposta) values ('doruk', now(), '$2a$10$P/Xl4V9KRAkWz3R6hzECLO6gustYI.uDoeQrk8TF0AYTQdHVxN5OG','doruk@voila.com');