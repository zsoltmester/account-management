/*
 * AM_USER
 */

drop table AM_USER;

create table AM_USER 
(
  name VARCHAR2(100) primary key not null,
  password VARCHAR2(100) not null
);

grant select on QT3QF8.AM_USER to PUBLIC;

insert into AM_USER values ('admin', 'YWRtaW4=');
insert into AM_USER values ('sandra', 'c2FuZHJh');
insert into AM_USER values ('emma', 'ZW1tYQ==');

/*
 * AM_CUSTOMER
 */
 
drop table AM_CUSTOMER;

 create table AM_CUSTOMER 
(
  id INTEGER primary key not null,
  name VARCHAR2(100),
  address VARCHAR2(100),
  phone VARCHAR2(100)
);

grant select on QT3QF8.AM_CUSTOMER to PUBLIC;

insert into AM_CUSTOMER values (1, 'Adam', 'Alberta', 123);
insert into AM_CUSTOMER values (2, 'Ben', 'Budapest', 22);
insert into AM_CUSTOMER values (3, 'Cedric', 'California', 321);
insert into AM_CUSTOMER values (4, 'Daniel', 'Dublin', 456);
insert into AM_CUSTOMER values (5, 'Emily', 'Edinborough', 789);

/*
 * AM_ACCOUNT
 */
 
 drop table AM_ACCOUNT;
 
 create table AM_ACCOUNT 
(
  id INTEGER primary key not null,
  customer_id INTEGER not null,
  account_number VARCHAR2(100) not null,
  balance NUMERIC(*, 5) not null,
  creation DATE not null,
  status NUMBER(1,0) not null,
  constraint fk_cid foreign key (customer_id) references AM_CUSTOMER(id),
  constraint chk_balance check (balance > 0)
);

grant select on QT3QF8.AM_ACCOUNT to PUBLIC;

insert into AM_ACCOUNT values (1, 1, '111-222-333', 100, TO_DATE('21-feb-1992 10:10:10','DD-MON-YYYY HH24:MI:SS'), 0);
insert into AM_ACCOUNT values (2, 2, '222-333-444', 200, TO_DATE('30-aug-1982 20:20:20','DD-MON-YYYY HH24:MI:SS'), 0);
insert into AM_ACCOUNT values (3, 2, '333-444-555', 300, TO_DATE('20-dec-2001 23:23:30','DD-MON-YYYY HH24:MI:SS'), 1);
insert into AM_ACCOUNT values (4, 3, '444-555-666', 400, TO_DATE('04-jan-2006 04:04:40','DD-MON-YYYY HH24:MI:SS'), 0);
insert into AM_ACCOUNT values (5, 3, '555-666-777', 500, TO_DATE('24-apr-2006 05:05:50','DD-MON-YYYY HH24:MI:SS'), 2);
insert into AM_ACCOUNT values (6, 3, '666-777-888', 600, TO_DATE('07-may-2007 06:16:56','DD-MON-YYYY HH24:MI:SS'), 0);
insert into AM_ACCOUNT values (7, 4, '123-456-789', 600, TO_DATE('11-nov-2009 19:29:57','DD-MON-YYYY HH24:MI:SS'), 1);

/*
 * AM_TRANSACTION
 */
 
 drop table AM_TRANSACTION;
 
 create table AM_TRANSACTION 
(
  id INTEGER primary key not null,
  source_account INTEGER not null,
  target_account INTEGER not null,
  amount NUMERIC(*, 5) not null,
  creation DATE not null,
  constraint fk_sa foreign key (source_account) references AM_ACCOUNT(id),
  constraint fk_ta foreign key (target_account) references AM_ACCOUNT(id),
  constraint chk_amount check (amount > 0),
  constraint chk_account check (source_account != target_Account)
);

grant select on AM_TRANSACTION to public;

drop sequence AM_SEQ_TRANS;
create sequence AM_SEQ_TRANS;
select AM_SEQ_TRANS.nextval from dual;

create or replace trigger AM_BIO_TRANS
before insert on AM_TRANSACTION
for each row
when (new.id is null)
begin
  select AM_SEQ_TRANS.nextval
  into :new.id
  from dual;
end;
/

create or replace trigger AM_AIO_TRANS
before insert on AM_TRANSACTION
for each row
begin
  update AM_ACCOUNT set balance = balance - (:new.amount * 1.05) where id = :new.source_account;
  update AM_ACCOUNT set balance = balance + :new.amount where id = :new.target_account;
end;
/

alter trigger AM_BIO_TRANS enable;
alter trigger AM_AIO_TRANS enable;

insert into AM_TRANSACTION values (1, 1, 2, 11.11, TO_DATE('10-feb-1993 11:11:11','DD-MON-YYYY HH24:MI:SS'));
insert into AM_TRANSACTION values (2, 2, 3, 22.22, TO_DATE('11-jan-2002 23:23:23','DD-MON-YYYY HH24:MI:SS'));
insert into AM_TRANSACTION values (3, 3, 4, 33.33, TO_DATE('01-oct-2010 20:10:00','DD-MON-YYYY HH24:MI:SS'));
insert into AM_TRANSACTION values (4, 4, 5, 44.44, TO_DATE('29-dec-2011 11:22:33','DD-MON-YYYY HH24:MI:SS'));
insert into AM_TRANSACTION values (5, 5, 6, 55.55, TO_DATE('25-nov-2012 22:33:44','DD-MON-YYYY HH24:MI:SS'));
insert into AM_TRANSACTION values (6, 6, 7, 66.66, TO_DATE('16-aug-2013 23:24:25','DD-MON-YYYY HH24:MI:SS'));
insert into AM_TRANSACTION values (7, 7, 1, 77.77, TO_DATE('03-jul-2014 01:02:04','DD-MON-YYYY HH24:MI:SS'));

commit;

-- tests
select * from 
  ((select * from AM_CUSTOMER where AM_CUSTOMER.id = 3) customer 
  left join AM_ACCOUNT account on customer.id = account.customer_id) 
  left join AM_TRANSACTION transaction on 
  (account.id = transaction.source_account or account.id = transaction.target_account);

update AM_ACCOUNT set status = 1 where id = 6;

update AM_ACCOUNT set balance = -1 where id = 6;

delete from AM_TRANSACTION where id = 10;
insert into AM_TRANSACTION values (NULL, 5, 5, 100, TO_DATE('01-oct-2010 20:10:00','DD-MON-YYYY HH24:MI:SS'));
insert into AM_TRANSACTION values (NULL, 2, 3, 2, TO_DATE('14.05.2016 19:09:57','DD-MM-YYYY HH24:MI:SS'));

select id from AM_ACCOUNT where account_number = '555-666-777';