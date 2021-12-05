ALTER session set "_ORACLE_SCRIPT"=true;
DROP RM UNIV CASCADE; 
   -- CASCADE option 
CREATE USER RS IDENTIFIED BY 1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP;
GRANT connect, resource, dba TO RS; 

--
CREATE TABLE userinfo (
    userid  VARCHAR(15) PRIMARY KEY,
    userpw  VARCHAR(15),
    username CHAR(10),
    userphone CHAR(11)
);

CREATE TABLE restaurant (
    resname   VARCHAR(15) PRIMARY KEY,
    resfoodinfo   VARCHAR(15),
    resavgstars   NUMBER(3,2),
    resreviews   INT
);
alter table restaurant modify(resfoodinfo char(6));
CREATE TABLE food(
    foodname   NCHAR(15) PRIMARY KEY,
    maincatagory   VARCHAR(6),
    middleclass CHAR(10),
    resname   VARCHAR(15),
    FOREIGN KEY(resname) REFERENCES restaurant(resname)
);

CREATE TABLE review(
    viewnum   INT PRIMARY KEY,
    viewcontents   NCHAR(40),
    Stars      NUMBER(3,2),
    userid      VARCHAR(15),
    resname   VARCHAR(15),
    FOREIGN KEY(userid) REFERENCES userinfo(userid),
    FOREIGN KEY(resname) REFERENCES restaurant(resname)
);

CREATE TABLE favorite (
    favnum      INT PRIMARY KEY,
    userid      VARCHAR(15),
    resname   VARCHAR(15),
    FOREIGN KEY(userid) REFERENCES userinfo(userid),
    FOREIGN KEY(resname) REFERENCES restaurant(resname)
);

--userinfo
INSERT INTO userinfo VALUES('moon', '1234', '조영문', '010-9089');
INSERT INTO userinfo VALUES('yuha', '1212', '유하영', '010-1234');
INSERT INTO userinfo VALUES('gangu', '123123', '유한규', '010-4241');
INSERT INTO userinfo VALUES('sangmi', '2323', '이상미', '010-5383');
INSERT INTO userinfo VALUES('yujin', '0099', '조유진', '010-1542');
INSERT INTO userinfo VALUES('sujin', '1441', '김수진', '010-1415');
INSERT INTO userinfo VALUES('minsuk', '12312', '진민석', '010-1344');
INSERT INTO userinfo VALUES('sangmok', '9281', '이상목', '010-4151');
INSERT INTO userinfo VALUES('gukin', '0081', '한국인', '010-4151','');



--restaurant
INSERT INTO restaurant VALUES('문이네식당','양식',0,0);
INSERT INTO restaurant VALUES('상목파스타','양식',0,0);
INSERT INTO restaurant VALUES('롤링파스타','양식',0,0);
INSERT INTO restaurant VALUES('피자스트릿','양식',0,0);
INSERT INTO restaurant VALUES('국산맛집','한식',0,0);
INSERT INTO restaurant VALUES('노포맛집','한식',0,0);
INSERT INTO restaurant VALUES('인생맛집','한식',0,0);
INSERT INTO restaurant VALUES('차이향','중식',0,0);
INSERT INTO restaurant VALUES('홍콩반점','중식',0,0);
INSERT INTO restaurant VALUES('짜이루','중식',0,0);
INSERT INTO restaurant VALUES('청사포횟집','일식',0,0);
INSERT INTO restaurant VALUES('숙이네','일식',0,0);
INSERT INTO restaurant VALUES('바다횟집','일식',0,0);
INSERT INTO restaurant VALUES('해들녁','일식',0,0);

---food
--한식

