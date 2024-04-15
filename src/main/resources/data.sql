INSERT INTO categorias (nombre,activa) VALUES ('Mini',false);
INSERT INTO categorias (nombre,activa) VALUES ('Guajes',true);
INSERT INTO categorias (nombre,activa) VALUES ('Promesas',true);
INSERT INTO categorias (nombre,activa) VALUES ('Estrellas',true);
INSERT INTO categorias (nombre,activa) VALUES ('Ok Masters',true);

INSERT INTO equipos (nombre,email_Contacto,numero_Telefono_Contacto,puntos,goles_Favor,goles_Contra,
partidos_Ganados,partidos_Perdidos,partidos_Empatados,categoria_id) VALUES ('Jaimitos','correo@ejemplo.com',
123456789,15,0,0,0,0,0,1);
INSERT INTO equipos (nombre,email_Contacto,numero_Telefono_Contacto,puntos,goles_Favor,goles_Contra,
partidos_Ganados,partidos_Perdidos,partidos_Empatados,categoria_id) VALUES ('Pepitos','correo@ejemplo.com',
123456789,15,0,0,0,0,0,1);
INSERT INTO equipos (nombre,email_Contacto,numero_Telefono_Contacto,puntos,goles_Favor,goles_Contra,
partidos_Ganados,partidos_Perdidos,partidos_Empatados,categoria_id) VALUES ('Manolitas','correo@ejemplo.com',
123456789,15,0,0,0,0,0,1);
INSERT INTO equipos (nombre,email_Contacto,numero_Telefono_Contacto,puntos,goles_Favor,goles_Contra,
partidos_Ganados,partidos_Perdidos,partidos_Empatados,categoria_id) VALUES ('Jositos','correo@ejemplo.com',
123456789,15,0,0,0,0,0,1);

INSERT INTO partidos (goles_Local, goles_Visitante, pista, equipo_Local, equipo_Visitante) VALUES (5,1,'Sara Roces',1,2);
INSERT INTO partidos (goles_Local, goles_Visitante, pista, equipo_Local, equipo_Visitante) VALUES (8,9,'Sara Roces',3,4);
INSERT INTO partidos (goles_Local, goles_Visitante, pista, equipo_Local, equipo_Visitante) VALUES (9,5,'Sara Roces',1,3);
INSERT INTO partidos (goles_Local, goles_Visitante, pista, equipo_Local, equipo_Visitante) VALUES (4,4,'Sara Roces',2,4);

INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234567A','Mario',1234,'L',false,1);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234567B','Mario',1234,'S',false,1);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234587D','Mario',1234,'XS',false,2);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234564D','Mario',1234,'M',false,2);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234567C','Mario',1234,'2XL',true,1);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234567D','Mario',1234,'3XL',false,1);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1634567D','Mario',1234,'S',true,2);
INSERT INTO jugadores (dni, nombre, numero_Seguro,talla_Camiseta,portero,equipo_id) VALUES ('1234527D','Mario',1234,'3XS',false,2);

/*La contraseña es mario, ADMIN*/
INSERT INTO Usuario (usuario,password) VALUES ('mario','$2a$12$MwHuCgJDFt4qZQaK28VyieKCR61gyk42n8ntG1QPeluwMqBJEIK3i');
/*La contraseña es marcos, USER*/
INSERT INTO Usuario (usuario,password) VALUES ('marcos','$2a$12$5656XO6Vyk2OCZeqCkunQe.tZ6LnoGfMATGkyAbPUiffhGY0ThGpy');
