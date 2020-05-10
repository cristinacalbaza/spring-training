CREATE TABLE IF NOT EXISTS orders (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    shipped_from_location_id INT,
    customer_id INT NOT NULL,
    created_at DATE,
    country VARCHAR(255),
    city VARCHAR(255),
    county VARCHAR(255),
    street_address VARCHAR(255),
    CONSTRAINT fk_shipment_location FOREIGN KEY (shipped_from_location_id)
    REFERENCES location(id),
    CONSTRAINT fk_customer FOREIGN KEY (customer_id)
    REFERENCES customer(id)
)