INSERT INTO food VALUES('된장찌개', '한식', '국물','노포맛집');
INSERT INTO food VALUES('스팸김치찌개', '한식', '국물','인생맛집');
INSERT INTO food VALUES('돼지김치찌개', '한식', '국물','노포맛집');
INSERT INTO food VALUES('부대찌개', '한식', '국물','노포맛집');
INSERT INTO food VALUES('청국장','한식', '국물','노포맛집');
INSERT INTO food VALUES('배추국', '한식', '국물','인생맛집');
INSERT INTO food VALUES('소고기무국', '한식', '국물','노포맛집');
INSERT INTO food VALUES('조개미역국', '한식', '국물','인생맛집');
INSERT INTO food VALUES('얼큰콩나물국', '한식', '국물','국산맛집');
INSERT INTO food VALUES('시금치된장국', '한식', '국물','노포맛집');
INSERT INTO food VALUES('낙지덮밥', '한식', '밥','인생맛집');
INSERT INTO food VALUES('김치치즈볶음밥', '한식', '밥','국산맛집');
INSERT INTO food VALUES('참치마요덮밥', '한식', '밥','인생맛집');
INSERT INTO food VALUES('스팸마요덮밥', '한식', '밥','국산맛집');
INSERT INTO food VALUES('스팸치즈덮밥', '한식', '밥','인생맛집');
INSERT INTO food VALUES('계절나물비빔밥', '한식', '밥','국산맛집');
INSERT INTO food VALUES('냉면', '한식', '면','인생맛집');
INSERT INTO food VALUES('얼큰라면', '한식', '면','노포맛집');
INSERT INTO food VALUES('치즈라면', '한식', '면','국산맛집');
INSERT INTO food VALUES('콩국수', '한식', '면','노포맛집');
INSERT INTO food VALUES('밀면', '한식', '면','인생맛집');
INSERT INTO food VALUES('막국수', '한식', '면','노포맛집');
INSERT INTO food VALUES('생삼겹살', '한식', '고기','국산맛집');
INSERT INTO food VALUES('소고기', '한식', '고기','인생맛집');
INSERT INTO food VALUES('삼계탕', '한식', '고기','노포맛집');
INSERT INTO food VALUES('후라이드치킨', '한식', '고기','국산맛집');
--양식
INSERT INTO food VALUES('알리오올리오', '양식', '면','롤링파스타');
INSERT INTO food VALUES('까르보나라', '양식', '면','상목파스타');
INSERT INTO food VALUES('해물파스타', '양식', '면','롤링파스타');
INSERT INTO food VALUES('게살오이스터파스타', '양식', '면','롤링파스타');
INSERT INTO food VALUES('빠네크림파스타', '양식', '면','피자스트릿');
INSERT INTO food VALUES('새우오일파스타', '양식', '면','문이네식당');
INSERT INTO food VALUES('토마토스파게티', '양식', '면','상목파스타');
INSERT INTO food VALUES('크림스파게티', '양식', '면','피자스트릿');
INSERT INTO food VALUES('토마토파스타', '양식', '면','롤링파스타');
INSERT INTO food VALUES('해물리조또', '양식', '밥','문이네식당');
INSERT INTO food VALUES('게살오이스터파스타', '양식', '밥','롤링파스타');
INSERT INTO food VALUES('목살필라프', '양식', '밥','롤링파스타');
INSERT INTO food VALUES('쉬림프피자', '양식', '피자','문이네식당');
INSERT INTO food VALUES('김치필라프', '양식', '밥','상목파스타');
INSERT INTO food VALUES('해물빠에야', '양식', '밥','롤링파스타');
INSERT INTO food VALUES('목살스테이크', '양식', '고기','상목파스타');
INSERT INTO food VALUES('안심스테이크', '양식', '고기','문이네식당');
INSERT INTO food VALUES('폭립', '양식', '고기','롤링파스타');
INSERT INTO food VALUES('티본스테이크', '양식', '고기','상목파스타');
INSERT INTO food VALUES('엘본스테이크', '양식', '고기','문이네식당');
INSERT INTO food VALUES('토마호크스테이크', '양식', '고기','롤링파스타');
INSERT INTO food VALUES('플랫아이언', '양식', '고기','문이네식당');
INSERT INTO food VALUES('고르곤졸라피자', '양식', '피자','롤링파스타');
INSERT INTO food VALUES('페페로니피자', '양식', '피자','피자스트릿');
INSERT INTO food VALUES('고구마피자', '양식', '피자','피자스트릿');
INSERT INTO food VALUES('포테이토피자', '양식', '피자','문이네식당');
INSERT INTO food VALUES('해물피자', '양식', '피자','피자스트릿');


