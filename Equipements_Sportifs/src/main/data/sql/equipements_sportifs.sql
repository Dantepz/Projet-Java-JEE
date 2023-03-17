create schema if not exists equipements_sportifs;

create table if not exists equipment_type
(
    equipment_type_code varchar(5)  not null
        primary key,
    equipment_type_lib  varchar(48) not null,
    equipment_famille   varchar(48) not null,
    equipment_categorie varchar(48) not null,
    constraint equipment_other_info_uk
        unique (equipment_type_lib)
);

create table if not exists equipement_sportif
(
    equipment_id           varchar(15) not null
        primary key,
    equ_nom                varchar(48) not null,
    com_insee              varchar(6)  not null,
    ins_numero_install     varchar(11) not null,
    ins_nom                varchar(48) not null,
    nature_libelle         varchar(48) not null,
    equ_surface_evolution  float       null,
    equip_gpsx             float       not null,
    equip_gpsy             float       not null,
    equipment_type_code_id varchar(5)  not null,
    constraint equipement_sportif_equipment_type_equipment_type_code_fk
        foreign key (equipment_type_code_id) references equipment_type (equipment_type_code)
);

