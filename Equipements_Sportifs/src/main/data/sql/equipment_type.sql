create table equipment_type
(
    equipment_type_code varchar(5)  not null
        primary key,
    equipment_type_lib  varchar(48) not null,
    equipment_famille   varchar(48) not null,
    equipment_categorie varchar(48) not null,
    constraint equipment_other_info_uk
        unique (equipment_type_lib)
);

