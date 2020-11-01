INSERT INTO USER(username, password, first_name, last_name) VALUES('user1', '$2a$10$wHKYunRCZePFRE3oLqsNuOoQr.GyM7h2LzEjgNqXo3NFCbGJzdWz6', 'Coral', 'Stark');
INSERT INTO USER(username, password, first_name, last_name) VALUES('user2', '$2a$10$wHKYunRCZePFRE3oLqsNuOoQr.GyM7h2LzEjgNqXo3NFCbGJzdWz6', 'Kadie ', 'Brennan');
INSERT INTO USER(username, password, first_name, last_name) VALUES('user3', '$2a$10$wHKYunRCZePFRE3oLqsNuOoQr.GyM7h2LzEjgNqXo3NFCbGJzdWz6', 'Vivaan', 'Hastings');
INSERT INTO USER(username, password, first_name, last_name) VALUES('user4', '$2a$10$wHKYunRCZePFRE3oLqsNuOoQr.GyM7h2LzEjgNqXo3NFCbGJzdWz6', 'Arslan', 'Gough');
INSERT INTO USER(username, password, first_name, last_name) VALUES('user5', '$2a$10$wHKYunRCZePFRE3oLqsNuOoQr.GyM7h2LzEjgNqXo3NFCbGJzdWz6', 'Duane', 'Mclean');

INSERT INTO ROLE(name) VALUES('SUPER_ADMIN');
INSERT INTO ROLE(name) VALUES('CONTENT_IMPORTER');

INSERT INTO USER_ROLE(user_id, role_id) VALUES(1,1);
INSERT INTO USER_ROLE(user_id, role_id) VALUES(2,1);
INSERT INTO USER_ROLE(user_id, role_id) VALUES(3,2);
INSERT INTO USER_ROLE(user_id, role_id) VALUES(4,2);
INSERT INTO USER_ROLE(user_id, role_id) VALUES(5,2);

INSERT INTO TAG(name) VALUES('Life Sim');
INSERT INTO TAG(name) VALUES('Simulation');
INSERT INTO TAG(name) VALUES('Typing');