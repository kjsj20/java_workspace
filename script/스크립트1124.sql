SELECT rownum , BOARD_ID, BOARD_STATUS, board_title, BOARD_WTIME FROM BOARD WHERE rownum > 11 and rownum < 20 ORDER BY BOARD_ID;

select * from board where  board_status = 0  AND  rownum < 20  ORDER BY BOARD_ID DESC;

select * from (SELECT rownum num,board_id, board_title, BOARD_CONTENT , BOARD_USERNAME, BOARD_IP , BOARD_WTIME , BOARD_STATUS , BOARD_COMMENT_COUNT, BOARD_HIT 
FROM board 
WHERE BOARD_STATUS  = 0 
ORDER BY board_id) 
WHERE num >= 1 
AND num <= 10

SELECT * FROM BOARD b ;

UPDATE board SET BOARD_HIT  = NVL(TO_NUMBER(BOARD_HIT), 0) + 1 WHERE  BOARD_ID = 28; 

SELECT rownum, board_id, board_title, BOARD_CONTENT , BOARD_USERNAME, BOARD_IP , BOARD_WTIME , BOARD_STATUS , BOARD_COMMENT_COUNT, BOARD_HIT FROM board WHERE rownum >= 1 AND rownum <= 10  ORDER BY BOARD_ID; 

select * from (SELECT rownum num,board_id, board_title, BOARD_CONTENT , BOARD_USERNAME, BOARD_IP ,
BOARD_WTIME , BOARD_STATUS , BOARD_COMMENT_COUNT, BOARD_HIT FROM board WHERE BOARD_STATUS  = 0 ORDER BY board_id) WHERE num >= 11 AND num <= 20

insert into board values(	board_seq.nextval, '안녕하세우',	'안녕하삼',	'jskim',	'192.168.25.26',	to_char(sysdate, 'yyyy-mm-dd hh24:mm:ss'),	0,	0,	0);

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
start with 1;

create sequence seq_chatmember
increment by 1
start with 1;

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
create sequence chatmember_seq
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
start with 1;

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
INCREMENT BY 1
START WITH 1;