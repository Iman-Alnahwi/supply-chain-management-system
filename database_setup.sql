-- ============================================================
-- Supply Chain Management System  –  Database Setup Script
-- Run this ONCE in MySQL Workbench or any MySQL client.
-- If you already ran it before, run it again — it drops and
-- recreates everything cleanly.
-- ============================================================

CREATE DATABASE IF NOT EXISTS supply_chain;
USE supply_chain;

-- Drop in reverse order (children before parents)
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS users;

-- ── 1. Users ──────────────────────────────────────────────
--  role:       'Manager' or 'Staff'
--  department: only for Staff  (Procurement / Warehouse / Logistics)
CREATE TABLE users (
    user_id    INT          AUTO_INCREMENT PRIMARY KEY,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL,
    role       VARCHAR(10)  NOT NULL DEFAULT 'Manager',
    name       VARCHAR(100) NOT NULL DEFAULT '',
    phone      VARCHAR(20)           DEFAULT '',
    department VARCHAR(50)           DEFAULT NULL
);

-- ── 2. Suppliers ──────────────────────────────────────────
CREATE TABLE suppliers (
    supplier_id    INT          AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(100) NOT NULL,
    contact_person VARCHAR(100)          DEFAULT '',
    phone          VARCHAR(20)           DEFAULT '',
    email          VARCHAR(100)          DEFAULT '',
    address        VARCHAR(200)          DEFAULT ''
);

-- ── 3. Products ───────────────────────────────────────────
CREATE TABLE products (
    product_id     INT            AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(100)   NOT NULL,
    category       VARCHAR(50)             DEFAULT 'Other',
    unit_price     DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    stock_quantity INT            NOT NULL DEFAULT 0,
    supplier_id    INT,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE SET NULL
);

-- ── 4. Orders ─────────────────────────────────────────────
CREATE TABLE orders (
    order_id     INT            AUTO_INCREMENT PRIMARY KEY,
    supplier_id  INT,
    order_date   DATE           NOT NULL,
    status       VARCHAR(20)    NOT NULL DEFAULT 'Pending',
    total_amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id) ON DELETE SET NULL
);

-- ── 5. Order Items ────────────────────────────────────────
CREATE TABLE order_items (
    item_id    INT            AUTO_INCREMENT PRIMARY KEY,
    order_id   INT            NOT NULL,
    product_id INT,
    quantity   INT            NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    subtotal   DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id)   REFERENCES orders(order_id)     ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE SET NULL
);

-- ============================================================
-- Sample Data
-- ============================================================

-- Users  (plain-text passwords – fine for a student project)
--   admin / 1234  → Manager  (full access to every screen)
--   john  / pass  → Staff – Procurement  (Suppliers + Products + Orders)
--   mary  / pass  → Staff – Warehouse    (Products only)
--   bob   / pass  → Staff – Logistics    (Orders only)
INSERT INTO users (username, password, role, name, phone, department) VALUES
    ('admin', '1234', 'Manager', 'Admin Manager', '555-0000', NULL),
    ('john',  'pass', 'Staff',   'John Smith',    '555-0101', 'Procurement'),
    ('mary',  'pass', 'Staff',   'Mary Jones',    '555-0102', 'Warehouse'),
    ('bob',   'pass', 'Staff',   'Bob Lee',       '555-0103', 'Logistics');

-- Suppliers
INSERT INTO suppliers (name, contact_person, phone, email, address) VALUES
    ('Tech Supplies Co', 'Alice Brown', '555-1001', 'alice@techsupplies.com', '123 Main St, New York'),
    ('Global Foods Ltd', 'David Chen',  '555-1002', 'david@globalfoods.com',  '456 Oak Ave, Chicago'),
    ('Auto Parts Inc',   'Sara White',  '555-1003', 'sara@autoparts.com',     '789 Pine Rd, Houston');

-- Products
INSERT INTO products (name, category, unit_price, stock_quantity, supplier_id) VALUES
    ('Laptop Pro',        'Electronics', 1299.99,  30, 1),
    ('Wireless Keyboard', 'Electronics',   79.99,  50, 1),
    ('Rice 50kg Bag',     'Food',          89.99, 100, 2),
    ('Engine Oil 5L',     'Machinery',     45.99,  60, 3),
    ('Winter Jacket',     'Clothing',     129.99,  40, 2);
