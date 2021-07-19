--Delete Table
drop table users;
--Delete Sequence
drop sequence seq_user_no;
--Create Table
create table users(
    no number(5),
    id varchar2(20) not null unique,
    password varchar2(20) not null,
    name varchar2(20),
    gender varchar2(10),
    primary key(no)
    );
--Create Sequence
create sequence seq_user_no
increment by 1
start with 1
nocache;

--Table Insert
insert into users
values (seq_user_no.nextval, 'thunderkyg', '1234', 'thomaskim', 'male');
    
--Select
select *
from users;

commit;