--일식
INSERT INTO food VALUES('스시', '일식', '밥','청사포횟집');
INSERT INTO food VALUES('규동', '일식', '밥','숙이네');
INSERT INTO food VALUES('가츠동', '일식', '밥','청사포횟집');
INSERT INTO food VALUES('오니기리', '일식', '밥','숙이네');
INSERT INTO food VALUES('덴푸라', '일식', '튀김','바다횟집');
INSERT INTO food VALUES('치즈돈가스', '일식', '튀김','청사포횟집');
INSERT INTO food VALUES('안심돈가스', '일식', '튀김','바다횟집');
INSERT INTO food VALUES('해들녁정식', '일식', '튀김','숙이네');
INSERT INTO food VALUES('고구마돈가스', '일식', '튀김','청사포횟집');
INSERT INTO food VALUES('매운돈가스', '일식', '튀김','숙이네');
INSERT INTO food VALUES('가라아게', '일식', '튀김','청사포횟집');
INSERT INTO food VALUES('스시야마', '일식', '국물','숙이네');
INSERT INTO food VALUES('라멘', '일식', '국물','바다횟집');
INSERT INTO food VALUES('튀김우동', '일식', '국물','청사포횟집');
INSERT INTO food VALUES('카케우동', '일식', '국물','바다횟집');
INSERT INTO food VALUES('찬고나배', '일식', '국물','숙이네');
INSERT INTO food VALUES('붓카케우동', '일식', '면','해들녁');
INSERT INTO food VALUES('시오라멘', '일식', '면','청사포횟집');
INSERT INTO food VALUES('츠케멘', '일식', '면','해들녁');
INSERT INTO food VALUES('소바', '일식', '면','청사포횟집');
INSERT INTO food VALUES('텐푸라소바', '일식', '면','숙이네');
INSERT INTO food VALUES('샤브샤브', '일식', '국물','해들녁');
INSERT INTO food VALUES('야키토리', '일식', '고기','청사포횟집');
INSERT INTO food VALUES('야키니쿠', '일식', '고기','해들녁');

--중식
INSERT INTO food VALUES('짜장면', '중식','면','차이향');
INSERT INTO food VALUES('짬뽕', '중식', '면','홍콩반점');
INSERT INTO food VALUES('볶음짬뽕', '중식', '면','차이향');
INSERT INTO food VALUES('중식우동', '중식', '면','짜이루');
INSERT INTO food VALUES('기스면', '중식', '면','홍콩반점');
INSERT INTO food VALUES('삼선짜장면', '중식', '면','짜이루');
INSERT INTO food VALUES('찹쌀탕수육','중식', '요리','차이향');
INSERT INTO food VALUES('일반탕수육','중식', '요리','홍콩반점');
INSERT INTO food VALUES('마파두부', '중식', '요리','짜이루');
INSERT INTO food VALUES('류산슬', '중식', '요리','차이향');
INSERT INTO food VALUES('깐풍기', '중식', '요리','짜이루');
INSERT INTO food VALUES('팔보채', '중식', '요리','차이향');
INSERT INTO food VALUES('야채볶음밥', '중식', '밥','짜이루');
INSERT INTO food VALUES('삼선볶음밥', '중식', '밥','차이향');
INSERT INTO food VALUES('새우볶음밥', '중식', '밥','짜이루');
INSERT INTO food VALUES('베이컨볶음밥', '중식', '밥','차이향');
INSERT INTO food VALUES('짬뽕밥', '중식', '밥','차이향');
INSERT INTO food VALUES('고추잡채밥', '중식', '밥','홍콩반점');
INSERT INTO food VALUES('군만두', '중식', '만두','차이향');
INSERT INTO food VALUES('찐만두', '중식', '만두','홍콩반점');
INSERT INTO food VALUES('물만두', '중식', '만두','짜이루');
INSERT INTO food VALUES('꽃빵', '중식', '만두','차이향');

