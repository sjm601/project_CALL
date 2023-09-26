-- ȸ������
INSERT INTO member (
    member_number,
    email,
    password,
    member_name,
    phone_number,
    birthday,
    gender
) VALUES (
    member_number_seq.NEXTVAL,
    'admin', '1111', '������', '00000000000', sysdate, 'M');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);

-- ��ǰ �԰�
INSERT INTO product (
    product_number,
    product_name,
    category,
    price,
    thumnail_path,
    description
) VALUES (
    product_number_seq.NEXTVAL,
    '�Ȱ�1', 'glasses', 100000, 'image/thumnail', '�Ȱ�1�Դϴ�.');

-- ��ǰ �ֹ�
INSERT INTO product_order ( 
    order_number,
    member_number,
    payment,
    receiver_name,
    delivery_phone_number,
    delivery_address,
    delivery_request
) VALUES ( 
   order_number_seq.NEXTVAL,
   1, 'credit', 'ȫ�浿', '01033334444', '����� �����' ,'�� ��Ź�帳�ϴ�.');

INSERT INTO order_list (
    order_list_number,
    order_number,
    product_number,
    order_count
) VALUES (
    order_list_number_seq.NEXTVAL,
    order_number_seq.CURRVAL,
    1, 1);
    
-- ���� �ۼ�
INSERT INTO product_review (
    product_review_number,
    product_number,
    review_rating,
    review_content,
    writer_name
) VALUES (
    product_review_number_seq.NEXTVAL,
    1, 9, '���� ��ǰ �Դϴ�.', '����');
    
commit;