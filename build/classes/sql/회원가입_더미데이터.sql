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
    member_number,
    ADMINISTRATOR
) VALUES (
    member_number_seq.CURRVAL, 'T');
-------------------------------------------------------------
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
    'a@naver.com', '1234', 'ȫ�浿', '01011111111', sysdate, 'M');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);
-------------------------------------------------------------
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
    'b@naver.com', '1234', 'ȫ���', '01011111112', sysdate, 'F');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);
-------------------------------------------------------------
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
    'c@naver.com', '1234', '��ȣ��', '01011111113', sysdate, 'M');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);
-------------------------------------------------------------
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
    'd@naver.com', '1234', '��ȿ��', '01011111114', sysdate, 'F');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);
-------------------------------------------------------------
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
    'e@naver.com', '1234', '���缮', '01011111115', sysdate, 'M');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);
-------------------------------------------------------------
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
    'f@naver.com', '1234', '�ڸ��', '01011111116', sysdate, 'M');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);
-------------------------------------------------------------
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
    'g@naver.com', '1234', '����ȿ', '01011111117', sysdate, 'F');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);
-------------------------------------------------------------
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
    'h@naver.com', '1234', '���ҹ�', '01011111118', sysdate, 'F');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);
-------------------------------------------------------------
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
    'i@naver.com', '1234', '�̱���', '01011111119', sysdate, 'M');

INSERT INTO member_history (
    member_number
) VALUES (
    member_number_seq.CURRVAL);
-------------------------------------------------------------

commit;