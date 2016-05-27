/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     24/05/2016 16:41:56                          */
/*==============================================================*/


alter table MONS_DEPARTAMENTO
   drop primary key;

drop table if exists MONS_DEPARTAMENTO;

alter table MONS_DETALLE_HORAS
   drop primary key;

drop table if exists MONS_DETALLE_HORAS;

alter table MONS_DETALLE_LUGAR
   drop primary key;

drop table if exists MONS_DETALLE_LUGAR;

alter table MONS_DIRECTOR
   drop primary key;

drop table if exists MONS_DIRECTOR;

alter table MONS_EMPLEADO
   drop primary key;

drop table if exists MONS_EMPLEADO;

alter table MONS_FAMILIAR
   drop primary key;

drop table if exists MONS_FAMILIAR;

alter table MONS_HORAS
   drop primary key;

drop table if exists MONS_HORAS;

alter table MONS_LUGAR
   drop primary key;

drop table if exists MONS_LUGAR;

alter table MONS_PROYECTO
   drop primary key;

drop table if exists MONS_PROYECTO;

alter table MONS_USER
   drop primary key;

drop table if exists MONS_USER;

/*==============================================================*/
/* Table: MONS_DEPARTAMENTO                                     */
/*==============================================================*/
create table MONS_DEPARTAMENTO
(
   DEPA_ID              int not null,
   DEPA_NOMBRE          varchar(50) not null,
   DEPA_NUMERO          varchar(10) not null,
   DEPA_DETALLE         varchar(150)
);

alter table MONS_DEPARTAMENTO
   add primary key (DEPA_ID);

/*==============================================================*/
/* Table: MONS_DETALLE_HORAS                                    */
/*==============================================================*/
create table MONS_DETALLE_HORAS
(
   PROY_ID              int not null,
   EMPL_ID              int not null
);

alter table MONS_DETALLE_HORAS
   add primary key (PROY_ID, EMPL_ID);

/*==============================================================*/
/* Table: MONS_DETALLE_LUGAR                                    */
/*==============================================================*/
create table MONS_DETALLE_LUGAR
(
   LUGA_ID              int not null,
   DEPA_ID              int not null
);

alter table MONS_DETALLE_LUGAR
   add primary key (LUGA_ID, DEPA_ID);

/*==============================================================*/
/* Table: MONS_DIRECTOR                                         */
/*==============================================================*/
create table MONS_DIRECTOR
(
   DIRE_ID              int not null,
   EMPL_ID              int,
   DEPA_ID              int not null,
   DIRE_FECHA           date not null,
   DIRE_DETALLE         varchar(200)
);

alter table MONS_DIRECTOR
   add primary key (DIRE_ID);

/*==============================================================*/
/* Table: MONS_EMPLEADO                                         */
/*==============================================================*/
create table MONS_EMPLEADO
(
   EMPL_ID              int not null,
   DEPA_ID              int,
   EMPL_NOMBRE          varchar(100) not null,
   EMPL_SEGURO          varchar(20) not null,
   EMPL_DIRECCION       varchar(100),
   EMPL_SALARIO         numeric(7,2) not null,
   EMPL_SEXO            char(1),
   EMPL_FECNAC          date not null
);

alter table MONS_EMPLEADO
   add primary key (EMPL_ID);

/*==============================================================*/
/* Table: MONS_FAMILIAR                                         */
/*==============================================================*/
create table MONS_FAMILIAR
(
   FAMI_ID              int not null,
   EMPL_ID              int,
   FAMI_NOMBRE          varchar(100) not null,
   FAMI_PARENTEZCO      varchar(50) not null,
   FAMI_FECHANACIMIENTO date not null,
   FAMI_DETALLE         varchar(200)
);

alter table MONS_FAMILIAR
   add primary key (FAMI_ID);

/*==============================================================*/
/* Table: MONS_HORAS                                            */
/*==============================================================*/
create table MONS_HORAS
(
   PROY_ID              int not null,
   EMPL_ID              int not null,
   HORA_ID              int not null,
   HORA_REG_FECHA       timestamp,
   HORA_REG_HORAS       numeric(2,2) not null,
   HORA_DETALLE         varchar(200),
   HORA_SEMANA          varchar(10) not null,
   HORA_MES             varchar(10) not null,
   HORA_ANIO            varchar(5) not null
);

