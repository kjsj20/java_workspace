CREATE TABLE cmmt(
cmmt_id NUMBER NOT null PRIMARY KEY,
board_id NUMBER NOT NULL,
cmmt_username varchar2(20) NOT NULL,
cmmt_content varchar2(500) NOT NULL,
cmmt_wtime varchar2(30) NOT NULL,
cmmt_ip varchar(15) NOT NULL
);

create sequence cmmt_seq
increment by 1
start with 0
maxvalue 999999999
minvalue 0;

SELECT * FROM board ORDER BY board_id desc;
SELECT * FROM CMMT;
SELECT * FROM CMMT WHERE 
SELECT * FROM CMMT WHERE BOARD_ID = 549 ORDER BY CMMT_ID DESC
SELECT * FROM CMMT WHERE BOARD_ID = 550 ORDER BY cmmt_id desc;

DROP TABLE cmmt;

drop sequence cmmt_seq;

//쿼리문
//chat생성
create table chat(
chat_id number primary key ,
chat_title varchar2(20),
chat_status char(1)
);

//seq_chat 생성
create sequence seq_chat
increment by 1
start with 0
maxvalue 999999999
minvalue 0;

create sequence seq_chatmember
increment by 1
start with 0
maxvalue 999999999
minvalue 0;

//seq_chatmember빼기!

//ChatMember생성
create table chatmember(
chat_id number ,
chatmember_id number primary key,
member_no number,
constraint chat_id_fk foreign key(chat_id) references chat(chat_id),
constraint registmember_id_fk foreign key(member_no) references registmember(member_no) 
);

//chatmember_seq 생성
create sequence seq_chatmember
increment by 1
start with 1;

//(채팅로그)message 생성
create table message(
message_id number primary key,
chat_id number ,
member_no number,
content varchar2(4000),
chat_time varchar2(30),
constraint message_chat_id_fk foreign key(chat_id) references chat(chat_id),
constraint message_registmember_id_fk foreign key(member_no) references registmember(member_no)
);


//message_seq 생성
create sequence seq_message
increment by 1
start with 0
maxvalue 999999999
minvalue 0;

//회원테이블생성
create table RegistMember(
member_no number
,member_name varchar(20)
,member_email varchar(200)
,member_id varchar(20)
,member_password varchar(20)
,member_rank varchar(30)
,PRIMARY key(member_no)   
);

CREATE SEQUENCE seq_RegistMember
increment by 1
start with 0
maxvalue 999999999
minvalue 0;