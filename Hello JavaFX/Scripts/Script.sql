--CREATE TABLE board(
--	board_id number NOT NULL PRIMARY key,
--	board_title varchar2(100) NOT NULL,
--	board_content varchar2(4000) NOT NULL,
--	board_username varchar2(20) NOT NULL,
--	board_ip varchar2(10) NOT NULL,
--	board_wtime date NOT NULL,
--	board_status char(1) NOT NULL,
--	board_comment_count number NOT NULL,
--	board_hit number NOT NULL
--);

--SELECT * FROM board;

--ALTER TABLE board RENAME TO board_user1104;

create sequence board_seq 
increment by 1
start with 0
MAXVALUE 999999999
minvalue 0;

insert into board(board_id, board_title, board_content, board_username, board_ip, board_wtime, board_status, board_comment_count, board_hit) 
values(board_seq.nextval, 'test', 'Test', 'test', 'test', to_char(sysdate, 'yyyy-mm-dd hh24:mm:ss'), 0,  0, 0);
SELECT * FROM board;