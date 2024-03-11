INSERT INTO users(username, password) VALUES('anupam', 'password');
INSERT INTO users(username, password) VALUES('nikita', 'password');
INSERT INTO users(username, password) VALUES('bobo', 'password');

INSERT INTO groups(name) VALUES('group1');

INSERT INTO user_groups(user_id, group_id) VALUES (1,1);
INSERT INTO user_groups(user_id, group_id) VALUES (2,1);

INSERT INTO messages(sender_id,group_id,message) VALUES(1,null,'hello nikki');
INSERT INTO messages(sender_id,group_id,message) VALUES(1,null,'supp???');
INSERT INTO messages(sender_id,group_id,message) VALUES(1,1,'Access token mechanism can be a session-less one like JWT or a sessionful one. Any assumptions made around tokens or messaging can be stated in the readme and implemented in code. Candidates are encouraged to make any other assumptions necessary to implement the indicated functionalities of a simple messaging server.');

INSERT INTO read_status (message_id,user_id) VALUES (1,1);
INSERT INTO read_status (message_id,user_id) VALUES (2,2);
INSERT INTO read_status (message_id,user_id) VALUES (3,1);
