INSERT INTO users(username, password) VALUES('anupam', 'password');
INSERT INTO users(username, password) VALUES('nikita', 'password');
INSERT INTO users(username, password) VALUES('bobo', 'password');

INSERT INTO messages(message) VALUES('hello nikki');
INSERT INTO messages(message) VALUES( 'supp???');
INSERT INTO messages(message) VALUES('Access token mechanism can be a session-less one like JWT or a sessionful one. Any assumptions made around tokens or messaging can be stated in the readme and implemented in code. Candidates are encouraged to make any other assumptions necessary to implement the indicated functionalities of a simple messaging server.');

INSERT INTO groups(name) VALUES('group1');

INSERT INTO chat(to_user ,from_user,chat_type,group_id) VALUES (1,2,'DIRECT',null);
INSERT INTO chat(to_user ,from_user,chat_type,group_id) VALUES (1,3,'DIRECT',null);
INSERT INTO chat(to_user ,from_user,chat_type,group_id) VALUES (1,3,'GROUP',1);


INSERT INTO user_messages_read_info (user_id,chat_id,message_id) VALUES (1,1,1);
INSERT INTO user_messages_read_info (user_id,chat_id,message_id) VALUES (1,2,2);
INSERT INTO user_messages_read_info (user_id,chat_id,message_id) VALUES (1,3,3);
