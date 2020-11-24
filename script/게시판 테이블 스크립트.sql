SELECT * FROM board;

CREATE TABLE board(
board_id number PRIMARY KEY,
board_title varchar2(100) NOT NULL,
board_content varchar2(4000) NOT NULL,
board_username varchar2(20) NOT NULL,
board_ip varchar2(15) NOT NULL,
board_wtime varchar2(30) NOT NULL,
board_status char(1) NOT NULL,
board_comment_count number NOT NULL,
board_hit number NOT null
);

create sequence board_seq 
increment by 1
start with 0
maxvalue 999999999
minvalue 0;


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
chat_id number ,
message_id number primary key,
member_no number,
content varchar2(4000),
member varchar2(20),
constraint message_chat_id_fk foreign key(chat_id) references chat(chat_id),
constraint message_registmember_id_fk foreign key(member_no) references registmember(member_no)
);

//message_seq 생성
create sequence seq_message
increment by 1
start with 1;