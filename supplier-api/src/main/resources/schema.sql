CREATE TABLE SUPPLIER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Cnpj VARCHAR(14) NOT NULL,
    NomeContato VARCHAR(255),
    EmailContato VARCHAR(255),
    TelefoneContato VARCHAR(20)
);