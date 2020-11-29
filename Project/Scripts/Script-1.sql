CREATE TABLE board_group(
board_group_id	number primary key,	
board_group_title varchar(100) not null,
board_group_username varchar2(20) not null,
board_group_wtime	varchar2(30) not null,
board_group_status char(1) not null
);

select * from board_group; 
--where board_group_status = 0;

create sequence board_group_seq
increment by 1 
start with 0
maxvalue 999999999
minvalue 0;

SELECT * FROM board_group;

CREATE TABLE board(
board_id number PRIMARY KEY,
board_group_id number,
board_title varchar2(100) NOT NULL,
board_content varchar2(4000) NOT NULL,
board_username varchar2(20) NOT NULL,
board_ip varchar2(15) NOT NULL,
board_wtime varchar2(30) NOT NULL,
board_status char(1) NOT NULL,
board_comment_count number NOT NULL,
board_hit number NOT NULL,
constraint board_group_id_fk foreign key(board_group_id) references board_group(board_group_id)
);

create sequence board_seq 
increment by 1
start with 0
maxvalue 999999999
minvalue 0;

CREATE TABLE cmmt(
cmmt_id NUMBER NOT null PRIMARY KEY,
board_id NUMBER NOT NULL,
cmmt_username varchar2(20) NOT NULL,
cmmt_content varchar2(4000) NOT NULL,
cmmt_wtime varchar2(30) NOT NULL,
cmmt_ip varchar(15) NOT NULL,
constraint board_id_fk foreign key(board_id) references board(board_id)
);

create sequence cmmt_seq
increment by 1
start with 0
maxvalue 999999999
minvalue 0;

drop table board;
drop table cmmt;
drop sequence board_seq; 
drop sequence cmmt_seq;