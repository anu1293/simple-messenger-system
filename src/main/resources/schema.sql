CREATE SCHEMA IF NOT EXISTS sms;
SET SCHEMA sms;
------------------------------------------------------------
CREATE TABLE users (
username VARCHAR(100) NOT NULL PRIMARY KEY,
password VARCHAR(100) NOT NULL
);

CREATE TABLE users_messages (
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
to_user VARCHAR(100) NOT NULL,
from_user VARCHAR(100) NOT NULL,
message_timestamp TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
is_read BOOLEAN DEFAULT FALSE,
message CLOB(10K),
FOREIGN KEY (to_user) REFERENCES users(username),
FOREIGN KEY (from_user) REFERENCES users(username)
)
------------------------------------------------------------