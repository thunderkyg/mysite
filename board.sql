------------------------------
--Board Table Drop
drop table board;
--Board Sequence Drop
drop sequence seq_board_no;
-------------------------------
--Board Table Create
Create table board (
    board_no number,
    title varchar2(500) not null,
    content varchar2(4000),
    hit number,
    reg_date date not null,
    user_no number,
    primary key (board_no),
    constraint users_fk foreign key (user_no)
    references users(no)
    );
    
--Board Sequence Create
create sequence seq_board_no
increment by 1
start with 1
nocache;

--Input Testing
insert into board
values (seq_board_no.nextval, '123', '123', 0, sysdate, 1);

--Select 예시
select *
from board;
