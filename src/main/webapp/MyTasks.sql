drop database MyTasks;

create database MyTasks;

use MyTasks;

create table ToDo (
    id          int not null auto_increment,
    description varchar(80),
    instructor  varchar(60),
    duedate     date,
    submitted   boolean,
    userID      int,
    PRIMARY KEY (id)
);

create table User (
    id          int not null auto_increment,
    name  varchar(60),
    email varchar(80),
    password varchar(20),
    PRIMARY KEY (id)
);

create table Instructor (
    id          int not null auto_increment,
    name        varchar(60),
    PRIMARY KEY (id)
);

insert into ToDo ( description, instructor, duedate, userID ) values
    ('assignment 4', 'Cheng Thao', "20200923", 1),
    ('assignment 9', 'Cheng Thao', '2020-11-04', 1);

insert into User ( name, email, password ) values
    ('andrew Roe', 'andrew.roe@my.saintpaul.edu', 'passwd'),
    ('Bob Smith', 'bsmith@live.com', 'pass'),
    ('Joe User', 'joe@user.org', 'user');

insert into Instructor ( name ) values
    ('Cheng Thao'),
    ('James Woodcock'),
    ('Khiet Nguyen'),
    ('Susan Metoxen'),
    ('Mary Anderson'),
    ('Dr. John Smith'),
    ('First grade teacher'),
    ('Spouse'),
    ('Creditors'),
    ('Family'),
    ('Karen E Johnson, phd');
     