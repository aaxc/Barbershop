DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS services;
DROP TABLE IF EXISTS clients;
DROP TABLE IF EXISTS genders;

CREATE TABLE services
(
    id    int NOT NULL AUTO_INCREMENT,
    name  varchar(100),
    price int,
    PRIMARY KEY (id)
);
CREATE TABLE genders
(
    id   int NOT NULL AUTO_INCREMENT,
    name varchar(100),
    PRIMARY KEY (id)
);
CREATE TABLE clients
(
    id         int NOT NULL AUTO_INCREMENT,
    first_name varchar(100),
    last_name  varchar(100),
    email      varchar(100),
    phone      varchar(20),
    gender_id  int,
    PRIMARY KEY (id)
);
ALTER TABLE clients add foreign key (gender_id) references genders (id);

CREATE TABLE orders
(
    service_id     int,
    client_id      int,
    timestamp_from bigint(13),
    timestamp_till bigint(13),
    cancelled      tinyint(1),
    finished       tinyint(1)
);
ALTER TABLE orders add foreign key (service_id) references services (id);
ALTER TABLE orders add foreign key (client_id) references clients (id);
