CREATE SCHEMA IF NOT EXISTS sms;
SET SCHEMA sms;
------------------------------------------------------------
CREATE TABLE users (
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);
----------------------------------------------------------------
CREATE TABLE groups (
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(200)
);
-----------------------------------------------------------------
CREATE TABLE chat (
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
to_user INTEGER NOT NULL,
from_user INTEGER NOT NULL ,
chat_type VARCHAR(7) NOT NULL,
group_id INTEGER,
FOREIGN KEY (to_user) REFERENCES users(id),
FOREIGN KEY (from_user) REFERENCES users(id),
FOREIGN KEY (group_id) REFERENCES groups(id)
);
------------------------------------------------------------
CREATE TABLE messages (
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT ,
message_timestamp TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
message CLOB(10K)
);
-------------------------------------------------------------
CREATE TABLE user_messages_read_info (
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
user_id INTEGER NOT NULL,
chat_id INTEGER NOT NULL,
message_id INTEGER NOT NULL,
is_read BOOLEAN DEFAULT FALSE,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (chat_id) REFERENCES chat(id),
FOREIGN KEY (message_id) REFERENCES messages(id)
);
------------------------------------------------------------
--CREATE TABLE direct_messages (
--id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
--user_id_1 INTEGER NOT NULL,
--user_id_2 INTEGER NOT NULL,
--chat_id INTEGER NOT NULL,
--FOREIGN KEY (user_id_1) REFERENCES users(id),
--FOREIGN KEY (user_id_2) REFERENCES users(id),
--FOREIGN KEY (chat_id) REFERENCES chat(id),
--)
------------------------------------------------------------
--CREATE TABLE group_messages (
--id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
--user_id INTEGER NOT NULL,
--group_id INTEGER NOT NULL,
--chat_id INTEGER NOT NULL,
--FOREIGN KEY (user_id_1) REFERENCES users(id),
--FOREIGN KEY (group_id) REFERENCES groups(id),
--FOREIGN KEY (chat_id) REFERENCES chat(id),
--)