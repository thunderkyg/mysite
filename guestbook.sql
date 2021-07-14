------------------------------
--Guestbook Table Drop
drop table guestbook;
--Guestbook Sequence Drop
drop sequence seq_guestbook_no;
-------------------------------
--Guestbook Table Create
Create table guestbook (
    guestbook_no number(5),
    name varchar2(80),
    password varchar2(20),
    content varchar2(2000),
    reg_date date,
    primary key (guestbook_no)
    );
--Guestbook Sequence Create
create sequence seq_guestbook_no
increment by 1
start with 1
nocache;
--Select 예시
select *
from guestbook;