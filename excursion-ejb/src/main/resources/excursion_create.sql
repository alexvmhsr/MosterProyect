/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     08/12/2015 11:50:19                          */
/*==============================================================*/


alter table ARCHIVO
   drop constraint FK_ARCHIVO_EXCURSION;

alter table CIUDAD
   drop constraint FK_CIUDAD_PROVINCIA;

alter table COMENTARIO
   drop constraint FK_COMENTARIO_EXCURSION;

alter table EXCURSION_ARTICULO
   drop constraint FK_EXCURSIONARTICULO_ARTICULO;

alter table EXCURSION_ARTICULO
   drop constraint FK_EXCURSIONARTICULO_EXCURSION;

alter table ITINERARIO
   drop constraint FK_ITINERARIO_ACTIVIDAD;

alter table ITINERARIO
   drop constraint FK_ITINERARIO_SITIO;

alter table ITINERARIO
   drop constraint FK_ITINERARIO_EXCURSION;

alter table PROVINCIA
   drop constraint FK_PROVINCIA_PAIS;

alter table SITIO
   drop constraint FK_SITIO_CIUDAD;

alter table USUARIO_EXCURSION
   drop constraint FK_USUARIOEXCURSION_EXCURSION;

alter table USUARIO_EXCURSION
   drop constraint FK_USUARIOEXCURSION_MOCHILA;

alter table USUARIO_EXCURSION
   drop constraint FK_USUARIOEXCURSION_USUARIO;

drop table ACTIVIDAD cascade constraints;

drop index EXCURSION_ARCHIVO_FK;

drop table ARCHIVO cascade constraints;

drop table ARTICULO cascade constraints;

drop index PROVINCIA_CIUDAD_FK;

drop table CIUDAD cascade constraints;

drop index EXCURSION_COMENTARIO_FK;

drop table COMENTARIO cascade constraints;

drop table EXCURSION cascade constraints;

drop index EXCURSION_ARTICULO2_FK;

drop index EXCURSION_ARTICULO_FK;

drop table EXCURSION_ARTICULO cascade constraints;

drop index SITIO_FK;

drop index EXCURSION_FK;

drop index ACTIVIDAD_FK;

drop table ITINERARIO cascade constraints;

drop table MOCHILA cascade constraints;

drop table PAIS cascade constraints;

drop index PAIS_PROVINCIA_FK;

drop table PROVINCIA cascade constraints;

drop index SITIO_CIUDAD_FK;

drop table SITIO cascade constraints;

drop table USUARIO cascade constraints;

drop index MOCHILA_USUARIO_FK;

drop index MOCHILA_EXCURSION_FK;

drop index USUARIO_MOCHILA_FK;

drop table USUARIO_EXCURSION cascade constraints;

/*==============================================================*/
/* Table: ACTIVIDAD                                             */
/*==============================================================*/
create table ACTIVIDAD 
(
   ID_ACTIVIDAD         INTEGER              not null,
   NOMBRE_ACTIVIDAD     VARCHAR2(32)         not null,
   DESCRIPCION_ACTIVIDAD VARCHAR2(256),
   COSTO_ACTIVIDAD      NUMBER(5,2),
   ENCARGADO_ACTIVIDAD  VARCHAR2(200),
   constraint PK_ACTIVIDAD primary key (ID_ACTIVIDAD)
);

CREATE SEQUENCE ACTIVIDAD_SEQ;

/*==============================================================*/
/* Table: ARCHIVO                                               */
/*==============================================================*/
create table ARCHIVO 
(
   ID_ARCHIVO           INTEGER              not null,
   ID_EXCURSION         INTEGER              not null,
   NOMBRE_ARCHIVO       VARCHAR2(64)         not null,
   RUTA_ARCHIVO         VARCHAR2(256)        not null,
   constraint PK_ARCHIVO primary key (ID_ARCHIVO)
);
CREATE SEQUENCE ARCHIVO_SEQ;

/*==============================================================*/
/* Index: EXCURSION_ARCHIVO_FK                                  */
/*==============================================================*/
create index EXCURSION_ARCHIVO_FK on ARCHIVO (
   ID_EXCURSION ASC
);

/*==============================================================*/
/* Table: ARTICULO                                              */
/*==============================================================*/
create table ARTICULO 
(
   ID_ARTICULO          INTEGER              not null,
   NOMBRE_ARTICULO      VARCHAR2(32)         not null,
   DESCRIPCION_ARTICULO VARCHAR2(256),
   PRECIO_ARTICULO      NUMBER               not null,
   constraint PK_ARTICULO primary key (ID_ARTICULO)
);
CREATE SEQUENCE ARTICULO_SEQ;

/*==============================================================*/
/* Table: CIUDAD                                                */
/*==============================================================*/
create table CIUDAD 
(
   ID_CIUDAD            INTEGER              not null,
   ID_PROVINCIA         INTEGER              not null,
   NOMBRE_CIUDAD        VARCHAR2(64)         not null,
   DESCRIPCION_CIUDAD   VARCHAR2(128),
   constraint PK_CIUDAD primary key (ID_CIUDAD)
);
CREATE SEQUENCE CIUDAD_SEQ;

