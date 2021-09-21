DROP DATABASE IF EXISTS hotelpro;
CREATE DATABASE hotelpro;
USE hotelpro;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
	username VARCHAR(12) NOT NULL, 
	password VARCHAR(20) NOT NULL, 
	Name VARCHAR(15) NOT NULL, 
	age INT NOT NULL,
	gender ENUM ('M', 'F', 'D'),
    city VARCHAR(12) NOT NULL,
	govermentId VARCHAR(12) NOT NULL,
	PRIMARY KEY (username),
    unique key(username)
     
);

insert into user (userName, password,Name, age, gender, city,govermentId)
values ("cust1id", "cust1pwd", "priya", 28, "F","chennai",'123456789012');

insert into user (userName, password,Name, age, gender, city,govermentId)
values ("cust2id", "cust2pwd", "kiran", 50, "M","banglore",'901212345678');



SELECT * FROM user;


DROP TABLE IF EXISTS Adminsec;
CREATE TABLE Adminsec(
username VARCHAR(12) NOT NULL, 
	password VARCHAR(20) NOT NULL, 
	Name VARCHAR(15) NOT NULL, 
	age INT NOT NULL,
	gender ENUM ('M', 'F', 'D'),
    city VARCHAR(12) NOT NULL,
	govermentId VARCHAR(12) NOT NULL,
	PRIMARY KEY (username)
    );
    
insert into Adminsec (userName, password, Name, age, gender, city,govermentId)
values ("manager", "managerpwd", "zara", 18, "F", "chennai",'987456123032');

select * from Adminsec;

DROP TABLE IF EXISTS room;
CREATE TABLE room (
	roomId INT(10) NOT NULL AUTO_INCREMENT,
	costPerNight DOUBLE(10,2) NOT NULL,
	roomType VARCHAR(50)NOT NULL,
	PRIMARY KEY (roomId) 
);

insert into room (costPerNight, roomType)
values (200, "The Shack 1"), (200, "The Shack 2"), (200, "The Shack 3"), (200, "The Shack 4"), (200, "The Shack 5"), 
(500, "Little Hut 6"), (500, "Little Hut 7"), (500, "Little Hut 8"), (500, "Little Hut 9"), (500, "Little Hut 10"), 
(800, "Modern 11"), (800, "Modern 12"), (800, "Modern 13"), (800, "Modern 14"), (800, "Modern 15"),
(1000, "Luxury 16"), (1000, "Luxury 17"), (1000, "Luxury 18"),(1000, "Luxury 19"),(1000, "Luxury 20"), (2000, "Presidential Suite 21");

SELECT * FROM room;

DROP TABLE IF EXISTS reservation;
CREATE TABLE reservation (
	
	roomId INT(10) NOT NULL,
	 userName VARCHAR(12) NOT NULL,       
	startDate DATE NOT NULL,
	endDate DATE NOT NULL,
	numOfDays INT(10),
    totalCost int,
	PRIMARY KEY (roomId), 
	FOREIGN KEY (roomId) REFERENCES room(roomId),
	FOREIGN KEY (userName) REFERENCES user(userName) 
    ON DELETE CASCADE 
	ON UPDATE CASCADE
);


     
insert into reservation (roomId,userName, startDate, endDate,numOfDays,totalCost)
values (6, "cust1id"," 2020-11-20 "," 2020-11-25 ", 5,2500 );
insert into reservation (roomId,userName, startDate, endDate,numOfDays,totalCost)
values (7, "cust2id"," 2020-11-24 "," 2020-11-28 ", 4 ,2000);

SELECT * FROM reservation;

SELECT * FROM user;







  



























