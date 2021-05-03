-- myHome_dml
-- member 초기화데이터
insert into member values ('user01', 'password01', '홍길동', 'user01@work.com', '010-1234-1000', '12345', '서울시 관악구', '111동 1111호', null, null, to_char(sysdate, 'yyyy-mm-dd'), 1000, '일반회원');
insert into member values ('admin', 'admin01', '마동석', 'admin@work.com', '010-1111-2222', '46321', '부산 해운대구', '102동 1103호', null, null, '2020-01-01', 0, '관리자');
insert into member values ('ucamp01', 'ucamppw01', '강감찬', 'ucamp01@work.com', '010-5678-1000', '73154', '서울시 은평구', 'oo건물 502호', '251-24-51821', 'KCC', '2021-03-20', 0, '판매자');

-- notice(공지사항) 초기화데이터
insert into notice values (1,'admin', '공지사항입니다.', '다음주에 2차 실습시험 있습니다.', to_char(sysdate, 'yyyy-mm-dd'), 3);

-- category 초기화데이터
insert into category values (1, '가구/가전');
insert into category values (2, '패브릭');
insert into category values (3, '홈데코/조명');
insert into category values (4, '수납/정리');
insert into category values (5, '생활용품');
insert into category values (6, '주방');
insert into category values (7, 'DIY/공구');

-- product 초기화데이터
insert into product values (1, '박나래 기절베개 - 호텔식베개 커버포함 2종',  25900, 'img/product/1.jpg', 'img/productDetail/1.png', 0, 'KCC', 2, 4.87, 4979 , 2000);
insert into product values (2, '프란시스 Y3.3 커피머신 + 웰컴캡슐 14개',  119000, 'img/product/2.jpg', 'img/productDetail/2.png', 5000, 'KCC', 1, 4.9, 1632, 1000);
insert into product values (3, '헤링본 발탄 사이잘룩 러그 카페트',  12800, 'img/product/3.jpg', 'img/productDetail/3.png', 0, 'KCC', 2, 4.85, 12614, 1000);
insert into product values (4, '스마트전구 (LED 컬러 전구 / 앱연동)',  19900, 'img/product/4.jpg', 'img/productDetail/4.png', 3000, 'KCC', 3, 3.85, 8, 100);
insert into product values (5, 'RASKOG 로스코그 키친 카트 트롤리 웨건',  34900, 'img/product/5.jpg', 'img/productDetail/5.png', 0, 'KCC', 4, 4.55, 1106, 1000);
insert into product values (6, '비바체 100매 20팩 캡형 물티슈',  14900, 'img/product/6.jpg', 'img/productDetail/6.png', 0, 'KCC', 5, 4.78, 1178, 1000);
insert into product values (7, '놋담 달수저 외 인기 커트러리 모음',  25800, 'img/product/7.jpg', 'img/productDetail/7.png', 2500, 'KCC', 6, 4.45, 1086, 1000);
insert into product values (8, '3.7V 전동 드라이버 WW-D1',  29900, 'img/product/8.jpg', 'img/productDetail/8.png', 0, 'KCC', 7, 4.3, 184, 300);

-- cart 초기화데이터
insert into cart values (1, 'user01', 2, 2);

-- review 초기화데이터
insert into review values (1, 2, '배개 후기입니다.', empty_blob(), '후기내용 어쩌고저쩌고', 4.8, 'user01', '2021-04-02');

-- qnotice 초기화데이터
insert into qnotice values (1, '이거 어떻게 해결해야하나요', '답변 부탁드립니다.', '/img/interior.jpg', 'user01', to_char(sysdate, 'yyyy-mm-dd'), 5);

-- qnotice 초기화데이터
insert into qnotice values (2, '마루바닥 눌림 어떻게 하면 좋을까요?', '사진과 같이 철제 다리로 된 소파를 사용한 지 6개월 정도 됐습니다.

첫 3개월은 신경도 안썼는데 어느날 바닥을 보니 사진과 같이 마루바닥에 자국이 나 있더군요 ㅠㅠ

다이소에서 여러 스크래치 방지 제품과 다리 보호 제품 사용해보다가 테프론 재질 스티커가 그나마 조금 덜 패이는 것 같아서 일단은 사용중입니다.

그치만 자국이 남기는 남아서 계속 방법을 찾고 있는데, 혹시 테니스공을 쓰거나 러그를 깔면 마루에 자국이 안 남을까요? 아니면 사진과 같은 나무 원판을 다리 밑에 놓아서 무게를 분산시키는게 나을까요?

그리고 눌린 부분을 다리미로 열을 가해 원상복구 시킬 수도 있을까요?', '/img/qNotice/maru.PNG', 'user01', to_char(sysdate, 'yyyy-mm-dd'), 5);

-- answer 초기화데이터
insert into answer values (1, 1, '그건 이렇게 해결하는겁니다.', 'ucamp01', to_char(sysdate, 'yyyy-mm-dd'));

-- orders_method 초기화데이터
insert into orders_method values (1, '무통장입금');
insert into orders_method values (2, '신용카드');
insert into orders_method values (3, '휴대폰결제');

-- orders_detail 초기화데이터
INSERT INTO orders_detail VALUES (1, 2, 2, 1);

-- orders 초기화데이터
INSERT INTO orders VALUES (1, 'user01', 2, 50000, to_char(sysdate, 'yyyy-mm-dd'), 2500);

-- interior 초기화데이터
insert into interior values (1, '유캠프인테리어', 5, '설명 대체', '서울시 태해란로 11길 5');

----------------------------------------------------------------------------------------------

-- 로그인
select * from member where member_id = 'user01' and member_pw = 'password01';

-- 비밀번호 변경
update member set member_pw='newpassword01' where member_id='user01' and name='홍길동' and mobile='010-1234-1000';
select * from member where member_id = 'user01' and member_pw = 'newpassword01';

-- 전체회원 조회
select * from member;

-- 공지사항 조회
select * from notice;

-- 질문과 답변
select * from qnotice, answer where qnotice.q_no = answer.q_no;

-- 상품조회 (+후기)
select * from product, review where product.p_no = review.p_no; 

-- 인테리어업체 조회
select * from interior;

-- 내정보(결제 정보 조회)
select * from orders where member_id='user01';

-- 상품 수량 변경
update product set p_count = 80 where p_no = 2;
select * from product, category where product.category_id= category.category_id ;

-- 카테고리
select * from category;

-- 결제수단
select * from orders_method;

-- 베스트상품 조회
select p_no, p_name, p_price, p_img, p_sales, p_score*p_count as "베스트상품" from product order by 1 desc;

-- 상품 검색
select * from product where p_name like '%배개%';

-- 후기
select * from review;

