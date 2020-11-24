SELECT rownum , BOARD_ID, BOARD_STATUS, board_title, BOARD_WTIME FROM BOARD WHERE rownum > 11 and rownum < 20 ORDER BY BOARD_ID;

select * from board where  board_status = 0  AND  rownum < 20  ORDER BY BOARD_ID DESC;

select * from (SELECT rownum num,board_id, board_title, BOARD_CONTENT 
, BOARD_USERNAME, BOARD_IP , BOARD_WTIME , BOARD_STATUS , BOARD_COMMENT_COUNT
, BOARD_HIT FROM board WHERE BOARD_STATUS  = 0 ORDER BY board_id) WHERE num >= 11 AND num <= 20

SELECT * FROM BOARD b ;

UPDATE board SET BOARD_HIT  = NVL(TO_NUMBER(BOARD_HIT), 0) + 1 WHERE  BOARD_ID = 28; 

SELECT rownum, board_id, board_title, BOARD_CONTENT , BOARD_USERNAME, BOARD_IP , BOARD_WTIME , BOARD_STATUS , BOARD_COMMENT_COUNT, BOARD_HIT FROM board WHERE rownum >= 1 AND rownum <= 10  ORDER BY BOARD_ID; 

select * from (SELECT rownum num,board_id, board_title, BOARD_CONTENT , BOARD_USERNAME, BOARD_IP ,
BOARD_WTIME , BOARD_STATUS , BOARD_COMMENT_COUNT, BOARD_HIT FROM board WHERE BOARD_STATUS  = 0 ORDER BY board_id) WHERE num >= 11 AND num <= 20

insert into board values(	board_seq.nextval, '안녕하세우',	'안녕하삼',	'jskim',	'192.168.25.26',	to_char(sysdate, 'yyyy-mm-dd hh24:mm:ss'),	0,	0,	0);