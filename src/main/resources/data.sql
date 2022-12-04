INSERT INTO CLIENTS (id, name, surname, fiscal_code, location)
VALUES(1, 'Gaetano', 'Gala', 'AHAHSHSHSHHSSHA', 'Bitonto');
INSERT INTO CLIENTS (id, name, surname, fiscal_code, location)
VALUES(2, 'Valerio', 'Rossi', 'AHAHSHSHSHHSSHA', 'Bari');
INSERT INTO CLIENTS (id, name, surname, fiscal_code, location)
VALUES(3, 'Giampiero', 'Bianchi', 'AHAHSHSHSHHSSHA', 'Mola di Bari');

INSERT INTO ORDERS (id, price, description, client_id)
VALUES(1, 30.48, '12 pezzi di acqua frizzante da 1L', 1);
INSERT INTO ORDERS (id, price, description, client_id)
VALUES(2, 30, '20 pezzi di birra Leffe', 2);


CREATE SEQUENCE CLIENTS_SEQUENCE_ID START WITH (select max(id) + 1 from CLIENTS);
CREATE SEQUENCE ORDERS_SEQUENCE_ID START WITH (select max(id) + 1 from ORDERS);
