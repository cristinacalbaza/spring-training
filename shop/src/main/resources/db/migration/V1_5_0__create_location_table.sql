CREATE TABLE IF NOT EXISTS location (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(255),
    city VARCHAR (255),
    county VARCHAR(255),
    street_address VARCHAR(255)
)