/*==============================================================*/
/* Index: PROVINCIA_CIUDAD_FK                                   */
/*==============================================================*/
create index PROVINCIA_CIUDAD_FK on CIUDAD (
   ID_PROVINCIA ASC
);

/*==============================================================*/
/* Table: COMENTARIO                                            */
/*==============================================================*/
create table COMENTARIO 
(
   ID_COMENTARIO        INTEGER              not null,
   ID_EXCURSION         INTEGER              not null,
   CUERPO_COMENTARIO    VARCHAR2(3000)       not null,
   AUTOR_COMENTARIO     VARCHAR2(200)        not null,
   MAIL_COMENTARIO      VARCHAR2(64),
   PUNTUACION_COMENTARIO NUMBER(8,2),
   constraint PK_COMENTARIO primary key (ID_COMENTARIO)
);

/*==============================================================*/
/* Index: EXCURSION_COMENTARIO_FK                               */
/*==============================================================*/
create index EXCURSION_COMENTARIO_FK on COMENTARIO (
   ID_EXCURSION ASC
);

/*==============================================================*/
/* Table: EXCURSION                                             */
/*==============================================================*/
create table EXCURSION 
(
   ID_EXCURSION         INTEGER              not null,
   NOMBRE_EXCURSION     VARCHAR2(200)        not null,
   PUNTUACION_EXCURSION NUMBER(3,2),
   DESTINO_EXCURSION    VARCHAR2(100),
   OBJETIVO_EXCURSION   VARCHAR2(1000),
   RIESGOS_EXCURSION    VARCHAR2(300),
   OBSERVACIONES_EXCURSION VARCHAR2(1000),
   COSTO_EXCURSION      NUMBER(8,2),
   constraint PK_EXCURSION primary key (ID_EXCURSION)
);

/*==============================================================*/
/* Table: EXCURSION_ARTICULO                                    */
/*==============================================================*/
create table EXCURSION_ARTICULO 
(
   ID_EXCURSION         INTEGER              not null,
   ID_ARTICULO          INTEGER              not null,
   CANTIDAD             INTEGER              not null,
   DESCUENTO            Number(8,2),
   constraint PK_EXCURSION_ARTICULO primary key (ID_EXCURSION, ID_ARTICULO)
);

/*==============================================================*/
/* Index: EXCURSION_ARTICULO_FK                                 */
/*==============================================================*/
create index EXCURSION_ARTICULO_FK on EXCURSION_ARTICULO (
   ID_EXCURSION ASC
);

/*==============================================================*/
/* Index: EXCURSION_ARTICULO2_FK                                */
/*==============================================================*/
create index EXCURSION_ARTICULO2_FK on EXCURSION_ARTICULO (
   ID_ARTICULO ASC
);

/*==============================================================*/
/* Table: ITINERARIO                                            */
/*==============================================================*/
create table ITINERARIO 
(
   ID_ACTIVIDAD         INTEGER              not null,
   ID_EXCURSION         INTEGER              not null,
   ID_SITIO             INTEGER              not null,
   FECHA_SALIDA         TIMESTAMP,
   FECHA_RETORNO        TIMESTAMP,
   DESCUENTO            NUMBER(8,2),
   constraint PK_ITINERARIO primary key (ID_ACTIVIDAD, ID_EXCURSION, ID_SITIO)
);

/*==============================================================*/
/* Index: ACTIVIDAD_FK                                          */
/*==============================================================*/
create index ACTIVIDAD_FK on ITINERARIO (
   ID_ACTIVIDAD ASC
);

/*==============================================================*/
/* Index: EXCURSION_FK                                          */
/*==============================================================*/
create index EXCURSION_FK on ITINERARIO (
   ID_EXCURSION ASC
);

/*==============================================================*/
/* Index: SITIO_FK                                              */
/*==============================================================*/
create index SITIO_FK on ITINERARIO (
   ID_SITIO ASC
);

/*==============================================================*/
/* Table: MOCHILA                                               */
/*==============================================================*/
create table MOCHILA 
(
   ID_MOCHILA           INTEGER              not null,
   NOMBRE_MOCHILA       VARCHAR2(40)         not null,
   DESCRIPCION_MOCHILA  VARCHAR2(64),
   constraint PK_MOCHILA primary key (ID_MOCHILA)
);

/*==============================================================*/
/* Table: PAIS                                                  */
/*==============================================================*/
create table PAIS 
(
   ID_PAIS              INTEGER              not null,
   NOMBRE_PAIS          VARCHAR2(64)         not null,
   DESCRIPCION_PAIS     VARCHAR2(128),
   constraint PK_PAIS primary key (ID_PAIS)
);

/*==============================================================*/
/* Table: PROVINCIA                                             */
/*==============================================================*/
create table PROVINCIA 
(
   ID_PROVINCIA         INTEGER              not null,
   ID_PAIS              INTEGER              not null,
   NOMBRE_PROVINCIA     VARCHAR2(64)         not null,
   DESCRIPCION_PROVINCIA VARCHAR2(128),
   constraint PK_PROVINCIA primary key (ID_PROVINCIA)
);