--favorite
CREATE SEQUENCE favnumSEQ --시퀸스로 즐겨찾기번호메기기
   START WITH 1
   INCREMENT BY 1;

INSERT INTO favorite VALUES(favnumSEQ.NEXTVAL, 'moon', '문이네식당');
INSERT INTO favorite VALUES(favnumSEQ.NEXTVAL, 'yuha', '롤링파스타');
INSERT INTO favorite VALUES(favnumSEQ.NEXTVAL, 'sangmi', '부산식당');
INSERT INTO favorite VALUES(favnumSEQ.NEXTVAL, 'gangu', '노포맛집');

--review
CREATE SEQUENCE viewnumSEQ --시퀸스로 리뷰번호메기기
   START WITH 1
   INCREMENT BY 1;

--review
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '맛있었다.',5, 'moon','문이네식당' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL,  '애매한맛.',3, 'yuha','문이네식당' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL,  '너무짜다 나름 괜찮긴 했다.',3, 'yujin','문이네식당' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL,  '환상의맛', 5 , 'sujin','차이향' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '완전 맛있었다. 또 시켜먹을 의향 있음',5, 'sangmi','차이향' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '꽤 괜찮은 맛집.',4, 'yujin','상목파스타' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '불친절한 곳 맛은 나쁘지않음 ',2, 'sujin','국산맛집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '평범한 일식집',3, 'yuha','해들녁' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '갓잡은 회 완전 싱싱하고 맛있음.',5, 'yujin','청사포횟집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '완전 별로',1, 'moon','짜이루' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '완전 맛있었다. 가게 사장님도 친절.',5, 'sujin','짜이루' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '짬뽕이 완전 내 스타일',4, 'yujin','홍콩반점' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '괜찮은 중국집 ',3, 'sangmi','홍콩반점' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '완전 맛있었다. 또 오고 싶은 곳',5, 'yuha','국산맛집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '일반적인 한식집 ',3, 'sujin','국산맛집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '짜장면이 내스타일 맛있었다.',4, 'gangu','차이향' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '나쁘지 않은 곳 ',3, 'minsuk','차이향' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '비린맛이 너무 강하고 불청결',1, 'minsuk','바다횟집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '양도 너무 적고 불친절.',1, 'sangmok','바다횟집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '나쁘지 않은 곳 괜찮았다. ',3, 'moon','바다횟집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '양도 너무 적고 불친절.',1, 'sangmok','피자스트릿' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '나쁘지 않은 곳 괜찮았다. ',3, 'moon','피자스트릿' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '괜찮은 맛집 나름 맛있었다. ',4, 'yuha','노포맛집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '완전 맛있었다 인생맛집 ',5, 'yuha','인생맛집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '꽤 괜찮은 맛집.',4, 'yujin','롤링파스타' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '나름 맛있었다. 한번쯤 더 올만한 곳',4, 'sangmok','롤링파스타' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '완전 별로',1, 'minsuk','짜이루' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '나름 맛있었다 즐겨 먹을 듯',4, 'sangmi','짜이루' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '나쁘지 않은 곳',3, 'yujin','짜이루' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '확실히 맛있는 곳',5, 'gangu','해들녁' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '맛있었다 내 스타일',4, 'sangmok','해들녁' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '갓잡은 회 완전 싱싱하고 맛있음.',5, 'sangmi','청사포횟집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '제발.',5, 'moon','청사포횟집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '제발3',4, 'moon','문이네식당' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '제발1',3, 'moon','문이네식당' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, '제발2',5, 'moon','문이네식당' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, 'NOPE',5, 'gukin','청사포횟집' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, 'GOOD',4, 'gukin','짜이루' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, 'GOOD',3, 'gukin','롤링파스타' );
INSERT INTO review VALUES(viewnumSEQ.NEXTVAL, 'EXECllent',5, 'gukin','인생맛집' );

