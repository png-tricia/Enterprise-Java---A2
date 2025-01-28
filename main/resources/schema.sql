CREATE TABLE music_tickets (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(25), 
	phone VARCHAR(15), 
	email VARCHAR(30), 
	gender VARCHAR(15),
	age_group VARCHAR(10),
	event_date VARCHAR(30), 
	ticket_price double,
	survey VARCHAR (20)
);