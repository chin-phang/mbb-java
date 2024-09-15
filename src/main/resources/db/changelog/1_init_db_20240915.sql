-- liquibase formatted sql

-- changeset aaron:1
USE mbb_assessment;

IF OBJECT_ID (N'dbo.customer', N'U') IS NOT NULL
DROP TABLE dbo.customer;

CREATE TABLE dbo.customer (
  id bigint NOT NULL IDENTITY PRIMARY KEY,
  name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  mobile_no varchar(20) NOT NULL,
  created_at datetime NOT NULL,
  created_by varchar(20) NOT NULL,
  last_modified_at datetime DEFAULT NULL,
  last_modified_by varchar(20) DEFAULT NULL,
  CONSTRAINT uq_customer_email UNIQUE (email)
);

INSERT INTO customer (name, email, mobile_no, created_at, created_by)
VALUES
('Test customer 1', 'test.customer1@maildrop.cc', '0112205577', current_timestamp, 'system'),
('Test customer 2', 'test.customer2@maildrop.cc', '0123309988', current_timestamp, 'system');

IF OBJECT_ID (N'dbo.mbb_user', N'U') IS NOT NULL
DROP TABLE dbo.mbb_user;

CREATE TABLE dbo.mbb_user (
  id bigint NOT NULL IDENTITY PRIMARY KEY,
  username varchar(50) NOT NULL,
  password_hash varchar(60) NOT NULL,
  role varchar(20) NOT NULL,
  customer_id bigint NOT NULL,
  created_at datetime NOT NULL,
  created_by varchar(20) NOT NULL,
  last_modified_at datetime DEFAULT NULL,
  last_modified_by varchar(20) DEFAULT NULL,
  CONSTRAINT uq_user_username UNIQUE(username),
  CONSTRAINT fk_user_customer FOREIGN KEY (customer_id) REFERENCES dbo.customer (id)
);

INSERT INTO mbb_user (username, password_hash, role, customer_id, created_at, created_by)
VALUES
('user222', '$2a$10$Z3zRk7Ztyrs2QECEf8hWYeEkeFMTfhyROUA/TelS11VR6fJqDxY4m', 'USER', (select id from dbo.customer where email = 'test.customer1@maildrop.cc'), current_timestamp, 'system'),
('user333', '$2a$10$Z3zRk7Ztyrs2QECEf8hWYeEkeFMTfhyROUA/TelS11VR6fJqDxY4m', 'USER', (select id from dbo.customer where email = 'test.customer2@maildrop.cc'), current_timestamp, 'system');

IF OBJECT_ID (N'dbo.account', N'U') IS NOT NULL
DROP TABLE dbo.account;

CREATE TABLE dbo.account (
  id bigint NOT NULL IDENTITY PRIMARY KEY,
  account_type varchar(50) NOT NULL,
  account_no varchar(100) NOT NULL,
  customer_id bigint NOT NULL,
  created_at datetime NOT NULL,
  created_by varchar(20) NOT NULL,
  last_modified_at datetime DEFAULT NULL,
  last_modified_by varchar(20) DEFAULT NULL,
  CONSTRAINT uq_account_account_no UNIQUE (account_no),
  CONSTRAINT fk_account_customer FOREIGN KEY (customer_id) REFERENCES dbo.customer (id)
);

INSERT INTO account (account_type, account_no, customer_id, created_at, created_by)
VALUES
('SAVINGS', '8872838283', (select id from dbo.customer where email = 'test.customer1@maildrop.cc'), current_timestamp, 'system'),
('CURRENT', '8872838299', (select id from dbo.customer where email = 'test.customer1@maildrop.cc'), current_timestamp, 'system'),
('SAVINGS', '6872838260', (select id from dbo.customer where email = 'test.customer2@maildrop.cc'), current_timestamp, 'system');

IF OBJECT_ID(N'dbo.mbb_transaction', N'U') IS NOT NULL
DROP TABLE dbo.mbb_transaction;

CREATE TABLE dbo.mbb_transaction (
  id bigint NOT NULL IDENTITY PRIMARY KEY,
  trx_reference_no varchar(100) NOT NULL,
  account_no varchar(100) NOT NULL,
  trx_amount decimal(20, 2) NOT NULL,
  description varchar(200) NOT NULL,
  trx_date date NOT NULL,
  trx_time time NOT NULL,
  customer_id bigint NOT NULL,
  created_at datetime NOT NULL,
  created_by varchar(20) NOT NULL,
  last_modified_at datetime DEFAULT NULL,
  last_modified_by varchar(20) DEFAULT NULL,
  CONSTRAINT  uq_transaction_trx_reference_no UNIQUE (trx_reference_no),
  CONSTRAINT fk_transaction_customer FOREIGN KEY (customer_id) REFERENCES dbo.customer (id)
);
