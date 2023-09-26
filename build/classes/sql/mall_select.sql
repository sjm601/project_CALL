SELECT
    *
FROM
    member;

-- 관리자 회원 관리
SELECT
    m.member_number,
    m.member_name,
    m.email,
    mh.member_register_date,
    mh.administrator
FROM
         member m
    JOIN member_history mh ON m.member_number = mh.member_number
WHERE
    mh.status = 'T';

-- 일반 안경 상품 전체
SELECT
    product_name,
    price,
    thumbnail_path
FROM
    product
WHERE
    category = 'glasses'
ORDER BY
    price;

-- 선글라스 상품 전체
SELECT
    *
FROM
    product
WHERE
    category = 'sunglasses'
ORDER BY
    price;

-- 상품 상세
SELECT
    *
FROM
    product
WHERE
    product_number = 1;

-- 상품 평점
SELECT
    AVG(review_rating) avg
FROM
    product_review
WHERE
    product_number = 1;

-- 리뷰 조회
SELECT
    pr.review_rating,
    pr.review_content,
    pr.review_date,
    pr.product_number,
    pr.writer_name
FROM
    product_review pr
WHERE
    product_number = 1;

-- 주문 조회
SELECT
    po.order_date,
    p.category,
    p.product_name,
    ol.order_count,
    ( p.price * ol.order_count ) price
FROM
         product_order po
    JOIN order_list ol ON ol.order_number = po.order_number
    JOIN product    p ON p.product_number = ol.product_number
WHERE
    po.member_number = 1;

SELECT page,
    member_number,
    email,
    member_name
FROM (
        SELECT
            ceil(ROWNUM / 3) page,
            member_number,
            email,
            member_name
        FROM member
        ORDER BY member_number)
WHERE page = 4;