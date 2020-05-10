CREATE TABLE IF NOT EXISTS stock (
    product_id INT NOT NULL,
    location_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY(product_id, location_id),
    CONSTRAINT fk_stock_product FOREIGN KEY (product_id)
    REFERENCES product(id),
    CONSTRAINT fk_stock_location FOREIGN KEY (location_id)
    REFERENCES location(id)
)