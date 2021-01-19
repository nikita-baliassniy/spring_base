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

COMMIT;