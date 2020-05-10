CREATE TABLE IF NOT EXISTS product (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price NUMERIC NOT NULL,
    weight NUMERIC,
    product_category_id INT,
    supplier_id INT,
    image_url VARCHAR(255),
    CONSTRAINT fk_product_category FOREIGN KEY (product_category_id)
    REFERENCES product_category(id),
    CONSTRAINT fk_supplier FOREIGN KEY (supplier_id)
    REFERENCES supplier(id)
)