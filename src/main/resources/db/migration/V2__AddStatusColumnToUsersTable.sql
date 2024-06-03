-- V2__AddStatusColumnToUsersTable.sql - 03/06/2024 Diego L. F. S.

ALTER TABLE users
ADD COLUMN status BOOLEAN DEFAULT TRUE;
