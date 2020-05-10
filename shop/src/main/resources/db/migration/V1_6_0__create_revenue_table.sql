CREATE TABLE IF NOT EXISTS revenue (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    location_id INT,
    date DATE,
    sum NUMERIC NOT NULL,
    CONSTRAINT fk_location FOREIGN KEY (location_id)
    REFERENCES location(id)
)