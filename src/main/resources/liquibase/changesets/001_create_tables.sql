create table data
(
    id          bigserial primary key,
    sensor_id   bigint    not null,
    timestamp   timestamp not null,
    measurement float     not null,
    type        varchar   not null
);