CREATE TABLE merchant ( 
mdn varchar(12) NOT NULL, 
first_name varchar(20) NOT NULL,
middle_name varchar(20)  NULL,
last_name varchar(20)  NULL,
address varchar(100)  NULL, 
dob varchar(20)  NOT NULL, 
email varchar(50)  NULL, 
password varchar(100) NOT NULL ,
lat numeric(20,10) NOT NULL ,
lng numeric(20,10) NOT NULL ,
shop_name varchar(20) NOT NULL,
category varchar(20) NOT NULL,
rating numeric(3,2) NOT NULL,
is_merchant boolean NOT NULL,
date_created timestamp DEFAULT now(), 
PRIMARY KEY (mdn)
) 

INSERT INTO merchant (mdn,first_name,address,dob,email,password,lat,lng,shop_name,category,rating,is_merchant) 
VALUES('919999635340','Muneer','2270 M M street Delhi-110002','12/12/1990','muneer@gmail.com','123123',123,123,'Hero Bakery','Food',4.5,true);

INSERT INTO merchant (mdn,first_name,address,dob,email,password,lat,lng,shop_name,category,rating,is_merchant) 
VALUES('919794933784','Shiv','94 M Block Sector 21  Delhi-110002','12/12/1990','shiv@gmail.com','123123',123,123,'Sanjay Automobile','Cars',4.5,true);

INSERT INTO merchant (mdn,first_name,dob,password,lat,lng,shop_name,category,rating,is_merchant) 
VALUES('919999635340','Muneer','n','muneer',123,123,'muneer','wwe',4.5,true);

CREATE TABLE offertable ( 
id bigserial NOT NULL, 
mdn varchar(12) NOT NULL REFERENCES merchant(mdn),
discription varchar(20)  NULL,
title varchar(20)  NULL,

expire_time timestamp DEFAULT now(), 
PRIMARY KEY (id)
) 
INSERT INTO offertable (mdn,discription,title) 
VALUES('919999635340','Muneer','n');