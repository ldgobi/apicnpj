CREATE TABLE SUPPLIER (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255) NOT NULL,
    Cnpj VARCHAR(14) NOT NULL,
    NomeContato VARCHAR(255),
    EmailContato VARCHAR(255),
    TelefoneContato VARCHAR(20)
);

And these instructions:
The CNPJ/CGC field must support uppercase letters and numbers. So instead of supporting only (\d or \D or [0-9]), it will now support ([A-Z0-9]).
Update types, parsers, input masks, and formatters as needed. All cnpj inputs should be converted to uppercase.
For Check Digit (DV) calculation routine only, after filtering for [A-Z0-9] or \W consider the value of each character as its ASCII code subtracted by 48 (e.g., '0'=0, '1'=1, '2'=2, 'A'=17, 'B'=18, 'C'=19, etc.).
Keep the rest of the algorithm unchanged. These rules doesn't appliy to CPF, don't change CPF logic that is not related to CNPJ.