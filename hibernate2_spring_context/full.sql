BEGIN;
SET search_path TO hiber,public;
DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost numeric(6, 2));
INSERT INTO products (title, cost) VALUES
('milk', 79.90),
('bread', 24.90),
('butter', 220.00),
('cheese', 350.55),
('coca-cola', 69.95);
SET search_path TO hiber,public;
DROP TABLE IF EXISTS clients CASCADE;
CREATE TABLE clients (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO clients (name) VALUES
('Ivan'),
('Semen'),
('Peter');
SET search_path TO hiber,public;
DROP TABLE IF EXISTS products_clients CASCADE;
CREATE TABLE products_clients (id bigserial PRIMARY KEY, product_id bigint, client_id bigint,
                               FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (client_id) REFERENCES clients (id));
INSERT INTO products_clients (product_id, client_id) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(1, 2),
(2, 2),
(2, 3),
(5, 3);

COMMIT;