/*==============================================================*/
/* Index: PAIS_PROVINCIA_FK                                     */
/*==============================================================*/
create index PAIS_PROVINCIA_FK on PROVINCIA (
   ID_PAIS ASC
);

/*==============================================================*/
/* Table: SITIO                                                 */
/*==============================================================*/
create table SITIO 
(
   ID_SITIO             INTEGER              not null,
   ID_CIUDAD            INTEGER              not null,
   NOMBRE_SITIO         VARCHAR2(32)         not null,
   DESCRIPCION_SITIO    VARCHAR2(256),
   constraint PK_SITIO primary key (ID_SITIO)
);

/*==============================================================*/
/* Index: SITIO_CIUDAD_FK                                       */
/*==============================================================*/
create index SITIO_CIUDAD_FK on SITIO (
   ID_CIUDAD ASC
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO 
(
   ID_USUARIO           INTEGER              not null,
   NOMBRE_USUARIO       VARCHAR2(64)         not null,
   CORREO_USUARIO       VARCHAR2(32)         not null,
   PASSWORD_USUARIO     VARCHAR2(32)         not null,
   TELEFONO_USUARIO     VARCHAR2(32),
   NOMBRES_COMPLETOS    VARCHAR2(200)        not null,
   ACERCA_DE            VARCHAR2(2000),
   constraint PK_USUARIO primary key (ID_USUARIO)
);

/*==============================================================*/
/* Table: USUARIO_EXCURSION                                     */
/*==============================================================*/
create table USUARIO_EXCURSION 
(
   ID_USUARIO_EXCURSION INTEGER              not null,
   ID_USUARIO           INTEGER              not null,
   ID_EXCURSION         INTEGER              not null,
   ID_MOCHILA           INTEGER              not null,
   constraint PK_USUARIO_EXCURSION primary key (ID_USUARIO_EXCURSION)
);

/*==============================================================*/
/* Index: USUARIO_MOCHILA_FK                                    */
/*==============================================================*/
create index USUARIO_MOCHILA_FK on USUARIO_EXCURSION (
   ID_USUARIO ASC
);

/*==============================================================*/
/* Index: MOCHILA_EXCURSION_FK                                  */
/*==============================================================*/
create index MOCHILA_EXCURSION_FK on USUARIO_EXCURSION (
   ID_EXCURSION ASC
);

/*==============================================================*/
/* Index: MOCHILA_USUARIO_FK                                    */
/*==============================================================*/
create index MOCHILA_USUARIO_FK on USUARIO_EXCURSION (
   ID_MOCHILA ASC
);

alter table ARCHIVO
   add constraint FK_ARCHIVO_EXCURSION foreign key (ID_EXCURSION)
      references EXCURSION (ID_EXCURSION);

alter table CIUDAD
   add constraint FK_CIUDAD_PROVINCIA foreign key (ID_PROVINCIA)
      references PROVINCIA (ID_PROVINCIA);

alter table COMENTARIO
   add constraint FK_COMENTARIO_EXCURSION foreign key (ID_EXCURSION)
      references EXCURSION (ID_EXCURSION);

alter table EXCURSION_ARTICULO
   add constraint FK_EXCURSIONARTICULO_ARTICULO foreign key (ID_ARTICULO)
      references ARTICULO (ID_ARTICULO);

alter table EXCURSION_ARTICULO
   add constraint FK_EXCURSIONARTICULO_EXCURSION foreign key (ID_EXCURSION)
      references EXCURSION (ID_EXCURSION);

alter table ITINERARIO
   add constraint FK_ITINERARIO_ACTIVIDAD foreign key (ID_ACTIVIDAD)
      references ACTIVIDAD (ID_ACTIVIDAD);

alter table ITINERARIO
   add constraint FK_ITINERARIO_SITIO foreign key (ID_SITIO)
      references SITIO (ID_SITIO);

alter table ITINERARIO
   add constraint FK_ITINERARIO_EXCURSION foreign key (ID_EXCURSION)
      references EXCURSION (ID_EXCURSION);

alter table PROVINCIA
   add constraint FK_PROVINCIA_PAIS foreign key (ID_PAIS)
      references PAIS (ID_PAIS);

alter table SITIO
   add constraint FK_SITIO_CIUDAD foreign key (ID_CIUDAD)
      references CIUDAD (ID_CIUDAD);

alter table USUARIO_EXCURSION
   add constraint FK_USUARIOEXCURSION_EXCURSION foreign key (ID_EXCURSION)
      references EXCURSION (ID_EXCURSION);

alter table USUARIO_EXCURSION
   add constraint FK_USUARIOEXCURSION_MOCHILA foreign key (ID_MOCHILA)
      references MOCHILA (ID_MOCHILA);

alter table USUARIO_EXCURSION
   add constraint FK_USUARIOEXCURSION_USUARIO foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO);

