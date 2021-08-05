------------------------------
--Guestbook Table Drop
drop table guestbook;
--Guestbook Sequence Drop
drop sequence seq_guestbook_no;
-------------------------------
--Guestbook Table Create
Create table guestbook (
    guestbook_no number(5),
    name varchar2(80) not null,
    password varchar2(20) not null,
    content varchar2(2000),
    reg_date date,
    primary key (guestbook_no)
    );
--Guestbook Sequence Create
create sequence seq_guestbook_no
increment by 1
start with 1
nocache;

--Table Insert
insert into guestbook
values (seq_guestbook_no.nextval, 'thunderkyg', '1234', 'CONTENT!!!', sysdate);

--Select 예시
select *
from guestbook;