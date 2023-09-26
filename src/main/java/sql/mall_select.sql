SELECT
    *
FROM
    member;

-- ������ ȸ�� ����
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

-- �Ϲ� �Ȱ� ��ǰ ��ü
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

-- ���۶� ��ǰ ��ü
SELECT
    *
FROM
    product
WHERE
    category = 'sunglasses'
ORDER BY
    price;

-- ��ǰ ��
SELECT
    *
FROM
    product
WHERE
    product_number = 1;

-- ��ǰ ����
SELECT
    AVG(review_rating) avg
FROM
    product_review
WHERE
    product_number = 1;

-- ���� ��ȸ
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

-- �ֹ� ��ȸ
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