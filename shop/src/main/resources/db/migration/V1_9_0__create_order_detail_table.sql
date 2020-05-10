CREATE TABLE IF NOT EXISTS order_detail (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY(order_id, product_id),
    CONSTRAINT fk_order FOREIGN KEY (order_id)
    REFERENCES orders(id),
    CONSTRAINT fk_order_product FOREIGN KEY (product_id)
    REFERENCES product(id)

)