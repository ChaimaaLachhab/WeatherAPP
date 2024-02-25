create database weatherApp;
use weatherApp;
create table City(
cityId int primary key not null,
cityName varchar(30),
currentTemperature double,
currentHumidity double,
currentWindSpeed double
);

create table CityHistory(
historicalDataId int primary key not null,
cityId int,
eventDate date,
temperature double,
foreign key (cityId) references city(cityId)
);

select * from City;
