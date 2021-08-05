--Delete Table
drop table gallery;
--Delete Sequence
drop sequence seq_gallery_no;
--Create Table
create table gallery (
  no        number(10),
  user_no   number(10),
  content   varchar2(1000),
  filePath  varchar2(500),
  orgName   varchar2(200),
  saveName  varchar2(500),
  fileSize  number,
  primary key(no),
  CONSTRAINT gallery_fk FOREIGN KEY (user_no)
  REFERENCES users(no)
);
--Create Sequence
create sequence seq_gallery_no
increment by 1
start with 1
nocache;

--Select
select *
from gallery;

commit;