-- 멤버 
-- 테이블 생성
CREATE TABLE member (
    member_number NUMBER(10),
    email  VARCHAR2(30) NOT NULL,
    password     VARCHAR2(30) NOT NULL,
    member_name       VARCHAR2(20) NOT NULL,
    phone_number      VARCHAR2(20) NOT NULL,
    birthday DATE NOT NULL,
    gender VARCHAR2(1)
);

-- 제약조건 추가
ALTER TABLE member ADD (
    CONSTRAINT member_number_pk PRIMARY KEY (member_number),
    CONSTRAINT member_email_uk UNIQUE (email),
    CONSTRAINT member_phone_number_uk UNIQUE (phone_number),
    CONSTRAINT member_gender_ck CHECK (gender IN ('M', 'F'))
);

-- 멤버 히스토리
CREATE TABLE member_history (
    member_number NUMBER(10),
    member_register_date DATE DEFAULT sysdate,
    administrator VARCHAR2(1) DEFAULT 'F',
    status VARCHAR2(1) DEFAULT 'T',
    member_withdraw_date DATE DEFAULT null
);

ALTER TABLE member_history ADD (
    CONSTRAINT mhistory_member_number_pk PRIMARY KEY (member_number),
    CONSTRAINT mhistory_member_number_fk FOREIGN KEY (member_number) REFERENCES member (member_number),
    CONSTRAINT mhistory_member_admin_ck CHECK (administrator IN ('T', 'F')),
    CONSTRAINT mhistory_member_status_ck CHECK (status IN ('T', 'F'))
);

-- 상품
CREATE TABLE product (
    product_number NUMBER(10),
    product_name  VARCHAR2(100) NOT NULL,
    category     VARCHAR2(20),
    price      NUMBER(10) NOT NULL,
    thumbnail_path VARCHAR2(1000),
    description       VARCHAR2(2000),
    sale_whether VARCHAR2(1) DEFAULT 'T'
);

ALTER TABLE product ADD (
    CONSTRAINT product_number_pk PRIMARY KEY (product_number),
    CONSTRAINT product_category_ck CHECK (category IN ('glasses', 'sunglasses')),
    CONSTRAINT sale_whether_ck CHECK (sale_whether IN ('T', 'F'))
);

-- 주문
CREATE TABLE product_order (
    order_number NUMBER(10),
    member_number     NUMBER(10),
    order_date  DATE DEFAULT sysdate,
    payment       VARCHAR2(20),
    receiver_name VARCHAR2(20),
    delivery_phone_number VARCHAR2(20),
    delivery_address   VARCHAR2(100) NOT NULL,
    delivery_request VARCHAR2(100)
);

ALTER TABLE product_order ADD (
    CONSTRAINT order_number_pk PRIMARY KEY (order_number),
    CONSTRAINT order_member_number_fk FOREIGN KEY (member_number) REFERENCES member (member_number),
    CONSTRAINT order_payment_ck CHECK (payment IN ('credit', 'without_passbook', 'kakao', 'apple'))
);

-- 주문 목록
CREATE TABLE order_list (
    order_list_number NUMBER(10),
    product_number  NUMBER(10),
    order_number NUMBER(10),
    order_count       VARCHAR2(2) NOT NULL,
    review_comp VARCHAR2(1) DEFAULT 'F'
);

ALTER TABLE order_list ADD (
    CONSTRAINT order_list_number_pk PRIMARY KEY (order_list_number),
    CONSTRAINT order_list_product_number_fk FOREIGN KEY (product_number) REFERENCES product (product_number),
    CONSTRAINT order_list_order_number_fk FOREIGN KEY (order_number) REFERENCES product_order (order_number),
    CONSTRAINT order_list_review_comp_ck CHECK (review_comp IN ('T', 'F'))
);

-- 상품 리뷰
CREATE TABLE product_review (
    product_review_number NUMBER(10),
    product_number  NUMBER(10),
    review_rating NUMBER(2),
    review_content     VARCHAR2(2000),
    review_date DATE DEFAULT sysdate,
    writer_name VARCHAR2(20) NOT NULL
);

ALTER TABLE product_review ADD (
    CONSTRAINT pro_review_number_pk PRIMARY KEY (product_review_number),
    CONSTRAINT pro_review_product_number_fk FOREIGN KEY (product_number) REFERENCES product (product_number)
);

-- 시퀀스 생성
CREATE SEQUENCE member_number_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE product_number_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE order_number_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE order_list_number_seq START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE product_review_number_seq START WITH 1 INCREMENT BY 1;

-- 테이블 삭제 시 제약조건 같이 삭제    
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE member_history CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE product_order CASCADE CONSTRAINTS;
DROP TABLE order_list CASCADE CONSTRAINTS;
DROP TABLE product_review CASCADE CONSTRAINTS;

COMMIT;