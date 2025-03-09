create database bancoLess;

use bancoLess;

CREATE TABLE persona(
	id_persona int auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar (50) not null,
    documento varchar(20) unique not null,
    nacimiento date not null
);

CREATE TABLE usuario(
	id_usuario int auto_increment primary key,
    id_persona int not null,
    username varchar(50) unique not null,
    password varchar(255) not null,
    rol enum('CLIENTE', 'ADMIN', 'EMPLEADO') default 'CLIENTE',
    foreign key (id_persona) references persona(id_persona) on delete cascade
);

CREATE TABLE cuenta(
	num_cuenta varchar(20) primary key,
    id_usuario int not null,
    saldo decimal(10,2) default 0.00,
    estado enum('ACTIVA', 'BLOQUEADA', 'CERRADA') default 'ACTIVA',
    foreign key (id_usuario) references usuario(id_usuario) on delete cascade
);

CREATE TABLE transaccion(
	id int auto_increment primary key,
    num_cuenta varchar(20) not null,
    cuenta_destino varchar(20),
    tipo enum('DEPOSITO', 'RETIRO', 'TRANSFERENCIA') not null,
    monto decimal(10,2) not null,
    fecha timestamp default current_timestamp,
    descripcion text,
    foreign key (num_cuenta) references cuenta(num_cuenta) on delete cascade
);

show databases;
show tables;


