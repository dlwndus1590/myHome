-- drop table  cascade constraints;
drop table member cascade constraints;
drop table CART cascade constraints;
drop table ANSWER cascade constraints;
drop table CATEGORY cascade constraints;
drop table INTERIOR cascade constraints;
drop table NOTICE cascade constraints;
drop table ORDERS cascade constraints;
drop table ORDERS_DETAIL cascade constraints;
drop table ORDERS_METHOD cascade constraints;
drop table PRODUCT cascade constraints;
drop table QNOTICE cascade constraints;
drop table REVIEW cascade constraints;

-- member table
create table member (
    member_id varchar2(20) primary key,
    member_pw varchar2(40) not null,
    name varchar2(15) not null,
    email varchar2(40) not null,
    mobile varchar2(30) not null,
    zip_code number(5) not null,
    address1 varchar2(100) not null,
    address2 varchar2(100) not null,
    business_number varchar2(12),
    company_name varchar2(40),
    entry_date date,
    mileage number(6),
    grade varchar2(15),
    
    constraint email_unique unique(email),
    constraint mobile_unique unique(mobile),
    constraint company_name_unique unique(company_name),
    constraint business_number_unique unique(business_number)
);

-- notice table(공지사항)
CREATE TABLE NOTICE (
   n_no        NUMBER       PRIMARY KEY,
   member_id varchar2(20),
   n_title     VARCHAR2(100) not null,
   n_content   CLOB not null,
   n_reg_date  DATE,
   n_hits      NUMBER,
   CONSTRAINT notice_fk foreign key(member_id) references member(member_id) on delete cascade 
);

-- category table(카테고리)
CREATE TABLE CATEGORY(
    CATEGORY_ID NUMBER,
    CATEGORY_NAME VARCHAR2(30),
    CATEGORY_IMG VARCHAR2(100),

    CONSTRAINT CATEGORY_PK PRIMARY KEY(CATEGORY_ID)
);

-- product table(상품)
create table product (
    p_no number primary key,
    p_name varchar2(100) not null,
    p_price number not null,
    p_img varchar2(100) not null,
    p_describe varchar2(100) not null,
    delivery_fee number not null,
    company_name varchar2(40),
    category_id number,
    p_score number(2,1),
    p_sales number,
    p_count number,
    p_regDate date,
    
    constraint company_name_fk foreign key(company_name) references member(company_name) on delete cascade,
    constraint category_id_fk foreign key(category_id) references category(category_id) on delete cascade
);

-- cart table(장바구니)
Create table cart(
    c_no number primary key,
    member_Id varchar2(20),
    p_no number,
    c_count number,
    
    constraint member_id_fk foreign key(member_id) references member(member_id) on delete cascade ,
    constraint p_no_fk foreign key(p_no) references product(p_no) on delete cascade
);

-- review table(후기)
create table review (
    r_no number primary key,
    p_no number,
    r_img varchar2(100),
    r_content clob not null,
    r_score number(2),
    member_id varchar2(20),
    r_reg_date date,
    
    constraint p_no_fk2 foreign key(p_no) references product(p_no) on delete cascade,
    constraint member_id_fk2 foreign key(member_id) references member(member_id) on delete cascade
);

-- qnotice table(질문과답변 게시판)
CREATE TABLE QNOTICE (
   q_no      NUMBER      PRIMARY KEY,
   q_title       VARCHAR2(100) not null,
   q_content   CLOB not null,
   q_img      VARCHAR2(100),
   member_id   VARCHAR2(20),
   q_reg_date   DATE,
   q_hits      NUMBER,
   
   constraint member_id_fk3 foreign key(member_id) references member(member_id) on delete cascade
);
-- answer table(답변)
create table answer(
    a_no     number primary key,
    q_no     number,
    a_content    clob,
    member_id    varchar2(20),
    a_reg_date   date,
    
    constraint member_id_fk4 foreign key(member_id) references member(member_id) on delete cascade,
    constraint answer_notice_fk foreign key(q_no) references qnotice(q_no) on delete cascade
);

-- orders_method table (결제수단)
create table orders_method (
    o_method_id number primary key,
    o_method_name varchar2(30)
);

-- orders table(구매 테이블) 변경
create table orders(
    o_no number primary key,
    member_id varchar2(20),
    o_method_id number,
    o_total_price number,
    o_date date,
    o_delivery_fee number,
    usedMileage number,
    accumulateMileage number,
    zip_Code number(5),
    address1 varchar2(100),
    address2 varchar2(100),
    
    constraint o_method_fk foreign key(o_method_id) references orders_method(o_method_id) on delete cascade,
    constraint member_id_fk5 foreign key(member_id) references member(member_id) on delete cascade
);	


-- orders_detail table (구매상세) 변경
create table orders_detail (
    d_no number primary key,
    d_count number not null,
    p_no number,
    o_no number,
    r_content varchar2(30),
    constraint p_no_fk3 foreign key(p_no) references product(p_no) on delete cascade,
    constraint o_no_pk foreign key(o_no) references orders(o_no) on delete cascade
);

-- interior table
create table interior(
    i_no     number primary key,
    i_name       varchar2(40),
    i_career    number,
    i_detail     clob,
    i_location    varchar2(100)
);
