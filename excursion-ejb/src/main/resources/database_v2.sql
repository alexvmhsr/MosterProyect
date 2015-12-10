/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     09/12/2015 23:48:52                          */
/*==============================================================*/


alter table ARCHIVO
   drop constraint FK_ARCHIVO_EXCURSION;

alter table CIUDAD
   drop constraint FK_CIUDAD_PROVINCIA;

alter table COMENTARIO
   drop constraint FK_COMENTARIO_EXCURSION;

alter table DETALLE
   drop constraint FK_DETALLE_DETALLE_A_ARTICULO;

alter table DETALLE
   drop constraint FK_DETALLE_DETALLE_F_FACTURA;

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

drop table DETALLE cascade constraints;

drop table EXCURSION cascade constraints;

drop index EXCURSION_ARTICULO2_FK;

drop index EXCURSION_ARTICULO_FK;

drop table EXCURSION_ARTICULO cascade constraints;

drop table FACTURA cascade constraints;

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

DROP SEQUENCE ARTICULO_SEQ;

DROP SEQUENCE ARCHIVO_SEQ;

DROP SEQUENCE SITIO_SEQ;

DROP SEQUENCE COMENTARIO_SEQ;

DROP SEQUENCE EXCURSION_SEQ;

DROP SEQUENCE ACTIVIDAD_SEQ;

DROP SEQUENCE USUARIO_SEQ;

DROP SEQUENCE MOCHILA_SEQ;

DROP SEQUENCE FACTURA_SEQ;

DROP SEQUENCE DETALLE_SEQ;

DROP SEQUENCE USUARIO_EXCURSION_SEQ;

/*==============================================================*/
/* Table: ACTIVIDAD                                             */
/*==============================================================*/
create table ACTIVIDAD 
(
   ID_ACTIVIDAD         INTEGER              not null,
   NOMBRE_ACTIVIDAD     VARCHAR2(32)         not null,
   DESCRIPCION_ACTIVIDAD VARCHAR2(256),
   COSTO_ACTIVIDAD      NUMBER(5,2),
   constraint PK_ACTIVIDAD primary key (ID_ACTIVIDAD)
);

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
   PRECIO_ARTICULO      NUMBER(8,2)          not null,
   constraint PK_ARTICULO primary key (ID_ARTICULO)
);

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
/* Table: DETALLE                                               */
/*==============================================================*/
create table DETALLE 
(
   ID_DETALLE           INTEGER              not null,
   ID_FACTURA           INTEGER,
   ID_ARTICULO          INTEGER,
   CANTIDAD_DETALLE     INTEGER              not null,
   DESCUENTO_DETALLE    NUMBER(8,2),
   constraint PK_DETALLE primary key (ID_DETALLE)
);

