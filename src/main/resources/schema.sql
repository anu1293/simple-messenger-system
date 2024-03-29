CREATE SCHEMA IF NOT EXISTS sms;
SET SCHEMA sms;
------------------------------------------------------------
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
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
FOREIGN KEY (sender_id) REFERENCES users(id),
FOREIGN KEY (group_id) REFERENCES groups(id)
);
-------------------------------------------------------------
CREATE TABLE read_status (
    message_id INT,
    user_id INT,
    is_read BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (message_id, user_id),
    FOREIGN KEY (message_id) REFERENCES messages(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
-------------------------------------------------------------
CREATE TABLE blocked_conversation (
    user_id1 INT NOT NULL,
    user_id2 INT NOT NULL,
    PRIMARY KEY (user_id1, user_id2),
    FOREIGN KEY (user_id1) REFERENCES users(id),
    FOREIGN KEY (user_id2) REFERENCES users(id)
);