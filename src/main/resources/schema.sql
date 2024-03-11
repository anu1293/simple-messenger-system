CREATE SCHEMA IF NOT EXISTS sms;
SET SCHEMA sms;
------------------------------------------------------------
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);
----------------------------------------------------------------
CREATE TABLE groups (
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(200) NOT NULL
);
-----------------------------------------------------------------
CREATE TABLE user_groups (
user_id INTEGER NOT NULL,
group_id INTEGER NOT NULL,
PRIMARY KEY (user_id, group_id),
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (group_id) REFERENCES groups(id)
);
------------------------------------------------------------
CREATE TABLE messages (
id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT ,
message_timestamp TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
sender_id INT NOT NULL,
group_id INT,
message CLOB(10K),
FOREIGN KEY (sender_id) REFERENCES users(user_id),
FOREIGN KEY (group_id) REFERENCES groups(group_id)
);
-------------------------------------------------------------
CREATE TABLE read_status (
    message_id INT,
    user_id INT,
    is_read BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (message_id, user_id),
    FOREIGN KEY (message_id) REFERENCES messages(message_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
-------------------------------------------------------------