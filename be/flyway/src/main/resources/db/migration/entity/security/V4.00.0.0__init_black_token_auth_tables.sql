create table black_token_auth
(
    id         bigint       not null auto_increment,
    create_at  datetime(6)  not null,
    deleted    bit          not null,
    update_at  datetime(6)  not null,
    token      longtext     not null,
    token_type varchar(255) not null,
    primary key (id)
);
