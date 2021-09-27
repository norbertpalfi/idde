create database if not exists UsedCars;
use UsedCars;

drop table if exists UsedCar;
drop table if exists Owner;
create table Owner (
    id int primary key not null auto_increment,
    name varchar(512),
    city varchar(512)
) engine=InnoDB;
desc Owners;

create table UsedCar (
    id int primary key not null auto_increment,
    kilometers int,
    horsepower int,
    ownerId int,
    carModel varchar(512),
    startDate int,
    CONSTRAINT ownerId FOREIGN KEY (ownerId) REFERENCES Cars(id)
) engine=InnoDB;
desc Cars;

insert into Owner (id, name, city) values (default, 'Sarbu Maricescu', 'Targoviste');
insert into Owner (id, name, city) values (default, 'Ela Guran', 'Timisoara');
insert into Owner (id, name, city) values (default, 'Moise Koncz', 'Cluj-Napoca');

insert into UsedCar (id, kilometers, horsepower, ownerId, carModel, startDate) values (default, 100000, 120, 1, 'Golf 4', 2005);
insert into UsedCar (id, kilometers, horsepower, ownerId, carModel, startDate) values (default, 50000, 90, 2, 'Dacia Logan', 2004);
insert into UsedCar (id, kilometers, horsepower, ownerId, carModel, startDate) values (default, 100000, 113, 3, 'Dacia Duster', 2014);


