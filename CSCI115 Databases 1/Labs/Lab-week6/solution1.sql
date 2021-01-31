use csit115;
CREATE TABLE AIRLINE(
    name VARCHAR(30) NOT NULL,
    phone DECIMAL(15) NOT NULL,
    fax DECIMAL(10) NOT NULL, 
    web VARCHAR(50) NOT NULL,
    CONSTRAINT airline_pkey PRIMARY KEY(name),
    CONSTRAINT airline_ckey UNIQUE(phone)
);

CREATE TABLE HOTEL(
    name VARCHAR(30) NOT NULL,  
    location VARCHAR(30) NOT NULL,
    phone DECIMAL(15) NOT NULL,
    CONSTRAINT hotel_pkey PRIMARY KEY(name, location)
);

CREATE TABLE CUSTOMER(
    cnumber DECIMAL(10) NOT NULL,
    first_name VARCHAR(15) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    address VARCHAR(30) NOT NULL,
    phone DECIMAL(15) NOT NULL,
    CONSTRAINT cstmer_pkey PRIMARY KEY(cnumber)
);

CREATE TABLE FLIGHT(
    number CHAR(10) NOT NULL,
    departure VARCHAR(30) NOT NULL,
    destination VARCHAR(30) NOT NULL,
    departure_date_time DATETIME NOT NULL,
    arrive_date_time DATETIME NOT NULL,
    name VARCHAR(30) NOT NULL,
    CONSTRAINT flight_pkey PRIMARY KEY(number, departure_date_time),
    CONSTRAINT airline_fkey FOREIGN KEY(name) REFERENCES AIRLINE(name)
);

CREATE TABLE SEAT(
    number CHAR(10) NOT NULL,
    departure_date_time DATETIME NOT NULL,
    class VARCHAR(8) NOT NULL,
    price DECIMAL(6, 2) NOT NULL,
    number_available DECIMAL(3) NULL,
    CONSTRAINT seat_pkey PRIMARY KEY(number, departure_date_time, class),
    CONSTRAINT flight_fkey1 FOREIGN KEY (number, departure_date_time) 
    REFERENCES FLIGHT(number, departure_date_time), 
    CONSTRAINT seat_check1 CHECK (class in ("FIRST", "BUSINESS", "ECONOMY"))
);

CREATE TABLE ROOM(
    type VARCHAR(6) NOT NULL, 
    name VARCHAR(30) NOT NULL,
    location VARCHAR(30) NOT NULL,
    price DECIMAL(6, 2) NOT NULL,
    number_available DECIMAL(3) NULL,
    CONSTRAINT room_pkey PRIMARY KEY(type, name, location),
    CONSTRAINT hotel_fkey1 FOREIGN KEY (name, location) 
    REFERENCES HOTEL(name, location),
    CONSTRAINT room_check1 CHECK (type in ("SINGLE", "DOUBLE", "TRIPLE", "QUAD"))
);

CREATE TABLE HOTELBOOKING(
    arrive_date DATETIME NOT NULL,
    number_of_night DECIMAL(2) NOT NULL,
    cnumber DECIMAL(10) NOT NULL,
    name VARCHAR(30) NOT NULL, 
    location VARCHAR(30) NOT NULL,
    date_time DATETIME NOT NULL, 
    CONSTRAINT hotelbook_pkey PRIMARY KEY(cnumber, name, location, date_time),
    CONSTRAINT cstmer_fkey1 FOREIGN KEY(cnumber) REFERENCES CUSTOMER(cnumber),
    CONSTRAINT hotel_fkey2 FOREIGN KEY(name, location) REFERENCES HOTEL(name, location)
);

CREATE TABLE FLIGHTBOOKING(
    number CHAR(10) NOT NULL, 
    departure_date_time DATETIME NOT NULL,
    cnumber DECIMAL(10) NOT NULL,
    date_time DATETIME NOT NULL,
    CONSTRAINT flightbook_pkey PRIMARY KEY(number, departure_date_time, cnumber),
    CONSTRAINT flight_fkey2 FOREIGN KEY(number, departure_date_time)
    REFERENCES FLIGHT(number, departure_date_time),
    CONSTRAINT cstmer_fkey2 FOREIGN KEY(cnumber) REFERENCES CUSTOMER(cnumber)
);

DROP TABLE FLIGHTBOOKING;
DROP TABLE HOTELBOOKING;
DROP TABLE ROOM;
DROP TABLE SEAT;
DROP TABLE FLIGHT;
DROP TABLE CUSTOMER;
DROP TABLE HOTEL;
DROP TABLE AIRLINE;



