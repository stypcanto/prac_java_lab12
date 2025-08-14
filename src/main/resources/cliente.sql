
CREATE TABLE laboratorio.cliente (
                                     idcliente BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     appaterno VARCHAR(50) NOT NULL,
                                     apmaterno VARCHAR(50) NOT NULL,
                                     nombres VARCHAR(50) NOT NULL,
                                     nacimiento DATE NULL,
                                     direccion VARCHAR(200) NULL,
                                     referencia VARCHAR(200) NULL,
                                     genero CHAR(1) NOT NULL,
                                     estado INT NULL
);
