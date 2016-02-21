DROP  TABLE IF EXISTS tickets;
DROP  TABLE IF EXISTS sessions;
DROP  TABLE IF EXISTS users;
DROP  TABLE IF EXISTS events;

create table events (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(255),
    price DECIMAL(20,2),
    rating varchar(255),
    auditorium_id BIGINT,
    PRIMARY KEY (ID)
);

create table users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(255),
    email varchar(255),
    PRIMARY KEY (ID)
);

create table sessions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    event_id BIGINT NOT NULL,
    auditorium_id BIGINT NOT NULL,
    date DATE NOT NULL,
    duration DATE NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (event_id) REFERENCES events(id)
);

create table tickets (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id BIGINT NOT NULL ,
    event_id BIGINT NOT NULL,
    category VARCHAR(255),
    place INT,
    PRIMARY KEY (ID),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (event_id) REFERENCES events(id)
);