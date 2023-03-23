-- 테이블 삭제 쿼리
DROP TABLE IF EXISTS b_management;
DROP TABLE IF EXISTS checkout;
DROP TABLE IF EXISTS memberdata;
DROP TABLE IF EXISTS bookdata;
DROP TABLE IF EXISTS admindata;

-- 테이블 생성 쿼리

CREATE TABLE IF NOT EXISTS admindata (
	a_no	int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	a_name	VARCHAR(20)	NOT NULL,
	a_id	VARCHAR(20)	NOT NULL,
	a_pwd	VARCHAR(15)	NOT NULL
);

CREATE TABLE IF NOT EXISTS bookdata (
  b_code int AUTO_INCREMENT PRIMARY KEY,
  b_name varchar(20) NOT NULL,
  b_writer varchar(20) NOT NULL,
  b_genre varchar(10) NOT NULL,
  b_date date NOT NULL
  );

CREATE TABLE IF NOT EXISTS memberdata (
  m_id varchar(20) PRIMARY KEY,
  m_pwd varchar(10) NOT NULL,
  m_name varchar(10) NOT NULL,
  m_phone char(13) NOT NULL
);


CREATE TABLE IF NOT EXISTS checkout (
	c_serialnum	INT	AUTO_INCREMENT PRIMARY KEY,
	m_id VARCHAR(20) NULL,
	b_code INT NOT NULL,
	c_stdate DATE DEFAULT (curdate()),
	c_endate DATE DEFAULT (curdate())
    );

CREATE TABLE IF NOT EXISTS b_management (
b_code INT NOT NULL,
m_id VARCHAR(20) NULL,
book_tf VARCHAR(10) NOT NULL DEFAULT ('N'),
c_serialnum INT NULL
);

CREATE OR REPLACE VIEW v_management
AS 
SELECT B.b_code `도서코드` , B.b_name `도서명` , B.b_writer `작가` , B.b_genre `장르` , 
C.m_id `Member ID` , C.c_serialnum `대출번호` , C.c_stdate `대출일` , C.c_endate `반납 예정일` FROM checkout C 
JOIN bookdata B ON C.b_code = B.b_code
JOIN memberdata M ON C.m_id = M.m_id;

ALTER TABLE test.checkout
ADD CONSTRAINT m_id
  FOREIGN KEY (m_id)
  REFERENCES test.memberdata (m_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT b_code
  FOREIGN KEY (b_code)
  REFERENCES test.bookdata (b_code)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE test.b_management
ADD CONSTRAINT m_M_id
  FOREIGN KEY (m_id)
  REFERENCES test.memberdata (m_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT b_M_code
  FOREIGN KEY (b_code)
  REFERENCES test.bookdata (b_code)
  ON DELETE CASCADE
  ON UPDATE CASCADE,
ADD CONSTRAINT c_B_serialnum
	FOREIGN KEY (c_serialnum)
    REFERENCES test.checkout (c_serialnum)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
ADD CONSTRAINT 
	CHECK (book_tf IN('Y','N'));
	




-- 회원 데이터 추가
INSERT INTO memberdata VALUES ('test01','1234','테스트','010-1111-2222');

-- 관리자 데이터 추가
INSERT INTO admindata VALUES (DEFAULT,'관리자','admin','9999');

-- 도서 데이터 추가 
INSERT INTO bookdata VALUES (DEFAULT,'해리포터','한정현','만화','2022-02-25');
INSERT INTO bookdata VALUES (DEFAULT,'노르웨이의 숲','소석진','만화','2022-04-25');
INSERT INTO bookdata VALUES (DEFAULT,'세븐 테크','김동민','만화','2022-05-01');
INSERT INTO bookdata VALUES (DEFAULT,'자바의 정석','김동진','만화','2022-06-25');
INSERT INTO bookdata VALUES (DEFAULT,'해리포터2','한정현','만화','2022-07-10');
INSERT INTO bookdata VALUES (DEFAULT,'해리포터3','한정현','만화','2022-08-25');
INSERT INTO bookdata VALUES (DEFAULT,'해리포터4','한정현','만화','2022-09-12');
INSERT INTO bookdata VALUES (DEFAULT,'내일점심은','지수문','현대문학','2022-10-25');
INSERT INTO bookdata VALUES (DEFAULT,'오늘아침은','김수민','현대문학','2022-11-02');
INSERT INTO bookdata VALUES (DEFAULT,'모레저녁은','김윤성','현대문학','2022-12-25');
INSERT INTO bookdata VALUES (DEFAULT,'자바의 정석','김윤성','참고서','2022-12-25');
INSERT INTO bookdata VALUES (DEFAULT,'반지의 제왕','한정현','소설','2022-12-25');


-- 대출 데이터 추가
INSERT INTO checkout VALUES (DEFAULT,'test01',1,'2022-10-07','2022-10-13');
INSERT INTO checkout VALUES (DEFAULT,'test01',2,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',3,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',4,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',5,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',6,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',7,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',8,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',9,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',10,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',11,'2022-10-09','2022-10-15');
INSERT INTO checkout VALUES (DEFAULT,'test01',12,'2022-10-09','2022-10-15');

-- 도서관리 데이터 추가
INSERT INTO b_management VALUES (1,'test01', 'Y',NULL);
INSERT INTO b_management VALUES (2,NULL,DEFAULT,NULL);
INSERT INTO b_management VALUES (3,NULL,DEFAULT,NULL);
INSERT INTO b_management VALUES (4,NULL,DEFAULT,NULL);
INSERT INTO b_management VALUES (5,'test01', 'Y',NULL);
INSERT INTO b_management VALUES (6,'test01', 'Y',NULL);
INSERT INTO b_management VALUES (7,'test01', 'Y',NULL);
INSERT INTO b_management VALUES (8,NULL,DEFAULT,NULL);
INSERT INTO b_management VALUES (9,NULL,DEFAULT,NULL);
INSERT INTO b_management VALUES (10,NULL,DEFAULT,NULL);
INSERT INTO b_management VALUES (11,NULL,DEFAULT,NULL);
INSERT INTO b_management VALUES (12,NULL,DEFAULT,NULL);



SELECT B.b_code `도서코드`, b_name `도서명`, b_writer `작가`, b_genre `장르`, b_date `출간일`, c_serialnum `대출코드`,book_tf `대출여부` FROM b_management BM
JOIN bookdata B ON BM.b_code = B.b_code
WHERE b_name LIKE '%자바%' OR b_writer = '소석진';

SELECT * FROM v_management
WHERE `도서명` LIKE '%자바%';



SELECT * FROM memberdata;
SELECT * FROM bookdata;
SELECT * FROM checkout;
SELECT * FROM admindata;
SELECT * FROM b_management;

commit;