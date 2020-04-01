create table module
(
    id bigserial not null,
    uuid uuid default uuid_generate_v4() not null,
    name text not null,
    description text not null,
    version text not null,
    url text not null
);

create unique index module_id_uindex
    on module (id);

create unique index module_uuid_uindex
    on module (uuid);

alter table module
    add constraint module_pk
        primary key (id);