comment on table DETALLE is
'Aqui tenemos los detalles de cada factura';

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
/* Table: FACTURA                                               */
/*==============================================================*/
create table FACTURA 
(
   ID_FACTURA           INTEGER              not null,
   TIPO_DOC_EMISOR      VARCHAR2(2)          not null,
   TIPO_DOC_RECEPTOR    VARCHAR2(2)          not null,
   DOC_EMISOR           VARCHAR2(20)         not null,
   DOC_RECEPTOR         VARCHAR2(20)         not null,
   RAZON_SOCIAL_EMISOR  VARCHAR2(200)        not null,
   DIRECCION_EMISOR     VARCHAR2(200),
   SECUENCIAL           VARCHAR2(15)         not null,
   FECHA_EMISION        TIMESTAMP            not null,
   FECHA_AUTORIZACION   TIMESTAMP,
   NUMERO_AUTORIZACION  VARCHAR2(37),
   CLAVE_ACCESO         VARCHAR2(49)         not null,
   SUBTOTAL             NUMBER(8,2)          not null,
   TOTAL                NUMBER(8,2)          not null,
   DESCUENTO            NUMBER(8,2),
   constraint PK_FACTURA primary key (ID_FACTURA)
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
   RESPONSABLE          VARCHAR2(200),
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

alter table DETALLE
   add constraint FK_DETALLE_DETALLE_A_ARTICULO foreign key (ID_ARTICULO)
      references ARTICULO (ID_ARTICULO);

alter table DETALLE
   add constraint FK_DETALLE_DETALLE_F_FACTURA foreign key (ID_FACTURA)
      references FACTURA (ID_FACTURA);

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

CREATE SEQUENCE ARTICULO_SEQ;
CREATE OR REPLACE TRIGGER ARTICULO_TRI 
BEFORE INSERT ON ARTICULO 
FOR EACH ROW
BEGIN
IF :NEW.ID_ARTICULO IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT ARTICULO_SEQ.NEXTVAL
          INTO   :NEW.ID_ARTICULO
              FROM   dual;
  END IF;
  END;
  /
CREATE SEQUENCE ARCHIVO_SEQ;
CREATE OR REPLACE TRIGGER ARCHIVO_TRI 
BEFORE INSERT ON ARCHIVO 
FOR EACH ROW
BEGIN
IF :NEW.ID_ARCHIVO IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT ARCHIVO_SEQ.NEXTVAL
          INTO   :NEW.ID_ARCHIVO
              FROM   dual;
  END IF;
  END;
  /
CREATE SEQUENCE SITIO_SEQ;
CREATE OR REPLACE TRIGGER SITIO_TRI 
BEFORE INSERT ON SITIO 
FOR EACH ROW
BEGIN
IF :NEW.ID_SITIO IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT SITIO_SEQ.NEXTVAL
          INTO   :NEW.ID_SITIO
              FROM   dual;
  END IF;
  END;
  /
CREATE SEQUENCE COMENTARIO_SEQ;
CREATE OR REPLACE TRIGGER COMENTARIO_TRI 
BEFORE INSERT ON COMENTARIO 
FOR EACH ROW
BEGIN
IF :NEW.ID_COMENTARIO IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT COMENTARIO_SEQ.NEXTVAL
          INTO   :NEW.ID_COMENTARIO
              FROM   dual;
  END IF;
  END;
  /
CREATE SEQUENCE EXCURSION_SEQ;
CREATE OR REPLACE TRIGGER EXCURSION_TRI 
BEFORE INSERT ON EXCURSION 
FOR EACH ROW
BEGIN
IF :NEW.ID_EXCURSION IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT EXCURSION_SEQ.NEXTVAL
          INTO   :NEW.ID_EXCURSION
              FROM   dual;
  END IF;
  END;
  /
CREATE SEQUENCE ACTIVIDAD_SEQ;
CREATE OR REPLACE TRIGGER ACTIVIDAD_TRI 
BEFORE INSERT ON ACTIVIDAD 
FOR EACH ROW
BEGIN
IF :NEW.ID_ACTIVIDAD IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT ACTIVIDAD_SEQ.NEXTVAL
          INTO   :NEW.ID_ACTIVIDAD
              FROM   dual;
  END IF;
  END;
  /

CREATE SEQUENCE USUARIO_SEQ;
CREATE OR REPLACE TRIGGER USUARIO_TRI 
BEFORE INSERT ON USUARIO 
FOR EACH ROW
BEGIN
IF :NEW.ID_USUARIO IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT USUARIO_SEQ.NEXTVAL
          INTO   :NEW.ID_USUARIO
              FROM   dual;
  END IF;
  END;
  /
CREATE SEQUENCE MOCHILA_SEQ;
CREATE OR REPLACE TRIGGER MOCHILA_TRI 
BEFORE INSERT ON MOCHILA 
FOR EACH ROW
BEGIN
IF :NEW.ID_MOCHILA IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT MOCHILA_SEQ.NEXTVAL
          INTO   :NEW.ID_MOCHILA
              FROM   dual;
  END IF;
  END;
  /
CREATE SEQUENCE FACTURA_SEQ;
CREATE OR REPLACE TRIGGER FACTURA_TRI 
BEFORE INSERT ON FACTURA 
FOR EACH ROW
BEGIN
IF :NEW.ID_FACTURA IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT FACTURA_SEQ.NEXTVAL
          INTO   :NEW.ID_FACTURA
              FROM   dual;
  END IF;
  END;
  /
CREATE SEQUENCE DETALLE_SEQ;
CREATE OR REPLACE TRIGGER DETALLE_TRI 
BEFORE INSERT ON DETALLE 
FOR EACH ROW
BEGIN
IF :NEW.ID_DETALLE IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT DETALLE_SEQ.NEXTVAL
          INTO   :NEW.ID_DETALLE
              FROM   dual;
  END IF;
  END;
  /
CREATE SEQUENCE USUARIO_EXCURSION_SEQ;
CREATE OR REPLACE TRIGGER USUARIO_EXCURSION_TRI 
BEFORE INSERT ON USUARIO_EXCURSION 
FOR EACH ROW
BEGIN
IF :NEW.ID_USUARIO_EXCURSION IS NOT NULL THEN
    RAISE_APPLICATION_ERROR(-20000, 'ID cannot be specified');
  ELSE
      SELECT USUARIO_EXCURSION_SEQ.NEXTVAL
          INTO   :NEW.ID_USUARIO_EXCURSION
              FROM   dual;
  END IF;
  END;
  /
  