alter table MONS_HORAS
   add primary key (HORA_ID);

/*==============================================================*/
/* Table: MONS_LUGAR                                            */
/*==============================================================*/
create table MONS_LUGAR
(
   LUGA_ID              int not null,
   LUGA_CIIUDAD         varchar(50) not null,
   LUGA_SECTOR          varchar(100),
   LUGA_NOMBRE          varchar(50) not null,
   LUGA_TORRE           varchar(10),
   LUGA_PISO            varchar(10),
   LUGA_ZONA            varchar(50)
);

alter table MONS_LUGAR
   add primary key (LUGA_ID);

/*==============================================================*/
/* Table: MONS_PROYECTO                                         */
/*==============================================================*/
create table MONS_PROYECTO
(
   PROY_ID              int not null,
   EMPL_ID              int,
   LUGA_ID              int,
   DEPA_ID              int,
   PROY_NUMERO          varchar(10) not null,
   PROY_NOMBRE          varchar(50) not null,
   PROY_DETALLE         varchar(200)
);

alter table MONS_PROYECTO
   add primary key (PROY_ID);

/*==============================================================*/
/* Table: MONS_USER                                             */
/*==============================================================*/
create table MONS_USER
(
   USER_ID              int not null,
   USER_PASWORD         varchar(20) not null,
   USER_NOMBRE          varchar(20) not null,
   USER_GRUPO           varchar(10),
   USER_CORREO          varchar(50)
);

alter table MONS_USER
   add primary key (USER_ID);

alter table MONS_DETALLE_HORAS add constraint FK_EMPLE_HORAS foreign key (EMPL_ID)
      references MONS_EMPLEADO (EMPL_ID) on delete restrict on update restrict;

alter table MONS_DETALLE_HORAS add constraint FK_PROYE_HORAS foreign key (PROY_ID)
      references MONS_PROYECTO (PROY_ID) on delete restrict on update restrict;

alter table MONS_DETALLE_LUGAR add constraint FK_DEPAR_DETAL foreign key (DEPA_ID)
      references MONS_DEPARTAMENTO (DEPA_ID) on delete restrict on update restrict;

alter table MONS_DETALLE_LUGAR add constraint FK_LUGAR_DETAL foreign key (LUGA_ID)
      references MONS_LUGAR (LUGA_ID) on delete restrict on update restrict;

alter table MONS_DIRECTOR add constraint FK_DIREC_DEPAR foreign key (DEPA_ID)
      references MONS_DEPARTAMENTO (DEPA_ID) on delete restrict on update restrict;

alter table MONS_DIRECTOR add constraint FK_EMPLE_DIREC foreign key (EMPL_ID)
      references MONS_EMPLEADO (EMPL_ID) on delete restrict on update restrict;

alter table MONS_EMPLEADO add constraint FK_DEPAR_EMPLE foreign key (DEPA_ID)
      references MONS_DEPARTAMENTO (DEPA_ID) on delete restrict on update restrict;

alter table MONS_FAMILIAR add constraint FK_EMPLE_FAMIL foreign key (EMPL_ID)
      references MONS_EMPLEADO (EMPL_ID) on delete restrict on update restrict;

alter table MONS_HORAS add constraint FK_DETAL_HORAS foreign key (PROY_ID, EMPL_ID)
      references MONS_DETALLE_HORAS (PROY_ID, EMPL_ID) on delete restrict on update restrict;

alter table MONS_PROYECTO add constraint FK_DEPAR_PROY foreign key (DEPA_ID)
      references MONS_DEPARTAMENTO (DEPA_ID) on delete restrict on update restrict;

alter table MONS_PROYECTO add constraint FK_LUGAR_PROYE foreign key (LUGA_ID)
      references MONS_LUGAR (LUGA_ID) on delete restrict on update restrict;

alter table MONS_PROYECTO add constraint FK_SUPERVISOR foreign key (EMPL_ID)
      references MONS_EMPLEADO (EMPL_ID) on delete restrict on update restrict;

