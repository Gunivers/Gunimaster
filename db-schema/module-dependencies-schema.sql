create table "module-dependencies"
(
    id         bigserial not null,
    dependent  uuid      not null
        constraint "module-dependencies_module_dependent_uuid_fk"
            references module (uuid)
            on delete cascade,
    dependency uuid      not null
        constraint "module-dependencies_module_dependency_uuid_fk"
            references module (uuid)
            on update cascade on delete set null
);

alter table "module-dependencies"
    owner to postgres;

create unique index "module-dependencies_id_uindex"
    on "module-dependencies" (id);
