-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS laboratorio;
USE laboratorio;

-- Crear la tabla cliente si no existe
CREATE TABLE IF NOT EXISTS cliente (
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

-- Insertar 15 registros de ejemplo
INSERT INTO cliente (appaterno, apmaterno, nombres, nacimiento, direccion, referencia, genero, estado) VALUES
                                                                                                           ('Perez','Gomez','Juan','1985-03-12','Av. Los Olivos 123, Chiclayo','Cerca al parque','M',1),
                                                                                                           ('Lopez','Diaz','Maria','1990-07-25','Calle 8 #456, Lambayeque','Frente a la iglesia','F',1),
                                                                                                           ('Ruiz','Martinez','Carlos','1982-11-10','Jr. Lima 789, Chiclayo','Al lado del colegio','M',1),
                                                                                                           ('Sanchez','Torres','Ana','1995-02-18','Av. Grau 321, Chiclayo','Esquina con Jr. Piura','F',1),
                                                                                                           ('Ramirez','Flores','Luis','1988-06-30','Calle San Martin 12','Frente a la plaza','M',1),
                                                                                                           ('Vargas','Castillo','Gabriela','1992-12-05','Av. La Cultura 45','Cerca al hospital','F',1),
                                                                                                           ('Mendoza','Rojas','Jorge','1979-09-20','Jr. Tacna 56','Al costado del banco','M',1),
                                                                                                           ('Cano','Paredes','Elena','1987-04-14','Av. 28 de Julio 89','Cerca del mercado','F',1),
                                                                                                           ('Quispe','Loayza','Ricardo','1991-08-03','Calle Piura 90','Junto a la biblioteca','M',1),
                                                                                                           ('Alvarado','Salazar','Patricia','1984-01-22','Av. Bolognesi 123','Frente a la farmacia','F',1),
                                                                                                           ('Torres','Vega','Daniel','1993-05-11','Jr. Arequipa 34','Cerca al colegio','M',1),
                                                                                                           ('Flores','Chavez','Lorena','1986-10-09','Av. España 78','Esquina con Jr. Lima','F',1),
                                                                                                           ('Garcia','Soto','Fernando','1980-03-17','Calle La Paz 56','Al lado del mercado','M',1),
                                                                                                           ('Ramos','Mamani','Cecilia','1994-07-28','Av. Independencia 12','Frente a la iglesia','F',1),
                                                                                                           ('Cruz','Hernandez','Alberto','1989-12-15','Jr. Amazonas 90','Cerca del hospital','M',1);
