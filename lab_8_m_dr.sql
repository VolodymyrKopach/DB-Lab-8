DROP DATABASE IF EXISTS Lab_8_m_dr;

CREATE DATABASE Lab_8_m_dr;

USE Lab_8_m_dr;

CREATE TABLE player
(
id_of_player int NOT NULL auto_increment ,
code_of_player int NULL,
primary key (id_of_player)
);

CREATE TABLE basketball_club
(
id_of_basketball_club int NOT NULL auto_increment,
name_of_basketball_club nvarchar(50) NULL,
finances nvarchar(50) NULL,
id_of_player int NULL,
primary key (id_of_basketball_club)
);

ALTER TABLE basketball_club

  add constraint FK_basketball_club_and_player FOREIGN KEY (id_of_player) REFERENCES player (id_of_player);


CREATE TABLE sponsor
(
id_of_sponsor int NOT NULL auto_increment  ,
name_of_sponsor nvarchar(50) NULL,
surname_of_sponsor nvarchar(50) NULL,
primary key (id_of_sponsor)
);


CREATE TABLE basketball_club_sponsor
(
id int  NOT NULL auto_increment ,
id_of_sponsor int NULL,
id_of_basketball_club int NULL,
primary key (id)
);

ALTER TABLE basketball_club_sponsor

  add constraint FK_lecturer_id FOREIGN KEY(id_of_sponsor) REFERENCES sponsor (id_of_sponsor),
  add constraint FK_basketball_club_id FOREIGN KEY(id_of_basketball_club) REFERENCES basketball_club (id_of_basketball_club);


INSERT player(code_of_player) VALUES
(21),
(22),
(23),
(24),
(25),
(26);

INSERT basketball_club(name_of_basketball_club, finances, id_of_player)
values('name_1', 'finances_1', 1),
('name_2', 'finances_2', 2),
('name_3', 'finances_3', 3),
('name_4', 'finances_4', 4),
('name_5', 'finances_5', 5),
('name_6', 'finances_6', 6),
('name_7', 'finances_7', 1),
('name_8', 'finances_8', 2),
('name_9', 'finances_9', 3),
('name_10', 'finances_10', 4);



INSERT sponsor(name_of_sponsor, surname_of_sponsor) 
VALUES('name_1','surname_1'),
('name_2','surname_2'),
('name_3','surname_3'),
('name_4','surname_4'),
('name_5','surname_5'),
('name_6','surname_6'),
('name_7','surname_7'),
('name_8','surname_8'),
('name_9','surname_9'),
('name_10','surname_10');
    

INSERT basketball_club_sponsor(id_of_basketball_club, id_of_sponsor) 
VALUES(1,1),
(2,2),
(3,3),
(4,4),
(5,4),
(6,4),
(7,1),
(8,2),
(9,3),
(10,4);

