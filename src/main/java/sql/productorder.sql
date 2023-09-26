SELECT 
    order_number,
    member_number,
    TO_CHAR(order_date, 'YYYY-mm-dd') orderdate,
    payment,
    receiver_name,
    delivery_phone_number,
    delivery_address,
    delivery_request
FROM product_order;