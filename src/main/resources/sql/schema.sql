DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS sessions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS event_counter;
DROP TABLE IF EXISTS discount_counter;

CREATE TABLE events (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  name          VARCHAR(255),
  price         DECIMAL(20, 2),
  rating        VARCHAR(255),
  auditorium_id BIGINT,
  PRIMARY KEY (ID)
);

CREATE TABLE users (
  id       BIGINT NOT NULL AUTO_INCREMENT,
  name     VARCHAR(255),
  email    VARCHAR(255),
  birthday DATE,
  PRIMARY KEY (ID)
);

CREATE TABLE sessions (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  event_id      BIGINT NOT NULL,
  auditorium_id BIGINT NOT NULL,
  date          DATE   NOT NULL,
  duration      DATE   NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (event_id) REFERENCES events (id)
);

CREATE TABLE tickets (
  id         BIGINT NOT NULL AUTO_INCREMENT,
  user_id    BIGINT NOT NULL,
  seat       INT,
  session_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (session_id) REFERENCES sessions (id)
);

CREATE TABLE event_counter (
  event_id         BIGINT NOT NULL,
  get_by_name      INT DEFAULT 0,
  get_ticket_price INT DEFAULT 0,
  book_ticket      INT DEFAULT 0,
  FOREIGN KEY (event_id) REFERENCES events (id),
);

CREATE TABLE discount_counter (
  user_id     BIGINT NOT NULL,
  name  VARCHAR(255),
  count INT
);