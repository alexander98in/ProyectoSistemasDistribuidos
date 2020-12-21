/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     9/12/2020 1:08:37 p.m.                       */
/*==============================================================*/


drop table if exists EMPRESAS;

drop table if exists FACTURAS;

drop table if exists HAMBURGUESASCOMPRADAS;

drop table if exists VALORES;

/*==============================================================*/
/* Table: EMPRESAS                                              */
/*==============================================================*/
create table EMPRESAS
(
   IDEMPRESA            int not null auto_increment,
   NOMBRE               varchar(30) not null,
   NIT                  varchar(30) not null,
   primary key (IDEMPRESA)
);

/*==============================================================*/
/* Table: FACTURAS                                              */
/*==============================================================*/
create table FACTURAS
(
   IDFACTURA            int not null auto_increment,
   IDEMPRESA            int,
   HAMBURGUESASPEQUENIAS int,
   HAMBURGUESASMEDIANAS int, 
   HAMBURGUESASGRANDES   int,
   COSTOSINIVA           float not null,
   IVA                  float not null,
   COSTOCONIVA          float not null,
   primary key (IDFACTURA)
);

/*==============================================================*/
/* Table: HAMBURGUESASCOMPRADAS                                 */
/*==============================================================*/
create table HAMBURGUESASCOMPRADAS
(
   IDHAMBURGUESA        int not null auto_increment,
   IDFACTURA            int,
   NOMBRE               varchar(30) not null,
   TIPO                 char not null,
   CANTIDADINGREDIENTESEXTRA int not null,
   COSTO                float not null,
   primary key (IDHAMBURGUESA)
);

/*==============================================================*/
/* Table: VALORES                                               */
/*==============================================================*/
create table VALORES
(
   IDVALOR              int not null auto_increment,
   IDEMPRESA            int,
   VALORINGREDIENTEEXTRA float not null,
   VALORHAMBURGUESAPEQUENIA float not null,
   VALORHAMBURGUESAMEDIANA float not null,
   VALORHAMBURGUESAGRANDE float not null,
   primary key (IDVALOR)
);

alter table VALORES add constraint FK_REFERENCE_1 foreign key (IDEMPRESA)
      references EMPRESAS (IDEMPRESA) on delete cascade on update cascade;

alter table FACTURAS add constraint FK_REFERENCE_2 foreign key (IDEMPRESA)
      references EMPRESAS (IDEMPRESA) on delete cascade on update cascade;

alter table HAMBURGUESASCOMPRADAS add constraint FK_REFERENCE_3 foreign key (IDFACTURA)
      references FACTURAS (IDFACTURA) on delete cascade on update cascade;


	 