-- 별점수  트리거 ---
CREATE or REPLACE TRIGGER TRIGGER_Star
 BEFORE INSERT OR DELETE
 ON review
 FOR EACH ROW
BEGIN
    IF INSERTING THEN        
        UPDATE restaurant SET resreviews = resreviews + 1 WHERE :NEW.resname = restaurant.resname;
    ELSIF DELETING  THEN   
        UPDATE restaurant SET resreviews = resreviews - 1 WHERE :NEW.resname = restaurant.resname;
    END IF;
END;

--가게명 변경 트리거- 
CREATE or REPLACE TRIGGER TRIGGER_resName
 AFTER UPDATE
 ON restaurant
 FOR EACH ROW
BEGIN
    IF UPDATING THEN        
        UPDATE review SET resname = :NEW.resname where :OLD.resname = review.resname;
        UPDATE food SET resname = :NEW.resname where :OLD.resname = food.resname;
        UPDATE favorite SET resname = :NEW.resname where :OLD.resname = favorite.resname;
    END IF;
END;

--평균별점트리거--
CREATE or REPLACE TRIGGER avgStar 
BEFORE INSERT OR DELETE 
ON review 
FOR EACH ROW
DECLARE
    tot int := 0;
    totres  int := 0;
    avgs int := 0;
BEGIN
    IF INSERTING THEN
        BEGIN
            select sum(stars) INTO tot from review where resname = :NEW.resname;
            select count(resname) INTO totres from review where resname = :NEW.resname;
            avgs := (tot/totres);
            UPDATE restaurant SET resavgstars = avgs where restaurant.resname = :NEW.resname;
        END;
    ELSIF DELETING THEN
        BEGIN
            select sum(stars) INTO tot from review where resname=:OLD.resname;
            select count(resname) INTO totres from review where resname = :OLD.resname;
            avgs := (tot/totres);
            UPDATE restaurant SET resavgstars = avgs where restaurant.resname = :OLD.resname;
        END;
    END IF;
END;

DROP PRO

--프로시져--
CREATE OR REPLACE PROCEDURE review_number as

V_ID varchar(10); --회원 아이디 
V_count NUMBER (10) := 0; --리뷰작성의 갯수
V_grade VARCHAR2 (20); -- 회원등급 

CURSOR C IS
    SELECT REVIEW.USERID, COUNT(*)
    FROM  REVIEW, USERINFO
    WHERE USERINFO.USERID = REVIEW.USERID
    GROUP BY REVIEW.USERID;
BEGIN
    OPEN C;
    LOOP
        FETCH C INTO V_ID, V_count;
        EXIT WHEN C%NOTFOUND;
        CASE 
            WHEN (V_count >=5) THEN V_grade := '우수고객';
            WHEN (V_count >=3) THEN V_grade := '일반고객';
            ELSE  V_grade := '유령고객';
        END CASE;
        UPDATE USERINFO SET USERGRADE = V_grade WHERE USERINFO.USERID = V_ID;
    END LOOP;
    CLOSE C;
END;
EXEC review_number;   
ALTER TABLE USERINFO ADD USERGRADE VARCHAR2(20);

SELECT USERID, USERNAME, USERGRADE
FROM userinfo;

SELECT USERID, USERNAME, USERGRADE
FROM userinfo;
commit;