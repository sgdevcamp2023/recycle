create table invalid_access_token_log
(
    id             bigint       not null auto_increment,
    create_at      datetime(6)  not null,
    deleted        bit          not null,
    update_at      datetime(6)  not null,
    black_token_fk bigint       not null,
    ip             varchar(255) not null,
    resource       json,
    useragent      varchar(255) not null,
    primary key (id)
);
