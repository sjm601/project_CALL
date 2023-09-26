INSERT INTO order_list (
    order_list_number,
    order_number,
    product_number,
    order_count
) VALUES (
    order_list_number_seq.NEXTVAL,
   1009,
    1, 1);

SELECT * FROM order_list;

SELECT * FROM product_order
WHERE member_number = 7;

ALTER TABLE product ADD (
    sale_whether VARCHAR2(1) DEFAULT 'T',
    CONSTRAINT sale_whether_ck CHECK (sale_whether IN ('T', 'F'))
);