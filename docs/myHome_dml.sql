-- myHome_dml
-- member 초기화데이터
insert into member values ('user01', 'password01', '홍길동', 'user01@work.com', '010-1234-1000', '12345', '서울시 관악구', '111동 1111호', null, null, to_char(sysdate, 'yyyy-mm-dd'), 1000, '일반회원');
insert into member values ('admin', 'admin01', '마동석', 'admin@work.com', '010-1111-2222', '46321', '부산 해운대구', '102동 1103호', null, null, '2020-01-01', 0, '관리자');
insert into member values ('ucamp01', 'ucamppw01', '강감찬', 'ucamp01@work.com', '010-5678-1000', '73154', '서울시 은평구', 'oo건물 502호', '251-24-51821', 'KCC', '2021-03-20', 0, '판매자');

-- notice(공지사항) 초기화데이터
insert into notice values (1, '공지사항입니다.', '다음주에 2차 실습시험 있습니다.', to_char(sysdate, 'yyyy-mm-dd'), 3);

-- category 초기화데이터
insert into category values (1, '가구/가전');
insert into category values (2, '패브릭');
insert into category values (3, '홈데코/조명');
insert into category values (4, '수납/정리');
insert into category values (5, '생활용품');
insert into category values (6, '주방');
insert into category values (7, '조명');

-- product 초기화데이터
insert into product values (1, '잠이 솔솔 오는 배개',  25000, empty_blob(), empty_blob(), 2500, 'KCC', 2, 4.5, 125, 200);
insert into product values (2, '푹신푹신 매트리스 커버',  38000, empty_blob(), empty_blob(), 2500, 'KCC', 2, 4.6, 80, 120);

-- cart 초기화데이터
insert into cart values (1, 'user01', 2, 2);

-- review 초기화데이터
insert into review values (1, 2, '배개 후기입니다.', empty_blob(), '후기내용 어쩌고저쩌고', 4.8, 'user01', '2021-04-02');

-- qnotice 초기화데이터
insert into qnotice values (1, '이거 어떻게 해결해야하나요', '답변 부탁드립니다.', empty_blob(), 'user01', to_char(sysdate, 'yyyy-mm-dd'), 5);

-- answer 초기화데이터
insert into answer values (1, 1, '그건 이렇게 해결하는겁니다.', 'ucamp01', to_char(sysdate, 'yyyy-mm-dd'));

-- orders_method 초기화데이터
insert into orders_method values (1, '무통장입금');
insert into orders_method values (2, '신용카드');
insert into orders_method values (3, '휴대폰결제');

-- orders_detail 초기화데이터
insert into orders_detail values (1, 2, 2);

-- orders 초기화데이터
insert into orders values (1, 'user01', 2, 1, 50000, to_char(sysdate, 'yyyy-mm-dd'), 2500);

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

