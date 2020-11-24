create table board_member(
  member_id number primary key,
  m_id varchar2(20),
  m_pass varchar2(20),
  m_name varchar2(20),
  regdate DATE DEFAULT sysdate
 );

 CREATE SEQUENCE seq_board_member INCREMENT BY 1 START WITH 1;

DROP TABLE board_member;

desc board_member;
 
CREATE TABLE board(
board_id NUMBER PRIMARY KEY
,title varchar2(100)
,writer varchar2(20)
,content clob
,regdate DATE DEFAULT sysdate
,hit NUMBER default 0
);

CREATE SEQUENCE seq_board INCREMENT BY 1 START WITH 1;

SELECT * FROM board;